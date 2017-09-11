(function(){
	angular.module("buystuff")
		.controller("orderSuppliesController",function(articleService){

			var self = this


			self.getArticles=function(){
				articleService.getLowArticles().then(function(retval){
               	    if(retval.ok){
                        self.articles=retval.data
                              
                    }else{
                        alert("ERROR "+retval.msg+" ERROR")
                    }

				})
			}


			self.orderArticle=function(articleId,amount){
				articleService.orderArticle(articleId,amount).then(function(retval){
               	    if(retval.ok){
                        self.getArticles()
                    }else{
                        alert("ERROR "+retval.msg+" ERROR")
                    }

				})
			}

			self.getArticles()

		})
})();