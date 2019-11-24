<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 拜尔
  Date: 2019/11/21
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel='stylesheet' type='text/css' />
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel='stylesheet' type='text/css' />
    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!----webfonts---->
    <link href='http://fonts.googleapis.com/css?family=Exo+2:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
</head>
<body>
<jsp:include page="customer_header.jsp"/>
<div class="main">
    <div class="container">
        <div class="Product_top">
            <div class="row content">
                <div class="col-md-9">
                    <div id="sorter" class="tie2">
                        <div class="tie2-indent">
                            <form name="sorter_form" action="#" method="get"><input type="hidden" name="main_page" value="products_all"><label for="disp-order-sorter">Sort by: </label>
                                <select name="disp_order" onchange="this.form.submit();" id="disp-order-sorter">
                                    <option value="1" selected="selected">Product Name</option>
                                    <option value="2">Product Name - desc</option>
                                    <option value="3">Price - low to high</option>
                                    <option value="4">Price - high to low</option>
                                    <option value="5">Model</option>
                                    <option value="6">Date Added - New to Old</option>
                                    <option value="7">Date Added - Old to New</option>
                                </select>
                            </form>
                        </div>
                    </div>
                    <div class="pagenation">
                        <div id="allProductsListingTopNumber" class="navSplitPagesResult back"><span>Displaying</span> 1 to 4 (of <span>20</span> products)</div>
                        <div id="allProductsListingTopLinks" class="navSplitPagesLinks forward"> &nbsp;<strong class="current">1</strong>&nbsp;&nbsp;
                            <a href="" title=" Page 2 ">2</a>&nbsp;&nbsp;
                            <a href="" title=" Page 3 ">3</a>&nbsp;&nbsp;
                            <a href="" title=" Page 4 ">4</a>&nbsp;&nbsp;
                            <a href="" title=" Page 5 ">5</a>&nbsp;&nbsp;
                            <a href="" title=" Next Page ">Next&nbsp;&gt;&gt;</a>&nbsp;
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                    <c:forEach items="${orders}" var="order">
                        <ul class="product_img">
                            <li class="product_left"><img src="images/pic8.jpg" class="img-responsive" alt=""/>
                                <p>In Stock:999</p>
                            </li>
                            <li class="product_right">
                                <h3>下单时间：${order.create_time}</h3>
                                <h4>订单状态：
                                    <c:choose>
                                        <c:when test="${order.order_state ==0}">商家暂未接单</c:when>
                                        <c:when test="${order.order_state ==1}">商家已接单</c:when>
                                        <c:when test="${order.order_state ==2}">订单已结束</c:when>
                                    </c:choose>
                                </h4>
                                <span class="model"><strong>订单价格: </strong>${order.order_price}</span><br>
                                <span class="model"><strong>折扣: ${order.discount}</strong></span>
                                <div class="product_price">配送状态:
                                    <c:choose>
                                        <c:when test="${order.delivery_state ==0}">
                                            <span class="actual">未配送</span>
                                        </c:when>
                                        <c:when test="${order.delivery_state==1}">配送中</c:when>
                                        <c:when test="${order.delivery_state==2}">已送达</c:when>
                                    </c:choose>
                                </div>
                                <div class="but1">
                                    <a href="<c:url value="/address_info?order_id=${order.order_id}" />">订单地址详情</a>
                                </div>
                            </li>
                            <div class="clearfix"> </div>
                            <h5 class="detail">备注:</h5>
                            <p class="detail_desc">${order.remark}<a href="<c:url value="/order_item?order_id=${order.order_id}"/>">订单详情</a></p>
                        </ul>
                    </c:forEach>

                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="customer_footer.jsp"/>
</body>
</html>

