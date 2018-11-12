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
            <div style="" >
                    <c:set var="column" value="1"/>
                    <c:forEach items="${messagee}" var="currentListItem">
                    <div style="border:solid grey 1px;">
                        <c:url var="updateLink" value="updateMessage">
                            <c:param name="messageIdUpdate" value="${currentListItem.id}" />
                            <c:param name="userNameUpdate" value="${currentListItem.userName}" />
                            <c:param name="titleUpdate" value="${currentListItem.titleName}" />
                            <c:param name="messageUpdate" value="${currentListItem.message}" />
                        </c:url>

                    <div style="background-color:#d9d9d9;border-bottom:solid grey 1px;">
                        <div style="float:left;"> <p style="margin:0;padding-right:20px;"><c:out value="${currentListItem.titleName}"/></p></div>
                        <div style=""><p style="margin:0;padding-right:20px;"> - user <c:out value="${currentListItem.userName}"/></p> </div>

                        <div style="">
                            <security:authorize access="isAuthenticated()">
                                <security:authentication var="authUser" property="principal.username" />
                                <c:if test="${authUser == currentListItem.userName}">
                                    <a href="${updateLink}" style="">Update</a>
                                </c:if>
                            </security:authorize>
                        </div>

                        <div>
                            <c:out value="${currentListItem.createDate}"/>
                        </div>
                    </div>
                        <div style="">
                             <p> <c:out value="${currentListItem.message}"/></p>
                             <c:if test="${currentListItem.createDate != currentListItem.updateDate}">
                                 <p style="margin:0;padding:0;font-size:14px;">updated  -  <c:out value="${currentListItem.updateDate}" /></p>
                             </c:if>
                         </div>

                    </div><br/>
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

                        <c:if test="${param.error != null}">
                            <p style="color:red;">Invalid username or password</p>
                        </c:if>

                        <p><label>Username: </label><input type="text" name="username" /></p>
                        <p><label>Password: </label><input type="password" name="password" /></p>
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
