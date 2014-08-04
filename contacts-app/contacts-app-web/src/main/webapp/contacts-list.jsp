<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:main_layout>
	<jsp:attribute name="head_area">
        <title>Contacts</title>  
        <script type="text/javascript" src="<c:url value="/static/js/contact-list.js" />"></script>
	</jsp:attribute>
	<jsp:attribute name="content">
        <form id="contacts_form" method="post" action="<c:url value="/"/>">
	        <table class="contacts-table">
		       <thead>
		           <th class="control-col"></th>
		           <th>Full Name</th>
		           <th>Date of Birth</th>
		           <th>Address</th>
		           <th>Company</th>
		           <th class="control-col"></th>
	            </thead>
	            <tbody>
					<c:forEach items="${contacts}" var="contact">
						<tr>
						    <td><input type=checkbox name="selected_contacts"
								value="${contact.id}" /></td>
							<td><a href="<c:url value="/contact?id=${contact.id}" />">${contact.lastName} ${contact.firstName}</a></td>
							<td><fmt:formatDate pattern="dd.MM.yyyy" value="${contact.dateOfBirth}" /></td>
							<td>${contact.city}${contact.city != null && contact.street != null ? ", " : null}${contact.street}</td>
							<td>${contact.company}</td>
	                        <td><a href="<c:url value="/contact?id=${contact.id}" />" class="fa fa-pencil fa-lg"></a></td>
						</tr>
					</c:forEach>
	            </tbody>
	        </table>
        </form>
        <c:if test="${totalpages > 1}">
	        <div class="pagination">
	            <c:if test="${page > 1}">
		            <a href="<c:url value="/contacts?page=${page-1}" />">Previous</a>
		            &nbsp;
	            </c:if>
	            <c:forEach begin="1" end="${totalpages}" varStatus="loop">
	                <c:choose>
	                    <c:when test="${page == loop.index}">
	                        <strong>${loop.index}</strong>
	                    </c:when>
	                    <c:otherwise>
	                        <a
								href="<c:url value="/contacts?page=${loop.index}"/>">${loop.index}</a>
	                    </c:otherwise>
				    </c:choose>
				    &nbsp;
				</c:forEach>
	            <c:if test="${page < totalpages}">
	                <a href="<c:url value="/contacts?page=${page+1}" />">Next</a>
	            </c:if>
	        </div>
        </c:if>
	</jsp:attribute>
	<jsp:attribute name="sidebar">
        <a class="button" href="<c:url value="/contact/add" />">
            <span class="fa fa-plus fa-fw"></span> 
            Create new contact
        </a>
        <a class="button" href="#" name="remove_btn" id="remove_btn">
            <span class="fa fa-trash-o fa-fw"></span> 
            Remove selected
        </a>
        <a class="button" href="#">
            <span class="fa fa-envelope-o fa-fw"></span> 
            Email
        </a>
    </jsp:attribute>
    <jsp:attribute name="bottom_area">
        
    </jsp:attribute>
</t:main_layout>
