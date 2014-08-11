newId = 0;

var phoneFieldRules = {
    "countryCodeTxt" : function(value) {
        if (value) {
            return hasOnlyDigits(value) && value.length <= 9 &&
                document.getElementById("operatorCodeTxt").value.trim() &&
                document.getElementById("numberTxt").value.trim();
        } 
        return true;
    },
    "operatorCodeTxt" : function(value) {
        if (value) {
            return hasOnlyDigits(value) && value.length <= 9 &&
                document.getElementById("numberTxt").value.trim();
        }
        return true;
    },
    "numberTxt" : function(value) {
        return value && hasOnlyDigits(value) && value.length <= 9;
    },
    "commentsTxt" : function(value) {
        return value.length <= 300;
    }
}

function hasOnlyDigits(value) {
    return value.match(/^\d+$/);
}

// validating empty field
function check_empty(){
    if(document.getElementById('name').value == ""
        || document.getElementById('email').value == ""
        ||document.getElementById('msg').value == "" ){
        alert ("Fill All Fields !");
    }
    else {
        document.getElementById('form').submit();
        alert ("Form submitted successfully...");
    }
}
 
//function to display Popup
function div_show(){
    document.getElementById('popup-wrapper').style.display = "block";
}

function div_hide(){
    document.getElementById('popup-wrapper').style.display = "none";
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

function clearPhoneForm() {
    document.getElementById("formPhoneId").value = "";
    document.getElementById("countryCodeTxt").value = "";
    document.getElementById("operatorCodeTxt").value = "";
    document.getElementById("numberTxt").value = "";
    // document.getElementById("phoneTypeSlct").value = "";
    document.getElementById("commentsTxt").value = "";
}

function fillPhoneForm(id) {
    var phone = getPhones()[id];
    document.getElementById("formPhoneId").value = phone.id;
    document.getElementById("countryCodeTxt").value = phone.countryCode;
    document.getElementById("operatorCodeTxt").value = phone.operatorCode;
    document.getElementById("numberTxt").value = phone.number;
    document.getElementById("phoneTypeSlct").value = phone.typeId;
    document.getElementById("commentsTxt").value = phone.comment;
}

function savePhone() {
    var id = document.getElementById("formPhoneId").value;
    var phoneDiv = document.getElementsByClassName("phone-data").namedItem("phone"+id);
    if (!phoneDiv) {
        id = getNewId();
        document.getElementById("formPhoneId").value = id;
        phoneDiv = createNewPhoneRecord(id);
    }
    fillPhoneDiv(phoneDiv);
    updatePhonesTable();
}

function getSelectedPhoneIds() {
    var checkboxes = document.getElementsByName("phoneChk");
    var selectedIds = [];
    for (var i = 0; i < checkboxes.length; i++) {
        var checkbox = checkboxes[i];
        if (checkbox.checked) {
            selectedIds.push(checkbox.dataset["id"]);
        }
    }
    return selectedIds.length ? selectedIds : null;
}

function removePhones(ids) {
    for (var i = 0; i < ids.length; i++) {
        var id = ids[i];
        var phoneDiv = document.getElementsByClassName("phone-data").namedItem("phone"+id);
        if (id[id.length-1] == "n") {
            phoneDiv.parentElement.removeChild(phoneDiv);
        } else {
            phoneDiv.children.namedItem("phoneId").value *= -1;
        }
    }
}

function getNewId() {
    newId += 1;
    return newId + "n";
}

function createNewPhoneRecord(id) {
    // <div class="phone-data" name="phone2">
    //     <input type="hidden" name="phoneId" value="2" />
    //     <input type="hidden" name="countryCode" value="233" />
    //     <input type="hidden" name="operatorCode" value="22" />
    //     <input type="hidden" name="number" value="2553324" />
    //     <input type="hidden" name="phoneTypeId" value="2" />
    //     <input type="hidden" name="phoneComment" value="lalalla" />
    // </div>

    var form = document.forms["contact_form"];
    var div = document.createElement("div");
    div.className = "phone-data";
    div.setAttribute("name", "phone"+id);
    var input = document.createElement("input");
    input.type = "hidden";
    input.name = "phoneId";
    div.appendChild(input);
    input = document.createElement("input");
    input.type = "hidden";
    input.name = "countryCode";
    div.appendChild(input);
    input = document.createElement("input");
    input.type = "hidden";
    input.name = "operatorCode";
    div.appendChild(input);
    input = document.createElement("input");
    input.type = "hidden";
    input.name = "number";
    div.appendChild(input);
    input = document.createElement("input");
    input.type = "hidden";
    input.name = "phoneTypeId";
    div.appendChild(input);
    input = document.createElement("input");
    input.type = "hidden";
    input.name = "phoneComment";
    div.appendChild(input);
    form.appendChild(div);
    return div;
}

function fillPhoneDiv(phoneDiv) {
    phoneDiv.children.namedItem("phoneId").value = document.getElementById("formPhoneId").value;
    phoneDiv.children.namedItem("countryCode").value = document.getElementById("countryCodeTxt").value;
    phoneDiv.children.namedItem("operatorCode").value = document.getElementById("operatorCodeTxt").value;
    phoneDiv.children.namedItem("number").value = document.getElementById("numberTxt").value;
    phoneDiv.children.namedItem("phoneTypeId").value = document.getElementById("phoneTypeSlct").value;
    phoneDiv.children.namedItem("phoneComment").value = document.getElementById("commentsTxt").value;
}

function getPhones() {
    var phoneDivs = document.getElementsByClassName("phone-data");
    var phones = {};
    for (var i = 0; i < phoneDivs.length; i++) {
        var phoneDiv = phoneDivs[i];
        if (phoneDiv.children.namedItem("phoneId").value[0] != "-") {
            var phone = {
                id : phoneDiv.children.namedItem("phoneId").value,
                countryCode : phoneDiv.children.namedItem("countryCode").value,
                operatorCode : phoneDiv.children.namedItem("operatorCode").value,
                number : phoneDiv.children.namedItem("number").value,
                typeId : phoneDiv.children.namedItem("phoneTypeId").value,
                comment : phoneDiv.children.namedItem("phoneComment").value
            }
            phones[phone.id] = phone;
        }
    }
    return phones;
}

function getPhoneTypes() {
    var options = document.getElementById("phoneTypeSlct").getElementsByTagName("option");
    var types = {};
    for (var i = 0; i < options.length; i++) {
        var option = options[i];
        types[option.value] = option.innerHTML;
    }
    return types;
}

function createPhoneRow(phone) {
    // <tr>
    //     <td><input type=checkbox name="phoneChk"/></td>
    //     <td>
    //         <div class="phone-no"><a href="#">+375295684111</a></div>
    //         <div class="phone-comments">Some comments here</div>
    //     </td>
    //     <td>Home</td>
    //     <td><a href="#" class="fa fa-pencil"></a></td>
    // </tr>
    var tr = document.createElement("tr");
    //checkbox
    var td = document.createElement("td");
    var input = document.createElement("input");
    input.type = "checkbox";
    input.name = "phoneChk";
    input.dataset["id"] = phone.id;
    td.appendChild(input);
    tr.appendChild(td);
    //phone-no
    td = document.createElement("td");
    var div = document.createElement("div");
    div.className = "phone-no";
    var a = document.createElement("a");
    a.href = "#";
    a.dataset["id"] = phone.id;
    var numberString = phone.operatorCode ? phone.countryCode + " (" +
        phone.operatorCode + ") " + phone.number : phone.number;
    a.appendChild(document.createTextNode(numberString));
    div.appendChild(a);
    td.appendChild(div);
    //comments
    div = document.createElement("div");
    div.className = "phone-comments";
    div.appendChild(document.createTextNode(phone.comment));
    td.appendChild(div);
    tr.appendChild(td);
    //phone type
    td = document.createElement("td");
    var types = getPhoneTypes();
    td.appendChild(document.createTextNode(types[phone.typeId]));
    tr.appendChild(td);
    //edit btn
    td = document.createElement("td");
    a = document.createElement("a");
    a.href = "#";
    a.className = "fa fa-pencil";
    a.dataset["id"] = phone.id;
    td.appendChild(a);
    tr.appendChild(td);

    return tr;
}

function updatePhonesTable() {
    var phones = getPhones();
    var tableBody = document.getElementById("phones-table").getElementsByTagName("tbody")[0];
    tableBody.innerHTML = "";
    for (var phoneId in phones) {
        tableBody.appendChild(createPhoneRow(phones[phoneId]));
    }
}

addLoadEvent(function() {
	document.body.addEventListener("click", check);
    updatePhonesTable();
    var tableLinks = document.getElementById("phones-table").getElementsByTagName("a");
    for (var i = 0; i < tableLinks.length; i++) {
        var link = tableLinks[i];
        link.addEventListener("click", function() { 
            div_show(); 
        });
    }
    var phoneOkBtn = document.getElementById("phoneOk");
    phoneOkBtn.addEventListener("click", function() {
        if (validateInputs(phoneFieldRules)) {
            savePhone();
            div_hide();
        }
    });
    var addPhoneBtn = document.getElementById("addPhoneBtn");
    addPhoneBtn.addEventListener("click", function() {
        clearPhoneForm();
        div_show();
    });
    var removePhoneBtn = document.getElementById("removePhoneBtn");
    removePhoneBtn.addEventListener("click", function() {
        var selectedIds;
        if (selectedIds = getSelectedPhoneIds()) {
            if (confirm('Are you sure you want to remove all selected phone numbers?')) {
                removePhones(selectedIds);
                updatePhonesTable();    
            }
        }
    });
});