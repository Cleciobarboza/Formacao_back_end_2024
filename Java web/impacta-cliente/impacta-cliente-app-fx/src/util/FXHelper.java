package util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Optional;

import app.controller.api.Controller;
import facade.exception.DomainException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public final class FXHelper {

    private FXHelper() {
        super();
    }

    public static String getString(String key) {
        return FXMLHelper.DEFAULT_BUNDLE.getString(key);
    }

    public static boolean confirmExit() {
        return confirm(Bundle.Message.EXIT);
    }

    public static boolean confirm(String headerText) {
        return confirm(headerText, null);
    }

    public static boolean confirm(String headerText, String contentText) {
        return confirm(getString(Bundle.Key.TITLE), headerText, contentText);
    }

    public static boolean confirm(String title, String headerText, String contentText) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(getString(headerText));
        if (contentText != null) {
            alert.setContentText(contentText);
        }

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public static void alert(DomainException cause) {
        alert(Bundle.Validation.FAIL, cause);
    }

    public static void alert(String headerText, Throwable cause) {
        cause = cause.getCause();
        alert(headerText, cause.getMessage());
    }

    public static void alert(String headerText, String contentText) {
        alert(Bundle.Key.TITLE, headerText, contentText);
    }

    public static void alert(String title, String headerText, String contentText) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(getString(title));
        alert.setHeaderText(getString(headerText));
        alert.setContentText(getString(contentText));

        alert.showAndWait();
    }

    public static void exception(Throwable cause) {
        cause.printStackTrace();
        exception(Bundle.Error.HEADER_TEXT, cause);
    }

    public static void exception(String headerText, Throwable cause) {
        exception(headerText, cause.getMessage(), cause);
    }

    public static void exception(String headerText, String contentText, Throwable cause) {
        exception(getString(Bundle.Key.TITLE), headerText, contentText, cause);
    }

    public static void exception(String title, String headerText, String contentText, Throwable cause) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(getString(headerText));
        alert.setContentText(getString(contentText));

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        cause.printStackTrace(pw);

        Label label = new Label("Stacktrace:");

        TextArea textArea = new TextArea(sw.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }

    public static ObservableList<Object> obervableArrayList(Collection<?> collection) {
        return FXCollections.observableArrayList(collection);
    }

    public static void confirmAndOpenView(Controller actualController, String nextView) {
        confirmAndOpenView(actualController, nextView, Bundle.Message.CANCEL);
    }

    public static void confirmAndOpenView(Controller actualController, String nextView, String bundleMessage) {
        if (confirm(bundleMessage)) {
            openView(actualController, nextView);
        }
    }

    public static void openView(Controller actualController, String nextView) {
        FXMLLoader fxml;
        Controller controller;
        Parent view;

        try {
            fxml = FXMLHelper.newFXMLLoader(nextView);
            view = fxml.load();
            controller = fxml.getController();
            controller.setStage(actualController.getStage());
            controller.setLayout(actualController.getLayout());
            controller.getLayout().setCenter(view);
            controller.getStage().setTitle(getString(controller.getTitle()));
        } catch (Exception cause) {
            FXHelper.exception(cause);
        }
    }

    public static void close(Stage stage) {
        close(stage, null);
    }

    public static void close(Stage stage, Event event) {
        if (confirmExit()) {
            stage.close();
        } else {
            event.consume();
        }
    }
}
