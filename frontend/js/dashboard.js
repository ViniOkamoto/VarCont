$(function () {
  carregarBarras();
})

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
function carregarBarras() {
  const api = ApiLote();
  api.Estoque(function (response) {
    const estoques = response.data;
    let text = "";
    estoques.forEach(estoque => {
      console.log(estoque);
      var id = estoque[0];
      var nome = estoque[1];
      var qtdTotal = estoque[2];
      var qtdCompra = estoque[3];
      var porcentagem = (qtdTotal / qtdCompra * 100).toFixed(0);
      console.log(id, nome, qtdTotal, qtdCompra);
      if (porcentagem <= 50 && porcentagem > 30) {
        let html = '<div class="container mt-3">' +
          '<div class="header-card cursor-pointer d-flex justify-content-between align-items-center" id="header-estoque">' +
          '<h2> Estoque ' + nome + '</h2>' +
          '<i class="material-icons expand"> expand_more</i>' +
          '</div>' +
          '<div class="progress mt-1">' +
          '<div class="progress-bar bg-warning" role="progressbar" align="center" aria-valuenow="' + porcentagem + '50" aria-valuemin="0" aria-valuemax="100" style="width:' + porcentagem + '%">' +
          porcentagem + "%" + '</div>' +
          '</div>' +
          '<div class="body-card d-none mt-3" id="body-estoque">' +
          '<h3> Quantidade Total do Estoque: </h3>' +
          '<div class="dataInfo mb-2">' + qtdCompra + '</div>' +
          '<h3>Quantidade Atual em Estoque:</h3>' +
          '<div class="dataInfo mb-2">' + qtdTotal + '</div>' +
          '</div>' +
          '</div>';
        text += html;
      } else if (porcentagem <= 30) {
        let html = '<div class="container mt-3">' +
          '<div class="header-card cursor-pointer d-flex justify-content-between align-items-center" id="header-estoque">' +
          '<h2> Estoque ' + nome + '</h2>' +
          '<i class="material-icons expand"> expand_more</i>' +
          '</div>' +
          '<div class="progress mt-1">' +
          '<div class="progress-bar bg-danger" role="progressbar" align="center" aria-valuenow="' + porcentagem + '50" aria-valuemin="0" aria-valuemax="100" style="width:' + porcentagem + '%">' +
          porcentagem + "%" + '</div>' +
          '</div>' +
          '<div class="body-card d-none mt-3" id="body-estoque">' +
          '<h3> Quantidade Total do Estoque: </h3>' +
          '<div class="dataInfo mb-2">' + qtdCompra + '</div>' +
          '<h3>Quantidade Atual em Estoque:</h3>' +
          '<div class="dataInfo mb-2">' + qtdTotal + '</div>' +
          '</div>' +
          '</div>';
        text += html;
      } else {
        let html = '<div class="container mt-3">' +
          '<div class="header-card cursor-pointer d-flex justify-content-between align-items-center" id="header-estoque">' +
          '<h2> Estoque ' + nome + '</h2>' +
          '<i class="material-icons expand"> expand_more</i>' +
          '</div>' +
          '<div class="progress mt-1">' +
          '<div class="progress-bar bg-success" role="progressbar" align="center" aria-valuenow="' + porcentagem + '" aria-valuemin="0" aria-valuemax="100" style="width:' + porcentagem + '%">' +
          porcentagem + "%" + '</div>' +
          '</div>' +
          '<div class="body-card d-none mt-3" id="body-estoque">' +
          '<h3> Quantidade Total do Estoque: </h3>' +
          '<div class="dataInfo mb-2">' + qtdCompra + '</div>' +
          '<h3>Quantidade Atual em Estoque:</h3>' +
          '<div class="dataInfo mb-2">' + qtdTotal + '</div>' +
          '</div>' +
          '</div>';
        text += html;
      }
    })
    $('.list-estoque').html(text);
    expandCardEvent();
  }, function () { }, function () { }, function (error) {
    const toast = {
      title: 'Erro na consulta do produto',
      message: 'Há um problema com a aplicação, entre em contato com o suporte.'
    }
    NewToast(toast);
    console.log(error);
  })
}