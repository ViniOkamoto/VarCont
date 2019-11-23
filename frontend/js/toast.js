
function NewToast(toast) {
    if ($('#toast-place').length === 0) {     
        $('body').prepend(
            '<div aria-live="polite" style="z-index: 2000" aria-atomic="true" class="position-relative">' +
                '<div class="position-absolute p-3" id="toast-place" style="top:0; right:0;"></div>' +
            '</div>'
        )   
    }

    let prop;
    if (toast.delay == undefined)
        prop = 'data-autohide="false"';
    else
        prop = 'data-delay="' + toast.delay + '"';

    $('#toast-place').append(
        '<div class="toast" role="alert" style="font-family: Segoe UI, Tahoma, Geneva, Verdana, sans-serif" aria-live="assertive" aria-atomic="true" ' + prop + '>' +
            '<div class="toast-header">' +
                '<strong class="mr-auto">' + toast.title + '</strong>' +
                '<small class="text-muted">Agora</small>' +
                '<button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Fechar">' +
                    '<span aria-hidden="true">&times;</span>' +
                '</button>' +
            '</div>' +
            '<div class="toast-body">' + toast.message + '</div>' +
        '</div>'
      );

    $('.toast').toast('show');

    $('.toast').on('hidden.bs.toast', e => {
        $(e.currentTarget).remove();
    })
}