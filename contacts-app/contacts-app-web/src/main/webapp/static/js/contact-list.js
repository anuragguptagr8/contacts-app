"use strict"
addLoadEvent(function() {
    var removeButton = document.getElementById("remove_btn");
    removeButton.addEventListener("click", function() { 
    	if (confirm('Are you sure you want to permanently remove all selected contacts?')) {
    	    document.forms["contacts_form"].action += "contact/remove";
    	    document.forms["contacts_form"].submit();
    	}
    });
    var emailButton = document.getElementById("email_btn");
    emailButton.addEventListener("click", function() { 
	    document.forms["contacts_form"].action += "email";
	    document.forms["contacts_form"].method = "get";
	    document.forms["contacts_form"].submit();
    });
});