package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class recepcionController implements Initializable {
    @FXML
    Button BtnReportes, BtnConsultas, BtnGuardar, BtnEliminar, BtnNuevo;
    @FXML
    TextField txtFolio, txtNoDoc;
    @FXML
    DatePicker dpFechaDoc, dpFechaRecep;
    @FXML
    ComboBox<String> cmbTipo, cmbFormato;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}


