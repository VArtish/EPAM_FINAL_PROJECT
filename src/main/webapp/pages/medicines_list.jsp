<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>Ads page</title>

</head>

<%@include file="fragment/fmt_message.jsp" %>

<body class="d-flex flex-column h-100 bg-light">

<%@include file="fragment/header.jspf" %>

<link rel="stylesheet" href="${abs}/css/album_style.css">

<main>
    <c:if test="${medicines == null}">
        <jsp:forward page="${abs}/controller?command=show_medicine_list"/>
    </c:if>
    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row justify-content-between">
                <div class="dropdown col-md-4">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                            data-bs-toggle="dropdown" aria-expanded="false">
                        <c:choose>
                            <c:when test="${requestScope.sorted eq 'medicine_id'}">
                                Sort by ID
                            </c:when>
                            <c:when test="${requestScope.sorted eq 'medicine_name'}">
                                Sort by Name
                            </c:when>
                            <c:when test="${requestScope.sorted eq 'medicine_price'}">
                                Sort by Price
                            </c:when>
                        </c:choose>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item"
                               href="${abs}/controller?command=show_medicine_list&page=${page.currentPage}&sorted=medicine_id">Sort
                            by ID</a></li>
                        <li><a class="dropdown-item"
                               href="${abs}/controller?command=show_medicine_list&page=${page.currentPage}&sorted=medicine_price">Sort
                            by Price</a></li>
                        <li><a class="dropdown-item"
                               href="${abs}/controller?command=show_medicine_list&page=${page.currentPage}&sorted=medicine_name">Sort
                            by Name</a></li>
                    </ul>
                </div>
            </div>
            <br/>
            <br/>


            <div class="row row-cols-4">
                <c:forEach items="${medicines}" var="medicine">
                    <div class="col pb-3">
                        <div class="card" style="width: 18rem;">
                            <img class="card-img-top" src="${medicine.imageLink}" alt="Card image cap">
                            <div class="card-body">
                                <div class="favourite">
                                    <a href="#" class="js-bookmark">
                                        <div class="bookmark">
                                            <div class="bookmark-edge">
                                                <div class="edge-top"></div>
                                                <div class="edge-bottom"></div>
                                            </div>
                                            <div class="bookmark-body">♥</div>
                                        </div>
                                    </a>
                                </div>
                                <h5 class="card-title">Name: ${medicine.name}</h5>
                                <p class="card-text">Price: ${medicine.price}</p>
                                <c:choose>
                                    <c:when test="${sessionScope.user_role ne 'GUEST'}">
                                        <a href="${abs}/controller?command=add_medicine_to_basket&medicine_id=${medicine.medicineId}&page=${page.currentPage}&sorted=${requestScope.sorted}"
                                           class="btn btn-primary">Add to basket</a>
                                    </c:when>
                                </c:choose>
                                <a href="${abs}/controller?command=show_more_info_medicine&medicine_id=${medicine.medicineId}&page=${page.currentPage}&sorted=${requestScope.sorted}"
                                   class="btn btn-primary">Show more</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <nav aria-label="Ad navigation">
                <ul class="pagination mt-2">
                    <li class="page-item ${page.firstPage ? 'disabled' : ''}">
                        <a class="page-link"
                           href="${abs}/controller?command=show_medicine_list&page=${page.currentPage - 1}"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach var="i" begin="1" end="${page.pageCount()}">
                        <li class="page-item ${page.currentPage == i ? 'active': ''}">
                            <a class="page-link"
                               href="${abs}/controller?command=show_medicine_list&page=${i}">${i}</a>
                        </li>
                    </c:forEach>

                    <li class="page-item ${page.lastPage ? 'disabled' : ''}">
                        <a class="page-link"
                           href="${abs}/controller?command=show_medicine_list&page=${page.currentPage + 1}"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    </div>
</main>

<%@include file="fragment/footer.jspf" %>

</body>
</html>