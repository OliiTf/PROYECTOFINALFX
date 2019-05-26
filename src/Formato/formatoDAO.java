package Formato;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class formatoDAO{
    Connection conn;
    public formatoDAO( Connection conn){this.conn=conn;}

    public ObservableList<formato> fetchAll() {
        ObservableList<formato> procedencias = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM formato";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            formato f = null;
            while(rs.next()) {
                f = new formato(
                        rs.getInt("idFormato"), rs.getString("nombreFormato")
                );
                procedencias.add(f);
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
            String query = "delete from formato where idFormato = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id_proce);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(formato form) {
        try {
            String query = "insert into formato"
                    + " (idFormato, nombreFormato)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(   1, form.getIdFormato());
            st.setString(  2, form.getNombreFormato());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(formato form) {
        try {
            String query = "update formato"
                    + " set nombreFormato = ?"
                    + " where idFormato=?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, form.getNombreFormato());
            st.setInt(   2, form.getIdFormato());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


}
