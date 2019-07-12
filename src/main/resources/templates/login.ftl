<#import 'parts/common.ftl' as c>
<#import "/spring.ftl" as spring>

<@c.page "page.login.login">
    <div id="container">
        <div id="loginFormContainer">
            <form action="/login" method="post">
                <label for="loginInput"><@spring.message "page.login.login"/></label><input type="text" id="loginInput" name="username" class="field"/><br/>
                <label for="passwordInput"><@spring.message "page.login.password"/></label><input type="password" id="passwordInput" name="password" class="field"><br/>
                <input type="submit" value="<@spring.message "page.login.login"/>"/>
            </form>
        </div>
    </div>
</@c.page>