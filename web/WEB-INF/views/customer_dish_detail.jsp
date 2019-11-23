<%--
  Created by IntelliJ IDEA.
  User: 97718
  Date: 2019/11/23
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Single</title>
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
    <!----webfonts---->
    <link href='http://fonts.googleapis.com/css?family=Exo+2:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
    <!----details-product-slider--->
    <!-- Include the Etalage files -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/etalage.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.etalage.min.js"></script>
    <!-- Include the Etalage files -->
    <script>
        jQuery(document).ready(function($){

            $('#etalage').etalage({
                thumb_image_width: 300,
                thumb_image_height: 400,

                show_hint: true,
                click_callback: function(image_anchor, instance_id){
                    alert('Callback example:\nYou clicked on an image with the anchor: "'+image_anchor+'"\n(in Etalage instance: "'+instance_id+'")');
                }
            });
            // This is for the dropdown list example:
            $('.dropdownlist').change(function(){
                etalage_show( $(this).find('option:selected').attr('class') );
            });

        });
    </script>
    <!----//details-product-slider--->
</head>
<body>
<jsp:include page="customer_header.jsp"/>
<div class="main">
    <div class="container">
        <div class="single">
            <div class="row content">
                <jsp:include page="customer_sider.jsp"/>
                <div class="col-md-9">
                    <div class="single_image">
                        <ul id="etalage">
                            <li>
<%--                                TODO --%>
                                <a href="optionallink.jsp">
                                    <img class="etalage_thumb_image" src="${pageContext.request.contextPath}/resources/${dish.picture_url}"  alt=""/>
                                    <img class="etalage_source_image"  src="${pageContext.request.contextPath}/resources/${dish.picture_url}" alt=""/>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="single_right">
                        <h3>${dish.dish_name}</h3>
                        <p class="m_5">${dish.description}</p>
                        <div class="price_single">
                            <span class="actual1">￥${dish.price}</span>
                        </div>

                        <div class="btn_form">
                            <form>
                                <input type="submit" value="加入购物车" title="">
                            </form>
                        </div>
<%--                        TODO 挑战项1：可以选择发送链接给好友，也可以以邮件形式发送--%>
<%--                        <div class="col-xs-12  col-sm-6  col-md-4">--%>
<%--                            <div class="banners--small  banners--small--social">--%>
<%--                                <a href="#" class="social"><i class="zocial-facebook"> </i>--%>
<%--                                    <span class="banners--small--text"> Share on<br>Facebook</span>--%>
<%--                                    <div class="clearfix"> </div>--%>
<%--                                </a>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                    </div>
                    <div class="clearfix"> </div>
<%--                    TODO 挑战项2：相似的产品，相关性推荐--%>
<%--                    <div class="related_products">--%>
<%--                        <h3>Related Products</h3>--%>
<%--                        <div class="row">--%>
<%--                            <div class="col-md-4 related">--%>
<%--                                <img src="${pageContext.request.contextPath}/resources/images/pic4.jpg" class="img-responsive" alt=""/>--%>
<%--                            </div>--%>
<%--                            <div class="col-md-4 related">--%>
<%--                                <img src="${pageContext.request.contextPath}/resources/images/pic5.jpg" class="img-responsive" alt=""/>--%>
<%--                            </div>--%>
<%--                            <div class="col-md-4">--%>
<%--                                <img src="${pageContext.request.contextPath}/resources/images/pic6.jpg" class="img-responsive" alt=""/>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="customer_footer.jsp"/>
</body>
</html>
