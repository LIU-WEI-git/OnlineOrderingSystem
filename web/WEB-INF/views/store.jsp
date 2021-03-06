<%--
  Created by IntelliJ IDEA.
  User: neilly
  Date: 2019/11/18
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Store</title>
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
                                <li class="active"><a href="store.jsp">Locate Store</a></li>
                                <li><a href="club.jsp">Fan Club</a></li>
                                <li><a href="contact.jsp">Contact</a></li>
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
                    <div class="register_account">
                        <form>
                            <select id="country" name="country" onchange="change_country(this.value)" class="frm-field required">
                                <option value="null">Select a Country</option>
                                <option value="AX">Åland Islands</option>
                                <option value="AF">Afghanistan</option>
                                <option value="AL">Albania</option>
                                <option value="DZ">Algeria</option>
                                <option value="AS">American Samoa</option>
                                <option value="AD">Andorra</option>
                                <option value="AO">Angola</option>
                                <option value="AI">Anguilla</option>
                                <option value="AQ">Antarctica</option>
                                <option value="AG">Antigua And Barbuda</option>
                                <option value="AR">Argentina</option>
                                <option value="AM">Armenia</option>
                                <option value="AW">Aruba</option>
                                <option value="AU">Australia</option>
                                <option value="AT">Austria</option>
                                <option value="AZ">Azerbaijan</option>
                                <option value="BS">Bahamas</option>
                                <option value="BH">Bahrain</option>
                                <option value="BD">Bangladesh</option>
                                <option value="BB">Barbados</option>
                                <option value="BY">Belarus</option>
                                <option value="BE">Belgium</option>
                                <option value="BZ">Belize</option>
                                <option value="BJ">Benin</option>
                                <option value="BM">Bermuda</option>
                                <option value="BT">Bhutan</option>
                                <option value="BO">Bolivia</option>
                                <option value="BA">Bosnia and Herzegovina</option>
                                <option value="BW">Botswana</option>
                                <option value="BV">Bouvet Island</option>
                                <option value="BR">Brazil</option>
                                <option value="IO">British Indian Ocean Territory</option>
                                <option value="BN">Brunei</option>
                                <option value="BG">Bulgaria</option>
                                <option value="BF">Burkina Faso</option>
                                <option value="BI">Burundi</option>
                                <option value="KH">Cambodia</option>
                                <option value="CM">Cameroon</option>
                                <option value="CA">Canada</option>
                                <option value="CV">Cape Verde</option>
                                <option value="KY">Cayman Islands</option>
                                <option value="CF">Central African Republic</option>
                                <option value="TD">Chad</option>
                                <option value="CL">Chile</option>
                                <option value="CN">China</option>
                                <option value="CX">Christmas Island</option>
                                <option value="CC">Cocos (Keeling) Islands</option>
                                <option value="CO">Colombia</option>
                                <option value="KM">Comoros</option>
                                <option value="CG">Congo</option>
                                <option value="CD">Congo, Democractic Republic</option>
                                <option value="CK">Cook Islands</option>
                                <option value="CR">Costa Rica</option>
                                <option value="CI">Cote D'Ivoire (Ivory Coast)</option>
                                <option value="HR">Croatia (Hrvatska)</option>
                                <option value="CU">Cuba</option>
                                <option value="CY">Cyprus</option>
                                <option value="CZ">Czech Republic</option>
                                <option value="DK">Denmark</option>
                                <option value="DJ">Djibouti</option>
                                <option value="DM">Dominica</option>
                                <option value="DO">Dominican Republic</option>
                                <option value="TP">East Timor</option>
                                <option value="EC">Ecuador</option>
                                <option value="EG">Egypt</option>
                                <option value="SV">El Salvador</option>
                                <option value="GQ">Equatorial Guinea</option>
                                <option value="ER">Eritrea</option>
                                <option value="EE">Estonia</option>
                                <option value="ET">Ethiopia</option>
                                <option value="FK">Falkland Islands (Islas Malvinas)</option>
                                <option value="FO">Faroe Islands</option>
                                <option value="FJ">Fiji Islands</option>
                                <option value="FI">Finland</option>
                                <option value="FR">France</option>
                                <option value="FX">France, Metropolitan</option>
                                <option value="GF">French Guiana</option>
                                <option value="PF">French Polynesia</option>
                                <option value="TF">French Southern Territories</option>
                                <option value="GA">Gabon</option>
                                <option value="GM">Gambia, The</option>
                                <option value="GE">Georgia</option>
                                <option value="DE">Germany</option>
                                <option value="GH">Ghana</option>
                                <option value="GI">Gibraltar</option>
                                <option value="GR">Greece</option>
                                <option value="GL">Greenland</option>
                                <option value="GD">Grenada</option>
                                <option value="GP">Guadeloupe</option>
                                <option value="GU">Guam</option>
                                <option value="GT">Guatemala</option>
                                <option value="GG">Guernsey</option>
                                <option value="GN">Guinea</option>
                                <option value="GW">Guinea-Bissau</option>
                                <option value="GY">Guyana</option>
                                <option value="HT">Haiti</option>
                                <option value="HM">Heard and McDonald Islands</option>
                                <option value="HN">Honduras</option>
                                <option value="HK">Hong Kong S.A.R.</option>
                                <option value="HU">Hungary</option>
                                <option value="IS">Iceland</option>
                                <option value="IN">India</option>
                                <option value="ID">Indonesia</option>
                                <option value="IR">Iran</option>
                                <option value="IQ">Iraq</option>
                                <option value="IE">Ireland</option>
                                <option value="IM">Isle of Man</option>
                                <option value="IL">Israel</option>
                                <option value="IT">Italy</option>
                                <option value="JM">Jamaica</option>
                                <option value="JP">Japan</option>
                                <option value="JE">Jersey</option>
                                <option value="JO">Jordan</option>
                                <option value="KZ">Kazakhstan</option>
                                <option value="KE">Kenya</option>
                                <option value="KI">Kiribati</option>
                                <option value="KR">Korea</option>
                                <option value="KP">Korea, North</option>
                                <option value="KW">Kuwait</option>
                                <option value="KG">Kyrgyzstan</option>
                                <option value="LA">Laos</option>
                                <option value="LV">Latvia</option>
                                <option value="LB">Lebanon</option>
                                <option value="LS">Lesotho</option>
                                <option value="LR">Liberia</option>
                                <option value="LY">Libya</option>
                                <option value="LI">Liechtenstein</option>
                                <option value="LT">Lithuania</option>
                                <option value="LU">Luxembourg</option>
                                <option value="MO">Macau S.A.R.</option>
                                <option value="MK">Macedonia</option>
                                <option value="MG">Madagascar</option>
                                <option value="MW">Malawi</option>
                                <option value="MY">Malaysia</option>
                                <option value="MV">Maldives</option>
                                <option value="ML">Mali</option>
                                <option value="MT">Malta</option>
                                <option value="MH">Marshall Islands</option>
                                <option value="MQ">Martinique</option>
                                <option value="MR">Mauritania</option>
                                <option value="MU">Mauritius</option>
                                <option value="YT">Mayotte</option>
                                <option value="MX">Mexico</option>
                                <option value="FM">Micronesia</option>
                                <option value="MD">Moldova</option>
                                <option value="MC">Monaco</option>
                                <option value="MN">Mongolia</option>
                                <option value="ME">Montenegro</option>
                                <option value="MS">Montserrat</option>
                                <option value="MA">Morocco</option>
                                <option value="MZ">Mozambique</option>
                                <option value="MM">Myanmar</option>
                                <option value="NA">Namibia</option>
                                <option value="NR">Nauru</option>
                                <option value="NP">Nepal</option>
                                <option value="NL">Netherlands</option>
                                <option value="AN">Netherlands Antilles</option>
                                <option value="NC">New Caledonia</option>
                                <option value="NZ">New Zealand</option>
                                <option value="NI">Nicaragua</option>
                                <option value="NE">Niger</option>
                                <option value="NG">Nigeria</option>
                                <option value="NU">Niue</option>
                                <option value="NF">Norfolk Island</option>
                                <option value="MP">Northern Mariana Islands</option>
                                <option value="NO">Norway</option>
                                <option value="OM">Oman</option>
                                <option value="PK">Pakistan</option>
                                <option value="PW">Palau</option>
                                <option value="PS">Palestinian Territory, Occupied</option>
                                <option value="PA">Panama</option>
                                <option value="PG">Papua new Guinea</option>
                                <option value="PY">Paraguay</option>
                                <option value="PE">Peru</option>
                                <option value="PH">Philippines</option>
                                <option value="PN">Pitcairn Island</option>
                                <option value="PL">Poland</option>
                                <option value="PT">Portugal</option>
                                <option value="PR">Puerto Rico</option>
                                <option value="QA">Qatar</option>
                                <option value="RE">Reunion</option>
                                <option value="RO">Romania</option>
                                <option value="RU">Russia</option>
                                <option value="RW">Rwanda</option>
                                <option value="SH">Saint Helena</option>
                                <option value="KN">Saint Kitts And Nevis</option>
                                <option value="LC">Saint Lucia</option>
                                <option value="PM">Saint Pierre and Miquelon</option>
                                <option value="VC">Saint Vincent And The Grenadines</option>
                                <option value="WS">Samoa</option>
                                <option value="SM">San Marino</option>
                                <option value="ST">Sao Tome and Principe</option>
                                <option value="SA">Saudi Arabia</option>
                                <option value="SN">Senegal</option>
                            </select>
                            <div><input type="text" value="City" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'City';}"></div>
                            <button class="grey">Search</button>
                        </form>
                    </div>
                    <div class="row content_bottom">
                        <div class="col-md-3">
                            <div class="content_box"><a href="single.jsp">
                                <div class="view view-fifth">
                                    <img src="${pageContext.request.contextPath}/resources/images/p1.jpg" class="img-responsive" alt="">
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
                                    <img src="${pageContext.request.contextPath}/resources/images/p4.jpg" class="img-responsive" alt="">
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
                                    <img src="${pageContext.request.contextPath}/resources/images/p3.jpg" class="img-responsive" alt="">
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
                                    <img src="${pageContext.request.contextPath}/resources/images/p2.jpg" class="img-responsive" alt="">
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
                                    <img src="${pageContext.request.contextPath}/resources/images/p1.jpg" class="img-responsive" alt="">
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
                                    <img src="${pageContext.request.contextPath}/resources/images/p4.jpg" class="img-responsive" alt="">
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
                                    <img src="${pageContext.request.contextPath}/resources/images/p3.jpg" class="img-responsive" alt="">
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
                                    <img src="${pageContext.request.contextPath}/resources/images/p2.jpg" class="img-responsive" alt="">
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
