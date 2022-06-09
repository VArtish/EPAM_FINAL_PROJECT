<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>Print out all users page</title>

</head>

<%@include file="fragment/fmt_message.jsp" %>

<body class="d-flex flex-column h-100 bg-dark">

<%@include file="fragment/header.jspf" %>
<c:if test="${requestScope.users == null}">
    <jsp:forward page="${abs}/controller?command=show_user_list"/>
</c:if>
<main class="flex-shrink-0">
    <div class="container-fluid" style="padding: 72px 0px 0;">
        <table class="table table-dark table-striped table-hover">
            <thead class="sticky-top" style="padding: 72px 0px 0;">
            <tr>
                <th></th>
                <th class="th-sm"><input type="text" id="myInput" onkeyup="myFunction()"
                                         placeholder="Search for logins.."></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Email</th>
                <th>Date of birth</th>
                <th>User role</th>
                <th>User state</th>
                <th>&nbsp</th>
            </tr>
            </thead>
            <tbody id="myTable">
            <jsp:useBean id="users" scope="request" type="java.util.List"/>
            <c:forEach items="${users}" var="user">

            <tr>
                <td>${user.getId()}</td>
                <td>${user.getLogin()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getDateOfBirth()}</td>
                <td>
                    <div class="dropdown">
                        <c:choose>
                            <c:when test="${user.getUserRole() eq 'ADMIN'}">
                                <button class="w-100 btn btn-danger btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getUserRole()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=ADMIN">ADMIN</a>
                                    </li>
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=DOCTOR">DOCTOR</a>
                                    </li>
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=PHARMACIST">PHARMACIST</a>
                                    </li>
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=CUSTOMER">CUSTOMER</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${user.getUserRole() eq 'DOCTOR'}">
                                <button class="w-100 btn btn-info btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getUserRole()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=ADMIN">ADMIN</a>
                                    </li>
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=DOCTOR">DOCTOR</a>
                                    </li>
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=PHARMACIST">PHARMACIST</a>
                                    </li>
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=CUSTOMER">CUSTOMER</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${user.getUserRole() eq 'PHARMACIST'}">
                                <button class="w-100 btn btn-info btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getUserRole()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=ADMIN">ADMIN</a>
                                    </li>
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=DOCTOR">DOCTOR</a>
                                    </li>
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=PHARMACIST">PHARMACIST</a>
                                    </li>
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=CUSTOMER">CUSTOMER</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${user.getUserRole() eq 'CUSTOMER'}">
                                <button class="w-100 btn btn-info btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getUserRole()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=ADMIN">ADMIN</a>
                                    </li>
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=DOCTOR">DOCTOR</a>
                                    </li>
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=PHARMACIST">PHARMACIST</a>
                                    </li>
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=CUSTOMER">CUSTOMER</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </td>
                <td>
                    <div class="dropdown">
                        <c:choose>
                            <c:when test="${user.getUserState() == 'ENABLED'}">
                                <button class="w-100 btn btn-success btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getUserState()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=ENABLED">ENABLED</a>
                                    </li>
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=BANNED">BANNED</a>
                                    </li>
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=DISABLED">DISABLED</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${user.getUserState() eq 'BANNED'}">
                                <button class="w-100 btn btn-danger btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getUserState()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=ENABLED">ENABLED</a>
                                    </li>
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=BANNED">BANNED</a>
                                    </li>
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=DISABLED">DISABLED</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${user.getUserState() eq 'DISABLED'}">
                                <button class="w-100 btn btn-secondary btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getUserState()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=ENABLED">ENABLED</a>
                                    </li>
                                    <li><a class="dropdown-item active"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=BANNED">BANNED</a>
                                    </li>
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=DISABLED">DISABLED</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </td>
                <td>
                    <a href="${abs}/controller?command=delete_user&user_login=${user.login}">
                        <button type="button" class="close" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </a>
                </td>
            </tr>
            </c:forEach>

        </table>
        <br/>

        <c:choose>
            <c:when test="${users.size() > 0}">
                ${users.size()} ${rec_found}<br/>
            </c:when>
            <c:otherwise>
                ${no_rec_found}<br/>
            </c:otherwise>
        </c:choose>
    </div>
</main>


<%@include file="fragment/footer.jspf" %>

</body>
</html>
