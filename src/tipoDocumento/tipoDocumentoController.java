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
    TableView<tipoDocumento> tbTipoDoc;

    tipoDocumentoDAO tipoDocumentoDAO = new tipoDocumentoDAO(MySQLConnection.getConnection());
    private boolean insertMode=false;
    private boolean updateMode=false;

   @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void initComponents()
    {
        TableColumn col1 = new TableColumn("Id Tipo Documento");
        TableColumn col2 = new TableColumn("Nombre Tipo Docuemnto");



        col1.setCellValueFactory(new PropertyValueFactory<>("idFormato"));
        col2.setCellValueFactory(new PropertyValueFactory<>("nombreFormato"));

        tbTipoDoc.getColumns().addAll(col1, col2);
        tbTipoDoc.setItems(tipoDocumentoDAO.fetchAll());
        tbTipoDoc.refresh();

        btnNew.setOnAction(handlerNew);
        tbTipoDoc.setOnMouseClicked(handlerTable);
        btnSave.setOnAction(handlerSave);
        btnDelete.setOnAction(handlerDelete);

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
                tipoDocumento tipdoc = tbTipoDoc.getSelectionModel().getSelectedItem();
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
                tipoDocumento tipdoc=tbTipoDoc.getSelectionModel().getSelectedItem();
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
        tbTipoDoc.getItems().clear();
        tbTipoDoc.setItems(tipoDocumentoDAO.fetchAll());
    }

    private void clearForm()
    {
        txtIdTipoDoc.setText("");
        txtNombreTipoDoc.setText("");
    }
}
