<%--
    Document   : calculation
    Created on : Mar 18, 2023, 11:47:39 PM
    Author     : Admin
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vay lãi</title>
        <script>
            function check() {
                var a = document.loanform.amount.value;
                if (a == "") {
                    document.getElementById("error").innerHTML = "Chưa nhập số tiền";
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <jsp:include page="index.jsp"/>
        <div style="width: 80%; float: right; font-size: 18px; margin-top: 15px">
            <c:if test="${requestScope.error != null}">
                <h3>${requestScope.error}</h3>
            </c:if>
            <h3 style="text-align: center">ĐĂNG KÍ VAY LÃI</h3>
            <br>
            <form action="loan" name="loanform" method="post" onsubmit="return check()">
                <input hidden name="accountid" value="${sessionScope.account.id}"/>
                <label for="input_time">Thu nhập hàng tháng của bạn (*):</label>
                <select id="input_time" name="salary">
                    <option value="5_under">Dưới 5 triệu đồng</option>
                    <option value="5_10_between">Từ 5 triệu đến 10 triệu đồng</option>
                    <option value="10_higher">Trên 10 triệu đồng</option>
                </select>
                &emsp;
                <label for="input_method">Hình thức trả lương (*):</label>
                <select id="input_method" name="method">
                    <option value="This_bank">Trả lương qua ngân hàng này</option>
                    <option value="Other_bank">Trả lương qua ngân hàng khác</option>
                    <option value="Cash">Tiền mặt</option>
                </select>
                &emsp;
                <br>
                <br>
                <label for="input_amount">Số tiền muốn vay (*): </label>
                <input type="text" id="input_amount" name="amount" />
                &nbsp; &nbsp; &nbsp;
                <label for="input_time">Thời gian vay (*):</label>
                <select id="input_time" name="loan_time">
                    <option value="3">3 tháng</option>
                    <option value="6">6 tháng</option>
                    <option value="9">9 tháng</option>
                    <option value="12">12 tháng</option>
                </select>
                &emsp;
                <br>
                <h4 style="color: crimson; text-align: center" id="error"></h4>
                <br>
                <div style="text-align: center">
                    <input type="submit" value="ĐĂNG KÝ VAY" />
                </div>
            </form>
        </div>
    </body>
</html>
