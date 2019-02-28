<#include "./layout.ftl">

<@layout>
    <div class="container">
        <h3>已购列表</h3>
        <table class="table table-striped list" id="data-table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">商品</th>
                <th scope="col">商品图片</th>
                <th scope="col">购买日期</th>
                <th scope="col">购买数量</th>
                <th scope="col">购买价格</th>
            </tr>
            </thead>
            <tbody>
            <#assign i=1>
            <#assign total=0>
            <#list bought as item>
                <tr>
                    <th scope="row">${i}</th>
                    <td>${item.commodity.title}</td>
                    <td>
                        <#if item.commodity.imgUrl??>
                            <img src="${item.commodity.imgUrl}" style="width: 50px;height: 50px">
                        <#else >
                            <img src="index/image/${item.commodity.id}" style="width: 50px;height: 50px">
                        </#if>
                    </td>
                    <td>${item.buyTime?string["yyyy/MM/dd HH:mm"]}</td>
                    <td class="count">${item.count}</td>
                    <td>￥${item.buyPrice} </td>
                </tr>
                <#assign i=i+1>
                <#assign total=total+item.count*item.buyPrice>
            </#list>
            <tr>
                <th scope="row">总价</th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>￥<span style="margin-right: 10px">${total}</span> </td>
            </tr>
            </tbody>

        </table>

    </div>
</@layout>