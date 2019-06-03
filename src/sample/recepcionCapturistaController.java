package sample;

import AreasAyuntamiento.AreasAyuntamiento;
import AreasAyuntamiento.AyuntamientoDAO;
import Procedencia.InstitucionProcedencia;
import Recepcion.Destinatario.Destinatario;
import Recepcion.Destinatario.DestinatarioDAO;
import Recepcion.Destinatario.Instruccion;
import Recepcion.Destinatario.Prioridad;
import Recepcion.Documento.*;
import Recepcion.Procedencia.InfomaciónProcedencia;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;


public class recepcionCapturistaController implements Initializable {
    @FXML
    MenuItem SignOff;
    @FXML
    TextField txtFolio, txtNoDoc,txtIdDoc,txtIdDestinatario,txtQuienRecibe,txtprocedencia,txtfirma,txtpuesto,txtdirigido,txtasunto,txtobservaciones;
    @FXML
    DatePicker dpFechaDoc, dpFechaRecep,dpfechalimite,dpfechaentrega;
    @FXML
    ComboBox<Formato> cmbFormato;
    @FXML
    ComboBox<Tipo> cmbTipo;

    @FXML
    ComboBox<AreasAyuntamiento> cmbAreaAyun;
    @FXML
    Button BtnRecepcion;
    @FXML
    Button BtnReportes;
    @FXML
    Button BtnConsultas,BtnEliminar,BtnNuevo;
    @FXML
    ComboBox<Instruccion>  cmbinstruccion;
    @FXML
    ComboBox<Prioridad> cmbprioridad;
    @FXML
    ComboBox<InstitucionProcedencia> cmbinstitucion;
    @FXML
    CheckBox chkAdjuntar,checkentregado;

    @FXML
     TableView<ConsultaDocumentos> tblDocuments;


    DetalleDocumentoDAO detalleDocumentoDAO = new DetalleDocumentoDAO(MySQLConnection.getConnection());
    DocumentoDAO DocumentoDAO = new DocumentoDAO(MySQLConnection.getConnection());
    DestinatarioDAO DestinatarioDAO = new DestinatarioDAO(MySQLConnection.getConnection());
    ProcedenciaDAO ProcedenciaDAO= new ProcedenciaDAO(MySQLConnection.getConnection());
    AyuntamientoDAO ayuntamientoDAO = new AyuntamientoDAO(MySQLConnection.getConnection());

    private boolean insertMode=false;
    private boolean updateMode=false;

    public void showStageReportes() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reportes.fxml"));
        Stage st= new Stage();
        st.setTitle("REPORTES");

