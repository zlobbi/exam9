<#import "partials/main.ftlh" as main>
    <@main.renderWith scripts=["/js/login.js", "/js/main.js"] styles=["/css/main.css"]>
    <section id="page-splash" class="page-splash mt-5">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <h3>Login</h3>
                <form method="post" action="/login" id="login-form">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <#if error!false >
                        <p style="color:red">User not found!</p>
                    </#if>
                    <input type="email" name="username" class="form-control mb-1" value="${(dto.email)!''}" autofocus
                           placeholder="email"><br>
                    <input type="password" name="password" class="form-control mb-1" value=""
                           placeholder="password"><br>
                    <button class="btn btn-outline-primary btn-block btn-su" type="submit">sign in</button>
                    <br>
                    <a href="/register" class="btn btn-su btn-outline-primary btn-block" >registration</a>
                    <br>
                </form>
            </div>
        </div>
    </section>
</@main.renderWith>
