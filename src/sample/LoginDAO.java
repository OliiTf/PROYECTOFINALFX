package sample;

import Login.Estados;
import Login.Municipio;
import Login.Rol;
import Login.Usuarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {

    Connection conn;
    public LoginDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<Rol> fetchRol() {
        ObservableList<Rol> rols = FXCollections.observableArrayList();
        try {
            String query = "select tipoRol from roles";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Rol p = null;
            while(rs.next()) {
                p = new Rol(
                        rs.getString("tipoRol")
                );
                rols.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return rols;
    }

    public ObservableList<Estados> fetchEstados() {
        ObservableList<Estados> estados = FXCollections.observableArrayList();
        try {
            String query = "select nombreEstado from estados";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Estados p = null;
            while(rs.next()) {
                p = new Estados(
                        rs.getString("nombreEstado")
                );
                estados.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return estados;
    }

    public ObservableList<Municipio> fetchMunicipios() {
        ObservableList<Municipio> municipios = FXCollections.observableArrayList();
        try {
            String query = "select nombreMunicipio from municipio";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Municipio p = null;
            while(rs.next()) {
                p = new Municipio(
                        rs.getString("nombreMunicipio")
                );
                municipios.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return municipios;
    }

    public Boolean validUser(String username, String password) {
        ResultSet rs = null;
        int total=0;
        Usuarios e = null;
        try {
            String query = "SELECT count(*) as total from usuario where nombre = '" + username + "'" +
                    " and contraseña = md5('"+ password +"')";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            total=rs.getInt("total");

            if(total==1)
            {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return (total>=1)? true:false;
    }

}
