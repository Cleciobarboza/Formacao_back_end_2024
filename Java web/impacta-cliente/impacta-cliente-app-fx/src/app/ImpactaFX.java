package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.FXHelper;
import util.FXMLHelper;
import app.controller.api.Controller;

public class ImpactaFX extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxml = FXMLHelper.newFXMLLoader(Controller.IMPACTA_VIEW);
		Scene s = new Scene(fxml.load());
		Controller controller = fxml.<Controller> getController();		

		controller.setStage(stage);
		controller.setLayout((BorderPane) s.getRoot());
		
		fxml = FXMLHelper.newFXMLLoader(Controller.CLIENTE_LISTAR_VIEW);
		controller.getLayout().setCenter(fxml.load());

		stage.setTitle(FXHelper.getString(controller.getTitle()));
		stage.setScene(s);
		stage.setOnCloseRequest(we -> FXHelper.close(stage, we));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
