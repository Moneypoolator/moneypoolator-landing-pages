<%-- 
    Document   : landing1
    Created on : 08.05.2013, 14:08:16
    Author     : 1
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
        <link href="_css/style.css" rel="stylesheet" type="text/css" />
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <link rel="icon" href="favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">

        <script type="text/javascript">

            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-40703103-1']);
            _gaq.push(['_setDomainName', 'moneypoolator.com']);
            _gaq.push(['_setAllowLinker', true]);
            _gaq.push(['_trackPageview']);

            (function() {
                var ga = document.createElement('script');
                ga.type = 'text/javascript';
                ga.async = true;
                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(ga, s);
            })();

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
                <div id="logo"><img src="_img/logo.png" alt="Moneypoolator - Рынок прогнозов" /></div>
                <div id="opening"></div>
                <div id="header-info">
                    <h2>Как заработать<br />на дефолте Кипра?</h2>
                    <p>Все что вам нужно для этого &mdash; это трезвый разум, немного свободного времени и Moneypoolator.</p>
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
                            <p class="required"><input type="hidden" name="list_id" value="18471" /><input type="hidden" name="no_conf" value="" /><input type="hidden" name="notify" value="" /></p>
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

                                <%--
                                <input class="required email" id="mce-EMAIL" type="email" name="email" value="" placeholder="Ваш адрес E-mail" />
                                <c:if test="${!empty sessionScope.mailErrorResponse}">
                                    <div class="mce_inline_error" style="">${sessionScope.mailErrorResponse}</div>  
                                    <c:remove var="mailErrorResponse" scope="session" />
                                </c:if>
                                --%>

                                <input class="button" type="submit" name="subscribe" id="mc-embedded-subscribe" value="Подписаться" onclick="_gaq.push(['_trackEvent', 'submit', 'clicked']);"/>

                                <div id="nump" class="counter">
                                    <%--  --%>
                                    Запуска уже ждут: <span><c:out value="${requestScope.subscribersCount}" default="4"/></span> <c:out value="${requestScope.subscribersSuffix}" default="человека"/>
                                    <%--  --%>
                                    <%--
                                    Запуска уже ждут: <span><c:out value="${sessionScope.subscribersCount}" default="4"/></span> <c:out value="${sessionScope.subscribersSuffix}" default="человека"/>
                                    <c:if test="${!empty sessionScope.subscribersCount}">
                                        <c:remove var="subscribersCount" scope="session" />
                                    </c:if>
                                    <c:if test="${!empty sessionScope.subscribersSuffix}">
                                        <c:remove var="subscribersCount" scope="session" />
                                    </c:if>
                                    --%>
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
            <footer>
                <p>&copy; 2013 &laquo;Moneypoolator&raquo; &mdash; рынки предсказаний</p>
            </footer>
        </div>	
    </body>
</html>
