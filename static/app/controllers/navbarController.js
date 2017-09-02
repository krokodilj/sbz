(function () {
	angular.module("buystuff")
		.controller("navbarController",function(authService){

			var self=this;

			self.role=authService.getUserRole();

			self.logout=authService.logout

		});

})();
