<%-- 
    Document   : header
    Created on : Jan 12, 2021, 8:19:06 PM
    Author     : Vu Ngoc Thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <body>
        <header class="header-container">
            <ul class="header">
                <li class="header-item"><a href="../J3LP0001/home">Home</a></li>
                <li class="header-item"><a href="../J3LP0001/take-quiz">Take Quiz</a></li>
                <li class="header-item"><a href="../J3LP0001/make-quiz">Make Quiz</a></li>
                <li class="header-item"><a href="../J3LP0001/manage-quiz">Manage Quiz</a></li>
            <c:if test="${user!=Empty}">
                <li class="header-item"><a href="../J3LP0001/logout">Log out</a></li>
            </c:if>
            </ul>
        </header>
    </body>
</html>
