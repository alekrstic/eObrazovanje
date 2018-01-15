'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:UplataDetailsCtrl
 * @description
 * # UplataDetailsCtrl
 * Controller of the studentsClientApp
 */

angular.module('studentsClientApp')
  .controller('UplataDetailsCtrl', ['$scope', 'localStorageService', 'Restangular','$routeParams', function($scope,localStorageService, Restangular, $routeParams) {
	$scope.uloga = localStorageService.get('uloga');
	  
	if($scope.uloga==null){
		window.location.href = "#/login";
	}
	//ovde preuzimas parametar iz rute koji ti je id studenta
	  $scope.id = $routeParams.uplataId;
	//Pravis rest poziv na kontroler koji ti vraca jednu uplatu
	  Restangular.one('uplatestudenata', $scope.id).get().then(function(data){
		 $scope.uplata= data; 
	  });
	  
	  $scope.logout = function(){
		localStorage.clear(); 
		window.location.href = "#/login";
	}
	  
  }]);