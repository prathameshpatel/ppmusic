<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />

<section>
	<h1><c:out value="${prod.getDescription()}" /></h1>
	<p>Product Price: $<c:out value="${prod.getPrice()}" /></p>
	
	<c:choose>
    	<c:when test="${sessionScope.user != null}">
    		<c:url var="listenURL" value="/listen.html" />
			<p><a href="${listenURL}">Listen to sample songs</a><br></p>
    	</c:when>
    	<c:otherwise>
        	<c:url var="listenURL" value="/register.html?status=1" />
			<p><a href="${listenURL}">Listen to sample songs</a><br></p>
    	</c:otherwise>
	</c:choose>
	
	<c:url var="addToCartURL" value="/cart.html">
		<c:param name="action" value="addToCart"/>
		<c:param name="prodCode" value="${prod.getCode()}"/>
	</c:url>
	<p><a href="${addToCartURL}">Add to Cart</a><br></p>
	
	<c:url var="cartURL" value="/cart.html?action=showCart" />
	<p><a href="${cartURL}">Show Cart</a><br></p>
	
	<c:url var="catalogURL" value="/catalog.html" />
	<p><a href="${catalogURL}">Back to Catalog</a><br></p>
	
	
</section>
