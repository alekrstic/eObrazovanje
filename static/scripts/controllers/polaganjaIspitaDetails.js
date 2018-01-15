'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:ExamDetailsCtrl
 * @description
 * # ExamDetailsCtrl
 * Controller of the studentsClientApp
 */

angular.module('studentsClientApp')
  .controller('PolaganjaIspitaDetailsCtrl', ['$scope', 'localStorageService','Restangular','$routeParams', function($scope, localStorageService, Restangular, $routeParams) {
	$scope.uloga = localStorageService.get('uloga');
	
	if($scope.uloga==null){
		window.location.href = "#/login";
	}
	//ovde preuzimas parametar iz rute koji ti je id ispita
	console.log($routeParams.examId);
	  $scope.id = $routeParams.examId;
	//Pravis rest poziv na kontroler koji ti vraca jednan ispit 
	  Restangular.one('polaganjaispita', $scope.id).get().then(function(data){
		 $scope.exam = data;
		 $scope.yesNo = 'Ne';
		 if($scope.exam.polozen){
			$scope.yesNo = 'Da'; 
		 } 
	  });
	  
	  	$scope.logout = function(){
		localStorage.clear(); 
		window.location.href = "#/login";
	}
	  
  }]);