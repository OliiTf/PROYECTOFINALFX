package Reportes;

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
import javafx.stage.StageStyle;
import sample.MySQLConnection;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class EntregadosController implements Initializable {


    @FXML
    Button btnReturn, btnGuardar;

    @FXML
    DatePicker dpFechaEntrega;

    @FXML
    TableView<ReportesNoEntregados> tblEntregados;

    @FXML
    TextField txtQuienR;

    ReportesDAO reportesDAO = new ReportesDAO(MySQLConnection.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    dpFechaEntrega.setVisible(false);
    initComponents();
    tblEntregados.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount()==2)
            {
                dpFechaEntrega.setVisible(true);
                txtQuienR.setVisible(true);
            }
        }
    });
    btnGuardar.setOnAction(handlerGuardar);
    btnReturn.setOnAction(handlerRet);
    }

    EventHandler<ActionEvent> handlerGuardar = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            ReportesNoEntregados reportesNoEntregados = tblEntregados.getSelectionModel().getSelectedItem();
            ReportesNoEntregados rne =new ReportesNoEntregados(Date.valueOf(dpFechaEntrega.getValue()),
                    txtQuienR.getText());
            if(reportesDAO.update(reportesNoEntregados.getIdDestinatario(),rne))
            {
                reloadDocList();
                clearForm();
            }
        }
    };

    private void reloadDocList()
    {
        tblEntregados.getItems().clear();
        tblEntregados.setItems(reportesDAO.findDocumentosNoentregados());
    }

    private void clearForm()
    {
        dpFechaEntrega.setValue(null);
        txtQuienR.setText("");
    }

    private void initComponents()
    {
        TableColumn col7 = new TableColumn("Id destinatario");
        TableColumn col1 = new TableColumn("Folio");
        TableColumn col2 = new TableColumn("No. Documento");
        TableColumn col3 = new TableColumn("Insititucion de Procedencia");
        TableColumn col4 = new TableColumn("Fecha de Recepcion");
        TableColumn col5 = new TableColumn("Entregado");
        TableColumn col6 = new TableColumn("Fecha Entrega");



        col7.setCellValueFactory(new PropertyValueFactory<>("idDestinatario"));
        col1.setCellValueFactory(new PropertyValueFactory<>("numFolio"));
        col2.setCellValueFactory(new PropertyValueFactory<>("numDocumento"));
        col3.setCellValueFactory(new PropertyValueFactory<>("nombreInstitucion"));
        col4.setCellValueFactory(new PropertyValueFactory<>("fechaRecepcion"));
        col5.setCellValueFactory(new PropertyValueFactory<>("entregado"));
        col6.setCellValueFactory(new PropertyValueFactory<>("fechaEntrega"));
        tblEntregados.getColumns().addAll(col7,col1, col2,col3,col4,col5,col6);
        tblEntregados.setItems(reportesDAO.findDocumentosNoentregados());


    }

    public void Return() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/reportes.fxml"));
        Stage st= new Stage();
        st.setTitle("REPORTES");

        Scene scene = new Scene(root,900,500);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.initStyle( StageStyle.TRANSPARENT );
        st.show();
    }

    EventHandler<ActionEvent> handlerRet = new EventHandler<ActionEvent>() {
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
