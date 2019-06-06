package sample;

import Login.Municipio;
import Login.Rol;
import Login.Usuarios;
import Login.UsuariosDAO;
import Recepcion.Destinatario.Destinatario;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;


public class recepcionAdminController implements Initializable {

    @FXML
    TextField txtIdUser,txtNombre;
    @FXML
    PasswordField txtContraseña;

    @FXML
    ComboBox<Municipio> cmbMunicipio;

    @FXML
    MenuItem SignOff;
    @FXML
    MenuItem instituciones;
    @FXML
    MenuItem menuAreas,menuTipodoc,menuTipoforma;

    @FXML
    Button btnUsuarios,btnNuevo,btnGuardar,btnBorrar,btnExit;

    @FXML
    TableView<Usuarios> tblUsuarios;

    @FXML
    Label lblPass;



    UsuariosDAO usuariosDAO = new UsuariosDAO(MySQLConnection.getConnection());
    LoginDAO loginDAO = new LoginDAO(MySQLConnection.getConnection());
    private boolean insertMode=false;
    private boolean updateMode=false;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComponents();
        btnNuevo.setOnAction(handlerNuevo);
        btnGuardar.setOnAction(handlerGuardar);
        btnBorrar.setOnAction(handlerDelete);
        SignOff.setOnAction(CerrarSesion);
        instituciones.setOnAction(Instituciones);
        menuAreas.setOnAction(WindowAreasAyuntamiento);
        menuTipodoc.setOnAction(WindowTipoDocumento);
        menuTipoforma.setOnAction(WindowTipoFormato);
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMACION DE SALIDA");
                alert.setContentText("ESTAS SEGURO QUE QUIERES SALIR?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get()==ButtonType.OK)
                {
                    System.exit(0);
                }
                else{
                    alert.close();
                }
            }
        });

    }

    private void initComponents()
    {
        TableColumn col1 = new TableColumn("ID USUARIO");
        TableColumn col2 = new TableColumn("NOMBRE ");
        TableColumn col3 = new TableColumn("ROL");
        TableColumn col4 = new TableColumn("MUNICIPIO");

        col1.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        col2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col3.setCellValueFactory(new PropertyValueFactory<>("tipoRol"));
        col4.setCellValueFactory(new PropertyValueFactory<>("nombreMunicipio"));

        tblUsuarios.getColumns().addAll(col1, col2, col3, col4);
        tblUsuarios.setItems(usuariosDAO.findUsuarios());
        cmbMunicipio.setItems(loginDAO.fetchMunicipios());
        tblUsuarios.setOnMouseClicked(handlertableUpd);

    }

    private void insertUser()
    {
        Usuarios usuarios = new Usuarios(
                Integer.valueOf(txtIdUser.getText()),
                txtNombre.getText(),
                txtContraseña.getText(),
                2,
                usuariosDAO.findIdMunicipio(cmbMunicipio.getSelectionModel().getSelectedItem().getNombreMunicipio()).getIdMunicipio());


        if(usuariosDAO.insert(usuarios))
        {
            reloadUserList();
            clearFormUser();
        }

    }

    private void updateUser()
    {
        Usuarios usuarios=new Usuarios(
                Integer.valueOf(txtIdUser.getText()),
                txtNombre.getText(),
                2,
                usuariosDAO.findIdMunicipio(cmbMunicipio.getSelectionModel().getSelectedItem().getNombreMunicipio()).getIdMunicipio());



        if(usuariosDAO.update(usuarios)){
            reloadUserList();
            clearFormUser();
        }
    }

    EventHandler<ActionEvent> handlerNuevo = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            insertMode=true;
            updateMode=false;
            insertUser();
            clearFormUser();
        }
    };

    EventHandler<ActionEvent> handlerGuardar = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (updateMode)
            {
                updateUser();
            }
        }
    };

    EventHandler<MouseEvent> handlertableUpd = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 2) {
                Usuarios usuarios = tblUsuarios.getSelectionModel().getSelectedItem();
                updateMode = true;
                insertMode = false;
                txtIdUser.setText(String.valueOf(usuarios.getIdUsuario()));
                lblPass.setVisible(false);
                txtContraseña.setVisible(false);
                txtNombre.setText(usuarios.getNombre());

            }
        }
    };

    EventHandler<ActionEvent> handlerDelete = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMACION DE BORRADO");
            alert.setContentText("Esta seguro de eliminar este usuario?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()==ButtonType.OK)
            {
                Usuarios usuarios=tblUsuarios.getSelectionModel().getSelectedItem();
                usuariosDAO.delete(usuarios.getIdUsuario());
                clearFormUser();
                reloadUserList();
            }
            else{
                alert.close();
            }
        }
    };

    private void reloadUserList()
    {
        tblUsuarios.getItems().clear();
        tblUsuarios.setItems(usuariosDAO.findUsuarios());
    }

    private void clearFormUser()
    {
        txtIdUser.setText("");
        txtNombre.setText("");
        lblPass.setVisible(true);
        txtContraseña.setVisible(true);
        txtContraseña.setText("");
        cmbMunicipio.valueProperty().setValue(null);

    }

    EventHandler<ActionEvent> CerrarSesion = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == SignOff) {
                try {
                    Login();
                    btnNuevo.getGraphic().getScene().getWindow().hide();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    EventHandler<ActionEvent> Instituciones = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == instituciones) {
                try {
                    showStageInstitucionProcedencia();
                    btnNuevo.getGraphic().getScene().getWindow().hide();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    public void Login() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage st = new Stage();
        st.setTitle("LOGIN");

        Scene scene = new Scene(root, 700, 400);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);

        st.setResizable(false);
        st.show();
    }

    public void showStageInstitucionProcedencia() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Procedencia/procedencia.fxml"));
        Stage st = new Stage();
        st.setTitle("ADMINISTRA INSTITUCIONES PROCEDENCIA");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.initStyle( StageStyle.TRANSPARENT );
        st.setMaximized(true);
        st.show();
    }

    public void showStageAreas() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AreasAyuntamiento/Ayuntamiento.fxml"));
        Stage st = new Stage();
        st.setTitle("ADMINISTRA AREAS DE AYUNTAMIENTO");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.initStyle( StageStyle.TRANSPARENT );
        st.setMaximized(true);
        st.show();
    }

    public void showStageTipoDoc() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tipoDocumento/tipoDocumento.fxml"));
        Stage st = new Stage();
        st.setTitle("ADMINISTRA TIPO DOCUMENTO");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.initStyle( StageStyle.TRANSPARENT );
        st.setMaximized(true);
        st.show();
    }

    public void showStageTipoFormato() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Formato/formato.fxml"));
        Stage st = new Stage();
        st.setTitle("Consultas");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.initStyle( StageStyle.TRANSPARENT );
        st.setMaximized(true);
        st.show();
    }
    EventHandler<ActionEvent> WindowAreasAyuntamiento= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == menuAreas) {
                try {
                    showStageAreas();
                    btnNuevo.getGraphic().getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    EventHandler<ActionEvent> WindowTipoDocumento = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == menuTipodoc) {
                try {
                    showStageTipoDoc();
                    btnNuevo.getGraphic().getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    EventHandler<ActionEvent> WindowTipoFormato = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == menuTipoforma) {
                try {
                    showStageTipoFormato();
                    btnNuevo.getGraphic().getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

}





