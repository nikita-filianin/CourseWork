<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit question</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
</head>
<body>
<%@ include file="../jspf/nav.jspf" %>
<form style="padding: 2%" action="editQuestion" method="post" >
    <input type="hidden" name="id" value="<c:out value="${question.id}"/>">
    <div class="form-group">
        <label >Title</label>
        <input type="text" name="title" class="form-control" placeholder="Some title" value="<c:out value="${question.title}"/>">
    </div>
    <div class="form-group">
        <label>Text</label>
        <textarea name="text" class="form-control" rows="6" placeholder="Some text"><c:out value="${question.text}"/></textarea>
    </div>
    <div class="form-group">
        <input type="submit" value="Edit question">
    </div>
</form>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>