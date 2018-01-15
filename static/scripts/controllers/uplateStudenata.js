'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:UplateCtrl
 * @description
 * # UplateCtrl
 * Controller of the studentsClientApp
 */
angular.module('studentsClientApp')
  .controller('UplateCtrl', ['$scope', 'localStorageService', 'Restangular', '$uibModal', '$log', '_', function($scope, localStorageService, Restangular, $uibModal, $log, _) {
    
	$scope.uloga = localStorageService.get('uloga');
	
	if($scope.uloga==null){
		window.location.href = "#/login";
	}
	
	if($scope.uloga=='administrator'){
		
	$scope.navUrl = '#/welcome'
	$scope.navStr = '<-- Na pocetnu stranu'
		
    Restangular.all("uplatestudenata").getList().then(function(entries) {
      // nakon sto resursi stgnu sa sa back enda, postavimo ih u $scope da bismo mogli da ih prikazemo na stranici
      $scope.uplatestudenata = entries;
    });
	}
	if($scope.uloga=='nastavnik'){
		document.getElementById("ddj").style.visibility = "hidden";
		document.getElementById("container").style.visibility = "hidden";
		alert("Neovlascen pristup!");
	}
	
	if($scope.uloga=='student'){
		document.getElementById("ddj").style.visibility = "hidden";
		document.getElementById("container").style.visibility = "hidden";
		alert("Neovlascen pristup!");
	}
	
	$scope.logout = function(){
		localStorage.clear(); 
		window.location.href = "#/login";
	}

    $scope.deleteUplata = function(id) {
      Restangular.one("uplatestudenata", id).remove().then(function() {
        // uklanjamo studenta sa zadatim id-om iz kolekcije
        _.remove($scope.uplatestudenata, {
          id: id
        });
      }, function() {
        $log.info("greska");
      });
    };
    //Kontroler za uplate modal
    var UplateModalCtrl = ['$scope', '$uibModalInstance', 'uplata', 'Restangular', '$log', '_',
      function($scope, $uibModalInstance, uplata, Restangular, $log, _) {
        $scope.uplata = uplata;
        Restangular.all("students").getList().then(function(entries) {
           $scope.students = entries;
          });
        $scope.datumUplate={};
        $scope.openDatumUplate = function() {
            $scope.datumUplate.opened = true;
          };
        if ($scope.uplata.id) {
          Restangular.one("uplatestudenata", $scope.uplata.id).getList("students").then(function(entries) {
			  console.log(entries);
            $scope.enrollments = entries;
          });
        }
        
          
        $scope.ok = function() {
          if ($scope.uplata.id) {
            Restangular.all('uplatestudenata').customPUT($scope.uplata).then(function (data) {         	
              var index = _.indexOf($scope.uplatestudenata, _.find($scope.uplatestudenata, {id: $scope.uplata.id}));
              $scope.uplatestudenata.splice(index, 1, data);
            });
          } else {
        	  $scope.uplata.student={"id":$scope.student.id};
            Restangular.all('uplatestudenata').post($scope.uplata).then(function (data) {
              $scope.uplatestudenata.push(data);
            },
              // callback za gresku sa servera
              function() {
                $log.info('wrong input info');
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
          
          /*// Kontroler za student modal
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
            


            $scope.ok = function() {
              $scope.enrollment.student={"id":$scope.student.id};
              console.log($scope.enrollment);
              Restangular.all('enrollment').post($scope.enrollment).then(function (data) {
                  $scope.enrollments.push(data);
              });
              $uibModalInstance.close('ok');
            };

            $scope.cancel = function() {
              $uibModalInstance.dismiss('cancel');
            };

          }];
          
          $scope.openModal = function() {
              $scope.enrollment = {"uplata":{"id":$scope.uplata.id}};
              var modalInstance = $uibModal.open({
                templateUrl: 'views/modals/studentEnrollment.html',
                controller: StudentEnrollmentModalCtrl,
                scope: $scope,
                resolve: {
                  uplata: function() {
                    return uplata;
                  }
                }
              });
              modalInstance.result.then(function(value) {
                $log.info('Modal finished its job at: ' + new Date() + ' with value: ' + value);
              }, function(value) {
                $log.info('Modal dismissed at: ' + new Date() + ' with value: ' + value);
              });
            };*/
      }
    ];

    $scope.openModal = function(uplata) {

      if (!uplata) {
    	  uplata = {
          id: '',
          datumUplate: '',
          svrhaUplate: ''
        };
      }

      var modalInstance = $uibModal.open({
          templateUrl: 'views/modals/uplata.html',
          controller: UplateModalCtrl,
          scope: $scope,
          resolve: {
        	  uplata: function() {
              return uplata;
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
