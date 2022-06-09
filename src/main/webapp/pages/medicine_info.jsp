<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>${medicine.name}</title>

</head>

<%@include file="fragment/fmt_message.jsp" %>

<body class="d-flex flex-column h-100 bg-light">

<%@include file="fragment/header.jspf" %>

<main>
    <c:if test="${medicine == null}">
        <jsp:forward page="${abs}/controller?command=show_more_info_medicine"/>
    </c:if>
    <div class="container col-12 col-sm-6 mt-3">
        <dl class="row my-3" height="200px">
            <img src="${medicine.imageLink}" class="figure-img img-fluid rounded">
        </dl>
        <dl class="row my-3">
            <dt class="col-4 mb-3">
                ${medicine_name}:
            </dt>
            <div class="form-group col-10 mb-3">
                <label> ${medicine.name}</label>
            </div>
            <dt class="col-4 mb-3">
                ${medicine_ingredients}:
            </dt>
            <div class="form-group col-10 mb-3">
                <label>${medicine.ingredients}</label>
            </div>
            <dt class="col-4 mb-3">
                ${medicine_producing_country}:
            </dt>
            <div class="form-group col-10 mb-3">
                <label>${medicine.producingCountry}
            </div>
            <dt class="col-4 mb-3">
                ${amount_in_plate}:
            </dt>
            <div class="form-group col-10 mb-3">
                <label>${medicine.amountInPlate}</label>
            </div>
            <dt class="col-4 mb-3">
                ${amount_in_package}:
            </dt>
            <div class="form-group col-10 mb-3">
                <label>${medicine.amountInPackage}</label>
            </div>
            <dt class="col-4 mb-3">
                ${medicine_need_prescription}:
            </dt>
            <div class="form-group col-10 mb-3">
                <c:choose>
                    <c:when test="${medicine.needPrescription}">
                        <label>+</label>
                    </c:when>
                    <c:otherwise>
                        <label>$-</label>
                    </c:otherwise>
                </c:choose>
            </div>
            <dt class="col-4 mb-3">
                ${medicine_price}:
            </dt>
            <div class="form-group col-10 mb-3">
                <label>${medicine.price}</label>
            </div>
            <dt class="col-4 mb-3">
                ${medicine_quantity}:
            </dt>
            <div class="form-group col-10 mb-3">
                <label>${medicine.quantity}</label>
            </div>
        </dl>
    </div>
</main>

<%@include file="fragment/footer.jspf" %>

</body>
</html>
