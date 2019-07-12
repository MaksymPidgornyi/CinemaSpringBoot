<#macro page title>
<#import "/spring.ftl" as spring>

<!DOCTYPE html>
<html>
<head>
    <title><@spring.message "${title}"/></title>
    <meta charset="UTF-8"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
</head>

<body>
<header>
    <p id="app-name"><@spring.message "page.header"/></p>
    <a href="?lang=ua">ua</a>
    <a href="?lang=en">en</a>
</header>
<#nested>
<footer>
    <p class="paragraph"><@spring.message "page.footer.about"/></p>
    <p><@spring.message "page.footer.me"/></p>
</footer>
</body>
</html>

</#macro>