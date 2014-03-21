<%-- 
    Document   : landing32
    Created on : 21.03.2014, 16:06:57
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
        <h1>Moneypoolator &mdash; рынок прогнозов</h1>
        <div id="container-header">
            <header>
                <div id="logo"><a href="/"><img src="<c:url value="/_img3/logo.png"/>" alt="Moneypoolator - рынок прогнозов" /></a></div>
                <div id="header-right">
                    <h2>Позвольте рассказать вам о рынке прогнозов Moneypoolator</h2>
                    <div class="information">
                        <p><strong>Это биржа, где Вы можете зарабатывать на прогнозах экономических и политических событиях.</strong></p>
                        <p>Экономические кризисы и геополитические тенденции - все это может стать прямым источником дохода.</p>
                    </div>
                    <form action="">
                        <p><strong>Оставьте нам свой e-mail, чтобы узнать как это работает</strong></p>
                        <p><input class="text" type="text" value="Email адрес" /><input class="submit" type="submit" value="Получить документ" /></p>
                        <p class="confidentiality">Мы не будем распростронять информацию о вашем электронном адресе.</p>
                    </form>
                </div>
                <div class="clear"></div>
            </header>
        </div>
        <div id="container-thesis1">
            <article id="thesis1" class="thesis">
                <p>Moneypoolator позволит вам играть в Большую игру по собственным правилам. И вот почему</p>
            </article>	
        </div>
        <div id="container">
            <div id="content" class="content">
                <section class="column-left">
                    <ul>
                        <li class="earnings">
                            <h3>Прямой заработок на конкретных событиях</h3>
                            <p>Торгуйте значимые для Вас события. <br />Каждый контракт привязан к исходу конкретного события.</p>
                        </li>
                        <li class="plainness">
                            <h3>Простой механизм <br />ставок</h3>
                            <p>Минимум усилий для понимания работы системы.</p>
                        </li>
                    </ul>
                </section>
                <section class="column-right">
                    <ul>
                        <li class="awareness">
                            <h3>Повышение осведомленности</h3>
                            <p>Вы всегда в курсе того, как рынок оценивает вероятность событий.</p>
                        </li>
                        <li class="markets">
                            <h3>Широкий спектр разных тематических рынков</h3>
                            <p>Вы можете торговать события из сферы экономики, политики, науки, культуры и многое другое.</p>
                        </li>
                    </ul>
                </section>
                <div class="clear"></div>
            </div>	
        </div>
        <div id="container-thesis2">
            <article id="thesis2" class="thesis">
                <p>Мы скоро запустимся. Присоединяйтесь к нам, чтобы проверять правильность своих гипотез и зарабатывать на этом!</p>
            </article>	
        </div>
        <div id="container-footer">
            <footer>
                <form action="">
                    <p><strong>Оставьте нам свой e-mail, чтобы мы могли <br />сообщить вам о запуске</strong></p>
                    <p><input class="text" type="text" value="Email адрес" /><input class="submit" type="submit" value="Сообщить о запуске" /></p>
                </form>
                <div class="clear"></div>
            </footer>
        </div>
    </body>
</html>
