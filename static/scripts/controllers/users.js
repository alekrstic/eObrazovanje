'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller: UsersCtrl
 * @description
 * # UsersCtrl
 * Controller of the studentsClientApp
 */
angular.module('studentsClientApp')
  .controller('UsersCtrl', ['$scope','$http', 'Restangular', '$uibModal', '$log', '_','localStorageService', function($scope, $http, Restangular, $uibModal, $log, _, localStorageService) {

	$scope.uloga = localStorageService.get('uloga');
	
	if($scope.uloga==null){
		window.location.href = "#/login";
	}
	
	if($scope.uloga=='administrator'){
		
	$scope.navUrl = '#/welcome'
	$scope.navStr = '<-- Na pocetnu stranu'
		
    $http.get('api/korisnici/').then(function(entries) {
      // nakon sto resursi stignu sa sa back enda, postavimo ih u $scope da bismo mogli da ih prikazemo na stranici
      $scope.users = entries;
      
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
	
    $scope.deleteUser = function(id) {
      Restangular.one("korisnici", id).remove().then(function() {
        // uklanjamo korisnika sa zadatim id-om iz kolekcije
        _.remove($scope.users.data, {
          id: id
        });
      }, function() {
        $log.info("");
      });
    };

    var UsersModalCtrl = ['$scope', '$uibModalInstance', 'user', 'Restangular', '$log', '_',
      function($scope, $uibModalInstance, user, Restangular, $log, _) {
        $scope.user = user;
        if ($scope.user.id) {
         
        }

        $scope.ok = function() {
        	//za izmenu ako postoji korisnik
          if ($scope.user.id) {
            Restangular.all('korisnici').customPUT($scope.user).then(function (data) {
              var index = _.indexOf($scope.users, _.find($scope.users, {id: $scope.user.id}));
              $scope.users.splice(index, 1, data);
            });
            //kad dodajemo novog korisnika
          } else {
            Restangular.all('korisnici').post($scope.user).then(function (data) {
              $scope.users.data.push(data);
            },
              // callback za gresku sa servera
              function() {
                $log.info('the korisnik with such username already exists');
              });
          }
          $uibModalInstance.close('ok');
        };

        $scope.cancel = function() {
          $uibModalInstance.dismiss('cancel');
        };
      }
    ];

    $scope.openModal = function(user) {

      if (!user) {
        user = {
          ime: '',
          prezime: '',
          uloga: '',
          username: '',
          password: ''
        };
      }

      var modalInstance = $uibModal.open({
        templateUrl: 'views/modals/user.html',
        controller: UsersModalCtrl,
        scope: $scope,
        resolve: {
        	user: function() {
            return user;
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
