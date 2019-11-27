<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 拜尔
  Date: 2019/11/23
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Address</title>
</head>
<body>
<div style="width:60%;margin: auto;">
    <span>订餐人姓名:${address.customer_name}</span><br>
    <span>订餐人昵称:${address.contact}</span><br>
    <span>电话号:${address.phone}</span><br>
    <span>地址信息:${address.info}</span><br>
    <a href="<c:url value="/order"/> ">返回</a>
</div>
</body>
</html>
