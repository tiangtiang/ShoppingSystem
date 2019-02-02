<#include "./layout.ftl">

<@layout>
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

        <div class="container inner">
            <#list goods as commodity>
                <#if commodity_index%cols==0>
                    <div class="row inner">
                </#if>
                    <div class="text-center align-self-start ccell col-lg-3" onclick="divfun(${commodity.id})"
                        style="cursor: pointer">
                        <p>标题：${commodity.title}</p>
                        <p>价格：${commodity.price} 元</p>
                        <img src="index/image/${commodity.id}" style="width: 200px;height: 200px;">
                        <#if user??>
                            <#if boughtList??>
                                <#if boughtList?seq_contains(commodity.id)>
                                    <#--是否已购买-->
                                    <span class="had">
                                        <b>已购买</b>
                                    </span>
                                </#if>
                            </#if>
                        </#if>

                    </div>
                <#if commodity_index%cols==cols-1>
                    </div>
                </#if>
            </#list>
        </div>

        <script>
            var divfun = function (id) {
                window.location.href = './commodity?id='+id;
            }
        </script>
</@layout>