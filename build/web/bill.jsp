<%-- 
    Document   : bill
    Created on : Mar 25, 2023, 10:12:57 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Khoản vay</title>
    </head>
    <body>
        <jsp:include page="index.jsp"/>
        <c:set var="a" value="${sessionScope.account}"/>
        <div style="width: 80%; float: right; margin-top: 15px">
            <input hidden name="accountid" value="${sessionScope.account.id}"/>
            <center>                
                <h3>DANH SÁCH KHOẢN VAY</h3>
                <c:set var="listloan" value="${requestScope.listloan}"/>
                <table border="1px">
                    <tr>
                        <th>Ngày vay</th>
                        <th>Số tiền vay</th>
                        <th>Thời gian vay (tháng)</th>
                        <th>Lãi suất (%/năm)</th>
                        <th>Trạng thái</th>
                        <th>Chi tiết</th>
                    </tr>
                    <c:forEach items="${requestScope.listloan}" var="loan">
                        <tr>
                            <td>${loan.begin_date}</td>
                            <td>${loan.amount} VNĐ</td>
                            <td>${loan.loan_time}</td>
                            <td>${loan.interest_rate}</td>
                            <td>${loan.state ? "Đã hoàn tất" : "Chưa hoàn tất"}</td>
                            <td><a href="detailbill?id=${loan.id}">Xem chi tiết</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </center>
        </div>
    </body>
</html>
