// const cameraPosition = $('#capture-body');
let cameraPosition = document.getElementById('capture-body');
const openCameraButton = $('#captureImg');
const cameraModal = $('#myModal');
const captureButton = $('#capture-button');
let canvas = document.getElementById('canvas');
let capturedImageParagraph= $('#captured-image-paragraph');
let photoBase64 = '';
let photo = new Image();
function openCamera(){
    let constraints = {
      audio:false,
      video: true
    };
    navigator.mediaDevices.getUserMedia(constraints)
        .then(stream => {
            cameraPosition.srcObject = stream;
            cameraPosition.play();
        })
        .catch(error => {console.error(error)});
}
function closeCamera(){
    if(cameraPosition.srcObject.active){
        cameraPosition.srcObject.getTracks().forEach(track => {
            track.stop();
        })
    }
    if(photoBase64 && photoBase64 !== ''){
        $('#image').remove();
        $('#uploadImg').remove();
        photo.src = photoBase64;
        capturedImageParagraph.append('<img id="image" src="'+photoBase64+'" height="500px" width="650px"/>');
        $(`
            <form id="uploadImg" action ="/uploadBase64Img" method="POST">
                <input name="imageFile" type="hidden" value="`+photoBase64+`"/>
                <button>Upload!</button>
            </form>
        `).insertAfter(capturedImageParagraph);
    }
}
function captureImage(){
    let context = canvas.getContext('2d');
    context.drawImage(cameraPosition,0,0,);
    photoBase64 = canvas.toDataURL('image/png');

}
captureButton.on('click',captureImage);
openCameraButton.on('click',openCamera);
cameraModal.on('hidden.bs.modal',closeCamera);


