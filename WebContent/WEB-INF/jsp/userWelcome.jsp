<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />
<section>

	<h1>You are inside the user section</h1>
	<h2>You can browse catalog of products or check your cart</h2>
	
	<c:url var="catalogURL" value="/catalog.html" />
	<p><a href="${catalogURL}">Browse Catalog</a><br></p>
	
	<c:url var="cartURL" value="/cart.html" />
	<p><a href="${cartURL}">Show Cart</a><br></p>
	
	
</section>

