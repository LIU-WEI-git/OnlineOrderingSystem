<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 97718
  Date: 2019/11/19
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <div class="container">
        <div class="header_top">
            <ul class="phone">
                <li class="phone_left"><i class="mobile"> </i><span>1-200-346-2986</span></li>
                <li class="phone_right">订单超过50元免配送费</li>
                <div class="clearfix"></div>
            </ul>
            <ul class="social">
                <li><a href=""> <i class="tw"> </i> </a></li>
                <li><a href=""><i class="fb"> </i> </a></li>
                <li><a href=""><i class="rss"> </i> </a></li>
                <li><a href=""><i class="msg"> </i> </a></li>
                <div class="clearfix"> </div>
            </ul>
            <ul class="account">
                <c:choose>
                    <c:when test="${not empty sessionScope.customer}">
                        <%--@elvariable id="customer" type="ordering.domain.Customer"--%>
<%--                        TODO 修改链接--%>
                        <li><a href="<c:url value="/"/>">${customer.customer_name}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="<c:url value="/login"/>">我的账户</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <ul class="shopping_cart">
                <a href="<c:url value="/shoppingCart"/>"><li class="shop_left"><i class="cart"> </i><span>购物车</span></li></a>
                <c:if test="${not empty sessionScope.shoppingCart}">
                    <jsp:useBean id="shoppingCart" scope="session" type="ordering.utils.ShoppingCart"/>
                    <a href="<c:url value="/shoppingCart"/>"><li class="shop_right"><span>￥${shoppingCart.totalPrice}</span></li></a>
                </c:if>
                <div class="clearfix"> </div>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="header_bottom">
            <div class="header_nav">
                <div class="logo">
                    <a href="index.jsp"><img src="${pageContext.request.contextPath}/resources/images/logo.png" alt=""/><br></a>
                </div>
                <nav class="navbar navbar-default menu" role="navigation"><h3 class="nav_right"><a href="index.jsp"><img src="${pageContext.request.contextPath}/resources/images/logo.png" class="img-responsive" alt=""/></a></h3>
                    <div class="container-fluid">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                        </div>
                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav menu1">
                                <!-- TODO 修改链接 -->
                                <li><a href="<c:url value="/"/>">首页</a></li>
                                <li><a href="club.jsp">折扣活动</a></li>
                                <li><a href="fruits.jsp">水果蔬菜</a></li>
                                <li><a href="club.jsp">粉丝俱乐部</a></li>
                                <li><a href="club.jsp">甜品饮品</a></li>
                                <li><a href="contact.jsp">联系我们</a></li>
                            </ul>
                            <ul class="login">
                                <c:choose>
                                    <c:when test="${not empty sessionScope.customer}">
                                        <a href="<c:url value="/logout"/>">
                                            <li class="login_top"><i class="sign">
                                            </i><span>注销</span></li>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="<c:url value="/login"/>">
                                            <li class="login_top"><i class="sign">
                                            </i><span>登录</span></li>
                                        </a>
                                        <a href="<c:url value="/register" />">
                                            <li class="login_bottom"><i class="register"> </i><span>注册</span></li>
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                            <div class="clearfix"></div>
                        </div><!-- /.navbar-collapse -->
                    </div><!-- /.container-fluid -->
                </nav>
                <div class="clearfix"></div>
            </div>
            <div class="search">
                <label>
                    <input type="text" class="text" value="美味搜索" onfocus="this.value = '';" onblur="if (this.value === '') {this.value = '美味搜索';}">
                </label>
                <input type="submit" value="搜索">
            </div>
        </div>
    </div>
</div>
