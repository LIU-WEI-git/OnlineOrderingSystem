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
    <title>注册</title>
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
            <sf:form method="post" commandName="customerRegisterForm" name="register_form"
                     onsubmit="return checkPassword()">
                <div class="register-top-grid">
                    <h3>账户信息</h3>
                    <div>
                        <span>账号<label>*</label></span>
                        <sf:input path="customer_account" type="text"/>
                        <sf:errors path="customer_account" cssStyle="color: red"/>
                    </div>
                    <div>
                        <span>密码<label>*</label></span>
                        <sf:input path="customer_password" type="password" name="password" id="password"/>
                        <sf:errors path="customer_password" cssStyle="color: red"/>
                    </div>
                    <div class="clearfix"></div>
                    <div>
                        <span>确认密码<label>*</label></span>
                        <sf:input path="confirm_password" type="password" name="confirm_password"
                                  id="confirm_password"/>
                        <sf:errors path="confirm_password" cssStyle="color: red"/>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="register-bottom-grid">
                    <h3>个人信息</h3>
                    <div>
                        <span>用户名<label>*</label></span>
                        <sf:input path="customer_name"/>
                        <sf:errors path="customer_name" cssStyle="color: red"/>
                    </div>
                    <div>
                        <span>电子邮箱地址<label>*</label></span>
                        <sf:input path="customer_email" type="text"/>
                        <sf:errors path="customer_email" cssStyle="color: red"/>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="register-but">
                    <div class="clearfix"></div>
                    <input type="submit" value="注册"/>
                    <div class="clearfix"></div>
                </div>
            </sf:form>
        </div>
    </div>
</div>
<jsp:include page="customer_footer.jsp"/>
<script>
    var url= new URL(window.location.href);
    var info=url.searchParams.get("info");
    if (info === 'existed_account') {
        alert("账号已被注册过!");
    }
</script>
<script>
    function checkPassword() {
        var password = document.getElementById("password");
        var confirm_password = document.getElementById("confirm_password");
        if (password.value !== confirm_password.value) {
            alert("两次输入的密码不一致");
            confirm_password.focus();
            return false;
        }
        return true;
    }
</script>
</body>
</html>