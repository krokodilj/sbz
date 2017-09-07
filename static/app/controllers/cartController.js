(function(){
	angular.module("buystuff")
		.controller("cartController",function(cartService,orderService){

			var self=this

			self.cart=cartService.get()

			self.remove=function(article){
				cartService.remove(article)
				self.cart=cartService.get()
			}

			self.createOrder=function(){
				orderService.calculateOrder(self.cart).then(function(){
					if(retval.ok){
						self.order=retval.data
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})
			}

		})
})()