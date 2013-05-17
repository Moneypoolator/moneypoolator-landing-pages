<%-- 
    Document   : First landing page
    Created on : 08.05.2013, 14:08:16
    Author     : Alexey Narolin <alexey.n.narolin@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
                    <h2>Как заработать<br />на дефолте Кипра?</h2>
                    <p>Все, что вам нужно для этого &mdash; это трезвый разум, немного свободного времени и Moneypoolator.</p>
                    <!--h2>Произойдет ли<br />дефолт на Кипре?</h2>
                    <p>Moneypoolator поможет вам найти ответ на этот вопрос, а также на многие другие вопросы. Уже скоро.</p-->
                </div>
            </header>
            <div id="content">
                <div id="main">
                    <h3>Что такое Moneypoolator?</h3>
                    <p>Это рынок предсказаний. Здесь вы сможете выбрать конкретный вариант будущего &mdash; например тот, в котором Кипр объявит дефолт, &mdash; и заработать на этом. А кроме того, на Moneypoolator можно будет узнать, произойдет интересующее вас событие или нет.</p>
                    <div id="mc_embed_signup">
                        <form novalidate action="subscribe" method="post" id="mc-embedded-subscribe-form" name="mc-embedded-subscribe-form" class="validate">
                            <p><strong>Мы почти готовы к запуску. Оставьте нам свой e-mail и мы оповестим вас о дате открытия.</strong></p>
                            <p class="required"><input type="hidden" name="goback" value="/landing1" /></p>
                            <div id="required">

                                <c:choose>
                                    <c:when test="${!empty sessionScope.currentAdress}">
                                        <input class="required email" id="mce-EMAIL" type="email" name="email" value="<c:out value="${sessionScope.currentAdress}"/>" />
                                        <c:remove var="currentAdress" scope="session" />
                                    </c:when>
                                    <c:otherwise>
                                        <input class="required email" id="mce-EMAIL" type="email" name="email" value="" placeholder="Ваш адрес E-mail" />
                                    </c:otherwise>
                                </c:choose>

                                <input class="button" type="submit" name="subscribe" id="mc-embedded-subscribe" value="Подписаться" onclick="_gaq.push(['_trackEvent', 'submit', 'landing1-clicked']);"/>

                                <div id="nump" class="counter">
                                    Запуска уже ждут: <span><c:out value="${requestScope.subscribersCount}" default="4"/></span> <c:out value="${requestScope.subscribersSuffix}" default="человека"/>
                                </div>

                                <c:choose>
                                    <c:when test="${!empty sessionScope.mailErrorResponse}">
                                        <div class="response" id="mce-error-response" style="">${sessionScope.mailErrorResponse}</div>
                                        <c:remove var="mailErrorResponse" scope="session" />
                                    </c:when>
                                    <c:otherwise>
                                        <div class="response" id="mce-error-response" style="display:none"></div>
                                    </c:otherwise>
                                </c:choose>

                                <div class="response" id="mce-success-response" style="display:none"></div>
                            </div>
                            <div class="clear"></div>
                        </form>
                    </div>
                </div>
            </div>
            <c:import charEncoding="UTF-8" url="/WEB-INF/jspf/footer.jspf" />
            <%--
            <footer>
                <p>&copy; 2013 &laquo;Moneypoolator&raquo; &mdash; рынки предсказаний</p>
            </footer>
            --%>
        </div>	
    </body>
</html>
