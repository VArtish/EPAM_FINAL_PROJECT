<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@include file="fragment/meta.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${abs}/css/shop.css">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>
    <title>Document</title>
</head>
<%@include file="fragment/fmt_message.jsp" %>
<body>
<%@include file="fragment/header.jspf" %>
<section class="h-100 h-custom" style="background-color: #d2c9ff;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12">
                <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                    <div class="card-body p-0">
                        <div class="row g-0">
                            <div class="col-lg-8">
                                <div class="p-5">
                                    <div class="d-flex justify-content-between align-items-center mb-5">
                                        <h1 class="fw-bold mb-0 text-black">${purchases}</h1>
                                    </div>
                                    <hr class="my-4">
                                    <c:forEach items="${sessionScope.basket}" var="medicine">
                                        <c:set var="sumPrice" scope="request"
                                               value="${sumPrice + (medicine.key.price * medicine.value)}"/>
                                        <div class="row mb-4 d-flex justify-content-between align-items-center">
                                            <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                                <a href="${abs}/controller?command=delete_medicine_from_basket&medicine_id=${medicine.key.medicineId}"
                                                   class="text-muted"><i class="fas fa-times">X</i></a>
                                            </div>
                                            <div class="col-md-2 col-lg-2 col-xl-2">
                                                <img
                                                        src="${medicine.key.imageLink}"
                                                        class="img-fluid rounded-3" alt="Cotton T-shirt">
                                            </div>
                                            <div class="col-md-3 col-lg-3 col-xl-3">

                                                <h6 class="text-black mb-0">${medicine.key.name}</h6>
                                            </div>
                                            <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                                <a href="${abs}/controller?command=change_size_medicine_in_basket&medicine_id=${medicine.key.medicineId}&operation=decrease">
                                                    <i class="sign">-</i>
                                                </a>

                                                <input id="form1" min="0" name="quantity" value="${medicine.value}"
                                                       type="number"
                                                       class="form-control form-control-sm" readonly/>
                                                <c:set var="sumQuantity" scope="request"
                                                       value="${sumQuantity + medicine.value}"/>

                                                <a href="${abs}/controller?command=change_size_medicine_in_basket&medicine_id=${medicine.key.medicineId}&operation=increase">
                                                    <i class="sign ">+</i>
                                                </a>
                                            </div>
                                            <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                                <h6 class="mb-0">${medicine.key.price}</h6>
                                            </div>
                                        </div>
                                        <hr class="my-4">
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="col-lg-4 bg-grey">
                                <div class="p-5">
                                    <h3 class="fw-bold mb-5 mt-2 pt-1">${total}</h3>
                                    <hr class="my-4">

                                    <div class="d-flex justify-content-between mb-4">
                                        <h5 class="text-uppercase">${nubmer_goods}: ${sumQuantity}</h5>
                                        <h5></h5>
                                    </div>

                                    <hr class="my-4">

                                    <div class="d-flex justify-content-between mb-5">
                                        <h5 class="text-uppercase">${total_price}: ${sumPrice}</h5>
                                    </div>

                                    <button type="submit" class="btn btn-dark btn-block btn-lg"
                                            data-mdb-ripple-color="dark">
                                        <a
                                                href="${abs}/controller?command=create_order">${buy}
                                        </a>
                                    </button>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>