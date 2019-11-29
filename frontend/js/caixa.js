$(function () {
	carregarBarras();
})

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
					'<h2> Estoque de ' + nome + '</h2>' +
					'<div class="progress mt-1">' +
					'<div class="progress-bar bg-warning" role="progressbar" align="center" aria-valuenow="' + porcentagem + '50" aria-valuemin="0" aria-valuemax="100" style="width:' + porcentagem + '%">' +
					porcentagem + "%" + '</div>' +
					'</div>' +
					'</div>';
				text += html;
			} else if (porcentagem <= 30) {
				let html = '<div class="container mt-3">' +
					'<h2> Estoque de ' + nome + '</h2>' +
					'<div class="progress mt-1">' +
					'<div class="progress-bar bg-danger" role="progressbar" align="center" aria-valuenow="' + porcentagem + '50" aria-valuemin="0" aria-valuemax="100" style="width:' + porcentagem + '%">' +
					porcentagem + "%" + '</div>' +
					'</div>' +
					'</div>';
				text += html;
			} else {
				let html = '<div class="container mt-3">' +
					'<h2> Estoque de ' + nome + '</h2>' +
					'<div class="progress mt-1">' +
					'<div class="progress-bar bg-success" role="progressbar" align="center" aria-valuenow="' + porcentagem + '" aria-valuemin="0" aria-valuemax="100" style="width:' + porcentagem + '%">' +
					porcentagem + "%" + '</div>' +
					'</div>' +
					'</div>';
				text += html;
			}
		})
		$('.list-estoque').html(text);
	}, function () { }, function () { }, function (error) {
		const toast = {
			title: 'Erro na consulta do produto',
			message: 'Há um problema com a aplicação, entre em contato com o suporte.'
		}
		NewToast(toast);
		console.log(error);
	})
}
$("#produto-select").change(function () {
	if ($(this).val() != 'default') {
		var value = $(this).val();
		$('.close').trigger('click');
		const apiProduto = ApiProduto();
		apiProduto.Consultar(value, function (response) {
			const produto = response.data;
			$('#produto-custo').val(produto.valorVenda);
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
					$('#estoque-quantidade').val(quantidade);
					$('.custom-range').attr('max', quantidade);
					$('.custom-range').val(0);
					fillDinamicFields();
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
	} else {
		clearFields();
	}
})

$('.custom-range').change(function () {
	fillDinamicFields();
})

function fillDinamicFields() {
	$('#range-value').val($('.custom-range').val());
	$('#total-value').val(parseFloat($('#range-value').val()) * parseFloat($('#produto-custo').val()));
}

$('#btnSale').click(function (e) {
	e.preventDefault();
	if (validateModalFields()) {
		const api = ApiVenda();
		let sale = {
			idUsuario: $('#usuario-select').val(),
			idProduto: $('#produto-select').val(),
			qtdVenda: $('#range-value').val()
		};
		api.Adicionar(sale, function (response) {
			carregarBarras();
			const toast = {
				title: 'Sucesso',
				message: 'Venda efetuada com êxito.',
				delay: 4000
			}
			NewToast(toast);
			clearFields();
		}, function () {
			$('#btnSale').text('Carregando...');
		}, function () {
			$('#btnSale').text('Realizar venda');
		}, function (error) {
			const toast = {
				title: 'Erro ao efetuar venda',
				message: 'Há um problema com a aplicação, entre em contato com o suporte.',
				delay: 4000
			}
			NewToast(toast);
			console.log(error);
		})
	}
})

$("#venda-caixa").ready(function () {
	const apiProduto = ApiProduto();
	carregarSelect("produto");
	function carregarSelect(id) {
		apiProduto.Listar(function (response) {
			let text = '<option value="default">Selecionar ' + id + '</option>'
			const produto = response.data
			produto.forEach(prod => {
				let html = '<option value= ' + prod.id + ' >' + prod.nomeProd + '</option>'
				text += html
			})
			$("#produto-select").html(text)
		}, function () { }, function () { }, function (error) {
			const toast = {
				title: 'Erro na listagem de produtos',
				message: 'Há um problema com a aplicação, entre em contato com o suporte.',
				delay: 4000
			}
			NewToast(toast);
			console.log(error);
		})
	}

	const apiUsuario = ApiUsuario();
	apiUsuario.Listar(function (response) {
		let text = '<option value="default">Selecionar usuário</option>';
		const users = response.data;
		users.forEach(user => {
			let html = '<option value= ' + user.id + ' >' + user.nome + '</option>';
			text += html;
		})
		$("#usuario-select").html(text)
	}, function () { }, function () { }, function (error) {
		const toast = {
			title: 'Erro na listagem de usuários',
			message: 'Há um problema com a aplicação, entre em contato com o suporte.',
			delay: 4000
		}
		NewToast(toast);
		console.log(error);
	})
})

function validateModalFields() {
	let erro = false;
	if ($('#usuario-select').val() == 'default') {
		const toast = {
			title: 'Campos vazios',
			message: 'O usuário é obrigatório.',
			delay: 4000
		}
		NewToast(toast)
		erro = true;
	}
	if ($('#produto-select').val() == 'default') {
		const toast = {
			title: 'Campos vazios',
			message: 'O produto é obrigatório.',
			delay: 4000
		}
		NewToast(toast)
		erro = true;
	}

	if ($('#range-value').val() == '' || $('#range-value').val() == '0') {
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

function clearFields() {
	$('#produto-select').val('default');
	$('#produto-custo').val('');
	$('#estoque-quantidade').val('');
	$('.custom-range').val(0);
	$('#total-value').val(0);
}