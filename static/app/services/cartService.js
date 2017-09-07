(function (){
	angular.module("buystuff")
	.service("cartService",function(){


		var self= this
			self.cart=[]
			

		self.add=function(article,count){
			var a=JSON.parse(JSON.stringify(article))
			a["number"]=count
			
			self.cart.push(a)
			
		}

		self.remove=function(article){
			var idx=self.cart.indexOf(article)

			self.cart.splice(idx,1)
			
		}

		self.get=function(){			
			return self.cart
		}

	})

})();