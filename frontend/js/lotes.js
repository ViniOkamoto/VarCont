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

$('#data-modal').ready(function () {
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
});

function listModal() {
	api = ApiLote();
	api.Listar(function (response) {
		let text = "";
		response.data.forEach(lote => {
			let html = '<div class="card-item p-3 mb-3">' +
				'<div class="header-card cursor-pointer d-flex justify-content-between align-items-center">' +
				'<h2>Lote de ' + lote.produto.nomeProd + '</h2>' +
				'<i class="material-icons expand"> expand_more</i>' +
				'</div>' +
				'<div class="body-card d-none">' +
				'<div class="d-flex justify-content-between align-items-end">' +
				'<div class="d-flex flex-column mt-3">' +
				'<h3> Data de criação:</h3>' +
				'<div class="dataInfo mb-2">' + lote.data + '</div>' +
				'<h3>Quantidade Adquirida:</h3>' +
				'<div class="dataInfo mb-2">' + lote.qtdCompra + '</div>' +
				'<h3> Quantidade do lote: </h3>' +
				'<div class="dataInfo mb-2">' + lote.qtdTotal + '</div>' +
				'</div>' +
				'<div class="d-flex">' +
				'<button class="btnEdit d-flex align-items-center" data-id="' + lote.id + '" data-toggle="modal"' +
				'data-target="#data-modal"><i class="material-icons">edit</i></button>' +
				'<button class="btnDel d-flex align-items-center ml-2" data-id="' + lote.id + '"><i class="material-icons">delete</i></button>' +
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
$('#data-modal').on('show.bs.modal', function (e) {
<<<<<<< HEAD
  const button = $(e.relatedTarget);
  const modal = $(this);
  $('#id-lote').val(button.data('id'));
  const idLote = $('#id-lote').val();

  if (idLote != "") {
    const api = ApiLote();
    api.Consultar(idLote, function (response) {
      const lote = response.data;
      modal.find('#produto-select').val(lote.produto.id);
      modal.find('#lote-quantidade').val(lote.qtdCompra);

    }, function () { }, function () { }, function (error) {
      const toast = {
        title: 'Erro na consulta do lote',
        message: 'Há um problema com a aplicação, entre em contato com o suporte.'
      }
      NewToast(toast);
      console.log(error);
    })
  }
=======
	const button = $(e.relatedTarget);
	const modal = $(this);
	$('#id-lote').val(button.data('id'));
	const idLote = $('#id-lote').val();

	if (idLote != "") {
		const api = ApiLote();
		api.Consultar(idLote, function (response) {
			const lote = response.data;
			modal.find('#produto-select').val(lote.produto.id);
			modal.find('#lote-quantidade').val(lote.valorCompra);

		}, function () { }, function () { }, function (error) {
			const toast = {
				title: 'Erro na consulta do lote',
				message: 'Há um problema com a aplicação, entre em contato com o suporte.'
			}
			NewToast(toast);
			console.log(error);
		})
	}
>>>>>>> d2e5e5c0c17e96d27ee482861736b38e1c9613df
})

$('#data-modal').on('hidden.bs.modal', function (e) {
	$('#id-lote').val('');
	$('#produto-select').val('');
	$('#lote-quantidade').val('');
})

$('.btnSave').click(function () {
<<<<<<< HEAD
  if (validateModalFields()) {
    const lote = {
      idProduto: $('#produto-select').val(),
      qtdCompra: $('#lote-quantidade').val(),
    }

    const api = ApiLote();

    if ($('#id-lote').val() != '') {
      lote.id = $('#id-lote').val();
      api.Alterar(lote, function (response) {
        const toast = {
          title: 'Sucesso',
          message: 'Lote alterado com êxito.',
          delay: 4000
        }
        NewToast(toast);
        $('#data-modal').modal('hide');
        listModal();
      }, function () {
        $('.btnSave').text('Carregando...');
      }, function () {
        $('.btnSave').text('Salvar');
      }, function (error) {
        const toast = {
          title: 'Erro ao alterar lote',
          message: 'Há um problema com a aplicação, entre em contato com o suporte.',
          delay: 4000
        }
        NewToast(toast);
        console.log(error);
      });
    } else {
      api.Adicionar(lote, function (response) {
        const toast = {
          title: 'Sucesso',
          message: 'Lote adicionado com êxito.',
          delay: 4000
        }
        NewToast(toast);
        $('#data-modal').modal('hide');
        listModal();
      }, function () {
        $('.btnSave').text('Carregando...');
      }, function () {
        $('.btnSave').text('Salvar');
      }, function (error) {
        const toast = {
          title: 'Erro ao adicionar lote',
          message: 'Há um problema com a aplicação, entre em contato com o suporte.',
          delay: 4000
        }
        NewToast(toast);
        console.log(error);
      })
    }
  }
=======
	if (validateModalFields()) {
		const lote = {
			idProduto: $('#produto-select').val(),
			qtdCompra: $('#lote-quantidade').val(),
		}

		const api = ApiLote();

		if ($('#id-lote').val() != '') {
			lote.id = $('#id-lote').val();
			api.Alterar(lote, function (response) {
				const toast = {
					title: 'Sucesso',
					message: 'lote alterado com êxito.',
					delay: 4000
				}
				NewToast(toast);
				$('#data-modal').modal('hide');
				listModal();
			}, function () {
				$('.btnSave').text('Carregando...');
			}, function () {
				$('.btnSave').text('Salvar');
			}, function (error) {
				const toast = {
					title: 'Erro ao alterar lote',
					message: 'Há um problema com a aplicação, entre em contato com o suporte.',
					delay: 4000
				}
				NewToast(toast);
				console.log(error);
			});
		} else {
			api.Adicionar(lote, function (response) {
				const toast = {
					title: 'Sucesso',
					message: 'lote adicionado com êxito.',
					delay: 4000
				}
				NewToast(toast);
				$('#data-modal').modal('hide');
				listModal();
			}, function () {
				$('.btnSave').text('Carregando...');
			}, function () {
				$('.btnSave').text('Salvar');
			}, function (error) {
				const toast = {
					title: 'Erro ao adicionar lote',
					message: 'Há um problema com a aplicação, entre em contato com o suporte.',
					delay: 4000
				}
				NewToast(toast);
				console.log(error);
			})
		}
	}
>>>>>>> d2e5e5c0c17e96d27ee482861736b38e1c9613df
})

function deleteClickEvent() {
	$('.btnDel').click(function () {
		const id = $(this).data('id');
		const api = ApiLote();
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
				message: 'Há um problema com a aplicação, entre em contato com o suporte.',
				delay: 4000
			}
			NewToast(toast);
			console.log(error);
		})
	})
}

function validateModalFields() {
	let erro = false;
	if ($('#produto-select').val() == '') {
		const toast = {
			title: 'Campo vazios',
			message: 'O produto é obrigatório.',
			delay: 4000
		}
		NewToast(toast)
		erro = true;
	}

	if ($('#lote-quantidade').val() == '') {
		const toast = {
			title: 'Campos vazios',
			message: 'A quantidade é obrigatória.',
			delay: 4000
		}
		NewToast(toast)
		erro = true;
	}

	if (erro)
		return false;
	else
		return true;
}