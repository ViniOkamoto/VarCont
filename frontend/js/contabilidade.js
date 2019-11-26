$(function () {
  listarEntrada();
})
$('.header-card').click(function () {
  console.log("clicou")
});
function expandCardEvent() {
  const headerCard = $('.header-card');

  headerCard.click(function (e) {
    let bodyCard = $(this).parent().find('.body-card');
    let expandIcon = $(this).find('.expand');

    if (bodyCard.hasClass('d-none')) {
      bodyCard.removeClass('d-none');
      expandIcon.text('expand_less')
    }
    else {
      bodyCard.addClass('d-none');
      expandIcon.text('expand_more')
    }
  })
}

function listarEntrada() {
  const api = ApiLote();
  api.Listar(function (response) {
    let entrada = "";
    let saida = "";
    let saldo = "";
    let media = "";
    response.data.forEach(lote => {
      let totalEntrada = (lote.qtdCompra * lote.produto.valorCompra);
      let htmlEntrada = '<tr>' +
        '<th scope="row">' + lote.data + '</th>' +
        '<td>' + lote.produto.nomeProd + '</td>' +
        '<td>' + lote.qtdCompra + '</td>' +
        '<td>' + lote.produto.valorCompra + '</td>' +
        '<td>' + total + '</td>' +
        '</tr>';

      let totalSaida = (lote.qtdCompra * lote.produto.valorCompra);
      let saida = (lote.qtdCompra - lote.qtdTotal)
      let htmlSaida = '<tr>' +
        '<th scope="row">' + lote.data + '</th>' +
        '<td>' + lote.produto.nomeProd + '</td>' +
        '<td>' + saida + '</td>' +
        '<td>' + lote.produto.valorVenda + '</td>' +
        '<td>' + total + '</td>' +
        '</tr>';

      let totalSaldo = (lote.qtdCompra * lote.produto.valorCompra);
      let htmlSaldo = '<tr>' +
        '<th scope="row">' + lote.data + '</th>' +
        '<td>' + lote.produto.nomeProd + '</td>' +
        '<td>' + lote.qtdCompra + '</td>' +
        '<td>' + lote.produto.valorCompra + '</td>' +
        '<td>' + total + '</td>' +
        '</tr>';
      let totalSaldo = (lote.qtdCompra * lote.produto.valorCompra);
      let htmlSaldo = '<tr>' +
        '<th scope="row">' + lote.data + '</th>' +
        '<td>' + lote.produto.nomeProd + '</td>' +
        '<td>' + lote.qtdCompra + '</td>' +
        '<td>' + lote.produto.valorCompra + '</td>' +
        '<td>' + total + '</td>' +
        '</tr>';

      entrada += htmlEntrada;
      saida += htmlEntrada;
      saldo += htmlEntrada;
      media += htmlEntrada;
    })
    $('#table-body').html(text);
  }, function () { }, function () { }, function (error) {
    const toast = {
      title: 'Erro na listagem de lotes',
      message: 'Há um problema com a aplicação, entre em contato com o suporte.'
    }
    NewToast(toast);
    console.log(error);
  })
  api.Listar()
}