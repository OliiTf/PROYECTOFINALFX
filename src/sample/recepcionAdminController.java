package sample;

import Recepcion.Destinatario.DestinatarioDAO;
import Recepcion.Destinatario.Instruccion;
import Recepcion.Destinatario.Prioridad;
import Recepcion.Documento.DocumentoDAO;
import Recepcion.Documento.Formato;
import Recepcion.Documento.Tipo;
import Recepcion.Procedencia.Institucion;
import Recepcion.Procedencia.ProcedenciaDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;


public class recepcionAdminController implements Initializable {

    @FXML
    MenuItem SignOff;
    @FXML
    MenuItem instituciones;
    @FXML
    MenuItem areas;
    @FXML
    MenuItem tipodoc;
    @FXML
    MenuItem tipoforma;

    @FXML
    TextField txtFolio, txtNoDoc,txtIdDoc,txtIdDestinatario,txtQuienRecibe,txtareaayuntamiento,txtprocedencia,txtfirma,txtpuesto,txtdirigido,txtasunto,txtobservaciones;
    @FXML
    DatePicker dpFechaDoc, dpFechaRecep,dpfechalimite,dpfechaentrega;
    @FXML
    ComboBox<Formato> cmbFormato;
    @FXML
    ComboBox<Tipo> cmbTipo;
    @FXML
    Button BtnRecepcion;
    @FXML
    Button BtnReportes;
    @FXML
    Button BtnConsultas;
    @FXML
    ComboBox<Instruccion>  cmbinstruccion;
    @FXML
    ComboBox<Prioridad> cmbprioridad;
    @FXML
    ComboBox<Institucion> cmbinstitucion;
    @FXML
    Menu menuCatalogos;


    DocumentoDAO Documento = new DocumentoDAO(MySQLConnection.getConnection());
    //DestinatarioDAO Destinatario = new DestinatarioDAO(MySQLConnection.getConnection());
    ProcedenciaDAO Procedencia= new ProcedenciaDAO(MySQLConnection.getConnection());


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initLogin();

    }


}


