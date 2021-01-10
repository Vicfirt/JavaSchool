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
            <tr>
                <th class="align-middle" scope="row">
                    <img height="100px" src="${item.getProduct().getProductImage()}">
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
                           max="${item.getProduct().getAmountInStock()!"1"}" type="text" size="5" value="${item.getProductCount()}"
                           name='count' onkeyup="change(this)">
                    <a href="/cart/modification?element_Id=${item.getId()}&quantity=${item.getProductCount()+1}">
                        <i class="fas fa-plus"></i></a>
                </td>
                <td class="align-middle">Price</td>
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
            <form action ="/cart/confirmation" method="post">
                <button type="submit" class="btn btn-primary float-right">Confirm</button>
            </form>
        </div>
    <#else>

        <div>
            <h4 class="text-muted text-center">Your cart is empty.</h4>
        </div>
    </#if>


</div>
    <#include "footer.ftl">



</div>
    <script>
        let timeout = null;
        function change(element)
        {   clearTimeout(timeout);
            let quantity = element.value;
            if (quantity === 0) quantity = 1;
            let id = element.id;
            let theUrl = "" + id + "" + quantity;
            timeout = setTimeout(function(){location.href = theUrl} , 1000);
        }
    </script>
</@home.home>