
function ApiLogin(){
    var api = new Object;

    api.Login(user, successAction, errorAction) {
        // Temporary - Ajax or Axios code to access api
        let answer = (user.login == 'user@user.com' && user.password == 'user123') ? true : false;
        successAction(answer);
    }
    return api;
}