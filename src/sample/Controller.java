package sample;

import Login.*;
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
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    ComboBox<Rol> cmbRol;

    @FXML
    ComboBox<Municipio> cmbMun;

    @FXML
    Label  lblMun, lblDir;

    @FXML
    TextField txtUsuario, txtPass;

    @FXML
    Button btnAccept, btnCancel;

    Usuarios users;


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

        cmbMun.setItems(loginDAO.fetchMunicipios());
        boolean disable = !cmbMun.isDisable();
        boolean enable = cmbMun.isDisable();
        cmbMun.setDisable(disable);

        cmbRol.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(cmbRol.getSelectionModel().getSelectedIndex()==0) {
                    cmbMun.setVisible(false);
                    lblMun.setVisible(false);
                    lblDir.setVisible(false);

                } else
                {
                    cmbMun.setDisable(enable);
                    cmbMun.setVisible(true);
                    lblMun.setVisible(true);
                    lblDir.setVisible(true);
                }
            }
        });



        cmbMun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lblMun.setText("");
                lblDir.setText("");
                Municipio municipio = cmbMun.getSelectionModel().getSelectedItem();
                lblMun.setText(municipio.getNombreMunicipio());


                List<Configuracion> ConfIdMun = loginDAO.findIdConf();

                for (int i=1; i<=ConfIdMun.size(); i++) {
                    if (cmbMun.getSelectionModel().getSelectedIndex() + 1 == i) {
                        List<Configuracion> configuracionList = loginDAO.findConfig(i);
                        lblDir.setText(configuracionList.toString());

                    }
                }
            }
        });





    }

    public void validarUsuarios() throws IOException {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        Alert alertUser= new Alert(Alert.AlertType.INFORMATION);
        Alert alertMun= new Alert(Alert.AlertType.INFORMATION);

      users = loginDAO.finIdRol(txtUsuario.getText());


       UsuarioByMun idMunByUser = loginDAO.findIdMunicipioUser(txtUsuario.getText());
       Municipio municipio =loginDAO.findIdMunicipio(cmbMun.getSelectionModel().getSelectedIndex()+1);




                if (loginDAO.validUser(txtUsuario.getText(), txtPass.getText())) {
                    if (users.getIdRol()==1)
                    {
                        if(users.getIdRol() == cmbRol.getSelectionModel().getSelectedIndex()+1) {
                            if (idMunByUser.getIdMunicipio() == municipio.getIdMunicipio()) {
                                showStageAdmin();
                                ((Stage) (btnAccept.getScene().getWindow())).hide();//mediante el boton aceptar accedemos a la escena despues
                            }else
                            {
                                alertMun.setContentText("Este usuario no pertenece a este Municipio");
                                alertMun.show();
                            }
                        }else
                        {
                            alertUser.setContentText("Este usuario no tiene ese rol");
                            alertUser.show();
                        }
                    } else if (users.getIdRol()==2)
                    {
                        if(users.getIdRol() == cmbRol.getSelectionModel().getSelectedIndex()+1) {
                            if (idMunByUser.getIdMunicipio() == municipio.getIdMunicipio()) {
                                showStageCapturista();
                                ((Stage) (btnAccept.getScene().getWindow())).hide();//mediante el boton aceptar accedemos a la escena despues
                            }else
                            {
                                alertMun.setContentText("Este usuario no pertenece a este Municipio");
                                alertMun.show();
                            }
                        }else
                        {
                            alertUser.setContentText("Este usuario no tiene ese rol");
                            alertUser.show();
                        }
                    }
                } else {
                    alert.setContentText("Usuario Inconrrecto");
                    alert.show();
                }

        //

    }

    public void showStageAdmin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("recepcionAdmin.fxml"));
        Stage st= new Stage();
        st.setTitle("Reportes");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.setMaximized(true);
        st.show();
    }

    public void showStageCapturista() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("recepcionCapturista.fxml"));
        Stage st= new Stage();
        st.setTitle("Reportes");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        st.setScene(scene);
        st.setMaximized(true);
        st.show();
    }



}
