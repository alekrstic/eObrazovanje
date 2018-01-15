'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:ProfesorDetailsCtrl
 * @description
 * # ProfesorDetailsCtrl
 * Controller of the studentsClientApp
 */

angular.module('studentsClientApp')
  .controller('ProfesorDetailsCtrl', ['$scope', 'localStorageService', 'Restangular','$routeParams', function($scope, localStorageService, Restangular, $routeParams) {
	$scope.uloga = localStorageService.get('uloga');
	
	if($scope.uloga==null){
		window.location.href = "#/login";
	}
	//ovde preuzimas parametar iz rute koji ti je id studenta
	  $scope.id = $routeParams.profesorId;
	//Pravis rest poziv na kontroler koji ti vraca jednog profesora
	  Restangular.one('profesors', $scope.id).get().then(function(data){
		 $scope.profesor= data; 
	  });
	  
	  Restangular.one("profesors", $scope.id).getList("courses").then(function(entries) {
	      // nakon sto resursi stgnu sa sa back enda, postavimo ih u $scope da bismo mogli da ih prikazemo na stranici
	      $scope.enrollments = entries;
	    });
		
		$scope.logout = function(){
		localStorage.clear(); 
		window.location.href = "#/login";
	}
	
  }]);