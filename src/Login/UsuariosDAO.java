package Login;

import Procedencia.InstitucionProcedencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class UsuariosDAO {

    Connection conn;
    public UsuariosDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<Usuarios> findUsuarios() {
        ObservableList<Usuarios> usuarios = FXCollections.observableArrayList();
        try {
            String query = " select u.idUsuario, u.nombre, r.tipoRol, m.nombreMunicipio" +
                    " from usuario u inner join municipio m on u.idMunicipio = m.idMunicipio" +
                    " inner join roles r on u.idRol = r.idRol order by u.idUsuario;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Usuarios p = null;
            while(rs.next()) {
                p = new Usuarios(
                        rs.getInt("u.idUsuario"),rs.getString("u.nombre"),
                        rs.getString("r.tipoRol"),rs.getString("m.nombreMunicipio")
                );
                usuarios.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return usuarios;
    }


    public Boolean delete(int id_user) {
        try {
            String query = "delete from usuario where idUsuario = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id_user);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Municipio findIdMunicipio(String  mun) {
        Municipio p = new Municipio();
        try {
            String query = "select idMunicipio from municipio" +
                    " where nombreMunicipio='" + mun + "'";
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
    public Boolean insert(Usuarios usuarios) {
        try {
            String query = "insert into usuario"
                    + " (idUsuario, nombre, contraseña, idRol, idMunicipio)"
                    + " values (?, ?, md5(?), ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(   1, usuarios.getIdUsuario());
            st.setString(  2, usuarios.getNombre());
            st.setString(3, usuarios.getContraseña());
            st.setInt(4, usuarios.getIdRol());
            st.setInt(5, usuarios.getIdMunicipio());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
    public Boolean update(Usuarios usuarios) {
        try {
            String query = "update usuario "
                    + " set nombre = ?, idRol = ?, idMunicipio = ?"
                    + " where idUsuario=?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, usuarios.getNombre());
            st.setInt(2,usuarios.getIdRol());
            st.setInt(3, usuarios.getIdMunicipio());
            st.setInt(   4, usuarios.getIdUsuario());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
