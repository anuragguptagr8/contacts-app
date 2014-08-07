//function to check target element
function check(e){
    var target = (e && e.target) || (event && event.srcElement);

    var obj = document.getElementById('popup-wrapper');
    var attachmentPopup = document.getElementById('attachment-popup-wrapper');

    checkParent(target) ? obj.style.display = 'none' : null;
    checkAttachParent(target) ? attachmentPopup.style.display = 'none' : null;
    
    if ((target.parentElement.className == "phone-no") 
        || (target.className.match(/edit-phone/))) { 
        obj.style.display = 'block'; 
        fillPhoneForm(target.dataset["id"]);
        hideMessages(phoneFieldRules);
    }
    if (target==document.getElementById("addPhoneBtn")) {
        obj.style.display = 'block'; 
        hideMessages(phoneFieldRules);
    }

    if (target.className.match(/edit-attach/)) { 
        attachmentPopup.style.display = 'block';
        prepareEditAttachmentForm(target.dataset["id"]);
        // hideMessages(phoneFieldRules);
    }
    if (target==document.getElementById("addAttachmentBtn")) {
        attachmentPopup.style.display = 'block'; 
        prepareAddAttachmentForm();
        // hideMessages(phoneFieldRules);
    }
}
 
//function to check parent node and return result accordingly
function checkParent(t){
    while(t.parentNode){
        if(t==document.getElementById('phone-popup')) {
            return false
        } else if (t==document.getElementById('popup-wrapper') 
            || t==document.getElementById('close') 
            || t==document.getElementById('phoneCancel')) {
            return true
        } 
        t=t.parentNode
    }
    return true
} 

addLoadEvent(function() {
    document.body.addEventListener("click", check);
});