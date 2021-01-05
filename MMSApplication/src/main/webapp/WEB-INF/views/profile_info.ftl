<#import "common_home.ftl" as home>
<@home.home>
    <div class="row">

                    <div class="col-sm-6">
                        <div class="card">
                        <h2>${customer.getCustomerFirstName()} ${customer.getCustomerLastName()}</h2>
                        <p><strong>Date of birth: </strong>  </p>
                        <p><strong>Email address</strong> ${customer.getCustomerEmailAddress()}</p>
                        <p><strong>Phone number</strong> ${customer.getPhoneNumber()}</p>

                        <form action="/profile/edit" method="get">
                            <button type="submit" class="btn btn-primary btn-block">Edit profile</button>
                        </form>
                        </div>

                    </div>



    <#if address?has_content>
    <div class="col sm-6">
        <div class="card">
            <h2>My address</h2>
            <p><strong>Country: </strong>${address.getCountry()}</p>
            <p><strong>City:</strong>${address.getCity()}</p>
            <p><strong>Street:</strong>${address.getStreet()}</p>
            <p><strong>Building:</strong>${customer.getCustomerAddress().getBuilding()}</p>
            <p><strong>Room:</strong>${customer.getCustomerAddress().getRoom()}</p>
            <p><strong>Postcode:</strong> ${customer.getCustomerAddress().getPostcode()}</p>
        <button class="btn btn-primary btn-block"><span class="fa fa-refresh-circle" style="size:200px"></span>Update Address</button>
        </div>
        </div>
        </div>


    <#else >
        <div class="row">

            <form action="/profile/address/new" method="get">
            <button class="btn btn-success btn-block"><span class="fa fa-plus-circle"></span>Add address</button>
            </form>
        </div>

    </#if>



</@home.home>