<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:main_layout>
    <jsp:attribute name="head_area">
        <title>Contacts</title>  
        <link type="text/css" rel="stylesheet" href="<c:url value="/static/css/profile.css" />"/>
        <link rel="stylesheet" href="<c:url value="/static/css/search.css" />">
        <script type="text/javascript" src="<c:url value="/static/js/search.js" />"></script>
    </jsp:attribute>
    <jsp:attribute name="content">
        <h2 class="page-name">Search Page</h2>
        <div class="contact-form">
            <form name="contact_form" method="get" action="<c:url value="/contacts/search"/>">
                <div class="field">
                    <div class="field-label">
                        <label for="firstName">First Name </label>
                    </div>
                    <div class="field-input" >
                        <input type="text" name="firstName" id="firstName" value="${contact.firstName}" >
                        <div class="field-message">
                            The field shouldn't be empty.
                        </div> 
                    </div>
                </div>
                <div class="field">
                    <div class="field-label">
                        <label for="lastName">Last Name </label>
                    </div>
                    <div class="field-input" >
                        <input type="text" name="lastName" id="lastName" value="${contact.lastName}" >
                        <div class="field-message">
                            The field shouldn't be empty.
                        </div> 
                    </div>  
                </div>
                <div class="field">
                    <div class="field-label">
                        <label for="middleName">Middle Name</label>
                    </div>
                    <div class="field-input">
                        <input type="text" name="middleName" id="middleName" value="${contact.middleName}">
                    </div>
                </div>
                <div class="field">
                    <div class="field-label">
                        <label for="dateOfBirth">Date of Birth</label>
                    </div>
                    <div class="field-input">
<%--                         <input type="text" name="dateOfBirth" id="dateOfBirth" value="<fmt:formatDate pattern="dd.MM.yyyy" value="${contact.dateOfBirth}" />" placeholder="DD.MM.YYYY"> --%>
                        after <input style="width:36%" type="text" name="dateAfter" id="dateAfter" placeholder="DD.MM.YYYY">
                        before <input style="width:36%" type="text" name="dateBefore" id="dateBefore" placeholder="DD.MM.YYYY">
                        <div class="field-message">
                            The date should match the DD.MM.YYYY pattern like '22.04.1988'
                        </div> 
                    </div>
                </div>
                <div class="field"> 
                    <div class="field-label">
                        <label>Gender</label>
                    </div>
                    <div class="field-input">
                        <input type="radio" value="male" name="gender" ${contact.isMale ? 'checked' : ''}/>
                        <label for="male">
                            Male
                        </label> 
                        <input type="radio" value="female" name="gender" ${(contact.isMale != null) && (!contact.isMale) ? 'checked' : ''}/>
                        <label for="female">
                            Female
                        </label>
                    </div>
                </div>
                <div class="field">
                    <div class="field-label">
                        <label>Citizenship </label>
                    </div>
                    <div class="field-input">
                        <select name="citizenship">
                            <option value="">-- Choose a citizenship --</option>
                            <c:forEach items="${countries}" var="countryId">
                                <option value="${countryId}" ${contact.citizenship == countryId ? 'selected' : ''} >
                                         ${countryId}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="field">
                    <div class="field-label">
                        <label>Relationship status </label>
                    </div>
                    <div class="field-input">
                        <select name="relationshipStatusId">
                            <option value="">-- Select a relationship status --</option>
                            <c:forEach items="${relationships}" var="statusId">
                                <option value="${statusId}" ${contact.relationshipStatusId == statusId ? 'selected' : ''} >
                                         ${statusId}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                
                <div class="field">
                    <div class="field-label">
                        <label for="country">Country</label>
                    </div>
                    <div class="field-input">
                        <select name="country">
                            <option value="">-- Choose a country --</option>
                            <c:forEach items="${countries}" var="countryId">
                                <option value="${countryId}" ${contact.country == countryId ? 'selected' : ''} >
                                    ${countryId}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="field">
                    <div class="field-label">
                        <label for="city">City </label>
                    </div>
                    <div class="field-input" >
                        <input type="text" name="city" id="city" value="${contact.city}" >
                    </div> 
                </div>
                <div class="field">
                    <div class="field-label">
                        <label for="street">Street Address </label>
                    </div>
                    <div class="field-input" >
                        <input type="text" name="street" id="street" value="${contact.street}" > 
                    </div>  
                </div>
                <div class="field">
                    <div class="field-label">
                        <label for="zip">ZIP/Postal Code </label>
                    </div>
                    <div class="field-input" >
                        <input type="text" name="postalCode" id="postalCode" value="${contact.postalCode}" >
                    </div>  
                </div>

            </form>
            
        </div>  
    </jsp:attribute>
    
    <jsp:attribute name="sidebar">
        <a href="#" id="searchBtn">Start Search<br/><br/><span class="fa fa-search fa-5x"></span></a>
    </jsp:attribute>
    
</t:main_layout>
