<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Profile</title>
    <%@include file="fragment/meta.jsp" %>
</head>
<%@include file="fragment/fmt_message.jsp" %>
<body>
<c:if test="${requestScope.user_name == null}">
    <jsp:forward page="${abs}/controller?command=show_user_cabinet"/>
</c:if>
<%@include file="fragment/header.jspf" %>
<div class="page">
    <div class="row">
        <h3 class="text-center p-3">My cabinet</h3>
        <form role="form" action="${abs}/controller" method="post" name="user_cabinet_form"
              onsubmit="return validateUserProfile()" novalidate>

            <input type="hidden" name="command" value="update_user_profile">
            <div class="container col-12 col-sm-6 mt-3">
                <dl class="row my-3">
                    <dt class="col-2 mb-3">
                        ${name}
                    </dt>
                    <div class="form-group col-10 mb-3">
                        <input type="text" name="name" id="uc_name" value="${requestScope.user_name}"
                               class="form-control"
                               placeholder="${name}" required pattern="${name_regexp}">
                        <div class="invalid-feedback d-block">
                            <p id="cabinet_name_msg"></p>
                        </div>
                        <c:if test="${not empty incorrect_name}">
                            <p class="invalid-feedback d-block" id="incorrect_name_msg"><fmt:message
                                    key="${incorrect_name}" bundle="${localization}"/></p>
                        </c:if>
                    </div>
                    <dt class="col-2 mb-3">
                        ${surname}
                    </dt>
                    <div class="form-group col-10 mb-3">
                        <input type="text" name="surname" id="uc_surname" value="${requestScope.user_surname}"
                               class="form-control"
                               placeholder="${surname}" required pattern="${surname_regexp}">
                        <div class="invalid-feedback d-block">
                            <p id="cabinet_surname_msg"></p>
                        </div>
                        <c:if test="${not empty incorrect_surname}">
                            <p class="invalid-feedback d-block" id="incorrect_surname_msg"><fmt:message
                                    key="${incorrect_surname}" bundle="${localization}"/></p>
                        </c:if>
                    </div>
                    <dt class="col-2 mb-3">
                        ${email}
                    </dt>
                    <div class="form-group col-10 mb-3">
                        <input type="email" name="email" id="uc_email" value="${requestScope.user_email}"
                               class="form-control"
                               placeholder="${email}" required
                               pattern="${email_regexp}">
                        <div class="invalid-feedback d-block">
                            <p id="cabinet_email_msg"></p>
                        </div>
                        <c:if test="${not empty incorrect_email}">
                            <p class="invalid-feedback d-block" id="incorrect_email_msg"><fmt:message
                                    key="${incorrect_email}" bundle="${localization}"/></p>
                        </c:if>
                        <c:if test="${not empty user_email_exist}">
                            <p class="invalid-feedback d-block" id="incorrect_email_exist_msg"><fmt:message
                                    key="${user_email_exist}" bundle="${localization}"/></p>
                        </c:if>
                    </div>
                    <dt class="col-2 mb-3">
                        ${date}
                    </dt>
                    <div class="form-group col-10 mb-3">
                        <input type="date" name="date_of_birth" id="uc_date_of_birth"
                               value="${requestScope.user_date_of_birth}" class="form-control"
                               placeholder="${date}" required readonly>
                        <div class="invalid-feedback d-block">
                            <p id="cabinet_date_of_birth_msg"></p>
                        </div>
                        <c:if test="${not empty incorrect_date_of_birth}">
                            <p class="invalid-feedback d-block" id="incorrect_date_of_birth_msg"><fmt:message
                                    key="${incorrect_date_of_birth}" bundle="${localization}"/></p>
                        </c:if>
                    </div>
                    <dt class="col-2 mb-3">
                        ${login}
                    </dt>
                    <div class="form-group col-10 mb-3">
                        <input type="text" name="login" id="uc_login" value="${requestScope.user_login}"
                               class="form-control" placeholder="${login}" required
                               pattern="${login_regexp}">
                        <div class="invalid-feedback d-block">
                            <p id="cabinet_login_msg"></p>
                        </div>
                        <c:if test="${not empty incorrect_login}">
                            <p class="invalid-feedback d-block" id="incorrect_login_msg"><fmt:message
                                    key="${incorrect_login}" bundle="${localization}"/></p>
                        </c:if>
                        <c:if test="${not empty user_login_exist}">
                            <p class="invalid-feedback d-block" id="incorrect_login_exist_msg"><fmt:message
                                    key="${user_login_exist}" bundle="${localization}"/></p>
                        </c:if>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-success">${update_user_cabinet}</button>
                    </div>
                </dl>
            </div>
        </form>

        <h3 class="text-center p-3">${change_password_title}</h3>
        <c:if test="${not empty successful_change_password}">
            <h2 class="valid-feedback d-block text-center" id="successful_change_password"><fmt:message
                    key="${successful_change_password}" bundle="${localization}"/></h2>
        </c:if>
        <form role="form" action="${abs}/controller" name="change_password_form" method="post"
              onsubmit="return validateChangePassword()" novalidate>
            <input type="hidden" name="command" value="change_password">
            <div class="container col-12 col-sm-6 mt-3">
                <dl class="row my-3">
                    <div class="form-group mb-3">
                        <label class="form-label">${enter_old_password}</label>
                        <input type="password" name="old_password" class="form-control" id="old_password"
                               required pattern="${password_regexp}">
                        <div class="invalid-feedback d-block">
                            <p id="old_password_msg"></p>
                        </div>
                        <c:if test="${not empty incorrect_old_password}">
                            <p class="invalid-feedback d-block" id="incorrect_old_password_msg"><fmt:message
                                    key="${incorrect_old_password}" bundle="${localization}"/></p>
                        </c:if>
                    </div>
                    <div class="form-group mb-3">
                        <label class="form-label">${enter_new_password}</label>
                        <input type="password" name="new_password" id="new_password" class="form-control"
                               required pattern="${password_regexp}">
                        <div class="invalid-feedback d-block">
                            <p id="new_password_msg"></p>
                        </div>
                        <c:if test="${not empty incorrect_new_password}">
                            <p class="invalid-feedback d-block" id="incorrect_new_password_msg"><fmt:message
                                    key="${incorrect_new_password}" bundle="${localization}"/></p>
                        </c:if>
                    </div>
                    <div class="form-group mb-3">
                        <label class="form-label">${enter_new_password_again}</label>
                        <input type="password" name="repeat_new_password" id="repeat_new_password"
                               class="form-control" required pattern="${password_regexp}">
                        <div class="invalid-feedback d-block">
                            <p id="repeat_new_password_msg"></p>
                        </div>
                        <c:if test="${not empty incorrect_repeat_new_password}">
                            <p class="invalid-feedback d-block" id="incorrect_repeat_new_password_msg"><fmt:message
                                    key="${incorrect_repeat_new_password}" bundle="${localization}"/></p>
                        </c:if>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-success">${change_password_title}</button>
                    </div>
                </dl>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    function validateChangePassword() {
        var result = true;
        const oldPassword = document.forms["change_password_form"]["old_password"];
        const newPassword = document.forms["change_password_form"]["new_password"];
        const repeatNewPassword = document.forms["change_password_form"]["repeat_new_password"];

        if (oldPassword.value === "" || !oldPassword.checkValidity()) {
            document.getElementById("old_password_msg").innerHTML = "${name_req}";
            document.getElementById("old_password").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("old_password").classList.remove('is-invalid')
            document.getElementById("old_password").classList.add('is-valid')
            document.getElementById("old_password_msg").innerHTML = "";
        }

        if (newPassword.value === "" || !newPassword.checkValidity()) {
            document.getElementById("new_password_msg").innerHTML = "${name_req}";
            document.getElementById("new_password").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("new_password").classList.remove('is-invalid')
            document.getElementById("new_password").classList.add('is-valid')
            document.getElementById("new_password_msg").innerHTML = "";
        }

        if (repeatNewPassword.value === "" || !repeatNewPassword.checkValidity()) {
            document.getElementById("repeat_new_password_msg").innerHTML = "${name_req}";
            document.getElementById("repeat_new_password").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("repeat_new_password").classList.remove('is-invalid')
            document.getElementById("repeat_new_password").classList.add('is-valid')
            document.getElementById("repeat_new_password_msg").innerHTML = "";
        }

        return result;
    }
