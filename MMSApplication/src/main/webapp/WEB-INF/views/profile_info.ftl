<#import "common_home.ftl" as home>
<@home.home>
    <#include "header.ftl">
    <div class="row" style="margin-top: 120px">

    <div class="col-sm-6">
        <div class="card" style="height: 22rem">
            <div class="card-body">
                <h2 class="card-title">${customer.getCustomerFirstName()} ${customer.getCustomerLastName()}</h2>
                <p class="card-text"><strong>Date of birth: </strong>${customer.getCustomerDateOfBirth()}</p>
                <p class="card-text"><strong>Email address: </strong> ${customer.getCustomerEmailAddress()}</p>
                <p class="card-text"><strong>Phone number: </strong> ${customer.getPhoneNumber()}</p>
            </div>
            <div class="card-footer">
                <form action="/profile/edition" method="get">
                    <button type="submit" class="btn btn-primary btn-block">Edit profile <i
                                class="fas fa-edit"></i></button>
                </form>
            </div>
        </div>

    </div>

    <#if address.country??>
    <div class="col sm-6">
        <div class="card" style="height: 22rem">
            <div class="card-body">
            <h2 class="card-title">My address</h2>
            <p class="card-text"><strong>Country: </strong>${address.getCountry()}</p>
            <p class="card-text"><strong>City: </strong>${address.getCity()}</p>
            <p class="card-text"><strong>Street: </strong>${address.getStreet()}</p>
            <p class="card-text"><strong>Building: </strong>${customer.getCustomerAddress().getBuilding()}</p>
                <p class="card-text"><strong>Room: </strong>${customer.getCustomerAddress().getRoom()}</p>
                <p class="card-text"><strong>Postcode:</strong> ${customer.getCustomerAddress().getPostcode()}</p>
            </div>
            <div class="card-footer">
                <form action="profile/address">
                    <button class="btn btn-primary btn-block">Update Address
                        <i class="fas fa-redo"></i></button>
                </form>
            </div>
        </div>
                <#else>
                <div class="col sm-6">
                <div class="card">
                    <div class="card-body">
                        <p></p>
                        <p></p>
                        <p></p>
                        <p class="card-text"><strong>Add your address please</strong></p>
                        <p></p>
                        <p></p>
                        <p></p>
                    <form action="/profile/address/new" method="get">
                        <button class="btn btn-success btn-block"><span class="fa fa-plus-circle"></span>Add address
                        </button>
                    </form>
                    </div>
                </div>
                </div>

                </#if>
    </div>
    </div>
    <div class="footer fixed-bottom">
        <#include "footer.ftl">
    </div>

</@home.home>