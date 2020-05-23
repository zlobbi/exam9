<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <section id="page-splash" class="page-splash mt-5">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <h3>New theme</h3>
                <form method="post" action="/theme/add">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="form-group">
                        <label for="theme">Theme</label>
                        <input type="text" class="form-control" name="theme" id="theme" value="${form.theme!''}">
                    </div>
                    <div class="form-group">
                        <label for="description">Example textarea</label>
                        <textarea class="form-control" name="description" id="description" rows="3">${form.description!''}</textarea>
                    </div>
                    <div class="g-recaptcha" data-sitekey="6LeJQfsUAAAAALmVZwzTwbuQmg6KoIl3_D6ADC76"></div>
                    <#if captchaError??>
                        <p>${captchaError}</p>
                    </#if>
                    <button class="btn btn-outline-primary btn-block btn-su" type="submit">add</button>
                    <#if errors?? >
                        <p>Invalid fields!</p>
                        <#list errors as fieldError>
                            <p style="color: red">${fieldError.field}
                                - ${fieldError.defaultMessage!'-- no message --'}</p>
                        </#list>
                    </#if>
                </form>
            </div>
        </div>
    </section>
</@main.renderWith>
