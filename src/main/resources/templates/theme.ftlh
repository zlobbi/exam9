<#import "partials/main.ftlh" as main/>

<@main.renderWith scripts=["/js/comment.js"] styles=["/css/main.css"]>
    <div class="container">
        <div class="d-flex justify-between mt-4 bg-dark text-light p-2">
            <span class="mr-auto ml-5 username">${theme.userName}</span> <span class="mr-auto username">${theme.theme}</span> <span class="mx-auto username">${theme.time}</span>
        </div>
        <div class="cont-themes my-4">
            <div class="card-body mb-3 border border-dark d-row">
                <p class="card-text">${theme.description}</p>
                <button type="button" id="com-but" class="btn btn-primary btn-su ml-auto" data-toggle="modal" data-target="#comment">
                    comment
                </button>
                <form hidden id="comment-form" action="/api/comment" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <input type="text" class="form-control" name="themeId" value="${theme.id}" hidden>
                    <div class="form-group">
                        <label for="message-text" class="col-form-label">Message:</label>
                        <textarea class="form-control" name="comment" id="message-text" autofocus></textarea>
                        <button type="submit" class="btn btn-su btn-primary" id="com-but">Send message</button>
                    </div>
                </form>
            </div>
            <#list comments as c>
                <div class="card mb-3 border border-dark">
                    <div class="row no-gutters">
                        <div class="col-2">
                            <img src="/images/${c.user.image!'no-image.jpg'}" class="card-img" alt="">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title d-flex">${c.time} <span class="username ml-4">${c.user.login}</span> <span class="badge badge-dark ml-auto">${c.user.comments}</span></h5>
                                <p class="card-text">${c.comment}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        <#--        Modal for comment-->
<#--        <div class="modal fade" id="comment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"-->
<#--             aria-hidden="true">-->
<#--            <div class="modal-dialog" role="document">-->
<#--                <div class="modal-content">-->
<#--                    <div class="modal-header sbch">-->
<#--                        <h5 class="modal-title" id="exampleModalLabel">New message</h5>-->
<#--                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">-->
<#--                            <span aria-hidden="true">&times;</span>-->
<#--                        </button>-->
<#--                    </div>-->
<#--                    <div class="modbod modal-body">-->
<#--                        <form method="post" action="/api/comment">-->
<#--                            <input type="text" class="form-control" name="themeId" value="${theme.id}" hidden>-->
<#--                            <div class="form-group">-->
<#--                                <label for="message-text" class="col-form-label">Message:</label>-->
<#--                                <textarea class="form-control" name="comment" id="message-text" autofocus></textarea>-->
<#--                            </div>-->
<#--                        </form>-->
<#--                    </div>-->
<#--                    <div class="su-foot modal-footer">-->
<#--                        <button type="button" class="btn btn-su btn-secondary" data-dismiss="modal">Close</button>-->
<#--                        <button type="submit"  class="btn btn-su btn-primary" id="com-but">Send message</button>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
<#--        </div>-->
        <#include "partials/pagination.ftlh">
    </div>
</@main.renderWith>


