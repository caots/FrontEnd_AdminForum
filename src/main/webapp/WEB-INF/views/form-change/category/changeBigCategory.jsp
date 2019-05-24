<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/js/ajax/category/ajax_category_create_select.js"></script>
<script src="resources/js/ajax/category/big_category/ajax_big_category_change.js"></script>
<div class="create-category">
    <div class="row ">
        <div class="col-md-2"></div>
        <div class="col-md-9">
            <h1 style="margin-top: 40px"> Big Category </h1>
            <div class="category">
                <div class="form-group row">
                    <div class="col-md-6">
                        <label><h5>Big Category</h5></label>
                        <input class="form-control" placeholder="big category" id="name-big-category">
                    </div>
                    <div class="col-md-6">
                        <label><h5>Menu</h5></label>
                        <select class="form-control" id="menu-value"></select>
                    </div>
                    <div class="col-md-2">
                        <button type="button" class="btn btn-lg btn-primary" id="btn-create-big-category">Submit
                        </button>
                    </div>

                </div>
            </div>

            <div style="text-align: center">
                <a href="big-category">
                    <button type="button" class="btn btn-lg btn-warning">Thoát</button>
                </a>
            </div>

        </div>
        <div class="col-md-2"></div>
    </div>
</div>