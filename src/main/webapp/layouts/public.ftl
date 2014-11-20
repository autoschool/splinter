<#macro layout title="Splinter">
<html>
<head>
    <meta charset="utf-8">
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/public/css/vendor/bootstrap.min.css">
    <link rel="stylesheet" href="/public/css/vendor/font-awesome.min.css">
    <link rel="stylesheet" href="/public/css/main.css">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="/public/js/vendor/html5shiv.js"></script>
    <script src="/public/js/vendor/respond.min.js"></script>
    <![endif]-->
</head>

<body>
    <div id="wrap">
        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a href="/" class="navbar-brand">Splinter</a>
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="navbar-collapse collapse" id="navbar-main">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="/posts/">Все посты</a>
                        </li>
                        <li>
                            <a href="/authors/">Авторы</a>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/about">О проекте</a></li>
                        <li><a href="/login/" data-toggle="modal" data-target="#authForm">Вход</a></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="download">Iasmani Pinazo <span class="caret"></span></a>
                            <ul class="dropdown-menu" aria-labelledby="download">
                                <li><a href="/profile/"><i class="fa fa-gear"></i> Профиль пользователя</a></li>
                                <li><a href="/posts/new"><i class="fa fa-plus"></i> Создать пост</a></li>
                                <li><a href="/logout"><i class="fa fa-sign-out"></i> Выход</a></li>
                            </ul>
                        </li>
                        <li>
                            <form class="navbar-form" role="search">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Поиск...">
                                    <span class="input-group-btn">
                                      <button type="submit" class="btn btn-default">
                                        <span class="fa fa-search">
                                          <span class="sr-only">Поиск...</span>
                                        </span>
                                      </button>
                                    </span>
                                </div>
                            </form>
                        </li>
                    </ul>

                </div>
            </div>
        </div>

        <div class="container" id="content">
            <#nested />
        </div>

        <div id="push"></div>
    </div>

    <footer id="footer">
        <div class="container">
            <div class="row">
                <div class="col-md-12 copyright">
                    <p>
                        Copyright 2014
                    </p>
                </div>
            </div>
        </div>
    </footer>

    <div id="back-to-top" >
        <a href="#" style=""><i class="fa fa-angle-up"></i></a>
    </div>

    <#include "/layouts/modal_auth_form.ftl">

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="/public/js/vendor/jquery-1.11.1.min.js"><\/script>')</script>
    <script src="/public/js/vendor/bootstrap.min.js"></script>
    <script src="/public/js/main.js"></script>
</body>
</#macro>