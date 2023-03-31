<%-- 
    Document   : bill.jsp
    Created on : Mar 25, 2023, 8:54:54 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết khoản vay</title>
        <script>
            function doPay(id, loan_id, account_id) {
                if (confirm("Bạn muốn thanh toán lần trả lãi này?")) {
                    window.location="payment?id=" + id + "&lid=" + loan_id + "&aid=" + account_id;
                }
            }
        </script>
    </head>
    <body>
        <jsp:include page="index.jsp"/>
        <c:set var="a" value="${sessionScope.account}"/>
        <c:set var="l" value="${requestScope.loan}"/>
        <c:set var="listpayment" value="${requestScope.listpayment}"/>
        <div style="width: 80%; float: right; font-size: 18px; margin-left: 15px">
            <input hidden name="accountid" value="${sessionScope.account.id}"/>
            <center>
                <h3>THÔNG TIN VAY LÃI</h3>
                <table>
                    <tr>
                        <td>Số tiền vay: ${l.amount} VNĐ</td>
                        <td>Thời gian vay: ${l.loan_time} tháng</td>
                    </tr>
                    <tr>
                        <td>Lãi suất: ${l.interest_rate}%/năm</td>
                        <td>Thời gian bắt đầu: ${l.begin_date}</td>
                    </tr>
                    <tr>
                        <td>Tổng số tiền phải trả: ${requestScope.sum} VNĐ</td>
                        <td>Thời gian kết thúc: ${requestScope.end_time}</td>
                    </tr>
                </table>
                <small style="color: red">Thanh toán trước ngày 13 hàng tháng</small>
                <p style="color: red">${sessionScope.message != null ? sessionScope.message: ""}</p>                    
                <table border="1px" style="margin-top: 10px">
                    <tr>
                        <th>Tiền gốc (VNĐ)</th>
                        <th>Tiền lãi (VNĐ)</th>
                        <th>Phí phạt (VNĐ)</th>
                        <th>Tổng tiền (VNĐ)</th>
                        <th>Ngày đến hạn</th>
                        <th>Tiền trả (VNĐ)</th>
                        <th>Ngày trả</th>
                        <th>Trạng thái</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${listpayment}" var="lp">
                        <tr>
                            <td align="right">${lp.amount_per_month}</td>
                            <td align="right">${lp.interest_per_month}</td>
                            <td align="right">${lp.fine}</td>
                            <td align="right">${lp.amount_per_month + lp.interest_per_month + lp.fine}</td>
                            <td>${lp.payment_date}</td>
                            <td align="right">${lp.payment_amount}</td>
                            <td>${lp.pay_date}</td>
                            <td>${lp.state ? "Đã trả" : "Chưa trả"}</td>
                            <td><a href="#" onclick="doPay(${lp.id}, ${l.id}, ${a.id})" style="${lp.state ? 'pointer-events: none' : ''}">Thanh toán</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </center>
        </div>
    </body>
</html>
