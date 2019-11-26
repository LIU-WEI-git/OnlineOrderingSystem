<%--
  Created by IntelliJ IDEA.
  User: 97718
  Date: 2019/11/25
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="SimpleTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付成功</title>
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
                    <h2>完成支付</h2><br/><br/>
                    <h5>您的餐品预计30分钟后送达</h5><br/><br/>
                    <a href="<c:url value="/order"/>" style="color: #3071a9">查看我的订单</a><br/><br/>
                    <a href="<c:url value="/"/>" style="color: #3071a9">返回首页</a><br/><br/>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="customer_footer.jsp"/>
</body>
</html>