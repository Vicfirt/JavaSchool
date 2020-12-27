<#import "common_home.ftl" as home>
<#import "header.ftl" as header>

<@home.home>
    <div class="row">
        <@header.header counter="${counter}"></@header.header>
    </div>
    <div class="row" style="margin-top: 100px">
        ADVERTISEMENT
    </div>
    <#include "footer.ftl">
</@home.home>


