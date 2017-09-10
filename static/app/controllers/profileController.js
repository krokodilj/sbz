(function () {
	angular.module("buystuff")
		.controller("profileController",function($rootScope,$routeParams,userService,authService,orderService){
                  
			var self=this;

                        self.user

                        self.orders

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

                   self.getUserOrders=function(){
                        orderService.getUserOrders($routeParams.username).then(function(retval){
                              if(retval.ok){
                                    self.orders=retval.data
                              }else{
                                    alert("ERROR "+retval.msg+" ERROR")
                              }
                        
                       })
                   }

            self.getUser()
            //in case of customer role load data
            var role=authService.getUserRole()
            if(role=="customer"){
                  self.getUserOrders()
            }


		});

})();