        Scene scene = new Scene(root,900,500);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.show();
    }
    public void showStageConsultas() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("consultas.fxml"));
        Stage st= new Stage();
        st.setTitle("CONSULTA DOCUMENTOS");

        Scene scene = new Scene(root,900,500);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComponents();
        BtnNuevo.setOnAction(handlerNew);
        SignOff.setOnAction(CerrarSesion);
        BtnReportes.setOnAction(handlerReportes);
        BtnConsultas.setOnAction(handlerConsultas);
        BtnEliminar.setOnAction(handlerDeleteDoc);


    }

    private void initComponents() {

        txtIdDoc.setText(String.valueOf(resetCount()));
        cmbFormato.setItems(DocumentoDAO.fetchFormato());
        cmbTipo.setItems(DocumentoDAO.fetchTipo());
        cmbinstitucion.setItems(ProcedenciaDAO.fetchInstituccion());
        cmbAreaAyun.setItems(ayuntamientoDAO.findnombreAreas());
        cmbinstruccion.setItems(DestinatarioDAO.fetchInstruccion());
        cmbprioridad.setItems(DestinatarioDAO.fetchPrioridad());
        txtIdDestinatario.setText(String.valueOf(resetCountDest()));
        txtprocedencia.setText(String.valueOf(resetCountProc()));

        TableColumn col6 = new TableColumn("ID DOC");
        TableColumn col7 = new TableColumn("ID DESTINATARIO");
        TableColumn col8 = new TableColumn("ID PROCEDENCIA");
        TableColumn col1 = new TableColumn("FOLIO");
        TableColumn col2 = new TableColumn("NO. DOCUMENTO");
        TableColumn col3 = new TableColumn("PROCEDENCIA");
        TableColumn col4 = new TableColumn("FECHA DE RECEPCION");
        TableColumn col5 = new TableColumn("OBSERVACIONES");




        col6.setCellValueFactory(new PropertyValueFactory<>("idDocumento"));
        col7.setCellValueFactory(new PropertyValueFactory<>("idDestinatario"));
        col8.setCellValueFactory(new PropertyValueFactory<>("idProcedencia"));
        col1.setCellValueFactory(new PropertyValueFactory<>("numFolio"));
        col2.setCellValueFactory(new PropertyValueFactory<>("numDocumento"));
        col3.setCellValueFactory(new PropertyValueFactory<>("nombreInstitucion"));
        col4.setCellValueFactory(new PropertyValueFactory<>("fechaRecepcion"));
        col5.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        tblDocuments.getColumns().addAll(col6,col7,col8,col1, col2, col3, col4, col5);


        tblDocuments.setItems(DocumentoDAO.AllDocs());


    }



    EventHandler<ActionEvent> handlerNew = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            insertMode=true;
            updateMode=false;
            insertDocumento();


        }




    };
    private void insertDocumento() {



        Destinatario destinatario = new Destinatario(
                resetCountDest(),
                Date.valueOf(dpfechalimite.getValue()),
                Date.valueOf(dpfechaentrega.getValue()),
                txtQuienRecibe.getText(),
                ayuntamientoDAO.findIdArea(cmbAreaAyun.getSelectionModel().getSelectedItem().getNombreArea()).getIdArea(),
                DestinatarioDAO.findIdintruccion(cmbinstruccion.getSelectionModel().getSelectedItem().getDescInstruccion()).getIdInstruccion(),
                DestinatarioDAO.findIdPrioridad(cmbprioridad.getSelectionModel().getSelectedItem().getDescPrioridad()).getIdPrioridad());


        InfomaciónProcedencia infomaciónProcedencia = new InfomaciónProcedencia(
                resetCountProc(),
                txtfirma.getText(),
                txtpuesto.getText(),
                txtdirigido.getText(),
                txtasunto.getText(),
                txtobservaciones.getText(),
                ProcedenciaDAO.findIdInst(cmbinstitucion.getSelectionModel().getSelectedItem().getNombreInstitucion()).getIdInstitucion());




        Documento doc = new Documento(
                Integer.valueOf(txtFolio.getText()),
                2,
                Integer.valueOf(txtIdDestinatario.getText()),
                Integer.valueOf(txtprocedencia.getText()),
                chkAdjuntar.isSelected(),
                checkentregado.isSelected());

        DetalleDocumento detaildoc = new DetalleDocumento(
                    resetCount(),
                Integer.valueOf(txtNoDoc.getText()),
                Date.valueOf(dpFechaRecep.getValue()),
                Date.valueOf(dpFechaDoc.getValue()),
                detalleDocumentoDAO.findIdFormato(cmbFormato.getSelectionModel().getSelectedItem().getNombreFormato()).getIdFormato(),
                detalleDocumentoDAO.findIfTipoDoc(cmbTipo.getSelectionModel().getSelectedItem().getNombreTipoDoc()).getIdTipoDocumento(),
                Integer.valueOf(txtFolio.getText()));


        DestinatarioDAO.insert(destinatario);
        ProcedenciaDAO.insert(infomaciónProcedencia);
        DocumentoDAO.insert(doc);


        if(detalleDocumentoDAO.insert(detaildoc))
        {
            clearFormDoc();
            reloadDocumentList();
        }


    }




    EventHandler<ActionEvent> handlerDeleteDoc = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación de borrado");
            alert.setContentText("Estas seguro de eliminar este documento");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()==ButtonType.OK)
            {
                ConsultaDocumentos consultaDocumentos =tblDocuments.getSelectionModel().getSelectedItem();
                DocumentoDAO.delete(consultaDocumentos.getIdDocumento(),consultaDocumentos.getNumFolio(),
                        consultaDocumentos.getIdDestinatario(),consultaDocumentos.getIdProcedencia());
                clearFormDoc();
                reloadDocumentList();
            }
            else{
                alert.close();
            }
        }
    };
    private void  reloadDocumentList()
    {
        tblDocuments.getItems().clear();
        tblDocuments.setItems(DocumentoDAO.AllDocs());
    }


    private int resetCount()
    {
        int contDoc = detalleDocumentoDAO.countDoc().getIdDocumento();
        return contDoc+1;
    }

    private int resetCountDest()
    {
        int contDest = DestinatarioDAO.countDestinat().getIdDestinatario();
        return contDest+1;
    }

    private int resetCountProc()
    {
        int contProc = ProcedenciaDAO.countProc().getIdProcedencia();
        return contProc+1;
    }

    private void clearFormDoc()
    {
        txtIdDoc.setText(String.valueOf(resetCount()));
        txtFolio.setText("");
        txtNoDoc.setText("");
        dpFechaRecep.setValue(null);
        dpFechaDoc.setValue(null);
        cmbFormato.valueProperty().setValue(null);
        cmbTipo.valueProperty().setValue(null);
        txtIdDestinatario.setText(String.valueOf(resetCountDest()));
        txtQuienRecibe.setText("");
        dpfechalimite.setValue(null);
        dpfechaentrega.setValue(null);
        cmbAreaAyun.valueProperty().setValue(null);
        cmbprioridad.valueProperty().setValue(null);
        cmbinstruccion.valueProperty().setValue(null);
        cmbinstitucion.valueProperty().setValue(null);
        cmbinstruccion.valueProperty().setValue(null);
        txtprocedencia.setText(String.valueOf(resetCountProc()));
        txtfirma.setText("");
        txtpuesto.setText("");
        txtdirigido.setText("");
        txtasunto.setText("");
        txtobservaciones.setText("");
        checkentregado.setSelected(false);
        chkAdjuntar.setSelected(false);
    }



    public void Login() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage st= new Stage();
        st.setTitle("Login");

        Scene scene = new Scene(root,700,400);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);

        st.setResizable(false);
        st.show();
    }

    EventHandler<ActionEvent> handlerReportes = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try {
                showStageReportes();
                ((Stage)(BtnReportes.getScene().getWindow())).hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    EventHandler<ActionEvent> handlerConsultas = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try {
                showStageConsultas();
                ((Stage)(BtnConsultas.getScene().getWindow())).hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    EventHandler<ActionEvent> CerrarSesion = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==SignOff)
            {
                try {
                    Login();
                    BtnNuevo.getGraphic().getScene().getWindow().hide();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

}


