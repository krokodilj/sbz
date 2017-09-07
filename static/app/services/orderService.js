(function(){
	angular.module("buystuff")
		.service("orderService",function($http){

			var self = this

			self.calculateOrder=function(cart){
				var ret
				alert(JSON.stringify(cart))

				return ret
			}
		})
})()