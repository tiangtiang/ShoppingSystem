<#include "./layout.ftl">
<@layout>
    <div class="container" style="margin-top:10px">
        <h4>内容发布</h4>
        <hr>
        <div class="row">
            <div class="col col-lg-8">
                <div class="form-group form-inline">
                    <label for="title" class="col-lg-2">标题：</label>
                    <input type="text" class="form-control col-lg-8"
                           id="title" placeholder="2-80个字符" onchange="textChange('title', 2, 80)">
                        <div class="invalid-feedback">
                            请输入合法的标题，2-80个字符
                        </div>
                </div>

                <div class="form-group form-inline">
                    <label for="summary" class="col-lg-2">摘要：</label>
                    <input type="text" class="form-control col-lg-8" id="summary"
                        placeholder="2-140个字符" onchange="textChange('summary', 2, 140)">
                    <div class="invalid-feedback">
                        请输入合法的摘要，2-140个字符
                    </div>
                </div>
                <div class="form-group form-inline">
                    <label class="col-lg-2">图片：</label>
                    <div class="col-lg-8">
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" id="local" name="upload" class="custom-control-input" checked>
                                <label class="custom-control-label" for="local">本地上传</label>
                            </div>
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" id="internet" name="upload" class="custom-control-input">
                                <label class="custom-control-label" for="internet">图片网址</label>
                            </div>
                    </div>
                </div>
                <div class="form-group form-inline">
                    <div class="col-lg-2"></div>
                    <div class="col-lg-8">
                        <div id="net" class="form-group form-inline" hidden>
                            <input type="text" class="form-control" id="website"
                                   placeholder="图片网址" onchange="downImgFormNet()">
                            <#--<button class="btn btn-primary" style="margin-left: 30px" onclick="downImgFormNet()">获取图片</button>-->
                            <div class="invalid-feedback">
                                请输入图片网址
                            </div>
                        </div>
                        <div id="lf">
                            <input type="file" onchange="showPreview(this, 'temp')" id="local-file" class="form-control-file" oninput="localFileChange()">
                            <div class="invalid-feedback" id="error-img">
                                请选择大小不超过1M的图片
                            </div>
                        </div>

                    </div>
                </div>

            </div>
            <div class="col float-right">
                <img src="" id="temp" style="width: 200px; height: 200px;" hidden="true" />
            </div>
        </div>
        <div class="row" style="margin-top: 20px">
            <div class="col form-group form-inline">
                <label for="content" class="col-lg-1">正文：</label>
                <textarea class="form-control col-lg-11" rows="10" id="content"
                    placeholder="2-1000个字符" onchange="textChange('content', 2, 1000)"></textarea>
                <div class="invalid-feedback">
                    请输入合法的正文描述，2-1000个字符
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col form-group form-inline">
                <label for="content" class="col-lg-1">价格：</label>
                <input class="form-control col-lg-2" id="price" onchange="priceChange()">
                <label for="content" class="col-lg-1">元</label>
                <div class="invalid-feedback">
                    请输入合法的数字
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col text-center">
                <button class="btn btn-primary" id="submit" onclick="publicCommodity()">发布</button>
            </div>
        </div>
    </div>

    <script>
        // 切换图片上传方式
        $("input[name=upload]").click(function () {
            var id = $(this).attr('id');
            if(id == 'local'){
                $('#lf').removeAttr('hidden');
                $('#net').attr('hidden', 'hidden');
            }else if(id=='internet'){
                $('#net').removeAttr('hidden');
                $('#lf').attr('hidden', 'hidden');
            }
        });
        // 提前预览本地图片
        function showPreview(source, imgId) {
            var file = source.files[0];
            if(window.FileReader) {
                var fr = new FileReader();
                fr.onloadend = function(e) {
                    var show = document.getElementById(imgId);
                    show.src = e.target.result;
                    show.hidden=false;
                    show.style.display = "block";
                }
                fr.readAsDataURL(file);
            }
        }
        // 提前预览web图片
        function downImgFormNet() {
            var url = $('#website').val()
            $('#temp').attr('src', url);
            $('#temp').attr('hidden', false);
            netChange();
        }
        // 发布商品
        function publicCommodity() {
            if (!validateText('title', 2, 80)) {
                $('#title').addClass('is-invalid');
                return;
            }
            var title = $('#title').val();

            if (!validateText('summary', 2, 140)) {
                $('#summary').addClass('is-invalid');
                return;
            }
            var summary = $('#summary').val();

            if (!validateText('content', 2, 1000)) {
                $('#content').addClass('is-invalid');
                return;
            }
            var content = $('#content').val();

            if (!validatePrice()) {
                $('#price').addClass('is-invalid');
                return;
            }
            var price = $('#price').val();

            if (!$('#net').attr('hidden')) {
                var imgUrl = $('#website').val();
            }
            if (!$('#lf').attr('hidden')) {
                var file = document.getElementById('local-file').files[0];
            }

            var data = {
                title: title,
                summary: summary,
                content: content,
                price: price,
                imgUrl: imgUrl,
                file: file
            };
            $.ajax('add',{
                data: data,
                method: 'POST',
                success: function (data) {
                    alert(data);
                },
                error: function () {
                    alert("发送失败！");
                }
            })
        }

        // 文字框限制
        function validateText(id, min, max) {
            var ctrl = $('#'+id);
            var value = String(ctrl.val());
            if(value.length<min || value.length>max){
                return false;
            }
            return true;
        }
        // 文本框输入事件
        function textChange(id, min,max) {
            if(validateText($('#'+id).attr('id'), min, max))
                $('#'+id).removeClass('is-invalid');
        }
        // 判断价格输入框输入是否有效
        function validatePrice() {
            var ctrl = $('#price');
            var value = ctrl.val();
            if(value == '' || isNaN(value))
                return false;
            return true;
        }
        // 价格输入框输入事件
        function priceChange() {
            if(validatePrice()){
                $('#price').removeClass('is-invalid');
            }
        }
        // 检查图片是否存在
        function checkImage() {
            if(!$('#net').attr('hidden')){
                var value = $('#website').val();
                if(value == ''){
                    $('#website').addClass('is-invalid');
                    return false;
                }
                return true;
            }
            if(!$('#lf').attr('hidden')){
                var value = document.getElementById('local-file').files;
                if(value.length == 0){
                    $('#local-file').addClass('is-invalid');
                    return false;
                }else if(value[0].size > 1024*1024){
                    $('#local-file').addClass('is-invalid');
                    return false;
                }
                return true;
            }
        }
        // 本地文件上传
        function localFileChange() {
            var value = document.getElementById('local-file').files;
            if(value.length>0){
                $('#local-file').removeClass('is-invalid');
            }
        }
        // 网络文件地址
        function netChange() {
            var ctrl = $('#website');
            if(ctrl.val() != ''){
                ctrl.removeClass('is-invalid');
            }
        }
    </script>
</@layout>