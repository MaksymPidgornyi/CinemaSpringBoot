<#import 'parts/common.ftl' as c>
<#import "spring.ftl" as spring/>

<@c.page "page.register.title">
    <div id="container">
        <div class="formDiv">
            <p class="formHeader"></p>
            <form id="registrationForm" action="/register" method="post">

                <label for="login"><@spring.message "form.login"/></label><input type="text" name="login" id="login"/><br/>
                <#if loginError??>
                    <div class="invalid-feedback d-block">${loginError}</div>
                </#if>

                <label for="email"><@spring.message "form.email"/></label>
                <input type="email" name="email" id="email"><br/>
                <#if emailError??>
                    <div class="invalid-feedback d-block">${emailError}</div>
                </#if>

                <label for="firstName"><@spring.message "form.firstname"/></label>
                <input type="text" name="firstName" id="firstName"/><br/>
                <#if firstNameError??>
                    <div class="invalid-feedback d-block">${firstNameError}</div>
                </#if>

                <label for="lastName"><@spring.message "form.lastname"/></label>
                <input type="text" name="lastName" id="lastName"><br/>
                <#if lastNameError??>
                    <div class="invalid-feedback d-block">${lastNameError}</div>
                </#if>

                <label for="password"><@spring.message "form.password"/></label>
                <input type="password" name="password" id="password"/><br/>
                <#if passwordError??>
                    <div class="invalid-feedback d-block">${passwordError}</div>
                </#if>

                <label for="confirmation"><@spring.message "form.confirm"/></label>
                <input type="password" name="confirmation" id="confirmation"/><br/>
                <#if confirmationError??>
                    <div class="invalid-feedback d-block">${confirmationError}</div>
                </#if>
                <input type="submit" id="regButton" value="<@spring.message "form.submit.register"/>"/>

            </form>
        </div>
    </div>
</@c.page>