<%-- 
    Document   : index2
    Created on : 24.06.2013, 19:58:47
    Author     : Alexey Narolin <alexey.n.narolin@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <title>Moneypoolator - Рынок прогнозов</title>
        <link href="<c:url value="/_css2/style.css"/>" rel="stylesheet" type="text/css" />
        <meta name="keywords" content="moneypoolator, манипулятор, рынок прогнозов, рынок предсказаний, политические прогнозы, рынки предсказаний, финансовые фьючерсы,  биржа прогнозов, биржа предсказаний, идейные фьючерсы, мудрость толпы,  информационные рынки, политические ставки, коллективный разум, фьючерсы на события, прогноз цен, информационная функция рынка, ставки на события, прогноз биржи ставок, биржа ставок, экономический прогноз, биржа, коллективный интеллект, прогноз стоимости, рынок ставок, фьючерсы на события" />
        <meta name="description" content="Рынок прогнозов Moneypoolator - заработайте на своем видении будущего. Большое количество событий и простой механизм прогноза." />
        <link rel="icon" href="<c:url value="/favicon.ico"/>" type="image/x-icon" />
        <link rel="shortcut icon" href="<c:url value="/favicon.ico"/>" type="image/x-icon" />

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
        <h1>Moneypoolator &mdash; Рынок прогнозов, рынок предсказаний</h1>
        <div id="container">
            <div id="container-header">
                <header>
                    <div id="logo"><img src="<c:url value="/_img2/logo.png"/>" alt="Moneypoolator - Рынок прогнозов" /></div>
                </header>
            </div>
            <div id="content">
                <div id="content-top">
                    <h2>Как заработать на <span>дефолте Кипра</span>?</h2>
                    <p>Все что вам нужно &mdash; это трезвый разум, немного свободного времени и Moneypoolator.</p>
                    <div id="scheme"><img src="<c:url value="/_img2/scheme.png"/>" alt="Cхема работы рынка предсказаний" /></div>
                    <p>Мы почти готовы к запуску. <br />Оставьте нам свой e-mail и мы оповестим вас об открытии!</p>
                </div>
                <form novalidate action="subscribe" method="post">
                    <div id="required">
                        <input type="hidden" name="goback" value="/index" />

                        <c:choose>
                            <c:when test="${!empty sessionScope.currentAdress}">
                                <c:choose>
                                    <c:when test="${!empty sessionScope.mailErrorResponse}">
                                        <input class="error-email" id="EMAIL" type="email" name="email" value="<c:out value="${sessionScope.currentAdress}"/>" />
                                    </c:when>
                                    <c:otherwise>
                                        <input class="email" id="EMAIL" type="email" name="email" value="<c:out value="${sessionScope.currentAdress}"/>" />
                                    </c:otherwise>
                                </c:choose>                    
                                <c:remove var="currentAdress" scope="session" />
                            </c:when>
                            <c:otherwise>
                                <input class="email" id="EMAIL" type="email" name="email" value="" placeholder="Ваш адрес E-mail" />
                            </c:otherwise>
                        </c:choose>

                        <input class="button" type="submit" name="" value="" title="Прислать мне сообщение о запуске Moneypoolator" onclick="_gaq.push(['_trackEvent', 'submit', 'clicked']);" />
                    </div>
                    <div id="counter">
                        <span><c:out value="${requestScope.subscribersCount}" default="4"/></span> <c:out value="${requestScope.subscribersSuffix}" default="человека"/> уже ждут запуска
                    </div>

                    <c:choose>
                        <c:when test="${!empty sessionScope.mailErrorResponse}">
                            <div class="response" id="error-response" style="">${sessionScope.mailErrorResponse}</div>
                            <c:remove var="mailErrorResponse" scope="session" />
                        </c:when>
                        <c:otherwise>
                            <div class="response" id="error-response" style="display:none"></div>
                        </c:otherwise>
                    </c:choose>                    
                </form>
                <div id="information">
                    <h3>Что такое <span>Moneypoolator</span>?</h3>
                    <p>Это рынок предсказаний. Здесь вы сможете выбрать конкретный вариант будущего &mdash; например тот, в котором Кипр объявит дефолт, &mdash; и заработать на этом. 
                    <p>А кроме того, на Moneypoolator можно будет узнать, произойдет интересующее вас событие или нет.</p>
                </div>
            </div>
        </div>
        <div id="container-footer">
            <footer>
                <p>&copy; 2012 &laquo;Moneypoolator&raquo; &mdash; рынки предсказаний</p>
            </footer>
        </div>
    </body>
</html>
