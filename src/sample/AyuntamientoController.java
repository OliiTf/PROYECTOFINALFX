package sample;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class AyuntamientoController implements Initializable {
    @FXML
    Label lblidArea, lblnombreArea;

    @FXML
    TextField txtidArea, txtnombreArea;
    @FXML
    Button  btnNew, btnSave, btnDelete;

    @FXML
    TableView<AreasAyuntamiento> tableAreasAyuntamiento;

    AyuntamientoDAO ayuntamientoDAO= new AyuntamientoDAO(MySQLConnection.getConnection());



    private boolean insertMode=false;
    private boolean updateMode=false;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lblidArea.setVisible(true);
        lblnombreArea.setVisible(true);
        txtidArea.setVisible(true);
        txtnombreArea.setVisible(true);
        btnSave.setVisible(true);
        tableAreasAyuntamiento.setVisible(true);


        TableColumn col1 = new TableColumn("Id Area");
        TableColumn col2 = new TableColumn("Nombre Area");



        col1.setCellValueFactory(new PropertyValueFactory<>("idArea"));
        col2.setCellValueFactory(new PropertyValueFactory<>("nombreArea"));




        tableAreasAyuntamiento.getColumns().addAll(col1, col2);


        tableAreasAyuntamiento.setItems(AyuntamientoDAO.fetchAll());
        tableAreasAyuntamiento.refresh();

        btnNew.setOnAction(handlerNew);
        btnSave.setOnAction(handlerSave);
        btnDelete.setOnAction(handlerDelete);






        tableAreasAyuntamiento.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    updateMode = true;
                    insertMode = false;
                    AreasAyuntamiento departments = tableAreasAyuntamiento.getSelectionModel().getSelectedItem();
                    txtidArea.setText(String.valueOf(departments.getIdArea()));
                    txtnombreArea.setText(departments.getNombreArea());
                }

            }
        });

    }



    EventHandler<ActionEvent> handlerNew = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            clearForm();
            insertMode=true;
            updateMode=false;
        }
    };



    EventHandler<ActionEvent> handlerSave = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(insertMode)
            {
                insertArea();
            }
            else if (updateMode)
            {
                updateArea();
            }
        }
    };



    EventHandler<ActionEvent> handlerDelete = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("DELETE Confirmation");
            alert.setContentText("Are you sure you want to DELETE this Area?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()==ButtonType.OK)
            {
                AreasAyuntamiento areasAyuntamiento=tableAreasAyuntamiento.getSelectionModel().getSelectedItem();
                AyuntamientoDAO.delete(areasAyuntamiento.getNombreArea());
                reloadArea();
            }
            else{
                alert.close();
            }
        }
    };
    private void insertArea()
    {
        AreasAyuntamiento areasayuntamiento= new AreasAyuntamiento(
                txtidArea.getText(),
                txtnombreArea.getText());


        if(ayuntamientoDAO.insert(areasayuntamiento))
        {
            reloadArea();
            clearForm();
        }

    }


    private void updateArea()
    {
        AreasAyuntamiento areasayuntamiento=new AreasAyuntamiento(
                txtidArea.getText(),
                txtnombreArea.getText());

        if(ayuntamientoDAO.update(areasayuntamiento)){
            reloadArea();
            clearForm();
        }
    }

    private void reloadArea()
    {
        tableAreasAyuntamiento.getItems().clear();
        tableAreasAyuntamiento.setItems(ayuntamientoDAO.fetchAll());
    }

    private void clearForm()
    {
        txtidarea.setText("");
        txtnombreArea.setText("");
    }






}
