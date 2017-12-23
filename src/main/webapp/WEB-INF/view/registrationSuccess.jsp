<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 13.12.2017
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <table border="0">
        <tr>
            <td colspan="2" align="center"><h2>Registration Succeeded!</h2></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <h3>Thank you for registering! Here's the review of your details:</h3>
            </td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td>${user.firstName}</td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td>${user.lastName}</td>
        </tr>
        <tr>
            <td>User Name:</td>
            <td>${user.userName}</td>
        </tr>
        <tr>
            <td>E-mail:</td>
            <td>${user.email}</td>
        </tr>
        <tr>
            <td>Password:</td>
            <td>${user.password}</td>
        </tr>

    </table>
</div>
</body>
</html>
