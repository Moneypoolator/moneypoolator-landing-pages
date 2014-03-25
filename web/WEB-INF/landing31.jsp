<%-- 
    Document   : The landing page for a wide audience (code 31).
    Created on : 17.03.2014, 17:51:01
    Author     : Alexey Narolin <alexey.n.narolin@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Moneypoolator - рынок прогнозов</title>
        <link href="<c:url value="/_css3/style.css"/>" rel="stylesheet" type="text/css" />
        <link rel="icon" href="<c:url value="/favicon.ico"/>" type="image/x-icon"> 
        <link rel="shortcut icon" href="<c:url value="/favicon.ico"/>" type="image/x-icon">
        <meta name="keywords" content="" />
        <meta name="description" content="" />
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
                    document.createElement('footer');
                </script>
            <![endif]-->
    </head>
    <body>
        <h1>Moneypoolator - Глобальный рынок прогнозов</h1>
        <div id="container-header2">
            <header>
                <div id="logo"><a href="<c:url value="/index"/>"><img src="<c:url value="/_img3/logo2.png"/>" alt="Moneypoolator - рынок прогнозов" /></a></div>
                <div id="header-right">
                    <h2>Единственный способ инвестировать, <br />не ломая голову!</h2>
                    <div class="information">
                        <p><strong>На рынке прогнозов Moneypoolator вы можете ставить не результаты конкретных событий.</strong></p>
                        <p>Инфляция и уровень рождаемости, отзывы банковских лиценций и принятие законопроектов - все это может стать прямым источником дохода.</p>
                    </div>
                    <form id="document-request" novalidate action="subscribe" method="post">
                        <p><strong>Оставьте нам свой e-mail, чтобы узнать как это работает</strong></p>
                        <p><input type="hidden" name="goback" value="/landing31" /></p>

                        <p>
                            <c:choose>
                                <c:when test="${!empty sessionScope.documentAddress}">
                                    <input class="text" type="email" name="document-email" value="<c:out value="${sessionScope.documentAddress}"/>" />
                                    <c:remove var="documentAddress" scope="session" />
                                </c:when>
                                <c:otherwise>
                                    <input class="text" type="email" name="document-email" value="" placeholder="Email адрес" />
                                </c:otherwise>
                            </c:choose>
                            <input class="submit" type="submit" name="subscribe" value="Получить документ" />
                        </p>

                        <c:choose>
                            <c:when test="${!empty sessionScope.documentAddressError}">
                                <p class="email-error-message">${sessionScope.documentAddressError}</p>
                                <c:remove var="documentAddressError" scope="session" />
                            </c:when>
                            <c:otherwise>
                                <p style="display:none"></p>
                            </c:otherwise>
                        </c:choose>
                        <p class="confidentiality">Мы не будем распростронять информацию о вашем электронном адресе.</p>
                    </form>
                </div>
                <div class="clear"></div>
            </header>
        </div>
        <div id="container-thesis1">
            <article id="thesis1" class="thesis">
                <p>Moneypoolator позволит вам эффективно зарабатывать на прогнозах. И вот почему</p>
            </article>	
        </div>
        <div id="container">
            <div id="content" class="content">
                <section class="column-left">
                    <ul>
                        <li class="event">
                            <h3>Вы выбираете именно то событие</h3>
                            <p>Покупайте или продавайте интересные вам события.</p>
                        </li>
                        <li class="knowledge">
                            <h3>Минимум дополнительных знаний</h3>
                            <p>Не нужно читать тонны литературы, просто будьте в курсе.</p>
                        </li>
                    </ul>
                </section>
                <section class="column-right">
                    <ul>
                        <li class="plainness">
                            <h3>Простой интерфейс</h3>
                            <p>Все действия элементарны и совершаются в браузере.</p>
                        </li>
                        <li class="perspective">
                            <h3>Ясная перспектива дохода</h3>
                            <p>Вам всегда понятен потенциальный размер прибыли и рисков.</p>
                        </li>
                    </ul>
                </section>
                <div class="clear"></div>
            </div>	
        </div>
        <div id="container-thesis2">
            <article id="thesis2" class="thesis">
                <p>Мы скоро запустимся. Присоединяйтесь к нам и получайте <br />выгоды от инвестирования в будущее!</p>
            </article>	
        </div>
        <div id="container-footer">
            <footer>
                <form id="announce-request" novalidate action="subscribe" method="post">
                    <p><strong>Оставьте нам свой e-mail, чтобы мы могли <br />сообщить вам о запуске и подарить $5</strong></p>
                        <p><input type="hidden" name="goback" value="/landing31" /></p>

                        <p>
                            <c:choose>
                                <c:when test="${!empty sessionScope.announceAddress}">
                                    <input class="text" type="email" name="announce-email" value="<c:out value="${sessionScope.announceAddress}"/>" />
                                    <c:remove var="announceAddress" scope="session" />
                                </c:when>
                                <c:otherwise>
                                    <input class="text" type="email" name="announce-email" value="" placeholder="Email адрес" />
                                </c:otherwise>
                            </c:choose>
                            <input class="submit" type="submit" name="subscribe" value="Сообщить о запуске" />
                        </p>

                        <c:choose>
                            <c:when test="${!empty sessionScope.announceAddressError}">
                                <p class="email-error-message">${sessionScope.announceAddressError}</p>
                                <c:remove var="announceAddressError" scope="session" />
                            </c:when>
                            <c:otherwise>
                                <p style="display:none"></p>
                            </c:otherwise>
                        </c:choose>

                </form>
                <div class="clear"></div>
            </footer>
        </div>
    </body>
</html>
