package Procedencia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ProcedenciaDAO {

    Connection conn;
    public ProcedenciaDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<InstitucionProcedencia> fetchAll() {
        ObservableList<InstitucionProcedencia> procedencias = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM institucionProcedencia";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            InstitucionProcedencia p = null;
            while(rs.next()) {
                p = new InstitucionProcedencia(
                        rs.getInt("idInstitucion"), rs.getString("nombreInstitucion")
                );
                procedencias.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return procedencias;
    }

    public Boolean delete(int id_proce) {
        try {
            String query = "delete from institucionProcedencia where idInstitucion = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id_proce);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(InstitucionProcedencia procedencia) {
        try {
            String query = "insert into institucionProcedencia"
                    + " (idInstitucion, nombreInstitucion)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(   1, procedencia.getIdInstitucion());
            st.setString(  2, procedencia.getNombreInstitucion());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(InstitucionProcedencia procedencia) {
        try {
            String query = "update institucionProcedencia "
                    + " set nombreInstitucion = ?"
                    + " where idInstitucion=?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, procedencia.getNombreInstitucion());
            st.setInt(   2, procedencia.getIdInstitucion());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
