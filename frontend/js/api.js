const baseUrlApiLogin = 'http://localhost:8181/login/';

function ApiLogin(){
    var api = new Object;

    api.Login = function(user, onSuccess, onBefore, onComplete, onError) {
        // Temporary - Ajax or Axios code to access api
        let answer = (user.email == 'user@user.com' && user.password == 'user123') ? 'admin' : undefined;

        onBefore();
        setTimeout(function(){
            onSuccess(answer);
            onComplete();
        }, 2000);
    }

    return api;
}