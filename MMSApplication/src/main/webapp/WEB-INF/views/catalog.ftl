<#import "common_home.ftl" as home>


<@home.home>

    <div class="row">

    <#include "header.ftl">

    </div>

    <div class="row">

    </div>
    <div class="row" style="margin-top: 80px;">
        <div class="col-lg-3">
            <div class="row">
            <h2>Filter By</h2>
            </div>
            <div class="row">

                <div class="btn-group dropright">
                    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        Category
                    </button>
                    <div class="dropdown-menu">
                        <a href="/catalog/category/1">Electronics</a>
                        <a href="/catalog/category/0">Books</a>
                        <a href="/catalog/category/2">Clothes</a>
                    </div>
                </div>

                <div class="btn-group dropright">
                    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        Brand
                    </button>
                    <div class="dropdown-menu">
                        <form class="form-inline" action="/catalog/brand">

                            <div class="md-form my-0">
                                <input type="text" class="form-control mr-sm-2" placeholder="Search" name="brandName" id="brandName"
                                       aria-label="Search">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div data-role="page">
                <div data-role="header">
                    <h3>Range by price</h3>
                </div>



                    <label for="amount">Price range:</label>

                    <input type="text" id="amount" name = "amount" readonly style="border:0; color:#f6931f; font-weight:bold;">

                    <div id="slider-range"></div>

                <form action="/catalog" method="get">

                    <input type="hidden" name="minValue" id="minValue" />
                    <input type="hidden" name="maxValue" id="maxValue" />

                    <input type="submit" value="Range">
                </form>

            </div>
        </div>

        <div class="col-lg-9">
            <div class="row wow fadeIn">
                <#if (products)??>
                    <#list products as product>
                        <div class="col col-sm-3">

                        <div class="card" style="width: 180px">
                            <div class="view overlay">
                                <img class="card-img-top" src="${product.productImage}" alt="Iphone" >
                                <a href="/product/${product.productId}">
                                    <div class="mask rgba-white-slight"></div>
                                </a>
                            </div>
                            <div class="card-body text-center">
                                <a href="/product/${product.productId}" class="grey-text">
                                    <h6>${product.productBrand} ${product.productName}</h6>
                                </a>

                                <h5 class="font-weight-bold blue-text">
                                    <strong>${product.productPrice} $</strong>
                                </h5>
                                <#if customer?? && customer.role == "CUSTOMER">
                                <a href="cart/product/${product.productId}" class="card-link btn btn-primary" >
                                    <i class="fa fa-shopping-cart ml-1"></i></a>
                                    <#elseif customer?? && customer.role == "EMPLOYEE">
                                        <div class="btn-group btn-group-sm" role="group">
                                            <a type="button" href="product/employee/edition/${product.productId}"
                                               class="card-link btn btn-success"><i class="fas fa-edit"></i></a>
                                            <a type="button" href="product/employee/deletion/${product.productId}"
                                               class="card-link btn btn-danger"><i class="fa fa-trash"
                                                                                   aria-hidden="true"></i></a>
                                        </div>
                                </#if>
                            </div>
                        </div>
                        </div>
                    </#list>
                    <#else>
                        <#list filteredProducts as product>
                            <div class="col col-md-2 lg-4 md-4">

                                <div class="card" style="width: 160px">
                                    <div class="view overlay">
                                        <img class="card-img-top" src="${product.productImage}" alt="Iphone" >
                                        <a href="/product/${product.productId}">
                                            <div class="mask rgba-white-slight"></div>
                                        </a>
                                    </div>
                                    <div class="card-body text-center">
                                        <a href="/product/${product.productId}" class="grey-text">
                                            <h6>${product.productBrand} ${product.productName}</h6>
                                        </a>

                                        <h5 class="font-weight-bold blue-text">
                                            <strong>${product.productPrice} $</strong>
                                        </h5>
                                        <#if customer??>
                                            <a href="cart/add/product/${product.productId}" class="card-link btn btn-primary" >
                                                <i class="fa fa-shopping-cart ml-1"></i></a>
                                        </#if>
                                    </div>
                                </div>
                            </div>
                        </#list>

                </#if>
            </div>

        </div>
    </div>
        <#include "footer.ftl">
</@home.home>
<script>
    $( function() {
        $( "#slider-range" ).slider({
            range: true,
            min: 0.0,
            max: 5000.0,
            values: [ 0.0, 5000.0 ],
            slide: function( event, ui ) {
                $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
                $( "#minValue" ).val(ui.values[0]);
                $( "#maxValue" ).val(ui.values[1]);
            }
        });
        $( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
            " - $" + $( "#slider-range" ).slider( "values", 1 ) );

        $("#minValue").val($("#slider-range").slider("values", 0));

        $("#maxValue").val($("#slider-range").slider("values", 1));
    } );

</script>