<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <TITLE>Sign up</TITLE>
    <%@include file="fragment/meta.jsp" %>
</head>
<%@include file="fragment/fmt_message.jsp" %>
<body>
<%@include file="fragment/header.jspf"%>
<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-8 col-md-8 mx-auto">
                <h2 class="lead text-muted">${reg_greeting}</h2>
                <p class="lead text-muted">${reg_promotion}</p>
            </div>

            <div class="text-secondary text-start col-md-7 col-lg-8 mx-auto">
                <form name="registration_form" action="${abs}/controller" method="post" novalidate autocomplete="off"
                     onsubmit="return validate()" >
                    <input type="hidden" name="command" value="create_user">

                    <div class="row g-3">
                        <div class="col-sm-6">
                            <label for="reg_name" class="form-label">${name}</label>
                            <input type="text" class="form-control" id="reg_name" name="name"
                                   placeholder="${name}" value="${user_name}" required autocomplete="off"
                                   pattern="${name_regexp}" maxlength="15">
                            <div class="invalid-feedback d-block">
                                <p id="reg_name_msg"></p>
                            </div>
                            <c:if test="${not empty incorrect_name}">
                                <p class="invalid-feedback d-block" id="incorrect_name_msg"><fmt:message key="${incorrect_name}" bundle="${localization}"/></p>
                            </c:if>
                        </div>

                        <div class="col-sm-6">
                            <label for="reg_surname" class="form-label">${surname}</label>
                            <input type="text" class="form-control" id="reg_surname" name="surname"
                                   placeholder="${surname}" value="${user_surname}" required autocomplete="off"
                                   pattern="${surname_regexp}">
                            <div class="invalid-feedback d-block">
                                <p id="reg_surname_msg"></p>
                            </div>
                            <c:if test="${not empty incorrect_surname}">
                                <p class="invalid-feedback d-block" id="incorrect_surname_msg"><fmt:message key="${incorrect_surname}"  bundle="${localization}" /></p>
                            </c:if>
                        </div>


                        <div class="col-12">
                            <label for="reg_login" class="form-label">${login}</label>
                            <input type="text" class="form-control" id="reg_login" name="login"
                                   value="${user_login}" placeholder="${login}" autocomplete="off"
                                   pattern="${login_regexp}" required>
                            <div class="invalid-feedback d-block">
                                <p id="reg_login_msg"></p>
                            </div>
                            <c:if test="${not empty incorrect_login}">
                                <p class="invalid-feedback d-block" id="incorrect_login_msg"><fmt:message key="${incorrect_login}" bundle="${localization}"/></p>
                            </c:if>
                        </div>

                        <div class="col-sm-6">
                            <label for="reg_password" class="form-label">${pass}</label>
                            <input type="password" class="form-control" id="reg_password"
                                   name="password"
                                   placeholder="${pass}" required autocomplete="off"
                                   pattern="${password_regexp}">
                            <div class="invalid-feedback d-block">
                                <p id="reg_password_msg"></p>
                            </div>
                            <c:if test="${not empty incorrect_password}">
                                <p class="invalid-feedback d-block" id="incorrect_password_msg"><fmt:message key="${incorrect_password}" bundle="${localization}"/></p>
                            </c:if>
                        </div>
                        <div class="col-sm-6">
                            <label for="reg_password_again" class="form-label">${pass_rep}</label>
                            <input type="password" class="form-control"
                                   id="reg_password_again"
                                   name="password_again"
                                   placeholder="${pass_rep}" required autocomplete="off"
                                   pattern="${password_regexp}">
                            <div class="invalid-feedback d-block">
                                <p id="reg_password_again_msg"></p>
                            </div>
                            <c:if test="${not empty incorrect_password_again}">
                                <p class="invalid-feedback d-block" id="incorrect_password_again_msg"><fmt:message key="${incorrect_password_again}" bundle="${localization}"/></p>
                            </c:if>
                        </div>

                        <div class="col-12">
                            <label for="reg_email" class="form-label">${email}</label>
                            <input type="email" class="form-control" id="reg_email" name="email"
                                   placeholder="you@example.com" value="${user_email}" required
                                   pattern="${email_regexp}">
                            <div class="invalid-feedback d-block">
                                <p id="reg_email_msg"></p>
                            </div>
                            <c:if test="${not empty incorrect_email}">
                                <p class="invalid-feedback d-block" id="incorrect_email_msg"><fmt:message key="${incorrect_email}" bundle="${localization}"/></p>
                            </c:if>
                        </div>

                        <div class="col-12">
                            <label for="reg_date_of_birth" class="form-label">${date}</label>
                            <input type="date" class="form-control" id="reg_date_of_birth" name="date_of_birth"
                                   value="${user_date_of_birth}" placeholder="${date}" autocomplete="off" required>
                            <div class="invalid-feedback d-block">
                                <p id="reg_date_of_birth_msg"></p>
                            </div>
                            <c:if test="${not empty incorrect_date_of_birth}">
                                <p class="invalid-feedback d-block" id="incorrect_date_of_birth_msg"><fmt:message key="${incorrect_date_of_birth}" bundle="${localization}"/></p>
                            </c:if>
                        </div>
                    </div>


                    <hr class="my-4">
                    <button class="w-100 align-content-lg-center btn btn-primary btn-lg"
                            type="submit">${create}</button>
                </form>

            </div>

        </div>

    </section>

