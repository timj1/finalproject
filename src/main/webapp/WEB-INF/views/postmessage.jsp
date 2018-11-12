<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="eng">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style><%@include file="/WEB-INF/views/css/style.css"%></style>
</head>

<body>

<div class="header">Forumtech</div>

<div class="row">
  <div class="column side" style="background-color:#aaa;"></div>

  <div class="column middle" style="background-color:#bbb;">
        <div>
            <h3>Send message</h3>
            <hr>
            <form:form action="${pageContext.request.contextPath}/processMessage" modelAttribute="newmessage" method="POST">
                    <c:if test="${registrationError != null}">
                       <p style="color:red;"><c:out value="${registrationError}" /></p>
                    </c:if>
                <form:input path="userName" type="hidden" value="${currentUser}" />
                <form:input path="title" placeholder="title" /><br/>
                <form:textarea path="message" rows="12" cols="60" maxlength="600"/><br/>
                <input type="submit" value="Submit" />
            </form:form>
        </div>

        <br/>
        <br/>

        <a href="${pageContext.request.contextPath}/">main</a>
        <a href="${pageContext.request.contextPath}/editpage">editpage</a>
  </div>

  <div class="column side" style="background-color:#ccc;">
        <p>"${currentUser}"</p>
            <security:authorize access="isAuthenticated()">
                authenticated as <security:authentication property="principal.username" />
                <c:set var="rootContext" value="${pageContext.request.contextPath}/logout"/>
                <a href="${rootContext}">logout</a>
            </security:authorize>

            <security:authorize access="!isAuthenticated()">
                    <h3>Login</h3>
                    <hr>

                    <form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">

                        <c:if test="${registrationComplete != null}">
                            <p style="color:green;"><c:out value="${registrationComplete}" /></p>
                        </c:if>

                        <c:if test="${param.error != nul}">
                            <p style="color:red;">Invalid username or password</p>
                        </c:if>

                        <label>Username: </label><input type="text" name="username" />
                        <label>Password: </label><input type="password" name="password" />
                        <input type="submit" value"Submit" />

                    </form:form>
                     <br/>
                     <a href="${pageContext.request.contextPath}/registration">Register</a>
            </security:authorize>
  </div>
</div>

<div class="footer">copyright timj1</div>

</body>

</html>