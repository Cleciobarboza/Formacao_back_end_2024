package app.controller.impl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import util.Bundle;
import util.FXHelper;

public class ImpactaController extends AbstractController {

	@Override
	public String getTitle() { return Bundle.Key.Cliente.TITLE; }

	@FXML
	private void clickClientes(ActionEvent event) {
		openView(CLIENTE_LISTAR_VIEW, event);
	}

	@FXML
	private void clickClienteNovo(ActionEvent event) {
		openView(CLIENTE_VIEW, event);
	}

	@FXML
	private void clickLogradouros(ActionEvent event) {
		openView(LOGRADOURO_VIEW, event);
	}

	@FXML
	private void clickBairros(ActionEvent event) {
		openView(BAIRRO_VIEW, event);
	}

	@FXML
	private void clickMunicipios(ActionEvent event) {
		openView(MUNICIPIO_VIEW, event);
	}

	@FXML
	private void clickClose(ActionEvent event) {
	    FXHelper.close(getStage(), event);
	}
}
