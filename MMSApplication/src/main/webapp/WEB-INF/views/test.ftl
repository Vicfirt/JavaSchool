<#import "common_home.ftl" as home>

<@home.home>
<#list products as product>

    <div>${product.productName}</div>
</#list>
</@home.home>



