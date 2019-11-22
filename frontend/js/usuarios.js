$(function () {
    onChangeRadios();
    expandCardEvent();
})

function onChangeRadios() {
    const radioAdmin = $('#radio-admin');
    const radioCaixa = $('#radio-caixa');
    const icoAdmin = $('#i-admin');
    const icoCaixa = $('#i-caixa');

    radioAdmin.change(function () {
        updateRadio();
    })
    radioCaixa.change(function () {
        updateRadio();
    })

    function updateRadio() {
        if (radioAdmin.prop('checked'))
            icoAdmin.text('radio_button_checked');
        else
            icoAdmin.text('radio_button_unchecked');

        if (radioCaixa.prop('checked'))
            icoCaixa.text('radio_button_checked');
        else
            icoCaixa.text('radio_button_unchecked');
    }
}

function expandCardEvent() {
    const headerCard = $('.header-card');
    const bodyCard = $('.body-card')
    const expandIcon = $('.expand')

    headerCard.click(function (e) {
        if (bodyCard.hasClass('d-none')) {
            bodyCard.removeClass('d-none');
            expandIcon.text('expand_less')
        }
        else {
            bodyCard.addClass('d-none');
            expandIcon.text('expand_more')
        }
    })
}