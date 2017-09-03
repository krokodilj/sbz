(function () {
	angular.module("buystuff")
		.controller("profileController",function($routeParams,userService){

			var self=this;

			self.getUser=function(){
   				userService.getUser($routeParams.username).then(function(retval){
            		self.user=retval
            	})
            }

            self.getUser()

		});

})();
