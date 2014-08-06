"use strict"
addLoadEvent(function() {
    var searchBtn = document.getElementById("searchBtn");
    searchBtn.addEventListener("click", function() { 
	    document.forms["contact_form"].submit();
    });
});