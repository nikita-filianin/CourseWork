<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link type="text/css" rel="stylesheet" href="../../css/styles.css"/>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

</head>
<body>

<%@ include file="../jspf/nav.jspf" %>
<div class="wrapper ">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class=" ">
            <img src="/user1.png" id="icon" height="80" width="80" alt="User Icon"/>
        </div>

        <!-- Login Form -->
        <form action="signup" method="post">
            <input type="text" id="login" name="login" placeholder="login">
            <input type="password" name="password1" placeholder="password">
            <input type="password" name="password2" placeholder="repeat password">
            <div>
                <c:if test="${!empty errorMsg}">
                    <div style="color: red">
                        <c:out value="${errorMsg}"/>
                    </div>
                </c:if>
            </div>
            <div style="padding-top: 20px; padding-bottom: 0px">
                <input type="submit" value="Sign Up">
            </div>
        </form>
    </div>
</div>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</body>
</html>