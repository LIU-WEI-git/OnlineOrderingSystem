<%--
  Created by IntelliJ IDEA.
  User: 97718
  Date: 2019/11/24
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="SimpleTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel='stylesheet' type='text/css'/>
    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!----webfonts---->
    <link href='http://fonts.googleapis.com/css?family=Exo+2:100,200,300,400,500,600,700,800,900' rel='stylesheet'
          type='text/css'>
</head>
<body>
<jsp:include page="customer_header.jsp"/>
<div class="main">
    <div class="container">
        <div class="Product_top">
            <div class="row content">
                <div class="col-md-9">
<%--                    TODO 选择配送地址--%>
                    地址<br>
                    配送时间：<st:deliveryTime/><br>
                    <table border="1">
                        <thead>
                        <tr>
                            <th style="width:150px" align="center">菜品名</th>
                            <th style="width:150px" align="center">单价</th>
                            <th style="width:150px" align="center">数量</th>
                            <th style="width:150px" align="center">小计</th>
                        </tr>
                        </thead>
                        <tbody>
                        <jsp:useBean id="shoppingCart" scope="session" type="ordering.utils.ShoppingCart"/>
                        <c:forEach items="${shoppingCart.shoppingCartItemList}" var="shoppingCartItem" varStatus="li">
                            <tr>
                                <td width="150" align="center">${shoppingCartItem.dish.dish_name}</td>
                                <td width="150" align="center">${shoppingCartItem.dish.price}</td>
                                <td width="150" align="center">${shoppingCartItem.amount}</td>
                                <td width="150"
                                    align="center">${shoppingCartItem.dish.price * shoppingCartItem.amount}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    折扣：￥-<st:discount totalPrice="${shoppingCart.totalPrice}"/>
                    <div>
                        总价：￥<st:orderPrice totalPrice="${shoppingCart.totalPrice}"/>
                        <div class="register-but">
                            <%--                            <div class="clearfix"></div>--%>
                            <input type="submit" value="确认下单" href=""/>
                            <%--                            <div class="clearfix"></div>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="customer_footer.jsp"/>
</body>
</html>
