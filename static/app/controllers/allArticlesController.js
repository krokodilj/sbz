(function(){
	angular.module("buystuff")
		.controller("allArticlesController",function(articleService,saleEventService,cartService,articleCategoryService){

			var self= this

				//model
				self.articles

				self.sales

				self.categories

				self.data=[]

			self.getArticles=function(){
				articleService.getArticles().then(function(retval){
					if(retval.ok){
						self.articles=retval.data
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})
			}

			self.getSaleEvents=function(){
				saleEventService.getSaleEvents().then(function(retval){
					if(retval.ok){
						self.sales=retval.data
					}else{
						alert("ERROR "+retval.message+" ERROR")
					}
				})
				return
			}

			self.getCategories=function(){
				articleCategoryService.getCategories().then(function(retval){
					if(retval.ok){
						self.categories=retval.data
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})
			}

			self.addToCart=function(article,count){
				alert("dodaj me u korpu")
				cartService.add(article,count)
			}

			self.filter=function(){
				articleService.getArticles(self.id,self.name,self.minPrice,self.maxPrice,self.category).then(function(retval){
					if(retval.ok){
						self.articles=retval.data
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})
			}


			self.getArticles()
			self.getSaleEvents()
			self.getCategories()


		})
})()