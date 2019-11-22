<%--
  Created by IntelliJ IDEA.
  User: neilly
  Date: 2019/11/18
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact</title>
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
</head>
<body>
<div class="header">
    <div class="container">
        <div class="header_top">
            <ul class="phone">
                <li class="phone_left"><i class="mobile"> </i><span>1-200-346-2986</span></li>
                <li class="phone_right">Free Ground Shipping for Products over $100</li>
                <div class="clearfix"></div>
            </ul>
            <ul class="social">
                <li><a href=""> <i class="tw"> </i> </a></li>
                <li><a href=""><i class="fb"> </i> </a></li>
                <li><a href=""><i class="rss"> </i> </a></li>
                <li><a href=""><i class="msg"> </i> </a></li>
                <div class="clearfix"> </div>
            </ul>
            <ul class="account">
                <li><a href="account.jsp">My Account</a></li>
            </ul>
            <ul class="shopping_cart">
                <a href="#"><li class="shop_left"><i class="cart"> </i><span>Shop</span></li></a>
                <a href="#"><li class="shop_right"><span>$0.00</span></li></a>
                <div class="clearfix"> </div>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="header_bottom">
            <div class="header_nav">
                <div class="logo">
                    <a href="index.jsp"><img src="${pageContext.request.contextPath}/resources/images/logo.png" alt=""/><br></a>
                </div>
                <nav class="navbar navbar-default menu" role="navigation"><h3 class="nav_right"><a href="index.jsp"><img src="${pageContext.request.contextPath}/resources/images/logo.png" class="img-responsive" alt=""/></a></h3>
                    <div class="container-fluid">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                        </div>
                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav menu1">
                                <li><a href="index.jsp">Specials</a></li>
                                <li><a href="fruits.jsp">Fruits & Veg</a></li>
                                <li><a href="products.jsp">Food Products</a></li>
                                <li><a href="store.jsp">Locate Store</a></li>
                                <li><a href="club.jsp">Fan Club</a></li>
                                <li class="active"><a href="contact.jsp">Contact</a></li>
                            </ul>
                            <ul class="login">
                                <a href="account.jsp"><li class="login_top"><i class="sign"> </i><span>Sign In</span></li></a>
                                <a href="register.jsp"><li class="login_bottom"><i class="register"> </i><span>Register</span></li></a>
                            </ul>
                            <div class="clearfix"></div>
                        </div><!-- /.navbar-collapse -->
                    </div><!-- /.container-fluid -->
                </nav>
                <div class="clearfix"></div>
            </div>
            <div class="search">
                <input type="text" class="text" value="Enter Product Details" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Enter Product Details';}">
                <input type="submit" value="Search">
            </div>
        </div>
    </div>
</div>
<div class="main">
    <div class="container">
        <div class="Product_top">
            <div class="row content">
                <div class="col-md-3">
                    <div class="category_box">
                        <h3 class="cate_head">Categories</h3>
                        <ul class="category">
                            <li><a href="#">Arts</a></li>
                            <li><a href="#">Beauty</a></li>
                            <li><a href="#">Books</a></li>
                            <li><a href="#">Cart Software</a></li>
                            <li><a href="#">Electronics</a></li>
                            <li><a href="#">Fashion / Clothing</a></li>
                            <li><a href="#">Food</a></li>
                            <li><a href="#">Furniture</a></li>
                            <li><a href="#">Home Goods</a></li>
                            <li><a href="#">Jewelry</a></li>
                            <li><a href="#">Lingerie</a></li>
                            <li><a href="#">Music</a></li>
                            <li><a href="#">Office Supplies</a></li>
                            <li><a href="#">Printing</a></li>
                            <li><a href="#">Software</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="breadcrumb">
                        <a href="index.jsp">Home</a>
                        &gt;&gt;  <span class="last">Contact</span>
                    </div>
                    <div class="contact-form">
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                        <form method="post" action="contact-post.jsp">
                            <div>
                                <span><label>Name</label></span>
                                <span><input name="userName" type="text" class="textbox"></span>
                            </div>
                            <div>
                                <span><label>E-Mail</label></span>
                                <span><input name="userEmail" type="text" class="textbox"></span>
                            </div>
                            <div>
                                <span><label>Subject</label></span>
                                <span><input name="userPhone" type="text" class="textbox"></span>
                            </div>
                            <div>
                                <span><label>Message</label></span>
                                <span><textarea name="userMsg"> </textarea></span>
                            </div>
                            <div>
                                <span><input type="submit" value="Submit"></span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <div class="container">
        <div class="footer-grid footer-grid1">
            <h3 class="m_2">Company</h3>
            <ul class="list1">
                <li><a href="#">Home</a></li>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Blog</a></li>
                <li><a href="#">Latest News</a></li>
                <li><a href="#">Login</a></li>
                <li><a href="#">Join Us</a></li>
            </ul>
        </div>
        <div class="footer-grid footer-grid2">
            <h3 class="m_2">Company</h3>
            <ul class="list1">
                <li><a href="#">Lorem ipsum dolor sit amet</a></li>
                <li><a href="#">diam nonummy nibh euismod</a></li>
                <li><a href="#">nostrud exerci tation</a></li>
                <li><a href="#">hendrerit in vulputate velit</a></li>
                <li><a href="#">soluta nobis eleifend option</a></li>
                <li><a href="#">dynamicus, qui sequitur</a></li>
            </ul>
        </div>
        <div class="footer-grid footer-grid3">
            <h3 class="m_2">Information</h3>
            <ul class="list1">
                <li><a href="#">My Account</a></li>
                <li><a href="#">Rewards</a></li>
                <li><a href="#">Terms & Conditions</a></li>
                <li><a href="#">Buying Guide</a></li>
                <li><a href="#">FAQ</a></li>
            </ul>
        </div>
        <div class="footer-grid footer-grid4">
            <h3 class="m_2">Let's be friends</h3>
            <ul class="footer_social">
                <li><a href=""> <i class="tw"> </i> </a></li>
                <li><a href=""><i class="fb"> </i> </a></li>
                <li><a href=""><i class="rss"> </i> </a></li>
                <li><a href=""><i class="msg"> </i> </a></li>
                <div class="clearfix"> </div>
            </ul>
            <h3 class="m_3">Subscribe</h3>
            <p class="m_4">aliquam erat volutpat. Ut wisi</p>
            <div class="footer_search">
                <input type="text" class="text" value="Enter Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Enter Email';}">
                <input type="submit" value="Search">
            </div>
        </div>
        <div class="footer-grid footer-grid_last">
            <ul class="secure">
                <li class="secure_img"><img src="${pageContext.request.contextPath}/resources/images/secure.png" alt=""/></li>
                <li class="secure_desc">Lorem ipsum dolor coadipiscing</li>
                <div class="clearfix"> </div>
            </ul>
            <ul class="secure">
                <li class="secure_img"><img src="${pageContext.request.contextPath}/resources/images/order.png" alt=""/></li>
                <li class="secure_desc">Lorem ipsum dolor coadipiscing</li>
                <div class="clearfix"> </div>
            </ul>
        </div>
        <div class="clearfix"> </div>
        <div class="copy">
            <p>Copyright &copy; 2014.Company name All rights reserved.More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
        </div>
    </div>
</div>
</body>
</html>
