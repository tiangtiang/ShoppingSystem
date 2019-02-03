<#include "./layout.ftl">
<#--展示购物车列表-->
<@layout>
<style>
    .btns{
        margin-top: 100px;
    }
    .list{
        margin-top: 20px;
    }
</style>
<div class="container">
    <h3 style="margin-top: 20px">购物车</h3>
    <table class="table table-striped list" id="data-table">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">商品</th>
                <th scope="col">添加日期</th>
                <th scope="col">数量</th>
                <th scope="col">价格</th>
            </tr>
        </thead>
        <tbody>
            <#assign i=1>
            <#list cart as item>
                <tr>
                    <th scope="row">${i}</th>
                    <td>${item.commodity.title}</td>
                    <td>${item.addTime?string["yyyy/MM/dd HH:mm"]}</td>
                    <td class="count">${item.count}</td>
                    <td><span class="price" style="margin-right: 10px">${item.commodity.price}</span> ￥</td>
                    <td hidden="hidden" class="cid">${item.commodityId}</td>
                </tr>
                <#assign i=i+1>
            </#list>
        </tbody>
    </table>

    <div class="row text-center btns">
        <div class="col">
            <button class="btn btn-primary" onclick="history.go(-1)">
                退出
            </button>
        </div>
        <div class="col">
            <button class="btn btn-primary" onclick="buyClick()">
                购买
            </button>
        </div>
    </div>
</div>

<script>
    var buyClick = function () {
        var cidList = getValue('cid');
        var countList = getValue('count');
        var priceList = getValue('price');
        var data = {
            cids: cidList,
            counts: countList,
            prices: priceList
        };
        $.ajax('cart/buy',
                {
                    // contentType:'application/json',
                    // data:JSON.stringify(data),
                    data: data,
                    type:'POST',
                    success:function (data) {
                        if(data == 'success'){
                            window.location.reload();
                        }
                    },
                    error:function () {
                        alert('failed');
                    }
                });
    }

    function getValue(cls) {
        var value = [];
        $('.'+cls).each(function () {
            value.push($(this).text());
        });
        return value;
    }
</script>
</@layout>