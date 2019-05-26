package sample;

import Recepcion.Destinatario.DestinatarioDAO;
import Recepcion.Destinatario.Instruccion;
import Recepcion.Destinatario.Prioridad;
import Recepcion.Documento.DocumentoDAO;
import Recepcion.Documento.DocumentoInsert;
import Recepcion.Documento.Formato;
import Recepcion.Documento.Tipo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;


public class recepcionController implements Initializable {

    @FXML
    TextField txtFolio, txtNoDoc,txtIdDoc;
    @FXML
    DatePicker dpFechaDoc, dpFechaRecep;
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

    DocumentoDAO Documento = new DocumentoDAO(MySQLConnection.getConnection());
    DestinatarioDAO Destinatario = new DestinatarioDAO(MySQLConnection.getConnection());
    private boolean insertMode = true;


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


    }

    EventHandler<ActionEvent> handlerinsert = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

                insertEmployee();

            System.out.println("p");
        }




    };
    private void insertEmployee() {
        DocumentoInsert doc = new DocumentoInsert(
                Integer.valueOf(txtNoDoc.getText()),
                Integer.valueOf(txtFolio.getText()),
                cmbFormato.getSelectionModel().getSelectedItem().getIdFomato(),
                cmbTipo.getSelectionModel().getSelectedItem().getIdTipoDocumento(),
                Integer.valueOf(txtIdDoc.getText()),
                Date.valueOf(dpFechaRecep.getValue()),
                Date.valueOf(dpFechaDoc.getValue())
                );
        Documento.insert(doc);
    }
}


