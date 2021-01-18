

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

                <#if !customer?? || !customer.role??>

                <ul class="navbar-nav mr-auto">

                    <li class="nav-item">
                        <a href="/catalog" class="nav-link waves-effect">Catalog</a>
                    </li>

                    <li class="nav-item">
                        <a href="/login" class="nav-link waves-effect">Log in</a>
                    </li>
                    <li class="nav-item">
                        <a href="/signup" class="nav-link waves-effect">Sign up</a>
                    </li>

                </ul>
                <form method="get" action="/catalog/name/" class="form-inline" style="margin-right: 150px">

                    <div class="md-form my-0">
                        <input type="text" name="productName" class="form-control mr-sm-2" placeholder="Search"
                        aria-label="Search">
                    </div>
                </form>

                <#elseif customer.getRole() == "EMPLOYEE">

                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a href="/catalog" class="nav-link waves-effect">Catalog</a>
                        </li>
                        <li class="nav-item">
                            <a href="/orders" class="nav-link waves-effect">Orders</a>
                        </li>
                        <li class="nav-item">
                            <a href="/product/employee/new" class="nav-link waves-effect">Add product</a>
                        </li>
                    </ul>
                    <form method="get" action="/catalog/name/" class="form-inline" style="margin-right: 150px">

                        <div class="md-form my-0">
                            <input type="text" name="productName" class="form-control mr-sm-2" placeholder="Search"
                                   aria-label="Search">
                        </div>
                    </form>
                    <ul class="navbar-nav nav-flex-icons">

                        <li class="nav-item">
                            <a href="" class="nav-link waves-effect"><i class="fa fa-user" aria-hidden="true"></i></a>
                        </li>
                        <li class="nav-item">
                            <a href="/logout" class="nav-link waves-effect">Log out</a>
                        </li>
                    </ul>

                    <#else>
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a href="/catalog" class="nav-link waves-effect">Catalog</a>
                            </li>
                            <li class="nav-item">
                                <a href="/orders/all" class="nav-link waves-effect">My Orders</a>
                            </li>
                        </ul>
                    <form method="get" action="/catalog/name/" class="form-inline" style="margin-right: 150px">

                        <div class="md-form my-0">
                            <input type="text" name="productName" class="form-control mr-sm-2" placeholder="Search"
                                   aria-label="Search">
                        </div>
                    </form>
                        <ul class="navbar-nav nav-flex-icons">

                            <li class="nav-item">
                                <a href="/cart" class="nav-link waves-effect">
                                    <#if customer.cart.elementsInCart != 0>
                                    <span class="badge red z-depth-1 mr-1">${customer.getCart().getElementsInCart()}</span>
                                    </#if>
                                    <i class="fa fa-shopping-cart"></i>
                                    <span class="clearfix d-none d-sm-inline-block">Cart</span>
                                </a>
                            </li>


                            <li class="nav-item">
                                <a href="/profile" class="nav-link waves-effect"><i class="fa fa-user" aria-hidden="true"></i></a>
                            </li>
                            <li class="nav-item">
                                <a href="/logout" class="nav-link waves-effect">Log out</a>
                            </li>
                        </ul>
                </#if>

            </div>
        </div>
    </nav>


