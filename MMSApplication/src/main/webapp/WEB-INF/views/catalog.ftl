<#import "common_home.ftl" as home>

<@home.home>
<#list products as product>
    <div class="card-group">

        <div class="card">
            <div class="embed-responsive embed-responsive-16by9">
            <img class="card-img-cap embed-responsive-item" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQAXXF0NS1dRjjosCqw7v1IYRi5QoNOMj1TVA&usqp=CAU" alt="Card image cap" >
            </div>
            <div class="card-body">
                <h5 class="card-title">${product.productName}+" "</h5>
                <p class="card-text"></p>
                <p class="card-text"><small class="text-muted"></small></p>
            </div>
        </div>
        <div class="card">
            <img class="card-img-top" src="..." alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
                <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
            </div>
        </div>
        <div class="card">
            <img class="card-img-top" src="..." alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This card has even longer content than the first to show that equal height action.</p>
                <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
            </div>
        </div>
    </div>
</#list>
</@home.home>