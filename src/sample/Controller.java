package sample;

import Login.Estados;
import Login.Municipio;
import Login.Rol;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    ComboBox<Rol> cmbRol;

    @FXML
    ComboBox<Estados> cmbEdo;

    @FXML
    ComboBox<Municipio> cmbMun;

    @FXML
    Label lblEdo, lblMun;

    @FXML
    TextField txtUsuario, txtPass;

    @FXML
    Button btnAccept, btnCancel;



    LoginDAO loginDAO = new LoginDAO(MySQLConnection.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            initLogin();
            btnAccept.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        validarUsuarios();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

    }

    private void initLogin()
    {
        cmbRol.setItems(loginDAO.fetchRol());
        cmbEdo.setItems(loginDAO.fetchEstados());
        cmbMun.setItems(loginDAO.fetchMunicipios());

        cmbEdo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Estados estados = cmbEdo.getSelectionModel().getSelectedItem();
                lblEdo.setText(estados.getNombreEstado());

            }
        });


    }

    public void validarUsuarios() throws IOException {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        if(loginDAO.validUser(txtUsuario.getText(),txtPass.getText()))
        {

            showStage();
            ((Stage)(btnAccept.getScene().getWindow())).hide();//mediante el boton aceptar accedemos a la escena despues a la ventana y lo convierte a Stage
        }
        else

        {
            alert.setContentText("Usuario Inconrrecto");
            alert.show();
        }

    }

    public void showStage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reportes.fxml"));
        Stage st= new Stage();
        st.setTitle("Reportes");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);

        st.setMaximized(true);
        st.show();
    }
}
