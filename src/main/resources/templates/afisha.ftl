<#import "parts/common.ftl" as c>
<#import "spring.ftl" as spring>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<@c.page "page.afisha.title">
    <div id="container">
        <div id="dateSelector" class="centered">
            <button><a href="?date=${sessionsDate.minusDays(1)}"><@spring.message "page.afisha.prevday"/></a></button>
            <button>${sessionsDate}</button>
            <button><a href="?date=${sessionsDate.plusDays(1)}"><@spring.message "page.afisha.nextday"/></a></button>
        </div>
        <#if sessions.content?has_content>
            <#list sessions.content as s>
                <div id="session${s.sessionId}" class="sessionBlock">
                    <p><@spring.message "page.afisha.movie"/>: ${s.film.filmName}</p>
                    <br/>
                    <p><@spring.message "page.afisha.start"/>: ${s.startTime}</p>
                    <br/>
                    <p><@spring.message "page.afisha.end"/>: ${s.endTime}</p>
                    <br/>
                    <button class="sessionBtn btn-info">
                        <a class="actionLink" href="/${s.sessionId}"><@spring.message "page.afisha.tickets"/></a>
                    </button>
                </div>
            </#list>
        <#else>
            <p class="paragraph centered"><@spring.message "page.afisha.nosessions"/></p>
        </#if>
        <div id="addSession" class="centered">
            <@security.authorize access='hasAuthority("ADMIN")'>
                <#if !sessions.hasContent() || (sessions.content.get(sessions.content.size() - 1).endTime < maxSessionStartTime)>
                    <@c.modalbp "page.afisha.addsession">
                        <label for="film"><@spring.message "page.afisha.choose.movie"/></label>
                        <input id="film" name="film" type="text" disabled/>

                        <div class="dropdown">
                            <button onclick="showFunction()" class="dropbtn"><@spring.message "page.afisha.choose.movie"/></button>
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

                        <label for="time"><@spring.message "page.afisha.settime"/></label>
                        <input id="time" type="time" name="time" min="${minSessionStartTime}" max="${maxSessionStartTime}"/>
                        <#if timeError??>
                        <p><@spring.message "${timeError}"/></p>
                        </#if>
                        <input type="submit" name="submitBtn" value="<@spring.message "form.submit"/>"/>

                    </@c.modalbp>
                </#if>
            </@security.authorize>
        </div>
    </div>
</@c.page>