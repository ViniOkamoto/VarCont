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
        password: inputPassword.val()
    };

    const api = ApiLogin();
    api.Login(user, function(answer){
        if (answer !== undefined)
            console.log('Login efetuado com sucesso.');
        else
            console.log('Email ou senha incorretos.');
            inputPassword.val('');
    }, function(){
        buttonLogin.text('Carregando...');
    }, function(){
        buttonLogin.text('Login');
    }, function(erro){
        console.log('Ocorreu um erro ao acessar a API de Login');
    })
})