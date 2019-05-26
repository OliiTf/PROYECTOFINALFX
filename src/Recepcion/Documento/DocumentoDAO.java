package Recepcion.Documento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DocumentoDAO {


    public Boolean insert(DocumentoInsert Document) {
        try {
            String query = "insert into detalledocumento(idDocumento, numDocumento, fechaRecepcion, " +
                    "fechaDocumento, idFormato, idTipoDocumento, numFolio) values (?,?,?,?,?,?,?);\n";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, Document.getIddocumento());
            st.setInt(2, Document.getTxtnodoc());
            st.setDate(3, Document.getDpfechasrecep());
            st.setDate(4, Document.getDpfechadoc());
            st.setInt(5,Document.getCmbformato() );
            st.setInt(6, Document.getCmbtipo());
            st.setInt(7, Document.getTxtfolio());


            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    Connection conn;

    public DocumentoDAO(Connection conn) {
        this.conn = conn;
    }


    public ObservableList<Formato> fetchFormato() {
        ObservableList<Formato> Formato = FXCollections.observableArrayList();
        try {
            String query = "select formato.nombreFormato from formato";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Formato p = null;
            while (rs.next()) {
                p = new Formato(
                        rs.getString("nombreFormato")
                );
                Formato.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Formato;
    }




    public ObservableList<Tipo> fetchTipo() {
        ObservableList<Tipo> Tipo = FXCollections.observableArrayList();
        try {
            String query = "select tipodocumento.nombreTipoDoc from tipodocumento";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Tipo p = null;
            while (rs.next()) {
                p = new Tipo(
                        rs.getString("nombreTipoDoc")
                );
                Tipo.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Tipo;
    }
}
