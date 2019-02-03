var load = function (text) {
    var tips = $('<div class="v-load v-load-result"><div class="load"><i></i><b>'
        +text+'</b></div></div>');
    $('body').append(tips);
    return tips;
}