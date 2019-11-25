<%--
  Created by IntelliJ IDEA.
  User: owner
  Date: 2019/11/24
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/res/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>Paper Dashboard by Creative Tim</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="${pageContext.request.contextPath}/res/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="${pageContext.request.contextPath}/res/css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    -->
    <link href="${pageContext.request.contextPath}/res/css/paper-dashboard.css" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="${pageContext.request.contextPath}/res/css/demo.css" rel="stylesheet" />


    <!--  Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/res/css/themify-icons.css" rel="stylesheet">

</head>
<body>

<div class="wrapper">
    <jsp:include page="admin_sider.jsp" flush="true"/>
    <div class="main-panel">
        <jsp:include page="admin_header.jsp" flush="true"/>
        <div class="content">
                <%request.getSession().getAttribute("admin");%>
            <form action="<c:url value="/admin/personma"/>">
                电话： <input type="text"  name="phone" value="${admin.getAdmin_phone()}" minlength="2" maxlength="15" required/><br/><br/>
                邮箱： <input type="email"  name="email" value="${admin.getAdmin_email()}"  required/><br/><br/>
                <input type="submit" value="修改" />
            </form>
            <a href="<c:url value="/admin/person"/>">返回</a>
        </div>
    </div></div>

</body>

<!--   Core JS Files   -->
<script src="${pageContext.request.contextPath}/res/js/jquery-1.10.2.js" type="text/javascript"></script>
            <script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js" type="text/javascript"></script>

            <!--  Checkbox, Radio & Switch Plugins -->
            <script src="${pageContext.request.contextPath}/res/js/bootstrap-checkbox-radio.js"></script>

            <!--  Charts Plugin -->
            <script src="${pageContext.request.contextPath}/res/js/chartist.min.js"></script>

            <!--  Notifications Plugin    -->
            <script src="${pageContext.request.contextPath}/res/js/bootstrap-notify.js"></script>

            <!--  Google Maps Plugin    -->
            <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

            <!-- Paper Dashboard Core javascript and methods for Demo purpose -->
            <script src="${pageContext.request.contextPath}/res/js/paper-dashboard.js"></script>

            <!-- Paper Dashboard DEMO methods, don't include it in your project! -->
            <script src="${pageContext.request.contextPath}/res/js/demo.js"></script>


</html>
