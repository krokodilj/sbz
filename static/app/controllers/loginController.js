(function (){
	angular.module("buystuff")
		.controller("loginController",function(authService){

			var self = this;

			self.error=false
			self.error_msg
			self.username
			self.password

			self.login=function(){
				authService.login(self.username,self.password).then(function(retval){
					if(retval.ok){
						//nthng
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})

			}

		})

})();