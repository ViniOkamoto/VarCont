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
	api = ApiProduto();
	api.Listar(function (response) {
		let text = "";
		response.data.forEach(prod => {
			let html = '<div class="card-item p-3 mb-3"> ' +
				'<div class="header-card cursor-pointer d-flex justify-content-between align-items-center">' +
				'<h2>' + prod.nomeProd + '</h2>' +
				'<i class="material-icons expand">expand_more</i>' +
				'</div>' +
				'<div class="body-card d-none">' +
				'<div class="d-flex justify-content-between align-items-end">' +
				'<div class="d-flex flex-column mt-3">' +
				'<h3>Custo Entrada:</h3>' +
				'<div class="dataInfo mb-2">' + prod.valorCompra + '</div>' +
				'<h3> Custo Saída: </h3>' +
				'<div class="dataInfo mb-2">' + prod.valorVenda + '</div>' +
				'</div>' +
				'<div class="d-flex">' +
				'<button class="btnEdit d-flex align-items-center" data-id="' + prod.id + '" data-toggle="modal"' +
				'data-target="#data-modal"><i class="material-icons">edit</i></button>' +
				'<button class="btnDel d-flex align-items-center ml-2" data-id="' + prod.id + '"><i class="material-icons">delete</i></button>' +
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
			title: 'Erro na listagem de produtos',
			message: 'Há um problema com a aplicação, entre em contato com o suporte.'
		}
		NewToast(toast);
		console.log(error);
	})
}

$('#data-modal').on('show.bs.modal', function (e) {
	const button = $(e.relatedTarget);
	const modal = $(this);
	$('#id-produto').val(button.data('id'));
	const idProduto = $('#id-produto').val();

	if (idProduto != null) {
		const api = ApiProduto();
		api.Consultar(idProduto, function (response) {
			const produto = response.data;
			modal.find('#produto-nome').val(produto.nomeProd);
			modal.find('#produto-entrada').val(produto.valorCompra);
			modal.find('#produto-saida').val(produto.valorVenda);

		}, function () { }, function () { }, function (error) {
			const toast = {
				title: 'Erro na consulta do produto',
				message: 'Há um problema com a aplicação, entre em contato com o suporte.'
			}
			NewToast(toast);
			console.log(error);
		})
	}
})

$('#data-modal').on('hidden.bs.modal', function (e) {
	$('#id-produto').val('');
	$('#produto-nome').val('');
	$('#produto-entrada').val('');
	$('#produto-saida').val('');
})


$('.btnSave').click(function () {
	if (validateModalFields()) {
		const produto = {
			nomeProd: $('#produto-nome').val(),
			valorCompra: $('#produto-entrada').val(),
			valorVenda: $('#produto-saida').val(),
		}

		const api = ApiProduto();

		if ($('#id-produto').val() != '') {
			produto.id = $('#id-produto').val();
			api.Alterar(produto, function (response) {
				const toast = {
					title: 'Sucesso',
					message: 'produto alterado com êxito.',
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
					title: 'Erro ao alterar produto',
					message: 'Há um problema com a aplicação, entre em contato com o suporte.',
					delay: 4000
				}
				NewToast(toast);
				console.log(error);
			});
		} else {
			api.Adicionar(produto, function (response) {
				const toast = {
					title: 'Sucesso',
					message: 'Produto adicionado com êxito.',
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
					title: 'Erro ao adicionar produto',
					message: 'Há um problema com a aplicação, entre em contato com o suporte.',
					delay: 4000
				}
				NewToast(toast);
				console.log(error);
			})
		}
	}
})

function deleteClickEvent() {
	$('.btnDel').click(function () {
		const id = $(this).data('id');
		const api = ApiProduto();
		api.Excluir(id, function (response) {
			const toast = {
				title: 'Sucesso',
				message: 'Produto excluído com êxito.',
				delay: 4000
			}
			NewToast(toast);
			listModal();
		}, function () { }, function () { }, function (error) {
			const toast = {
				title: 'Erro ao excluir produto',
				message: 'Há um problema com a aplicação, entre em contato com o suporte.'
			}
			NewToast(toast);
			console.log(error);
		})
	})
}

function validateModalFields() {
	let erro = false;
	if ($('#produto-nome').val() == '') {
		const toast = {
			title: 'Campos vazios',
			message: 'O nome é obrigatório.',
			delay: 4000
		}
		NewToast(toast)
		erro = true;
	}

	if ($('#produto-entrada').val() == '') {
		const toast = {
			title: 'Campos vazios',
			message: 'O preço é obrigatório.',
			delay: 4000
		}
		NewToast(toast)
		erro = true;
	}

	if ($('#produto-saida').val() == '') {
		const toast = {
			title: 'Campos vazios',
			message: 'O preço é obrigatório.',
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