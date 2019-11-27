<%--
  Created by IntelliJ IDEA.
  User: 97718
  Date: 2019/11/20
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑地址</title>
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
                    <h3>地址信息</h3>
                    <div>
                        <span>地址<label>*</label></span>
                        <label>
                            <input type="text" name="address_info" value="${address.info}" minlength="3" maxlength="20" required/>
                        </label>
                    </div>
<%--                    <div hidden>--%>
<%--                        <span >address_id<label>*</label></span>--%>
<%--                        <label>--%>
<%--                            <input type="text" name="address_id" value="${address.address_id}"/>--%>
<%--                        </label>--%>
<%--                    </div>--%>
                </div>
                <div class="clearfix"></div>
                <div class="register-bottom-grid">
                    <h3>联系人信息</h3>
                    <div>
                        <span>联系人<label>*</label></span>
                        <label>
                            <input type="text" name="contact" value="${address.contact}" minlength="3" maxlength="20" required/>
                        </label>
                    </div>
                    <div>
                        <span>电话<label>*</label></span>
                        <label>
                            <input type="tel" name="phone" value="${address.phone}"/>
                        </label>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="register-but">
                    <div hidden>
                        <span>customer_account<label>*</label></span>
                        <label>
                            <input type="text" name="customer_account" value="${address.customer_account}" minlength="3" maxlength="20" required/>
                        </label>
                    </div>
                    <div class="clearfix"></div>
                    <input type="submit" value="确认修改"/>
                    <a href="<c:url value="/myAddress"/>">
                        <input type="button" value="返回">
                    </a>
                    <div class="clearfix"></div>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="customer_footer.jsp"/>
</body>
</html>