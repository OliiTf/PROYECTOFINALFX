package Recepcion.Procedencia;




import Procedencia.InstitucionProcedencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ProcedenciaDAO {


    Connection conn;

    public ProcedenciaDAO(Connection conn) {
        this.conn = conn;
    }

    public Boolean insert(InfomaciónProcedencia infomaciónProcedencia) {
        try {
            String query = "insert into informacionProcedencia(idProcedencia, quienFirma, puesto,dirigidaA," +
                    " asunto, observaciones, idInstitucion) values (?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, infomaciónProcedencia.getIdProcedencia());
            st.setString(2, infomaciónProcedencia.getQuienFirma());
            st.setString(3, infomaciónProcedencia.getPuesto());
            st.setString(4,infomaciónProcedencia.getDirigidaA() );
            st.setString(5, infomaciónProcedencia.getAsunto());
            st.setString(6, infomaciónProcedencia.getObservaciones());
            st.setInt(7,infomaciónProcedencia.getIdInstitucion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


    public InfomaciónProcedencia countProc() {
        InfomaciónProcedencia p = new InfomaciónProcedencia();
        try {
            String query = "select count(*) as idProcedencia from informacionProcedencia;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                p = new InfomaciónProcedencia(
                        rs.getInt("idProcedencia")
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


    public ObservableList<InstitucionProcedencia> fetchInstituccion() {
        ObservableList<InstitucionProcedencia> Institucion = FXCollections.observableArrayList();
        try {
            String query = "select institucionprocedencia.nombreInstitucion from institucionprocedencia";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            InstitucionProcedencia p = null;
            while (rs.next()) {
                p = new InstitucionProcedencia(
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

    public InstitucionProcedencia findIdInst(String  nom_inst) {
        InstitucionProcedencia p = new InstitucionProcedencia();
        try {
            String query = "select idInstitucion from institucionProcedencia where nombreInstitucion='" + nom_inst + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                p = new InstitucionProcedencia(
                        rs.getInt("idInstitucion")
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
