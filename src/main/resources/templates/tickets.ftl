<#import "parts/common.ftl" as c>
<#import "spring.ftl" as spring>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<@c.page "page.afisha.tickets">
    <div id="container">
        <div id="formDiv">
            <form action="${springMacroRequestContext.contextPath}/afisha/session/${session.getSessionId()}/tickets" method="post">

                <input id="places" name="placeNumber" value="" hidden/>
                <input name="session" value="${session.getSessionId()}" hidden/>
                <input name="userId" value="<@security.authorize access="isAuthenticated()">
                <@security.authentication property="principal.username"/>
                </@security.authorize>" hidden/>

                <div id="stage" class="centered">
                    <@spring.message "page.afisha.screen"/>
                </div>
                <br/>
                <br/>

                <#list 1..rowsNumber as t>
                    <div class="row">
                        <div class="col-2">
                            <p class="tierNum">${t}</p>
                        </div>
                        <div class="col-10">
                            <#list 1..seatsPerRow as seat>
                                <input type="button" id="btn${seat + (t - 1) * seatsPerRow}"
                                       class="atbtn btn" value="${seat + (t - 1) * seatsPerRow}"
                                       <#if sessionPlaces?seq_contains(seat + (t - 1) * seatsPerRow)>style="background-color: red"
                                       disabled</#if>
                                       onclick="selectTicket(value)"/>
                            </#list>
                        </div>
                    </div>
                </#list>
                <input type="submit" value="<@spring.message "form.submit"/>"/>
            </form>
        </div>
    </div>
</@c.page>