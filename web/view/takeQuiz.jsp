<%-- 
    Document   : takeQuiz
    Created on : Jan 13, 2021, 7:23:47 PM
    Author     : Vu Ngoc Thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz</title>
        <link href="css/take-quiz.css" rel="stylesheet" type="text/css"/>        
        <link href="css/base.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <c:set var="user" scope="session" value="${sessionScope.user}"/>
            <%@include file="header.jsp"%>
            <c:if test="${user!=Empty}">
                <div class="content-container">
                    <h3 class="label">Welcome </h3> 
                    <span class="infor">${sessionScope.user.getUserName()}</span> 
                    <c:choose>  
                        <c:when test="${questions==Empty}">
                            <h3 id="number-ques" class="label">Enter number of Questions</h3><br>
                            <form id="number-form" class="form" method="get" action="../J3LP0001/take-quiz">
                                <div class="form-item">
                                    <input min="1" max="${requestScope.maxQuiz}" type="number" id="number-input" name="number"/>
                                </div>
                                <div class="form-item form-button">
                                    <input class="button" id="number-button" type="submit" value="Start"/>
                                </div>                        
                            </form>
                        </c:when>
                        <c:otherwise>
                            <div id="timer">
                                <h3 class="label">Time remaining</h3>
                                <span id="time"></span>
                            </div>
                            <div id="answer-container">                    
                                <c:forEach items="${questions}" var="q">
                                    <div class="ques" id="${q.getId()}">
                                        <h3 class="ques-title">${q.getContent()}</h3> 
                                        <c:set var="answers" scope="request" value="${q.getAnswers()}"/>
                                        <c:forEach items="${answers}" var="a">
                                            <input name="${q.getId()}" class="op"
                                                   id="${a.getId()}" 
                                                   type="checkbox"
                                                   value="${a.getId()}"/>
                                            <label class="answer-label login-label text-display" for="${a.getId()}">${a.getContent()}</label>
                                            <br> 
                                        </c:forEach>                           
                                    </div>
                                </c:forEach>    
                                <button class="button" id="nextBtn">Next</button>
                            </div>
                            <form id="checkTimeForm" method="post" action="../J3LP0001/take-quiz">
                                <input id="result" name="result" type="hidden"/>                            
                            </form>
                        </c:otherwise>    
                    </c:choose>
                </div>
            </c:if>
        </div>
        <script src="js/takeQuiz.js" type="text/javascript"></script>
    </body>
</html>
