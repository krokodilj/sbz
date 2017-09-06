(function (){
	angular.module("buystuff")
		.service("saleEventService",function($http){

			var self = this

			self.getSaleEvents=function(){

				var ret=$http.get("api/sale_event").then(
					function(response){
						return {"ok":true,"data":response.data}
					},function(error){
						return {"ok":false,"msg":error.data.message}
					})

				return ret
			}

			self.addSale=function(data){

				var ret=$http.post("api/sale_event",data).then(
					function(response){
						return {"ok":true,"data":response.data}
					},function(error){
						return {"ok":false,"msg":error.data.message}
					})

				return ret
			}

			self.updateSale=function(saleId,sale){

				var ret=$http.put("api/sale_event/"+saleId,sale).then(
					function(response){
						return {"ok":true,"data":response.data}
					},function(error){
						return {"ok":false,"msg":error.data.message}
					})

				return ret

			}

		})
})();