(function(){
	angular.module("buystuff")
		.service("articleCategoryService",function($http){

			var self=this

			self.getCategories=function(){
				var ret=$http.get("api/article_category").then(
					function(response){
						return {"ok":true,"data":response.data}
					},function(error){
						return {"ok":false,"msg":error.data.message}
					})

				return ret
			}


			self.addCategory=function(data){
				var ret=$http.post("api/article_category",data).then(
					function(response){
						return {"ok":true,"data":response.data}
					},function(error){
						return {"ok":false,"msg":error.data.message}
					})

				return ret
			}



			self.updateCategory=function(categoryId,category){
				var ret=$http.put("api/article_category/"+categoryId,category).then(
					function(response){
						return {"ok":true,"data":response.data}
					},function(error){
						return {"ok":false,"msg":error.data.message}
					})

				return ret
			}

		})
})();