</main>
<script type="text/javascript">
    function validate() {
        document.getElementById("incorrect_date_of_birth_msg").innerHTML = "";
        document.getElementById("incorrect_email_msg").innerHTML = "";
        document.getElementById("incorrect_login_msg").innerHTML = "";
        document.getElementById("incorrect_name_msg").innerHTML = "";
        document.getElementById("incorrect_surname_msg").innerHTML = "";
        document.getElementById("incorrect_password_again_msg").innerHTML = "";
        document.getElementById("incorrect_password_msg").innerHTML = "";

        var result = true;
        const passwordAgainValue = document.forms["registration_form"]["reg_password_again"];
        const passwordValue = document.forms["registration_form"]["reg_password"];
        const name = document.forms["registration_form"]["reg_name"];
        const surname = document.forms["registration_form"]["reg_surname"];
        const login = document.forms["registration_form"]["reg_login"];
        const email = document.forms["registration_form"]["reg_email"];
        const dateOfBirth = document.forms["registration_form"]["reg_date_of_birth"];

        if (passwordValue.value === "" || !passwordValue.checkValidity()) {
            document.getElementById("reg_password_msg").innerHTML = "${pass_req}";
            document.getElementById("reg_password").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("reg_password").classList.remove('is-invalid')
            document.getElementById("reg_password").classList.add('is-valid')
            document.getElementById("reg_password_msg").innerHTML = "";
        }

        if (!dateOfBirth.checkValidity()) {
            document.getElementById("reg_date_of_birth_msg").innerHTML = "${date_of_birth_req}";
            document.getElementById("reg_date_of_birth").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("reg_date_of_birth").classList.remove('is-invalid')
            document.getElementById("reg_date_of_birth").classList.add('is-valid')
            document.getElementById("reg_date_of_birth_msg").innerHTML = "";
        }

        if (passwordAgainValue.value !== passwordValue.value) {
            document.getElementById("reg_password_again_msg").innerHTML = "${passrep_req}";
            document.getElementById("reg_password_again").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("reg_password_again").classList.remove('is-invalid')
            document.getElementById("reg_password_again").classList.add('is-valid')
            document.getElementById("reg_password_again_msg").innerHTML = "";
        }

        if (name.value === "" || !name.checkValidity()) {
            document.getElementById("reg_name_msg").innerHTML = "${name_req}";
            document.getElementById("reg_name").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("reg_name").classList.remove('is-invalid')
            document.getElementById("reg_name").classList.add('is-valid')
            document.getElementById("reg_name_msg").innerHTML = "";
        }

        if (surname.value === "" || !surname.checkValidity()) {
            document.getElementById("reg_surname_msg").innerHTML = "${surname_req}";
            document.getElementById("reg_surname").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("reg_surname").classList.remove('is-invalid')
            document.getElementById("reg_surname").classList.add('is-valid')
            document.getElementById("reg_surname_msg").innerHTML = "";
        }

        if (email.value === "" || !email.checkValidity()) {
            document.getElementById("reg_email_msg").innerHTML = "${email_req}";
            document.getElementById("reg_email").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("reg_email").classList.remove('is-invalid')
            document.getElementById("reg_email").classList.add('is-valid')
            document.getElementById("reg_email_msg").innerHTML = "";
        }

        if (login.value === "" || !login.checkValidity()) {
            document.getElementById("reg_login_msg").innerHTML = "${login_req}";
            document.getElementById("reg_login").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("reg_login").classList.remove('is-invalid')
            document.getElementById("reg_login").classList.add('is-valid')
            document.getElementById("reg_login_msg").innerHTML = "";
        }

        return result;
    }
</script>
<%@include file="fragment/footer.jspf"%>
</body>
</html>
