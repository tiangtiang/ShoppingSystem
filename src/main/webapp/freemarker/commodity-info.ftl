<#include "./layout.ftl">

<@layout>

    <style>
        .img{
            width: 300px;
            height: 300px;
        }
        hr {
            border-style: solid none;
            border-width: 1px 0;
            margin: 18px 0;
        }
    </style>
    <div class="container" style="margin-top: 50px">
        <div class="row">
            <div class="col-lg-4">
                <#if commodity.imgUrl??>
                    <img src="${commodity.imgUrl}" class="img"/>
                <#else >
                    <img src="./index/image/${commodity.id}" class="img"/>
                </#if>
            </div>
            <div class="col">
                <p class="font-weight-bold"><h4>${commodity.title}</h4></p>
                <p class="font-weight-normal">${commodity.summary}</p>
                <p class="font-weight-normal">￥ ${commodity.price}</p>
                <p>
                    <#if user?? && user.isBuyer == 0>
                        <div>
                            <p class="font-weight-normal">已售出${commodity.sellCount}件</p>
                        </div>
                    <#else >
                        <div>
                            <label class="form-inline font-weight-normal">购买数量：
                                <input type="number" class="form-control" min="0"
                                       style="width: 100px;" id="count" onchange="numChange(this)">
                            </label>
                        </div>
                    </#if>

                </p>

                <#if user?? && user.isBuyer == 1>
                    <#if bought??>
                        <#--如果用户已购买，显示上次购买的价格-->
                        <div class="form-inline">
                            <button class="btn btn-danger disabled" aria-disabled="true">已购买</button>
                            <span class="font-weight-light" style="margin-left: 10px">
                                上次购买的价格是：${bought.buyPrice}￥</span>
                        </div>
                    <#else>
                        <#--买家登录，可以购买商品-->
                        <div>
                            <button class="btn btn-primary" data-toggle="modal" data-target="#inCart">加入购物车</button>
                        </div>

                        <!-- Modal -->
                        <div class="modal fade" id="inCart" tabindex="-1" role="dialog" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-body">
                                        确认加入购物车？
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                                        <button type="button" class="btn btn-primary" onclick="confirmClick()">确定</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <link type="text/css" rel="stylesheet" href="./css/common.css">
                        <script src="./js/common.js"></script>

                        <script>
                            var confirmClick = function () {
                                // 没有填数量就隐藏
                                var num = $('#count');
                                if(num.val() == ''|| num.val()<=0){
                                    num.addClass('is-invalid');
                                    $('#inCart').modal('hide');
                                    return;
                                }
                                // 发送请求添加成功
                                $.ajax('commodity/add.do',{
                                    method:'POST',
                                    data:{
                                        commodityId:${commodity.id},
                                        count:$('#count').val()
                                    },
                                    success:function (data) {
                                        $('#inCart').modal('hide');
                                        if(data == 'success')
                                            var div = load('添加成功');
                                        else
                                            var div = load('添加失败');
                                        setTimeout(function () {
                                            div.remove();
                                        }, 2000);
                                    },
                                    error: function () {
                                        $('#inCart').modal('hide');
                                        var div = load('发送失败');
                                        setTimeout(function () {
                                            div.remove();
                                        }, 2000);
                                    }
                                })
                            }
                        </script>
                    </#if>
                <#elseif user??>
                    <div>
                        <button class="btn btn-outline-primary"
                                onclick="window.location.href='public?id=${commodity.id}'">编辑</button>
                    </div>
                </#if>
            </div>
        </div>

        <script>
            var numChange = function (cnt) {
                cnt = $(cnt);
                if(cnt.val() != '' && cnt.val() > 0){
                    cnt.removeClass('is-invalid');
                }
            }
        </script>

        <div style="margin-top: 50px">
            <p><h4>详细信息</h4></p>
            <hr/>
            <p>${commodity.content}</p>
        </div>
    </div>
</@layout>