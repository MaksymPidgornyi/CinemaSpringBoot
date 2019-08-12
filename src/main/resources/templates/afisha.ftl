<#import "parts/common.ftl" as c>
<#import "spring.ftl" as spring>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign page=sessions.content/>

<@c.page "page.afisha.title">
    <div id="container">
        <div id="dateSelector" class="centered">
            <button><a href="?date=${sessionsDate.minusDays(1)}"><@spring.message "page.afisha.prevday"/></a></button>
            <button>${sessionsDate}</button>
            <button><a href="?date=${sessionsDate.plusDays(1)}"><@spring.message "page.afisha.nextday"/></a></button>
       </div>

        <@c.pager sessions sizes></@c.pager>

        <#if sessions.content?has_content>
            <#list sessions.content as s>
                <div id="session${s.sessionId}" class="sessionBlock bordered row">
                    <div class="col-8">
                        <p><@spring.message "page.afisha.movie"/>: ${s.film.filmName}</p>
                        <p><@spring.message "page.afisha.start"/>: ${s.startTime}</p>
                        <p><@spring.message "page.afisha.end"/>: ${s.endTime}</p>
                    </div>
                    <div class="col-4">
                        <button class="sessionBtn btn-info">
                            <a class="actionLink"
                               href="/afisha/session?session=${s.sessionId}"><@spring.message "page.afisha.tickets"/></a>
                        </button>
                        <@security.authorize access="hasAuthority('ADMIN')">
                            <button data-method="delete" type="button" class="cancelBtn btn-danger" id="${s.getSessionId()}" value="${s.getSessionId()}">
<#--                                    <a href="/afisha/delete/${s.getSessionId()}" data-method="delete">-->
                                        <@spring.message "page.afisha.cancel"/>
<#--                                    </a>-->
                            </button>
                        </@security.authorize>
                    </div>
                </div>
            </#list>
        <#else>
            <p class="paragraph centered"><@spring.message "page.afisha.nosessions"/></p>
        </#if>
        <div id="addSession" class="centered">
            <@security.authorize access='hasAuthority("ADMIN")'>
                <#if page?? || hasGap>
                    <@c.modalbp "page.afisha.addsession">
                        <form action="/afisha/session" method="post">
                            <input id="film" name="film" type="text" hidden/>
                            <#if filmError??>
                                <p>${filmError}</p>
                            </#if>
                            <input id="sessionDate" name="sessionDate" value="${sessionsDate}" hidden/>
                            <#if sessionDateError?has_content>
                                <p>${sessionDateError}</p>
                            </#if>
                            <div class="dropdown">
                                <label for="film"><@spring.message "page.afisha.choose.movie"/></label>
                                <button onclick="showFunction()" type="button"
                                        class="dropbtn"><@spring.message "page.afisha.choose.movie"/></button>
                                <br/>
                                <div id="myDropdown" class="dropdown-content">
                                    <input type="text" placeholder="Search.." id="myInput" onkeyup="filterFunction()">
                                    <#if movies?has_content>
                                        <#list movies as m>
                                            <div class="movieOption row no-gutters">
                                                <p class="col">${m.filmName}</p>
                                                <input class="ambtn dpbtn col-3" id="${m.filmId}btn" type="button"
                                                       onclick="selectMovie(${m.filmId})"
                                                       value="<@spring.message "page.afisha.select"/>"/>
                                                <input class="dmbtn dpbtn col-3" id="${m.filmId}dbtn" type="button"
                                                       onclick="deselectMovie(${m.filmId})"
                                                       value="<@spring.message "page.afisha.deselect"/>"/>
                                            </div>
                                        </#list>
                                    </#if>
                                </div>
                            </div>

                            <br/>
                            <label for="time"><@spring.message "page.afisha.settime"/></label>
                            <input id="time" type="time" name="startTime" min="${minSessionStartTime}"
                                   max="${maxSessionStartTime}"/>
                            <#if startTimeError??>
                                <p><@spring.message "${timeError}"/></p>
                            </#if>
                            <br/>
                            <input type="submit" name="submitBtn" value="<@spring.message "form.submit"/>"/>
                        </form>
                    </@c.modalbp>
                </#if>
            </@security.authorize>
        </div>
    </div>
    <div class="push"></div>
    <script src="js/cancelSession.js"></script>
    <script src="js/switchSize.js"></script>
</@c.page>