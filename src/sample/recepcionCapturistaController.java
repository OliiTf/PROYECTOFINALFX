package sample;

import Recepcion.Destinatario.DestinatarioDAO;
import Recepcion.Destinatario.DestinatarioInsert;
import Recepcion.Destinatario.Instruccion;
import Recepcion.Destinatario.Prioridad;
import Recepcion.Documento.DocumentoDAO;
import Recepcion.Documento.DocumentoInsert;
import Recepcion.Documento.Formato;
import Recepcion.Documento.Tipo;
import Recepcion.Procedencia.Institucion;
import Recepcion.Procedencia.ProcedenciaDAO;
import Recepcion.Procedencia.ProcedenciaInsert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;


public class recepcionCapturistaController implements Initializable {

    @FXML
    TextField txtFolio, txtNoDoc,txtIdDoc,txtIdDestinatario,txtQuienRecibe,txtareaayuntamiento,txtprocedencia,txtfirma,txtpuesto,txtdirigido,txtasunto,txtobservaciones;
    @FXML
    DatePicker dpFechaDoc, dpFechaRecep,dpfechalimite,dpfechaentrega;
    @FXML
    ComboBox<Formato> cmbFormato;
    @FXML
    ComboBox<Tipo> cmbTipo;
    @FXML
    Button BtnRecepcion;
    @FXML
    ComboBox<Instruccion>  cmbinstruccion;
    @FXML
    ComboBox<Prioridad> cmbprioridad;
    @FXML
    ComboBox<Institucion> cmbinstitucion;


    DocumentoDAO Documento = new DocumentoDAO(MySQLConnection.getConnection());
    DestinatarioDAO Destinatario = new DestinatarioDAO(MySQLConnection.getConnection());
    ProcedenciaDAO Procedencia= new ProcedenciaDAO(MySQLConnection.getConnection());


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLogin();

    }

    private void initLogin() {

        cmbFormato.setItems(Documento.fetchFormato());

        cmbTipo.setItems(Documento.fetchTipo());

        cmbinstruccion.setItems(Destinatario.fetchInstruccion());

        cmbprioridad.setItems(Destinatario.fetchPrioridad());

        BtnRecepcion.setOnAction(handlerinsert);

        cmbinstitucion.setItems(Procedencia.fetchInstituccion());


    }

    EventHandler<ActionEvent> handlerinsert = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            insertDocumento();


            System.out.println("p");
        }




    };
    private void insertDocumento() {
        DocumentoInsert doc = new DocumentoInsert(
                Integer.valueOf(txtNoDoc.getText()),
                Integer.valueOf(txtFolio.getText()),
                cmbFormato.getSelectionModel().getSelectedIndex(),
                cmbTipo.getSelectionModel().getSelectedIndex(),
                Integer.valueOf(txtIdDoc.getText()),
                Date.valueOf(dpFechaRecep.getValue()),
                Date.valueOf(dpFechaDoc.getValue())
                );
        DestinatarioInsert des = new DestinatarioInsert(
                Integer.valueOf(txtIdDestinatario.getText()),
                Integer.valueOf(txtareaayuntamiento.getText()),
                String.valueOf(txtQuienRecibe.getText()),
                cmbinstruccion.getSelectionModel().getSelectedIndex(),
                cmbprioridad.getSelectionModel().getSelectedIndex(),
                Date.valueOf(dpfechalimite.getValue()),
                Date.valueOf(dpfechaentrega.getValue())


        );
        ProcedenciaInsert pro = new ProcedenciaInsert(
                Integer.valueOf(txtprocedencia.getText()),
                cmbinstitucion.getSelectionModel().getSelectedItem().getIdInstitucion(),
                String.valueOf(txtfirma.getText()),
                String.valueOf(txtpuesto.getText()),
                String.valueOf(txtdirigido.getText()),
                String.valueOf(txtasunto.getText()),
                String.valueOf(txtobservaciones.getText())


        );



        Documento.insert(doc);
        Destinatario.insert(des);
        Procedencia.insert(pro);
    }
}


