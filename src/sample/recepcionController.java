package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class recepcionController implements Initializable {
    @FXML
    Button BtnReportes, BtnConsultas, BtnGuardar, BtnEliminar, BtnNuevo;
    @FXML
    TextField txtFolio, txtNoDoc, txtQuienRecibe,txtareaayuntamiento,
            txtfirma, txtpuesto, txtdirigido, txtasunto, txtobservaciones;
    @FXML
    DatePicker dpFechaDoc, dpFechaRecep, dpfechalimite, dpfechaentrega;
    @FXML
    ComboBox<String> cmbTipo, cmbFormato, cmbinstruccion, cmbprioridad, cmbinstitucion;
    @FXML
    CheckBox checkentregado;





    public void showStage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("recepcion.fxml"));
        Stage st= new Stage();
        st.setTitle("RECEPCION");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbTipo.getItems().add(0, "Oficio");
        cmbTipo.getItems().add(1, "Tarjeta Informativa");
        cmbTipo.getItems().add(2, "Escrito personal");

        cmbFormato.getItems().add(0,"Original");
        cmbFormato.getItems().add(1, "Copia");





    }
}


