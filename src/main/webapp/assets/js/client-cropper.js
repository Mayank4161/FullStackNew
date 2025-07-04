// Client Image Cropper Integration
let clientCropper, clientCroppedBlob;
const clientImageInput = document.getElementById('clientImageInput');
const clientCropModal = document.getElementById('clientCropModal');
const clientCropImage = document.getElementById('clientCropImage');
const clientCropBtn = document.getElementById('clientCropBtn');
const clientCancelCropBtn = document.getElementById('clientCancelCropBtn');
const clientImagePreview = document.getElementById('clientImagePreview');
const addClientForm = document.getElementById('addClientForm');
const clientMessage = document.getElementById('client-message');

if (clientImageInput) {
    clientImageInput.addEventListener('change', function(e) {
        const file = e.target.files[0];
        if (file && /^image\//.test(file.type)) {
            const reader = new FileReader();
            reader.onload = function(evt) {
                clientCropImage.src = evt.target.result;
                clientCropModal.style.display = 'flex';
                if (clientCropper) clientCropper.destroy();
                clientCropper = new Cropper(clientCropImage, {
                    aspectRatio: 1,
                    viewMode: 1
                });
            };
            reader.readAsDataURL(file);
        }
    });
    clientCropBtn.addEventListener('click', function() {
        if (clientCropper) {
            clientCropper.getCroppedCanvas().toBlob(blob => {
                clientCroppedBlob = blob;
                // Preview
                const url = URL.createObjectURL(blob);
                clientImagePreview.innerHTML = `<img src="${url}" style="max-width:120px;max-height:120px;border-radius:8px;">`;
                clientCropModal.style.display = 'none';
            }, 'image/jpeg');
        }
    });
    clientCancelCropBtn.addEventListener('click', function() {
        clientCropModal.style.display = 'none';
        clientImageInput.value = '';
        clientImagePreview.innerHTML = '';
    });
    addClientForm.addEventListener('submit', function(e) {
        if (clientCroppedBlob) {
            e.preventDefault();
            const formData = new FormData(addClientForm);
            formData.set('image', clientCroppedBlob, 'cropped.jpg');
            fetch('AddClientServlet', {
                method: 'POST',
                body: formData
            }).then(res => res.text()).then(msg => {
                clientMessage.innerHTML = `<div style='color:green;'>${msg}</div>`;
                addClientForm.reset();
                clientImagePreview.innerHTML = '';
                clientCroppedBlob = null;
            }).catch(() => {
                clientMessage.innerHTML = `<div style='color:red;'>Error adding client.</div>`;
            });
        }
    });
} 