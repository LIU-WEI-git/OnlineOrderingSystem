<%--
  Created by IntelliJ IDEA.
  User: 97718
  Date: 2019/11/17
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
Hello World!
<br>
${adminCount}
<a href="<c:url value="/admin/alogin" />">管理员登陆</a>
</body>
</html>
