<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="content"/>
<!DOCTYPE html>
<html lang="${language}" dir="ltr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><fmt:message key="title"/></title>

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
    <link href="assets/css/myCss.css" rel="stylesheet" type="text/css">
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
                        <li class="dropdown"><a class="dropdown-toggle" href="#" data-toggle="dropdown">Account</a>
                            <ul class="dropdown-menu">
                                <li><a href="login_register.jsp"><fmt:message key="navbar.login.register"/></a></li>
                            </ul>
                        </li>
                    </c:if>
                    <li class="dropdown"><a class="dropdown-toggle" href="#" data-toggle="dropdown"><fmt:message key="navbar.languages"/></a>
                        <ul class="dropdown-menu" role="menu">
                            <form>
                                <li><button type="submit" name="language" value="ru"><em class="fa fa-list-alt"></em><fmt:message key="russian"/></button></li>
                                <li><button type="submit" name="language" value="en"><em class="fa fa-list-alt"></em><fmt:message key="english"/></button></li>
                            </form>
                        </ul>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/productList"><fmt:message key="navbar.shop"/></a></li>
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
        <div class="myClass">
            <div align="center">
            <form action="addProduct.jsp">
                <input type="submit" value="<fmt:message key="button.add"/>">
            </form>
            </div>
            <table border="2" align="center">
                <tr>
                    <th><fmt:message key="productId"/></th>
                    <th><fmt:message key="name"/></th>
                    <th><fmt:message key="volume"/></th>
                    <th><fmt:message key="color"/></th>
                    <th><fmt:message key="price"/></th>
                    <th><fmt:message key="manufacturer"/></th>
                    <th><fmt:message key="create.date"/></th>
                    <th><fmt:message key="action"/></th>
                </tr>
                <jsp:useBean id="products" scope="request" type="java.util.List"/>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.getId()}</td>
                        <td>${product.getName()}</td>
                        <td>${product.getVolume()}</td>
                        <td>${product.getColor()}</td>
                        <td>${product.getPrice()}</td>
                        <td>${product.getProductManufacturer().getName()}</td>
                        <td>${product.getLocalDateTime()}</td>
                        <td>
                            <form action="updateProduct.jsp">
                                <input type="hidden" name="id" value="${product.getId()}">
                                <input type="hidden" name="name" value="${product.getName()}">
                                <input type="hidden" name="volume" value="${product.getVolume()}">
                                <input type="hidden" name="color" value="${product.getColor()}">
                                <input type="hidden" name="price" value="${product.getPrice()}">
                                <input type="hidden" name="manufacturerName" value="${product.getProductManufacturer().getName()}">
                                <input type="submit" value="<fmt:message key="button.change"/>" style="float:left">
                            </form>
                            <form action="deleteProduct.jsp">
                                <input type="hidden" name="id" value="${product.getId()}">
                                <input type="submit" value="<fmt:message key="button.delete"/>" style="float:left">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
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
