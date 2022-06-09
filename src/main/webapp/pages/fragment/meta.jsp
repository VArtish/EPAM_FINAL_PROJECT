<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<link rel="stylesheet" href="${abs}/css/bootstrap.min.css">

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization/locale" var="localization"/>
<fmt:setBundle basename="regexp" var="regexp"/>