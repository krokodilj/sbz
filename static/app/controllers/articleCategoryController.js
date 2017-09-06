(function(){
	angular.module("buystuff")
		.controller("articleCategoryController",function(articleCategoryService){

			var self = this

			self.getCategories=function(){
				articleCategoryService.getCategories().then(function(retval){
					if(retval.ok){
						alert(JSON.stringify(retval.data))
					}else{

					}
				})
			}



			self.getCategories()

		})
})();