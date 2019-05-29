package AreasAyuntamiento;

import AreasAyuntamiento.AreasAyuntamiento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AyuntamientoDAO {

    Connection conn;

    public AyuntamientoDAO(Connection conn) {
        this.conn = conn;
    }

    public ObservableList<AreasAyuntamiento> fetchAll() {
        ObservableList<AreasAyuntamiento> ayuntamiento = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM areasAyuntamiento";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            AreasAyuntamiento p = null;
            while (rs.next()) {
                p = new AreasAyuntamiento(
                        rs.getInt("idArea"), rs.getString("nombreArea")
                );
                ayuntamiento.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return ayuntamiento;
    }
    public AreasAyuntamiento findIdArea(String  nom_area) {
        AreasAyuntamiento p = new AreasAyuntamiento();
        try {
            String query = "select idArea from areasAyuntamiento where nombreArea='" + nom_area + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                p = new AreasAyuntamiento(
                        rs.getInt("idArea")
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

    public ObservableList<AreasAyuntamiento> findnombreAreas() {
        ObservableList<AreasAyuntamiento> ayuntamiento = FXCollections.observableArrayList();
        try {
            String query = "SELECT nombreArea FROM areasAyuntamiento";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            AreasAyuntamiento p = null;
            while (rs.next()) {
                p = new AreasAyuntamiento(
                        rs.getString("nombreArea")
                );
                ayuntamiento.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return ayuntamiento;
    }

    public Boolean delete(int id_area) {
        try {
            String query = "delete from areasAyuntamiento where idArea = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id_area);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(AreasAyuntamiento ayuntamiento) {
        try {
            String query = "insert into areasAyuntamiento"
                    + " (idArea, nombreArea)"
                    + " values (?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, ayuntamiento.getIdArea());
            st.setString(2, ayuntamiento.getNombreArea());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(AreasAyuntamiento ayuntamiento) {
        try {
            String query = "update areasAyuntamiento "
                    + " set nombreArea = ?"
                    + " where idArea=?";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, ayuntamiento.getNombreArea());
            st.setInt(2, ayuntamiento.getIdArea());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}