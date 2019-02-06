<#include "./layout.ftl">

<@layout>

        <#if user?? && user.isBuyer==1>
            <ul class="nav nav-tabs" style="margin-top: 10px">
                <li class="nav-item">
                    <a class="nav-link active" id="allList" data-toggle="tab" role="tab"
                        aria-selected="true" style="cursor: pointer">所有商品</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="notBuy" data-toggle="tab" role="tab"
                        aria-selected="false" style="cursor: pointer">未购买商品</a>
                </li>
            </ul>
        </#if>

        <#--定义商品列表的样式-->
        <style>
            .inner{
                margin-top: 20px;
            }
            .ccell{
                background: #f0e9e9;
                margin-right: 10px;
                margin-left: 10px;
                margin-bottom: 10px;
                padding-top: 5px;
                padding-bottom: 5px;
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                width: 300px;
                height: 320px;
            }
            .had{
                position: absolute;
                top: 0;
                left: 0;
                height: 0;
                width: 0;
                border-style: solid;
                border-width: 22px;
                border-color: #aaa transparent transparent #aaa;
                border-top-left-radius: 10px;
            }
            .had b{
                position: absolute;
                top: -12px;
                left: -25px;
                width: 40px;
                text-align: center;
                white-space: nowrap;
                line-height: 14px;
                font-size: 12px;
                transform: rotate(-45deg);
                color: #eee;
            }
        </style>
        <#--定义图片展示列数-->
        <#assign cols = 3>

        <div class="container inner" id="all">
            <#list goods as commodity>
                <#if commodity_index%cols==0>
                    <div class="row inner">
                </#if>
                    <div class="text-center align-self-start ccell col-lg-3">
                        <a href="./commodity?id=${commodity.id}" style="text-decoration: none; color: black; cursor: pointer;">
                        <p>标题：${commodity.title}</p>
                        <p>价格：${commodity.price} 元</p>
                        <#if commodity.imgUrl??>
                            <img src="${commodity.imgUrl}" style="width: 200px;height: 200px;">
                        <#else>
                            <img src="index/image/${commodity.id}" style="width: 200px;height: 200px;">
                        </#if>
                        <#if user??>
                            <#if boughtList?? && boughtList?seq_contains(commodity.id)>
                                    <#--是否已购买-->
                                    <span class="had">
                                        <b>已购买</b>
                                    </span>
                            <#elseif user.isBuyer==0 && commodity.sellCount gt 0>
                                <span class="had">
                                        <b>已售出</b>
                                    </span>
                            </#if>
                        </#if>
                        </a>
                        <#if user?? && user.isBuyer==0 && commodity.sellCount == 0>
                        <#--未售出的商品要显示删除按钮-->
                                    <button class="btn btn-link" data-toggle="modal"
                                            data-target="#delCommodity" data-id="${commodity.id}">删除</button>
                        </#if>
                    </div>
                <#if commodity_index%cols==cols-1>
                    </div>
                </#if>
            </#list>
        </div>

<div class="modal fade" id="delCommodity" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                确定要删除该商品吗？
            </div>
            <input hidden id="cid">
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                <button type="button" id="confirm" class="btn btn-primary" onclick="delCommodity()">确定</button>
            </div>
        </div>
    </div>
</div>
<link type="text/css" rel="stylesheet" href="./css/common.css">
<script src="./js/common.js"></script>
        <script>
            $('#allList').click(function () {
                window.location.href='index';
            });
            $('#notBuy').click(function () {
                window.location.href='index?type=1';
            });
            window.onload=function (ev) {
                var href = window.location.href;
                if(href.indexOf('type')>-1){
                    $('#allList').removeClass('active');
                    $('#notBuy').addClass('active');
                }
            };
            function delCommodity() {
                $.ajax({
                    url: 'commodity/del',
                    method: 'POST',
                    data:{
                        id: $('#cid').val()
                    },
                    success: function (data) {
                        $('#delCommodity').modal('hide');
                        if(data == 'success'){
                            var div = load('删除成功');
                            setTimeout(function () {
                                div.remove();
                                window.location.reload();
                            }, 1000);
                        }else
                            alert(data);
                    },
                    error:function () {
                        $('#delCommodity').modal('hide');
                        alert("删除失败！");
                    }
                })
            }

            $('#delCommodity').on('show.bs.modal', function (event) {
                var btn = $(event.relatedTarget);
                var id = btn.data('id');
                console.log(id);
                $('#cid').val(id);
            })
        </script>
</@layout>