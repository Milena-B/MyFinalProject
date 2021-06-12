<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="content"/>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>MStore | Shopping online</title>

    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="assets/images/favicons/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
    <link href="assets/lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Volkhov:400i" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
    <link href="assets/lib/animate.css/animate.css" rel="stylesheet">
    <link href="assets/lib/components-font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/lib/et-line-font/et-line-font.css" rel="stylesheet">
    <link href="assets/lib/flexslider/flexslider.css" rel="stylesheet">
    <link href="assets/lib/owl.carousel/dist/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="assets/lib/owl.carousel/dist/assets/owl.theme.default.min.css" rel="stylesheet">
    <link href="assets/lib/magnific-popup/dist/magnific-popup.css" rel="stylesheet">
    <link href="assets/lib/simple-text-rotator/simpletextrotator.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
<body data-spy="scroll" data-target=".onpage-navigation" data-offset="60">
<main>
    <div class="page-loader">
        <div class="loader">Loading...</div>
    </div>
    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#custom-collapse"><span
                        class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
                        class="icon-bar"></span><span class="icon-bar"></span></button>
                <a class="navbar-brand" href="index_shop.jsp">MStore</a>
            </div>
            <div class="collapse navbar-collapse" id="custom-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="index_shop.jsp"><fmt:message key="navbar.home"/></a></li>
                    <c:if test="${sessionScope.authUser==null}">
                        <li class="dropdown"><a class="dropdown-toggle" href="#" data-toggle="dropdown"><fmt:message
                                key="navbar.account"/></a>
                            <ul class="dropdown-menu">
                                <li><a href="login_register.jsp"><fmt:message key="navbar.login.register"/></a></li>
                            </ul>
                        </li>
                    </c:if>
                    <li class="dropdown"><a class="dropdown-toggle" href="#" data-toggle="dropdown"><fmt:message
                            key="navbar.languages"/></a>
                        <ul class="dropdown-menu" role="menu">
                            <form>
                                <li>
                                    <button type="submit" name="language" value="ru"><em
                                            class="fa fa-list-alt"></em><fmt:message key="russian"/></button>
                                </li>
                                <li>
                                    <button type="submit" name="language" value="en"><em
                                            class="fa fa-list-alt"></em><fmt:message key="english"/></button>
                                </li>
                            </form>
                        </ul>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/productList"><fmt:message key="navbar.shop"/></a>
                    </li>
                    <li><a href="cart.jsp"><fmt:message key="navbar.cart"/></a></li>
                    <c:if test="${sessionScope.authUser != null}">
                        <c:if test="${sessionScope.authUser.role != 'ADMIN'}">
                            <li><a href="showCabinet.jsp"><fmt:message key="cabinet"/></a></li>
                        </c:if>
                    </c:if>
                    <c:if test="${sessionScope.authUser != null}">
                        <li><a href="${pageContext.request.contextPath}/Logout"><fmt:message key="logout"/></a></li>
                    </c:if>
                    <c:if test="${sessionScope.authUser.role == 'ADMIN'}">
                        <li><a href="admin.jsp"><fmt:message key="admin"/></a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
    <div class="main">
        <section class="module bg-dark-60 shop-page-header" data-background="assets/images/shop/product-page-bg.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-sm-offset-3">
                        <h2 class="module-title font-alt"><fmt:message key="shop.products"/></h2>
                        <div class="module-subtitle font-serif"><fmt:message key="shop.enjoy.shopping"/></div>
                    </div>
                </div>
            </div>
        </section>
        <section class="module-small">
            <div class="container">
                <form class="row">
                    <div class="col-sm-2 mb-sm-20">
                        <label>
                            <select name="sort" class="form-control">
                                <option value="" ${requestScope.selectedSort == requestScope.selectedSort ? 'selected="selected"' : ''}>
                                    <fmt:message key="sort.default"/></option>
                                <option value="name asc" ${"name asc" == requestScope.selectedSort ? 'selected="selected"' : ''}>
                                    <fmt:message key="sort.by.name.az"/></option>
                                <option value="name desc" ${"name desc" == requestScope.selectedSort ? 'selected="selected"' : ''}>
                                    <fmt:message key="sort.by.name.za"/></option>
                                <option value="create_date asc" ${"create_date asc" == requestScope.selectedSort ? 'selected="selected"' : ''}>
                                    <fmt:message key="sort.latest"/></option>
                                <option value="price desc" ${"price desc" == requestScope.selectedSort ? 'selected="selected"' : ''}>
                                    <fmt:message key="sort.high.price"/></option>
                                <option value="price asc" ${"price asc" == requestScope.selectedSort ? 'selected="selected"' : ''}>
                                    <fmt:message key="sort.low.price"/></option>
                            </select>
                        </label>
                    </div>
                    <div class="col-sm-2 mb-sm-20">
                        <label>
                            <select class="form-control" name="volume">
                                <option value=""  ${requestScope.selectedVolume == requestScope.selectedVolume ? 'selected="selected"' : ''}>
                                    <fmt:message key="sort.volume"/></option>
                                <c:forEach items="${requestScope.volumes}" var="volume">
                                    <option value="${volume}"  ${volume == requestScope.selectedVolume ? 'selected="selected"' : ''}>${volume}
                                        <fmt:message key="sorter.ml"/></option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>

                    <div class="col-sm-2 mb-sm-20">
                        <label>
                            <select class="form-control" name="color">
                                <option value=""  ${requestScope.selectedColor == requestScope.selectedColor ? 'selected="selected"' : ''}>
                                    <fmt:message key="sort.color"/></option>
                                <c:forEach items="${requestScope.colors}" var="color">
                                    <option value="${color}"  ${color == requestScope.selectedColor ? 'selected="selected"' : ''}>${color}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>

                    <div class="col-sm-2 mb-sm-20">
                        <label>
                            <label>
                                <select class="form-control" name="brand">
                                    <option value=""  ${requestScope.selectedBrand == requestScope.selectedBrand ? 'selected="selected"' : ''}>
                                        <fmt:message key="sort.brands"/></option>
                                    <c:forEach items="${requestScope.brands}" var="brand">
                                        <option value="${brand}"  ${brand == requestScope.selectedBrand ? 'selected="selected"' : ''}>${brand}</option>
                                    </c:forEach>
                                </select>
                            </label>
                        </label>
                    </div>

                    <div class="col-md-2">
                        <label><fmt:message key="minPrice"/></label>
                        <input name="minPrice" class="form-control" value="${requestScope.selectedMinPrice}"
                               id="price-range" type="text" placeholder="<fmt:message key="placeholder.minPrice"/>">
                    </div>
                    <div class="col-md-2">
                        <label><fmt:message key="maxPrice"/></label>
                        <input name="maxPrice" class="form-control" value="${requestScope.selectedMaxPrice}"
                               id="max-price" type="text" placeholder="<fmt:message key="placeholder.maxPrice"/>">
                    </div>

                    <div class="col-sm-2 mb-sm-20">
                        <button class="btn btn-block btn-round btn-g" type="submit"><fmt:message key="apply"/></button>
                    </div>
                </form>
            </div>
        </section>
        <hr class="divider-w">
        <section class="module-small">
            <div class="container">
                <div class="row multi-columns-row">
                    <c:forEach items="${requestScope.products}" var="product">
                        <div class="col-sm-6 col-md-3 col-lg-3">
                            <div class="shop-item">
                                <form action="${pageContext.request.contextPath}/addToCart" method="GET">
                                    <div class="shop-item-image"><img src="assets/images/shop/product-12.jpg"
                                                                      alt="Accessories Pack"/>
                                        <input type="hidden" name="id" value="${product.id}">
                                        <input type="hidden" name="name" value="${product.name}">
                                        <input type="hidden" name="volume" value="${product.volume}">
                                        <input type="hidden" name="color" value="${product.color}">
                                        <input type="hidden" name="price" value="${product.price}">
                                        <input type="hidden" name="manufacturerId"
                                               value="${product.productManufacturer.id}">
                                        <input type="hidden" name="manufacturerName"
                                               value="${product.productManufacturer.name}">
                                        <div class="shop-item-detail">
                                            <input type="submit" class="icon-basket"
                                                   value="<fmt:message key="add.to.cart"/>"/>
                                        </div>
                                    </div>
                                </form>
                                <h4 class="shop-item-title font-alt">${product.getVolume()}ml ${product.getName()}</h4>
                                Price: ${product.getPrice()} $<br>
                                Color: ${product.getColor()}
                                Brand: ${product.getProductManufacturer().getName()}
                            </div>
                        </div>
                    </c:forEach>
                    <div class="row" align="center">
                        <div class="col-sm-12">
                            <div class="pagination font-alt">
                                <c:if test="${requestScope.currentPage != 1}">
                                    <a href="${pageContext.request.contextPath}/productList?sort=${requestScope.selectedSort}&volume=${requestScope.selectedVolume}&color=${requestScope.selectedColor}&brand=${requestScope.selectedBrand}&minPrice=${requestScope.selectedMinPrice}&maxPrice=${requestScope.selectedMaxPrice}&page=${requestScope.currentPage - 1}">
                                        <em class="fa fa-angle-left"></em>
                                    </a>
                                </c:if>
                                <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
                                    <c:choose>
                                        <c:when test="${requestScope.currentPage eq i}">
                                            <td>${i}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/productList?sort=${requestScope.selectedSort}&volume=${requestScope.selectedVolume}&color=${requestScope.selectedColor}&brand=${requestScope.selectedBrand}&minPrice=${requestScope.selectedMinPrice}&maxPrice=${requestScope.selectedMaxPrice}&page=${i}">${i}</a>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${requestScope.currentPage lt requestScope.numberOfPages}">
                                    <a href="${pageContext.request.contextPath}/productList?sort=${requestScope.selectedSort}&volume=${requestScope.selectedVolume}&color=${requestScope.selectedColor}&brand=${requestScope.selectedBrand}&minPrice=${requestScope.selectedMinPrice}&maxPrice=${requestScope.selectedMaxPrice}&page=${requestScope.currentPage + 1}">
                                        <em class="fa fa-angle-right"></em>
                                    </a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</main>
<script src="assets/lib/jquery/dist/jquery.js"></script>
<script src="assets/lib/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="assets/lib/wow/dist/wow.js"></script>
<script src="assets/lib/jquery.mb.ytplayer/dist/jquery.mb.YTPlayer.js"></script>
<script src="assets/lib/isotope/dist/isotope.pkgd.js"></script>
<script src="assets/lib/imagesloaded/imagesloaded.pkgd.js"></script>
<script src="assets/lib/flexslider/jquery.flexslider.js"></script>
<script src="assets/lib/owl.carousel/dist/owl.carousel.min.js"></script>
<script src="assets/lib/smoothscroll.js"></script>
<script src="assets/lib/magnific-popup/dist/jquery.magnific-popup.js"></script>
<script src="assets/lib/simple-text-rotator/jquery.simple-text-rotator.min.js"></script>
<script src="assets/js/plugins.js"></script>
<script src="assets/js/main.js"></script>
</body>
</html>