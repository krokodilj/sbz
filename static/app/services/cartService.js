(function (){
	angular.module("buystuff")
	.service("cartService",function(){


		var self= this
			self.cart=[]
			

		self.add=function(article,count){

			var idx=-1
			self.cart.forEach(function(e,i){
				if(e.id==article.id) idx=i
			})

			if(idx!=-1){
				self.cart[idx].number+=count
			}
			else{
				var a=JSON.parse(JSON.stringify(article))
				a["number"]=count
				
				//ako ne postoji u listi
				self.cart.push(a)
			}
			
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