package Recepcion.Documento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import sample.ConsultaDocumentos;

import java.sql.*;


public class DocumentoDAO {


    public Boolean insert(Documento Document) {
        try {
            String query = "insert into documento(numFolio, idRol, idDestinatario, idProcedencia, adjuntar,entregado) values (?,?,?,?,?,?);";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, Document.getNumFolio());
            st.setInt(2, Document.getIdRol());
            st.setInt(3, Document.getIdDestinatario());
            st.setInt(4, Document.getIdProcedencia());
            st.setBoolean(5, Document.isAdjuntar());
            st.setBoolean(6, Document.isEntregado());
            st.execute();
            return true;
        }catch (SQLIntegrityConstraintViolationException ex) {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Este folio ya esta registrado" + "\n\n" + ex);
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean delete(int idDoc, int numFolio, int idDest, int idProc) {
        try {
            PreparedStatement st;
            String query = "delete from detalledocumento where idDocumento=?;";
            st = conn.prepareStatement(query);
            st.setInt(1, idDoc);
            st.execute();
            String query2 = "delete from documento where numFolio=?;";
            st = conn.prepareStatement(query2);
            st.setInt(1, numFolio);
            st.execute();
            String query3 = "delete from informaciondestinatario where idDestinatario=?;";
            st = conn.prepareStatement(query3);
            st.setInt(1, idDest);
            st.execute();
            String query4 = "delete from informacionprocedencia where idProcedencia=?;";
            st = conn.prepareStatement(query4);
            st.setInt(1, idProc);
            st.execute();
            return true;

        } catch (Exception e) {
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

    public ObservableList<ConsultaDocumentos> AllDocs() {
        ObservableList<ConsultaDocumentos> consultaDocumentos = FXCollections.observableArrayList();
        try {
            String query = " select det.idDocumento, iD.idDestinatario,iP.idProcedencia,det.numFolio, det.numDocumento,i.nombreInstitucion, det.fechaRecepcion, iP.observaciones" +
                    " from informacionDestinatario iD inner join documento d on iD.idDestinatario = d.idDestinatario" +
                    " inner join detalleDocumento det on d.numFolio = det.numFolio" +
                    " inner join informacionProcedencia iP on d.idProcedencia = iP.idProcedencia" +
                    " inner join institucionProcedencia i on iP.idInstitucion = i.idInstitucion;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ConsultaDocumentos p = null;
            while(rs.next()) {
                p = new ConsultaDocumentos(
                        rs.getInt("det.idDocumento"),rs.getInt("iD.idDestinatario"),
                        rs.getInt("iP.idProcedencia"),
                        rs.getInt("det.numFolio"), rs.getInt("det.numDocumento"),
                        rs.getString("i.nombreInstitucion"),rs.getDate("det.fechaRecepcion"),
                        rs.getString("iP.observaciones")
                );
                consultaDocumentos.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return consultaDocumentos;
    }

}
