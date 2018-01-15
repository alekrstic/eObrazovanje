
angular.module('studentsClientApp')
  .controller('LoginCtrl', ['$rootScope','$scope', 'Restangular', '$uibModal', '$log', '_', '$http', 'localStorageService' , function($rootScope, $scope, Restangular, $uibModal, $log, _, $http, localStorageService) {
    

	$scope.username = "";
	$scope.password = "";
	

	
	$scope.login = function(){
		$scope.loginParams = {"username":$scope.username,"password":$scope.password};
		$http.post('api/korisnici/findUsername', $scope.loginParams).then(function(ulogovanKorisnik){
			
	    //Restangular.all('korisnici/findUsername').post($scope.loginParams).then(function(ulogovanKorisnik){
			
			localStorageService.set('uloga', ulogovanKorisnik.data.uloga);
			localStorageService.set('ime', ulogovanKorisnik.data.ime);
			localStorageService.set('prezime', ulogovanKorisnik.data.prezime);
			
			//console.log(localStorageService.get('ime'));
		//	console.log(localStorageService.get('prezime'));
		//	console.log(localStorageService.get('uloga'));
			

			$scope.identitet = {"ime":ulogovanKorisnik.data.ime,"prezime":ulogovanKorisnik.data.prezime};
			
			if ( ulogovanKorisnik.data.uloga == "student"){
				$http.post('api/students/findPrezimeAndIme', $scope.identitet).then(function(students){
				var lokacija1 = "#/students/";
				var lokacija2 = String(students.data[0].id);
				var lokacija = lokacija1.concat(lokacija2); 
				alert ("Student je ulogovan!");
				window.location.href = lokacija; // redirektovanje na drugu stranicu
				})
			}
			else if (ulogovanKorisnik.data.uloga == "nastavnik"){
				alert ("Nastavnik je ulogovan!");
				window.location.href = "#/profesors";
			}
			else if (ulogovanKorisnik.data.uloga == "administrator"){
				alert ("Administrator je ulogovan!");
				window.location.href = "#/welcome";
			}
			else {
				alert("Doslo je do greske!")
				window.location.href = "#";
			}

			})
		
	}

	
	
	
 }]);