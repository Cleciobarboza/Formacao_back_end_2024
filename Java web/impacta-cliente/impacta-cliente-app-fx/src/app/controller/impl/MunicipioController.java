package app.controller.impl;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import util.Bundle;
import util.FXHelper;
import app.model.MunicipioTableRow;
import facade.MunicipioFacade;
import facade.UfFacade;
import facade.exception.DomainException;

public class MunicipioController extends AbstractController {

    private UfFacade ufFacade;
    private MunicipioFacade facade;

    @FXML
    private ComboBox<Object> ufCombo;

    @FXML
    private TextField municipioText;

    @FXML
    private TableView<MunicipioTableRow> municipios;

    @FXML
    private TableColumn<MunicipioTableRow, String> municipioColumn;

    @FXML
    private TableColumn<MunicipioTableRow, String> ufColumn;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelarButton;

    @Override
    public String getTitle() { return Bundle.Key.Municipio.TITLE; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initFacades(resources);
            initComponents();
            initActions();
            load();
        } catch (Exception cause) {
            FXHelper.exception(cause);
        }
    }

    private void initActions() {
        ufCombo.setOnAction(this::list);
        okButton.setOnAction(this::save);
        cancelarButton.setOnAction(super::confirmAndOpenClienteListaView);
    }

    private void initComponents() {
        ufCombo.setItems(FXHelper.obervableArrayList(ufFacade.listarUFs()));
        ufCombo.getSelectionModel().select(0);

        municipioColumn.setCellValueFactory(data -> data.getValue().getMunicipio());
        ufColumn.setCellValueFactory(data -> data.getValue().getUf());

        ContextMenu cm = new ContextMenu();

        cm.getItems().addAll(new MenuItem("Atualizar"), new MenuItem("Apagar"), new SeparatorMenuItem(), new MenuItem("Selecionar todos"));

        municipios.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            System.out.println("ok");
            if (e.isSecondaryButtonDown()) {
                cm.show(municipios, e.getScreenX(), e.getScreenY());
            }
        });
    }

    private void initFacades(ResourceBundle resources) throws Exception {
        ufFacade = new UfFacade();
        facade = new MunicipioFacade(resources.getString(KEY_SOURCE));
    }

    private void clearForm(ActionEvent event) {
        municipioText.clear();
    }

    @FXML
	private void list(ActionEvent event) {
    	load();
	}

    private void load() {
	    Object uf;
	    Collection<Object[]> municipios;
	    
	    try {
    	    uf = ufCombo.getValue();
    	    municipios = facade.listar(uf);
    	    this.municipios.getItems().clear();

    	    for (Object[] row : municipios) {
    	        this.municipios.getItems().add(new MunicipioTableRow(row));
    	    }
	    } catch (Exception cause) {
	        FXHelper.exception(cause);
	    }
    }

    private void save(ActionEvent event) {
        try {
            facade.validar(municipioText.getText(), ufCombo.getValue().toString());
            facade.salvar(municipioText.getText(), ufCombo.getValue().toString());
            list(event);
            clearForm(event);
        } catch (DomainException cause) {
            FXHelper.alert(cause);
        } catch (Exception cause) {
            FXHelper.exception(cause);
        }
    }

    @FXML
    private void delete(ActionEvent event) {

    }
}
