<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="fragment/meta.jsp" %>
    <link rel="stylesheet" href="${abs}/css/oders.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>
    <title>Document</title>
</head>
<%@include file="fragment/fmt_message.jsp" %>
<body>

<%@include file="fragment/header.jspf" %>
<section class="h-100" style="background-color: #eee;">
    <c:if test="${orders == null}">
        <jsp:forward page="${abs}/controller?command=show_order_list"/>
    </c:if>
    <div class="container h-100 py-5">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-10">

                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h3 class="fw-normal mb-0 text-black">Заказы</h3>

                </div>
                <c:set var="index" scope="request"
                       value="0"/>
                <c:forEach items="${orders}" var="order">
                    <c:set var="sumPrice" scope="request"
                           value="0"/>
                    <c:set var="index" scope="request"
                           value="${index + 1}"/>
                    <c:forEach items="${order.medicines}" var="medicine">
                        <c:set var="sumPrice" scope="request"
                               value="${sumPrice + (medicine.key.price * medicine.value)}"/>
                    </c:forEach>
                    <div class="card rounded-3 mb-4">
                        <div class="card-body p-4">
                            <div class="row d-flex justify-content-between align-items-center">
                                <div class="accordion accordion-flush" id="accordionFlushExample">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header" id="flush-heading${index}">
                                            <button class="accordion-button collapsed" type="button"
                                                    data-bs-toggle="collapse" data-bs-target="#flush-collapse${index}"
                                                    aria-expanded="false" aria-controls="flush-collapseOne">
                                                <div class="info">
                                                    <div class="number">№${index}</div>
                                                    <div class="img"><img
                                                            src="http://cdn.onlinewebfonts.com/svg/img_560812.png"
                                                            alt=""></div>
                                                    <div class="id">#${order.orderId}</div>
                                                    <div class="price">${sumPrice} BYN</div>
                                                    <div class="date">30.05.2022</div>
                                                </div>
                                            </button>
                                        </h2>
                                        <div id="flush-collapse${index}" class="accordion-collapse collapse"
                                             aria-labelledby="flush-heading${index}"
                                             data-bs-parent="#accordionFlushExample">
                                            <div class="accordion-body">
                                                <c:forEach items="${order.medicines}" var="medicine">
                                                    <div class="row mb-4 d-flex justify-content-between align-items-center">
                                                        <div class="col-md-2 col-lg-2 col-xl-2">
                                                            <img
                                                                    src="${abs}/img/default.jpg"
                                                                    class="img-fluid rounded-3" alt="Cotton T-shirt">
                                                        </div>
                                                        <div class="col-md-3 col-lg-3 col-xl-3">

                                                            <h6 class="text-black mb-0">${medicine.key.name}</h6>
                                                        </div>
                                                        <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                                            <h6 class="mb-0">${medicine.value} ШТУК(А)</h6>
                                                        </div>
                                                        <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                                            <h6 class="mb-0">
                                                                #${medicine.key.price * medicine.value}</h6>
                                                        </div>
                                                    </div>
                                                </c:forEach>


                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
</body>
</html>