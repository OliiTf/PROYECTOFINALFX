package AreasAyuntamiento;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.MySQLConnection;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AyuntamientoController implements Initializable {
    @FXML
    Button  btnNew, btnSave, btnDelete;

    @FXML
    TextField txtidArea, txtnombreArea;

    @FXML
    TableView<AreasAyuntamiento> tblAreasAyuntamiento;

    AyuntamientoDAO ayuntamientoDAO = new AyuntamientoDAO(MySQLConnection.getConnection());
    private boolean insertMode=false;
    private boolean updateMode=false;


    public void initialize(URL location, ResourceBundle resources) {
        initComponents();
    }
    private void initComponents()
    {
        TableColumn col1 = new TableColumn("Id Institucion");
        TableColumn col2 = new TableColumn("Nombre Institucion");



        col1.setCellValueFactory(new PropertyValueFactory<>("idInstitucion"));
        col2.setCellValueFactory(new PropertyValueFactory<>("nombreInstitucion"));

        tblAreasAyuntamiento.getColumns().addAll(col1, col2);
        tblAreasAyuntamiento.setItems(ayuntamientoDAO.fetchAll());
        tblAreasAyuntamiento.refresh();

        btnNew.setOnAction(handlerNew);
        tblAreasAyuntamiento.setOnMouseClicked(handlerTable);
        btnSave.setOnAction(handlerSave);
        btnDelete.setOnAction(handlerDelete);

    }

    EventHandler<ActionEvent> handlerNew = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            insertMode=true;
            updateMode=false;
            insertArea();
            clearForm();
        }
    };

    EventHandler<MouseEvent> handlerTable = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 2) {
                updateMode = true;
                insertMode = false;
                AreasAyuntamiento ayuntamiento = tblAreasAyuntamiento.getSelectionModel().getSelectedItem();
                txtidArea.setText(String.valueOf(ayuntamiento.getIdArea()));
                txtnombreArea.setText(ayuntamiento.getNombreArea());
            }
        }
    };


    EventHandler<ActionEvent> handlerSave = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (updateMode)
            {
                updateArea();
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
                AreasAyuntamiento ayuntamiento=tblAreasAyuntamiento.getSelectionModel().getSelectedItem();
                ayuntamientoDAO.delete(ayuntamiento.getIdArea());
                clearForm();
                reloadAreaList();
            }
            else{
                alert.close();
            }
        }
    };

    private void insertArea()
    {
        AreasAyuntamiento ayuntamiento = new AreasAyuntamiento(
                Integer.valueOf(txtidArea.getText()),
                txtnombreArea.getText());

        if(ayuntamientoDAO.insert(ayuntamiento))
        {
            reloadAreaList();
            clearForm();
        }

    }

    private void updateArea()
    {
        AreasAyuntamiento ayuntamiento=new AreasAyuntamiento(
                Integer.valueOf(txtidArea.getText()),
                txtnombreArea.getText());

        if(ayuntamientoDAO.update(ayuntamiento)){
            reloadAreaList();
            clearForm();
        }
    }

    private void reloadAreaList()
    {
        tblAreasAyuntamiento.getItems().clear();
        tblAreasAyuntamiento.setItems(ayuntamientoDAO.fetchAll());
    }

    private void clearForm()
    {
        txtidArea.setText("");
        txtnombreArea.setText("");
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