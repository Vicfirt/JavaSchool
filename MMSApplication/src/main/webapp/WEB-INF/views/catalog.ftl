<#import "common_home.ftl" as home>
<#import "spring.ftl" as spring>
<@home.home>

    <div class="row">

    <#include "header.ftl">

    </div>

    <div class="row">

    </div>
    <div class="row" style="margin-top: 80px;">
        <div class="col-lg-3">
            <div class="row">
            <h3 style="margin-left: 20px" >Filter By</h3>
            </div>
            <div class="row">

                <div class="btn-group dropright">
                    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        Category
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item "href="/catalog/category/1">Electronics</a>
                        <a class="dropdown-item "href="/catalog/category/0">Books</a>
                        <a class="dropdown-item "href="/catalog/category/2">Clothes</a>
                    </div>
                </div>

                <div class="btn-group dropright">
                    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        Brand
                    </button>
                    <div class="dropdown-menu">
                        <#list brands as brand>
                        <a class="dropdown-item "href="/catalog/brand/${brand}">${brand}</a>
                        </#list>
                    </div>
                </div>
            </div>
            <div data-role="page" style="margin-top: 30px">
                <div data-role="header">
                    <h3>Range by price</h3>
                </div>


                    <input type="text" id="amount" name = "amount" readonly style="border:0; color:#f6931f; font-weight:bold;">

                    <div id="slider-range"></div>

                <form action="/catalog" method="get">

                    <input type="hidden" name="minValue" id="minValue" />
                    <input type="hidden" name="maxValue" id="maxValue" />

                    <input align="center" type="submit" class="btn btn-primary" value="Range"style="margin-top: 10px">
                </form>

            </div>
        </div>

        <div class="col-lg-9">
            <div class="row wow fadeIn">
                <#if (products)??>
                    <#list products as product>
                        <div class="col col-lg-3 col-md-6">

                        <div class="card mb-4  ${product.getActive()?then("","text-white bg-warning")}" style="width: 180px; height: 250px">

                            <div class="view overlay"
                                  <#if (customer?? && customer.role == "CUSTOMER") || (customer?? && customer.role == "EMPLOYEE") >

                                 style="width: 125px; height: 125px; margin-left: 26px"

                                <#else>

                                 style="width: 180px; height: 180px"

                            </#if>
                            >
                                <img class="card-img-top"
                                     <#if product.productImage != "">

                                     src="${product.getProductImage()}"

                                     <#else>

                                     src="/media/Product_${product.getProductId()}.jpg"
                                     </#if>

                                      alt="Image" >

                                <a href="/product/${product.productId}">
                                    <div class="mask rgba-white-slight"></div>
                                </a>
                            </div>
                            <div class="card-body text-center">
                                <a href="/product/${product.productId}" class="grey-text">
                                    <h6>${product.productBrand} ${product.productName}</h6>
                                </a>
                                <h6 class="font-weight-bold blue-text">
                                    <strong>${product.productPrice} $</strong>
                                </h6>
                            </div>


                                <#if customer?? && customer.role == "CUSTOMER">
                                <a type="button" href="cart/product/${product.productId}" class="card-link btn btn-primary btn-sm" >
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
                    </#list>
                    <#elseif filteredProducts?has_content>
                        <#list filteredProducts as product>
                            <div class="col col-lg-3 col-md-6">

                                <div class="card mb-4" style="width: 180px; height: 250px">
                                    <div class="view overlay text-center"
                                            <#if (customer?? && customer.role == "CUSTOMER") || (customer?? && customer.role == "EMPLOYEE") >

                                                style="width: 125px; height: 125px; margin-left: 26px"

                                            <#else>

                                                style="width: 180px; height: 180px"

                                            </#if>
                                    >
                                        <img class="card-img-top"
                                                <#if product.productImage != "">

                                                    src="${product.getProductImage()}"

                                                <#else>

                                                    src="/media/Product_${product.getProductId()}.jpg"
                                                </#if>

                                             alt="Image" >

                                        <a href="/product/${product.productId}">
                                            <div class="mask rgba-white-slight"></div>
                                        </a>
                                    </div>
                                    <div class="card-body text-center">
                                        <a href="/product/${product.productId}" class="grey-text">
                                            <h6>${product.productBrand} ${product.productName}</h6>
                                        </a>


                                        <h6 class="font-weight-bold blue-text">
                                            <strong>${product.productPrice} $</strong>
                                        </h6>

                                    </div>
                                        <#if customer?? && customer.role == "CUSTOMER">
                                            <a type="button" href="cart/product/${product.productId}" class="card-link btn btn-sm btn-primary" >
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
                        </#list>
                    <#else>

                    <div style="margin-top: 100px; margin-left: 50px">
                        <h4 class="text-muted text-center">No results were found for your search.</h4>
                    </div>

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
            max: 2000.0,
            values: [ 0.0, 2000.0 ],
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