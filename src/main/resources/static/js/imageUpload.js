function convertToBase64(file){
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    })
}
let submitButton = document.getElementById('form-submit');
submitButton.addEventListener('click',function (event){
    let input = document.getElementById('imageFile');
    let input2 = document.getElementById('base64convertedvalue');
    let form = document.getElementById('form-upload');
    convertToBase64(input.files[0])
        .then(data=>{
            input2.value=data;
            form.action = "/uploadBase64Img";
            form.method = "POST";
            form.submit();
        }).catch(error => {

    });
});

