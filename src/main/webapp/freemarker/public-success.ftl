<#include "./layout.ftl">
<@layout>

    <div class="container" style="margin-top: 50px">
        <div class="row">
            <div class="col col-lg-4"></div>
            <div class="col col-lg-4"><h3 class="text-center">
                <#if modify??>
                    保存成功
                <#else >
                    发布成功
                </#if>
                </h3></div>
            <div class="col-lg-4"></div>
        </div>
        <div class="row" style="margin-top: 20px">
            <div class="col-lg-4 text-center"></div>
            <div class="col-lg-2 text-center"><a href="commodity?id=${cid}">查看内容</a></div>
            <div class="col-lg-2 text-center"><a href="index">返回首页</a></div>
        </div>
    </div>

</@layout>