<%--
  Created by IntelliJ IDEA.
  User: owner
  Date: 2019/11/26
  Time: 18:03
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
         <%--  <form action="<c:url value="/admin/ordertype"/>">
            <select  name="signal" class="btn btn-info btn-search">
                <option>all</option>
                <option>noconfirmed</option>
                <option>nodelivery</option>
                <option>deliverying</option>
                <option>deliveried</option>

            </select>
                <br><br>
                        <button type="submit" class="btn btn-info btn-search">类别查询</button>

           </form>--%>

            <form class="input-group col-md-12" style="margin: 10px;position: relative" action="<c:url value="/admin/searchorder"/>">



                <input type="text" class="form-control" name="message"
                       placeholder="请输入要查询的顾客账号">
                <span class="input-group-btn">
                        <button type="submit" class="btn btn-info btn-search">账号查询</button>
                    </span>
            </form>
            <div class="content table-responsive table-full-width">
               &nbsp;
                <a href="<c:url value="/admin/order"/> " style="color: #3071a9">查看所有订单</a>&nbsp;&nbsp;&nbsp;
                <a href="<c:url value="/admin/order?complete=2"/> " style="color: #3071a9">查看已完成订单</a>&nbsp;
                <a href="<c:url value="/admin/order?complete=1"/> " style="color: #3071a9">查看配送中的订单</a>&nbsp;
                <a href="<c:url value="/admin/order?complete=0"/> " style="color: #3071a9">查看未确定的订单</a>
                <a href="<c:url value="/admin/order?complete=3"/> " style="color: #3071a9">查看未配送的订单</a>
                <br/><br/>

                <c:forEach items="${paginationSupport.currentPageItem}" var="order">
                    <li class="product_right">
                        <h3>下单时间：${order.create_time}</h3>
                        <a href="<c:url value="/admin/order_item?order_id=${order.order_id}"/>"><h4 style="color:blue"><strong>订单详情</strong></h4></a>
                        <h4 class="model"><strong>订单价格: </strong>${order.order_price}</h4>
                        <h4 class="model"><strong>折扣: ${order.discount}</strong></h4><br>
                        <h4>订单状态:
                            <c:choose>
                                <c:when test="${order.order_state ==0}">商家暂未接单</c:when>
                                <c:when test="${order.order_state ==1}">商家已接单</c:when>
                                <c:when test="${order.order_state ==2}">订单已结束</c:when>
                            </c:choose>
                        </h4>
                        <div class="product_price">
                            <h4>配送状态:
                                <c:choose>
                                    <c:when test="${order.delivery_state ==0}">未配送</c:when>
                                    <c:when test="${order.delivery_state==1}">配送中</c:when>
                                    <c:when test="${order.delivery_state==2}">已送达</c:when>
                                </c:choose>
                            </h4>
                        </div>
                        <div class="but1">
                            <a href="<c:url value="/admin/address_info?order_id=${order.order_id}" />"><h4 style="color:blue">订单地址详情</h4></a>
                        </div>
                        <h4>备注：</h4><span>${order.remark}</span>
                    </li>
                    <c:choose>
                        <c:when test="${order.delivery_state ==0&&order.order_state==0}">
                            <p class="detail_desc"><a href="<c:url value="/admin/confirmorder?order_id=${order.order_id}"/>">确认订单</a></p>
                        </c:when>
                        <c:when test="${order.delivery_state ==0&&order.order_state==1}">
                            <p class="detail_desc"><a href="<c:url value="/admin/begindeliver?order_id=${order.order_id}"/>">开始配送</a></p>
                        </c:when>
                        <c:when test="${order.delivery_state==1&&order.order_state==1}">
                            <p class="detail_desc"><a href="<c:url value="/admin/enddeliver?order_id=${order.order_id}"/>">完成配送</a></p>
                        </c:when>

                    </c:choose>
                    <h4 class="detail"> </h4>
                    <p class="detail_desc "> </p>
                    <a>----------------------------------------------------------------------------------------</a>
                    <br>
                </c:forEach>
                <c:choose>
                    <c:when test="${complete eq 'all'}">
                        <%--<a href="<c:url value="/order?pageNo=1"/> " class="btn-default">首页</a>&nbsp;--%>&nbsp;&nbsp;
                        <a href="<c:url value="/admin/order?pageNo=${paginationSupport.currentPageNo-1}"/>" class="btn-default">上一页</a>&nbsp;&nbsp;&nbsp;
                        <b>当前是第${paginationSupport.currentPageNo}页，共${paginationSupport.totalPageCount}页</b>&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value="/admin/order?pageNo=${paginationSupport.currentPageNo+1}"/>" class="btn-default">下一页</a>&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value="/admin/order?pageNo=${paginationSupport.totalPageCount}"/>" class="btn-default">末页</a>
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value="/admin/order?pageNo=1&&complete=${complete}"/> " class="btn-default">首页</a>&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value="/admin/order?pageNo=${paginationSupport.currentPageNo-1}&&complete=${complete}"/>" class="btn-default">上一页</a>&nbsp;&nbsp;&nbsp;
                        <b>当前是第${paginationSupport.currentPageNo}页，共${paginationSupport.totalPageCount}页</b>&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value="/admin/order?pageNo=${paginationSupport.currentPageNo+1}&&complete=${complete}"/>" class="btn-default">下一页</a>&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value="/admin/order?pageNo=${paginationSupport.totalPageCount}&&complete=${complete}"/>" class="btn-default">末页</a>
                    </c:otherwise>
                </c:choose>
              <%--  <c:if test="${paginationSupport.previousPage}">
                    <a href="<c:url value="/admin/order?pageNo=${paginationSupport.getCurrentPageNo()-1}" />" >上一页</a>
                </c:if>
                <c:if test="${paginationSupport.nextPage}">
                    <a href="<c:url value="/admin/order?pageNo=${paginationSupport.getCurrentPageNo()+1}" />" >下一页</a>
                </c:if>--%>
           <%-- <c:forEach items="${orders}" var="order">
                <ul class="">
                    <li class=""><img src="" class="img-responsive" alt=""/>
                        <p>订单流水号：${order.order_id}</p>
                    </li>
                    <li class="product_right">
                        <h3>下单时间：${order.create_time}</h3>
                        <h4>订单状态：
                            <c:choose>
                                <c:when test="${order.order_state ==0}">商家暂未接单</c:when>
                                <c:when test="${order.order_state ==1}">商家已接单</c:when>
                                <c:when test="${order.order_state ==2}">订单已结束</c:when>
                            </c:choose>
                        </h4>
                        <span class="model"><strong>订单价格: </strong>${order.order_price}</span><br>
                        <span class="model"><strong>折扣: ${order.discount}</strong></span>
                        <div class="product_price">配送状态:
                            <c:choose>
                                <c:when test="${order.delivery_state ==0}">
                                    <span class="actual">未配送</span>
                                </c:when>
                                <c:when test="${order.delivery_state==1}">配送中</c:when>
                                <c:when test="${order.delivery_state==2}">已送达</c:when>
                            </c:choose>
                        </div>
                        <div class="but1">
                            <a href="<c:url value="/admin/address_info?order_id=${order.order_id}" />">订单地址详情</a>
                        </div>
                    </li>
                    <div class="clearfix"> </div>
                    <h5 class="detail">备注:${order.remark}</h5>
                    <c:choose>
                        <c:when test="${order.delivery_state ==0&&order.order_state==0}">
                            <p class="detail_desc"><a href="<c:url value="/admin/confirmorder?order_id=${order.order_id}"/>">确认订单</a></p>
                        </c:when>
                        <c:when test="${order.delivery_state ==0&&order.order_state==1}">
                            <p class="detail_desc"><a href="<c:url value="/admin/begindeliver?order_id=${order.order_id}"/>">开始配送</a></p>
                        </c:when>
                        <c:when test="${order.delivery_state==1&&order.order_state==1}">
                            <p class="detail_desc"><a href="<c:url value="/admin/enddeliver?order_id=${order.order_id}"/>">完成配送</a></p>
                        </c:when>

                    </c:choose>
                    <p class="detail_desc"><a href="<c:url value="/admin/order_item?order_id=${order.order_id}"/>">订单详情</a></p>
                </ul>
            </c:forEach>--%>
            </div>
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
