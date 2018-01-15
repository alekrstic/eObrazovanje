'use strict';

/**
 * @ngdoc function
 * @name studentsClientApp.controller:StudentDetailsCtrl
 * @description
 * # StudentDetailsCtrl
 * Controller of the studentsClientApp
 */

angular.module('studentsClientApp')
  .controller('StudentDetailsCtrl', ['$scope', 'localStorageService','Restangular','$routeParams', '$uibModal', '$log', '_', 
                                     function($scope, localStorageService, Restangular, $routeParams, $uibModal, $log, _) {
	  $scope.uloga = localStorageService.get('uloga');
	  
	if($scope.uloga==null){
		window.location.href = "#/login";
	}
	  
	//ovde preuzimas parametar iz rute koji ti je id studenta
	  $scope.id = $routeParams.studentId;
	//Pravis rest poziv na kontroler koji ti vraca jednog studenta sada mozda vama nije namapirano na students ako nije to promeni kako treba kod vas
	  Restangular.one('students', $scope.id).get().then(function(data){
		 $scope.student= data; 
	  });
	  
	  Restangular.one("students", $scope.id).getList("courses").then(function(entries) {
	      // nakon sto resursi stgnu sa sa back enda, postavimo ih u $scope da bismo mogli da ih prikazemo na stranici
	      $scope.enrollments = entries;
	    });
	  Restangular.one("students", $scope.id).getList("uplatestudenata").then(function(entries) {
	      // nakon sto resursi stgnu sa sa back enda, postavimo ih u $scope da bismo mogli da ih prikazemo na stranici
	      $scope.uplate = entries;
	      console.log($scope.uplate);
	    });
	  Restangular.one("students", $scope.id).getList("polaganjaispita").then(function(entries) {
	      // nakon sto resursi stgnu sa sa back enda, postavimo ih u $scope da bismo mogli da ih prikazemo na stranici
	      $scope.ispiti = entries;
	      console.log($scope.ispiti);
	    });
		
		if($scope.uloga=='administrator'){
			$scope.navUrl = '#/students'
			$scope.navStr = '<-- Nazad na evidenciju studenata'
		}
		
	$scope.logout = function(){
		localStorage.clear(); 
		window.location.href = "#/login";
	}
	
	
	// Kontroler za UPLOAD
	var StudentUploadDocumentModalCtrl = ['$scope', '$uibModalInstance', 
	                                      function ($scope, $uibModalInstance){
		
		$scope.cancel = function() {
          $uibModalInstance.dismiss('cancel');
        };
        
        $scope.ok = function() {
        	
        	var fd = new FormData();
        	fd.append('naziv', $scope.document.file.name);
    		fd.append('tip', $scope.document.file.type);
    		fd.append('id', $scope.student.id);
    		
    		//Selektovan fajl
        	if($scope.document.file.size != null){
        		var t = $scope.document.file.type;
        		if(t == 'application/pdf' || t == 'application/kswps' || t == 'image/jpg' || t == 'image/jpeg'){
        			if($scope.document.file.size < 5242880){
			        	fd.append('file', $scope.document.file);
			    			
			    			Restangular.one('dokument')
				    		.withHttpConfig({transformRequest: angular.identity})
				    		.customPOST(fd, '', undefined, {'Content-Type': undefined})
				    		.then(function (data) {
				    			$scope.documents.push(data);
				    		});
			    			$uibModalInstance.close('ok');
			    		
			    	}else{
			    		alert('Error! File is to big. Max aproved size is 5MB');
	        			$log.info('Error! File is to big. Max aproved size is 5MB');
		        	}
        		}else{
        			alert('Error! Invalid document type. Allowed formats are .pdf .jpg .jpeg');
        			$log.info('Error! Invalid document type. Allowed formats are .pdf .jpg .jpeg');
        		}
        	//Nije selektovan fajl
        	}else{
    			alert('Error! To create Document u must select File to upload.');
    			$log.info('Error! To create Document u must select File to upload.');
        	}
        }
    }];
    //END StudentUploadDocumentModalCtrl
    
    /////
    $scope.openModalUploadDocument = function(student) {
    	
    
    	$scope.document = {};
    	$scope.document.file = {};
    	
        var modalInstance = $uibModal.open({
        	templateUrl: 'views/modals/document.html',
        	controller: StudentUploadDocumentModalCtrl,
        	
        	scope: $scope,
        	resolve: {
        		student: function() {
        			return student;
        		}
        	}
        });
    };
    //END StudentDocumentsModalCtrl

  }]);





