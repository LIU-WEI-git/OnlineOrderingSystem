<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 97718
  Date: 2019/11/23
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
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
                <jsp:include page="customer_sider.jsp"/>
                <div class="col-md-9">
                    <table border="1">
                        <thead>
                        <tr>
                            <th style="width:150px" align="center">菜品名</th>
                            <th style="width:150px" align="center">单价</th>
                            <th style="width:150px" align="center">数量</th>
                            <th style="width:150px" align="center">小计</th>
                            <th style="width:200px" align="center">操作</th>
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
                                <td width="200" align="center">
                                    <a href="<c:url value="/shoppingCart/addDish?dish_id=${shoppingCartItem.dish.dish_id}"/>">
                                        <input type="button" value="加一"/>
                                    </a>
                                    <c:choose>
                                        <c:when test="${shoppingCartItem.amount eq 1}">
                                            <input type="button" value="减一" disabled="disabled"/>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="<c:url value="/shoppingCart/minusDish?dish_id=${shoppingCartItem.dish.dish_id}"/>">
                                                <input type="button" value="减一"/>
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                    <a href="<c:url value="/shoppingCart/deleteDish?dish_id=${shoppingCartItem.dish.dish_id}"/>">
                                        <input type="button" value="删除"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div>
                        总价：￥${shoppingCart.totalPrice}
                        <div class="register-but">
                            <a href="<c:url value="/createOrder"/>">
                                <input type="submit" value="结算"/>
                            </a>
                        </div>
                    </div>
                    <%--                    <ul class="product_img">--%>
                    <%--                        <li class="product_left"><img src="${pageContext.request.contextPath}/resources/" class="img-responsive" alt=""/>--%>
                    <%--                            <p>In Stock:999</p>--%>
                    <%--                        </li>--%>
                    <%--                        <li class="product_right">--%>
                    <%--                            <h3>Date Added: Wed 25 June, 2014</h3>--%>
                    <%--                            <h4><a href="#">nostrud exerci tation ullamcorper suscipit </a></h4>--%>
                    <%--                            <span class="model"><strong>Model : </strong>Model6</span><br>--%>
                    <%--                            <span class="model"><strong>Manufacturer : </strong>Example1</span>--%>
                    <%--                            <div class="product_price">Price:--%>
                    <%--                                <span class="actual">$12.00</span>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="but1">--%>
                    <%--                                <a href="#">Buy Now</a>--%>
                    <%--                            </div>--%>
                    <%--                        </li>--%>
                    <%--                        <div class="clearfix"> </div>--%>
                    <%--                        <h5 class="detail">Details:</h5>--%>
                    <%--                        <p class="detail_desc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie<a href="#">...More Info</a></p>--%>
                    <%--                    </ul>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="customer_footer.jsp"/>
</body>
</html>
