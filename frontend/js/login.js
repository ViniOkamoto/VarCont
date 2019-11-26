const inputEmail = $('#inpEmail');
const inputPassword = $('#inpPassword');
const formSubmit = $('#formSubmit');
const buttonLogin = $('#btnLogin');

$(function () {
    setBackgroundSize();
})

function setBackgroundSize() {
    windowSize = $(window).height();
    $('.right-container').height(windowSize);
}

formSubmit.submit(function (form) {
    form.preventDefault();
    const user = {
        email: inputEmail.val(),
        senha: inputPassword.val()
    };

    const api = ApiLogin();
    api.Login(user, function (answer) {
        if (answer.data === 'admin')
            window.location.replace('dashboard.html');
        else
            window.location.replace('caixa.html');
    }, function () {
        buttonLogin.text('Carregando...');
    }, function () {
        buttonLogin.text('Login');
        inputPassword.val('');
    }, function (erro) {
        if (erro.response.status == 400) {
            const toast = { title: 'Erro no Login', message: 'Usuario ou senha inválidos, tente novamente.', delay: 3000 };
            NewToast(toast);
        } else {
            const toast = { title: 'Erro no Login', message: 'Há um problema com a aplicação, entre em contato com o suporte.', delay: 3000 };
            NewToast(toast);
        }
    })
})