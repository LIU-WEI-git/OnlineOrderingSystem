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
            <%--            <sf:form method="post" commandName="customer">--%>
            <%--                <div class="register-top-grid">--%>
            <%--                    <h3>账户信息</h3>--%>
            <%--                    <div>--%>
            <%--                        <span>账号<label>*</label></span>--%>
            <%--                        <sf:input path="customer_account" type="text"/>--%>
            <%--                        <sf:errors path="customer_account" cssClass="has-error"/>--%>
            <%--                    </div>--%>
            <%--                    <div>--%>
            <%--                        <span>密码<label>*</label></span>--%>
            <%--                        <sf:input path="customer_password" type="password"/>--%>
            <%--                        <sf:errors path="customer_password" cssClass="has-error"/>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--                <div class="clearfix"></div>--%>
            <%--                <div class="register-bottom-grid">--%>
            <%--                    <h3>个人信息</h3>--%>
            <%--                    <div>--%>
            <%--                        <span>用户名<label>*</label></span>--%>
            <%--                        <sf:input path="customer_name"/>--%>
            <%--                        <sf:errors path="customer_name" cssClass="has-error"/>--%>
            <%--                    </div>--%>
            <%--                    <div>--%>
            <%--                        <span>电子邮箱地址<label>*</label></span>--%>
            <%--                        <sf:input path="customer_email" type="email"/>--%>
            <%--                        <sf:errors path="customer_email" cssClass="has-error"/>--%>
            <%--                    </div>--%>
            <%--                    <div class="clearfix"></div>--%>
            <%--                </div>--%>
            <%--                <div class="register-but">--%>
            <%--                    <div class="clearfix"></div>--%>
            <%--                    <input type="submit" value="注册"/>--%>
            <%--                    <div class="clearfix"></div>--%>
            <%--                </div>--%>
            <%--            </sf:form>--%>
            <form method="post">
                <div class="register-top-grid">
                    <h3>账户信息</h3>
                    <div>
                        <span>账号<label>*</label></span>
                        <label>
                            <input type="text" name="customer_account" minlength="3" maxlength="20"/>
                        </label>
                    </div>
                    <div>
                        <span>密码<label>*</label></span>
                        <label>
                            <input type="password" name="customer_password"/>
                        </label>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="register-bottom-grid">
                    <h3>个人信息</h3>
                    <div>
                        <span>用户名<label>*</label></span>
                        <label>
                            <input type="text" name="customer_name" minlength="3" maxlength="20"/>
                        </label>
                    </div>
                    <div>
                        <span>电子邮箱地址<label>*</label></span>
                        <label>
                            <input type="email" name="customer_email"/>
                        </label>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="register-but">
                    <div class="clearfix"></div>
                    <input type="submit" value="注册"/>
                    <div class="clearfix"></div>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="customer_footer.jsp"/>
<script>
    var info = '<%=request.getParameter("info")%>';
    if (info == 'existed_account') {
        alert("账号已被注册过!");
    }
</script>
</body>
</html>