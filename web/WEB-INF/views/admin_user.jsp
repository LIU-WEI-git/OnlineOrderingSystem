<%--
  Created by IntelliJ IDEA.
  User: owner
  Date: 2019/11/22
  Time: 18:53
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

        <%request.getAttribute("admin");%>
<div class="content">
    <div class="container-fluid">
        <div class="row">
                <div class="card">
                    <div class="header">
                        <h4 class="title">管理员管理</h4>
                    </div>
                    <div class="content">

                            <table style="width:100%;border:1px white solid">
                                <tr bgcolor="#4F81BD"style="color: #fff;">
                                    <th style="text-align: center">管理员账号</th>
                                    <th style="text-align: center">管理员名字</th>
                                    <th style="text-align: center">管理员注册时间</th>
                                    <th style="text-align: center">邮箱</th>
                                    <th style="text-align: center">电话</th>
                                    <th style="text-align: center">管理员权限状态</th>
                                    <th style="text-align: center">修改管理员密码</th>
                                    <th style="text-align: center">删除管理员</th>
                                </tr>
                                <c:forEach items="${alist}" var="admin_1" varStatus="li">
                                    <tr bgcolor="${status.index%2 == 0?'#D0D8E8':'#E9EDF4'}">
                                        <td align="center">${admin_1.getAdmin_account()}</td>
                                        <td align="center">${admin_1.getAdmin_name()}</td>
                                        <td align="center">${admin_1.getAdmin_register_date()}</td>
                                        <td align="center">${admin_1.getAdmin_email()}</td>
                                        <td align="center">${admin_1.getAdmin_phone()}</td>
                                        <td align="center"><c:if test ="${admin_1.isDelete_tag()==0}"> <c:out value="正常" /></c:if>
                                            <c:if test ="${admin_1.isDelete_tag()==1}"> <c:out value="已被删除" /></c:if>
                                        </td>
                                        <td align="center">
                                            <a href="<c:url value="/admin/Achange?admin_username=${admin_1.getAdmin_name()}" />">修改</a>
                                        </td>
                                        <td align="center">
                                            <c:if test ="${admin_1.getAdmin_account().equals(admin.getAdmin_account())}"> </c:if>
                                            <c:if test ="${admin_1.getAdmin_account()!=admin.getAdmin_account()}">
                                                <a href="<c:url value="/admin/deleteadmin?admin_username=${admin_1.getAdmin_name()}" />">删除</a> </c:if>

                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        <a href="<c:url value="/admin/addadmin" />">添加管理员</a>
                       <%-- <button type="submit" class="btn btn-info btn-fill btn-wd" href="<c:url value="/admin/addadmin" />">添加管理员</button>--%>

                    </div>
                </div>

            <%--<div class="col-lg-8 col-md-7">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Edit Profile</h4>
                    </div>
                    <div class="content">
                        <form>
                            <div class="row">
                                <div class="col-md-5">
                                    <div class="form-group">
                                        <label>Company</label>
                                        <input type="text" class="form-control border-input" disabled placeholder="Company" value="Creative Code Inc.">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Username</label>
                                        <input type="text" class="form-control border-input" placeholder="Username" value="michael23">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Email address</label>
                                        <input type="email" class="form-control border-input" placeholder="Email">
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>First Name</label>
                                        <input type="text" class="form-control border-input" placeholder="Company" value="Chet">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Last Name</label>
                                        <input type="text" class="form-control border-input" placeholder="Last Name" value="Faker">
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Address</label>
                                        <input type="text" class="form-control border-input" placeholder="Home Address" value="Melbourne, Australia">
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>City</label>
                                        <input type="text" class="form-control border-input" placeholder="City" value="Melbourne">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Country</label>
                                        <input type="text" class="form-control border-input" placeholder="Country" value="Australia">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Postal Code</label>
                                        <input type="number" class="form-control border-input" placeholder="ZIP Code">
                                    </div>
                                </div>
                            </div>


                            <div class="text-center">
                                <button type="submit" class="btn btn-info btn-fill btn-wd">Update Profile</button>
                            </div>
                            <div class="clearfix"></div>
                        </form>
                    </div>
                </div>
            </div>


        </div>--%>
        </div>
    </div>
</div>



</div>
</div>


</body>
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

<%--<script type="text/javascript">
    $(document).ready(function(){

        demo.initChartist();

        $.notify({
            icon: 'ti-gift',
            message: "Welcome to <b>Paper Dashboard</b> - a beautiful Bootstrap freebie for your next project."

        },{
            type: 'success',
            timer: 4000
        });

    });
</script>--%>
</html>
