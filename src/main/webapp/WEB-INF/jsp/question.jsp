<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

</head>
<body>
<%@ include file="../jspf/nav.jspf" %>
<div class="container">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-lg-12">
            <div id="postlist">
                <div>
                    <div class="text-center">
                        <div class="row">
                            <div class="col-sm-9">
                                <c:if test="${!empty user}">
                                    <form action="deleteQuestion" method="post"
                                          style="display: flex; align-content: flex-start">
                                        <input type="hidden" name="id" value="${question.id}">
                                        <input type="submit" style="color: red" value="Delete question">
                                    </form>

                                    <form action="editQuestion" method="post"
                                          style="display: flex; align-content: flex-start">
                                        <input type="hidden" name="id" value="${question.id}">
                                        <input type="submit" style="color: cornflowerblue" value="Edit question">
                                    </form>
                                </c:if>

                            </div>
                        </div>
                    </div>


                    <div>
                        <c:out value="${question.text}"/>
                    </div>
                </div>
                <hr>
            </div>
        </div>
    </div>
</div>
<div style="display:flex; justify-content:center">
    <h4>Answers</h4>
</div>
<div class="container mt-5">
    <div class="d-flex justify-content-center row">
        <div class="col-md-8">
            <div class="d-flex flex-column answer-section">


                <c:forEach var="answer" items="${requestScope.answers}">
                    <div class="bg-white p-2">
                        <div class="d-flex flex-row user-info">
                            <div class="d-flex flex-column justify-content-start ml-2">
                                <span class="d-block font-weight-bold name"><c:out
                                        value="${answer.user.login}"/></span>
                            </div>
                        </div>
                        <div class="mt-2">
                            <p class="answer-text"><c:out value="${answer.text}"/></p>
                        </div>
                    </div>
                </c:forEach>

                <c:if test="${!empty sessionScope.user}">
                    <div class="bg-light p-2">
                        <form action="answer" method="post">
                            <div class="d-flex flex-row align-items-start">
                                <textarea name="text" class="form-control ml-1 shadow-none textarea"></textarea>
                            </div>
                            <input type="hidden" name="id" value="${question.id}">
                            <div class="mt-2 text-right">
                                <button class="btn btn-primary btn-sm shadow-none" type="submit">Reply</button>
                            </div>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>