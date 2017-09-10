(function (){
	angular.module("buystuff")
		.controller("orderController",function(orderService){

			var self =this

				self.orders

			self.getOrders=function(){
				 orderService.getOrders().then(function(retval){
                              if(retval.ok){
                                    self.orders=retval.data
                              }else{
                                    alert("ERROR "+retval.msg+" ERROR")
                              }
                        
                       })
			}

			self.processOrder=function(orderId){
				orderService.processOrder(orderId).then(function(retval){
                              if(retval.ok){
                                    self.getOrders()
                                    
                              }else{
                                    alert("ERROR "+retval.msg+" ERROR")
                              }
                        
                       })
			}

			self.getOrders()


		})
})()