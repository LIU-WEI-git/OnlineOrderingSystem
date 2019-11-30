<%--
  Created by IntelliJ IDEA.
  User: owner
  Date: 2019/11/20
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="copyrights">Collect from <a href="http://www.cssmoban.com/"  title="网站模板">网站模板</a></div>--%>

<%--<div class="main-panel">--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar bar1"></span>
                    <span class="icon-bar bar2"></span>
                    <span class="icon-bar bar3"></span>
                </button>
                <a class="navbar-brand" href="#">订餐管理员系统</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="<c:url value="/admin/person"/>"  class="dropdown-toggle" <%--data-toggle="dropdown"--%>>
                            <i class="ti-panel"></i>
                            <p>个人信息</p>
                        </a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="ti-bell"></i>
                            <p class="notification">6</p>
                            <p>开发人</p>
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">刘威</a></li>
                            <li><a href="#">盛佳煜</a></li>
                            <li><a href="#">田皓哲</a></li>
                            <li><a href="#">吴东杰</a></li>
                            <li><a href="#">聂俊杰</a></li>
                            <li><a href="#">赵辰龙</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="<c:url value="/admin/explain"/>">
                            <i class="ti-settings"></i>
                            <p>系统设置</p>
                        </a>
                    </li>
                </ul>

            </div>
        </div>
    </nav>
<%--</div>--%>
