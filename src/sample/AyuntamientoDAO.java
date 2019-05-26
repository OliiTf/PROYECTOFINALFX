package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AyuntamientoDAO {

    public static ObservableList<AreasAyuntamiento> fetchAll() {
        ObservableList<AreasAyuntamiento> areasayuntamiento = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM areasayuntamiento order by idArea";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            AreasAyuntamiento p = null;
            while(rs.next()) {
                p = new AreasAyuntamiento(
                        rs.getInt("idArea"), rs.getString("nombreArea")
                );
                areasayuntamiento.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return areasayuntamiento;
    }

    public ObservableList<AreasAyuntamiento> fetchAllArea() {
        ObservableList<AreasAyuntamiento> areasayuntamiento = FXCollections.observableArrayList();
        try {
            String query = "SELECT idArea FROM areasayuntamiento";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            AreasAyuntamiento p = null;
            while(rs.next()) {
                p = new AreasAyuntamiento(
                        rs.getInt("idArea")
                );
                areasayuntamiento.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return areasayuntamiento;
    }

    Connection conn;
    public AyuntamientoDAO(Connection conn)
    {
        this.conn = conn;
    }

    public List<AreasAyuntamiento> findAll() {
        List<AreasAyuntamiento> areasayuntamiento = new ArrayList<AreasAyuntamiento>();
        try {
            String query = "SELECT * FROM areasayuntamiento order by idArea";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            AreasAyuntamiento p = null;
            while(rs.next()) {
                p= new AreasAyuntamiento(
                        rs.getInt("idArea"), rs.getString("nombreAyuntamiento")
                );
                areasayuntamiento.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return areasayuntamiento;
    }


    public Boolean delete(int AreaId) {
        try {
            String query = "delete from areasayuntamiento where idArea = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, AreaId);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(AreasAyuntamiento areasayuntamiento) {
        try {
            String query = "insert into areasayuntamiento "
                    + " (idArea, nombreArea)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(   1, areasayuntamiento.getIdArea());
            st.setString(  2, areasayuntamiento.getNombreArea());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(AreasAyuntamiento areasayuntamiento) {
        try {
            String query = "update areasayuntamiento "
                    + " set nombreArea = ?"
                    + " where idArea=?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setInt(  1, areasayuntamiento.getIdArea());
            st.setString(2, areasayuntamiento.getNombreArea());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
