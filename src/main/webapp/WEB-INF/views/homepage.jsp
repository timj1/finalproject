<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

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
        <div class="column side" style="background-color:#aaa;">
        </div>

        <div class="column middle" style="background-color:#bfbfbf;">
            <h3>Thread</h3>
            <hr/>
            <div style="border:solid black 1px;" >
                    <c:set var="column" value="1"/>
                    <c:forEach items="${messagee}" var="currentListItem">
                        <c:if test="${column == 1}">

                        </c:if>
                        <div style="float:left; border:solid black 1px;"> <p style="margin:0;padding-right:20px;"><c:out value="${currentListItem.titleName}"/></p></div>
                        <div style="border:solid black 1px;"><p style="margin:0;"> - user <c:out value="${currentListItem.userName}"/></p> </div>
                        <div> <c:out value="${currentListItem.createDate}"/> </div>
                        <div style="border:solid green 1px;"><p> <c:out value="${currentListItem.message}"/></p> </div>
                        <div>--------------------------------------------------------------</div>

                        <c:if test="${column == 2}">

                            <c:set var="column" value="0"/>
                        </c:if>
                        <c:set var="column" value="${column+1}"/>
                    </c:forEach>
            </div>

            <a href="${pageContext.request.contextPath}/editpage">editpage</a>
            <a href="${pageContext.request.contextPath}/postmessage">Send message</a>
        </div>

        <div class="column side" style="background-color:#ccc;">

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
