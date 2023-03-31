<%-- 
    Document   : register
    Created on : Mar 30, 2023, 9:38:41 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Đăng ký tài khoản</title>
        <link rel="stylesheet" href="css/basic.css">
        <script>
            function check() {
                var u = document.registerform.Username.value;
                var p = document.registerform.Password.value;
                var rp = document.registerform.SamePassword.value;
                if (u == "") {
                    document.getElementById("error").innerHTML = "Chưa nhập tên người dùng";
                    return false;
                }
                if (p == "") {
                    document.getElementById("error").innerHTML = "Chưa nhập mật khẩu";
                    return false;
                }
                if (rp == "") {
                    document.getElementById("error").innerHTML = "Nhập lại mật khẩu";
                    return false;
                }
                if (p != rp) {
                    document.getElementById("error").innerHTML = "Mật khẩu không khớp";
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <div class="container">
            <div class="left">
                <div id="for_show_1" style="color: red">1. Thông tin đăng nhập</div>
                <br>
                <div id="for_show_2">2. Cung cấp thông tin cá nhân</div>
                <br>
                <div id="for_show_3">3. Đăng kí thành công</div>
            </div>
            <div class="right">
                <div>
                    <h1 class="register-form_heading" style="text-align: center">TẠO TÀI KHOẢN ĐĂNG NHẬP TRỰC TUYẾN</h1>
                    <form class="register" action="register" name="registerform" method="post" onsubmit="return check()">
                        <div class="register-form_form">
                            <div class="register-form_group">
                                <label for="un">Tên người dùng: </label><br>
                                <input type="text" style="width: 370px" id="un" class="register-form_input" name="username">
                            </div>
                            <br>
                            <div class="register-form_group">
                                <label for="pw">Mật khẩu: </label><br>
                                <input type="password" style="width: 370px" id="pw" class="register-form_input" name="password">
                            </div>
                            <br>
                            <div class="register-form_group">
                                <label for="spw">Nhập lại mật khẩu: </label><br>
                                <input type="password" style="width: 370px" id="spw" class="register-form_input" name="samepassword">
                            </div>
                            <br>
                            <h4 style="color: crimson; text-align: center" id="error"></h4>
                            <c:if test="${requestScope.message != null}">
                                <h4 style="color: crimson; text-align: center">${requestScope.message}</h4>
                            </c:if>
                        </div>
                        <div style="text-align: center" class="register-form_button">
                            <input class="register_button" type="submit" value="Đăng ký">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>