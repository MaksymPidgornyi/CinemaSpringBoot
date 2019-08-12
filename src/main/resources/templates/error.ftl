<#import "parts/common.ftl" as c/>
<#import "spring.ftl" as spring/>

<@c.page "page.error.title">
    <div id="container">
        <div id="errorContainer" class="centered">
            <#if errorMessage?has_content>
                <h2><@spring.message "${errorMessage}"/></h2>
            </#if>
        </div>
    </div>
</@c.page>