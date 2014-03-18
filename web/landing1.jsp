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
        <h1>Moneypoolator &mdash; Рынок прогнозов, рынок предсказаний</h1>
        <div id="container">
            <header>
                <div id="logo"><img src="<c:url value="/_img/logo.png"/>" alt="Moneypoolator - Рынок прогнозов" /></div>
                <div id="opening"></div>
                <div id="header-info">
                    <h2>Как заработать<br />на отставке Бена Бернанке?</h2>
                    <p>Все, что вам нужно для этого &mdash; это трезвый разум, немного свободного времени и Moneypoolator.</p>
                    <!--h2>Произойдет ли<br />дефолт на Кипре?</h2>
                    <p>Moneypoolator поможет вам найти ответ на этот вопрос, а также на многие другие вопросы. Уже скоро.</p-->
                </div>
            </header>
            <div id="content">
                <div id="main">
                    <h3>Что такое Moneypoolator?</h3>
                    <p>Это рынок предсказаний. Здесь вы сможете выбрать конкретный вариант будущего &mdash; например тот, в котором Бернанке уйдет в отставку в январе 2014 года, &mdash; и заработать на этом. А кроме того, на Moneypoolator можно будет узнать, произойдет интересующее вас событие или нет.</p>
                    <div id="mc_embed_signup">
                        <form novalidate action="subscribe" method="post" id="mc-embedded-subscribe-form" name="mc-embedded-subscribe-form" class="validate" onsubmit="_gaq.push(['_trackEvent', 'submit', 'landing1-clicked']);yaCounter21951481.reachGoal('LANDING1_SUBMIT'); return true;">
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

                                <input class="button" type="submit" name="subscribe" id="mc-embedded-subscribe" value="Подписаться" title="Прислать мне сообщение о запуске Moneypoolator" />

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

        <!-- Yandex.Metrika counter -->
        <script type="text/javascript">
            (function(d, w, c) {
                (w[c] = w[c] || []).push(function() {
                    try {
                        w.yaCounter21951481 = new Ya.Metrika({id: 21951481,
                            webvisor: true,
                            clickmap: true,
                            trackLinks: true,
                            accurateTrackBounce: true});
                    } catch (e) {
                    }
                });

                var n = d.getElementsByTagName("script")[0],
                        s = d.createElement("script"),
                        f = function() {
                    n.parentNode.insertBefore(s, n);
                };
                s.type = "text/javascript";
                s.async = true;
                s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js";

                if (w.opera == "[object Opera]") {
                    d.addEventListener("DOMContentLoaded", f, false);
                } else {
                    f();
                }
            })(document, window, "yandex_metrika_callbacks");
        </script>
        <noscript><div><img src="//mc.yandex.ru/watch/21951481" style="position:absolute; left:-9999px;" alt="" /></div></noscript>
        <!-- /Yandex.Metrika counter -->
        
        <script type="text/javascript" async="true" defer="defer">
            yaCounter21951481.reachGoal('LANDING1_SUBMIT');
        </script>

    </body>
</html>
