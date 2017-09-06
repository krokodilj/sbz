(function (){
	angular.module("buystuff")
		.controller("saleEventController",function($scope,saleEventService,articleCategoryService){

			var self = this

				//model
				self.sales

				//ui 
				self.state=0
				self.selectedSale=-1

				//ui models
				self.fsale//add form model				
				self.psale//preview model	
				self.usale//update form model

				//dropdown checkbox
				self.articleCategories	

						

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

			self.addSale=function(){

				saleEventService.addSale(self.fsale).then(function(retval){
					if(retval.ok){

						alert("Sale Added : "+self.fsale.name)

						self.state=0
						self.selectedSale=-1
						self.getSaleEvents()
						
												
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})

			}

			self.getCategories=function(){
				articleCategoryService.getCategories().then(function(retval){
					if(retval.ok){
						self.articleCategories=retval.data
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})
			}


			self.updateSale=function(){
				saleEventService.updateSale(self.selectedSale,self.usale).then(function(retval){
					if(retval.ok){
						

						alert("Sale Updated : "+self.psale.name)

						self.state=0
						self.selectedSale=-1
						self.getSaleEvents()
						
					}else{
						alert("ERROR "+retval.msg+" ERROR")
					}
				})
			}

			/////////////////////////////////////////////////////////////////////////////////////

			self.checkboxCheck=function(id){
				var idx
				if(self.state==1) 
					idx=self.fsale.articleCategoriesIds.indexOf(id);
				else	
					idx=self.usale.articleCategoriesIds.indexOf(id);

				if(idx<0){
					if(self.state==1) 
						self.fsale.articleCategoriesIds.push(id)
					else	
						self.usale.articleCategoriesIds.push(id)
						
					
				}else{
					if(self.state==1) 
						self.fsale.articleCategoriesIds.splice(idx,1)			
					else	
						self.usale.articleCategoriesIds.splice(idx,1)
							
				}
			}			

			self.setPsale=function(){				
				var obj=self.sales.find(function(element){return element.id==self.selectedSale})
				self.psale=JSON.parse(JSON.stringify(obj))
				self.psale.startDate=new Date(self.psale.startDate).toString().slice(0,15)
				self.psale.endDate=new Date(self.psale.endDate).toString().slice(0,15)

				if(!self.psale.articleCategories.length>0) self.psale.articleCategories=[{"name":"none"}]
			}

			self.setFsale=function(){
				self.fsale={"articleCategoriesIds":[]}
			}

			self.setUsale=function(){
				var obj=self.sales.find(function(element){return element.id==self.selectedSale})
				self.usale=JSON.parse(JSON.stringify(obj))

				self.usale.startDate=new Date(self.usale.startDate)
				self.usale.endDate=new Date(self.usale.endDate)

				var list=[]
				self.usale.articleCategories.forEach(function(element){
					list.push(element.id)
				})
				self.usale.articleCategoriesIds=list
			}


			//////////////////////////////////////////////////////////////////////

			self.getSaleEvents()

			self.getCategories()

		})
})();