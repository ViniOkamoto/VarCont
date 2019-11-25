$(function () {
})

$("#produto-select").on("change", function () {
  var value = $(this).val();
  $('.close').trigger('click');
  const apiProduto = ApiProduto();
  console.log(value)
  apiProduto.Consultar(value, function (response) {
    const produto = response.data
    $('#produto-custo').attr({ placeholder: produto.valorVenda })
  }, function () { }, function () { }, function (error) {
    const toast = {
      title: 'Erro na consulta do produto',
      message: 'Há um problema com a aplicação, entre em contato com o suporte.'
    }
    NewToast(toast);
    console.log(error);
  })
  const apiLote = ApiLote();
  apiLote.Estoque(function (response) {
    const estoques = response.data
    estoques.forEach(estoque => {
      var id = estoque[0];
      var quantidade = estoque[2];
      if (id == value) {
        $('#estoque-quantidade').attr({ placeholder: quantidade })
      }
    })
  }, function () { }, function () { }, function (error) {
    const toast = {
      title: 'Erro na consulta do estoque',
      message: 'Há um problema com a aplicação, entre em contato com o suporte.'
    }
    NewToast(toast);
    console.log(error);
  })
})
$("#venda-caixa").ready(function () {
  const apiProduto = ApiProduto();
  carregarSelect("Produtos")
  function carregarSelect(id) {
    apiProduto.Listar(function (response) {
      let text = '<option> Selecionar ' + id + '</option>'
      const produto = response.data
      produto.forEach(prod => {
        let html = '<option value= ' + prod.id + ' >' + prod.nomeProd + '</option>'
        text += html
      })
      $("#produto-select").html(text)
    }, function () { }, function () { }, function (error) {
      const toast = {
        title: 'Erro na listagem de produtos',
        message: 'Há um problema com a aplicação, entre em contato com o suporte.'
      }
      NewToast(toast);
      console.log(error);
    })
  }
})