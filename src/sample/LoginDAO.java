package sample;

import Login.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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


    public List<Configuracion> findConfig(int  id_mun) {
        List<Configuracion> configList = new ArrayList<Configuracion>();
        try {
            String query = "select c.nombreJefe, c.direccion, c.telefono, c.horarioInicio, c.horarioSalida, m.nombreEstado" +
                    " from configuracion c inner join municipio m on c.idMunicipio = m.idMunicipio where c.idMunicipio = '" + id_mun + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Configuracion p = null;
            while(rs.next()) {
                p = new Configuracion(
                        rs.getString("c.nombreJefe"), rs.getString("c.direccion"),
                        rs.getString("c.telefono"), rs.getString("c.horarioInicio"),
                        rs.getString("c.horarioSalida"), rs.getString("m.nombreEstado")
                );
                configList.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return configList;

    }

    public Usuarios finIdRol(String  nom_user) {
        Usuarios p = new Usuarios();
        try {
            String query = "select u.idRol from usuario u" +
                    " inner join roles r on u.idRol = r.idRol where u.nombre='" + nom_user + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                p = new Usuarios(
                        rs.getInt("u.idRol")
                );

            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return p;

    }

    public UsuarioByMun findIdMunicipioUser(String  nom_user) {
        UsuarioByMun p = new UsuarioByMun();
        try {
            String query = "select u.idMunicipio from usuario u" +
                    " inner join municipio m on u.idMunicipio = m.idMunicipio where u.nombre='" + nom_user + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                p = new UsuarioByMun(
                        rs.getInt("u.idMunicipio")
                );

            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return p;

    }

    public Municipio findIdMunicipio(int  mun) {
        Municipio p = new Municipio();
        try {
            String query = "select idMunicipio from municipio" +
                    " where idMunicipio='" + mun + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                p = new Municipio(
                        rs.getInt("idMunicipio")
                );

            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return p;

    }

    public List<Configuracion> findIdConf() {
        List<Configuracion> configList = new ArrayList<Configuracion>();
        try {
            String query = "select idMunicipio from configuracion;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Configuracion p = null;
            while(rs.next()) {
                p = new Configuracion(
                        rs.getInt("idMunicipio")
                );
                configList.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        System.out.println(configList);
        return configList;

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
