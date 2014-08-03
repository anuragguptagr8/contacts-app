"use strict"

var fieldRules = {
	"firstName" : function(value) {
		return !!value;
	},
	"lastName" : function(value) {
		return !!value;
	},
	"dateOfBirth" : function(value) {
		if (!value) {
			return true;
		}
		var dateParts = value.match(/^(\d\d)\.(\d\d)\.(\d{4})$/);
		if (dateParts) {
			var date = new Date(dateParts[3], (dateParts[2]-1), dateParts[1]);
			return date && date.getDate() == dateParts[1] && (date.getMonth()+1) == dateParts[2] && date.getFullYear() == dateParts[3]; 
		}
		return false;
	},
	"email" : function(value) {
		if (!value) {
			return true;
		}
		return value.match(/^.+@.+\..+$/);
	}
}

var getField = function(fieldId) {
	return document.getElementById(fieldId);
}
var getFieldMsg = function(fieldId) {
	return document.getElementById(fieldId).parentElement
			.getElementsByClassName("field-message").item(0);
}
var validateInputs = function() {
	var result = true;
	for (var fieldName in fieldRules) {
		var fieldValue = getField(fieldName).value.trim();
		if (!fieldRules[fieldName](fieldValue)) {
			getFieldMsg(fieldName).style.display = "block";
			result = false;
		}
	}
	return result;
}
var hideMessages = function () {
	for (var fieldName in fieldRules) {
		getFieldMsg(fieldName).style.display = "none";
	}
}

addLoadEvent(function() {
	var saveButton = document.getElementById("save_button");
	saveButton.addEventListener("click", function() {
		hideMessages();
		if (validateInputs()) {
			document.forms["contact_form"].submit();
		}
	});
});