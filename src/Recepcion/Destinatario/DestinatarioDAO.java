package Recepcion.Destinatario;

import Recepcion.Documento.DocumentoInsert;
import Recepcion.Documento.Formato;
import Recepcion.Documento.Tipo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DestinatarioDAO{



    public Boolean insert(DestinatarioInsert Document) {
        try {
            String query = "insert into informaciondestinatario(idDestinatario, fechaLimite, fechaEntrega," +
                    " quienRecibe, idArea, idInstruccion, idPrioridad) values (?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, Document.getIdDestinatario());
            st.setDate(2, Document.getFechaLimite());
            st.setDate(3, Document.getFechaEntrega());
            st.setString(4,Document.getQuienRecibe() );
            st.setInt(5, Document.getIdArea());
            st.setInt(6, Document.getIdInstruccion());
            st.setInt(7,Document.getIdPrioridad());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    Connection conn;

    public DestinatarioDAO(Connection conn) {
        this.conn = conn;
    }


    public ObservableList<Instruccion> fetchInstruccion() {
        ObservableList<Instruccion> Instruccion = FXCollections.observableArrayList();
        try {
            String query = "select instruccion.descInstruccion from instruccion";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Instruccion p = null;
            while (rs.next()) {
                p = new Instruccion(
                        rs.getString("descInstruccion")
                );
                Instruccion.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Instruccion;
    }




    public ObservableList<Prioridad> fetchPrioridad() {
        ObservableList<Prioridad> Prioridad = FXCollections.observableArrayList();
        try {
            String query = "select prioridad.descPrioridad from prioridad;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Prioridad p = null;
            while (rs.next()) {
                p = new Prioridad(
                        rs.getString("descPrioridad")
                );
                Prioridad.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Prioridad;
    }
}
