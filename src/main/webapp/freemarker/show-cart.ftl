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
            <#assign total=0>
            <#list cart as item>
                <tr>
                    <th scope="row">${i}</th>
                    <td>${item.commodity.title}</td>
                    <td>${item.addTime?string["yyyy/MM/dd HH:mm"]}</td>
                    <td class="count">${item.count}</td>
                    <td>￥<span class="price" style="margin-right: 10px">${item.commodity.price}</span></td>
                    <td hidden="hidden" class="cid">${item.commodityId}</td>
                </tr>
                <#assign i=i+1>
                <#assign total=total+item.count*item.commodity.price>
            </#list>
            <tr>
                <th scope="row">总价</th>
                <td></td>
                <td></td>
                <td></td>
                <td>￥<span style="margin-right: 10px">${total}</span></td>
            </tr>
        </tbody>

    </table>

    <div class="row text-center btns">
        <div class="col">
            <button class="btn btn-primary" onclick="history.go(-1)">
                退出
            </button>
        </div>
        <div class="col">
            <button id="btnBuy" class="btn btn-primary" data-toggle="modal"
                    data-target="#buyCommodity">
                购买
            </button>
        </div>
    </div>
</div>
    <#--购买商品对话框-->
                        <!-- Modal -->
                        <div class="modal fade" id="buyCommodity" tabindex="-1" role="dialog" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-body">
                                        确定购买购物车中的所有商品吗？
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                                        <button type="button" class="btn btn-primary" onclick="buyClick()">确定</button>
                                    </div>
                                </div>
                            </div>
                        </div>
<#--购物车中没有商品对话框-->
                        <div class="modal fade" id="noCommodity" tabindex="-1" role="dialog" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-body">
                                        没有商品加入购物车！
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                                    </div>
                                </div>
                            </div>
                        </div>

<link type="text/css" rel="stylesheet" href="./css/common.css">
<script src="./js/common.js"></script>
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
                        $('#buyCommodity').modal('hide');
                        if(data == 'success'){
                            var div = load('购买成功');
                            setTimeout(function () {
                                div.remove();
                                window.location.reload();
                            }, 2000);
                        }
                    },
                    error:function () {
                        alert('failed');
                    }
                });
    }

    {
        if(getValue('cid').length == 0){
            $('#btnBuy').attr('data-target', '#noCommodity');
        }else{
            $('#btnBuy').attr('data-target', '#buyCommodity');
        }
    }

    function getValue(cls) {
        var value = [];
        $('.'+cls).each(function () {
            value.push($(this).text().replace(',', ''));
        });
        return value;
    }
</script>
</@layout>