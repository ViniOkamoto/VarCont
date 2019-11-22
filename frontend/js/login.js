const inputEmail = $('#inpEmail');
const inputPassword = $('#inpPassword'); 
const formSubmit = $('#formSubmit');
const buttonLogin = $('#btnLogin');

$(function() {
    setBackgroundSize();
})

function setBackgroundSize() {
    windowSize = $(window).height();
    $('.right-container').height(windowSize);
}

formSubmit.submit(function(form){
    form.preventDefault();
    const user = {
        email: inputEmail.val(),
        senha: inputPassword.val()
    };

    const api = ApiLogin();
    api.Login(user, function(answer){
        if (answer === 'admin')
            window.location.replace('http://127.0.0.1:5500/usuarios.html');
        else
            console.log('Redirecionando para a tela Caixa.')
    }, function(){
        buttonLogin.text('Carregando...');
    }, function(){
        buttonLogin.text('Login');
        inputPassword.val('');
    }, function(erro){
        if (erro.response.status == 400) {
            const toast = { title: 'Erro no Login', message: 'Usuario ou senha inválidos, tente novamente.', delay: 3000 };
            NewToast(toast);
        } else {
            const toast = { title: 'Erro no Login', message: 'Há um problema com a aplicação, entre em contato com o suporte.', delay: 3000 };
            NewToast(toast);
        }
    })
})