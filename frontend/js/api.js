const baseUrlApi = 'http://localhost:8181/';

function ApiLogin() {
    var api = new Object;

    api.Login = function (user, onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.post(baseUrlApi + 'login/', user)
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }

    return api;
}