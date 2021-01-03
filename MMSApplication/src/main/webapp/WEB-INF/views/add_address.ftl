<#import "common_home.ftl" as home>
    <#import "spring.ftl" as spring>
<@home.home>

    <#include "header.ftl">

    <div class="container ">

        <h1 align="center" class="display-4 mb-5">Add address</h1>
        <div style="width:40%; margin: 25px auto" >
            <form action="/profile/address/new"  method="post">
                <@spring.bind "address"/>
                <div class="form-group">
                    <label>Country *</label>
                    <@spring.bind "address.country"/>
                    <input value="${address.country!}" type="text" class="form-control form-control-lg" id="country" name="country"  required="true" autofocus="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>

                <div class="form-group">
                    <label>City *</label>
                    <@spring.bind "address.city"/>
                    <input value="${address.city!}" type="text" class="form-control form-control-lg" id="city" name="city"  required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>

                <div class="form-group">
                    <label>Street *</label>
                    <@spring.bind "address.street"/>
                    <input type="" class="form-control form-control-lg" id="street" name="street"  required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>

                <div class="form-group">
                    <label>Building *</label>
                    <@spring.bind "address.building"/>
                    <input value="" type="text" class="form-control form-control-lg" id="building" name="building" required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>

                <div class="form-group">
                    <label>Room *</label>
                    <@spring.bind "address.room"/>
                    <input value="" type="text" class="form-control form-control-lg" id="room" name="room" required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>

                <div class="form-group">
                    <label>Postcode *</label>
                    <@spring.bind "address.postcode"/>
                    <input value="" type="text" class="form-control form-control-lg" id="postcode" name="postcode" required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>


                <div class="form-group">
                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Add address"/>
                </div>
            </form>
        </div>
    </div>

    <#include "footer.ftl">

</@home.home>