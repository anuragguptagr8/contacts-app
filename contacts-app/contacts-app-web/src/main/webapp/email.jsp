<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:main_layout>
    <jsp:attribute name="head_area">
        <title>Contacts</title>  
        <link type="text/css" rel="stylesheet" href="<c:url value="/static/css/profile.css" />"/>
        <link rel="stylesheet" href="<c:url value="/static/css/email.css" />">
        <script type="text/javascript" src="<c:url value="/static/js/validation.js" />"></script>
        <script type="text/javascript" src="<c:url value="/static/js/email.js" />"></script>
    </jsp:attribute>
    <jsp:attribute name="content">
        <h2 class="page-name">Email</h2>
        <div class="contact-form">
            <form method="post" action="<c:url value="/email/send" />" name="email_form" >
                <div class="field">
                    <div class="field-label">
                        <label for="recipients">To </label>
                    </div>
                    <div class="field-input" >
                        <span>${emails}</span> 
                    </div>  
                </div>
                
                <c:forEach items="${contacts}" var="contact">
                    <input type="hidden" name="selected_contacts" value="${contact.id}" />
                </c:forEach>
                
                <div class="field">
                    <div class="field-label">
                        <label for="subject">Subject </label>
                    </div>
                    <div class="field-input" >
                        <input type="text" name="subject" id="subject" required>
                        <div class="field-message">
                            Can't be empty
                        </div>
                    </div>  
                </div>
                
                <div class="field">
                    <div class="field-label">
                        <label>Template </label>
                    </div>
                    <div class="field-input">
                        <select name="template" id="template">
<!--                             <option value="none">--Choose a template--</option> -->
                        </select>
                    </div>
                </div>
                <div class="field">
                    <div class="field-label">
                        <label for="message">Message </label>
                    </div>
                    <div class="field-input" >
                        <textarea name="message" id="message" rows="10" placeholder="Your message here" style="width: 100%"></textarea>
                        <div class="field-message">
                            Can't be empty
                        </div>
                    </div> 
                </div>
            </form>
        </div>  

    </jsp:attribute>
    
    <jsp:attribute name="sidebar">
        <a href="#" id="emailBtn">Send Email<br/><br/><span class="fa fa-envelope-o fa-5x"></span></a>
    </jsp:attribute>
    
</t:main_layout>
