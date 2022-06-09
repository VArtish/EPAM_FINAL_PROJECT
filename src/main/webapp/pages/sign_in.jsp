<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign in</title>
    <link href="${abs}/css/signin.css" rel="stylesheet">
    <link href="${abs}/css/bookmasrk_style.css" rel="stylesheet">
    <%@include file="fragment/meta.jsp" %>
</head>
<%@include file="fragment/fmt_message.jsp" %>
<body class="text-center">

<main class="form-signin">
    <form action="${abs}/controller" name="sign_in_form" onsubmit="return validate()" method="post" novalidate>
        <input type="hidden" name="command" value="login">
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
        <c:if test="${not empty incorrect_sign_in_data}">
            <p class="invalid-feedback d-block" id="incorrect_sign_in_msg"><fmt:message key="${incorrect_sign_in_data}"
                                                                                      bundle="${localization}"/></p>
        </c:if>
        <div class="form-floating">
            <input type="text" class="form-control" id="sign_in_login" name="login" value="${user_login}"
                   placeholder="${login}" pattern="${login_regexp}">
            <label for="sign_in_login">${login}</label>
            <div class="invalid-feedback d-block">
                <p id="sign_in_login_msg"></p>
            </div>
            <c:if test="${not empty incorrect_login}">
                <p class="invalid-feedback d-block" id="incorrect_login_msg"><fmt:message key="${incorrect_login}"
                                                                                          bundle="${localization}"/></p>
            </c:if>
        </div>

        <div class="form-floating">
            <input type="password" class="form-control" id="sign_in_password" name="password" placeholder="${pass}"
                   pattern="${password_regexp}">
            <label for="sign_in_password">${pass}</label>
            <div class="invalid-feedback d-block">
                <p id="sign_in_password_msg"></p>
            </div>
            <c:if test="${not empty incorrect_password}">
                <p class="invalid-feedback d-block" id="incorrect_password_msg"><fmt:message key="${incorrect_password}"
                                                                                             bundle="${localization}"/></p>
            </c:if>
        </div>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
        <br/>
        <br/>
    </form>
    <button class="w-100 btn btn-lg btn-secondary">
        <a class="w-100 btn btn-lg btn-secondary" href="${abs}/controller?command=go_to_sign_up_page">Sign up</a>
    </button>

</main>
<script type="text/javascript">
    function validate() {
        var result = true;
        const name = document.forms["sign_in_form"]["sign_in_login"];
        const password = document.forms["sign_in_form"]["sign_in_password"];

        if (password.value === "" || !password.checkValidity()) {
            document.getElementById("sign_in_password_msg").innerHTML = "${pass_req}";
            document.getElementById("sign_in_password").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("sign_in_password").classList.remove('is-invalid')
            document.getElementById("sign_in_password").classList.add('is-valid')
            document.getElementById("sign_in_password_msg").innerHTML = "";
        }


        if (name.value === "" || !name.checkValidity()) {
            document.getElementById("sign_in_login_msg").innerHTML = "${login_req}";
            document.getElementById("sign_in_login").classList.add('is-invalid')
            result = false;
        } else {
            document.getElementById("sign_in_login").classList.remove('is-invalid')
            document.getElementById("sign_in_login").classList.add('is-valid')
            document.getElementById("sign_in_login_msg").innerHTML = "";
        }

        return result;
    }
</script>
<%@include file="fragment/footer.jspf"%>
</body>
</html>
