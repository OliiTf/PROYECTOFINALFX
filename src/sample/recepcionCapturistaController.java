package sample;

import Recepcion.Destinatario.Destinatario;
import Recepcion.Destinatario.DestinatarioDAO;
import Recepcion.Destinatario.Instruccion;
import Recepcion.Destinatario.Prioridad;
import Recepcion.Documento.*;
import Recepcion.Procedencia.Institucion;
import Recepcion.Procedencia.ProcedenciaDAO;
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


    DetalleDocumentoDAO detalleDocumentoDAO = new DetalleDocumentoDAO(MySQLConnection.getConnection());
    DocumentoDAO DocumentoDAO = new DocumentoDAO(MySQLConnection.getConnection());
    DestinatarioDAO DestinatarioDAO = new DestinatarioDAO(MySQLConnection.getConnection());
    ProcedenciaDAO ProcedenciaDAO= new ProcedenciaDAO(MySQLConnection.getConnection());
    private boolean insertMode=false;
    private boolean updateMode=false;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComponents();
        BtnRecepcion.setOnAction(handlerNew);


    }

    private void initComponents() {

        txtIdDoc.setText(String.valueOf(resetCount()));
        cmbFormato.setItems(DocumentoDAO.fetchFormato());
        cmbTipo.setItems(DocumentoDAO.fetchTipo());
        cmbinstitucion.setItems(ProcedenciaDAO.fetchInstituccion());
        txtIdDestinatario.setText(String.valueOf(resetCountDest()));


        //detalleDocumentoDAO.findIdFormato()




        /*cmbinstruccion.setItems(Destinatario.fetchInstruccion());

        cmbprioridad.setItems(Destinatario.fetchPrioridad());

        BtnRecepcion.setOnAction(handlerinsert);

        cmbinstitucion.setItems(Procedencia.fetchInstituccion());*/




    }



    EventHandler<ActionEvent> handlerNew = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            insertMode=true;
            updateMode=false;
            insertDocumento();
            insertDestinatario();

        }




    };
    private void insertDocumento() {
        Documento doc = new Documento(
                Integer.valueOf(txtFolio.getText()),
                2,5,2,false);


        DocumentoDAO.insert(doc);
        DetalleDocumento detaildoc = new DetalleDocumento(
                    resetCount(),
                Integer.valueOf(txtNoDoc.getText()),
                Date.valueOf(dpFechaRecep.getValue()),
                Date.valueOf(dpFechaDoc.getValue()),
                detalleDocumentoDAO.findIdFormato(cmbFormato.getSelectionModel().getSelectedItem().getNombreFormato()).getIdFormato(),
                detalleDocumentoDAO.findIfTipoDoc(cmbTipo.getSelectionModel().getSelectedItem().getNombreTipoDoc()).getIdTipoDocumento(),
                Integer.valueOf(txtFolio.getText())
        );
        if(detalleDocumentoDAO.insert(detaildoc))
        {
            clearFormDetailDoc();
        }

        /*Destinatario.insert(des);
        Procedencia.insert(pro);*/
    }

    private void insertDestinatario()
    {
        Destinatario destinatario = new Destinatario(
                resetCountDest()+1,
                Date.valueOf(dpfechalimite.getValue()),
                Date.valueOf(dpfechaentrega.getValue()),
                txtQuienRecibe.getText(),
                10,5,2
        );
    }

    private int resetCount()
    {
        int contDoc = detalleDocumentoDAO.countDoc().getIdDocumento();
        return contDoc+1;
    }

    private int resetCountDest()
    {
        int contDest = DestinatarioDAO.countDestinat().getIdDestinataario();
        return contDest+1;
    }

    private void clearFormDetailDoc()
    {
        txtIdDoc.setText(String.valueOf(resetCount()));
        txtFolio.setText("");
        txtNoDoc.setText("");
        dpFechaRecep.setValue(null);
        dpFechaDoc.setValue(null);
        cmbFormato.valueProperty().setValue(null);
        cmbTipo.valueProperty().setValue(null);

    }
}


