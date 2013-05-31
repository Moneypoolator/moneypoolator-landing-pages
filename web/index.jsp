<%-- 
    Document   : index
    Created on : 29.04.2013, 14:02:31
    Author     : Alexey Narolin <alexey.n.narolin@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <title>Moneypoolator - Рынок прогнозов</title>
        <link href="<c:url value="/_css/style.css" />" rel="stylesheet" type="text/css" />
        <meta name="keywords" content="moneypoolator, манипулятор, рынок прогнозов, рынок предсказаний, прогноз цен на, прогноз рынка, политические прогнозы, рынки предсказаний, финансовые фьючерсы,  биржа прогнозов, биржа предсказаний, идейные фьючерсы, мудрость толпы,  информационные рынки, политические ставки, коллективный разум, фьючерсы на события, прогноз цен, информационная функция рынка, ставки на события, прогнозы рынка форекс, прогнозы валютного рынка, прогнозы фондового рынка, прогноз рынка акций, прогноз финансового рынка, прогноз рынка валют, прогноз на рынке forex, прогноз биржи ставок, биржа ставок, экономический прогноз, прогноз фондового рынка, биржа,  экономический календарь форекс, прогноз валют, прогноз курса валют, коллективный интеллект, прогноз стоимости, рынок ставок, фьючерсы на события" />
        <meta name="description" content="Рынок прогнозов Moneypoolator - заработайте на своем видении будущего. Большое количество событий и простой механизм прогноза." />
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
                    <!--<h2>Как заработать<br />на дефолте Кипра?</h2>
                    <p>Все что вам нужно для этого &mdash; это трезвый разум, немного свободного времени и Moneypoolator.</p>-->
                    <!--h2>Произойдет ли<br />дефолт на Кипре?</h2>
                    <p>Moneypoolator поможет вам найти ответ на этот вопрос, а также на многие другие вопросы. Уже скоро.</p-->
                </div>
            </header>
            <div id="content">
                <div id="main">
                    <h3>Что такое Moneypoolator?</h3>
                    <p>Это рынок предсказаний. Здесь вы сможете выбрать конкретный вариант будущего &mdash; например тот, в котором Кипр объявит дефолт, &mdash; и заработать на этом. А кроме того, на <a class="a" href="#">Moneypoolator</a> можно будет узнать, произойдет интересующее вас событие или нет.</p>
                    <div id="use">
                        <h4>Как использовать Moneypoolator?</h4>
                        <ul>
                            <li id="first">
                                <h5><span>1.</span> Выбор события</h5>
                                <div>
                                    <p>Вы выбираете из списка представленных на Moneypoolator</p>
                                </div>
                            </li>
                            <li id="second">
                                <h5><span>2.</span> Выбор контракта</h5>
                                <div>
                                    <p>Для каждого такого события существует набор торгующихся контрактов</p>
                                </div>
                            </li>
                            <li id="third">
                                <h5><span>3.</span> Выбор варианта будущего</h5>
                                <div>
                                    <p>Вы выбираете тот вариант будущего, который кажется вам более вероятным и инвестируете в него</p>
                                    <p class="emphasized">Если считаете, что событие:</p>
                                    <ol>
                                        <li><span>а.</span> произойдет &mdash; вы покупаете контракты</li>
                                        <li><span>б.</span> не произойдет &mdash; вы продаете контракты</li>
                                    </ol>
                                </div>
                                <div id="inverstirovanie">
                                    <p id="yes" class="answer">да</p>
                                    <p>Инвестируйте в ваш вариант будущего</p>
                                    <p id="no" class="answer">нет</p>
                                </div>
                                <div id="example">
                                    <em>Например:</em>
                                    <p>До 2020 года <span>на Марс будет отправлена</span> пилотируемая космическая миссия</p>
                                </div>
                            </li>
                            <li id="fourth">
                                <h5><span>4.</span> Подведение итогов</h5>
                                <div>
                                    <p>Вы можете выйти из контрактов или ждать даты подведения итогов. Это установленная заранее дата, по наступлению которой можно будет однозначно утверждать, был ли ваш прогноз правильныи или нет</p>
                                </div>
                            </li>
                            <li id="fifth">
                                <h5><span>5. Profit!</span></h5>
                                <p>Если вы окажетесь правы, то на ваш счёт зачисляется сумма выигрыша.</p>
                            </li>
                        </ul>
                    </div>

                    <div id="mc_embed_signup">
                        <form novalidate action="subscribe" method="post" id="mc-embedded-subscribe-form" name="mc-embedded-subscribe-form" class="validate">
                            <p><strong>Мы почти готовы к запуску. Оставьте нам свой e-mail и мы оповестим вас о дате открытия.</strong></p>
                            <p class="required"><input type="hidden" name="goback" value="/index" /></p>
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

                                <input class="button" type="submit" name="subscribe" id="mc-embedded-subscribe" value="Подписаться" title="Прислать мне сообщение о запуске Moneypoolator" onclick="_gaq.push(['_trackEvent', 'submit', 'clicked']);"/>

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
            <jsp:include page="/WEB-INF/jspf/footer.jspf" />
            --%>
            <%--
            <footer>
                <p>&copy; 2013 &laquo;Moneypoolator&raquo; &mdash; рынки предсказаний</p>
            </footer>
            --%>
        </div>	
    </body>
</html>
