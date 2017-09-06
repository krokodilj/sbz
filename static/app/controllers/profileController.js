(function () {
	angular.module("buystuff")
		.controller("profileController",function($routeParams,userService,authService){

			var self=this;

                        self.user

			self.getUser=function(){
   				userService.getUser($routeParams.username).then(function(retval){
                              if(retval.ok){
                                    self.user=retval.data
                                    //lolo
                                    self.user.registered=new Date(self.user.registered).toString().slice(0,15)
                              }else{
                                    alert("ERROR "+retval.msg+" ERROR")
                              }
            		
            	})
            }

            self.getUser()
            //in case of user role load data
            var role=authService.getUserRole()
            if(role=="customer"){

            }else if(role=="salesman"){

            }//else if(role=="manager"){}
            //netrebanista za menadzera


		});

})();
