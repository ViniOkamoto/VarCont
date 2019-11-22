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

function ApiUsuario() {
    var api = new Object;

    api.Adicionar = function (user, onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.post(baseUrlApi + 'usuario/criar', user)
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }
    
    api.Listar = function (onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.get(baseUrlApi + 'usuario/')
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }

    api.Consultar = function (id, onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.get(baseUrlApi + 'usuario/' + id)
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }

    api.Alterar = function (user, onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.put(baseUrlApi + 'usuario/' + user.id, user)
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }

    api.Excluir = function (id, onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.put(baseUrlApi + 'usuario/' + id)
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }

    return api;
}

function ApiProduto() {
    var api = new Object;

    api.Adicionar = function (product, onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.post(baseUrlApi + 'produto/criar', product)
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }
    
    api.Listar = function (onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.get(baseUrlApi + 'produto/')
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }

    api.Consultar = function (id, onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.get(baseUrlApi + 'produto/' + id)
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }

    api.Alterar = function (product, onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.put(baseUrlApi + 'produto/' + product.id, product)
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }

    api.Excluir = function (id, onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.put(baseUrlApi + 'produto/' + id)
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }

    return api;
}

function ApiLote() {
    var api = new Object;

    api.Adicionar = function (lot, onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.post(baseUrlApi + 'lote/criar', lot)
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }
    
    api.Listar = function (onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.get(baseUrlApi + 'lote/')
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }

    api.Consultar = function (id, onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.get(baseUrlApi + 'lote/' + id)
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }

    api.Alterar = function (lot, onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.put(baseUrlApi + 'lote/' + lot.id, lot)
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }

    api.Excluir = function (id, onSuccess, onBeforeSend, onComplete, onError) {
        onBeforeSend();
        axios.put(baseUrlApi + 'lote/' + id)
            .then(function (response) { onSuccess(response) })
            .catch(function (error) { onError(error) })
            .finally(function () { onComplete() });
    }

    return api;
}