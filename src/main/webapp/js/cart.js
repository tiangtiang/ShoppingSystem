//进入编辑模式
function startModify() {
    $('.modify').removeAttr('hidden');
    $('.cc').removeAttr('readonly');
    $('#btn-modify').attr('disabled', 'disabled');
}
//结束编辑模式，提交修改
function endModify() {
    $('.modify').attr('hidden', 'true');
    $('.cc').attr('readonly', 'true');
    $('#btn-modify').removeAttr('disabled');
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
            // 查找编号
            var num = tr.children('th').text();
            //比该行编号大的行的编号都减一
            btn.parents('tbody').find('th').each(function (i, item) {
                if(!isNaN($(item).text())){
                    var nn = parseInt($(item).text());
                    if(nn > num){
                        $(item).text(nn-1);
                    }
                }
            });
            // 查找该行的价格和数量，删掉该行，同时重新计算总价
            var price = parseFloat(tr.find('.price').text());
            var cnt = parseInt(tr.find('.cc').val());
            tr.remove();
            var total = parseFloat($('#total').text()) - cnt*price;
            $('#total').text(total);
        }
    );
}