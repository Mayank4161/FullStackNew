// Project Image Cropper Integration
let projectCropper, projectCroppedBlob;
const projectImageInput = document.getElementById('projectImageInput');
const projectCropModal = document.getElementById('projectCropModal');
const projectCropImage = document.getElementById('projectCropImage');
const projectCropBtn = document.getElementById('projectCropBtn');
const projectCancelCropBtn = document.getElementById('projectCancelCropBtn');
const projectImagePreview = document.getElementById('projectImagePreview');
const addProjectForm = document.getElementById('addProjectForm');
const projectMessage = document.getElementById('project-message');

if (projectImageInput) {
    projectImageInput.addEventListener('change', function(e) {
        const file = e.target.files[0];
        if (file && /^image\//.test(file.type)) {
            const reader = new FileReader();
            reader.onload = function(evt) {
                projectCropImage.src = evt.target.result;
                projectCropModal.style.display = 'flex';
                if (projectCropper) projectCropper.destroy();
                projectCropper = new Cropper(projectCropImage, {
                    aspectRatio: 1,
                    viewMode: 1
                });
            };
            reader.readAsDataURL(file);
        }
    });
    projectCropBtn.addEventListener('click', function() {
        if (projectCropper) {
            projectCropper.getCroppedCanvas().toBlob(blob => {
                projectCroppedBlob = blob;
                // Preview
                const url = URL.createObjectURL(blob);
                projectImagePreview.innerHTML = `<img src="${url}" style="max-width:120px;max-height:120px;border-radius:8px;">`;
                projectCropModal.style.display = 'none';
            }, 'image/jpeg');
        }
    });
    projectCancelCropBtn.addEventListener('click', function() {
        projectCropModal.style.display = 'none';
        projectImageInput.value = '';
        projectImagePreview.innerHTML = '';
    });
    addProjectForm.addEventListener('submit', function(e) {
        if (projectCroppedBlob) {
            e.preventDefault();
            const formData = new FormData(addProjectForm);
            formData.set('image', projectCroppedBlob, 'cropped.jpg');
            fetch('AddProjectServlet', {
                method: 'POST',
                body: formData
            }).then(res => res.text()).then(msg => {
                projectMessage.innerHTML = `<div style='color:green;'>${msg}</div>`;
                addProjectForm.reset();
                projectImagePreview.innerHTML = '';
                projectCroppedBlob = null;
            }).catch(() => {
                projectMessage.innerHTML = `<div style='color:red;'>Error adding project.</div>`;
            });
        }
    });
} 
 