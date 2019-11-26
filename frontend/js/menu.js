$(function () {
    if ($('#menu').length == 0) {
        $('body').prepend(
            '<div id="menu" class="pt-3 pr-3 d-none">' +
            '<div class="p-4">' +
            '	<a href="dashboard.html" class="d-flex align-items-center">' +
            '		<i class="material-icons mr-3">dashboard</i>' +
            '       Dashboard' +
            '   </a>' +
            '</div>' +
            '<div class="p-4">' +
            '	<a href="contabilidade.html" class="d-flex align-items-center">' +
            '		<i class="material-icons mr-3">trending_up</i>' +
            '		Contabilidade e Controle' +
            '	</a>' +
            '</div>' +
            '<div class="p-4">' +
            '	<a href="usuarios.html" class="d-flex align-items-center">' +
            '		<i class="material-icons mr-3">supervisor_account</i>' +
            '		Usuários' +
            '	</a>' +
            '</div>' +
            '<div class="p-4">' +
            '	<a href="produtos.html" class="d-flex align-items-center">' +
            '		<i class="material-icons mr-3">store</i>' +
            '		Produtos' +
            '	</a>' +
            '</div>' +
            '<div class="p-4">' +
            '	<a href="venda.html" class="d-flex align-items-center">' +
            '		<i class="material-icons mr-3">shopping_cart</i>' +
            '		Vendas' +
            '	</a>' +
            '</div>' +
            '<div class="p-4">' +
            '	<a href="lotes.html" class="d-flex align-items-center">' +
            '		<i class="material-icons mr-3">layers</i>' +
            '		Lotes' +
            '	</a>' +
            '</div>' +
            '<div class="p-4">' +
            '	<a href="login.html" class="d-flex align-items-center">' +
            '		<i class="material-icons mr-3">exit_to_app</i>' +
            '		Sair' +
            '	</a>' +
            '</div>' +
            '</div>'
        )         
    }

    $('#menu').css('height', $(document).height());
    const url = window.location.href;
    switch (url) {
        case 'http://127.0.0.1:5500/usuarios.html':
            $('a[href="usuarios.html"]').parent().addClass('only');
            break;
        case 'http://127.0.0.1:5500/lotes.html':
            $('a[href="lotes.html"]').parent().addClass('only');
            break;
        case 'http://127.0.0.1:5500/venda.html':
            $('a[href="venda.html"]').parent().addClass('only');
            break;
        case 'http://127.0.0.1:5500/produtos.html':
            $('a[href="produtos.html"]').parent().addClass('only');
            break;
        case 'http://127.0.0.1:5500/dashboard.html':
            $('a[href="dashboard.html"]').parent().addClass('only');
            break;
        case 'http://127.0.0.1:5500/contabilidade.html':
            $('a[href="contabilidade.html"]').parent().addClass('only');
            break;
        case 'http://127.0.0.1:5500/caixa.html':
            $('a[href="caixa.html"]').parent().addClass('only');
            break;

        default:
            break;
    }

    $('header i:first').click(function () {
        $('#menu').removeClass('d-none');
    })

    $('#menu').mouseleave(function(){
        setTimeout(function(){ $('#menu').addClass('d-none')}, 500)
    });
})


