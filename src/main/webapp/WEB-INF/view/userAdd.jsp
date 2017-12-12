<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 10.12.2017
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form:form id = "reg" action="/userAdd" method="post" commandName="user">
    <table>
        <c:if test="${!empty user.login}">
            <tr>
                <td>
                    <form:label path="idUser">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="idUser" readonly="true" size="8"  disabled="true" />
                    <form:hidden path="id" />
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="firstName">
                    <spring:message text="First Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="firstName" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lastName">
                    <spring:message text="Last Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="lastName" />
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="login">
                    <spring:message text="Login"/>
                </form:label>
            </td>
            <td>
                <form:input path="login" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password">
                    <spring:message text="Password"/>
                </form:label>
            </td>
            <td>
                <form:input path="password" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="email">
                    <spring:message text="Email"/>
                </form:label>
            </td>
            <td>
                <form:input path="email" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="role">
                    <spring:message text="Role"/>
                </form:label></td>
            <td>
                <form:select path="roles" items="${roles}" multiple="true" itemValue="idRole" itemLabel="typeRole" />
            </td>
        </tr>


        <tr>
            <td colspan="2">
                <c:if test="${!empty user.firstName}">
                    <input type="submit"
                           value="<spring:message text="Edit User"/>" />
                </c:if>
                <c:if test="${empty user.firstName}">
                    <input type="submit"
                           value="<spring:message text="Add User"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<br>
<h3>Persons List</h3>
<c:if test="${!empty listUsers}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">First Name</th>
            <th width="120">Last Name</th>
            <th width="120">Email</th>
            <th width="120">Login</th>
            <th width="120">Password</th>
            <th width="120">Role</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.idUser}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.roles}</td>
                <td><a href="<c:url value='/edit/${user.idUser}'/>" >Edit</a></td>
                <td><a href="<c:url value='/remove/${user.idUser}' />" >Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
