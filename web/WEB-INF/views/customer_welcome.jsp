<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: neilly
  Date: 2019/11/18
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>网上订餐系统</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel='stylesheet' type='text/css' />
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel='stylesheet' type='text/css' />
    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link href='http://fonts.googleapis.com/css?family=Exo+2:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
</head>
<body>
<jsp:include page="customer_header.jsp" flush="true"/>
<div class="main">
    <div class="container">
        <div class="banner">
            <img src="${pageContext.request.contextPath}/resources/images/banner.jpg" class="img-responsive" alt=""/>
        </div>
        <div class="row content">
            <jsp:include page="customer_sider.jsp"/>
            <div class="col-md-9">
                <ul class="feature">
                    <h3><i class="arrow"> </i><span>全部菜品</span></h3>
                </ul>
                <div class="tlinks">Collect from <a href="http://www.cssmoban.com/"  title="网站模板">网站模板</a></div>
                <div class="row content_bottom">
                    <c:forEach items="${paginationSupport.items}" var="dishCategorySupport">
                        <div class="col-md-3">
                                <%-- TODO 修改链接到商品详情页--%>
                            <div class="content_box"><a href="<c:url value="/dishDetail/${dishCategorySupport.dish.dish_id}"/>">
                                <div class="view view-fifth">
                                    <img src="${pageContext.request.contextPath}/resources/${dishCategorySupport.dish.picture_url}" class="img-responsive" alt=""/>
                                    <div class="content_box-grid">
                                        <p class="m_1">${dishCategorySupport.dish.description}</p>
                                        <div class="price">
                                             <span class="dish-name">${dishCategorySupport.dish.dish_name}  </span>
                                            <span class="actual">￥${dishCategorySupport.dish.price}</span>
                                        </div>
                                        <ul class="product_but">
                                            <li class="but3">购买</li>
                                            <div class="clearfix"> </div>
                                        </ul>
                                        <div class="mask">
                                            <div class="info">Quick View</div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
<%--                <div class="row content_bottom1">--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="content_box"><a href="single.jsp">--%>
<%--                            <div class="view view-fifth">--%>
<%--                                <img src="${pageContext.request.contextPath}/resources/images/p8.jpg" class="img-responsive" alt=""/>--%>
<%--                                <div class="content_box-grid">--%>
<%--                                    <p class="m_1">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</p>--%>
<%--                                    <div class="price">Price:--%>
<%--                                        <span class="actual">$12.00</span>--%>
<%--                                    </div>--%>
<%--                                    <ul class="product_but">--%>
<%--                                        <li class="but3">Buy</li>--%>
<%--                                        <li class="like"><span>120</span><i class="like1"> </i></li>--%>
<%--                                        <div class="clearfix"> </div>--%>
<%--                                    </ul>--%>
<%--                                    <div class="mask">--%>
<%--                                        <div class="info">Quick View</div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </a>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <ul class="dc_pagination dc_paginationA dc_paginationA06">
                    <c:if test="${paginationSupport.previousPage}">
                        <a href="<c:url value="/?pageNo=${paginationSupport.currentPageNo-1}" />">上一页</a>
                    </c:if>
                    第${paginationSupport.currentPageNo}页，共${paginationSupport.totalPageCount}页
                    <c:if test="${paginationSupport.nextPage}">
                        <a href="<c:url value="/?pageNo=${paginationSupport.currentPageNo+1}" />">下一页</a>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>
<a href="<c:url value="/admin/alogin" />">登录</a>
<jsp:include page="customer_footer.jsp" flush="true"/>
</body>
</html>
