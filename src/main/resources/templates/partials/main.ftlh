<#macro renderWith title="Welcome to My-Forum!" scripts=[] styles=["/css/main.css"]>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <title>${title}</title>

        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
        <#list styles as styleSource>
            <link rel="stylesheet" href="${styleSource}">
        </#list>
    </head>
    <body>
    <header class="header">
        <div class="container">
            <nav class="navbar navbar-expand-lg">
                <a class="navbar-brand mr-5 text-upercease" href="/">.<b>My</b>Forum</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-nav"
                        aria-controls="main-nav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon text-light"><i class="fas fa-bars"></i></span>
                </button>
                <div class="collapse navbar-collapse" id="main-nav">
                    <ul class="navbar-nav text-uppercase mx-auto">
                        <li class="nav-item">
                            <a class="nav-link pr-4 active" href="/">Main</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pr-4" href="/theme/add">Add theme </a>
                        </li>
                    </ul>
                </div>

                <hr class="my-3 border-white d-lg-none">

                <#if user??>

                    <a href="/profile" type="button" class="btn btn-su "><i class="fas fa-id-badge"></i></a>
                    <form action="/logout" method="post" class="d-flex">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                   value="${(_csrf.token)!'--no-token--'}"/>
                        </#if>
                        <button type="submit" class="btn btn-su"><i class="fas fa-sign-out-alt"></i></button>
                    </form>
                <#else >
                    <a href="/login" type="button" class="btn-su ml-auto mr-2">
                        <i class="fas fa-sign-in-alt px-3"></i>
                    </a>
                    <a href="/register" type="button" class="btn-su ml-auto" >
                        <i class="fas fa-registered px-3"></i>
                    </a>
                </#if>
            </nav>
            <#--            <button type="button" class="btn-su ml-auto pl-3 pr-5 py-2" id="mo" data-toggle="modal" data-target="#head-su">-->
            <#--                join-->
            <#--            </button>-->
        </div>
    </header>
    <#--
    например если бы у нашего сайта было какое-то верхнее меню,
    то мы могли бы его тут указать
    и оно появится сразу на всех страницах которые используют
    этот шаблон
    -->

    <div class="container text-center">
        <#--
         элемент nested указывает что в этом место
         будет подставлено некоторое содержимое,
         которое передаётся как тело макро-функции при её вызове
        -->
    </div>
    <#nested>

    <footer class="footer">
        <div class="container pt-3 pb-2 my-0">
            <p class="pt-2 pb-0 mb-0">email: kad@gmail.com <em> password: zlobbi </em></p>
        </div>
    </footer>
    <!-- modal-login -->
    <div class="su-blank modal fade" id="head-su" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="sbch modal-header pl-4">
                    <h5 class="modal-title" id="head-suTitle">Sign UP</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true" class="text-light pr-1">&times;</span>
                    </button>
                </div>
                <div class="modbod modal-body pb-0">
                    <form class="px-4 py-3" method="post" id="login-form" action="/login">
                        <div class="form-group">
                            <label class="lab" for="emad">Email address</label>
                            <input type="email" class="form-control" id="emad" placeholder="email@example.com"
                                   aria-label="Email address" required>
                        </div>
                        <div class="form-group">
                            <label class="lab" for="passw">Password</label>
                            <input type="password" class="form-control" id="passw" placeholder="Password"
                                   aria-label="password" required>
                        </div>
                        <div class="su-foot card-footer row remember">
                            <button class="btn btn-outline-primary btn-block btn-su" type="submit">sign in</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <#list scripts as scriptSource>
        <script src="${scriptSource}"></script>
    </#list>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>

    </body>
    </html>

</#macro>