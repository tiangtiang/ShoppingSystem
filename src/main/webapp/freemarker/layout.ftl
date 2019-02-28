<#--定义带导航栏的母版-->
<#macro layout>
<html>

<head>
    <title>首页</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="./js/jquery.js"></script>
    <#--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

</head>

<body style="background: #F3F3F3">

<style>
    #nav a{
        color: #ffffff;
    }
</style>
<#--首部导航栏-->
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #6eb5e8;" id="nav">
    <a class="navbar-brand" href="./index">首页</a>
    <div class="collapse navbar-collapse" id="navbarNav"></div>
            <#if user??>
            <#--如果用户已经登录，根据用户类别显示不同的标题-->
                <div class="my-2 my-lg-0 nav-item">
                    ${user.nickName} 你好
                </div>
                <div class="my-2 my-lg-0 nav-item">
                    <a class="nav-link my-2 my-sm-0" href="./logout">退出</a>
                </div>
                <#if user.isBuyer == 1>
                    <div class="my-2 my-lg-0 nav-item">
                        <a class="nav-link my-2 my-sm-0" href="./bought">财务</a>
                    </div>
                    <div class="my-2 my-lg-0 nav-item">
                        <a class="nav-link my-2 my-sm-0" href="./cart">购物车</a>
                    </div>
                <#elseif user.isBuyer == 0>
                    <div class="my-2 my-lg-0 nav-item">
                        <a class="nav-link my-2 my-sm-0" href="public">发布</a>
                    </div>
                </#if>
            <#else>
            <#--如果没有登录，显示登录按钮-->
                <div class="my-2 my-lg-0 nav-item">
                    <a class="nav-link my-2 my-sm-0" href="html/login.html">登录</a>
                </div>
            </#if>
</nav>

<#nested />

</body>
</html>

</#macro>