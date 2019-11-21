const baseUrlApi = 'http://localhost:8181/';

function ApiLogin(){
    var api = new Object;

    // api.Login = function(user, onSuccess, onBefore, onComplete, onError) {
    //     // Temporary - Ajax or Axios code to access api
    //     let answer = (user.email == 'user@user.com' && user.password == 'user123') ? 'admin' : undefined;

    //     onBefore();
    //     setTimeout(function(){
    //         onSuccess(answer);
    //         onComplete();
    //     }, 2000);
    // }

    api.Login = function(user, onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.post(baseUrlApi + 'login/', user)
            .then(function(response){ onSuccess(response)})
            .catch(function(error){ onError(error) })
            .finally(function () { onComplete() });
    }

    return api;
}