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
		})
})();