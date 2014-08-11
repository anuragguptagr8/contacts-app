var photoFieldRules = {
    "popupPhotoFile" : function(value) {
        return !!value && value.match(/^.+\.(jpe?g|JPE?G|)$/);
    }
}

function photo_div_hide(){
    document.getElementById('photo-popup-wrapper').style.display = "none";
}

function checkPhotoParent(t){
    while(t.parentNode){
        if(t==document.getElementById('photo-popup')) {
            return false
        } else if (t==document.getElementById('photo-popup-wrapper') 
            || t==document.getElementById('photoClose') 
            || t==document.getElementById('photoCancel')) {
            return true
        } 
        t=t.parentNode
    }
    return true
} 

function savePhoto() {
    var form = document.forms["contact_form"];
    var existingPhoto = form.children.namedItem("photo");
    if (existingPhoto) {
        existingPhoto.parentElement.removeChild(existingPhoto);
    }
    var file = document.getElementById("popupPhotoFile");
    file.name = "photo";
    file.id = "";
    file.style.display = "none";
    var newFileInput = document.createElement("input");
    newFileInput.type = "file";
    newFileInput.id = "popupPhotoFile";
    file.parentNode.replaceChild(newFileInput, file);
    form.appendChild(file);
}

function removePhoto() {
    var form = document.forms["contact_form"];
    var existingPhoto = form.children.namedItem("photo");
    if (existingPhoto) {
        existingPhoto.parentElement.removeChild(existingPhoto);
    }
    var emptyPhoto = document.createElement("input");
    emptyPhoto.type = "hidden";
    emptyPhoto.value = "";
    form.appendChild(emptyPhoto);
}

addLoadEvent(function() {
    var existingPhoto = document.forms["contact_form"].children.namedItem("photo");
    if (existingPhoto && existingPhoto.value) {
        document.getElementById("removePhotoDiv").style.display = "block";
    } else {
        document.getElementById("removePhotoDiv").style.display = "none";
    }

    var photoOkBtn = document.getElementById("photoOk");
    photoOkBtn.addEventListener("click", function() {
        if (validateInputs(photoFieldRules)) {
            savePhoto();
            document.getElementById("refreshPhotoMsg").style.display = "block";
            document.getElementById("removePhotoDiv").style.display = "block";
            photo_div_hide();
        }
    });

    var removePhotoBtn = document.getElementById("removePhotoBtn");
    removePhotoBtn.addEventListener("click", function() {
        if (confirm("Are you sure you want to remove contact's photo?")) {
            removePhoto();
            document.getElementById("refreshPhotoMsg").style.display = "block";
            document.getElementById("removePhotoDiv").style.display = "none";
        }
    });

});