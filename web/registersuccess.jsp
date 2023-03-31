<%-- 
    Document   : registersuccess
    Created on : Mar 30, 2023, 10:37:40 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Đăng ký tài khoản</title>
        <link rel="stylesheet" href="css/basic.css">
    </head>
    <body>
        <div class="container">
            <div class="left">
                <div id="for_show_1">1. Thông tin đăng nhập</div>
                <br>
                <div id="for_show_2">2. Cung cấp thông tin cá nhân</div>
                <br>
                <div id="for_show_3" style="color: red">3. Đăng kí thành công</div>
            </div>
            <div class="right">
                <div>     
                    <form class="register" action="registersuccess" method="post" onsubmit="return check()">
                        <h1 class="register-form_heading" style="text-align: center">ĐĂNG KÝ THÀNH CÔNG</h1>
                        <div class="register-form_form">
                            <input hidden name="customerusername" value="${requestScope.customer.username}">
                        <div style="text-align: center" class="register-form_button">
                            <input class="register_button" type="submit" value="HOÀN TẤT">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
