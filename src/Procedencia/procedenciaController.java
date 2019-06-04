package Procedencia;

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
import sample.MySQLConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class procedenciaController implements Initializable {
    @FXML
    Button btnReturn, btnNew, btnSave, btnDelete;

    @FXML
    TextField txtIdIns, txtNombreIns;

    @FXML
    TableView<InstitucionProcedencia> tblProcedencia;

    @FXML
    MenuItem SignOff;

    @FXML
    MenuItem RProced,RDocProced;


    ProcedenciaDAO procedenciaDAO = new ProcedenciaDAO(MySQLConnection.getConnection());
    private boolean insertMode=false;
    private boolean updateMode=false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComponents();
        RProced.setOnAction(handlerPDFProced);
        RDocProced.setOnAction(handlerPDFProced2);
    }

    private void initComponents()
    {
        TableColumn col1 = new TableColumn("Id Institucion");
        TableColumn col2 = new TableColumn("Nombre Institucion");



        col1.setCellValueFactory(new PropertyValueFactory<>("idInstitucion"));
        col2.setCellValueFactory(new PropertyValueFactory<>("nombreInstitucion"));

        tblProcedencia.getColumns().addAll(col1, col2);
        tblProcedencia.setItems(procedenciaDAO.fetchAll());
        tblProcedencia.refresh();

        btnNew.setOnAction(handlerNew);
        tblProcedencia.setOnMouseClicked(handlerTable);
        btnSave.setOnAction(handlerSave);
        btnDelete.setOnAction(handlerDelete);

    }
    public static final String DEST1 = "C:/Users/Lizeth R/reports/InstitucionesProcedencia.pdf";
    public static final String DEST4 = "C:/Users/Lizeth R/reports/DocumentosProcedencia.pdf";

    EventHandler<ActionEvent> handlerPDFProced = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource()==RProced)
            {
                File file = new File(DEST1);
                file.getParentFile().mkdirs();
                try {
                    new ReportProcedencia().createPdfProcedencia(DEST1,procedenciaDAO.fetchAll());
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("REPORT 1");
                    alert.setContentText("Reporte Creado");
                    alert.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    EventHandler<ActionEvent> handlerPDFProced2 = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource()==RDocProced)
            {
                File file = new File(DEST4);
                file.getParentFile().mkdirs();
                try {
                    new GeneracionReporteProcedencia().createPdf(DEST4);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };



    EventHandler<ActionEvent> handlerNew = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            insertMode=true;
            updateMode=false;
            insertProcedencia();
            clearForm();
        }
    };

    EventHandler<MouseEvent> handlerTable = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 2) {
                updateMode = true;
                insertMode = false;
                InstitucionProcedencia procedencia = tblProcedencia.getSelectionModel().getSelectedItem();
                txtIdIns.setText(String.valueOf(procedencia.getIdInstitucion()));
                txtNombreIns.setText(procedencia.getNombreInstitucion());
            }
        }
    };


    EventHandler<ActionEvent> handlerSave = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (updateMode)
            {
                updateProcedencia();
            }
        }
    };

    EventHandler<ActionEvent> handlerDelete = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmacion");
            alert.setContentText("Estas seguro de eliminar este registro?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()==ButtonType.OK)
            {
                InstitucionProcedencia procedencia=tblProcedencia.getSelectionModel().getSelectedItem();
                procedenciaDAO.delete(procedencia.getIdInstitucion());
                clearForm();
                reloadProceptList();
            }
            else{
                alert.close();
            }
        }
    };

    private void insertProcedencia()
    {
        InstitucionProcedencia procedencia = new InstitucionProcedencia(
                Integer.valueOf(txtIdIns.getText()),
                txtNombreIns.getText());

        if(procedenciaDAO.insert(procedencia))
        {
            reloadProceptList();
            clearForm();
        }

    }

    private void updateProcedencia()
    {
        InstitucionProcedencia procedencia=new InstitucionProcedencia(
                Integer.valueOf(txtIdIns.getText()),
                txtNombreIns.getText());

        if(procedenciaDAO.update(procedencia)){
            reloadProceptList();
            clearForm();
        }
    }

    private void reloadProceptList()
    {
        tblProcedencia.getItems().clear();
        tblProcedencia.setItems(procedenciaDAO.fetchAll());
    }

    private void clearForm()
    {
        txtIdIns.setText("");
        txtNombreIns.setText("");
    }

    /*public void Return() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Table.fxml"));
        Stage st= new Stage();
        st.setTitle("Employees");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);

        st.setMaximized(true);
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
    };*/
}
