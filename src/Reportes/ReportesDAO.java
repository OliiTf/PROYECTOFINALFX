package Reportes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

}
