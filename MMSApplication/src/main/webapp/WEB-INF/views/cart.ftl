<#import "common_home.ftl" as home>
<@home.home>
<div>
    <#list cartItems as cartItem>

        <div>
            ${cartItem.product.productName}
        </div>
    </#list>
</div>
</@home.home>