<#include "./layout.ftl">
<@layout>
    <div class="container" style="margin-top:10px">
        <h4>内容发布</h4>
        <hr>
        <div class="row">
            <div class="col col-lg-8">
                <div class="form-group form-inline">
                    <label for="title" class="col-lg-2">标题：</label>
                    <input type="text" class="form-control col-lg-8" id="title">
                </div>

                <div class="form-group form-inline">
                    <label for="summary" class="col-lg-2">摘要：</label>
                    <input type="text" class="form-control col-lg-8" id="summary">
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
                            <input type="text" class="form-control" id="website" placeholder="图片网址">
                            <button class="btn btn-primary" style="margin-left: 30px" onclick="downImgFormNet()">获取图片</button>
                        </div>
                        <div id="lf">
                            <input type="file" onchange="showPreview(this, 'temp')" id="local-file" class="form-control-file">
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
                <textarea class="form-control col-lg-11" rows="10" id="content"></textarea>
            </div>
        </div>
        <div class="row">
            <div class="col form-group form-inline">
                <label for="content" class="col-lg-1">价格：</label>
                <input class="form-control col-lg-2" id="price">
                <label for="content" class="col-lg-1">元</label>
            </div>
        </div>
        <div class="row">
            <div class="col text-center">
                <button class="btn btn-primary" id="submit">发布</button>
            </div>
        </div>
    </div>

    <script>
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
        function downImgFormNet() {
            var url = $('#website').val()
            $('#temp').attr('src', url);
            $('#temp').attr('hidden', false);
        }
    </script>
</@layout>