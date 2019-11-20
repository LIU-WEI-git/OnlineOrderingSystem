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
    <title>OnlineOrdering</title>
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
            <div class="col-md-3 content_top">
                <div class="category_box">
                    <h3 class="cate_head">Categories</h3>
                    <ul class="category">
                        <c:forEach var="category" items="${categoryList}">
                            <li><a href="#">${category.category_name}</a></li>
                        </c:forEach>
                    </ul>
                </div>
                <ul class="product_reviews">
                    <h3><i class="arrow"> </i><span>Product Reviews</span></h3>
                    <li>
                        <ul class="review1">
                            <li class="review1_img"><img src="${pageContext.request.contextPath}/resources/images/pic1.jpg" class="img-responsive" alt=""/></li>
                            <li class="review1_desc"><h3><a href="#">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</a></h3><p>Wed, June 2014</p></li>
                            <div class="clearfix"> </div>
                        </ul>
                    </li>
                    <li>
                        <ul class="review1">
                            <li class="review1_img"><img src="${pageContext.request.contextPath}/resources/images/pic2.jpg" class="img-responsive" alt=""/></li>
                            <li class="review1_desc"><h3><a href="#">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</a></h3><p>Wed, June 2014</p></li>
                            <div class="clearfix"> </div>
                        </ul>
                    </li>
                    <li>
                        <ul class="review1">
                            <li class="review1_img"><img src="${pageContext.request.contextPath}/resources/images/pic3.jpg" class="img-responsive" alt=""/></li>
                            <li class="review1_desc"><h3><a href="#">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</a></h3><p>Wed, June 2014</p></li>
                            <div class="clearfix"> </div>
                        </ul>
                    </li>
                    <div class="but">
                        <a href="#">More Reviews<i class="but_arrow"> </i></a>
                    </div>
                </ul>

                <ul class="product_reviews">
                    <h3><i class="arrow"> </i><span>Payment Methods</span></h3>
                    <img src="${pageContext.request.contextPath}/resources/images/payment.png" class="img-responsive" alt=""/>
                </ul>
            </div>
            <div class="col-md-9">
                <ul class="feature">
                    <h3><i class="arrow"> </i><span>Today's Featured Products</span></h3>
                </ul>
                <ul class="feature_grid">
                    <li class="grid1"><img src="${pageContext.request.contextPath}/resources/images/f1.jpg" class="img-responsive" alt=""/>
                        <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed</p>
                        <div class="price">Price:
                            <span class="actual">$12.00</span>
                        </div>
                        <div class="but1">
                            <a href="#">Buy Now</a>
                        </div>
                    </li>
                    <li class="grid1"><img src="${pageContext.request.contextPath}/resources/images/f2.jpg" class="img-responsive" alt=""/>
                        <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed</p>
                        <div class="price">Price:
                            <span class="actual">$12.00</span>
                        </div>
                        <div class="but1">
                            <a href="#">Buy Now</a>
                        </div>
                    </li>
                    <li class="grid2"><img src="${pageContext.request.contextPath}/resources/images/f3.jpg" class="img-responsive" alt=""/>
                        <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed</p>
                        <div class="price">Price:
                            <span class="actual">$12.00</span>
                        </div>
                        <div class="but1">
                            <a href="#">Buy Now</a>
                        </div>
                    </li>
                    <div class="clearfix"> </div>
                </ul>
                <ul class="feature">
                    <h3><i class="arrow"> </i><span>Popular products</span></h3>
                </ul>
                <div class="tlinks">Collect from <a href="http://www.cssmoban.com/"  title="网站模板">网站模板</a></div>
                <div class="row content_bottom">
                    <div class="col-md-3">
                        <div class="content_box"><a href="single.jsp">
                            <div class="view view-fifth">
                                <img src="${pageContext.request.contextPath}/resources/images/p1.jpg" class="img-responsive" alt=""/>
                                <div class="content_box-grid">
                                    <p class="m_1">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</p>
                                    <div class="price">Price:
                                        <span class="actual">$12.00</span>
                                    </div>
                                    <ul class="product_but">
                                        <li class="but3">Buy</li>
                                        <li class="like"><span>120</span><i class="like1"> </i></li>
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
                    <div class="col-md-3">
                        <div class="content_box"><a href="single.jsp">
                            <div class="view view-fifth">
                                <img src="${pageContext.request.contextPath}/resources/images/p4.jpg" class="img-responsive" alt=""/>
                                <div class="content_box-grid">
                                    <p class="m_1">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</p>
                                    <div class="price">Price:
                                        <span class="actual">$12.00</span>
                                    </div>
                                    <ul class="product_but">
                                        <li class="but3">Buy</li>
                                        <li class="like"><span>120</span><i class="like1"> </i></li>
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
                    <div class="col-md-3">
                        <div class="content_box"><a href="single.jsp">
                            <div class="view view-fifth">
                                <img src="${pageContext.request.contextPath}/resources/images/p3.jpg" class="img-responsive" alt=""/>
                                <div class="content_box-grid">
                                    <p class="m_1">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</p>
                                    <div class="price">Price:
                                        <span class="actual">$12.00</span>
                                    </div>
                                    <ul class="product_but">
                                        <li class="but3">Buy</li>
                                        <li class="like"><span>120</span><i class="like1"> </i></li>
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
                    <div class="col-md-3">
                        <div class="content_box"><a href="single.jsp">
                            <div class="view view-fifth">
                                <img src="${pageContext.request.contextPath}/resources/images/p2.jpg" class="img-responsive" alt=""/>
                                <div class="content_box-grid">
                                    <p class="m_1">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</p>
                                    <div class="price">Price:
                                        <span class="actual">$12.00</span>
                                    </div>
                                    <ul class="product_but">
                                        <li class="but3">Buy</li>
                                        <li class="like"><span>120</span><i class="like1"> </i></li>
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
                </div>
                <div class="row content_bottom1">
                    <div class="col-md-3">
                        <div class="content_box"><a href="single.jsp">
                            <div class="view view-fifth">
                                <img src="${pageContext.request.contextPath}/resources/images/p8.jpg" class="img-responsive" alt=""/>
                                <div class="content_box-grid">
                                    <p class="m_1">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</p>
                                    <div class="price">Price:
                                        <span class="actual">$12.00</span>
                                    </div>
                                    <ul class="product_but">
                                        <li class="but3">Buy</li>
                                        <li class="like"><span>120</span><i class="like1"> </i></li>
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
                    <div class="col-md-3">
                        <div class="content_box"><a href="single.jsp">
                            <div class="view view-fifth">
                                <img src="${pageContext.request.contextPath}/resources/images/p7.jpg" class="img-responsive" alt=""/>
                                <div class="content_box-grid">
                                    <p class="m_1">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</p>
                                    <div class="price">Price:
                                        <span class="actual">$12.00</span>
                                    </div>
                                    <ul class="product_but">
                                        <li class="but3">Buy</li>
                                        <li class="like"><span>120</span><i class="like1"> </i></li>
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
                    <div class="col-md-3">
                        <div class="content_box"><a href="single.jsp">
                            <div class="view view-fifth">
                                <img src="${pageContext.request.contextPath}/resources/images/p6.jpg" class="img-responsive" alt=""/>
                                <div class="content_box-grid">
                                    <p class="m_1">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</p>
                                    <div class="price">Price:
                                        <span class="actual">$12.00</span>
                                    </div>
                                    <ul class="product_but">
                                        <li class="but3">Buy</li>
                                        <li class="like"><span>120</span><i class="like1"> </i></li>
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
                    <div class="col-md-3">
                        <div class="content_box"><a href="single.jsp">
                            <div class="view view-fifth">
                                <img src="${pageContext.request.contextPath}/resources/images/p5.jpg" class="img-responsive" alt=""/>
                                <div class="content_box-grid">
                                    <p class="m_1">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</p>
                                    <div class="price">Price:
                                        <span class="actual">$12.00</span>
                                    </div>
                                    <ul class="product_but">
                                        <li class="but3">Buy</li>
                                        <li class="like"><span>120</span><i class="like1"> </i></li>
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
                </div>
                <ul class="dc_pagination dc_paginationA dc_paginationA06">
                    <li><a href="#">1</a></li>
                    <li><a href="#" class="current">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">...</a></li>
                    <li><a href="#">19</a></li>
                    <li><a href="#">20</a></li>
                    <li><a href="#" class="previous">Next></a></li>
                    <li><a href="#" class="next">Last>></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<a href="<c:url value="/admin/alogin" />">登录</a> |
<jsp:include page="customer_footer.jsp" flush="true"/>
</body>
</html>
