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

	$('#municipioForm').attr('action', '/impacta/municipio/alterar');
	$('#municipioForm').submit();
}

function apagar(id, municipio, uf) {
	apagarDialog(id, municipio, uf);
}

function cancelarNovoMunicipio() {
	cancelarDialog('novo município');
}

function cancelarAlterarMunicipio() {
	cancelarDialog('alterar município');
}

function apagarDialog(id, municipio, uf) {
	$("#dialog-confirm").html('Confirma apagar?');

	// Define the Dialog and its properties.
	$("#dialog-confirm").dialog({
		resizable : false,
		modal : true,
		title : '' + municipio + '/' + uf ,
		height : 250,
		width : 400,
		buttons : {
			"Sim" : function() {
				$(this).dialog('close');

				updateForm(id, municipio, uf);
				$('#municipioForm').attr('action', '/impacta/municipio/apagar');
				$('#municipioForm').submit();
			},
			"Não" : function() {
				$(this).dialog('close');
			}
		}
	});
}

function cancelarDialog(msg) {
	$("#dialog-confirm").html('Confirma cancelar?');

	// Define the Dialog and its properties.
	$("#dialog-confirm").dialog({
		resizable : false,
		modal : true,
		title : 'Cancelar ' + msg,
		height : 250,
		width : 400,
		buttons : {
			"Sim" : function() {
				$(this).dialog('close');

				updateForm("", "", "");
				$('#municipioForm').attr('action', '/impacta/municipio/listar');
				$('#municipioForm').submit();
			},
			"Não" : function() {
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