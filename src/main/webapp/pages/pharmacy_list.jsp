<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <%@include file="fragment/meta.jsp" %>
    <link rel="stylesheet" type="text/css" href="${abs}/css/pharmacy_style.css">
    <title>Title</title>
</head>
<%@include file="fragment/fmt_message.jsp" %>
<body>
<%@include file="fragment/header.jspf" %>
<hr>
<div class="container bootstrap snippets bootdey">
    <div class="row">
        <div class="col-lg-12">
            <div class="main-box no-header clearfix">
                <div class="main-box-body clearfix">
                    <div class="table-responsive">
                        <table class="table user-list">
                            <thead>
                            <tr>
                                <th><span>Pharmacy</span></th>
                                <th><span>Address</span></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <span class="user-subhead">Member</span>
                                </td>
                                <td>
                                    <a href="#">marlon@brando.com</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
