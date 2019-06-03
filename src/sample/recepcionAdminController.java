package sample;

import Recepcion.Destinatario.Destinatario;
import Recepcion.Destinatario.DestinatarioDAO;
import Recepcion.Destinatario.Instruccion;
import Recepcion.Destinatario.Prioridad;
import Recepcion.Documento.DocumentoDAO;
import Recepcion.Documento.Formato;
import Recepcion.Documento.Tipo;
import Recepcion.Procedencia.Institucion;
import Recepcion.Procedencia.ProcedenciaDAO;
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
import java.sql.Date;
import java.util.ResourceBundle;


public class recepcionAdminController implements Initializable {

    @FXML
    MenuItem SignOff;
    @FXML
    MenuItem instituciones;
    @FXML
    MenuItem areas;
    @FXML
    MenuItem tipodoc;
    @FXML
    MenuItem tipoforma;

    @FXML
    Button btnUsuarios;

    @FXML
    Menu menuCatalogos;




    public void showStageAreas() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("areasAyuntamiento.fxml"));
        Stage st = new Stage();
        st.setTitle("Procedencia");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.show();
    }

    public void showStageInstitucionProcedencia() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Procedencia/procedencia.fxml"));
        Stage st = new Stage();
        st.setTitle("ADMINISTRA INSTITUCIONES PROCEDENCIA");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.setMaximized(true);
        st.show();
    }

    public void showStageTipoDoc() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tipoDocumento.fxml"));
        Stage st = new Stage();
        st.setTitle("Procedencia");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.show();
    }

    public void showStageTipoFormato() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("formato.fxml"));
        Stage st = new Stage();
        st.setTitle("Consultas");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnUsuarios.setOnAction(handlerUser);
        SignOff.setOnAction(CerrarSesion);
        instituciones.setOnAction(Instituciones);


    }





    public void Login() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage st = new Stage();
        st.setTitle("LOGIN");

        Scene scene = new Scene(root, 700, 400);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);

        st.setResizable(false);
        st.show();
    }

    EventHandler<ActionEvent> handlerUser = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("hola bb");
        }
    };


    EventHandler<ActionEvent> CerrarSesion = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == SignOff) {
                try {
                    Login();
                    btnUsuarios.getGraphic().getScene().getWindow().hide();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    EventHandler<ActionEvent> Instituciones = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == instituciones) {
                try {
                    showStageInstitucionProcedencia();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
  /* EventHandler<ActionEvent> AreasAyuntamiento= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try {
                showStageAreas();
                ((Stage)(areas.getScene().getWindow())).hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    EventHandler<ActionEvent> TipoDocumento = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try {
                showStageTipoDoc();
                ((Stage)(BtnConsultas.getScene().getWindow())).hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    EventHandler<ActionEvent> TipoFormato = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try {
                showStageTipoFormato();
                ((Stage)(BtnConsultas.getScene().getWindow())).hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

}

*/
