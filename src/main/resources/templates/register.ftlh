<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <section id="page-splash" class="page-splash mt-5">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <h3>Registration</h3>
                <form id="registration-form" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <input type="email" name="email" class="form-control mb-1" value="${form.email!''}"
                           placeholder="Email"><br>
                    <input type="text" name="login" class="form-control mb-1" value="${form.login!''}"
                           placeholder="Login"><br>
                    <input type="password" name="password" class="form-control mb-1" value="${form.password!''}"
                           placeholder="Password"><br>
                    <div class="g-recaptcha" data-sitekey="6LeJQfsUAAAAALmVZwzTwbuQmg6KoIl3_D6ADC76"></div>
                    <#if captchaError??>
                        <p style="color: red">${captchaError}</p>
                    </#if>
                    <button class="btn btn-outline-primary btn-block btn-su" type="submit">Register</button>
                    <br>
                    <#if errors?? >
                        <p>Invalid fields!</p>
                        <#list errors as fieldError>
                            <p style="color: red">${fieldError.field}
                                - ${fieldError.defaultMessage!'-- no message --'}</p>
                        </#list>
                    </#if>
                    <#if user?? >
                        <p>${user.login} - ${user.email} already exists!</p>
                    </#if>
                </form>
            </div>
        </div>
    </section>
</@main.renderWith>
