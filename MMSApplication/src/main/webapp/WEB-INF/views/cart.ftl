<#import "common_home.ftl" as home>
<@home.home>
<div>
    <#list cartitems as cartitem>

        <div>
            ${cartItem.getProduct().getProductName()}
        </div>
    </#list>
</div>
</@home.home>