// newId = 0;

// var phoneFieldRules = {
//     "countryCodeTxt" : function(value) {
//         if (value) {
//             return hasOnlyDigits(value) && value.length <= 9 &&
//                 document.getElementById("operatorCodeTxt").value.trim() &&
//                 document.getElementById("numberTxt").value.trim();
//         } 
//         return true;
//     },
//     "operatorCodeTxt" : function(value) {
//         if (value) {
//             return hasOnlyDigits(value) && value.length <= 9 &&
//                 document.getElementById("numberTxt").value.trim();
//         }
//         return true;
//     },
//     "numberTxt" : function(value) {
//         return value && hasOnlyDigits(value) && value.length <= 9;
//     },
//     "commentsTxt" : function(value) {
//         return value.length <= 300;
//     }
// }

// function hasOnlyDigits(value) {
//     return value.match(/^\d+$/);
// }

// // validating empty field
// function check_empty(){
//     if(document.getElementById('name').value == ""
//         || document.getElementById('email').value == ""
//         ||document.getElementById('msg').value == "" ){
//         alert ("Fill All Fields !");
//     }
//     else {
//         document.getElementById('form').submit();
//         alert ("Form submitted successfully...");
//     }
// }
 
//function to display Popup
function attach_div_show(){
    document.getElementById('attachment-popup-wrapper').style.display = "block";
}

function attach_div_hide(){
    document.getElementById('attachment-popup-wrapper').style.display = "none";
}
 
//function to check parent node and return result accordingly
function checkAttachParent(t){
    while(t.parentNode){
        if(t==document.getElementById('attachment-popup')) {
            return false
        } else if (t==document.getElementById('attachment-popup-wrapper') 
            || t==document.getElementById('attachClose') 
            || t==document.getElementById('attachmentCancel')) {
            return true
        } 
        t=t.parentNode
    }
    return true
} 

function prepareEditAttachmentForm(id) {
    document.getElementById("fileUploadDiv").style.display = "none";
    document.getElementById("fileNameDiv").style.display = "block";

    var attachment = getAttachments()[id];
    document.getElementById("formAttachId").value = attachment.id;
    document.getElementById("fileNameDiv").innerHTML = "File: " + attachment.name;
    document.getElementById("attachmentCommentsTxt").value = attachment.comment;
}

function prepareAddAttachmentForm() {
    document.getElementById("fileUploadDiv").style.display = "block";
    document.getElementById("fileNameDiv").style.display = "none";

    document.getElementById("formAttachId").value = "";
    document.getElementById("fileNameDiv").innerHTML = "";
    document.getElementById("attachmentCommentsTxt").value = "";
}

function saveAttachment() {
    var id = document.getElementById("formAttachId").value;
    var attachDiv = document.getElementsByClassName("attachment-data").namedItem("attach"+id);
    if (attachDiv) {
        updateAttachmentRecord(attachDiv);
    } else {
        createNewAttachmentRecord();
    }
    updateAttachmentsTable();
}

function updateAttachmentRecord(div) {
    div.children.namedItem("attachComment").value = document.getElementById("attachmentCommentsTxt").value;
}

function createNewAttachmentRecord() {
    // <div class="attachment-data" name="attach1">
    //     <input type="hidden" name="attachId" value="1" />
    //     <input type="file" name="attachName" value="facepalm.gif" />
    //     <input type="hidden" name="attachComment" value="some comments" />
    //     <input type="hidden" name="attachUrl" value="lalalaUrl" />
    //     <input type="hidden" name="attachDate" value="31.12.1988" />
    // </div>

    var id = getNewId();

    var form = document.forms["contact_form"];

    var div = document.createElement("div");
    div.className = "attachment-data";
    div.setAttribute("name", "attach"+id);
    var input = document.createElement("input");
    input.type = "hidden";
    input.name = "attachId";
    input.value = id;
    div.appendChild(input);

    var file = document.getElementById("attachmentFile");
    file.name = "attachName";
    file.id = "";
    input = document.createElement("input");
    input.type = "file";
    input.id = "attachmentFile";
    file.parentNode.replaceChild(input, file);
    div.appendChild(file);

    input = document.createElement("input");
    input.type = "hidden";
    input.name = "attachComment";
    input.value = document.getElementById("attachmentCommentsTxt").value;
    div.appendChild(input);

    input = document.createElement("input");
    input.type = "hidden";
    input.name = "attachUrl";
    div.appendChild(input);

    input = document.createElement("input");
    input.type = "hidden";
    input.name = "attachDate";
    input.value = "not uploaded";
    div.appendChild(input);

    form.appendChild(div);
}

