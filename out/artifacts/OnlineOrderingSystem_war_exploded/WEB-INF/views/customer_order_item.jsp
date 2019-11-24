<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 拜尔
  Date: 2019/11/23
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order items</title>
</head>
<body>
<div style="width: 60%;margin:auto;">
    <c:forEach items="${orderitems}" var="item">
        <div style="margin-top:20px;">
            <div style="width: 40%;height: auto;">
                <img src="${pageContext.request.contextPath}/resources/${item.picture_url}"/>
            </div>
            <div style="width: 60%;font-size:1.5em">
                <span style="margin-top: 5px;">菜品名称：${item.dish_name}</span><br>
                <span style="margin-top: 5px;">菜品单价：${item.price}</span><br>
                <span style="margin-top: 5px;">菜品数量：${item.amount}</span><br>
                <span style="margin-top: 5px;">总计：${item.total_price}</span><br>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
