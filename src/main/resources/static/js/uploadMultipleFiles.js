function convertToBase64(file){
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    })
}

$('#form-submit').click(function(){
	let formData = new FormData();
	let input1 = $('#form-file1');
	let input2 = $('#form-file2');
	let input1File = input1[0].files[0];
	let input2File = input2[0].files[0];
	formData.append("file1",input1File,input1File.name);
	formData.append("file2",input2File,input2File.name);
	let URLparam = "/v3";
	let URLForAjax = "/uploadMultipleFiles"+URLparam;
	let successCallBack = function(resp){
		console.log('in success');
		console.log(resp);
	};
	let errorCallBack = function(resp){
		console.log('in failure');
		console.log(resp);
	};
	$.ajax({
		url: URLForAjax,
		type:'POST',
		cache:false,
        contentType: false,
        processData: false,
		data:formData,
		success:successCallBack,
		error:errorCallBack,
	});
});


$('#submit-text-form').click(function(){
	let form = document.getElementById('text-form');
	let URLparam = "/v1";
	let URLForAjax = "/uploadText"+URLparam;
	let successCallBack = function(resp){
		console.log('in success');
		console.log(resp);
	};
	let errorCallBack = function(resp){
		console.log('in failure');
		console.log(resp);
	};
	let dataToSend = {
		"Name":"Ram",
		"Profession":"God"
	};
	$.ajax({
		url: URLForAjax,
		type:'POST',
		data:JSON.stringify(dataToSend),
		contentType:"application/json",
		success:successCallBack,
		error:errorCallBack,
	});
});