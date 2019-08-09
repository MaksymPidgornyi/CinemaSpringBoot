<#import "parts/common.ftl" as c/>
<#import "parts/form" as f/>
<#import "/spring.ftl" as spring/>

<@c.page "page.add.title">
    <div class="row justify-content-center">
        <div class="formDiv col">
            <p id="actorFormHeader" class="formHeader"><@spring.message "form.actor.header"/></p>
            <@f.artistForm "${springMacroRequestContext.contextPath}/actor"></@f.artistForm>
        </div>
        <div id="directorForm" class="formDiv col">
            <p id="directorFormHeader" class="formHeader"><@spring.message "form.director.header"/></p>
            <@f.artistForm "${springMacroRequestContext.contextPath}/director"></@f.artistForm>
        </div>
    </div>
</@c.page>