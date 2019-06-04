package tipoDocumento;

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

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class tipoDocumentoController implements  Initializable{

    @FXML
    Button btnReturn, btnNew, btnSave, btnDelete;

    @FXML
    TextField txtIdTipoDoc, txtNombreTipoDoc;

    @FXML
    TableView<tipoDocumento> tblFormato;

    tipoDocumentoDAO tipoDocumentoDAO = new tipoDocumentoDAO(MySQLConnection.getConnection());
    private boolean insertMode=false;
    private boolean updateMode=false;

   @Override
    public void initialize(URL location, ResourceBundle resources) {
       initComponents();
    }

    private void initComponents()
    {
        TableColumn col1 = new TableColumn("Id Tipo Documento");
        TableColumn col2 = new TableColumn("Nombre Tipo Documento");



        col1.setCellValueFactory(new PropertyValueFactory<>("idTipoDocumento"));
        col2.setCellValueFactory(new PropertyValueFactory<>("nombreTipoDoc"));

        tblFormato.getColumns().addAll(col1, col2);
        tblFormato.setItems(tipoDocumentoDAO.fetchAll());
        tblFormato.refresh();

        btnNew.setOnAction(handlerNew);
        tblFormato.setOnMouseClicked(handlerTable);
        btnSave.setOnAction(handlerSave);
        btnDelete.setOnAction(handlerDelete);
        btnReturn.setOnAction(handlerReturn);

    }

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
                tipoDocumento tipdoc = tblFormato.getSelectionModel().getSelectedItem();
                txtIdTipoDoc.setText(String.valueOf(tipdoc.getIdTipoDocumento()));
                txtNombreTipoDoc.setText(tipdoc.getNombreTipoDoc());
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
                tipoDocumento tipdoc=tblFormato.getSelectionModel().getSelectedItem();
                tipoDocumentoDAO.delete(tipdoc.getIdTipoDocumento());
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
        tipoDocumento tipdoc = new tipoDocumento(
                Integer.valueOf(txtIdTipoDoc.getText()),
                txtNombreTipoDoc.getText());

        if(tipoDocumentoDAO.insert(tipdoc))
        {
            reloadProceptList();
            clearForm();
        }

    }

    private void updateProcedencia()
    {
        tipoDocumento tipdoc=new tipoDocumento(
                Integer.valueOf(txtIdTipoDoc.getText()),
                txtNombreTipoDoc.getText());

        if(tipoDocumentoDAO.update(tipdoc)){
            reloadProceptList();
            clearForm();
        }
    }

    private void reloadProceptList()
    {
        tblFormato.getItems().clear();
        tblFormato.setItems(tipoDocumentoDAO.fetchAll());
    }

    private void clearForm()
    {
        txtIdTipoDoc.setText("");
        txtNombreTipoDoc.setText("");
    }
    public void Return() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/recepcionAdmin.fxml"));
        Stage st= new Stage();
        st.setTitle("ADMINISTRADOR");

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
