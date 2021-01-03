<#import "common_home.ftl" as home>
<#import "header.ftl" as header>

<@home.home>

       <#include "header.ftl">

<div style="margin-top: 100px">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" >
            <div class="carousel-item active" >
                <img class="d-block w-100" src="https://images.pexels.com/photos/3608311/pexels-photo-3608311.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940" alt="First slide" style="height: 300px; width:1000px ">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Welcome</h5>
                    <p></p>
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="https://images.pexels.com/photos/3608311/pexels-photo-3608311.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940" alt="Second slide" style="height: 300px; width:1000px ">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Buy something</h5>
                    <p>please</p>
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="https://images.pexels.com/photos/3608311/pexels-photo-3608311.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940" alt="Third slide" style="height: 300px; width:1000px ">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Best offer</h5>
                    <p>really</p>
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

    <#include "footer.ftl">
</@home.home>


