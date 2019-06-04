package Recepcion.Procedencia;
import Recepcion.Procedencia.Institucion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Procedencia.InstitucionProcedencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProcedenciaDAOReporte {

    Connection conn;

    public ProcedenciaDAOReporte(Connection conn) {
        this.conn = conn;
    }

    public List<InstitucionProcedencia> findAll() {
        List<InstitucionProcedencia> departments = new ArrayList<InstitucionProcedencia>();
        try {
            String query = "SELECT * FROM institucionprocedencia order by idInstitucion asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            InstitucionProcedencia p = null;
            while(rs.next()) {
                p = new InstitucionProcedencia(
                        rs.getInt("idInstitucion"), rs.getString("nombreInstitucion")
                );
                departments.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return departments;
    }
}
