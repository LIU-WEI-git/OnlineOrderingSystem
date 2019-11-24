<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 97718
  Date: 2019/11/22
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3 content_top">
    <div class="category_box">
        <h3 class="cate_head">菜品类别</h3>
        <ul class="category">
<%--            TODO 如果用户通过url直接访问其他页面，则其他页面的控制方法还没有加入categoryList属性，可能无法显示category--%>
            <jsp:useBean id="categoryList" scope="session" type="java.util.List"/>
            <c:forEach var="category" items="${categoryList}">
                <li><a href="#">${category.category_name}</a></li>
            </c:forEach>
        </ul>
    </div>
    <ul class="product_reviews">
        <h3><i class="arrow"> </i><span>支付方式</span></h3>
        <img src="${pageContext.request.contextPath}/resources/images/payment.png" class="img-responsive" alt=""/>
    </ul>
</div>
