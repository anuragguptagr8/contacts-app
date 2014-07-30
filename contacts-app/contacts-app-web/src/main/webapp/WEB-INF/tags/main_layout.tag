<!DOCTYPE html>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="head_area" fragment="true" %>
<%@attribute name="content" fragment="true" %>
<%@attribute name="sidebar" fragment="true" %>
<%@attribute name="bottom_area" fragment="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<link type="text/css" rel="stylesheet" href="<c:url value="/static/css/layout.css" />" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/static/css/contacts.css" />"/>
	<link type="text/css" rel="stylesheet" href="<c:url value="/static/font-awesome-4.1.0/css/font-awesome.min.css" />"/>
	<script type="text/javascript">
    function addLoadEvent(func) { 
	    var oldonload = window.onload; 
		if (typeof window.onload != 'function') { 
			  window.onload = func; 
	    } else { 
	        window.onload = function() { 
	            if (oldonload) { 
	                oldonload(); 
	            } 
	            func(); 
	        } 
		} 
	} 
	addLoadEvent(function() {
		setTimeout(function() {
			document.getElementById('action_message').innerHTML = "";
		}, 7000);
	});
	</script>
    <jsp:invoke fragment="head_area"/>
</head>
<body>
    <div class="wrapper">
        <div class="header">
            <h1><a href="#">Contacts App</a></h1>
            <div class="header-sidebar">
                <a href="#"><span class="fa fa-search"></span> Search</a>
            </div>
        </div>
        <div class="container">
            <div class="content">
	            <div id="action_message" class="action_message">${action_message}</div>
                <jsp:invoke fragment="content"/>
            </div>
            <div class="sidebar">
                <jsp:invoke fragment="sidebar"/>
            </div>
        </div>
        <div class="push"></div>
    </div>
    <div class="footer"><p>Kiryl Tsvilik for iTechArt Java Courses, 2014</p></div>
    <jsp:invoke fragment="bottom_area"/>
</body>
</html>
