<#import "common_home.ftl" as home>


<@home.home>

    <#include "header.ftl">

    <div class="row">

    </div>
    <div class="row" style="margin-top: 80px;">
        <div class="col-lg-3">
            <div class="row">
            <strong>Filter By</strong>
            </div>
            <div class="row">

                <div class="btn-group dropright">
                    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        Category
                    </button>
                    <div class="dropdown-menu">
                        <a href="#">Somelink</a>
                        <a href="#">Somelink</a>
                    </div>
                </div>

                <div class="btn-group dropright">
                    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        Brand
                    </button>
                    <div class="dropdown-menu">
                        <a href="#">Somelink</a>
                        <a href="#">Somelink</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-9">
            <div class="row wow fadeIn">
                    <#list products as product>
                        <div class="col col-md-4 lg-6 md-4">
                        <div class="card">
                            <div class="view overlay">
                                <img class="card-img-top" src="${product.productImage}" alt="Iphone" height="200px" width="50px">
                                <a href="/product/${product.productId}">
                                    <div class="mask rgba-white-slight"></div>
                                </a>
                            </div>
                            <div class="card-body text-center">
                                <a href="/product/${product.productId}" class="grey-text">
                                    <h5>${product.productBrand}</h5>
                                </a>

                                <h5>
                                    <strong>
                                        <a href="/product/${product.productId}" class="dark-grey-text">${product.productName} ${product.productModel}
                                            <span class="badge badge-pill danger-color">NEW</span></a>
                                    </strong>
                                </h5>
                                <h4 class="font-weight-bold blue-text">
                                    <strong>${product.productPrice} $</strong>
                                </h4>
                                <a href="cart/add/product/${product.productId}" class="btn btn-primary">Add to Cart</a>
                            </div>
                        </div>
                        </div>
                    </#list>
            </div>

        </div>
    </div>
        <#include "footer.ftl">
</@home.home>