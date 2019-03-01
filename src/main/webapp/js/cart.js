//进入编辑模式
function startModify() {
    $('.modify').removeAttr('hidden');
    $('#cc').removeAttr('readonly');
    $('#btn-modify').attr('disabled', 'disabled');
}
//结束编辑模式，提交修改
function endModify() {
    $('.modify').attr('hidden', 'true');
    $('#cc').attr('readonly', 'true');
    $('#btn-modify').removeAttr('disabled');
}
//限制价格输入框的输入，必须为大于1的正整数
function restrictNumber() {
    var value = $('#cc').val();
    if(!isNaN(value) && parseInt(value)>=1){
        $('#cc').val(parseInt(value));
    }else{
        $('#cc').val(1);
    }
}
//删除当前商品
function deleteCart() {
    var btn = $(event.target);
    var id = btn.parent().parent().children('th').text();
    var price = parseFloat(btn.parent().parent().find('.price').text());
    btn.parent().parent().remove();
    var total = parseFloat($('#total').text()) - price;
    $('#total').text(total);
}