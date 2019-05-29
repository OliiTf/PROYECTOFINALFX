package Recepcion.Destinatario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DestinatarioDAO{

    Connection conn;

    public DestinatarioDAO(Connection conn) {
        this.conn = conn;
    }

    public Destinatario countDestinat() {
        Destinatario p = new Destinatario();
        try {
            String query = "select count(*) as idDestinatario from informacionDestinatario;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                p = new Destinatario(
                        rs.getInt("idDestinatario")
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




}