</script>
<script type="text/javascript">
    function validateUserProfile() {

        const name = document.forms["user_cabinet_form"]["uc_name"];
        const surname = document.forms["user_cabinet_form"]["uc_surname"];
        const login = document.forms["user_cabinet_form"]["uc_login"];
        const email = document.forms["user_cabinet_form"]["uc_email"];
        const dateOfBirth = document.forms["user_cabinet_form"]["uc_date_of_birth"];

        if (!dateOfBirth.checkValidity()) {
            document.getElementById("cabinet_date_of_birth_msg").innerHTML = "${date_of_birth_req}";
            document.getElementById("uc_date_of_birth").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("uc_date_of_birth").classList.remove('is-invalid')
            document.getElementById("uc_date_of_birth").classList.add('is-valid')
            document.getElementById("cabinet_date_of_birth_msg").innerHTML = "";
        }

        if (name.value === "" || !name.checkValidity()) {
            document.getElementById("cabinet_name_msg").innerHTML = "${name_req}";
            document.getElementById("uc_name").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("uc_name").classList.remove('is-invalid')
            document.getElementById("uc_name").classList.add('is-valid')
            document.getElementById("cabinet_name_msg").innerHTML = "";
        }

        if (surname.value === "" || !surname.checkValidity()) {
            document.getElementById("cabinet_surname_msg").innerHTML = "${surname_req}";
            document.getElementById("uc_surname").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("uc_surname").classList.remove('is-invalid')
            document.getElementById("uc_surname").classList.add('is-valid')
            document.getElementById("cabinet_surname_msg").innerHTML = "";
        }

        if (email.value === "" || !email.checkValidity()) {
            document.getElementById("cabinet_email_msg").innerHTML = "${email_req}";
            document.getElementById("uc_email").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("uc_email").classList.remove('is-invalid')
            document.getElementById("uc_email").classList.add('is-valid')
            document.getElementById("cabinet_email_msg").innerHTML = "";
        }

        if (login.value === "" || !login.checkValidity()) {
            document.getElementById("cabinet_login_msg").innerHTML = "${login_req}";
            document.getElementById("uc_login").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("uc_login").classList.remove('is-invalid')
            document.getElementById("uc_login").classList.add('is-valid')
            document.getElementById("cabinet_login_msg").innerHTML = "";
        }

        return result;
    }
</script>
<%@include file="fragment/footer.jspf" %>
</body>
</html>