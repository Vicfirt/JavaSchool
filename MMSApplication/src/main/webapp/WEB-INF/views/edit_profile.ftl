<#import "common_home.ftl" as home>
<#import "/spring.ftl" as spring />
<@home.home>

    <#include "header.ftl">

    <div class="container ">


        <h1 align="center" class="display-4 mb-5">Edit Profile</h1>
        <div style="width:40%; margin: 25px auto" >
            <form action="/profile/edition"  method="post">
                <@spring.bind "customer"/>


                    <input hidden value="${customer.customerEmailAddress!}" type="email" class="form-control form-control-lg" id="customerEmailAddress" name="customerEmailAddress"  required="true" autofocus="true">



                <div class="form-group">
                    <label>First Name *</label>
                    <@spring.bind "customer.customerFirstName"/>
                    <input value="${customer.customerFirstName!}" type="text" class="form-control form-control-lg" id="customerFirstName" name="customerFirstName"  required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>

                <div class="form-group">
                    <label>Last Name *</label>
                    <@spring.bind "customer.customerLastName"/>
                    <input value="${customer.customerLastName!}" type="text" class="form-control form-control-lg" id="customerLastName" name="customerLastName"  required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>


                    <input hidden value="${customer.customerDateOfBirth!}" type="date" min="1900-01-01" max="2021-01-01"class="form-control form-control-lg" id="customerDateOfBirth" name="customerDateOfBirth"  required="true">

                <div class="form-group">
                    <label>Password *</label>
                    <@spring.bind "customer.customerPassword"/>
                    <input class="form-control form-control-lg" id="customerPassword" name="customerPassword"  required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>

                <div class="form-group">
                    <label>Phone *</label>
                    <@spring.bind "customer.phoneNumber"/>
                    <input value="${customer.phoneNumber!}" type="text" class="form-control form-control-lg" id="phoneNumber" name="phoneNumber" placeholder="phone" required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>


                <input hidden type="text" name="role" value="CUSTOMER">

                <input hidden type="text" name="customerId" value="${customer.getCustomerId()}">

                <input hidden type="checkbox" name="active" value="true" checked>
                <div class="form-group">
                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Save Changes"/>
                </div>
            </form>
        </div>
    </div>

    <#include "footer.ftl">
</@home.home>