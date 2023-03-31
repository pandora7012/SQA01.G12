<%-- 
    Document   : login
    Created on : Mar 30, 2023, 9:21:15 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <link rel="stylesheet" href="css/login.css">
        <script>
            function check() {
                var u = document.loginform.username.value;
                var p = document.loginform.password.value;
                if (u == "") {
                    document.getElementById("error").innerHTML = "Chưa nhập tên người dùng";
                    return false;
                }
                if (p == "") {
                    document.getElementById("error").innerHTML = "Chưa nhập mật khẩu";
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <div>
            <div class="auth-form">
                <div class="auth-form_header">
                    <h1 class="auth-form_heading" style="text-align: center">ĐĂNG NHẬP</h1>
                </div>
                <div class="login">
                    <form action="login" name="loginform" method="post" onsubmit="return check()">
                        <div class="auth-form_form">
                            <div class="auth-form_group">
                                <label for="un">Tên người dùng: </label><br>
                                <input style="width: 370px" type="text" id="un" class="auth-form_input" name="username">
                            </div>
                            <br/>
                            <div class="auth-form_group">
                                <label for="pw">Mật khẩu: </label><br>
                                <input style="width: 370px" type="password" id="pw" class="auth-form_input" name="password">
                            </div>
                        </div>
                        <br/>
                        <div style="text-align: center" class="auth-form_button">
                            <input class="auth_button" type="submit" value="ĐĂNG NHẬP"/>
                        </div>
                    </form>
                    <br>
                    <h4 style="color: crimson; text-align: center" id="error"></h4>
                    <c:if test="${requestScope.message != null}">
                          <h4 style="color: crimson; text-align: center">${requestScope.message}</h4>
                    </c:if>
                    <br/>
                    <div class="auth-form_footer" style="text-align: center">
                        <label class="to_register" for="r">Bạn chưa có tài khoản?</label>&nbsp;&nbsp;<a id="r" style="color: white" href="register">ĐĂNG KÝ</a>
                    </div>
            </div>
        </div>
    </div>
</body>
</html>
