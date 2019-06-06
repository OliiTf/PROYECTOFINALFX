package Reportes;

import Procedencia.InstitucionProcedencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportesDAO {

    Connection conn;

    public ReportesDAO(Connection conn) {
        this.conn = conn;
    }

    public ObservableList<Reportes> fech(String dates) {
        ObservableList<Reportes> Reportes2 = FXCollections.observableArrayList();
        try {
            String query = "select d2.numFolio,d2.fechaRecepcion,i3.nombreInstitucion,i5.quienRecibe\n" +
                    "from documento d inner join informacionprocedencia i on d.idProcedencia = i.idProcedencia\n" +
                    "                 inner join institucionprocedencia i4 on i.idInstitucion = i4.idInstitucion\n" +
                    "                 inner join informaciondestinatario i5 on d.idDestinatario = i5.idDestinatario\n" +
                    "                 inner join detalledocumento d2 on d.numFolio = d2.numFolio\n" +
                    "                 inner join informaciondestinatario i2 on d.idDestinatario = i2.idDestinatario\n" +
                    "                 inner join institucionprocedencia i3 on i.idInstitucion = i3.idInstitucion " +
                    "where fechaRecepcion = '" + dates + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Reportes p = null;
            while(rs.next()) {
                p = new Reportes(
                        rs.getInt("numfolio"),
                        rs.getString("nombreInstitucion"),
                        rs.getString("quienRecibe"),
                        rs.getDate("fechaRecepcion")
                );
                Reportes2.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Reportes2;
    }

    public ObservableList<Reportes> fech2() {
        ObservableList<Reportes> Reportes3 = FXCollections.observableArrayList();
        try {
            String query = "select d2.numFolio,d2.fechaRecepcion,i3.nombreInstitucion,i5.quienRecibe\n" +
                    "from documento d inner join informacionprocedencia i on d.idProcedencia = i.idProcedencia\n" +
                    "                 inner join institucionprocedencia i4 on i.idInstitucion = i4.idInstitucion\n" +
                    "                 inner join informaciondestinatario i5 on d.idDestinatario = i5.idDestinatario\n" +
                    "                 inner join detalledocumento d2 on d.numFolio = d2.numFolio\n" +
                    "                 inner join informaciondestinatario i2 on d.idDestinatario = i2.idDestinatario\n" +
                    "                 inner join institucionprocedencia i3 on i.idInstitucion = i3.idInstitucion\n" +
                    "where d.entregado=1;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Reportes p = null;
            while(rs.next()) {
                p = new Reportes(
                        rs.getInt("numfolio"),
                        rs.getString("nombreInstitucion"),
                        rs.getString("quienRecibe"),
                        rs.getDate("fechaRecepcion")
                );
                Reportes3.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Reportes3;
    }
    public List<ReporteEntregaD> fetchAll2(String nombre) {
        List<ReporteEntregaD> reportes = new ArrayList<ReporteEntregaD>();
        try {
            String query = "select d2.numFolio,i3.nombreInstitucion,i5.quienRecibe,t.nombreTipoDoc,p.descPrioridad,f.nombreFormato,i.observaciones,d2.fechaRecepcion\n" +
                    "from documento d inner join informacionprocedencia i on d.idProcedencia = i.idProcedencia\n" +
                    "                 inner join institucionprocedencia i4 on i.idInstitucion = i4.idInstitucion\n" +
                    "                 inner join informaciondestinatario i5 on d.idDestinatario = i5.idDestinatario\n" +
                    "                 inner join detalledocumento d2 on d.numFolio = d2.numFolio\n" +
                    "                 inner join informaciondestinatario i2 on d.idDestinatario = i2.idDestinatario\n" +
                    "                 inner join institucionprocedencia i3 on i.idInstitucion = i3.idInstitucion\n" +
                    "                 inner join tipodocumento t on d2.idTipoDocumento = t.idTipoDocumento\n"+
                    "                 inner join prioridad p on i5.idPrioridad = p.idPrioridad\n"+
                    "                 inner join formato f on d2.idFormato = f.idFormato\n"+
                    "where i3.nombreInstitucion='"+nombre+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ReporteEntregaD p = null;
            while (rs.next()) {
                p = new ReporteEntregaD(
                        rs.getInt("numfolio"),
                        rs.getString("nombreInstitucion"),
                        rs.getString("quienRecibe"),
                        rs.getString("nombreTipoDoc"),
                        rs.getString("descPrioridad"),
                        rs.getString("nombreFormato"),
                        rs.getString("observaciones"),
                        rs.getDate("fechaRecepcion")
                );
                reportes.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return reportes;

    }


    public ObservableList<Reportes> fetchAll() {
        ObservableList<Reportes> reportes = FXCollections.observableArrayList();
        try {
            String query = "select d2.numFolio,d2.fechaRecepcion,i3.nombreInstitucion,i5.quienRecibe\n" +
                    "from documento d inner join informacionprocedencia i on d.idProcedencia = i.idProcedencia\n" +
                    "                 inner join institucionprocedencia i4 on i.idInstitucion = i4.idInstitucion\n" +
                    "                 inner join informaciondestinatario i5 on d.idDestinatario = i5.idDestinatario\n" +
                    "                 inner join detalledocumento d2 on d.numFolio = d2.numFolio\n" +
                    "                 inner join informaciondestinatario i2 on d.idDestinatario = i2.idDestinatario\n" +
                    "                 inner join institucionprocedencia i3 on i.idInstitucion = i3.idInstitucion;\n";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Reportes p = null;
            while (rs.next()) {
                p = new Reportes(
                        rs.getInt("numfolio"),
                        rs.getString("nombreInstitucion"),
                        rs.getString("quienRecibe"),
                        rs.getDate("fechaRecepcion")
                );
                reportes.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return reportes;

    }
    public ObservableList<Reportes> fech3(String dates) {
        ObservableList<Reportes> Reportes2 = FXCollections.observableArrayList();
        try {
            String query = "select d2.numFolio,d2.fechaRecepcion,i3.nombreInstitucion,i5.quienRecibe\n" +
                    "from documento d inner join informacionprocedencia i on d.idProcedencia = i.idProcedencia\n" +
                    "                 inner join institucionprocedencia i4 on i.idInstitucion = i4.idInstitucion\n" +
                    "                 inner join informaciondestinatario i5 on d.idDestinatario = i5.idDestinatario\n" +
                    "                 inner join detalledocumento d2 on d.numFolio = d2.numFolio\n" +
                    "                 inner join informaciondestinatario i2 on d.idDestinatario = i2.idDestinatario\n" +
                    "                 inner join institucionprocedencia i3 on i.idInstitucion = i3.idInstitucion " +
                    "where fechaRecepcion = '" + dates + "'and d.entregado=1";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Reportes p = null;
            while(rs.next()) {
                p = new Reportes(
                        rs.getInt("numfolio"),
                        rs.getString("nombreInstitucion"),
                        rs.getString("quienRecibe"),
                        rs.getDate("fechaRecepcion")
                );
                Reportes2.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Reportes2;
    }


    public ObservableList<ReportesNoEntregados> findDocumentosNoentregados() {
        ObservableList<ReportesNoEntregados> docsNE = FXCollections.observableArrayList();
        try {
            String query = " select iD.idDestinatario,det.numFolio,det.numDocumento,i.nombreInstitucion, det.fechaRecepcion,d.entregado, iD.fechaEntrega, iD.quienRecibe" +
                    " from informacionDestinatario iD inner join documento d on iD.idDestinatario = d.idDestinatario" +
                    " inner join detalleDocumento det on d.numFolio = det.numFolio" +
                    " inner join informacionProcedencia iP on d.idProcedencia = iP.idProcedencia" +
                    " inner join institucionProcedencia i on iP.idInstitucion = i.idInstitucion where d.entregado=false;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ReportesNoEntregados p = null;
            while(rs.next()) {
                p = new ReportesNoEntregados(
                        rs.getInt("iD.idDestinatario"),
                        rs.getInt("det.numFolio"),
                        rs.getInt("det.numDocumento"),
                        rs.getString("i.nombreInstitucion"),
                        rs.getDate("det.fechaRecepcion"),
                        rs.getBoolean("d.entregado"),
                        rs.getDate("iD.fechaEntrega"),
                        rs.getString("iD.quienRecibe")
                );
                docsNE.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return docsNE;
    }



    public Boolean update(int idDEsti, ReportesNoEntregados RNE) {
        try {
            PreparedStatement st;
            String query = "update informacionDestinatario "
                    + " set fechaEntrega = ?, quienRecibe = ?"
                    + " where idDestinatario= '"+idDEsti+"'";
            st = conn.prepareStatement(query);
            st.setDate(1, RNE.getFechaEntrega());
            st.setString(2,RNE.getQuienRecibe());
            st.execute();
            String query1 = " update documento"
                    + " set entregado = true"
                    + " where idDestinatario= '"+idDEsti+"'";
            st = conn.prepareStatement(query1);
            //st.setBoolean(1, RNE.getEntregado());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

}
