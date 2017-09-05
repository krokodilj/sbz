(function (){
	angular.module("buystuff")
		.controller("registerController",function($scope,userService,authService){

			var self=this

				self.step=1
				
				self.username
				self.usernameTaken=false

				self.password

				self.fname

				self.lname

				self.roles= ['customer','salesman','manager']
				self.role=self.roles[0]

				self.address

				self.img_preview="/images/guest.png"

				self.file

			self.register = function(){


				userService.register(self.username, self.password, self.fname,
										self.lname, self.role, self.address)
				.then(function(retval){
				 	if(retval) self.step=2
				})

				
			}

			self.checkUsername= function(){
				userService.checkUsername(self.username).then(function(retval){
					self.usernameTaken=!retval
				})
			}

			self.skip=function(){
				authService.login(self.username,self.password)
			}

			self.upload=function(){
				userService.uploadImage(self.username,self.file).then(function(retval){
					if(!retval){ alert("image upload ERROR")}
					else { authService.login(self.username,self.password)	}
				})
			}

			$scope.readFile = function(input) {
               	self.input=input
                var reader = new FileReader();

	            reader.onload = function (e) {
	                self.img_preview=e.target.result
	                $scope.$apply()
	            }
	            reader.readAsDataURL(input.files[0]);
	            self.file = input.files[0]
        	}

		});

})();