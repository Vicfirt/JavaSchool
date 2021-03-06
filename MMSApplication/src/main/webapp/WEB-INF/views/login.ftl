<#import "common_home.ftl" as home>

<@home.home>

    <#include "header.ftl">

    <body>
<div class="container">

    <div style="width:40%; margin: 25px auto; margin-top: 120px" >
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

                <a class="float-right" href="/signup">Sign Up</a>
            </div>
        </div>

        <div class="form-group">
            <input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign In"/>
        </div>
    </form>

    <div class="footer fixed-bottom">
    <#include "footer.ftl">
    </div>



</@home.home>