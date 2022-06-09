<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="fragment/meta.jsp" %>
    <link rel="stylesheet" href="${abs}/css/product.css">
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
<section class="vh-100" style="background-color: #EAE8E8;">
    <div class="container h-100">
        <form class="row d-flex justify-content-center align-items-center h-100"
              action="${abs}/controller" method="post">
            <div class="col-xl-9">

                <h1 class="text-white mb-4">Регистрация товара</h1>

                <div class="card" style="border-radius: 15px;">
                    <input type="hidden" name="command" value="add_new_medicine">
                    <div class="card-body">

                        <div class="row align-items-center pt-4 pb-3">
                            <div class="col-md-3 ps-5">

                                <h6 class="mb-0">Имя товара</h6>

                            </div>
                            <div class="col-md-9 pe-5">

                                <input type="text" name="medicine_name" class="form-control form-control-lg"/>

                            </div>
                        </div>

                        <hr class="mx-n3">

                        <div class="row align-items-center pt-4 pb-3">
                            <div class="col-md-3 ps-5">

                                <h6 class="mb-0">Количество пластинок в упаковке</h6>

                            </div>
                            <div class="col-md-9 pe-5">

                                <input type="text" name="medicine_amount_in_package"
                                       class="form-control form-control-lg"/>

                            </div>
                        </div>

                        <hr class="mx-n3">

                        <div class="row align-items-center pt-4 pb-3">
                            <div class="col-md-3 ps-5">

                                <h6 class="mb-0">Колличество таблеток в пластинке</h6>

                            </div>
                            <div class="col-md-9 pe-5">

                                <input type="text" name="medicine_amount_in_plate" class="form-control form-control-lg"/>

                            </div>
                        </div>

                        <hr class="mx-n3">

                        <div class="row align-items-center pt-4 pb-3">
                            <div class="col-md-3 ps-5">

                                <h6 class="mb-0">Цена</h6>

                            </div>
                            <div class="col-md-9 pe-5">

                                <input type="text" name="medicine_price" class="form-control form-control-lg"/>

                            </div>
                        </div>

                        <hr class="mx-n3">

                        <div class="row align-items-center pt-4 pb-3">
                            <div class="col-md-3 ps-5">

                                <h6 class="mb-0">Количество(в аптеке)</h6>

                            </div>
                            <div class="col-md-9 pe-5">

                                <input type="text" name="medicine_quantity" class="form-control form-control-lg"/>

                            </div>
                        </div>

                        <hr class="mx-n3">

                        <div class="row align-items-center pt-4 pb-3">
                            <div class="col-md-3 ps-5">

                                <h6 class="mb-0">Страна-производитель</h6>

                            </div>
                            <div class="col-md-9 pe-5">

                                <input type="text" name="medicine_producing_country"
                                       class="form-control form-control-lg"/>

                            </div>
                        </div>

                        <hr class="mx-n3">

                        <div class="row align-items-center pt-4 pb-3">
                            <div class="col-md-3 ps-5">

                                <h6 class="mb-0">Адрес аптеки</h6>

                            </div>
                            <div class="col-md-9 pe-5">

                                <select class="form-select" name="medicine_pharmacy_id"
                                        aria-label="Default select example">
                                    <option selected class="option-first"></option>
                                    <c:forEach items="${pharmacies}" var="pharmacy">
                                        <option value="${pharmacy.pharmacyId}">${pharmacy.address}</option>
                                    </c:forEach>
                                </select>

                            </div>
                        </div>


                        <hr class="mx-n3">

                        <div class="row align-items-center py-3">

                            <div class="d-md-flex justify-content-start align-items-center mb-4 py-2">
                                <div class="col-md-3 ps-5 recept">
                                    <h6 class="mb-0 me-4">С рецептом </h6>
                                </div>

                                <div class="form-check form-check-inline mb-0 me-4">
                                    <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                           id="femaleGender"
                                           value="1"/>
                                    <label class="form-check-label" for="femaleGender">Да</label>
                                </div>

                                <div class="form-check form-check-inline mb-0 me-4">
                                    <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                           id="maleGender"
                                           value="2"/>
                                    <label class="form-check-label" for="maleGender">Нет</label>
                                </div>

                            </div>
                        </div>

                        <hr class="mx-n3">

                        <div class="row align-items-center py-3">
                            <div class="col-md-3 ps-5">

                                <h6 class="mb-0">Ингредиенты</h6>

                            </div>
                            <div class="col-md-9 pe-5">

                                <textarea class="form-control" name="medicine_ingredients" rows="3" placeholder="Напишите ингредиенты"></textarea>

                            </div>
                        </div>

                        <hr class="mx-n3">

                        <div class="row align-items-center py-3">
                            <div class="col-md-3 ps-5">

                                <h6 class="mb-0">Загрузка Картинки</h6>

                            </div>
                            <div class="col-md-9 pe-5">

                                <input type="text" name="medicine_img"
                                       class="form-control form-control-lg"/>
                                <div class="small text-muted mt-2">Выберите любую картинку с размером до 10мб</div>

                            </div>
                        </div>

                        <hr class="mx-n3">

                        <div class="px-5 py-4">
                            <button type="submit" class="btn btn-primary btn-lg">Зарегестрировать товар</button>
                        </div>

                    </div>
                </div>

            </div>
        </form>
    </div>
</section>
</body>
</html>