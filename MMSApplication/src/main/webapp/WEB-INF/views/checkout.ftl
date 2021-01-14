<#import "common_home.ftl" as home>
<#import "spring.ftl" as spring>
<@home.home>

    <#include "header.ftl">

    <div class="container" style="margin-top: 100px">

         <div class="row">

             <div class="col-sm-6">
                 <div class="card">
                     <div class="card-body text-center">
                 <h2>Your order</h2>
                 <table class="table table-striped text-center">
                     <thead>
                     <tr>
                         <th scope="col">Product</th>
                         <th scope="col">Amount</th>
                         <th scope="col">Price</th>
                     </tr>
                     </thead>
                     <tbody>
                 <#list cartElements as element>
                 <tr>
                     <th class="align-middle" scope="row">${element.getProduct().getProductBrand()}  ${element.getProduct().getProductName()} ${element.getProduct().getProductModel()}</th>
                     <th class="allign-middle" scope="row">${element.getProductCount()} </th>
                     <th class="allign-middle" scope="row">${element.getTotalPrice()}$ </th>
                 </tr>
                 </#list>
                     </tbody>
                 </table>
                     </div>
                 </div>
             </div>

             <div class="col-sm-6">
                 <div class="card">
                     <div class="card-body text-center">
                 <h2>Contact information</h2>
                 ${address.getCountry()}, ${address.getCity()}, ${address.getStreet()} st.  ${address.getBuilding()},
                 room ${address.getRoom()}
                 <div>
                     Postcode: ${address.getPostcode()}
                 </div>
                 <div>
                     ${customer.getCustomerFirstName()} ${customer.getCustomerLastName()}, ${customer.getPhoneNumber()}
                 </div>


                     </div>
                 </div>
             </div>

         </div>

        <div class="row">
           <div>

           </div>

            <div class="col-sm-6 offset-md-6" >
                <div class="card">

                    <div class="card-body text-center">
                        <h2>Payment method  <i class="fab fa-paypal"></i>
                            <i class="fab fa-cc-visa"></i>
                            <i class="fas fa-money-bill-wave-alt"></i>

                        </h2>
                    </div>
                </div>

                <div>

                </div>


                <form action="/orders/order/new" method="post">


                    <select class="custom-select custom-select-lg " id="paymentMethodId" name="paymentMethodId"
                            required="true">
                        <option value="0">Online</option>
                        <option value="1">Card</option>
                        <option value="2">Cash</option>

                    </select>
                    <@spring.bind "order"/>

                    <@spring.bind "order.customerId"/>
                    <input hidden type="number" name="customerId" value="${customer.getCustomerId()}">
                    <@spring.bind "order.orderCount"/>
                    <input hidden type="number" name="orderCount" value="${cart.getElementsInCart()}">
                    <@spring.bind "order.shippingId"/>
                    <input hidden type="number" name="shippingId" value="${address.getCustomerAddressId()}">
                    <@spring.bind "order.statusId"/>
                    <input hidden type="number" name="statusId" value="0">

                    <button class="btn btn-success btn-block">Checkout</button>
                </form>

            </div>

        </div>
    </div>




</@home.home>