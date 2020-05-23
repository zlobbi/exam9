<#import "partials/main.ftlh" as main/>

<@main.renderWith scripts=[] styles=["/css/main.css"]>
    <div class="container row">
        <div class="col-lg-3 d-flex justify-content-around my-2">
            <div class="card ab-us-card">
                <img src="images/${user.image}" class="card-img-top" alt="">
                <div class="ctxt text-center">
                    <p class="card-text py-2">${user.login}</p>
                    <div class="card-social d-flex justify-content-around mt-2 text-dark">
                        <a href="#"><i class="fab fa-skype"></i></a>
                        <a href="#"><i class="fas fa-globe"></i></a>
                        <a href="#"><i class="fab fa-twitter"></i></a>
                        <a href="#"><i class="fab fa-google-plus-g"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="cont-themes my-2 col-9 d-flex">
                <#list themes>
                    <table class="table table-hover text-left">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">Theme</th>
                            <th scope="col">Date</th>
                            <th scope="col">Author</th>
                            <th scope="col">Comments</th>
                        </tr>
                        </thead>
                        <#items as t>
                            <tbody>
                            <tr onclick="location.href='/theme/${t.id}'" style="cursor: pointer;">
                                <td>${t.theme}</td>
                                <td>${t.time}</td>
                                <td class="username">${t.userName}</td>
                                <td class="text-center">${t.comments}</td>
                            </tr>
                            </tbody>
                        </#items>
                    </table>
                </#list>
            <#include "partials/pagination.ftlh">
        </div>
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
<#--        <#include "partials/pagination.ftlh">-->
    </div>
</@main.renderWith>


