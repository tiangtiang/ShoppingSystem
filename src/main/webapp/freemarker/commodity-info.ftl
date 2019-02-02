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
                <p><h4>${commodity.title}</h4></p>
                <p>${commodity.summary}</p>
                <p>￥ ${commodity.price}</p>
            </div>
        </div>

        <div style="margin-top: 50px">
            <p><h4>详细信息</h4></p>
            <hr/>
            <p>${commodity.content}</p>
        </div>
    </div>
</@layout>