<#import "partials/main.ftlh" as main/>

<@main.renderWith scripts=[] styles=["/css/main.css"]>
    <div class="container">
        <p id="authorized" hidden> authorized </p>
        <p id="unauthorized" hidden> unauthorized </p>
        <div class="cont-themes border border-rounded border-dark mt-4">
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
        </div>
        <#include "partials/pagination.ftlh">
    </div>

</@main.renderWith>
