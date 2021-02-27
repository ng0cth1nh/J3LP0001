<%-- 
    Document   : register
    Created on : Jan 12, 2021, 7:55:16 PM
    Author     : Vu Ngoc Thinh
--%>

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
            <c:set var="mess" scope="session" value="${sessionScope.mess}"/>
            <%@include file="header.jsp"%> 
            <div class="content-container">
                <h1 id="form-title">Registration Form</h1>
                <div class="mess">
                    <c:if test="${mess!=Empty}">
                        <h5>${mess}</h5>  
                    </c:if> 
                </div> 
                <form class="form" method="post" action="../J3LP0001/register">
                    <div class="form-item">
                        <label class="form-label" for="userName">User name:</label> 
                        <input type="text"  id="userName" name="userName" value="${param.userName}"/>
                    </div>
                    <div class="form-item">
                        <label class="form-label" for="password">Password:</label> 
                        <input type="password" id="password" name="password" value="${param.password}"/>
                    </div>
                    <div class="form-item">
                        <label class="form-label" for="type">User Type:</label> 
                        <select id="type" name="userType">
                            <option value="true">Teacher</option>
                            <option value="false">Student</option>
                        </select>
                    </div>
                    <div class="form-item">
                        <label class="form-label" for="email">Email:</label> 
                        <input type="text" id="email" name="email" value="${param.email}"/>
                    </div>
                    <div class="form-item form-button">
                        <input type="submit" id="register-btn" value="Register"/> 
                    </div>                   
                </form>  
            </div>

        </div>
    </body>
</html>
