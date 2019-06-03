package sample;

import Recepcion.Documento.Documento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class consultasDAO {

    Connection conn;
    public consultasDAO(Connection conn)
    {
        this.conn = conn;
    }

    public ObservableList<ConsultaDocumentos> fetchDocumentsProc(String nombre_institucion) {
        ObservableList<ConsultaDocumentos> consultaDocumentos = FXCollections.observableArrayList();
        try {
            String query = "select det.numFolio, det.numDocumento,i.nombreInstitucion, det.fechaRecepcion, iP.observaciones" +
                    " from detalleDocumento det" +
                    " inner join documento d on det.numFolio = d.numFolio" +
                    " inner join informacionProcedencia iP on d.idProcedencia = iP.idProcedencia" +
                    " inner join institucionProcedencia i on iP.idInstitucion = i.idInstitucion" +
                    " where i.nombreInstitucion='" + nombre_institucion + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ConsultaDocumentos p = null;
            while(rs.next()) {
                p = new ConsultaDocumentos(
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

    public ObservableList<ConsultaDocumentos> fetchDocumentsFolio(int no_Folio) {
        ObservableList<ConsultaDocumentos> consultaDocumentosFolio = FXCollections.observableArrayList();
        try {
            String query = "select det.numFolio, det.numDocumento,i.nombreInstitucion, det.fechaRecepcion, iP.observaciones" +
                    " from detalleDocumento det" +
                    " inner join documento d on det.numFolio = d.numFolio" +
                    " inner join informacionProcedencia iP on d.idProcedencia = iP.idProcedencia" +
                    " inner join institucionProcedencia i on iP.idInstitucion = i.idInstitucion" +
                    " where  det.numFolio='" + no_Folio + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ConsultaDocumentos p = null;
            while(rs.next()) {
                p = new ConsultaDocumentos(
                        rs.getInt("det.numFolio"), rs.getInt("det.numDocumento"),
                        rs.getString("i.nombreInstitucion"),rs.getDate("det.fechaRecepcion"),
                        rs.getString("iP.observaciones")
                );
                consultaDocumentosFolio.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return consultaDocumentosFolio;
    }

    public ObservableList<Documento> fetchDocs() {
        ObservableList<Documento> consultaDocumentos = FXCollections.observableArrayList();
        try {
            String query = "select numFolio from documento;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Documento p = null;
            while(rs.next()) {
                p = new Documento(
                        rs.getInt("numFolio")
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


    public ObservableList<ReporteConsultas> reportDocumento(int no_Folio, int no_Doc) {
        ObservableList<ReporteConsultas> consultaDocumentos= FXCollections.observableArrayList();
        try {
            String query = " select d.numFolio, det.numDocumento, det.fechaDocumento, det.fechaRecepcion, f.nombreFormato, t.nombreTipoDoc" +
                    " from documento d inner join detalleDocumento det on d.numFolio = det.numFolio" +
                    " inner join formato f on det.idFormato = f.idFormato" +
                    " inner join tipoDocumento t on det.idTipoDocumento = t.idTipoDocumento" +
                    " where d.numFolio='" + no_Folio + "' and det.numDocumento='" + no_Doc +"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ReporteConsultas p = null;
            while(rs.next()) {
                p = new ReporteConsultas(
                        rs.getInt("d.numFolio"), rs.getInt("det.numDocumento"),
                        rs.getDate("det.fechaDocumento"), rs.getDate("det.fechaRecepcion"),
                        rs.getString("f.nombreFormato"), rs.getString("t.nombreTipoDoc")
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

    public ObservableList<ReporteConsultas> reportDestinat(int no_Folio, int no_Doc) {
        ObservableList<ReporteConsultas> consultaDestinat= FXCollections.observableArrayList();
        try {
            String query = " select iD.quienRecibe, a.nombreArea, p.descPrioridad, i.descInstruccion, iD.fechaEntrega, iD.fechaLimite" +
                    " from informacionDestinatario iD inner join areasAyuntamiento a on iD.idArea = a.idArea" +
                    " inner join instruccion i on iD.idInstruccion = i.idInstruccion" +
                    " inner join prioridad p on iD.idPrioridad = p.idPrioridad" +
                    " inner join documento d on iD.idDestinatario = d.idDestinatario" +
                    " inner join detalleDocumento dD on d.numFolio = dD.numFolio" +
                    " where d.numFolio='" + no_Folio + "' and dD.numDocumento='" + no_Doc +"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ReporteConsultas p = null;
            while(rs.next()) {
                p = new ReporteConsultas(
                        rs.getString("iD.quienRecibe"),rs.getString("a.nombreArea"),
                        rs.getString("p.descPrioridad"),rs.getString("i.descInstruccion"),
                        rs.getDate("iD.fechaEntrega"),rs.getDate("iD.fechaLimite")
                );
                consultaDestinat.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return consultaDestinat;
    }

    public ObservableList<ReporteConsultas> reportProc(int no_Folio, int no_Doc) {
        ObservableList<ReporteConsultas> consultaproc= FXCollections.observableArrayList();
        try {
            String query = " select i.nombreInstitucion, ip.quienFirma, ip.puesto, ip.dirigidaA,ip.asunto,ip.observaciones" +
                    " from institucionProcedencia i inner join informacionProcedencia iP on i.idInstitucion = iP.idInstitucion" +
                    " inner join documento d on iP.idProcedencia = d.idProcedencia" +
                    " inner join detalleDocumento dD on d.numFolio = dD.numFolio" +
                    " where d.numFolio='" + no_Folio + "' and dD.numDocumento='" + no_Doc +"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ReporteConsultas p = null;
            while(rs.next()) {
                p = new ReporteConsultas(
                        rs.getString("i.nombreInstitucion"),rs.getString("ip.quienFirma"),
                        rs.getString("ip.puesto"),rs.getString("ip.dirigidaA"),
                        rs.getString("ip.asunto"),rs.getString("ip.observaciones")
                );
                consultaproc.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return consultaproc;
    }

}
