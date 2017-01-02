<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside id="sidebar">
    <nav>
        <c:url var="welcomeURL" value="/welcome.html" />
        <c:url var="adminWelcomeURL" value="/adminController/adminWelcome.html" />
        <c:url var="userWelcomeURL" value="/userWelcome.html" />

        <h4>Links</h4>

        <a href="${welcomeURL}">Home</a><br>
        <%-- <a href="${adminWelcomeURL}">Admin Service</a><br> --%>
        <a href="${userWelcomeURL}">User Service</a> <br>
    </nav>
</aside>