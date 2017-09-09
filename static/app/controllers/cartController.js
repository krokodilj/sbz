(function(){
	angular.module("buystuff")
		.controller("cartController",function($location,cartService,orderService){

			var self=this

				self.cart=cartService.get()

				self.order

				self.max

			self.remove=function(article){
				cartService.remove(article)
				self.cart=cartService.get()
			}

			self.createOrder=function(){
				orderService.calculateOrder(self.cart).then(function(retval){
					if(retval.ok){
						self.order=retval.data
						self.max=self.order.customer.userProfile.buyingPoints<self.order.price ? self.order.customer.userProfile.buyingPoints:self.order.price
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})
			}

			self.confirmOrder=function(){
				if(!self.order.pointsSpent) self.order.pointsSpent=0

				orderService.order(self.order).then(function(retval){
					if(retval.ok){
						$location.path("#/buy")
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})
			}

		})
})()