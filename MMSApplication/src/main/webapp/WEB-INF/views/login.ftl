<#import "common_home.ftl" as home>
    <#import "header.ftl" as header>
<@home.home>

    <@header.header counter="${counter}"></@header.header>
    <body>
<div class="container ">

    <h1 align="center" class="display-4 mb-5">Sign In</h1>
    <div style="width:40%; margin: 25px auto" >
    <#if (RequestParameters.error)??>
        <div class="alert alert-danger">
            Invalid username and password.
        </div>
    </#if>
    <#if (RequestParameters.logout)??>
        <div class="alert alert-info">
            You have been logged out.
        </div>
    </#if>

    <form action="/login" method="post">
        <div class="form-group">
            <label>Email address</label>
            <input type="text" class="form-control form-control-lg" id="email" name="email" placeholder="Enter email" required="true" autofocus="true">
        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="password" class="form-control form-control-lg" id="password" name="password" placeholder="Password" required="true">
        </div>

        <div class="form-group">
            <div>
                <input type="checkbox" id="remember_me" name="remember-me">
                <label for="remember_me" class="inline">Remember me</label>
                <a class="float-right" href="/register">Sign Up</a>
            </div>
        </div>

        <div class="form-group">
            <input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign In"/>
        </div>
    </form>

    <#include "footer.ftl">



</@home.home>