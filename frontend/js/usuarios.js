$(function () {
    onChangeRadios();
    expandCardEvent();
    listUsers();
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

function listUsers(){
    api = ApiUsuario();
    api.Listar(function(response){
        response.data.forEach(user => {
            user.admin = (user.admin) ? 'administrador' : 'caixa';
            let html = '<div class="card-item p-3 mb-3">' +
                '<div class="header-card cursor-pointer d-flex justify-content-between align-items-center">' +
                    '<h2>' + user.nome + '</h2>' +
                    '<i class="material-icons expand">expand_more</i>' +
                '</div>' +
                '<div class="body-card d-none">' +
                    '<div class="d-flex justify-content-between align-items-end">' +
                        '<div class="d-flex flex-column mt-3">' +
                            '<h3>Email:</h3>' +
                            '<div class="userInfo mb-2">' + user.email + '</div>'+
                            '<h3>Senha:</h3>' +
                            '<div class="userInfo mb-2">' + user.senha + '</div>' +
                            '<h3>Tipo de Usuário</h3>' +
                            '<div class="userInfo">' + user.admin + '</div>' +
                        '</div>' +
                        '<button class="btnEdit" data-id="' + user.id + '" data-toggle="modal" data-target="#edit-modal">Editar</button>' +
                    '</div>' +
                '</div>' +
            '</div>';

            text += html;
        })
        $('.list-users').html(text);
        expandCardEvent();
    }, function(){}, function(){}, function(){
        const toast = {
            title: 'Erro na listagem de usuários',
            message: 'Há um problema com a aplicação, entre em contato com o suporte.'    
        }
        NewToast(toast);
    })
}