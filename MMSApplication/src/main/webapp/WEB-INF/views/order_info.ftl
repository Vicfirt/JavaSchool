<#import "common_home.ftl" as home>
<#import "header.ftl" as header>
<@home.home>

       <#include "header.ftl">

    <div class="container" style="margin-top: 100px">

        <h1 align="center" class="display-4 mb-5">Orders</h1>

        <table class="table table-striped text-center">
            <thead>
            <tr>
                <th scope="col">Order #</th>
                <th scope="col">Customer First Name</th>
                <th scope="col">Customer Last Name</th>
                <th scope="col">Customer Email</th>
                <th scope="col">Customer phone</th>
                <th scope="col">Total</th>
                <th scope="col">Status</th>
                <#if customer.role == "EMPLOYEE">
                <th scope="col">Action</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <tr>
                    <#if customer.role == "CUSTOMER">


                    <#list orders as order>

                    <td class="align-middle">${order.getOrderId()}</td>
                    <td class="align-middle">${customer.getCustomerFirstName()}</td>
                    <td class="align-middle">${customer.getCustomerLastName()}</td>
                    <td class="align-middle">${customer.getCustomerEmailAddress()}</td>
                    <td class="align-middle">${customer.getPhoneNumber()}</td>
                    <td class="align-middle">${order.getTotal()}</td>
                     <td class="align-middle">
                            <#if order.statusId == 0>
                            In process
                                <#elseif order.statusId == 1>
                                During delivery
                                    <#else>
                                Delivered
                            </#if>
                     </td>
            </tr>

                         </#list>

                <#else>

                <#list orders as order>
                <td class="align-middle">${order.getOrderId()}</td>

                    <td class="align-middle">${customers[order?index].getCustomerFirstName()}</td>
                    <td class="align-middle">${customers[order?index].getCustomerLastName()}</td>
                    <td class="align-middle">${customers[order?index].getCustomerEmailAddress()}</td>
                    <td class="align-middle">${customers[order?index].getPhoneNumber()}</td>

                <td class="align-middle">${order.getTotal()}</td>
            <form action="/orders/status" method="get">
                <td class="align-middle">


                    <select class="custom-select custom-select-lg" id="statusId" name="statusId" >
                        <option value="" disabled selected>
                            <#if order.statusId == 0>
                                In process
                                <#elseif order.statusId ==1>
                                During delivery
                                    <#elseif order.statusId ==2>
                                Delivered
                                <#else >
                                Rejected
                            </#if>
                        </option>
                        <option value="0">In process</option>
                        <option value="1">During delivery</option>
                        <option value="2">Delivered</option>
                        <option value="3">Rejected</option>
                    </select>

                    <input hidden type="number" name="orderId" id="orderId" value="${order.getOrderId()}">

                    </td>

                <td class="align-middle">

                        <button type="submit" class="btn btn-primary">
                            <i class="fa fa-edit"
                               aria-hidden="true"></i>
                        </button>

                </td>
            </form>
                    </tr>
                </#list>
            </#if>

            </tbody>

            <#if orders?has_content>
            <#else>
                <div>
                    <h4 class="text-muted text-center">Order list is empty </h4>
                </div>

            </#if>

    </div>


</@home.home>