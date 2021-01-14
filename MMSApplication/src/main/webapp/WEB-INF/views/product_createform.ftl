<#import "common_home.ftl" as home>

    <#import "spring.ftl" as spring>
<@home.home>

    <#include "header.ftl">

    <div class="container ">

        <h1 align="center" class="display-4 mb-5">Create Product</h1>

        <div style="width:40%; margin: 25px auto">
            <form action="/product/employee/new" method="post">
                <@spring.bind "product"/>

                <#--Photo-->
                <div class="form-group">
                    <label>Photo Link</label>
                    <@spring.bind "product.productImage"/>
                    <input value="${product.productImage!}"  type="text" class="form-control form-control-lg" id="productImage"
                           name="productImage">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>

                <#--Name-->
                <div class="form-group">
                    <label>Name *</label>
                    <@spring.bind "product.productName"/>
                    <input value="${product.productName!}" type="text" class="form-control form-control-lg" id="productName"
                           name="productName" required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>

                <#--Category-->
                <div class="form-group">
                    <label>Category *</label>
                    <select class="custom-select custom-select-lg " id="categoryId" name="categoryId"
                            required="true">
                        <option value="0">Books</option>
                        <option value="1">Electronics</option>
                        <option value="2">Clothes</option>

                    </select>
                </div>

                <#--Model-->
                <div class="form-group">
                    <label>Model *</label>
                    <@spring.bind "product.productModel"/>
                    <input value="${product.productModel!}"  type="text" class="form-control form-control-lg" id="productModel"
                           name="productModel" required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>

                <#--Brand-->
                <div class="form-group">
                    <label>Brand *</label>
                    <@spring.bind "product.productBrand"/>
                    <input value="${product.productBrand!}" type="text" class="form-control form-control-lg" id="productBrand"
                           name="productBrand" required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>

                <#--Weight-->
                <div class="form-group">
                    <label>Weight *</label>
                    <@spring.bind "product.productWeight"/>
                    <input value="${product.productWeight!}" type="number" class="form-control form-control-lg" id="productWeight"
                           name="productWeight" required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>

                <#--Capacity-->
                <div class="form-group">
                    <label>Capacity</label>
                    <@spring.bind "product.productCapacity"/>
                    <input value="${product.productCapacity!}"  type="number" class="form-control form-control-lg" id="productCapacity"
                           name="productCapacity">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>

                <#--Description-->
                <div class="form-group">
                    <label>Description</label>
                    <@spring.bind "product.productDescription"/>
                    <textarea class="form-control form-control-lg text-left"
                              id="productDescription" name="productDescription"
                    >${product.productDescription!}</textarea>
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>
                <#--Price-->
                <div class="form-group">
                    <label>Price</label>
                    <@spring.bind "product.productPrice"/>
                    <input class="form-control form-control-lg"
                           type="number"
                           id="productPrice"
                           name="productPrice"
                           step="0.01"
                           value="${product.productPrice!'5.00'}"
                           required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>
                <#--Stock-->
                <div class="form-group">
                    <label>Stock</label>
                    <@spring.bind "product.amountInStock"/>
                    <input class="form-control form-control-lg"
                           type="number"
                           id="amountInStock"
                           name="amountInStock"
                           min="0"
                           value="${product.amountInStock!'50'}"
                           required="true">
                    <span class="text-danger"><@spring.showErrors ""/></span>
                </div>
                <#--Status-->
                <div class="form-group">
                    <label>Status</label>
                    <select class="custom-select custom-select-lg " id="status" name="status" required="true">
                        <option value="true">Available</option>
                        <option value="false">Unavailable</option>
                    </select>
                </div>

                <div class="form-group">
                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Save product"/>
                </div>
            </form>


        </div>
    </div>

    <#include "footer.ftl">

</@home.home>