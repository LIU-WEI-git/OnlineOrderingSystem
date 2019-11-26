<jsp:useBean id="customer" scope="session" type="ordering.domain.Customer"/>
<%--
  Created by IntelliJ IDEA.
  User: 97718
  Date: 2019/11/20
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<html>
<head>
    <title>个人信息修改</title>
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
            <form method="post">
                <div class="register-top-grid">
                    <h3>个人信息修改</h3>
                    <div>
                        <span>用户名</span>
                        <label>
                            <input type="text" name="new_customer_name" minlength="3" maxlength="20"
                                   value="${customer.customer_name}"/>
                        </label>
                    </div>
                    <div>
                        <span>电子邮箱地址</span>
                        <label>
                            <input type="email" name="new_customer_email" value="${customer.customer_email}"/>
                        </label>
                    </div>
                    <div class="clearfix"></div>
                    <div class="register-but">
                        <div class="clearfix"></div>
                        <input type="submit" value="确认修改"/>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </form>
<%--          TODO  没有返回按钮--%>
        </div>
    </div>
</div>
<jsp:include page="customer_footer.jsp"/>
</body>
</html>
