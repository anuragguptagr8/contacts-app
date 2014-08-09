var getField = function(fieldId) {
    return document.getElementById(fieldId);
}
var getFieldMsg = function(fieldId) {
    return document.getElementById(fieldId).parentElement
            .getElementsByClassName("field-message").item(0);
}
var validateInputs = function(fieldRules) {
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
var hideMessages = function(fieldRules) {
    for (var fieldName in fieldRules) {
        getFieldMsg(fieldName).style.display = "none";
    }
}