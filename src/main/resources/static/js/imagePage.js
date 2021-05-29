(function(){
	const imgPosition = $('#qrCode');
	const buttonGetQR = $('#getQR');
	buttonGetQR.click(function(){
		$.ajax({
		url:"/genCodeInBytes",
		data:{"msg":"Hello This is Deepak"},
		success:function(response){
			imgPosition.attr('src',"data:image/png;base64,"+response);
		},
		error:function(){},
		});
	});

})();