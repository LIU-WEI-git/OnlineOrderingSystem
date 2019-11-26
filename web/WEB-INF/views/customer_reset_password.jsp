<jsp:useBean id="customer" scope="session" type="ordering.domain.Customer"/>
<%--
  Created by IntelliJ IDEA.
  User: 97718
  Date: 2019/11/26
  Time: 20:00
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
                    <h3>账户信息修改</h3>
                    <div>
                        <span>原密码</span>
                        <label>
                            <input type="password" name="old_password"/>
                        </label>
                    </div>
                    <div>
                        <span>新密码</span>
                        <label>
                            <input type="password" name="new_password"/>
                        </label>
                    </div>
                    <div>
                        <span>密码确认</span>
                        <label>
                            <input type="password" name="confirm_password"/>
                        </label>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="register-but">
                    <div class="clearfix"></div>
                    <input type="submit" value="确认修改"/>
                    <div class="clearfix"></div>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="customer_footer.jsp"/>
<script>
    var url = new URL(window.location.href);
    var info = url.searchParams.get("info");
    if (info === 'wrong_old_password') {
        alert("原密码错误!");
    }
    if (info === 'wrong_confirm_password') {
        alert("确认密码与新密码不一致!");
    }
</script>
</body>
</html>
