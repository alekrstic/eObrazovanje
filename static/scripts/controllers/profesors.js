'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:ProfesorsCtrl
 * @description
 * # ProfesorsCtrl
 * Controller of the studentsClientApp
 */
angular.module('studentsClientApp')
  .controller('ProfesorsCtrl', ['$scope', '$http', 'Restangular', '$uibModal', '$log', '_', 'localStorageService', function($scope, $http, Restangular, $uibModal, $log, _, localStorageService) {

	$scope.uloga = localStorageService.get('uloga');
	
	if($scope.uloga==null){
		window.location.href = "#/login";
	}
	
    if($scope.uloga=='administrator'){
		
	$scope.navUrl = '#/welcome'
	$scope.navStr = '<-- Na pocetnu stranu'
		
    $http.get('api/profesors/').then(function(entries) {
      // nakon sto resursi stignu sa back enda, postavimo ih u $scope da bismo mogli da ih prikazemo na stranici
      $scope.profesors = entries;
      
      $scope.sort = function(keyname){
          $scope.sortKey = keyname;   //set the sortKey to the param passed
          $scope.reverse = !$scope.reverse; //if true make it false and vice versa
      }
    });
	}
	
	if($scope.uloga=='nastavnik'){
		
		document.getElementById("ddj").style.visibility = "hidden";
		
		var ime = localStorageService.get('ime');
		var prezime = localStorageService.get('prezime');
		var authParams = {"ime":ime,"prezime":prezime};
		
	  $http.post('api/profesors/findPrezimeAndIme', authParams).then(function(entries) {
        $scope.profesors = entries;
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
	
    $scope.deleteProfesor = function(id) {
	 if($scope.uloga=='administrator'){
      Restangular.one("profesors", id).remove().then(function() {
        // uklanjamo profesora sa zadatim id-om iz kolekcije
        _.remove($scope.profesors.data, {
          id: id
        });
      }, function() {
        $log.info("the profesor cannot be removed since they are enrolled to some courses");
	 });}
	 
	 if($scope.uloga=='nastavnik'){
		 alert('Ne mozete obrisati svoje podatke, obratite se administratoru!');
	 }
	 
    };

    var ProfesorsModalCtrl = ['$scope', '$uibModalInstance', 'profesor', 'Restangular', '$log', '_',
      function($scope, $uibModalInstance, profesor, Restangular, $log, _) {
        $scope.profesor = profesor;
        if ($scope.profesor.id) {
          Restangular.one("profesors", $scope.profesor.id).getList("courses").then(function(entries) {
            $scope.enrollments = entries;
          });
        }

        $scope.ok = function() {
          if ($scope.profesor.id) {
            Restangular.all('profesors').customPUT($scope.profesor).then(function (data) {
              var index = _.indexOf($scope.profesors, _.find($scope.profesors, {id: $scope.profesor.id}));
              $scope.profesors.splice(index, 1, data);
            });
          } else {
            Restangular.all('profesors').post($scope.profesor).then(function (data) {
              $scope.profesors.data.push(data);
            },
              // callback za gresku sa servera
              function() {
                $log.info('the profesors with such a brIndexa already exists');
              });
          }
          $uibModalInstance.close('ok');
        };

        $scope.cancel = function() {
          $uibModalInstance.dismiss('cancel');
        };
      }
    ];

    $scope.openModal = function(profesor) {

      if (!profesor) {
    	  profesor = {
          ime: '',
          prezime: '',
          cast: '',
          mail: ''
        };
      }

      var modalInstance = $uibModal.open({
        templateUrl: 'views/modals/profesor.html',
        controller: ProfesorsModalCtrl,
        scope: $scope,
        resolve: {
        	profesor: function() {
            return profesor;
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
