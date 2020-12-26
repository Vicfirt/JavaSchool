<#import "common_home.ftl" as home>
<#import "header.ftl" as header>
<@home.home>
    <div class="row">
        <@header.header></@header.header>
    </div>

    <div class="row" style="margin-top: 100px">
        <#list orders as order>

            <div class="col">
                ${order.orderId}
            </div>
            <div class="col">
                ${order.orderCount}
            </div>

            <div class="col">

                ${order.total}
            </div>


        </#list>

    </div>




</@home.home>