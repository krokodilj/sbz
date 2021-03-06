(function () {
	angular.module("buystuff")
		.service("authService",function($rootScope,$window,$http,$cookies){

			var self = this;

			self.login=function(username,password){

				data={
					"username":username,
					"password":password
				}

				var ret=$http.post('api/users/login',data).then(
                    function(response){
                            $rootScope.role=response.data.role
                            $rootScope.name=response.data.username
                            $cookies.putObject("token",response.data)
                            $http.defaults.headers.common.Authorization = response.data;
                            $window.location.href="#/"
                            return  {"ok":true,"data":response.data}
                    },function(response){	
                            return {"ok":false,"msg":response.data.message}
                    })

				return ret
			}

			self.logout=function(){
				$cookies.remove("token")
				$rootScope.role="guest"
				$rootScope.name="guest"
				$http.defaults.headers.common.Authorization = undefined;
				$window.location.href="#/login"

			}

			self.getCookie= function(){
				return $cookies.getObject('token')
			}

			self.getUserRole=function(){
				var token = $cookies.getObject('token')
				if(!token) return 'guest';
				return token.role;
			}

			self.getUserName=function(){
				var token = $cookies.getObject('token')
				if(!token) return 'guest';
				return token.username;
			}

			$rootScope.role=self.getUserRole();
			$rootScope.name=self.getUserName();
		})
})();