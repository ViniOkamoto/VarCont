const inputEmail = $('#inpEmail');
const inputPassword = $('#inpPassword') 
const formSubmit = $('#formSubmit');

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
    api.Login(user, function(aswer){
        if (answer)
            console.log('Login efetuado com sucesso.');
        else
            console.log('Email ou senha incorreto.');
    }, function(erro){
        console.log('Ocorreu um erro ao acessar a API de Login');
    })
})