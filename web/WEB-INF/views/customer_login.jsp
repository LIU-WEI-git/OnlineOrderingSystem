<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 97718
  Date: 2019/11/24
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel='stylesheet' type='text/css' />
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel='stylesheet' type='text/css' />
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
                <jsp:include page="customer_sider.jsp"/>
                <div class="col-md-9">
                    <div class="breadcrumb">
                        <a href="<c:url value="/"/>">返回首页</a>
                    </div>
                    <div class="account_grid">
                        <div class="col-md-6 login-left">
                            <h3>新顾客</h3>
                            <p>通过在我们的商店中创建一个帐户，您将能够更快地完成结帐过程，存储多个发货地址，查看和跟踪您在您的帐户中的订单等等。</p>
                            <a class="acount-btn" href="<c:url value="/register"/>">注册</a>
                        </div>
                        <div class="col-md-6 login-right">
                            <h3>已注册顾客</h3>
                            <p>如果你已注册，请登录</p>
                            <form method="post">
                                <div>
                                    <span>账号<label>*</label></span>
                                    <label>
                                        <input type="text" name="customer_account" minlength="3" maxlength="20">
                                    </label>
                                </div>
                                <div>
                                    <span>密码<label>*</label></span>
                                    <label>
                                        <input type="password" name="customer_password">
                                    </label>
                                </div>
<%--                                TODO 挑战3 忘记密码--%>
<%--                                <a class="forgot" href="#">Forgot Your Password?</a>--%>
                                <input type="submit" value="登录">
                            </form>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="customer_footer.jsp"/>
<script>
    var info = '<%=request.getParameter("info")%>';
    if (info == 'failure') {
        alert("账号或密码错误!");
    }
</script>
</body>
</html>