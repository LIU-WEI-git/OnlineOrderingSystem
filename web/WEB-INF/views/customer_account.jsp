<jsp:useBean id="customer" scope="session" type="ordering.domain.Customer"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 97718
  Date: 2019/11/26
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人账号管理</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel='stylesheet' type='text/css'/>
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
                    <div class="breadcrumb">
                        <a href="<c:url value="/"/>">返回首页</a>
                    </div>
                    <div class="account_grid">
                        <div class="col-md-6 login-left">
                            <h3>个人信息</h3>
                            <p>账号：${customer.customer_account}</p>
                            <p>用户名：${customer.customer_name}</p>
                            <p>邮箱：${customer.customer_email}</p>
                            <P>注册时间：${customer.customer_register_time}</P>
                            <a class="acount-btn" href="<c:url value="/account/resetAccount"/>">修改个人信息</a>
                            <a class="acount-btn" href="<c:url value="/account/resetPassword"/>">修改密码</a>
                        </div>
                        <div class="col-md-6 login-right">
                            <h3>个人订单信息</h3>
                            <a class="acount-btn" href="<c:url value="/order"/>">查看订单</a><br/><br/><br/>
                            <a class="acount-btn" href="<c:url value="/myAddress"/>">查看个人地址信息</a><br/>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="customer_footer.jsp"/>
<script>
    var url = new URL(window.location.href);
    var info = url.searchParams.get("info");
    if (info === 'reset_account_success') {
        alert("成功修改账户信息!");
    }
    if (info === 'reset_password_success') {
        alert("成功修改密码!");
    }
</script>
</body>
</html>