<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="content" />
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
                <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#custom-collapse"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button><a class="navbar-brand" href="index_shop.jsp">MStore</a>
            </div>
            <div class="collapse navbar-collapse" id="custom-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="index_shop.jsp"><fmt:message key="navbar.home"/></a></li>
                        <li class="dropdown"><a class="dropdown-toggle" href="#" data-toggle="dropdown"><fmt:message key="navbar.account"/></a>
                            <ul class="dropdown-menu">
                                <li><a href="login_register.jsp"><fmt:message key="navbar.login.register"/></a></li>
                            </ul>
                        </li>
                    <li class="dropdown"><a class="dropdown-toggle" href="#" data-toggle="dropdown"><fmt:message key="navbar.languages"/></a>
                        <ul class="dropdown-menu" role="menu">
                            <form>
                                <li><button type="submit" name="language" value="ru"><em class="fa fa-list-alt"></em><fmt:message key="russian"/></button></li>
                                <li><button type="submit" name="language" value="en"><em class="fa fa-list-alt"></em><fmt:message key="english"/></button></li>
                            </form>
                        </ul>
                    <li><a href="${pageContext.request.contextPath}/productList"><fmt:message key="navbar.shop"/></a></li>
                    <li><a href="cart.jsp"><fmt:message key="navbar.cart"/></a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="main">
        <section class="module bg-dark-30" data-background="assets/images/section-4.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-sm-offset-3">
                        <h1 class="module-title font-alt mb-0"><fmt:message key="login.register"/></h1>
                    </div>
                </div>
            </div>
        </section>
        <section class="module">
            <div class="container">
                <div class="row">
                    <div class="col-sm-5 col-sm-offset-1 mb-sm-40">
                        <h4 class="font-alt"><fmt:message key="login"/></h4>
                        <hr class="divider-w mb-10">
                        <form class="form" action="${pageContext.request.contextPath}/LoginController" method="post">
                            <div style="color: #D53E36;">${errors.login_confirmation}</div>
                            <div class="form-group" >
                                <input class="form-control" value="${requestScope.userEmail}" id="E-mail"  type="text" name="email" placeholder="<fmt:message key="email"/>"/>
                            </div>
                            <div class="form-group">
                                <input class="form-control" id="password" type="password" name="password" placeholder="<fmt:message key="password"/>"/>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-round btn-b"><fmt:message key="login"/></button>
                            </div>
                        </form>
                    </div>
                    <div class="col-sm-5">
                        <h4 class="font-alt"><fmt:message key="register"/></h4>
                        <hr class="divider-w mb-10">
                        <form class="form" action="${pageContext.request.contextPath}/RegisterController" method="post">
                            <div style="color: #D53E36;">${requestScope.errors.email}</div>
                            <div style="color: #D53E36;">${requestScope.errors.email_confirmation}</div>
                            <div class="form-group">
                                <input class="form-control" value="${requestScope.email}" id="E-mail" type="text" name="email" placeholder="<fmt:message key="email"/>"/>
                            </div>
                            <div style="color: #D53E36;">${requestScope.errors.firstName}</div>
                            <div class="form-group">
                                <input class="form-control" value="${requestScope.firstName}" id="firstName" type="text" name="firstName" placeholder="<fmt:message key="fistName"/>"/>
                            </div>
                            <div style="color: #D53E36;">${requestScope.errors.lastName}</div>
                            <div class="form-group">
                                <input class="form-control" value="${requestScope.lastName}" id="lastName" type="text" name="lastName" placeholder="<fmt:message key="lastName"/>"/>
                            </div>
                            <div style="color: #D53E36;">${requestScope.errors.password}</div>
                            <div class="form-group">
                                <input class="form-control" id="password" type="password" name="password" placeholder="<fmt:message key="password"/>"/>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-block btn-round btn-b"><fmt:message key="register"/></button>
                            </div>
                        </form>
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