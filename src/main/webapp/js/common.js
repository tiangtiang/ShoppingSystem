var load = function (text) {
    var len = text.length*20;
    var tips = $('<div class="v-load v-load-result"><div class="load"><i></i><b>'
        +text+'</b></div></div>');
    tips.find('.load').css('width', len+'px');
    $('body').append(tips);
    return tips;
}