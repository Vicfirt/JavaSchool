<#import "common_home.ftl" as home>
<#import "header.ftl" as header>
<@home.home>

    <@header.header counter="${counter}"></@header.header>
<div class="container ">

    <h1 align="center" class="display-4 mb-5">Sign Up</h1>
    <div style="width:40%; margin: 25px auto" >
        <form action="/register"  method="post">

            <div class="form-group">
                <label>Email address</label>

                <input value="" type="email" class="form-control form-control-lg" id="email" name="email" placeholder="Enter email" required="true" autofocus="true">

            </div>
            <div class="form-group">
                <label>Name</label>

                <input value="" type="text" class="form-control form-control-lg" id="name" name="name" placeholder="Your name" required="true">

            </div>
            <div class="form-group">
                <label>Password</label>

                <input type="" class="form-control form-control-lg" id="password" name="password" placeholder="Password" required="true">

            </div>
            <div class="form-group">
                <label>Phone</label>

                <input value="" type="text" class="form-control form-control-lg" id="phone" name="phone" placeholder="Phone" required="true">

            </div>
            <div class="form-group">
                <label>Address</label>

                <input value="" type="text" class="form-control form-control-lg" id="address" name="address" placeholder="Address" required="true">

            </div>

            <input hidden type="text" name="role" value="ROLE_CUSTOMER">

            <input hidden type="checkbox" name="active" value="1" checked>
            <div class="form-group">
                <input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign Up"/>
            </div>
        </form>
    </div>
    <#include "footer.ftl">
</@home.home>