function getSelectedAttachIds() {
    var checkboxes = document.getElementsByName("attachChk");
    var selectedIds = [];
    for (var checkbox of checkboxes) {
        if (checkbox.checked) {
            selectedIds.push(checkbox.dataset["id"]);
        }
    }
    return selectedIds.length ? selectedIds : null;
}

function removeAttachments(ids) {
    for (var id of ids) {
        var attachDiv = document.getElementsByClassName("attachment-data").namedItem("attach"+id);
        if (id[id.length-1] == "n") {
            attachDiv.parentElement.removeChild(attachDiv);
        } else {
            attachDiv.children.namedItem("attachId").value *= -1;
        }
    }
}

function getAttachments() {
    var attachDivs = document.getElementsByClassName("attachment-data");
    var attachments = {};
    for (var attachDiv of attachDivs) {
        if (attachDiv.children.namedItem("attachId").value[0] != "-") {
            var attachment = {
                id : attachDiv.children.namedItem("attachId").value,
                name : attachDiv.children.namedItem("attachName").value,
                comment : attachDiv.children.namedItem("attachComment").value,
                url : attachDiv.children.namedItem("attachUrl").value,
                date : attachDiv.children.namedItem("attachDate").value,
            }
            attachments[attachment.id] = attachment;
        }
    }
    return attachments;
}

function createAttachmentRow(attachment) {
    // <tr>
    //     <td rowspan=2><input type="checkbox" name="attachChk" data-id="1"/></td>
    //     <td><a href="#">facepalm.gif</a></td>
    //     <td>28.04.2014</td>
    //     <td rowspan=2><a href="#" class="fa fa-pencil edit-attach"></a></td>
    // </tr>
    // <tr>
    //     <td colspan=2 class="attachment-comments">Some comments here aldkf lakjfl a lorem</td>
    // </tr>

    var resultRow = [];

    var tr = document.createElement("tr");
    var td = document.createElement("td");
    td.rowSpan = 2;
    var input = document.createElement("input");
    input.type = "checkbox";
    input.name = "attachChk";
    input.dataset["id"] = attachment.id;
    td.appendChild(input);
    tr.appendChild(td);
    
    td = document.createElement("td");
    if (attachment.url) { 
        var a = document.createElement("a");
        a.href = attachment.url;
        a.innerHTML = attachment.name;
        td.appendChild(a);
    } else {
        td.innerHTML = attachment.name;
    }
    tr.appendChild(td);

    td = document.createElement("td");
    td.innerHTML = attachment.date;
    tr.appendChild(td);

    td = document.createElement("td");
    td.rowSpan = 2;
    a = document.createElement("a");
    a.href = "#";
    a.className = "fa fa-pencil edit-attach";
    a.dataset["id"] = attachment.id;
    td.appendChild(a);
    tr.appendChild(td);

    resultRow[0] = tr;

    tr = document.createElement("tr");
    td = document.createElement("td");
    td.colSpan = 2;
    td.className = "attachment-comments";
    td.innerHTML = attachment.comment;
    tr.appendChild(td);

    resultRow[1] = tr;
    return resultRow;
}

function updateAttachmentsTable() {
    var attachments = getAttachments();
    var tableBody = document.getElementById("attachmentTable");
    tableBody.innerHTML = "<tr><th class='control-col'></th><th></th><th></th><th class='control-col'></th></tr>";
    for (var attachId in attachments) {
        var newRow = createAttachmentRow(attachments[attachId]);
        tableBody.appendChild(newRow[0]);
        tableBody.appendChild(newRow[1]);
    }
}

addLoadEvent(function() {
    // document.body.addEventListener("click", check);
    updateAttachmentsTable();
    // var tableLinks = document.getElementById("phones-table").getElementsByTagName("a");
    // for (var link of tableLinks) {
    //     link.addEventListener("click", function() { 
    //         attach_div_show(); 
    //     });
    // }
    var attachOkBtn = document.getElementById("attachmentOk");
    attachOkBtn.addEventListener("click", function() {
        // if (validateInputs(phoneFieldRules)) {
            saveAttachment();
            attach_div_hide();
        // }
    });
    // var addPhoneBtn = document.getElementById("addPhoneBtn");
    // addPhoneBtn.addEventListener("click", function() {
    //     clearPhoneForm();
    //     attach_div_show();
    // });
    var removeAttachBtn = document.getElementById("removeAttachmentBtn");
    removeAttachBtn.addEventListener("click", function() {
        var selectedIds;
        if (selectedIds = getSelectedAttachIds()) {
            if (confirm('Are you sure you want to remove all selected attachments?')) {
                removeAttachments(selectedIds);
                updateAttachmentsTable();    
            }
        }
    });
});