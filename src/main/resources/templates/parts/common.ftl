<#macro page title>
    <#import "/spring.ftl" as spring>
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

    <!DOCTYPE html>
    <html lang="${springMacroRequestContext.locale}">
    <head>
        <title><@spring.message "${title}"/></title>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous"/>
        <link rel="stylesheet" type="text/css" href="${springMacroRequestContext.contextPath}/css/main.css"/>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
                crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
                crossorigin="anonymous"></script>
        <script src="${springMacroRequestContext.contextPath}/js/script.js"></script>
        <@security.csrfMetaTags/>
    </head>

    <body>
    <header>
        <div class="row">
            <div id="appNameContainer" class="col-4">
                <p id="app-name"><@spring.message "page.header"/></p>
            </div>
            <div class="col-5"></div>
            <div id="langParamContainer" class="col-1">
                <a href="?lang=ua"><img src="${springMacroRequestContext.contextPath}/images/uaFlag.png" class="flag" alt="ua"/></a>
                <a href="?lang=en"><img src="${springMacroRequestContext.contextPath}/images/ukFlag.png" class="flag" alt="en"/></a>
            </div>
            <div class="col-1 logDiv">
                <@security.authorize access="isAuthenticated()">
                    <p><@security.authentication property="principal.username"/></p>
                </@security.authorize>
            </div>
            <div class="col-1 justify-content-end logDiv">
                <@security.authorize access="isAuthenticated()">
                    <button><a class="actionLink" href="/logout"><@spring.message "page.header.logout"/></a></button>
                </@security.authorize>
                <@security.authorize access="! isAuthenticated()">
                    <button><a class="actionLink" href="/login"><@spring.message "page.login.login"/></a></button>
                </@security.authorize>
            </div>

        </div>
        <div id="actionsDiv">
            <div class="row actionsRow">
                <div class="action col-2">
                    <a class="actionLink" href="/afisha">
                        <@spring.message "page.afisha.title"/>
                    </a>
                </div>

                <@security.authorize access='hasAuthority("ADMIN")'>
                    <div class="action col-2">
                        <a href="/add-movie" class="actionLink">
                            <@spring.message "page.addmovie.title"/>
                        </a>
                    </div>

                    <div class="action col-2">
                        <a href="/add-artist" class="actionLink">
                            <@spring.message "page.add.title"/>
                        </a>
                    </div>
                </@security.authorize>
            </div>
        </div>
    </header>

    <#nested>

    <footer>
        <p class="paragraph"><@spring.message "page.footer.about"/></p>
        <p><@spring.message "page.footer.me"/></p>
    </footer>
    </body>
    </html>

</#macro>

<#macro modalbp modalTitle>
    <#import "/spring.ftl" as spring>

    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
        <@spring.message "${modalTitle}"/>
    </button>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><@spring.message "${modalTitle}"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <#nested>
                </div>
                <div class="modal-footer">
                </div>
            </div>
        </div>
    </div>
<#--        MODAL END        -->
</#macro>

<#macro pager page sizes>
    <#import "/spring.ftl" as spring>

    <div>
        <label for="pagSize"><@spring.message "page.afisha.size"/></label>

        <ul id="pagSize" class="pagination">
            <#list sizes as s>
                <#if s == page.getSize()>
                    <li class="page-item active">
                        <a class="page-link" href="#" tabindex="-1">${s}</a>
                    </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="?page=${page.getNumber()}&size=${s}" tabindex="-1">${s}</a>
                    </li>
                </#if>
            </#list>
        </ul>

        <ul class="pagination">
            <#if page.getNumber() == 0>
            <li class="page-item disabled">
                <a class="page-link" href="#"
                   tabindex="-1"><@spring.message 'pagination.prev'/></a>
            </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="?page=${page.getNumber() - 1}&size=${page.getSize()}"
                           tabindex="-1"><@spring.message 'pagination.prev'/></a>
                    </li>
            </#if>

            <#list 1..page.getTotalPages() as p>
                <#if (p - 1) == page.getNumber()>
                    <li class="page-item active">
                        <a class="page-link" href="#" tabindex="-1">${p}</a>
                    </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="?page=${p - 1}&size=${page.getSize()}" tabindex="-1">${p}</a>
                    </li>
                </#if>
            </#list>

            <#if page.getNumber() == page.getTotalPages()>
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1"><@spring.message 'pagination.next'/></a>
                </li>
            <#else>
                <li class="page-item">
                    <a class="page-link" href="?page=${page.getNumber() + 1}&size=${page.getSize()}"
                       tabindex="-1"><@spring.message 'pagination.next'/></a>
                </li>
            </#if>
        </ul>
    </div>
</#macro>