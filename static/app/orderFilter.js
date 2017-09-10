(function(){
	angular.module("buystuff")
		.filter("orderState",function(){
			return function(items,state){
				var filtered = [];
	            angular.forEach(items,function(item) {
	            	if(!state)	filtered.push(item);
	            	if(item.state==state)  filtered.push(item);


			    });
	            return filtered
			}

		})
})()