/*
package sample;



import Recepcion.Formato;
import Recepcion.Tipo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecepcionDAO {

    Connection conn;
    public RecepcionDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<Formato> fetchFormato() {
        ObservableList<Formato> formato = FXCollections.observableArrayList();
        try {
            String query = "select nombreFormato from formato";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Formato p = null;
            while(rs.next()) {
                p = new Formato(
                        rs.getString("nombreFormato")
                );
                formato.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return formato;
    }

    public ObservableList<Tipo> fetchTipo() {
        ObservableList<Tipo> tipo= FXCollections.observableArrayList();
        try {
            String query = "select nombreTipoDoc from tipodocumento";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Tipo p = null;
            while(rs.next()) {
                p = new Tipo(
                        rs.getString("nombreTipoDoc")
                );
                tipo.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return tipo;
    }
}
*/