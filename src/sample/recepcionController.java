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

        cmbprioridad.getItems().add(0, "Ordinario" );
        cmbprioridad.getItems().add(1, "Urgente" );
        cmbprioridad.getItems().add(2, "Inmediata" );

        cmbinstitucion.getItems().add(0,"Obras publicas y servicios");
        cmbinstitucion.getItems().add(1,"Desarrollo Urbano");
        cmbinstitucion.getItems().add(2,"Desarrollo Rural");
        cmbinstitucion.getItems().add(3,"Recursos Humanos");
        cmbinstitucion.getItems().add(4,"Servicio Civil");
        cmbinstitucion.getItems().add(5,"Educaion");
        cmbinstitucion.getItems().add(6,"Agua Potable y Alcantarillado");
        cmbinstitucion.getItems().add(7,"Igualdad de Género");
        cmbinstitucion.getItems().add(8,"Contraloria");
        cmbinstitucion.getItems().add(9,"Salud y Asistencia Social");
        cmbinstitucion.getItems().add(11,"Desarrollo Economico y Turismo");
        cmbinstitucion.getItems().add(12,"Cultura y Deporte");
        cmbinstitucion.getItems().add(12,"Tesoreria");
        cmbinstitucion.getItems().add(14,"Oficialia Mayor");
        cmbinstitucion.getItems().add(15,"Juridico");
        cmbinstitucion.getItems().add(16,"Seguridad Publica");
        cmbinstitucion.getItems().add(17,"Relaciones Exteriores");
        cmbinstitucion.getItems().add(18,"Desarrollo Social");

        cmbinstruccion.getItems().add(0,"Para su Atencion Porcedente");
        cmbinstruccion.getItems().add(1,"Dar Respuesta al Interesado");
        cmbinstruccion.getItems().add(2,"Para su Conocimiento");
        cmbinstruccion.getItems().add(3,"Para su Difusion");
        cmbinstruccion.getItems().add(4,"Atencion y Seguimiento");








    }
}


