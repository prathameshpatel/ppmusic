<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />

<section>
	<h1>You are inside the catalog section</h1>
	<h2>You can check individual products here by clicking on them</h2>
	
	<p>
	<c:forEach var="prod" items="${prodList}">
		<c:url var="prodURL" value="/product.html">
			<c:param name="prodCode" value="${prod.getCode()}" />
		</c:url>
		<p><a href="${prodURL}"><c:out value="${prod.getDescription()}" /></a><br></p>		
	</c:forEach>
	</p>
	<c:url var="cartURL" value="/cart.html" />
	<p><a href="${cartURL}">Show Cart</a><br></p>
</section>