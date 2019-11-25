$(function () {
    onChangeRadios();
    listUsers();
})

function onChangeRadios() {
    const radioAdmin = $('#radio-admin');
    const radioCaixa = $('#radio-caixa');

    radioAdmin.change(function () {
        updateRadio();
    })
    radioCaixa.change(function () {
        updateRadio();
    })
}

function updateRadio() {
    const radioAdmin = $('#radio-admin');
    const radioCaixa = $('#radio-caixa');
    const icoAdmin = $('#i-admin');
    const icoCaixa = $('#i-caixa');

    if (radioAdmin.prop('checked'))
        icoAdmin.text('radio_button_checked');
    else
        icoAdmin.text('radio_button_unchecked');

    if (radioCaixa.prop('checked'))
        icoCaixa.text('radio_button_checked');
    else
        icoCaixa.text('radio_button_unchecked');
}


function expandCardEvent() {
    const headerCard = $('.header-card');

    headerCard.click(function (e) {
        let bodyCard = $(this).parent().find('.body-card');
        let expandIcon = $(this).find('.expand');

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

function listUsers() {
    api = ApiUsuario();
    api.Listar(function (response) {
        let text = "";
        response.data.forEach(user => {
            user.admin = (user.admin) ? 'Administrador' : 'caixa';
            let html = '<div class="card-item p-3 mb-3">' +
                '<div class="header-card cursor-pointer d-flex justify-content-between align-items-center">' +
                '<h2>' + user.nome + '</h2>' +
                '<i class="material-icons expand">expand_more</i>' +
                '</div>' +
                '<div class="body-card d-none">' +
                '<div class="d-flex justify-content-between align-items-end">' +
                '<div class="d-flex flex-column mt-3">' +
                '<h3>Email:</h3>' +
                '<div class="dataInfo mb-2">' + user.email + '</div>' +
                '<h3>Senha:</h3>' +
                '<div class="dataInfo mb-2">' + user.senha + '</div>' +
                '<h3>Tipo de Usuário</h3>' +
                '<div class="dataInfo">' + user.admin + '</div>' +
                '</div>' +
                '<div class="d-flex">' +
                '<button class="btnEdit d-flex align-items-center" data-id="' + user.id + '" data-toggle="modal"' +
                    'data-target="#user-modal"><i class="material-icons">edit</i></button>' +
                '<button class="btnDel d-flex align-items-center ml-2" data-id="' + user.id + '"><i class="material-icons">delete</i></button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';

            text += html;
        })
        $('.list-users').html(text);
        expandCardEvent();
        deleteClickEvent();
    }, function () { }, function () { }, function (error) {
        const toast = {
            title: 'Erro na listagem de usuários',
            message: 'Há um problema com a aplicação, entre em contato com o suporte.',
            delay: 3000
        }
        NewToast(toast);
    })
}

$('#user-modal').on('show.bs.modal', function (e) {
    updateRadio();
    const button = $(e.relatedTarget);
    const modal = $(this);
    $('#id-user').val(button.data('id'));
    const idUser = $('#id-user').val();

    if (idUser != null) {
        const api = ApiUsuario();
        api.Consultar(idUser, function (response) {
            const user = response.data;
            modal.find('#nome').val(user.nome);
            modal.find('#email').val(user.email);
            modal.find('#senha').val(user.senha);
            if (user.admin)
                $('#radio-admin').prop('checked', true);
            else
                $('#radio-caixa').prop('checked', true);
            updateRadio();

        }, function () { }, function () { }, function (error) {
            const toast = {
                title: 'Erro na consulta do usuário',
                message: 'Há um problema com a aplicação, entre em contato com o suporte.',
                delay: 3000
            }
            NewToast(toast);
        })
    }
})

$('#user-modal').on('hidden.bs.modal', function (e) {
    $('#id-user').val('');
    $('#nome').val('');
    $('#email').val('');
    $('#senha').val('');
    $('#radio-admin').prop('checked', false);
    $('#radio-caixa').prop('checked', false);
})

$('.btnSave').click(function () {
    if (validateModalFields()) {
        const user = {
            nome: $('#nome').val(),
            email: $('#email').val(),
            senha: $('#senha').val(),
            admin: ($('#radio-admin').prop('checked')) ? true : false
        }

        const api = ApiUsuario();

        if ($('#id-user').val() != '') {
            user.id = $('#id-user').val();
            api.Alterar(user, function (response) {
                const toast = {
                    title: 'Sucesso',
                    message: 'Usuário alterado com êxito.',
                    delay: 3000
                }
                NewToast(toast);
                $('#user-modal').modal('hide');
                listUsers();
            }, function () {
                $('.btnSave').text('Carregando...');
            }, function () {
                $('.btnSave').text('Salvar');
            }, function (error) {
                const toast = {
                    title: 'Erro ao alterar usuário',
                    message: 'Há um problema com a aplicação, entre em contato com o suporte.',
                    delay: 3000
                }
                NewToast(toast);
            });
        } else {
            api.Adicionar(user, function (response) {
                const toast = {
                    title: 'Sucesso',
                    message: 'Usuário adicionado com êxito.',
                    delay: 4000
                }
                NewToast(toast);
                $('#user-modal').modal('hide');
                listUsers();
            }, function () {
                $('.btnSave').text('Carregando...');
            }, function () {
                $('.btnSave').text('Salvar');
            }, function (error) {
                const toast = {
                    title: 'Erro ao adicionar usuário',
                    message: 'Há um problema com a aplicação, entre em contato com o suporte.',
                    delay: 3000
                }
                NewToast(toast);
            })
        }
    }
})

function deleteClickEvent(){
    $('.btnDel').click(function(){
        const id = $(this).data('id');
        const api = ApiUsuario();
        api.Excluir(id, function(response){
            const toast = {
                title: 'Sucesso',
                message: 'Usuário excluído com êxito.',
                delay: 4000
            }
            NewToast(toast);
            listUsers();
        }, function(){}, function(){}, function(error){
            const toast = {
                title: 'Erro ao excluir usuário',
                message: 'Há um problema com a aplicação, entre em contato com o suporte.',
                delay: 3000
            }
            NewToast(toast);
        })
    })
}

function validateModalFields() {
    let erro = false;
    if ($('#nome').val() == '') {
        const toast = {
            title: 'Campos vazios',
            message: 'O nome é obrigatório.',
            delay: 3000
        }
        NewToast(toast)
        erro = true;
    }

    if ($('#email').val() == '') {
        const toast = {
            title: 'Campos vazios',
            message: 'O email é obrigatório.',
            delay: 3000
        }
        NewToast(toast)
        erro = true;
    }

    if ($('#senha').val() == '') {
        const toast = {
            title: 'Campos vazios',
            message: 'A senha é obrigatória.',
            delay: 3000
        }
        NewToast(toast)
        erro = true;
    }

    if ($('#radio-admin').prop('checked') == false && $('#radio-caixa').prop('checked') == false) {
        const toast = {
            title: 'Campos vazios',
            message: 'O tipo de usuário é obrigatório.',
            delay: 3000
        }
        NewToast(toast)
        erro = true;
    }

    if (erro)
        return false;
    else
        return true;
}