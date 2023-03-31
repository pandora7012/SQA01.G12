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
        <title>Tham khảo vay lãi</title>
    </head>
    <body>
        <jsp:include page="index.jsp"/>
        <div style="width: 80%; float: right; margin-top: 15px">
            <input hidden name="accountid" value="${sessionScope.account.id}"/>
            <center>
                <h3>THAM KHẢO VAY LÃI</h3>
                <c:if test="${requestScope.error != null}">
                    <p style="color: red">${requestScope.error}</p>
                </c:if>
                    <form action="calculation" name="calculationform" method="post">
                    <label for="input_amount">Số tiền muốn vay: </label>
                    <input type="text" id="input_amount" name="amount" />
                    &nbsp; &nbsp; &nbsp;
                    <label for="input_time">Thời gian vay: </label>
                    <select id="input_time" name="time">
                        <option value="3">3 tháng</option>
                        <option value="6">6 tháng</option>
                        <option value="9">9 tháng</option>
                        <option value="12">12 tháng</option>
                    </select>
                    &nbsp; &nbsp; &nbsp;
                    <input type="submit" value="TÍNH" />
                </form>
                <c:if test="${requestScope.interest != null && requestScope.time != null}">
                    <p>Lãi suất hiện tại: ${requestScope.interest}%/${requestScope.time} tháng</p>
                </c:if>
                <c:if test="${requestScope.interest_per_month != null}">
                    <p>Tiền lãi hàng tháng: ${requestScope.interest_per_month} VNĐ</p>
                </c:if>
                <c:if test="${requestScope.root_per_month != null}">    
                    <p>Tiền gốc hàng tháng: ${requestScope.root_per_month} VNĐ</p>
                </c:if>
                <c:if test="${requestScope.total_per_month != null}">    
                    <p>Tổng tiền hàng tháng: ${requestScope.total_per_month} VNĐ</p>
                </c:if>
            </center>
        </div>
    </body>
</html>
