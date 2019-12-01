<%--
  Created by IntelliJ IDEA.
  User: owner
  Date: 2019/11/24
  Time: 10:22
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
            <form class="input-group col-md-12" style="margin: 10px;position: relative" action="<c:url value="/admin/searchdish"/>">
                    <span class="input-group-btn">
                        <select  name="signal" class="btn btn-info btn-search">
                            <option>all</option>
<c:forEach items="${categories}" var="category" varStatus="li">
    <option>${category.getCategory_name()}</option>
</c:forEach>
                            <%--<option>pizza</option>
                            <option>bakedrice</option>
                            <option>bakednoodles</option>
                            <option>snack</option>
                            <option>drink</option>--%>
                        </select>
                    </span>
                <input type="text" class="form-control" name="message"
                       placeholder="请输入要查询的菜品关键字">
                <span class="input-group-btn">
                        <button type="submit" class="btn btn-info btn-search">搜索</button>
                    </span>
        </form>

<div>
    <a href="<c:url value="/admin/adddish"  />" style="font-size:30px" >添加菜品</a>
</div>
            <div class="content table-responsive table-full-width">
            <table class="table table-striped">
                <thead>
                <th style="text-align: center">菜品编号</th>
                <th style="text-align: center">菜品名称</th>
                <th style="text-align: center">价格</th>
                <th style="text-align: center">描述</th>
                <th style="text-align: center">图片</th>
                <th style="text-align: center">删除菜品</th>
                <th style="text-align: center">修改菜品</th>
                </thead>
                <tbody>
                <c:forEach items="${list.items}" var="dishCategorySupport" varStatus="li">
                <tr bgcolor="${status.index%2 == 0?'#D0D8E8':'#E9EDF4'}">
                    <td align="center">${dishCategorySupport.dish.getDish_id()}</td>
                    <td align="center">${dishCategorySupport.dish.getDish_name()}</td>
                    <td align="center">${dishCategorySupport.dish.getPrice()}</td>
                    <td align="center">${dishCategorySupport.dish.getDescription()}</td>
                    <td align="center"><img src="${pageContext.request.contextPath}/resources/${dishCategorySupport.dish.getPicture_url()}"></td>
                    <td align="center">
                    <a href="<c:url value="/admin/deletedish?dish_id=${dishCategorySupport.dish.getDish_id()}" />">删除</a>
                    </td>
                    <td align="center">
                        <a href="<c:url value="/admin/changedish?dish_id=${dishCategorySupport.dish.getDish_id()}" />">修改</a>
                    </td>
                </tr>
                </c:forEach></tbody></table></div>
            <c:if test="${list.previousPage}">
                <a href="<c:url value="/admin/dish?pageNo=${list.getCurrentPageNo()-1}" />" >上一页</a>
            </c:if>
            <c:if test="${list.nextPage}">
                <a href="<c:url value="/admin/dish?pageNo=${list.getCurrentPageNo()+1}" />" >下一页</a>
            </c:if>
             <%--   <table style="width:100%;border:1px white solid">
                    <tr bgcolor="#4F81BD"style="color: #fff;">
                        <th style="text-align: center">dish_id</th>
                        <th style="text-align: center">dish_name</th>
                        <th style="text-align: center">price</th>
                        <th style="text-align: center">admin_email</th>
                        <th style="text-align: center">description</th>
                        <th style="text-align: center">picture</th>
                        <th style="text-align: center">修改菜品</th>
                    </tr>
                    <c:forEach items="${list}" var="dish" varStatus="li">
                        <tr bgcolor="${status.index%2 == 0?'#D0D8E8':'#E9EDF4'}">
                            <td align="center">${dish.getDish_id()}</td>
                            <td align="center">${admin.getAdmin_name()}</td>
                            <td align="center">${admin.getAdmin_register_date()}</td>
                            <td align="center">${admin.getAdmin_email()}</td>
                            <td align="center">${admin.getAdmin_phone()}</td>
                            <td align="center"><c:if test ="${admin.isDelete_tag()==0}"> <c:out value="正常" /></c:if>
                                <c:if test ="${admin.isDelete_tag()==1}"> <c:out value="已被删除" /></c:if>
                            </td>
                            <td align="center">
                                <a href="<c:url value="/admin/deleteadmin?admin_username=${admin.getAdmin_name()}" />">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>--%>
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
