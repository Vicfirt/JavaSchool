<#import "common_home.ftl" as home>

<@home.home>

    <#include "header.ftl">

    <div class="mt-5 pt-4">

        <div class="container dark-grey-text mt-5">
            <div class="row wow fadeIn">

                <div class="col-md-6 mb-4 ">
                    <img
                             <#if product.productImage != "">

                            src="${product.productImage}"

                            <#else>
                                src="/media/Product_${product.getProductId()}.jpg"
                                     </#if>

                            alt="Iphone" class="img-fluid">
                </div>
                <div class="col-md-6 mb-4">
                    <div class="p-4">
                        <div class="mb-3">
                            <a href="/catalog/category/${product.categoryId}">
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

                            <a href="/catalog/brand/${product.getProductBrand()}">
                                <span class="badge red mr-1">${product.getProductBrand()}</span>
                            </a>
                        </div>

                        <p class="lead">
                            <span class="mr-1">
                               $ ${product.productPrice}
                            </span>

                        </p>

                        <p>${product.productBrand} ${product.productName}
                        <#if product.productModel??>
                        ${product.productModel}
                        </#if>
                        </p>

                         <#if product.productDescription??>
                        <p class="lead font-weight-bold">Description</p>
                        <p>${product.productDescription}</p>
                         </#if>

                        <#if product.productWeight??>
                            <p class="lead font-weight-bold">Weight</p>
                            <p>${product.productWeight} g.</p>

                        </#if>
                        <#if product.productCapacity??>
                            <p class="lead font-weight-bold">Capacity</p>
                            <p>${product.productCapacity} cm^2.</p>

                        </#if>

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