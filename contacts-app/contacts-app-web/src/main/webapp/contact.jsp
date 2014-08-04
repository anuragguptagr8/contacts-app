<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:main_layout>
    <jsp:attribute name="head_area">
        <title>Contacts</title>  
        <link type="text/css" rel="stylesheet" href="<c:url value="/static/css/profile.css" />"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/static/css/phone.css" />"/>
        <script type="text/javascript" src="<c:url value="/static/js/contact.js" />"></script>
        <script type="text/javascript" src="<c:url value="/static/js/phone.js" />"></script>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div class="avatar">
            <img src="<c:url value="/static/img/user.gif" />" />
        </div>
        <div class="contact-form">
			<form name="contact_form" method="post" action="<c:url value="/contact/save"/>">
			    <input type="hidden" name="id" value="${contact.id}"/> 
			    <input type="hidden" name="photoId" value="${contact.photoId}"/>
			    <div class="field">
				    <div class="field-label">
				        <label for="firstName">First Name * </label>
				    </div>
				    <div class="field-input" >
				        <input type="text" name="firstName" id="firstName" value="${contact.firstName}" required >
				        <div class="field-message">
				            The field shouldn't be empty.
				        </div> 
				    </div>
				</div>
				<div class="field">
				    <div class="field-label">
				        <label for="lastName">Last Name * </label>
				    </div>
				    <div class="field-input" >
				        <input type="text" name="lastName" id="lastName" value="${contact.lastName}" required>
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
				        <input type="text" name="dateOfBirth" id="dateOfBirth" value="<fmt:formatDate pattern="dd.MM.yyyy" value="${contact.dateOfBirth}" />" placeholder="DD.MM.YYYY">
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
				        <label for="website">Web Site </label>
				    </div>
				    <div class="field-input" >
				        <input type="text" name="website" id="website" value="${contact.website}" placeholder="some-site.com"> 
				    </div>  
				</div>
				<div class="field">
				    <div class="field-label">
				        <label for="email">Email </label>
				    </div>
				    <div class="field-input" >
				        <input type="text" name="email" id="email" value="${contact.email}" placeholder="some@email.com">
				        <div class="field-message">
                            The email should look something like that 'some@email.com'
                        </div> 
				    </div>
				</div>
				<div class="field">
				    <div class="field-label">
				        <label for="company">Company</label>
				    </div>
				    <div class="field-input">
				        <input type="text" name="company" id="company" value="${contact.company}">
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
			    <div class="save-button">
                    <a href="#" id="save_button" class="button small center-text">Save</a>
                </div> 
                
                <c:forEach items="${numbers}" var="number">
	                <div class="phone-data" name="phone${number.id}">
	                    <input type="hidden" name="phoneId" value="${number.id}" />
						<input type="hidden" name="countryCode" value="${number.countryCode}" />
						<input type="hidden" name="operatorCode" value="${number.operatorCode}" />
						<input type="hidden" name="number" value="${number.number}" />
						<input type="hidden" name="phoneTypeId" value="${number.typeId}" />
						<input type="hidden" name="phoneComment" value="${number.comment}" />
	                </div>
                </c:forEach>
                
			</form>
	        
        </div>  
    </jsp:attribute>
    <jsp:attribute name="sidebar">
        <table class="button-container">
            <tr>
                <td>
                    <a href="#" id="addPhoneBtn" class="button small center-text">Add new No.</a>
                </td>
                <td>
                    <a href="#" id="removePhoneBtn" class="button small center-text">Remove selected</a>
                </td>
            </tr>
        </table>

        <table class="contacts-table" id="phones-table">
            <thead>
                <th class="control-col"></th>
                <th>Phone No.</th>
                <th>Type</th>
                <th class="control-col"></th>
            </thead>
            <tbody>
              <%--   <tr>
                    <td><input type=checkbox /></td>
                    <td>
                        <div class="phone-no"><a href="#">+375295684111</a></div>
                        <div class="phone-comments">Some comments here</div>
                    </td>
                    <td>Home</td>
                    <td><a href="#" class="fa fa-pencil"></a></td>
                </tr> --%>
                
            </tbody>

        </table>
    </jsp:attribute>
    
    <jsp:attribute name="bottom_area">
	    <div id="popup-wrapper">
	        <div id="phone-popup">
	            <form action="#" id="phone_form">
	                <span class="fa fa-times" id="close"></span>
	                <h2>Enter a phone number</h2><hr/>
	                <div>
	                    <input type="hidden" name="formPhoneId" id="formPhoneId" value="" />
	                    <input size="5" type="text" name="countryCodeTxt" id="countryCodeTxt" placeholder="Country code" />                      
	                    <input size="5" type="text" name="operatorCodeTxt" id="operatorCodeTxt" placeholder="Operator code" />
	                    <input type="text" name="numberTxt" id="numberTxt" placeholder="number" />
	                    <select name="phoneTypeSlct" id="phoneTypeSlct" >
	                        <option value="1">Home</option>
	                        <option value="2">Cellular</option>
	                    </select>
	                    <div class="field-message">
	                        Only numbers are accepted. Country code can't be entered without an operator code. Max length is 9 digits.
	                    </div>
	                </div>
	                <div>
	                    <textarea name="commentsTxt" placeholder="Any comments here" id="commentsTxt"></textarea>
	                    <div class="field-message">
	                        Max length is 300 digits.
	                    </div>
	                </div>
	                <table class="button-container">
	                    <tr>
	                        <td><a href="#" class="button small center-text" id="phoneOk">OK</a></td>
	                        <td><a href="#" class="button small center-text" id="phoneCancel">Cancel</a></td>
	                    </tr>
	                </table>
	            </form>
	        </div>
	    </div>
    </jsp:attribute>
</t:main_layout>
