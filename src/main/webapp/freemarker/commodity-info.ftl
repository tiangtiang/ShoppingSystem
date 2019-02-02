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
                <img src="./index/image/${commodity.id}" class="img"/>
            </div>
            <div class="col">
                <p class="font-weight-bold"><h4>${commodity.title}</h4></p>
                <p class="font-weight-normal">${commodity.summary}</p>
                <p class="font-weight-normal">￥ ${commodity.price}</p>
                <p>
                    <div>
                        <label class="form-inline font-weight-normal">购买数量：
                        <input type="number" class="form-control" min="0" style="width: 70px;">
                        </label>
                    </div>
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
                        <div>
                            <button class="btn btn-primary">加入购物车</button>
                        </div>
                    </#if>
                <#elseif user??>
                    <div>
                        <button class="btn btn-outline-primary">编辑</button>
                    </div>
                </#if>
            </div>
        </div>

        <div style="margin-top: 50px">
            <p><h4>详细信息</h4></p>
            <hr/>
            <p>${commodity.content}</p>
        </div>
    </div>
</@layout>