$('#kuchBhi').on('click',function(){
	var dataToSend = {
		"name":"Deepak",
		"profession":"Bisht Brothers"
	}
	$.ajax({
		url:'/newEntity',
		type:'POST',
		data:JSON.stringify(dataToSend),
		success:function(){
			console.log('hello');
		},
		error:function(){}
	});
});