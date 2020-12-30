<#import "common_home.ftl" as home>
<#import "header.ftl" as header>
<@home.home>

        <@header.header counter="${counter}"></@header.header>

    <div class="container" style="margin-top: 100px">

        <h1 align="center" class="display-4 mb-5">Orders</h1>

        <table class="table table-striped text-center">
            <thead>
            <tr>
                <th scope="col">Order #</th>
                <th scope="col">Customer Name</th>
                <th scope="col">Customer Email</th>
                <th scope="col">Customer phone</th>
                <th scope="col">Shipping Address</th>
                <th scope="col">Total</th>
                <th scope="col">Order Data</th>
                <th scope="col">Status</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <#list orders as order>
                <tr>
                    <th class="align-middle" scope="row">

                    </th>
                    <td class="align-middle">Name</td>
                    <td class="align-middle">Email</td>
                    <td class="align-middle">Phone</td>
                    <td class="align-middle">Address}</td>
                    <td class="align-middle">OrderTotal}</td>
                    <td class="align-middle">Status</td>
                    <td class="align-middle">


                    </td>

                </tr>
            </#list>
            </tbody>
        </table>


</@home.home>