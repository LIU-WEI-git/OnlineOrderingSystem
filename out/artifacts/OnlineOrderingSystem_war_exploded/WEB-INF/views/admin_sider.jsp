<%--
  Created by IntelliJ IDEA.
  User: owner
  Date: 2019/11/20
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<div class="sidebar" data-background-color="white" data-active-color="danger">

    <!--
		Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
		Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
	-->

    <div class="sidebar-wrapper">
        <div class="logo">
            <a href="#" class="simple-text">
               管理员菜单
            </a>
        </div>

        <ul class="nav">
            <li >
                <a href="dashboard.html">
                    <i class="ti-panel"></i>
                    <p>总览</p>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/user" />">
                    <i class="ti-user"></i>
                    <p>管理员</p>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/dishcategory"/>">
                    <i class="ti-view-list-alt"></i>
                    <p>菜品类</p>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/dish" />">
                    <i class="ti-text"></i>
                    <p>菜品</p>
                </a>
            </li>
            <li>
                <a href="icons.html">
                    <i class="ti-pencil-alt2"></i>
                    <p>订单</p>
                </a>
            </li>
            <li>
                <a href="maps.html">
                    <i class="ti-map"></i>
                    <p>Maps</p>
                </a>
            </li>
            <li>
                <a href="notifications.html">
                    <i class="ti-bell"></i>
                    <p>Notifications</p>
                </a>
            </li>
            <li class="active-pro">
                <a href="<c:url value="/admin/logout" />">
                    <i class="ti-export"></i>
                    <p>管理员登出</p>
                </a>
            </li>
        </ul>
    </div>
</div>
