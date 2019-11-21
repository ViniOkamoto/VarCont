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
        if (answer !== undefined) {
            if (answer === 'admin')
                window.location.replace('http://127.0.0.1:5500/dashboard.html');
            else
                console.log('Redirecionando para a tela Caixa.')            
        }
        else
        {
            const toast = { title: 'Erro no Login', message: 'Usuario ou senha inválidos, tente novamente.', delay: 3000 };
            NewToast(toast);
        }
        inputPassword.val('');
    }, function(){
        buttonLogin.text('Carregando...');
    }, function(){
        buttonLogin.text('Login');
    }, function(erro){
        const toast = { title: 'Erro no Login', message: 'Há um problema com a aplicação, entre em contato com o suporte.', delay: 3000 };
        NewToast(toast);
    })
})