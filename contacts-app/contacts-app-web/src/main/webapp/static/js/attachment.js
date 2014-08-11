 
var newAttachmentFieldRules = {
    "attachmentFile" : function(value) {
        return value;
    },
    "attachmentName" : function(value) {
        return value.length <= 256;
    },
    "attachmentCommentsTxt" : function(value) {
        return value.length <= 300;
    }
}

var updatedAttachmentFieldRules = {
    "attachmentName" : function(value) {
        return value.length <= 256;
    },
    "attachmentCommentsTxt" : function(value) {
        return value.length <= 300;
    }
}

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

    var attachment = getAttachments()[id];
    document.getElementById("formAttachId").value = attachment.id;
    document.getElementById("attachmentName").value = attachment.name;
    document.getElementById("attachmentCommentsTxt").value = attachment.comment;
}

function prepareAddAttachmentForm() {
    document.getElementById("fileUploadDiv").style.display = "block";
    document.getElementById("formAttachId").value = "";
    document.getElementById("attachmentName").value = "";
    document.getElementById("attachmentCommentsTxt").value = "";
}

function isAttachmentNew() {
    var id = document.getElementById("formAttachId").value;
    var attachDiv = document.getElementsByClassName("attachment-data").namedItem("attach"+id);
    return !attachDiv;
}

function saveAttachment() {
    var success = false;
    if (isAttachmentNew()) {
        if (validateInputs(newAttachmentFieldRules)) {
            if (!document.getElementById("attachmentName").value) {
                document.getElementById("attachmentName").value = document.getElementById("attachmentFile").value.match(/[^\\]*$/)[0];
            }
            createNewAttachmentRecord();
            success = true;
        }
    } else { 
        if (validateInputs(updatedAttachmentFieldRules)) {
            updateAttachmentRecord();
            success = true;
        }
    }
    updateAttachmentsTable();
    return success;
}

function updateAttachmentRecord() {
    var id = document.getElementById("formAttachId").value;
    var div = document.getElementsByClassName("attachment-data").namedItem("attach"+id);
    div.children.namedItem("attachName").value = document.getElementById("attachmentName").value;
    div.children.namedItem("attachComment").value = document.getElementById("attachmentCommentsTxt").value;
}

function createNewAttachmentRecord() {
    // <div class="attachment-data" name="attach1">
    //     <input type="hidden" name="attachId" value="1" />
    //     <input type="file" name="attachFile2" id="attachFile2"/>
    //     <input type="hidden" name="attachName" value="facepalm.gif" />
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
    file.name = "attachFile"+id;
    file.id = "";
    input = document.createElement("input");
    input.type = "file";
    input.id = "attachmentFile";
    file.parentNode.replaceChild(input, file);
    div.appendChild(file);

    input = document.createElement("input");
    input.type = "hidden";
    input.name = "attachName";
    input.value = document.getElementById("attachmentName").value ? document.getElementById("attachmentName").value : file.value;
    div.appendChild(input);

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
    for (var i = 0; i < checkboxes.length; i++) {
        if (!checkboxes.hasOwnProperty(i)) {
            continue;
        }
        if (checkboxes[i].checked) {
            selectedIds.push(checkboxes[i].dataset["id"]);
        }
    }
    return selectedIds.length ? selectedIds : null;
}

function removeAttachments(ids) {
    for (var i = 0; i < ids.length; i++) {
        if (!ids.hasOwnProperty(i)) {
            continue;
        }
        var id = ids[i];
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
    for (var i = 0; i < attachDivs.length; i++) {
        if (!attachDivs.hasOwnProperty(i)) {
            continue;
        }
        var attachDiv = attachDivs[i];
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
    updateAttachmentsTable();

    var attachOkBtn = document.getElementById("attachmentOk");
    attachOkBtn.addEventListener("click", function() {
        if (saveAttachment()) {
            attach_div_hide();
        }
    });
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