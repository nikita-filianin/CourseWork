<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questions</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
</head>
<body>
<%@ include file="../jspf/nav.jspf" %>
<div class="bg-contact2" style="background-image: url('../../01.jpg');">


    <nav class="navbar justify-content-center navbar-light bg-light">

        <form class="float-center form-inline" method="post">
            <input name="searchText" class="form-control mr-md-2" type="search" placeholder="type here..."
                   aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>

    </nav>

    <div class="container">
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-lg-12">
                <div id="postlist">
                    <c:forEach var="question" items="${requestScope.questions}">
                        <div>
                            <div class="text-center">
                                <div class="row">
                                    <div class="col-sm-9">
                                        <a href="question?id=${question.id}"><h3 class="float-left"><c:out
                                                value="${question.text}"/></h3></a>
                                    </div>
                                </div>
                            </div>


                            <div>
                                <c:out value="${question.text}"/>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>

                </div>
            </div>
        </div>
    </div>
</div>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</body>
</html>