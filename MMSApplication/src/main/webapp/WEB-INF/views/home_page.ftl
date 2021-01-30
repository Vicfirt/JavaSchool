<#import "common_home.ftl" as home>
<#import "header.ftl" as header>

<@home.home>

       <#include "header.ftl">

<div style="margin-top: 100px" >
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="https://cdn.shopify.com/s/files/1/2728/6638/files/dickies-banner_1024x1024.png?v=1565769455">
                <a href="/catalog"></a>
                <div class="carousel-caption d-none d-md-block">

                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="https://7ns3u38klvh1d264d2i1jx3d-wpengine.netdna-ssl.com/wp-content/uploads/2019/07/Untitled-design-73.jpg" alt="Second slide">
                <div class="carousel-caption d-none d-md-block">

                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="https://s3.amazonaws.com/paradigmpress-uploads/wp-content/uploads/2020/08/shutterstock_image-87.jpg" alt="Third slide">
                <div class="carousel-caption d-none d-md-block">

                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

</div>
<div class="row wow fadeIn" style="margin-top: 50px">
    <#list products as product>

    <div class="col col-sm-3">

        <div class="card mb-4" style="height: 250px">
            <div class="view overlay">
                <img class="card-img-top" style="height: 150px; width: 150px; margin-left: 50px"
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

                    <span class="badge blue mr-1">New</span>

            </div>

        </div>
    </div>
    </#list>
</div>

    <#include "footer.ftl">
</@home.home>


