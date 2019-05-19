package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class consultController implements Initializable {

    @FXML
    TableView tblConsulta;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn col1 = new TableColumn("FOLIO");
        TableColumn col2 = new TableColumn("FECHA RECEPCION");
        TableColumn col3 = new TableColumn("PROCEDENCIA");
        TableColumn col4 = new TableColumn("NO. DOCUMENTO");
        TableColumn col5 = new TableColumn("OBSERVACIONES");



        col1.setCellValueFactory(new PropertyValueFactory<>("noFolio"));
        col2.setCellValueFactory(new PropertyValueFactory<>("fechaRecepcion"));
        col3.setCellValueFactory(new PropertyValueFactory<>("procedencia"));
        col4.setCellValueFactory(new PropertyValueFactory<>("noDocumento"));
        col5.setCellValueFactory(new PropertyValueFactory<>("Observaciones"));


        tblConsulta.getColumns().addAll(col1, col2, col3, col4, col5);

    }
}
