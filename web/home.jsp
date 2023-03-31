<%-- 
    Document   : home.jsp
    Created on : Mar 21, 2023, 8:35:40 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ</title>
    </head>
    <body>
        <jsp:include page="index.jsp"/>
        <c:set var="a" value="${sessionScope.account}"/>
        <div style="width: 80%; float: right; font-size: 18px;">
            <input hidden name="accountid" value="${sessionScope.account.id}"/>
            <h2>Xin chào: ${a.customer.name}</h2>
            <h3>Tài khoản: ${a.number}</h3>
            <h3>Số dư: ${a.balance} VNĐ</h3>
        </div>
    </body>
</html>
