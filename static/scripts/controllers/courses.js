'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:CoursesCtrl
 * @description
 * # CoursesCtrl
 * Controller of the studentsClientApp
 */
angular.module('studentsClientApp')
  .controller('CoursesCtrl', ['$scope', '$http', 'Restangular', '$uibModal', '$log', '_', 'localStorageService', function($scope, $http, Restangular, $uibModal, $log, _, localStorageService) {
    $scope.uloga = localStorageService.get('uloga');
	
	if($scope.uloga==null){
		window.location.href = "#/login";
	}
	
	if($scope.uloga=='administrator'){
	
	$scope.navUrl = '#/welcome'
	$scope.navStr = '<-- Na pocetnu stranu'
	
	$http.get("api/courses").then(function(entries) {
      $scope.courses = entries;
      
      $scope.sort = function(keyname){
          $scope.sortKey = keyname;   //set the sortKey to the param passed
          $scope.reverse = !$scope.reverse; //if true make it false and vice versa
      }
    });
	}
		
	if($scope.uloga=='nastavnik'){
		
		$scope.navUrl = '#/profesors'
	    $scope.navStr = '<-- Nazad na evidenciju profesora'	
		
		document.getElementById("ddj").style.visibility = "hidden";
		
		var ime = localStorageService.get('ime');
		var prezime = localStorageService.get('prezime');
		var authParams = {"ime":ime,"prezime":prezime};
		
	  $http.post('api/profesors/findPrezimeAndIme', authParams).then(function(entries) {
        var profesors = entries;
		var id = profesors.data[0].id;
		
		Restangular.one("profesors", id).getList("courses").then(function(entries) {
	      var predavanja = entries;
		  $scope.courses.data = [];
		  var i;
		  for(i=0; i<predavanja.length; i++){
			  $scope.courses.data.push(predavanja[i].course);
		  }
		  });
	      $scope.sort = function(keyname){
          $scope.sortKey = keyname;   //set the sortKey to the param passed
          $scope.reverse = !$scope.reverse; //if true make it false and vice versa
          }
	  
          });
	}
	
	if($scope.uloga=='student'){
		document.getElementById("container").style.visibility = "hidden";
		document.getElementById("ddj").style.visibility = "hidden";
		alert('Studenti nemaju pravo pristupa podacima na ovoj stranici!');
	}
	
    $scope.deleteCourse = function(id) {
	 if($scope.uloga=='administrator'){
      Restangular.one("courses", id).remove().then(function() {
        // uklanjamo studenta sa zadatim id-om iz kolekcije
        _.remove($scope.courses.data, {
          id: id
        });
      }, function() {
        $log.info("something went wrong");
      });
	 }
	 
	 if($scope.uloga=='nastavnik'){
		alert('Ne mozete obrisati kurs, obratite se administratoru!');
	 }
	 
    };
	
	$scope.logout = function(){
		localStorage.clear(); 
		window.location.href = "#/login";
	}

    var CourseModalCtrl = ['$scope', '$uibModalInstance', 'course', 'Restangular', '$log', '_',
        function($scope, $uibModalInstance, course, Restangular, $log, _) {
          $scope.course = course;
          if ($scope.course.id) {
            Restangular.one("courses", $scope.course.id).getList("students").then(function(entries) {
              $scope.enrollments = entries;
            });
            Restangular.one("courses", $scope.course.id).getList("profesors").then(function(entries) {
                $scope.predajePredmete = entries;
            });
           
          }

          $scope.ok = function() {
            if ($scope.course.id) {
              Restangular.all('courses').customPUT($scope.course).then(function (data) {
                var index = _.indexOf($scope.courses, _.find($scope.courses, {id: $scope.course.id}));
                $scope.courses.splice(index, 1, data);
              });
            } else {
              Restangular.all('courses').post($scope.course).then(function (data) {
                $scope.courses.data.push(data);
              },
                // callback za gresku sa servera
                function() {
                  $log.info('something went wrong!');
                });
            }
            $uibModalInstance.close('ok');
          };

          $scope.cancel = function() {
            $uibModalInstance.dismiss('cancel');
          };

          $scope.deleteEnrollment = function (id) {
            Restangular.one("enrollment", id).remove().then(function() {
              _.remove($scope.enrollments, {
                id: id
              });
            }, function() {
              $log.info("something went wrong");
            });
          };
          $scope.deletePredajePredmet = function (id) {
              Restangular.one("predajepredmete", id).remove().then(function() {
                _.remove($scope.predajePredmete, {
                  id: id
                });
              }, function() {
                $log.info("something went wrong");
              });
            };
          
          // Kontroler za student modal
          var StudentEnrollmentModalCtrl = ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance) {
            var enrolledStudentIds = _.map($scope.enrollments,function (value) {
              return value.student.id;
            });
            Restangular.all('students').getList().then(function (data) {
              $scope.students = data;
              _.remove($scope.students, function (student) {
                return _.contains(enrolledStudentIds, student.id);
              });
            });
            
            $scope.startDate={};
            $scope.openStartDate = function() {
                $scope.startDate.opened = true;
              };

            $scope.endDate={};
            $scope.openEndDate = function() {
                $scope.endDate.opened = true;
              };

            $scope.ok = function() {
              $scope.enrollment.student={"id":$scope.student.id};
              Restangular.all('enrollment').post($scope.enrollment).then(function (data) {
                  $scope.enrollments.push(data);
              });
			  $scope.polozen = 'false';
			  $scope.ocena = '5';
			  $scope.std = $scope.enrollment.student;
			  $scope.kurs = $scope.course;
			  
			  $scope.ispit = {'polozen':$scope.polozen,'ocena':$scope.ocena,'student':$scope.std,'course':$scope.kurs};
			  
			  console.log($scope.ispit);
			  
			   Restangular.all('polaganjaispita').post($scope.ispit).then(function (data) {
                  console.log(data);
              });
			  
              $uibModalInstance.close('ok');
            };

            $scope.cancel = function() {
              $uibModalInstance.dismiss('cancel');
            };

          }];

          $scope.openModal = function() {
            $scope.enrollment = {"course":{"id":$scope.course.id}};
            var modalInstance = $uibModal.open({
              templateUrl: 'views/modals/studentEnrollment.html',
              controller: StudentEnrollmentModalCtrl,
              scope: $scope,
              resolve: {
                course: function() {
                  return course;
                }
              }
            });
            modalInstance.result.then(function(value) {
              $log.info('Modal finished its job at: ' + new Date() + ' with value: ' + value);
            }, function(value) {
              $log.info('Modal dismissed at: ' + new Date() + ' with value: ' + value);
            });
          };
          
          // Kontroler za predaje predmet modal
          var PredajePredmetModalCtrl = ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance) {
              var predajePredmetProfesorIds = _.map($scope.predajePredmete,function (value) {
                return value.profesor.id;
              });
              Restangular.all('profesors').getList().then(function (data) {
                $scope.profesors = data;
                _.remove($scope.profesors, function (profesor) {
                  return _.contains(predajePredmetProfesorIds, profesor.id);
                });
              });

              $scope.ok = function() {
            	console.log($scope.profesor);

                $scope.predajePredmet.profesor={"id":$scope.profesor.id};
                console.log($scope.predajePredmet);
                Restangular.all('predajepredmete').post($scope.predajePredmet).then(function (data) {
                    $scope.predajePredmete.push(data);
                });
                $uibModalInstance.close('ok');
              };

              $scope.cancel = function() {
                $uibModalInstance.dismiss('cancel');
              };

            }];
          
          $scope.openModalProfesor = function() {
              $scope.predajePredmet = {"course":{"id":$scope.course.id}};
              var modalInstance = $uibModal.open({
                templateUrl: 'views/modals/predajePredmet.html',
                controller: PredajePredmetModalCtrl,
                scope: $scope,
                resolve: {
                  course: function() {
                    return course;
                  }
                }
              });
              modalInstance.result.then(function(value) {
                $log.info('Modal finished its job at: ' + new Date() + ' with value: ' + value);
              }, function(value) {
                $log.info('Modal dismissed at: ' + new Date() + ' with value: ' + value);
              });
            };

        }
      ];
	  
	  
	  

    $scope.openModal = function(course) {
      if (!course) {
        course = {
          naziv: ''
        };
      }
      var modalInstance = $uibModal.open({
        templateUrl: 'views/modals/course.html',
        controller: CourseModalCtrl,
        scope: $scope,
        resolve: {
          course: function() {
            return course;
          }
        }
      });
      modalInstance.result.then(function(value) {
        $log.info('Modal finished its job at: ' + new Date() + ' with value: ' + value);
      }, function(value) {
        $log.info('Modal dismissed at: ' + new Date() + ' with value: ' + value);
      });
    };

  }]);
