package app.controller.impl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.Bundle;
import util.FXHelper;
import app.controller.api.Controller;

abstract class AbstractController implements Controller, Initializable {

    static final String KEY_SOURCE = "dir.source";

    private Stage stage;
    private BorderPane layout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void setLayout(BorderPane layout) {
        this.layout = layout;
    }

    @Override
    public BorderPane getLayout() {
        return layout;
    }

    protected void confirmAndOpenClienteListaView(Event event) {
        FXHelper.confirmAndOpenView(this, Controller.CLIENTE_LISTAR_VIEW, Bundle.Message.Municipio.CANCEL);
    }

    protected void openView(String nextView, Event event) {
        FXHelper.openView(this, nextView);
    }
}
