<%-- 
    Document   : information.jsp
    Created on : Mar 30, 2023, 9:58:54 PM
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
                var name = document.informationform.name.value;
                var address = document.informationform.address.value;
                var dob = document.informationform.dob.value;
                var gender = document.informationform.gender.value;
                var id_card = document.informationform.id_card.value;
                var phone = document.informationform.phone.value;
                var email = document.informationform.email.value;
                if (name == "") {
                    document.getElementById("error").innerHTML = "Chưa nhập tên";
                    return false;
                }
                if (address == "") {
                    document.getElementById("error").innerHTML = "Chưa nhập địa chỉ";
                    return false;
                }
                if (dob == "") {
                    document.getElementById("error").innerHTML = "Chưa nhập ngày sinh";
                    return false;
                }
                if (gender == null) {
                    document.getElementById("error").innerHTML = "Chưa chọn giới tính";
                    return false;
                }
                if (id_card == "") {
                    document.getElementById("error").innerHTML = "Chưa nhập số CCCD";
                    return false;
                }
                if (phone == "") {
                    document.getElementById("error").innerHTML = "Chưa nhập số điện thoại";
                    return false;
                }
                if (email == "") {
                    document.getElementById("error").innerHTML = "Chưa nhập email";
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <div class="container">
            <div class="left">
                <div id="for_show_1">1. Thông tin đăng nhập</div>
                <br>
                <div id="for_show_2" style="color: red">2. Cung cấp thông tin cá nhân</div>
                <br>
                <div id="for_show_3">3. Đăng kí thành công</div>
            </div>
            <div class="right">
                <div>
                    <h1 class="register-form_heading" style="text-align: center">KHAI BÁO THÔNG TIN CÁ NHÂN</h1>
                    <form class="register" action="information" name="informationform" method="post" onsubmit="return check()">
                        <div class="register-form_form">
                            <input hidden name="username" value="${requestScope.username}">
                            <input hidden name="password" value="${requestScope.password}">
                            <div class="register-form_group_2">
                                <label for="ln">Họ và tên: </label><br>
                                <input type="text" style="width: 370px" id="ln"class="register-form_input" name="name">
                            </div>
                            <div class="register-form_group_2">
                                <label for="la">Địa chỉ: </label><br>
                                <input type="text" style="width: 370px" id="la" class="register-form_input" name="address">
                            </div>
                            <div class="register-form_DOB_and_S">
                                <div class="register-form_DOB">
                                    <label for="ldob">Ngày tháng năm sinh (yyyy-MM-dd): </label><br>
                                    <input type="date" style="width: 373px" id="ldob" class="register-form_DOB_input" name="dob">
                                </div>
                                <div class="register_sex">
                                    <label>Giới tính: </label>
                                    <input type="radio" class="gender" id="male" value="1" name="gender" checked> Nam
<!--                                    <label class="gender" for="nam">Nam</label>-->
                                    <input type="radio" class="gender" id="female" value="0" name="gender"> Nữ
<!--                                    <label class="gender" for="nu">Nữ</label>-->
                                </div>
                            </div>
                            <div class="register-form_group_2">
                                <label for="lid">Số CCCD: </label><br>
                                <input type="text" style="width: 370px" id="lid" class="register-form_input" name="id_card">
                            </div>
                            <div class="register-form_group_2">
                                <label for="lp">Số điện thoại: </label><br>
                                <input type="text" style="width: 370px" id="lp" class="register-form_input" name="phone">
                            </div>
                            <div class="register-form_group_2">
                                <label for="le">Email: </label><br>
                                <input type="text" style="width: 370px" id="le" class="register-form_input" name="email">
                            </div>
                        </div>
                        <br>
                        <div class="register-footer">
                            <div style="text-align: center" class="register_condition">
                                <label>Nhấn nút ĐĂNG KÝ có nghĩa là bạn đã đồng ý với điều khoản ngân hàng</label>
                            </div>
                        </div>
                        <h4 style="color: red; text-align: center" id="error"></h4>
                        <c:if test="${requestScope.message != null}">
                            <h4 style="color: crimson; text-align: center">${requestScope.message}</h4>
                        </c:if>
                        <div style="text-align: center" class="register-form_button">
                            <input class="register_button" type="submit" value="ĐĂNG KÝ">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
