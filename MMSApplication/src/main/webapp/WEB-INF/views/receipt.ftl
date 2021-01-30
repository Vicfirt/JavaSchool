<#import "common_home.ftl" as home>
<#import "header.ftl" as header>
<@home.home>

<#include "header.ftl">
<div style="margin-top: 100px">
<h1 align="center" class="display-4 mb-5">Order No: ${order.getOrderId()}</h1>
<table class="table table-striped text-center">
    <thead>
    <tr>
        <th scope="col">Photo</th>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col">Quantity</th>
        <th scope="col">Summary</th>

    </tr>
    </thead>
    <tbody>
    <#list orders as order>

        <tr>

            <th class="align-middle" scope="row">
                <img height="100px"
                        <#if order.getProduct().getProductImage() != "">

                            src="${order.getProduct().getProductImage()}"

                        <#else>
                            src="/media/Product_${order.getProduct().getProductId()}.jpg"
                        </#if>

                     alt="Iphone">
            </th>
            <td class="align-middle">${order.getProduct().getProductName()}</td>
            <td class="align-middle">$${order.getPrice()}</td>
            <td class="align-middle">${order.getProductCount()}</td>
            <td class="align-middle">$${order.getTotalPrice()}</td>


        </tr>
    </#list>
    </tbody>
</table>
    <div>
        <h5 style="display: inline;">Total: $${order.getTotal()}</h5>
    </div>

</div>

    <#include "footer.ftl">



</@home.home>

