(function () {
	angular.module("buystuff")
		.service("userService",function($http){

			var self=this;

			self.register=function(un,ps,fn,ln,rl,ad){


				data={
					"username":un,
					"password":ps,
					"firstName":fn,
					"lastName":ln,
					"role":rl,
					"address":ad
				}
				
				var ret= $http.post('api/users',data).then(
					function(response){
						return {"ok":true,"data":response.data}
					},function(error){
						return {"ok":false,"msg":error.data.message}
					})
				return ret
			}

			self.checkUsername=function(username){

				var ret= $http.get('api/users/check/'+username).then(
					function(response){
						return {"ok":true,"data":response.data}
					},function(error){
						return {"ok":false,"msg":error.data.message}
					});
				return ret

			}

			self.uploadImage=function(username,file){

				data= new FormData()
				data.append("username",username)
				data.append("file",file)

				var ret=$http.post('api/users/upload',data,{headers: {'Content-Type': undefined}}).then(
					function(response){
						return {"ok":true,"data":response.data}
					},function(error){
						return {"ok":false,"msg":error.data.message}
					})
				return ret
			}

			self.getUser=function(username){
				var ret = $http.get('api/users/'+username).then(
				function(response){
					return {"ok":true,"data":response.data}
				},function(error){
					return {"ok":false,"msg":error.data.message}
				});
				return ret;
			}

		})
})();