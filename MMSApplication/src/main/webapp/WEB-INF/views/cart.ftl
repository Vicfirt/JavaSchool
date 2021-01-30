<#import "common_home.ftl" as home>
    <#import "header.ftl" as header>
<@home.home>

    <#include "header.ftl">


<div class="container" style="margin-top: 100px">
    <h1 align="center" class="display-4 mb-5">My Cart</h1>
    <table class="table table-striped text-center">
        <thead>
        <tr>
            <th scope="col">Photo</th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Subtotal</th>
            <th scope="col">Action</th>

        </tr>
        </thead>
        <tbody>
        <#list cartItems as item>
        <form action ="/cart/confirmation" method="get">
            <tr>
                <th class="align-middle" scope="row">
                    <img height="100px"
                            <#if item.getProduct().getProductImage() != "">

                                src="${item.getProduct().getProductImage()}"

                            <#else>
                                src="/media/Product_${item.getProduct().getProductId()}.jpg"
                            </#if>

                         alt="Iphone">
                </th>
                <td class="align-middle">${item.getProduct().getProductName()}</td>
                <td class="align-middle">$${item.getElementPrice()}</td>

                <td class="align-middle">
                    <a <#if item.productCount == 1>
                            href="/cart/deletion?element_Id=${item.getId()}"
                            <#else >
                            href="/cart/modification?element_Id=${item.getId()}&quantity=${item.getProductCount()-1}">
                        </#if>
                            <i class="fas fa-minus"></i></a>

                    <input min="1" id="${item.getProduct().getProductId()}"
                           max="${item.getProduct().getAmountInStock()!1}" type="number" value="${item.getProductCount()}"
                           name='count'>
                    <a href="/cart/modification?element_Id=${item.getId()}&quantity=${item.getProductCount()+1}">
                        <i class="fas fa-plus"></i></a>

                </td>
                <td class="align-middle">$${item.getProductCount() * item.getElementPrice()}</td>
                <td class="align-middle">

                    <a href="/cart/deletion?element_Id=${item.getId()}" style="color: red" >Remove</a>

                </td>

            </tr>
        </#list>
        </tbody>
    </table>
    <#if cartItems?has_content >
        <div>
            <h5 style="display: inline;">Total: $${customer.getCart().getCartTotal()}</h5>

            <#if !customer.customerAddress.country??>

                <h4 class="text-muted text-center">Your profile has no address. Please add an address.</h4>

                <a href="/profile" type="button" class="btn btn-primary float-right">Add address</a>

                <#else>
                <button type="submit" class="btn btn-primary float-right">Confirm</button>

            </#if>

        </div>
    <#else>

        <div>
            <h4 class="text-muted text-center">Your cart is empty.</h4>
        </div>
    </#if>
    </form>


</div>
<div style="margin-top: 100px">
        <#include "footer.ftl">
</div>
</@home.home>
