<%-- 
    Document   : thanks
    Created on : 21.03.2014, 15:40:46
    Author     : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Moneypoolator - рынок прогнозов</title>
        <link href="<c:url value="/_css3/style.css"/>" rel="stylesheet" type="text/css" />
        <link rel="icon" href="<c:url value="/favicon.ico"/>" type="image/x-icon"> 
        <link rel="shortcut icon" href="<c:url value="/favicon.ico"/>" type="image/x-icon">
        <meta name="keywords" content="moneypoolator, манипулятор, рынок прогнозов, рынок предсказаний, политические прогнозы, рынки предсказаний, финансовые фьючерсы,  биржа прогнозов, биржа предсказаний, идейные фьючерсы, мудрость толпы,  информационные рынки, политические ставки, коллективный разум, фьючерсы на события, прогноз цен, информационная функция рынка, ставки на события, прогноз биржи ставок, биржа ставок, экономический прогноз, биржа, коллективный интеллект, прогноз стоимости, рынок ставок, фьючерсы на события" />
        <meta name="description" content="Рынок прогнозов Moneypoolator - заработайте на своем видении будущего. Большое количество событий и простой механизм прогноза." />
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
                    document.createElement('footer');
                </script>
            <![endif]-->
    </head>
    <body class="thank">
        <h1>Moneypoolator &mdash; рынок прогнозов</h1>
        <div id="container-thesis4">
            <article id="thesis4" class="thesis">
                <p>Спасибо за ваш e-mail!</p>
                <h6>На вашу почту отправлено письмо с подробным рассказом о рынке прогнозов Moneypoolator.</h6>
            </article>	
        </div>
        <div id="container">
            <div id="content3" class="content">
                <section class="column-left">
                    <aside>
                        <p>Пригласите своих друзей и подарите им $5 на счет!</p>
                    </aside>
                    <div class="arguments">
                        <h3>Больше людей на Moneypoolator</h3>
                        <ul>
                            <li class="li1">
                                <p>Лучшие цены при совершении сделок</p>
                            </li>
                            <li class="li2">
                                <p>Это больше интересных соперников</p>
                            </li>
                            <li class="li3">
                                <p>Это больше ценных идей для событий</p>
                            </li>
                        </ul>
                    </div>
                    <div class="clear" id="clear2"></div>
                </section>
                <section class="column-right">
                    <div class="social-icons">
                        <script type="text/javascript" src="http://yandex.st/share/share.js" charset="utf-8"></script>
                        <!-- <c:out value="${sessionScope.subscriptionSource}"/> -->
                        <c:choose>
                            <c:when test="${sessionScope.subscriptionSource eq '/landing31'}">
                                <div class="yashare-auto-init" data-yashareLink="http://moneypoolator.ru<c:out value="${sessionScope.subscriptionSource}"/>" data-yashareImage="http://moneypoolator.ru/_img3/logo2_for_sn.png" data-yashareL10n="ru" data-yashareType="none" data-yashareQuickServices="vkontakte,facebook,twitter,odnoklassniki,moimir" data-yashareTitle="Moneypoolator - рынок прогнозов" data-yashareDescription="В 94 случаях из 100 коллективная оценка оказывается точней индивидуальной. Узнайте, как правильно прогнозировать будущие события и зарабатывать на этом, на рынке прогнозов Moneypoolator."></div> 
                            </c:when>
                            <c:when test="${sessionScope.subscriptionSource eq '/landing32'}">
                                <div class="yashare-auto-init" data-yashareLink="http://moneypoolator.ru<c:out value="${sessionScope.subscriptionSource}"/>" data-yashareImage="http://moneypoolator.ru/_img3/logo2_for_sn.png" data-yashareL10n="ru" data-yashareType="none" data-yashareQuickServices="vkontakte,facebook,twitter,odnoklassniki,moimir" data-yashareTitle="Moneypoolator - рынок прогнозов" data-yashareDescription="Увеличение вероятности войны на 10% прибавляет 1 доллар к цене нефти. Узнайте, как сохранять и зарабатывать деньги в неустойчивом мире."></div> 
                            </c:when>
                            <c:when test="${sessionScope.subscriptionSource eq '/landing33'}">
                                <div class="yashare-auto-init" data-yashareLink="http://moneypoolator.ru<c:out value="${sessionScope.subscriptionSource}"/>" data-yashareImage="http://moneypoolator.ru/_img3/logo2_for_sn.png" data-yashareL10n="ru" data-yashareType="none" data-yashareQuickServices="vkontakte,facebook,twitter,odnoklassniki,moimir" data-yashareTitle="Moneypoolator - рынок прогнозов" data-yashareDescription="Фондовые индексы теряют 1,5% на каждые 10% увеличения вероятности войны. Рынок прогнозов Moneypoolator поможет сохранять и зарабатывать деньги в неустойчивом мире."></div> 
                            </c:when>
                            <c:otherwise>
                                <div class="yashare-auto-init" data-yashareLink="http://moneypoolator.ru/landing31" data-yashareImage="http://moneypoolator.ru/_img3/logo2_for_sn.png" data-yashareL10n="ru" data-yashareType="none" data-yashareQuickServices="vkontakte,facebook,twitter,odnoklassniki,moimir" data-yashareTitle="Moneypoolator - рынок прогнозов" data-yashareDescription="В 94 случаях из 100 коллективная оценка оказывается точней индивидуальной. Узнайте, как правильно прогнозировать будущие события и зарабатывать на этом, на рынке прогнозов Moneypoolator."></div> 
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <p>Мы хотим создать большое и увлеченное сообщество на основе сайта Moneypoolator и просим в этом вашей помощи. Надеемся, вы оценили идею нашего сервиса и его преимущества для себя.</p>
                    <p>Мы уверены, что среди ваших друзей, родственников и знакомых может найтись несколько людей, которым было бы интересно попробовать себя в увлекательном процессе прогнозирования событий будущего и заработать на этом!</p>
                </section>
                <div class="clear"></div>
            </div>	
        </div>
        <div id="container-thesis5">
            <article id="thesis5" class="thesis">
                <h6>Спасибо за вашу помощь!</h6>
            </article>	
        </div>
        <!-- Yandex.Metrika counter -->
        <script type="text/javascript">
            <c:import url="/WEB-INF/javascript/yandex-metrika-counter.js" />
        </script>
        <noscript><div><img src="//mc.yandex.ru/watch/24442862" style="position:absolute; left:-9999px;" alt="" /></div></noscript>
        <!-- /Yandex.Metrika counter -->
    </body>
</html>
