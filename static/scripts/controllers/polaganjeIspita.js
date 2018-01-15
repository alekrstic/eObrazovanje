'use strict';


angular.module('studentsClientApp')
  .controller('IspitiCtrl', ['$scope', '$http', 'localStorageService', 'Restangular', '$uibModal', '$log', '_', function($scope, $http, localStorageService, Restangular, $uibModal, $log, _) {
    $scope.uloga = localStorageService.get('uloga');
	
	if($scope.uloga==null){
		window.location.href = "#/login";
	}
	
	if($scope.uloga=='administrator'){
		
		$scope.navUrl = '#/welcome'
	    $scope.navStr = '<-- Na pocetnu stranu'	
		
    Restangular.all("polaganjaispita").getList().then(function(entries) {
      // nakon sto resursi stgnu sa sa back enda, postavimo ih u $scope da bismo mogli da ih prikazemo na stranici
      $scope.exams = entries;
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
		  $scope.courses= [];
		  var i;
		  
		  for(i=0; i<predavanja.length; i++){
			  $scope.courses.push(predavanja[i].course);
			  
		  }
		  
		  var course;
		  
		  for(course in $scope.courses){
			Restangular.one("courses", id).getList("ispiti").then(function(entries) {
				$scope.exams = entries;
		  });
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
	
	$scope.logout = function(){
		localStorage.clear(); 
		window.location.href = "#/login";
	}

    $scope.deleteExam = function(id) {
	 if($scope.uloga=='administrator'){
      Restangular.one("polaganjaispita", id).remove().then(function() {
        // uklanjamo ispit sa zadatim id-om iz kolekcije
        _.remove($scope.exams, {
          id: id
        });
      }, function() {
        $log.info("ispit cannot be removed");
      });
	 }
	 
	 if($scope.uloga=='nastavnik'){
		alert('Ne mozete obrisati ispit, obratite se administratoru!');
	 }
	 
    };
    //Kontroler za ispite modal
    var IspitiModalCtrl = ['$scope', '$uibModalInstance', 'exam', 'Restangular', '$log', '_',
      function($scope, $uibModalInstance, exam, Restangular, $log, _) {
        $scope.exam = exam;
        Restangular.all("students").getList().then(function(entries) {
           $scope.students = entries;
          });
		Restangular.all("courses").getList().then(function(entries) {
           $scope.courses = entries;
          });
        if ($scope.exam.id) {
          Restangular.one("polaganjaispita", $scope.exam.id).getList("students").then(function(entries) {
            $scope.students = entries;
			console.log($scope.students);
          });
		  Restangular.one("polaganjaispita", $scope.exam.id).getList("courses").then(function(entries) {
            $scope.courses = entries;
          });
        }
		
        
          
        $scope.ok = function() {
          if ($scope.exam.id) {
            Restangular.all('polaganjaispita').customPUT($scope.exam).then(function (data) {         	
              var index = _.indexOf($scope.exams, _.find($scope.exams, {id: $scope.exam.id}));
              $scope.exams.splice(index, 1, data);
            });
          } else {
        	  $scope.exam.student={"id":$scope.student.id};
			  $scope.exam.course={"id":$scope.course.id};
            Restangular.all('polaganjaispita').post($scope.exam).then(function (data) {
              $scope.exams.push(data);
            },
              // callback za gresku sa servera
              function() {
                $log.info('doslo je do greske');
              });
          }
          $uibModalInstance.close('ok');
        };

        $scope.cancel = function() {
          $uibModalInstance.dismiss('cancel');
        };
        
        $scope.deleteExam = function (id) {
            Restangular.one("polaganjaispita", id).remove().then(function() {
              _.remove($scope.exams, {
                id: id
              });
            }, function() {
              $log.info("something went wrong");
            });
          };
          
      }
    ];

    $scope.openModal = function(exam) {

      if (!exam) {
    	  exam = {
          polozen: '',
          ocena: ''
        };
      }

      var modalInstance = $uibModal.open({
          templateUrl: 'views/modals/exam.html',
          controller: IspitiModalCtrl,
          scope: $scope,
          resolve: {
        	  exam: function() {
              return exam;
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
