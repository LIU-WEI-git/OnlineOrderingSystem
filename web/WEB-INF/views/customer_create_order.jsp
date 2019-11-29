<%--
  Created by IntelliJ IDEA.
  User: 97718
  Date: 2019/11/24
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="SimpleTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建订单</title>
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
            <div class="row content">
                <div class="col-md-9">
                    <form method="post">
                        <c:choose>
                            <c:when test="${not empty addressList}">
                                地址:<br>
                                <label>
                                    <select required="required" name="address_id">
                                        <c:forEach var="address" items="${addressList}">
                                            <option value="${address.address_id}">
                                                联系人：${address.contact}&nbsp;&nbsp;电话：${address.phone}&nbsp;&nbsp;地址:${address.info}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </label>
                                <a href="<c:url value="/myAddress"/>" style="color: #3071a9">
                                    管理个人配送信息
                                </a>
                                <br/>
                            </c:when>
                            <c:otherwise>
                                您还没有设置个人配送信息<br>
                                <a href="<c:url value="/myAddress"/>" style="color: #E8360F">
                                    新增配送信息
                                </a>
                                <br/>
                            </c:otherwise>
                        </c:choose>
                        配送时间：<st:deliveryTime/><br>
                        <table border="1">
                            <thead>
                            <tr>
                                <th style="width:150px" align="center">菜品名</th>
                                <th style="width:150px" align="center">单价</th>
                                <th style="width:150px" align="center">数量</th>
                                <th style="width:150px" align="center">小计</th>
                            </tr>
                            </thead>
                            <tbody>
                            <jsp:useBean id="shoppingCart" scope="session" type="ordering.utils.ShoppingCart"/>
                            <c:forEach items="${shoppingCart.shoppingCartItemList}" var="shoppingCartItem"
                                       varStatus="li">
                                <tr>
                                    <td width="150" align="center">${shoppingCartItem.dish.dish_name}</td>
                                    <td width="150" align="center">${shoppingCartItem.dish.price}</td>
                                    <td width="150" align="center">${shoppingCartItem.amount}</td>
                                    <td width="150"
                                        align="center">${shoppingCartItem.dish.price * shoppingCartItem.amount}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        折扣：￥-<st:discount totalPrice="${shoppingCart.totalPrice}"/>
                        总价：￥<st:orderPrice totalPrice="${shoppingCart.totalPrice}"/><br/>
                        备注：
                        <label>
                            <input type="text" name="remark" width="250px"/>
                        </label>
                        <div class="register-but">
                            <input type="button" value="确认下单" id="triggerBtn"/>
                        </div>
                        <!-- 模态框 -->
                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h2>扫码支付</h2>
                                </div>
                                <c:choose>
                                    <c:when test="${shoppingCart.shoppingCartItemList.size()==0}">
                                        <h3>您的购物车是空的，无法下单，赶快去购物吧</h3>
                                        <div class="modal-footer">
                                            <div class="register-but">
                                                <a href="<c:url value="/"/>">
                                                    <input type="button" value="返回首页购物"/>
                                                </a>
                                                </c:when>
                                                <c:when test="${empty addressList}">
                                                <h3>您还没有设置地址信息，无法下单，请先设置地址信息</h3>
                                                <div class="modal-footer">
                                                    <div class="register-but">
                                                        <a href="<c:url value="/myAddress/add_address"/>">
                                                            <input type="button" value="设置地址信息"/>
                                                        </a>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <div class="modal-body">
                                                            <img src="${pageContext.request.contextPath}/resources/images/QRCode.png"
                                                                 class="img-responsive" alt=""/>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <div class="register-but">
                                                                <p>完成扫码支付后请点击“支付完成”按钮</p>
                                                                <input type="submit" value="支付完成"/>
                                                                </c:otherwise>
                                                                </c:choose>
                                                                <input type="button" value="取消" id="closeBtn"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                        <script type="text/javascript"
                                src="${pageContext.request.contextPath}/resources/js/modalBox.js"></script>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="customer_footer.jsp"/>
<%--<script>--%>
<%--    (function() {--%>
<%--        /*建立模态框对象*/--%>
<%--        var modalBox = {};--%>
<%--        /*获取模态框*/--%>
<%--        modalBox.modal = document.getElementById("myModal");--%>
<%--        /*获得trigger按钮*/--%>
<%--        modalBox.triggerBtn = document.getElementById("triggerBtn");--%>
<%--        /*获得关闭按钮*/--%>
<%--        modalBox.closeBtn = document.getElementById("closeBtn");--%>
<%--        /*模态框显示*/--%>
<%--        modalBox.show = function() {--%>
<%--            console.log(this.modal);--%>
<%--            this.modal.style.display = "block";--%>
<%--        }--%>
<%--        /*模态框关闭*/--%>
<%--        modalBox.close = function() {--%>
<%--            this.modal.style.display = "none";--%>
<%--        }--%>
<%--        /*当用户点击模态框内容之外的区域，模态框也会关闭*/--%>
<%--        modalBox.outsideClick = function() {--%>
<%--            var modal = this.modal;--%>
<%--            window.onclick = function(event) {--%>
<%--                if(event.target == modal) {--%>
<%--                    modal.style.display = "none";--%>
<%--                }--%>
<%--            }--%>
<%--        }--%>
<%--        /*模态框初始化*/--%>
<%--        modalBox.init = function() {--%>
<%--            var that = this;--%>
<%--            this.triggerBtn.onclick = function() {--%>
<%--                that.show();--%>
<%--            }--%>
<%--            this.closeBtn.onclick = function() {--%>
<%--                that.close();--%>
<%--            }--%>
<%--            this.outsideClick();--%>
<%--        }--%>
<%--        modalBox.init();--%>
<%--    })();--%>
<%-- </script> --%>
</body>
</html>
