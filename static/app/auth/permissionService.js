(function () {

	angular.module("buystuff")
		.service("permissionService",function($window,$rootScope){

			var self = this

			self.redirects={
				"guest":"#/login",
				"customer":"#/",
				"manager":"#/",
				"salesman":"#/"
			}

			self.givePermission= function(roles){

				var role=$rootScope.role

				if (!roles.includes(role)){
					$window.location=self.redirects[role]
				}

			}



		})

})();