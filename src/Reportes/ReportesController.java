package Reportes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.GeneracionReporte;
import sample.MySQLConnection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ReportesController implements Initializable {

    /*número de folio, fecha de recepción, institución de procedencia
    y destinatario, así también un campo para firma de recepción.*/


    @FXML
    Button filtrar,generarreporte ;

   @FXML
   CheckBox documentos,fecha;

    @FXML
    TableView<Reportes> tablereportes;
    @FXML
    DatePicker date;




    ReportesDAO reportesDAO = new ReportesDAO(MySQLConnection.getConnection());
    public static final String DEST1 = "results/Reportes/ReportePorFecha.pdf";
    public static final String DEST2 = "results/Reportes/ReportePorFechaYEntregados.pdf";
    public static final String DEST3 = "results/Reportes/TodosLosReportes.pdf";
    public static final String DEST4 = "results/Reportes/ReporteEntregados.pdf";


    public void initialize(URL location, ResourceBundle resources) {
        initComponents();
    }
    private void initComponents()
    {
        TableColumn col1 = new TableColumn("Numero de Folio");
        TableColumn col2 = new TableColumn("Fecha de Recepcion");
        TableColumn col3 = new TableColumn("Insititucion de Procedencia");
        TableColumn col4 = new TableColumn("Destinatario");
        TableColumn col5 = new TableColumn("Firma de Recidido");


        col1.setCellValueFactory(new PropertyValueFactory<>("numfolio"));
        col2.setCellValueFactory(new PropertyValueFactory<>("nombreInstitucion"));
        col3.setCellValueFactory(new PropertyValueFactory<>("quienRecibe"));
        col4.setCellValueFactory(new PropertyValueFactory<>("fechaRecepcion"));
        tablereportes.getColumns().addAll(col1, col2,col3,col4);
        tablereportes.setItems(reportesDAO.fetchAll());
        tablereportes.refresh();

        filtrar.setOnAction(handlerHabilitar);
        generarreporte.setOnAction(handlergenerarReporte);





    }
private void habilitar()
{
    if(fecha.isSelected()&& documentos.isSelected()) {
        tablereportes.getItems().clear();
        String dates = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        tablereportes.setItems(reportesDAO.fech3(dates));
        tablereportes.refresh();

    }else if (fecha.isSelected())
    {
        tablereportes.getItems().clear();
        String dates = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        tablereportes.setItems(reportesDAO.fech(dates));
        tablereportes.refresh();

    }else if(documentos.isSelected())
    {
        tablereportes.getItems().clear();
        tablereportes.setItems(reportesDAO.fech2());
        tablereportes.refresh();

    }else
    {
        tablereportes.getItems().clear();
        tablereportes.setItems(reportesDAO.fetchAll());
        tablereportes.refresh();
    }


}
private void reporte(){
        if (fecha.isSelected() && documentos.isSelected()){
            File file = new File(DEST2);
            file.getParentFile().mkdirs();
            try {
                ReportesDAO reporte = new ReportesDAO(MySQLConnection.getConnection());
                String dates = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                new GeneracionReporte().createPdf(DEST2,reporte.fech3(dates));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if (fecha.isSelected())
        {
            File file = new File(DEST1);
            file.getParentFile().mkdirs();
            try {
                ReportesDAO reporte = new ReportesDAO(MySQLConnection.getConnection());
                String dates = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                new GeneracionReporte().createPdf(DEST1,reporte.fech(dates));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(documentos.isSelected())
        {
            File file = new File(DEST4);
            file.getParentFile().mkdirs();
            try {
                ReportesDAO reporte = new ReportesDAO(MySQLConnection.getConnection());

                new GeneracionReporte().createPdf(DEST4,reporte.fech2());
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else
        {
            File file = new File(DEST3);
            file.getParentFile().mkdirs();
            try {
                ReportesDAO reporte = new ReportesDAO(MySQLConnection.getConnection());
                new GeneracionReporte().createPdf(DEST3,reporte.fetchAll());
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

}


    EventHandler<ActionEvent> handlerHabilitar = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

                habilitar();

        }
    };
    EventHandler<ActionEvent> handlergenerarReporte = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            reporte();

        }
    };




}
