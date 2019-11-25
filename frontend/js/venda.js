$(function () {
  listModal();
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

function listModal() {
  api = ApiVenda();
  api.Listar(function (response) {
    let text = "";
    response.data.forEach(venda => {
      console.log(venda)
      let calculo = (venda.qtdVenda * venda.produto.valorVenda)
      let html = '<div class="card-item p-3 mb-3">' +
        '<div class="header-card cursor-pointer d-flex justify-content-between align-items-center">' +
        '<h2>Venda de ' + venda.usuario.nome + '</h2>' +
        '<i class="material-icons expand">expand_more</i>' +
        '</div>' +
        '<div class="body-card d-none">' +
        '<div class="d-flex justify-content-between align-items-end">' +
        '<div class="d-flex flex-column mt-3">' +
        '<h3> Data de criação:</h3>' +
        '<div class="dataInfo mb-2">' + venda.data + '</div>' +
        '<h3>Produto vendido:</h3>' +
        '<div class="dataInfo mb-2">' + venda.produto.nomeProd + '</div>' +
        '<h3>Custo unitário:</h3>' +
        '<div class="dataInfo mb-2">' + venda.produto.valorVenda + '</div>' +
        '<h3> Quantidade vendida:</h3>' +
        '<div class="dataInfo mb-2">' + venda.qtdVenda + '</div>' +
        '<h3>Total R$</h3>' +
        '<div class="dataInfo mb-2">' + calculo + '</div>' +
        '</div>' +
        '<div class="d-flex">' +
        '<button class="btnDel d-flex align-items-center ml-2" data-id="' + venda.id + '">' +
        '<i class="material-icons">delete</i></button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';

      text += html;
    })
    $('.list-data').html(text);
    expandCardEvent();
    deleteClickEvent();
  }, function () { }, function () { }, function (error) {
    const toast = {
      title: 'Erro na listagem de lotes',
      message: 'Há um problema com a aplicação, entre em contato com o suporte.'
    }
    NewToast(toast);
    console.log(error);
  })
}

function deleteClickEvent() {
  $('.btnDel').click(function () {
    const id = $(this).data('id');
    const api = ApiVenda();
    api.Excluir(id, function (response) {
      const toast = {
        title: 'Sucesso',
        message: 'Lote excluído com êxito.',
        delay: 4000
      }
      NewToast(toast);
      listModal();
    }, function () { }, function () { }, function (error) {
      const toast = {
        title: 'Erro ao excluir lote',
        message: 'Há um problema com a aplicação, entre em contato com o suporte.'
      }
      NewToast(toast);
      console.log(error);
    })
  })
}