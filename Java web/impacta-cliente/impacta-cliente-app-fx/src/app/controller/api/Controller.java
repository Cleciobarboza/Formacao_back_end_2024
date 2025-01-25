package app.controller.api;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public interface Controller {

	String VIEW_PATH_FORMAT = "/app/view/%sView.fxml";
	String CLIENTE_LISTAR_VIEW = String.format(VIEW_PATH_FORMAT, "ClienteLista");
	String CLIENTE_VIEW = String.format(VIEW_PATH_FORMAT, "Cliente");
	String LOGRADOURO_VIEW = String.format(VIEW_PATH_FORMAT, "Logradouro");
	String BAIRRO_VIEW = String.format(VIEW_PATH_FORMAT, "Bairro");
	String MUNICIPIO_VIEW = String.format(VIEW_PATH_FORMAT, "Municipio");
	String IMPACTA_VIEW = String.format(VIEW_PATH_FORMAT, "Impacta");

	String getTitle();

	Stage getStage();

	void setStage(Stage stage);

	BorderPane getLayout();

	void setLayout(BorderPane layout);
}
