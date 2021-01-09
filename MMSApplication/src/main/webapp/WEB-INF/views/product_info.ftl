<#import "common_home.ftl" as home>
<#import "header.ftl" as header>
    <#import "footer.ftl" as footer>
<@home.home>

    <#include "header.ftl">

    <div class="mt-5 pt-4">

        <div class="container dark-grey-text mt-5">
            <div class="row wow fadeIn">

                <div class="col-md-6 mb-4 ">
                    <img src="${product.productImage}" alt="Iphone" class="img-fluid">
                </div>
                <div class="col-md-6 mb-4">
                    <div class="p-4">
                        <div class="mb-3">
                            <a href="">
                                <span class="badge purple mr-1">
                                    <#if product.categoryId == 0>
                                        Books
                                        <#elseif product.categoryId == 1>
                                        Electronics
                                            <#else >
                                        Clothes
                                    </#if>
                                </span>
                            </a>
                            <a href="">
                                <span class="badge blue mr-1">New</span>
                            </a>
                            <a href="">
                                <span class="badge red mr-1">Sale</span>
                            </a>
                        </div>
                        <p class="lead">
                            <span class="mr-1">
                                ${product.productPrice}
                            </span>

                        </p>
                        <p class="lead font-weight-bold">Description</p>
                        <p>${product.productDescription}</p>

                        <#if customer?? && customer.role == "CUSTOMER">
                        <form action="../cart/add/product/${product.productId}" class="d-flex justify-content-left">
                            <button type="submit" class="btn btn-primary btn-md my-0 p">
                                Add to cart <i class="fa fa-shopping-cart ml-1"></i> </button>
                        </form>
                        </#if>
                    </div>


            </div>
        </div>
    </div>

    <#include "footer.ftl">

</@home.home>