package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import ucr.lab.HelloApplication;

import java.io.IOException;

public class BaseConverterController
{
    @FXML
    private TextField tf_result;
    @FXML
    private BorderPane bp;
    @FXML
    private TextField tf_decimalValue;
    @FXML
    private RadioButton rbtnO;
    @FXML
    private RadioButton rbtnH;
    @FXML
    private RadioButton rbtnB;

    private void load(String form) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(form));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
    }

    @FXML
    public void rb_binary(ActionEvent actionEvent) {
        this.rbtnB.setSelected(true);
        this.rbtnO.setSelected(false);
        this.rbtnH.setSelected(false);

    }

    @FXML
    public void rb_octal(ActionEvent actionEvent) {
        this.rbtnB.setSelected(false);
        this.rbtnO.setSelected(true);
        this.rbtnH.setSelected(false);
    }

    @FXML
    public void rb_hexa(ActionEvent actionEvent) {
        this.rbtnB.setSelected(false);
        this.rbtnO.setSelected(false);
        this.rbtnH.setSelected(true);
    }

    @FXML
    public void convertOnAction(ActionEvent actionEvent) {
        //El ".trim()" permite que el usuario pueda convertir el numero
        // cual sea que ingrese sin importar si lo ingresa con espacios,
        // este solo tomará el numero entero y eliminará los espacios
        String input = tf_decimalValue.getText().trim();

        // Número aun no ingresado
        if (input.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Información", "No ha ingresado un número.\nIngrese un número para convertir.");
            return;
        }

        //Caracteres no validos
        int decimal;
        try {
            decimal = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Solo se permiten números enteros positivos.");
            return;
        }

        // Base aun no seleccionada
        if (!rbtnB.isSelected() && !rbtnO.isSelected() && !rbtnH.isSelected()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Todavía no ha seleccionado una base para convertir.\nPor favor, seleccione una de las opciones disponibles.");
            return;
        }

        String result;

        if (rbtnB.isSelected()) {
            result = Integer.toBinaryString(decimal);
        } else if (rbtnO.isSelected()) {
            result = Integer.toOctalString(decimal);
        } else {
            result = Integer.toHexString(decimal).toUpperCase();
        }

        tf_result.setText(result);
    }

    @FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        tf_result.setText("");
        tf_decimalValue.setText("");
        this.rbtnB.setSelected(false);
        this.rbtnO.setSelected(false);
        this.rbtnH.setSelected(false);
    }
}