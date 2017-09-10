(function(){
	angular.module("buystuff")
		.service("orderService",function($http,$location,$rootScope){

			var self = this

			self.calculateOrder=function(cart){
				var ret
				if($rootScope.role=='guest'){
					$location.path("/login")
				}
				else{

					data=[]
					cart.forEach(function(e,i){
						data.push({"id":e.id,"number":e.number})
					})

					ret=$http.post("api/order/getOrderFromCart/"+$rootScope.name,data).then(
						function(response){
							return {"ok":true,"data":response.data}
						},function(error){
							return {"ok":false,"msg":error.data.msg}	
						})
					

					
				}
				return ret
			}

			self.order=function(order){
				var ret=$http.post("api/order",order).then(
						function(response){
							return {"ok":true,"data":response.data}
						},function(error){
							return {"ok":false,"msg":error.data.msg}	
						})

				return ret

			}

			self.getUserOrders=function(user){
				var ret=$http.get("api/order/"+user).then(
						function(response){
							return {"ok":true,"data":response.data}
						},function(error){
							return {"ok":false,"msg":error.data.msg}	
						})

				return ret

			}

			self.getOrders=function(){
				var ret=$http.get("api/order").then(
						function(response){
							return {"ok":true,"data":response.data}
						},function(error){
							return {"ok":false,"msg":error.data.msg}	
						})

				return ret
			}

			self.processOrder=function(orderId){
				var ret=$http.put("api/order/"+orderId).then(
						function(response){
							return {"ok":true,"data":response.data}
						},function(error){
							return {"ok":false,"msg":error.data.msg}	
						})

				return ret	
			}


		})
})()