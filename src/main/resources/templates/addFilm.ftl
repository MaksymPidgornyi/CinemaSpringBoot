<#import "parts/common.ftl" as c/>
<#import "/spring.ftl" as spring/>

<@c.page "page.addmovie.title">
    <div id="container" xmlns="http://www.w3.org/1999/html">
        <div class="formDiv">
            <p class="formHeader"><@spring.message "page.addmovie.title"/></p>
            <form id="add-movie-form" method="post" action="/add-movie">
                <label for="filmName"><@spring.message "page.addmovie.moviename"/></label>
                <input id="filmName" name="filmName" type="text"/><br/>
                <#if filmNameError??>
                    <div class="invalid-feedback d-block">${filmNameError}</div>
                </#if>

                <label for="filmGenre"><@spring.message "page.addmovie.moviegenre"/></label>
                <select multiple size="4" id="filmGenre" name="filmGenre">
                    <#list genres?keys as k>
                        <option value="${k}"><@spring.message "${genres[k]}"/></option>
                    </#list>
                </select><br/>
                <#if filmGenreError??>
                    <div class="invalid-feedback d-block">${filmGenreError}</div>
                </#if>

                <label for="director"><@spring.message "page.addmovie.director"/></label>
                <input list="directors" size="4" id="director" name="director" autocomplete="off"/>
                <datalist id="directors">
                    <option disabled selected><@spring.message "page.addmovie.choose.director"/></option>
                    <#list directors as d>
                        <option value="${d.directorId}">${d}</option>
                    </#list>
                </datalist>
                <#if directorError?has_content>
                    <div class="invalid-feedback d-block">${directorError}</div>
                </#if>
                <br/>

                <label for="actors"><@spring.message "page.addmovie.actors"/></label>
                <input id="actors" name="actors" type="hidden"/>
                <@c.modalbp "page.addmovie.selectactors">
                    <#list actors as a>
                        <div class="row">
                            <div class="col"><p>${a.actorId + " " + a.firstName + " " + a.lastName}</p></div>
                            <div class="col">
                                <input id="btn${a.actorId}" type="button" onclick="addActor(${a.actorId})"
                                       value="<@spring.message "page.addmovie.addbtn"/>"/>
                                <input class="dbtn" id="dbtn${a.actorId}" type="button"
                                       onclick="deleteActor(${a.actorId})"
                                       value="<@spring.message "page.addmovie.deletebtn"/>"/>
                            </div>
                        </div>
                    </#list>
                </@c.modalbp>
                <#if actorsError??>
                    <div class="invalid-feedback d-block">${actorsError}</div>
                </#if>
                <br/>

                <label for="created"><@spring.message "page.addmovie.created"/></label>
                <input type="text" id="created" name="created"><br/>
                <#if createdError??>
                    <div class="invalid-feedback d-block">${createdError}</div>
                </#if>

                <label for="duration"><@spring.message "page.addmovie.duration"/></label>
                <input type="number" id="duration" name="duration"><br/>
                <#if durationError??>
                    <div class="invalid-feedback d-block">${durationError}</div>
                </#if>

                <input type="submit" value="<@spring.message "form.submit"/>">
            </form>
        </div>
    </div>
    <div id="clr"></div>
</@c.page>