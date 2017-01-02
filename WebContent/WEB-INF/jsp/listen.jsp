<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />

<section>
	<h1><c:out value="${prod.getDescription()}" /></h1>
	<p>Product Price: $<c:out value="${prod.getPrice()}" /></p>
	
	<h2>You are inside the song listening section</h2>
	<h3>You can listen to songs here by clicking on their mp3</h3>
	
	<c:url var="addToCartURL" value="cart.html">
		<c:param name="addItem" value="true"/>
	</c:url>
	<p><a href="${addToCartURL}">Add to Cart</a><br></p>
	
	<c:url var="cartURL" value="/cart.html" />
	<p><a href="${cartURL}">Show Cart</a><br></p>
	
	<c:url var="catalogURL" value="/catalog.html" />
	<p><a href="${catalogURL}">Back to Catalog</a><br></p>
	
	<c:url var="prodURL" value="/product.html" />
	<p><a href="${prodURL}">Back to Product page</a><br></p>
	
	<p>
		<c:forEach var="track" items="${prod.getTracks()}">
			<c:url var="trackURL" value="/track.html">
				<c:param name="trackNum" value="${track.trackNumber}"/>
			</c:url>
			<p><c:out value="${track.getTitle()} " /><a href='<c:url value= "/sound/${prod.getCode()}/${track.getSampleFilename()}"/>'>MP3</a><br></p>	
		</c:forEach>
	</p>
</section>