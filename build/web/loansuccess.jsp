<%-- 
    Document   : loansuccess
    Created on : Mar 31, 2023, 11:51:44 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vay lãi</title>
    </head>
    <body>
        <jsp:include page="index.jsp"/>
        <div style="width: 80%; float: right; font-size: 18px; margin-top: 15px;">
            <form action="loansuccess" method="post">
                <input hidden name="accountid" value="${sessionScope.account.id}">
                <h3 style="text-align: center">ĐĂNG KÝ VAY LÃI THÀNH CÔNG</h3><br>
                <div style="text-align: center">
                    <input type="submit" value="OK">
                </div>
            </form>
        </div>
    </body>
</html>
