$(document).ready(function() {

	$('#buttonCancelarNovoMunicipio').click(function() {
		cancelarNovoMunicipio();
	});

	$('#buttonCancelarAlterarMunicipio').click(function() {
		cancelarAlterarMunicipio();
	});
});

function alterar(id, municipio, uf) {
	updateForm(id, municipio, uf);

	$('#municipioForm').attr('action', urlAlterar);
	$('#municipioForm').submit();
}

function apagar(id, municipio, uf) {
	apagarDialog(id, municipio, uf);
}

function cancelarNovoMunicipio() {
	cancelarDialog(novoMunicipio);
}

function cancelarAlterarMunicipio() {
	cancelarDialog(alterarMunicipio);
}

function apagarDialog(id, municipio, uf) {
	$("#dialog-confirm").html(confirmaApagar);

	// Define the Dialog and its properties.
	$("#dialog-confirm").dialog({
		resizable : false,
		modal : true,
		title : '' + municipio + '/' + uf ,
		height : 250,
		width : 400,
		buttons : {
			Y : function() {
				$(this).dialog('close');

				updateForm(id, municipio, uf);
				$('#municipioForm').attr('action', urlApagar);
				$('#municipioForm').submit();
			},
			N : function() {
				$(this).dialog('close');
			}
		}
	});
}

function cancelarDialog(msg) {
	$("#dialog-confirm").html(confirmaCancelar);

	// Define the Dialog and its properties.
	$("#dialog-confirm").dialog({
		resizable : false,
		modal : true,
		title : msg,
		height : 250,
		width : 400,
		buttons : {
			Y : function() {
				$(this).dialog('close');

				updateForm("", "", "");
				$('#municipioForm').attr('action', urlListar);
				$('#municipioForm').submit();
			},
			N : function() {
				$(this).dialog('close');
			}
		}
	});
}

function updateForm(id, municipio, uf) {
	$('[name=id]').val(id);
	$('[name=municipio]').val(municipio);
	$('[name=uf]').val(uf);
}