<%-- 
    Document   : contract
    Created on : Mar 31, 2023, 10:30:44 AM
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
        <c:set var="account" value="${sessionScope.account}"/>
        <div style="width: 80%; float: right; font-size: 18px; margin-top: 15px;">
            <c:if test="${requestScope.error != null}">
                <h3>${requestScope.error}</h3>
            </c:if>
            <form action="contract" method="post">
                <input hidden name="accountid" value="${sessionScope.account.id}">
                <h3 style="text-align: center">HỢP ĐỒNG VAY LÃI</h3><br>
                Họ và tên: 
                <input name="name" value="${account.customer.name}" readonly size="50"><br>
                Giới tính: 
                <input name="gender" value="${(account.customer.gender ? "Nam" : "Nữ")}" readonly size="50"><br>
                Ngày sinh: 
                <input name="dob" value="${account.customer.dob}" readonly size="50"><br>
                Email: 
                <input name="email" value="${account.customer.email}" readonly size="50"><br>
                Số điện thoại: 
                <input name="phone" value="${account.customer.phonenumber}" readonly size="50"><br>
                Địa chỉ: 
                <input name="address" value="${account.customer.address}" readonly size="50"><br>
                <h3 style="text-align: center">THÔNG TIN TÍN DỤNG</h3><br>
                Thu nhập hàng tháng: 
                <input name="salary" value="${requestScope.salary}" readonly size="50"> VNĐ<br>
                Số tiền muốn vay: 
                <input name="amount" value="${requestScope.amount}" readonly size="50"> VNĐ<br>
                Lãi suất: 
                <input name="interest" value="${requestScope.interest}" readonly size="50">%<br>
                Thời hạn vay: 
                <input name="time" value="${requestScope.time}" readonly size="50"> tháng<br>
                Hình thức trả lương: 
                <input name="method" value="${requestScope.method}" readonly size="50"><br>
                <p style="text-align: center">Nhấn nút ĐỒNG Ý có nghĩa bạn đồng ý với điều khoản của chúng tôi</p>
                <p style="text-align: center">Ngân hàng sẽ liên lạc với bạn sau 24 giờ làm việc để thông báo kết quả duyệt hồ sơ</p>
                <div style="text-align: center">
                    <input type="submit" value="ĐỒNG Ý">
                </div>
            </form>
        </div>
    </body>
</html>
