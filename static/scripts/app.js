'use strict';

/**
 * @ngdoc overview
 * @name studentsClientApp
 * @description
 * # studentsClientApp
 *
 * Main module of the application.
 */
var myApp = angular
  .module('studentsClientApp', [
    'ngResource',
    'ngRoute',
    'restangular',
    'ui.bootstrap',
    'lodash',
	'LocalStorageModule'
  ])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
       .when('/login', {
    	templateUrl: 'views/login.html',
		controller: 'LoginCtrl',
        controllerAs: 'korisnici'
      })
      .when('/welcome', {
        templateUrl: 'views/welcome.html'
      })
      .when('/students', {
        templateUrl: 'views/students.html',
        controller: 'StudentsCtrl',
        controllerAs: 'students'
      })
      .when('/courses', {
        templateUrl: 'views/courses.html',
        controller: 'CoursesCtrl',
        controllerAs: 'courses'
      })
      .when('/profesors', {
        templateUrl: 'views/profesors.html',
        controller: 'ProfesorsCtrl',
        controllerAs: 'profesors'
      })
       .when('/users', {
        templateUrl: 'views/users.html',
        controller: 'UsersCtrl',
        controllerAs: 'users'
      })
      .when('/students/:studentId', {
        templateUrl: 'views/studentDetails.html',
        controller: 'StudentDetailsCtrl',
        controllerAs: 'studentDetails'
      })
      .when('/courses/:courseId', {
        templateUrl: 'views/courseDetails.html',
        controller: 'CourseDetailsCtrl',
        controllerAs: 'courseDetails'
      })
      .when('/profesors/:profesorId', {
        templateUrl: 'views/profesorDetails.html',
        controller: 'ProfesorDetailsCtrl',
        controllerAs: 'profesorDetails'
      })
      .when('/uplate', {
        templateUrl: 'views/uplate.html',
        controller: 'UplateCtrl',
        controllerAs: 'uplateStudenata'
      })
      .when('/exams', {
        templateUrl: 'views/exams.html',
        controller: 'IspitiCtrl',
        controllerAs: 'polaganjeIspita'
      })
      .when('/uplate/:uplataId', {
        templateUrl: 'views/uplataDetails.html',
        controller: 'UplataDetailsCtrl',
        controllerAs: 'uplataDetails'
      })
      .when('/exams/:examId', {
        templateUrl: 'views/examDetails.html',
        controller: 'PolaganjaIspitaDetailsCtrl',
        controllerAs: 'polaganjaIspitaDetails'
      })
      .otherwise({
        redirectTo: '/login'
      });
  }])
  // run se izvrsava pre svega ostalog
  .run(['Restangular', '$log', function(Restangular, $log) {
    // postavimo base url za Restangular da ne bismo morali da ga
    // navodimo svaki put kada se obracamo back endu
    // poziv vrsimo na http://localhost:8080/api/
    Restangular.setBaseUrl("api");
    Restangular.setErrorInterceptor(function(response) {
      if (response.status === 500) {
        $log.info("internal server error");
        return true; // greska je obradjena
      }
      return true; // greska nije obradjena
    });
  }]);
