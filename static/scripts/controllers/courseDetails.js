'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:CourseDetailsCtrl
 * @description
 * # CourseDetailsCtrl
 * Controller of the studentsClientApp
 */

angular.module('studentsClientApp')
  .controller('CourseDetailsCtrl', ['$scope', 'localStorageService', 'Restangular','$routeParams', function($scope, localStorageService, Restangular, $routeParams) {
    $scope.uloga = localStorageService.get('uloga');
	
	if($scope.uloga==null){
		window.location.href = "#/login";
	}
	  
	//ovde preuzimas parametar iz rute koji ti je id studenta (parametar iz url-a)
	  $scope.id = $routeParams.courseId;
	  Restangular.one('courses', $scope.id).get().then(function(data){
		 $scope.course= data; 
	  });
	  
	  Restangular.one("courses", $scope.id).getList("students").then(function(entries) {
	      // nakon sto resursi stgnu sa sa back enda, postavimo ih u $scope da bismo mogli da ih prikazemo na stranici
	      $scope.enrollments = entries;
	    });
	  
	  Restangular.one("courses", $scope.id).getList("profesors").then(function(entries) {
	      // nakon sto resursi stgnu sa sa back enda, postavimo ih u $scope da bismo mogli da ih prikazemo na stranici
	      $scope.profesors = entries;
	    });
		
	  $scope.logout = function(){
		localStorage.clear(); 
		window.location.href = "#/login";
	}
		
  }]);