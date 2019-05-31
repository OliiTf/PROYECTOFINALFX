package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportesController implements Initializable {

    /*número de folio, fecha de recepción, institución de procedencia
    y destinatario, así también un campo para firma de recepción.*/


    @FXML
    Button filtrar ;

   @FXML
   CheckBox documentos,fecha;

    @FXML
    TableView<Reportes> tablereportes;
    @FXML
    DatePicker date;




    ReportesDAO reportesDAO = new ReportesDAO(MySQLConnection.getConnection());


    public void initialize(URL location, ResourceBundle resources) {
        initComponents();
    }
    private void initComponents()
    {
        TableColumn col1 = new TableColumn("Numero de Folio");
        TableColumn col2 = new TableColumn("Fecha de Recepcion");
        TableColumn col3 = new TableColumn("Insititucion de Procedencia");
        TableColumn col4 = new TableColumn("Destinatario");

        col1.setCellValueFactory(new PropertyValueFactory<>("numfolio"));
        col2.setCellValueFactory(new PropertyValueFactory<>("nombreInstitucion"));
        col3.setCellValueFactory(new PropertyValueFactory<>("quienRecibe"));
        col4.setCellValueFactory(new PropertyValueFactory<>("fechaRecepcion"));
        tablereportes.getColumns().addAll(col1, col2,col3,col4);
        tablereportes.setItems(reportesDAO.fetchAll());
        tablereportes.refresh();

        filtrar.setOnAction(handlerHabilitar);





    }
private void habilitar()
{
    tablereportes.getItems().clear();
    tablereportes.setItems(reportesDAO.fech(date));
    tablereportes.refresh();
    System.out.println(date);

}


    EventHandler<ActionEvent> handlerHabilitar = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

                habilitar();

        }
    };





/*

    private void reloadAreaList()
    {
        tablereportes.getItems().clear();
        tablereportes.setItems(ayuntamientoDAO.fetchAll());
    }

    private void clearForm()
    {
        txtidArea.setText("");
        txtnombreArea.setText("");
    }

*/
}
