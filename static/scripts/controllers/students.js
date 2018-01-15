'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:StudentsCtrl
 * @description
 * # StudentsCtrl
 * Controller of the studentsClientApp
 */
angular.module('studentsClientApp')
  .controller('StudentsCtrl', ['$scope','$http', 'Restangular', '$uibModal', '$log', '_','localStorageService', function($scope, $http, Restangular, $uibModal, $log, _, localStorageService) {

	$scope.uloga = localStorageService.get('uloga');
	
	if($scope.uloga==null){
		window.location.href = "#/login";
	}
	
	if($scope.uloga=='administrator'){
		
	$scope.navUrl = '#/welcome'
	$scope.navStr = '<-- Na pocetnu stranu'
		
    $http.get('api/students/').then(function(entries) {
      // nakon sto resursi stignu sa sa back enda, postavimo ih u $scope da bismo mogli da ih prikazemo na stranici
      $scope.students = entries;
      
      $scope.sort = function(keyname){
          $scope.sortKey = keyname;   //set the sortKey to the param passed
          $scope.reverse = !$scope.reverse; //if true make it false and vice versa
      }
    });
	}
	
	if($scope.uloga=='student'){
		
		document.getElementById("ddj").style.visibility = "hidden";
		document.getElementById("container").style.visibility = "hidden";
		alert("Neovlascen pristup!");
		/*
		var ime = localStorageService.get('ime');
		var prezime = localStorageService.get('prezime');
		var authParams = {"ime":ime,"prezime":prezime};
		
		
	$http.post('api/students/findPrezimeAndIme', authParams).then(function(entries) {
      $scope.students = entries;
	  
	      $scope.sort = function(keyname){
          $scope.sortKey = keyname;  
          $scope.reverse = !$scope.reverse; 
          }
	  
          }); */
	}
	
	if($scope.uloga=='nastavnik'){
		document.getElementById("ddj").style.visibility = "hidden";
		document.getElementById("container").style.visibility = "hidden";
		alert("Neovlascen pristup!")
	}
	
	$scope.logout = function(){
		localStorage.clear(); 
		window.location.href = "#/login";
	}
	
    $scope.deleteStudent = function(id) {
      Restangular.one("students", id).remove().then(function() {
        // uklanjamo studenta sa zadatim id-om iz kolekcije
        _.remove($scope.students.data, {
          id: id
        });
      }, function() {
        $log.info("the student cannot be removed since they are enrolled to some courses");
      });
    };

    var StudentsModalCtrl = ['$scope', '$uibModalInstance', 'student', 'Restangular', '$log', '_',
      function($scope, $uibModalInstance, student, Restangular, $log, _) {
        $scope.student = student;
        if ($scope.student.id) {
          Restangular.one("students", $scope.student.id).getList("courses").then(function(entries) {
            $scope.enrollments = entries;
          });
          Restangular.one("students", $scope.student.id).getList("uplatestudenata").then(function(entries) {
              $scope.uplata = entries;
            });
          Restangular.one("students", $scope.student.id).getList("polaganjaispitia").then(function(entries) {
              $scope.exam = entries;
            });
        }

        $scope.ok = function() {
        	//za izmenu ako postoji student
          if ($scope.student.id) {
            Restangular.all('students').customPUT($scope.student).then(function (data) {
              var index = _.indexOf($scope.students, _.find($scope.students, {id: $scope.student.id}));
              $scope.students.splice(index, 1, data);
            });
            //kad dodajemo novog studenta
          } else {
            Restangular.all('students').post($scope.student).then(function (data) {
				console.log($scope.students);
              $scope.students.data.push(data);
            },
              // callback za gresku sa servera
              function() {
                $log.info('the student with such a brIndexa already exists');
              });
          }
          $uibModalInstance.close('ok');
        };

        $scope.cancel = function() {
          $uibModalInstance.dismiss('cancel');
        };
      }
    ];

    $scope.openModal = function(student) {

      if (!student) {
        student = {
          brIndexa: '',
          ime: '',
          prezime: '',
          mail: '',
          adresa: '',
          godina: '',
          semestar: ''
        };
      }

      var modalInstance = $uibModal.open({
        templateUrl: 'views/modals/student.html',
        controller: StudentsModalCtrl,
        scope: $scope,
        resolve: {
          student: function() {
            return student;
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
