$(function () {
	const api = ApiProduto();
	api.Listar(function (response) {
		let html = '<option value="default">Selecionar produto</option>'
		response.data.forEach(x => {
			let text = '<option value="' + x.id + '">' + x.nomeProd + '</option>'
			html += text;
		});
		$('#products').html(html)
	}, function () { }, function () { }, function (error) {
		const toast = {
			title: 'Erro na listagem dos produtos',
			message: 'Há um problema com a aplicação, entre em contato com o suporte.',
			delay: 3000
		}
		NewToast(toast);
	})
})

$('#products').change(function () {
	const idProd = $(this).val();
	if (idProd != 'default') {
		fillTable(idProd);
	}
})

function fillTable(idProd) {
	const api = ApiTabela();
	api.Listar(idProd, function (response) {
		let html = '', saldo = 0, qtd = 0, entradaSoma = 0, saidaSoma = 0, saldoSoma = 0;
		response.data.forEach(x => {
			console.log(x)
			let text;
			if (x.tipo == false) {
				saldo += x.qtd;
				entradaSoma += x.qtd * x.produto.valorCompra;
				text = '<tr>' +
					'<th scope="row">' + x.data + '</th>' +
					'<td>' + x.qtd + '</td>' +
					'<td>' + x.produto.valorCompra + '</td>' +
					'<td>' + (x.qtd * x.produto.valorCompra) + '</td>' +
					'<td></td>' +
					'<td></td>' +
					'<td></td>' +
					'<td>' + saldo + '</td>' +
					'<td>' + x.produto.valorCompra + '</td>' +
					'<td>' + x.produto.valorCompra * saldo + '</td>' +
					'</tr>';
			} else {
				saldo -= x.qtd;
				saidaSoma += x.qtd * x.produto.valorCompra;
				text = '<tr>' +
					'<th scope="row">' + x.data + '</th>' +
					'<td></td>' +
					'<td></td>' +
					'<td></td>' +
					'<td>' + x.qtd + '</td>' +
					'<td>' + x.produto.valorCompra + '</td>' +
					'<td>' + (x.qtd * x.produto.valorCompra) + '</td>' +
					'<td>' + saldo + '</td>' +
					'<td>' + x.produto.valorCompra + '</td>' +
					'<td>' + x.produto.valorCompra * saldo + '</td>' +
					'</tr>';
			}
			saldoSoma = x.produto.valorCompra * saldo;
			html += text;
		});
		const htmlFinal = '<tr>' +
			'<td colspan="3">' + entradaSoma + '</td>' +
			'<td colspan="3">' + saidaSoma + '</td>' +
			'<td colspan="3">' + saldoSoma + '</td>' +
			'</tr>';
		$('.table-body').html(html);
		$('.final-table-body').html(htmlFinal);
	}, function () { }, function () { }, function (error) {
		const toast = {
			title: 'Erro na listagem da tabela',
			message: 'Há um problema com a aplicação, entre em contato com o suporte.',
			delay: 3000
		}
		NewToast(toast);
	})
}