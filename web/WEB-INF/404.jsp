<%-- 
    Document   : File not found error page
    Created on : 11.05.2013, 0:09:21
    Author     : Alexey Narolin <alexey.n.narolin@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <title>Moneypoolator - Рынок прогнозов</title>
        <link href="<c:url value="/_css/style.css" />" rel="stylesheet" type="text/css" />
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <link rel="icon" href="<c:url value="/favicon.ico"/>" type="image/x-icon">
        <link rel="shortcut icon" href="<c:url value="/favicon.ico"/>" type="image/x-icon">

        <script type="text/javascript">
            <c:import url="/WEB-INF/javascript/google-analytics-counter.js" />
        </script>

        <!--[if IE]>
                        <script>
                                document.createElement('header');
                                document.createElement('nav');
                                document.createElement('menu');
                                document.createElement('section');
                                document.createElement('article');
                                document.createElement('aside');
                                document.createElement('figure');
                                document.createElement('figcaption');
                                document.createElement('video');
                                document.createElement('footer');
                        </script>
        <![endif]-->
    </head>
    <body>
        <h1>Moneypoolator &mdash; Рынок прогнозов</h1>
        <div id="container">
            <header>
                <div id="logo"><img src="<c:url value="/_img/logo.png"/>" alt="Moneypoolator - Рынок прогнозов" /></div>
                <div id="opening"></div>
                <div id="header-info">
                    <h2>404</h2>
                    <p>Страница не найдена или больше не существует.</p>
                </div>
            </header>
            <div id="content">
                <div id="main">
                    <h3>Почему запрошенная вами страница не найдена?</h3>
                    <p>Возможно, вы нажали на ссылку, которая
                        ведёт на несуществующую страницу,
                        или ошиблись при наборе адреса.</p>
                    <p>Для продолжения просмотра сайта перейдите на <a href="<c:url value="/index"/>" title="Вернуться на главную страницу">главную страницу.</a></p>
                    <p>&nbsp;<br/>&nbsp;<br/>&nbsp;<br/>&nbsp;<br/></p>
                </div>
            </div>
            <c:import charEncoding="UTF-8" url="/WEB-INF/jspf/footer.jspf" />
        </div>	
    </body>
</html>
