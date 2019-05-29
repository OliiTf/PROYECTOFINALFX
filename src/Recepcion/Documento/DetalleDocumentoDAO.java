package Recepcion.Documento;

import java.sql.*;
import Formato.formato;
import tipoDocumento.tipoDocumento;

public class DetalleDocumentoDAO {
    Connection conn;

    public DetalleDocumentoDAO(Connection conn) {
        this.conn = conn;
    }

    public DetalleDocumento countDoc() {
        DetalleDocumento p = new DetalleDocumento();
        try {
            String query = "select count(*) as idDocumento from detalledocumento;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                p = new DetalleDocumento(
                        rs.getInt("idDocumento")
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

    public Boolean insert(DetalleDocumento detDoc) {
        try {
            String query = "insert into detalleDocumento(idDocumento, numDocumento, fechaRecepcion, fechaDocumento, idFormato, idTipoDocumento, numFolio) values (?,?,?,?,?,?,?);";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, detDoc.getIdDocumento());
            st.setInt(2, detDoc.getNumDocumento());
            st.setDate(3, detDoc.getFechaRecepcion());
            st.setDate(4, detDoc.getFechaDocumento());
            st.setInt(5, detDoc.getIdFormato());
            st.setInt(6, detDoc.getIdTipoDocumento());
            st.setInt(7, detDoc.getNumFolio());



            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public formato findIdFormato(String  nom_for) {
        formato p = new formato();
        try {
            String query = "select idFormato from formato where nombreFormato='" + nom_for + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                p = new formato(
                        rs.getInt("idFormato")
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

    public tipoDocumento findIfTipoDoc(String  nom_TipoDoc) {
        tipoDocumento p = new tipoDocumento();
        try {
            String query = "select idTipoDocumento from tipoDocumento where nombreTipoDoc='" + nom_TipoDoc + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                p = new tipoDocumento(
                        rs.getInt("idTipoDocumento")
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
