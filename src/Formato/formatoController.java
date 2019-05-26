package Formato;

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

public class formatoController implements  Initializable{

    @FXML
    Button btnReturn, btnNew, btnSave, btnDelete;

    @FXML
    TextField txtIdIns, txtNombreIns;

    @FXML
    TableView<formato> tblFormato;

    formatoDAO formatoDAO = new formatoDAO(MySQLConnection.getConnection());
    private boolean insertMode=false;
    private boolean updateMode=false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        }

    private void initComponents()
    {
        TableColumn col1 = new TableColumn("Id Formato");
        TableColumn col2 = new TableColumn("Nombre Formato");



        col1.setCellValueFactory(new PropertyValueFactory<>("idFormato"));
        col2.setCellValueFactory(new PropertyValueFactory<>("nombreFormato"));

        tblFormato.getColumns().addAll(col1, col2);
        tblFormato.setItems(formatoDAO.fetchAll());
        tblFormato.refresh();

        btnNew.setOnAction(handlerNew);
        tblFormato.setOnMouseClicked(handlerTable);
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
                formato form = tblFormato.getSelectionModel().getSelectedItem();
                txtIdIns.setText(String.valueOf(form.getIdFormato()));
                txtNombreIns.setText(form.getNombreFormato());
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
                formato form=tblFormato.getSelectionModel().getSelectedItem();
                formatoDAO.delete(form.getIdFormato());
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
        formato form = new formato(
                Integer.valueOf(txtIdIns.getText()),
                txtNombreIns.getText());

        if(formatoDAO.insert(form))
        {
            reloadProceptList();
            clearForm();
        }

    }

    private void updateProcedencia()
    {
        formato form=new formato(
                Integer.valueOf(txtIdIns.getText()),
                txtNombreIns.getText());

        if(formatoDAO.update(form)){
            reloadProceptList();
            clearForm();
        }
    }

    private void reloadProceptList()
    {
        tblFormato.getItems().clear();
        tblFormato.setItems(formatoDAO.fetchAll());
    }

    private void clearForm()
    {
        txtIdIns.setText("");
        txtNombreIns.setText("");
    }

}
