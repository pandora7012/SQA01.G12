<%-- 
    Document   : base
    Created on : Mar 18, 2023, 11:26:55 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css"/>
        <title>Ngân hàng</title>
    </head>
    <body>
        <div class="sidebar">
            <c:if test="${sessionScope.account != null}">
            <a href="home">TRANG CHỦ</a>
            <a href="#">CHUYỂN KHOẢN</a>
            <a href="bill">HÓA ĐƠN</a>
            <a href="loan">VAY TIÊU DÙNG</a>
            <a href="calculation">TRA CỨU</a>
            <a href="logout">ĐĂNG XUẤT</a>
            </c:if>
            <c:if test="${sessionScope.account == null}">
            <a href="login">ĐĂNG NHẬP</a>
            </c:if>
        </div>
    </body>
</html>
