(function (){
	angular.module("buystuff")
		.service("userCategoryService",function($http){

			var self = this

			self.getCategories=function(){

				var ret=$http.get("api/user_category").then(
					function(response){
						return {"ok":true,"data":response.data}
					},function(error){
						return {"ok":false,"msg":error.data.message}
					})

				return ret
			}

			self.saveCategory=function(categoryId,limits){

				var data=[]

				limits.forEach(function(e){
					data.push({
						"lowerLimit":e.lowerLimit,
						"upperLimit":e.upperLimit,
						"percent":e.percent
					})
				})
				

				var ret=$http.put("api/user_category/"+categoryId,data).then(
					function(response){
						return {"ok":true,"data":response.data}
					},function(error){
						return {"ok":false,"msg":error.data.message}
					})

				return ret
			}










		})
})();