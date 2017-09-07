(function (){
	angular.module("buystuff")
		.service("articleService",function($http){

			var self=this

			self.getArticles=function(id,name,min,max,cat){
				var query=""
				if(id||name||min||max||cat){
					query=query+"?"
					if(id) query=query+"id="+id+"&"
					if(name) query=query+"name="+name+"&"
					if(min) query=query+"min="+min+"&"
					if(max) query=query+"max="+max+"&"
					if(cat) query=query+"category="+cat+"&"
				}

				var ret=$http.get("api/article"+query).then(
					function(response){
						return {"ok":true,"data":response.data}
					},function(error){
						return {"ok":false,"msg":error.data.message}
					})

				return ret
			}


		})

})();