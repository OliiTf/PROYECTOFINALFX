package Recepcion.Procedencia;

import Recepcion.Destinatario.DestinatarioInsert;
import Recepcion.Destinatario.Instruccion;
import Recepcion.Destinatario.Prioridad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ProcedenciaDAO {
    public Boolean insert(ProcedenciaInsert Document) {
        try {
            String query = "insert into informacionprocedencia(idProcedencia, quienFirma, puesto, dirigidaA," +
                    " asunto, observaciones, idInstitucion) values (?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, Document.getIdProcedencia());
            st.setString(2,Document.getQuienFirma());
            st.setString(3,Document.getPuesto());
            st.setString(4,Document.getDirigidaA());
            st.setString(5,Document.getAsunto());
            st.setString(6,Document.getObservaciones());
            st.setInt(7,Document.getIdInstitucion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    Connection conn;

    public ProcedenciaDAO(Connection conn) {
        this.conn = conn;
    }


    public ObservableList<Institucion> fetchInstituccion() {
        ObservableList<Institucion> Institucion = FXCollections.observableArrayList();
        try {
            String query = "select institucionprocedencia.nombreInstitucion from institucionprocedencia";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Institucion p = null;
            while (rs.next()) {
                p = new Institucion(
                        rs.getString("nombreInstitucion")
                );
                Institucion.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Institucion;
    }




}
