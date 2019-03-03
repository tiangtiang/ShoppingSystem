//进入编辑模式
function startModify() {
    if($('tbody').find('tr').length === 1){
        var div = load('购物车中没有商品');
        setTimeout(function () {
            div.remove();
        }, 1000);
    }else {
        $('.modify').removeAttr('hidden');
        $('.cc').removeAttr('readonly');
        $('#btn-modify').attr('disabled', 'disabled');
    }
}
//结束编辑模式，提交修改
function endModify() {
    var cids = [];
    var counts = [];
    $('.modified').each(function (i, item) {
        cids.push($(item).find('.cid').text());
        counts.push($(item).find('.cc').val());
    });
    if(counts.length > 0) {
        $.ajax('cart/update', {
            method: 'POST',
            data: {
                cids: cids,
                counts: counts
            },
            success: function (result) {
                if (result === 'success') {
                    var div = load('修改成功');
                    setTimeout(function () {
                        div.remove();
                    }, 1000);
                    $('.modify').attr('hidden', 'true');
                    $('.cc').attr('readonly', 'true');
                    $('#btn-modify').removeAttr('disabled');
                } else {
                    var div = load('修改失败');
                    setTimeout(function () {
                        div.remove();
                    }, 1000);
                }
            },
            error: function () {
                var div = load('请求发送失败');
                setTimeout(function () {
                    div.remove();
                }, 1000);
            }
        });
    }else{
        $('.modify').attr('hidden', 'true');
        $('.cc').attr('readonly', 'true');
        $('#btn-modify').removeAttr('disabled');
    }
}
//限制价格输入框的输入，必须为大于1的正整数
function restrictNumber() {
    var num = $(event.target);
    var value = num.val();
    if(!isNaN(value) && parseInt(value)>=1){
        num.val(parseInt(value));
    }else{
        num.val(1);
    }
    //重新计算总价
    var sum = 0;
    num.parents('table').find('.price').each(function (i, item) {
        var count = $(item).parents('tr').find('.cc').val();
        count = parseInt(count);
        sum += count * parseFloat($(item).text());
    });
    $('#total').text(sum);
    num.parents('tr').addClass('modified');
}
//删除当前商品
function deleteCart() {
    var btn = $(event.target);
    $('#del-item').modal('show');
    // 重新为确定删除按钮绑定事件
    $('#confirm-del').unbind('click');

    $('#confirm-del').click(
        function () {
            var tr = btn.parents('tr');
            $.ajax('cart/del',{
                method: 'POST',
                data: {
                    cid: tr.find('.cid').text()
                },
                success: function (result) {
                    if(result == 'success') {
                        // 查找编号
                        var num = tr.children('th').text();
                        //比该行编号大的行的编号都减一
                        btn.parents('tbody').find('th').each(function (i, item) {
                            if (!isNaN($(item).text())) {
                                var nn = parseInt($(item).text());
                                if (nn > num) {
                                    $(item).text(nn - 1);
                                }
                            }
                        });
                        // 查找该行的价格和数量，删掉该行，同时重新计算总价
                        var price = parseFloat(tr.find('.price').text());
                        var cnt = parseInt(tr.find('.cc').val());
                        tr.remove();
                        var total = parseFloat($('#total').text()) - cnt * price;
                        $('#total').text(total);

                        var div = load('删除成功');
                        setTimeout(function () {
                            div.remove();
                        }, 1000);
                    }else{
                        var div = load('删除失败');
                        setTimeout(function () {
                            div.remove();
                        }, 1000);
                    }
                },
                error: function () {
                    var div = load('请求发送失败');
                    setTimeout(function () {
                        div.remove();
                    }, 1000);
                }
            });
            
        }
    );
}