<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />

<c:url value="/cart.html" var="cartURL"/>
<section>
	<h1>You are inside the Cart</h1>
	<table>
	<c:forEach var="item" items="${cart.getItems()}" >
		<form action="${cartURL}">
			<tr>
			<td><input type="hidden" name="action" value="update"></td>
			<td><input type="hidden" name="code" value="${item.getProduct().getCode()}"></td>
				<td>${item.getProduct().getCode()}</td>
				<td>${item.getProduct().getDescription()}</td>
				<%-- <td>${item.getQuantity()}</td> --%>
				<td>
					<select name="quantity">
						<c:forEach begin="1" end="${quantity}" step="1" var="i" >
							<option
								<c:if test="${i == item.getQuantity()}">
								selected = "selected"	
								</c:if>
								value="${i}">${i}
							</option>
						</c:forEach>
					</select>
				</td>
				<td><button type="submit">Save</button></td>
				<td>
					<c:url var="remove" value="/cart.html?action=remove&code=${item.getProduct().getCode()}" />
					<a href="${remove}">Remove</a>
				</td>
			</tr>
		</form>
	</c:forEach>
	</table>
	
	<c:url var="catalogURL" value="/catalog.html" />
	<p><a href="${catalogURL}">Back to Catalog</a><br></p>
	<c:url var="checkoutURL" value="/checkout.html" />
	<p><a href="${checkoutURL}">Checkout</a><br></p>
	
	
</section>