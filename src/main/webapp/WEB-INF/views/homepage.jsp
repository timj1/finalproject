<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="eng">
<head>
  <title>My page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style><%@include file="/WEB-INF/views/css/style.css"%></style>
</head>
<body>
<div class="header">Forumtech</div>
<div class="row">
      <div class="column side" style="background-color:#999999;">Navtree</div>
        <div class="column middle" style="background-color:#bfbfbf;">Content</div>
        <div class="column side" style="background-color:#999999;">
            <form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">
                        <h3>login</h3>
                        <c:if test="${registrationComplete != null}">
                            <p style="color:green;"><c:out value="${registrationComplete}" /></p>
                        </c:if>

                        <c:if test="${param.error != nul}">
                            <p style="color:red;">Invalid username or password</p>
                        </c:if>

                        <label>Username: </label><input type="text" name="username" /><br/>
                        <label>Password: </label><input type="password" name="password" /><br/>
                        <input type="submit" value"Submit" />

                    </form:form>

                    <br/>
                    <a href="${pageContext.request.contextPath}/register">Register</a>
        </div>
</div>
<div class="footer">copyright timj1</div>
</body>
</html>
