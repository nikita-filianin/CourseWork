<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
    <link type="text/css" rel="stylesheet" href="../../css/styles.css"/>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

</head>
<body>

<%@ include file="../jspf/nav.jspf" %>
<div class="wrapper ">
    <div id="formContent">
        <!-- Tabs Titles -->
        <!-- Icon -->
        <div>
            <img src="/user1.png" id="icon" height="80" width="120" alt="User Icon"/>
        </div>

        <!-- Log In Form -->
        <form action="./login" method="post">
            <input type="text" name="login" placeholder="login">
            <input type="password" name="password" placeholder="password">
            <c:if test="${!empty errorMsg}">
                <div style="color: red">
                    <c:out value="${errorMsg}"/>
                </div>
            </c:if>
            <c:if test="${!empty success}">
                <div style="color: #56baed">
                    <c:out value="${success}"/>
                </div>
            </c:if>

            <div style="padding-top: 20px; padding-bottom: 0px">
                <input type="submit" value="Log In">
            </div>
        </form>

        <div style="padding-bottom: 30px">
            <a href="signup">Sign Up</a>
        </div>
    </div>
</div>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</body>
</html>