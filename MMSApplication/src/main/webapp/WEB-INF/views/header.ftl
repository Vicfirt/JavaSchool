<#macro header>

    <nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling-navbar">
        <div class="container">
            <a href="/" class="navbar-brand waves-effect">
                <strong class="blue-text">Online Shop</strong>
            </a>
            <button class="navbar-toggler" type="button"
                data-toggle="collapse" data-target="#navbarContent"
                aria-controls="navbarContent" aria-expanded="false" aria-label="Toogle nav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a href="/catalog" class="nav-link waves-effect">Catalog</a>
                    </li>
                    <li class="nav-item">
                        <a href="/login" class="nav-link waves-effect">Log in</a>
                    </li>

                </ul>
                <form class="form-inline" style="margin-right: 150px">

                    <div class="md-form my-0">
                        <input type="text" class="form-control mr-sm-2" placeholder="Search"
                        aria-label="Search">
                    </div>
                </form>
                <ul class="navbar-nav nav-flex-icons">
                    <li class="nav-item">
                        <a href="/cart" class="nav-link waves-effect">
                            <span class="badge red z-depth-1 mr-1">1</span>
                            <i class="fa fa-shopping-cart"></i>
                            <span class="clearfix d-none d-sm-inline-block">Cart</span>
                        </a>
                    </li>
                </ul>

            </div>
        </div>
    </nav>
</#macro>

