<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />
<section>
<c:url value="/register.html" var="registerURL"/>
<c:choose>
	<c:when test='${param["status"] eq "1" }'>
	<c:set var="action" value="listen"/>
	</c:when>
	<c:otherwise>
	<c:set var="action" value="checkout"/>
	</c:otherwise>
</c:choose>
<h1>Music registration</h1>

<p>You need to register before you can listen to a song or you can checkout with your cart</p>

<form action="${registerURL}" method="get">
<input type="hidden" name="action" value="${action}"/>        
    <label>Email:</label>
    <input type="email" name="email"><br>
    <label>First Name:</label>
    <input type="text" name="firstName"><br>
    <label>Last Name:</label>
    <input type="text" name="lastName"><br>        
    <label>&nbsp;</label>
    <input type="submit" value="Register" class="margin_left">
</form>
</section>
