"use strict"

var emailFieldRules = {
    "subject" : function(value) {
        return !!value;
    },
    "message" : function(value) {
        return !!value;
    }
}

var templates = {
    "None" : "",
    "Tomorrow meeting" : "Hello <contact.firstName>.\n" +
        "This email is just a reminder about tomorrow's meeting.\n" +
        "Don't forget to come, please.",
    "Have a nice day" : "Hi <contact.firstName>!\n" + 
        "Just wanted to wish you to have a nice day.)\n" +
        "Good luck."
}

function fillTemplateSelect() {
    var select = document.getElementById("template");
    select.innerHTML = "";
    var option;
    for (var templateName in templates) {
        option = document.createElement("option");
        option.value = templateName;
        option.innerHTML = templateName;
        select.appendChild(option);
    }
    var timesSelectClicked = 0;
    select.addEventListener("click", function(e) { 
        if (timesSelectClicked == 0) {
            timesSelectClicked += 1;
        }
        else if (timesSelectClicked == 1) {
            timesSelectClicked = 0;
            var textarea = document.getElementById("message");
            textarea.value = templates[e.target.value];
        }
    })
}

addLoadEvent(function() {
    fillTemplateSelect();

    var emailBtn = document.getElementById("emailBtn");
    emailBtn.addEventListener("click", function() { 
        hideMessages(emailFieldRules);
        if (validateInputs(emailFieldRules)) {
            document.forms["email_form"].submit();
        }
    });
});