<#include "./layout.ftl">
<@layout>
<script src="js/md5.js"></script>
<script src="js/common.js"></script>
<link href="css/common.css" rel="stylesheet">
<style>
    :root {
        --input-padding-x: 1.5rem;
        --input-padding-y: .75rem;
    }

    .card-signin {
        margin-top: 10%;
        border: 0;
        border-radius: 1rem;
        box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
    }

    .card-signin .card-title {
        margin-bottom: 2rem;
        font-weight: 300;
        font-size: 1.5rem;
    }

    .card-signin .card-body {
        padding: 2rem;
    }

    .form-signin {
        margin-top: 10%;
        width: 100%;
    }

    .form-label-group {
        position: relative;
        margin-bottom: 1rem;
    }

    .form-label-group input {
        height: auto;
        border-radius: 2rem;
    }

    .form-label-group>input,
    .form-label-group>label {
        padding: var(--input-padding-y) var(--input-padding-x);
    }

    .form-label-group>label {
        position: absolute;
        top: 0;
        left: 0;
        display: block;
        width: 100%;
        margin-bottom: 0;
        /* Override default `<label>` margin */
        line-height: 1.5;
        color: #495057;
        border: 1px solid transparent;
        border-radius: .25rem;
        transition: all .1s ease-in-out;
    }

    .form-label-group input::-webkit-input-placeholder {
        color: transparent;
    }

    .form-label-group input:-ms-input-placeholder {
        color: transparent;
    }

    .form-label-group input::-ms-input-placeholder {
        color: transparent;
    }

    .form-label-group input::-moz-placeholder {
        color: transparent;
    }

    .form-label-group input::placeholder {
        color: transparent;
    }

    .form-label-group input:not(:placeholder-shown) {
        padding-top: calc(var(--input-padding-y) + var(--input-padding-y) * (2 / 3));
        padding-bottom: calc(var(--input-padding-y) / 3);
    }

    .form-label-group input:not(:placeholder-shown)~label {
        padding-top: calc(var(--input-padding-y) / 3);
        padding-bottom: calc(var(--input-padding-y) / 3);
        font-size: 12px;
        color: #777;
    }

</style>
<#if user??>
    <div class="container" style="text-align: center">
        <h2 style="margin-top: 20rem">您已经有账号了，无法重复注册，将于<span id="time">5</span>秒后返回首页！</h2>
    </div>
    <script>
        //设定倒数秒数
        var t = 5;
        //显示倒数秒数
        function showTime(){
            t -= 1;
            $('#time').text(t);
            if(t==0){
                location.href='index';
            }
            //每秒执行一次,showTime()
            setTimeout("showTime()",1000);
        }
        //执行showTime()
        showTime();
    </script>
<#else>
<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">注  册</h5>
                    <div class="form-signin">
                        <div class="form-label-group">
                            <input id="username" class="form-control" type="text" placeholder="用户名"
                                   oninput="inInput(this)">
                            <div class="invalid-feedback">
                                请输入用户名
                            </div>
                            <label for="username">用户名</label>
                        </div>

                        <div class="form-label-group">
                            <input id="nickName" class="form-control" type="text" placeholder="昵称"
                                   oninput="inInput(this)">
                            <div class="invalid-feedback">
                                请输入昵称
                            </div>
                            <label for="nickName">昵称</label>
                        </div>

                        <div class="form-label-group">
                            <input id="password" type="password" placeholder="密码" class="form-control" oninput="inInput(this)">
                            <div class="invalid-feedback">
                                请输入密码
                            </div>
                            <label for="password">密码</label>
                        </div>
                        <div class="form-label-group">
                            <input id="re-password" type="password" placeholder="再次输入密码" class="form-control" oninput="inInput(this)">
                            <div class="invalid-feedback">
                                请再次输入密码
                            </div>
                            <label for="re-password">再次输入密码</label>
                        </div>

                        <div class="form-label-group" style="text-align: center">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="type" id="buyer" value="1" checked>
                                <label class="form-check-label" for="buyer">买家</label>
                            </div>
                            <div class="form-check form-check-inline" style="margin-left: 10rem;">
                                <input class="form-check-input" type="radio" name="type" id="seller" value="0">
                                <label class="form-check-label" for="seller">卖家</label>
                            </div>
                        </div>

                        <div id="error" class="alert alert-danger" role="alert" hidden></div>
                        <div style="text-align:center">
                            <button id="submit" class="btn btn-primary" onclick="bntClick()" style="border-radius:20px;">提 交</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--登录跳转页面-->
<script>
    // 输入框输入内容变化时
    var inInput = function (cnt) {
        if($(cnt).val()!=''){
            $(cnt).removeClass('is-invalid');
        }
    }
    //点击按钮提交登录情况
    var bntClick = function () {
        var inputUsername = $('#username');
        if(inputUsername.val() == ''){
            inputUsername.addClass('is-invalid');
            return;
        }
        var nickName = $('#nickName');
        if(nickName.val() == ''){
            nickName.addClass('is-invalid');
            return;
        }
        var inputPassword = $('#password');
        if(inputPassword.val() == ''){
            inputPassword.addClass('is-invalid');
            return;
        }
        var reInputPwd = $('#re-password');
        if(reInputPwd.val() == ''){
            reInputPwd.addClass('is-invalid');
            return;
        }
        if(reInputPwd.val()!==inputPassword.val()){
            $('#error').text('两次密码输入不相同');
            $('#error').removeAttr('hidden');
            return;
        }
        var isBuyer = $("input[name='type']:checked").val();
        $.ajax(
                {
                    url: './register/do',
                    method: 'POST',
                    data: {
                        userName: $('#username').val(),
                        password: md5($('#password').val()),
                        nickName: nickName.val(),
                        isBuyer: isBuyer
                    },
                    success: function (data) {
                        if (data == 'failed') {
                            $('#error').text("注册失败");
                            $('#error').removeAttr('hidden');
                        } else if(data == 'success'){
                            var div = load("注册成功");
                            setTimeout(function () {
                                div.remove();
                                window.location.href = "index";
                            }, 1000);
                        }else if(data == 'exist'){
                            $('#error').text("用户名已被使用");
                            $('#error').removeAttr('hidden');
                        }
                    },
                    error: function () {
                        $('#error').text("访问服务器失败");
                        $('#error').removeAttr('hidden');
                    }
                }
        )
    }
</script>
</#if>
</@layout>