<%-- 
    Document   : home
    Created on : Jan 12, 2021, 3:26:01 PM
    Author     : Vu Ngoc Thinh
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/base.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <c:set var="mess" scope="request" value="${requestScope.mess}"/>
            <%@include file="header.jsp"%>
            <div class="content-container">
                <h1 id="form-title">Login Form</h1>  
                <div class="mess">
                    <c:if test="${mess!=Empty}">
                        <h5>${mess}</h5>  
                    </c:if> 
                </div>                  
                <form class="form" method="post" action="../J3LP0001/home">
                    <div class="form-item">
                        <label class="form-label" for="userName">User name:</label> 
                        <input type="text" required id="userName" name="userName" value="${param.userName}"/>
                    </div>
                    <div class="form-item">
                        <label class="form-label" for="password">Password:</label> 
                        <input type="password" required id="password" name="password" value="${param.password}"/>
                    </div>
                    <div class="form-item form-button">
                        <input type="submit" id="login-btn" class="button" value="Sign in"/>
                        <a href="../J3LP0001/register" id="register" class="form-label">Register</a>
                    </div>
                </form>
            </div> 
        </div>
    </body>
</html>