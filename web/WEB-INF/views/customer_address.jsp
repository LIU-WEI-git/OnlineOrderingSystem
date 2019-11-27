<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 拜尔
  Date: 2019/11/24
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的地址信息</title>
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
                    <ul class="product_img">
                        <c:forEach items="${addresses}" var="address">
                            <li>
                                <h4>地址：${address.info}</h4>
                                <span>${address.contact}</span>&nbsp;&nbsp;&nbsp;<span>${address.phone}</span>
                                <a style="width: 10%;margin-right: 5%;float: right;color: #E8360F" href="<c:url value="/myAddress/delete_address?address_id=${address.address_id}" /> ">删除</a>
                                <a style="width: 10%;margin-right: 5%;float: right;color: #3071a9" href="<c:url value="/myAddress/edit_address?address_id=${address.address_id}" />">编辑</a>
                            </li>
                            <hr>
                        </c:forEach>
                    </ul>
                    <a href="<c:url value="/myAddress/add_address"/> " style="color: #3071a9">新增地址</a>&nbsp;&nbsp;&nbsp;
                    <a href="<c:url value="/account"/> " style="color: #3071a9">返回我的账户</a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="customer_footer.jsp"/>
</body>
</html>
