(function (){
	angular.module("buystuff")
		.controller("userCategoryController",function(userCategoryService){

			var self = this

				//model
				self.categories

			self.getCategories=function(){
				userCategoryService.getCategories().then(function(retval){
					if(retval.ok){
						self.categories=retval.data
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})
			}

			self.saveCategory=function(categoryId,limits){

				userCategoryService.saveCategory(categoryId,limits).then(function(retval){
					if(retval.ok){
						alert("successfully updated")
						self.getCategories()
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})
			}

			self.addLimit=function(categoryId){

				self.categories.forEach(function(c){
					
					if(c.id==categoryId){
						 c.spendingLimit.push({"upperLimit":0,"lowerLimit":0,"percent":0.0})
					}

				})

			}

			self.removeLimit=function(categoryId,limit){
				self.categories.forEach(function(c){
					
					if(c.id==categoryId){
						var idx=-1
						idx=c.spendingLimit.indexOf(limit)
						if(idx!=-1)
							c.spendingLimit.splice(idx,1)
					}

				})

			}

			self.getCategories()			



		})
})();