package AreasAyuntamiento;

import Procedencia.ReportProcedencia;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.MySQLConnection;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AyuntamientoController implements Initializable {
    @FXML
    Button btnNew, btnSave, btnDelete,btnReturn;

    @FXML
    MenuItem RAreas;

    @FXML
    TextField txtIdArea, txtNombreArea;

    @FXML
    TableView<AreasAyuntamiento> tblAreasAyuntamiento;

    AyuntamientoDAO ayuntamientoDAO = new AyuntamientoDAO(MySQLConnection.getConnection());
    private boolean insertMode = false;
    private boolean updateMode = false;


    public void initialize(URL location, ResourceBundle resources) {
        initComponents();
    }

    private void initComponents() {
        TableColumn col1 = new TableColumn("Id Area");
        TableColumn col2 = new TableColumn("Nombre Area");


        col1.setCellValueFactory(new PropertyValueFactory<>("idArea"));
        col2.setCellValueFactory(new PropertyValueFactory<>("nombreArea"));

        tblAreasAyuntamiento.getColumns().addAll(col1, col2);
        tblAreasAyuntamiento.setItems(ayuntamientoDAO.fetchAll());
        tblAreasAyuntamiento.refresh();

        btnNew.setOnAction(handlerNew);
        tblAreasAyuntamiento.setOnMouseClicked(handlerTable);
        btnSave.setOnAction(handlerSave);
        btnDelete.setOnAction(handlerDelete);
        btnReturn.setOnAction(handlerReturn);
        RAreas.setOnAction(handlerPDFAreas);

    }

    EventHandler<ActionEvent> handlerNew = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            insertMode = true;
            updateMode = false;
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
                txtIdArea.setText(String.valueOf(ayuntamiento.getIdArea()));
                txtNombreArea.setText(ayuntamiento.getNombreArea());
            }
        }
    };


    EventHandler<ActionEvent> handlerSave = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (updateMode) {
                updateArea();
            }
        }
    };

    public static final String DEST = "/results/Reportes/AreasAyuntamiento.pdf";
    EventHandler<ActionEvent> handlerPDFAreas = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource()==RAreas)
            {
                File file = new File(DEST);
                file.getParentFile().mkdirs();
                try {
                    new ReportAreas().createPdfAreas(DEST,ayuntamientoDAO.fetchAll());
                    Desktop.getDesktop().open(new File(DEST));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    EventHandler<ActionEvent> handlerDelete = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmacion");
            alert.setContentText("Estas seguro de eliminar este registro?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                AreasAyuntamiento ayuntamiento = tblAreasAyuntamiento.getSelectionModel().getSelectedItem();
                ayuntamientoDAO.delete(ayuntamiento.getIdArea());
                clearForm();
                reloadAreaList();
            } else {
                alert.close();
            }
        }
    };

    private void insertArea() {
        AreasAyuntamiento ayuntamiento = new AreasAyuntamiento(
                Integer.valueOf(txtIdArea.getText()),
                txtNombreArea.getText());

        if (ayuntamientoDAO.insert(ayuntamiento)) {
            reloadAreaList();
            clearForm();
        }

    }

    private void updateArea() {
        AreasAyuntamiento ayuntamiento = new AreasAyuntamiento(
                Integer.valueOf(txtIdArea.getText()),
                txtNombreArea.getText());

        if (ayuntamientoDAO.update(ayuntamiento)) {
            reloadAreaList();
            clearForm();
        }
    }

    private void reloadAreaList() {
        tblAreasAyuntamiento.getItems().clear();
        tblAreasAyuntamiento.setItems(ayuntamientoDAO.fetchAll());
    }

    private void clearForm() {
        txtIdArea.setText("");
        txtNombreArea.setText("");
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