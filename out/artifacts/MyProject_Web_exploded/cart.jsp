<%@ page contentType="text/html;charset=UTF-8" %>
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
    <title>MStore | Cart</title>

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
</head>
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
                                <li><button type="submit" name="language" value="ru"><em class="fa fa-list-alt"></em><fmt:message key="russian"/></button></li>
                                <li><button type="submit" name="language" value="en"><em class="fa fa-list-alt"></em><fmt:message key="english"/></button></li>
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
        <section class="module">
            <div class="container">
                <div class="row">
                    <c:if test="${sessionScope.cart==null}">
                        <h2 align="center"><fmt:message key="empty.cart"/></h2>
                        <img src="assets/images/shop/png2.png" height="85" width="85">
                    </c:if>
                    <c:if test="${sessionScope.cart !=null}">
                    <div class="col-sm-6 col-sm-offset-3">
                        <h1 class="module-title font-alt"><fmt:message key="checkout"/></h1>
                    </div>
                </div>
                <hr class="divider-w pt-20">
                <div class="row">
                    <div class="col-sm-12">
                        <table class="table table-striped table-border checkout-table">
                            <tbody>
                            <tr>
                                <th><fmt:message key="item"/></th>
                                <th><fmt:message key="name"/></th>
                                <th><fmt:message key="price"/></th>
                                <th><fmt:message key="quantity"/></th>
                                <th><fmt:message key="total.total"/></th>
                                <th><fmt:message key="remove"/></th>
                            </tr>
                            <c:forEach items="${cart}" var="product">
                                <tr>
                                    <td class="hidden-xs"><a href="#"><img src="assets/images/shop/product-12.jpg"
                                                                           alt="Accessories Pack"/></a></td>
                                    <td>
                                        <h5 class="product-title font-alt">${product.key.name}</h5>
                                    </td>
                                    <td class="hidden-xs">
                                        <h5 class="product-title font-alt">$${product.key.price}</h5>
                                    </td>
                                    <td>
                                        <h5 class="product-title font-alt">${product.value}</h5>
                                    </td>
                                    <td>
                                        <h5 class="product-title font-alt">$${product.key.price * product.value}</h5>
                                    </td>

                                    <td class="pr-remove">
                                        <form action="${pageContext.request.contextPath}/deleteFromCart" method="get">
                                            <input type="hidden" name="id" value="${product.key.id}">
                                            <input type="hidden" name="name" value="${product.key.name}">
                                            <input type="hidden" name="volume" value="${product.key.volume}">
                                            <input type="hidden" name="color" value="${product.key.color}">
                                            <input type="hidden" name="price" value="${product.key.price}">
                                            <input type="hidden" name="manufacturerId"
                                                   value="${product.key.productManufacturer.id}">
                                            <input type="hidden" name="manufacturerName"
                                                   value="${product.key.productManufacturer.name}">
                                            <button class="fa fa-times" href="#" type="submit" value="Remove"><fmt:message key="remove.product"/>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <hr class="divider-w">
                <div class="row mt-70">
                    <div class="col-sm-5 col-sm-offset-7">
                        <div class="shop-Cart-totalbox">
                            <h4 class="font-alt"><fmt:message key="cart.total"/></h4>
                            <table class="table table-striped table-border checkout-table">
                                <tbody>
                                <tr class="shop-Cart-totalprice">
                                    <th><fmt:message key="total"/></th>
                                    <td>$${totalSum}</td>
                                </tr>
                                </tbody>
                            </table>
                            <form action="checkout.jsp">
                                <button class="btn btn-lg btn-block btn-round btn-d" type="submit"
                                        value="Proceed to Checkout"><fmt:message key="proceed.to.checkout"/>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
                </c:if>
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