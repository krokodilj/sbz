(function(){
	angular.module("buystuff")
		.controller("articleCategoryController",function(articleCategoryService){

			var self = this

				//model
				self.categories

				//ui 
				self.state=0
				self.selectedCategory=-1

				//ui models
				self.fcat
				self.pcat
				self.ucat

				self.nocatid=-1

			self.getCategories=function(){
				articleCategoryService.getCategories().then(function(retval){
					if(retval.ok){
						self.categories=retval.data
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})
			}

			self.addCategory=function(){
				articleCategoryService.addCategory(self.fcat).then(function(retval){
					if(retval.ok){
						alert("Category Added : "+self.fcat.name)
						self.state=0
						self.selectedCategory=-1
						self.getCategories()
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})
			}

			self.updateCategory=function(){
				alert(JSON.stringify(self.ucat))

				articleCategoryService.updateCategory(self.selectedCategory,self.ucat).then(function(retval){
					if(retval.ok){

						alert("Category Updated : "+self.ucat.name)
						self.state=0
						self.selectedCategory=-1
						self.getCategories()
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})
			}

			//////////////////////////////////////////////////////////////////////////////////////////			

			self.setPcat=function(){				
				var obj=self.categories.find(function(element){return element.id==self.selectedCategory})
				self.pcat=JSON.parse(JSON.stringify(obj))
				if(!self.pcat.parentCategory) self.pcat.parentCategory={"name":"none"}
			}

			self.setFcat=function(){
				self.fcat={}
			}

			self.setUcat=function(){
				var obj=self.categories.find(function(element){return element.id==self.selectedCategory})
				self.ucat=JSON.parse(JSON.stringify(obj))

				
				self.ucat.parentCategoryId=self.ucat.parentCategory ? self.ucat.parentCategory.id : -1
			}

			/////////////////////////////////////////////////////////////////////////////

			self.getCategories()

		})
})();