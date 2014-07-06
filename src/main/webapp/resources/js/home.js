var visitorbooks = visitorbooks || {};
visitorbooks = angular.module("VisitorBookApp", []);

visitorbooks.controller('VisitorBookListController', ["$scope", "$http", function ($scope, $http) {
  	$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
  	
  	$http.get('/lists').
  	  success(function(data) {
  	    $scope.visitorBooks = data;
      });
  	$scope.rowCount = 0;
  	
  	$scope.showModal = function(id){
  	  $("#inputConfirmId").val(id);
	  $('#passwordConfirmModal').modal('show');
	  $("#inputPasswordConfirm").val("");
  	};
  }]);
