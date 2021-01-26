<#import "common_home.ftl" as home>
<@home.home>



    <h1 align="center" class="display-4 mb-5"></h1>
    <div class="row clearfix" style="margin-top: 120px">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-danger">

                <h4> Error!</h4>
                <strong>${message!""}</strong>
                <a href="${url!"/"}" class="alert-link float-right"><u>Back</u></a>
            </div>
        </div>
    </div>
    <div style="margin-top: 400px">
    <#include "footer.ftl">
    </div>
</@home.home>
