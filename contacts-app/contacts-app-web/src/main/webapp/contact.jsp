<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:main_layout>
    <jsp:attribute name="head_area">
        <title>Contacts</title>  
        <link type="text/css" rel="stylesheet" href="<c:url value="/static/css/profile.css" />"/>
        <script type="text/javascript">
        "use strict"
        
        </script>
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
				    </div>
				</div>
				<div class="field">
				    <div class="field-label">
				        <label for="lastName">Last Name * </label>
				    </div>
				    <div class="field-input" >
				        <input type="text" name="lastName" id="lastName" value="${contact.lastName}" required>
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
			    <button type="submit">submit</button>
			</form>
	        
        </div>  
    </jsp:attribute>
    <jsp:attribute name="sidebar">
        <table class="button-container">
            <tr>
                <td>
                    <a href="#" class="button small center-text">
                        <span class="fa fa-plus"></span> Add new No.
                    </a>
                </td>
                <td>
                    <a href="#" class="button small center-text">
                        <span class="fa fa-trash-o"></span> Remove selected
                    </a>
                </td>
            </tr>
        </table>

        <table class="contacts-table">
            <thead>
                <th class="control-col"></th>
                <th>Phone No.</th>
                <th>Type</th>
                <th class="control-col"></th>
            </thead>
            <tbody>
                <tr>
                    <td><input type=checkbox /></td>
                    <td>
                        <div class="phone-no"><a href="#">+375295684111</a></div>
                        <div class="phone-comments">Some comments here</div>
                    </td>
                    <td>Home</td>
                    <td><a href="#" class="fa fa-pencil"></a></td>
                </tr>
                <tr>
                    <td><input type=checkbox /></td>
                    <td>
                        <div class="phone-no"><a href="#">+375295684111</a></div>
                        <div class="phone-comments">Some comments here</div>
                    </td>
                    <td>Home</td>
                    <td><a href="#" class="fa fa-pencil"></a></td>
                </tr>
                <tr>
                    <td><input type=checkbox /></td>
                    <td>
                        <div class="phone-no"><a href="#">+375295684111</a></div>
                        <div class="phone-comments">Some comments here</div>
                    </td>
                    <td>Home</td>
                    <td><a href="#" class="fa fa-pencil"></a></td>
                </tr>
                <tr>
                    <td><input type=checkbox /></td>
                    <td>
                        <div class="phone-no"><a href="#">+375295684111</a></div>
                        <div class="phone-comments">Some comments here</div>
                    </td>
                    <td>Home</td>
                    <td><a href="#" class="fa fa-pencil"></a></td>
                </tr>
            </tbody>

        </table>
    </jsp:attribute>
</t:main_layout>
