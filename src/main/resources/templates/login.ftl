<#import 'parts/common.ftl' as c>
<#import "/spring.ftl" as spring>

<@c.page "page.login.login">
    <div id="container">
        <div class="formDiv row">
            <div class="loginForm col-md-6 col-md-offset-3">
                <p class="formHeader"><@spring.message "page.login.login"/></p>
                <form action="/login" method="post">
                    <label for="loginInput"><@spring.message "page.login.login"/></label><input type="text"
                                                                                                id="loginInput"
                                                                                                name="username"
                                                                                                class="field"/><br/>
                    <label for="passwordInput"><@spring.message "page.login.password"/></label><input type="password"
                                                                                                      id="passwordInput"
                                                                                                      name="password"
                                                                                                      class="field"><br/>
                    <input type="submit" value="<@spring.message "page.login.login"/>"/>
                </form>
                <br/>
                <a id="regLink" href="/register"><@spring.message "page.register.title"/></a>
                <br/>
            </div>
        </div>
    </div>
</@c.page>