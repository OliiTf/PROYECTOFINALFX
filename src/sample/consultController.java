package sample;

import Procedencia.InstitucionProcedencia;
import Recepcion.Documento.Documento;
import Recepcion.Procedencia.Institucion;
import Recepcion.Procedencia.ProcedenciaDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class consultController implements Initializable {

    @FXML
    TableView <ConsultaDocumentos> tblConsulta;
    @FXML
    Button btnReturn,btnFiltrarProc, btnFiltrarFol;

    @FXML
    ComboBox<InstitucionProcedencia> cmbProcedencia;

    @FXML
    TextField txtFolio;

    @FXML
     CheckBox chkFolios,chkProcedencia;

    ProcedenciaDAO procedenciaDAO = new ProcedenciaDAO(MySQLConnection.getConnection());
    consultasDAO consultasDAO = new consultasDAO(MySQLConnection.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initComponents();
        chkFolios.selectedProperty().addListener(listenerCheckFolios);
        chkProcedencia.selectedProperty().addListener(listenerCheckProced);

    }

    private void initComponents()
    {
        TableColumn col1 = new TableColumn("FOLIO");
        TableColumn col2 = new TableColumn("NO. DOCUMENTO");
        TableColumn col3 = new TableColumn("PROCEDENCIA");
        TableColumn col4 = new TableColumn("FECHA DE RECEPCION");
        TableColumn col5 = new TableColumn("OBSERVACIONES");





        col1.setCellValueFactory(new PropertyValueFactory<>("numFolio"));
        col2.setCellValueFactory(new PropertyValueFactory<>("numDocumento"));
        col3.setCellValueFactory(new PropertyValueFactory<>("nombreInstitucion"));
        col4.setCellValueFactory(new PropertyValueFactory<>("fechaRecepcion"));
        col5.setCellValueFactory(new PropertyValueFactory<>("observaciones"));


        tblConsulta.getColumns().addAll(col1, col2, col3, col4, col5);


        btnReturn.setOnAction(handlerReturn);

    }


    ChangeListener<Boolean> listenerCheckProced = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (newValue)
            {
                btnFiltrarProc.setVisible(true);
                btnFiltrarFol.setVisible(false);
                cmbProcedencia.setVisible(true);
                cmbProcedencia.setItems(procedenciaDAO.fetchInstituccion());
                cmbProcedencia.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        btnFiltrarProc.setOnAction(handlerFiltarProc);
                    }
                });
            }

            if (!chkProcedencia.isSelected())
            {
                cmbProcedencia.setVisible(false);
            }
        }
    };

    ChangeListener<Boolean> listenerCheckFolios = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (newValue)
            {
                txtFolio.setVisible(true);
                btnFiltrarProc.setVisible(false);
                btnFiltrarFol.setVisible(true);
                btnFiltrarFol.setOnAction(getHandlerFiltarFolio);

            }

            if (!chkFolios.isSelected()){
               txtFolio.setVisible(false);
            }

        }
    };

    EventHandler<ActionEvent> handlerFiltarProc = new EventHandler<ActionEvent>() {

        public static final String DEST1 = "results/reports/reporteConsulta.pdf";
        @Override
        public void handle(ActionEvent event) {
            tblConsulta.setItems(consultasDAO.fetchDocumentsProc(cmbProcedencia.getSelectionModel().getSelectedItem().getNombreInstitucion()));
            tblConsulta.refresh();

            tblConsulta.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount()==2) {
                        File file = new File(DEST1);
                        file.getParentFile().mkdirs();
                        try {
                            ConsultaDocumentos consultaDocumentos = tblConsulta.getSelectionModel().getSelectedItem();
                            new ReporteConsultaDocumentos().PDFdocuments(DEST1,consultasDAO.reportDocumento(consultaDocumentos.getNumFolio(),consultaDocumentos.getNumDocumento()),
                                    consultasDAO.reportDestinat(consultaDocumentos.getNumFolio(),consultaDocumentos.getNumDocumento()),
                                    consultasDAO.reportProc(consultaDocumentos.getNumFolio(),consultaDocumentos.getNumDocumento()));
                            Desktop.getDesktop().open(new File(DEST1));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }
            });



            if (tblConsulta.getItems().isEmpty())
            {
                Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CONSULTA DOCUMENTOS INSTITUCION PROCEDENCIA");
                alert.setContentText("Esta Institución no tiene documentos registrados");
                alert.show();
            }
        }
    };

    EventHandler<ActionEvent> getHandlerFiltarFolio = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            int folio = Integer.valueOf(txtFolio.getText());
            busquedaFolio(folio);

        }


    };



    public  void busquedaFolio(int folio){

        List<Documento> documentoArrayList = consultasDAO.fetchDocs();
        System.out.println(documentoArrayList.size());

        for(int i = 0; i < documentoArrayList.size(); i++){

            if(documentoArrayList.get(i).getNumFolio() == folio ){//comparamos el elemento en el arreglo con el buscado
                tblConsulta.setItems(consultasDAO.fetchDocumentsFolio(Integer.valueOf(txtFolio.getText())));
                tblConsulta.refresh();
                txtFolio.setText("");
            }
        }
        if (folio>documentoArrayList.size())
        {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("CONSULTA DOCUMENTOS NO. FOLIO");
            alert.setContentText("Este folio no existe");
            alert.show();
            txtFolio.setText("");
        }

    }

    public void Return() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("recepcionCapturista.fxml"));
        Stage st= new Stage();
        st.setTitle("Recepcion Capturista");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.setMaximized(true);

        st.show();
    }
    EventHandler<ActionEvent> handlerReturn = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try {
                Return();
                ((Stage)(btnReturn.getScene().getWindow())).hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    };
}
