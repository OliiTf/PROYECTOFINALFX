package tipoDocumento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class tipoDocumentoDAO {

    Connection conn;
    public tipoDocumentoDAO( Connection conn){this.conn=conn;}

    public ObservableList<tipoDocumento> fetchAll() {
        ObservableList<tipoDocumento> procedencias = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM tipoDocumento";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            tipoDocumento td = null;
            while(rs.next()) {
                td = new tipoDocumento(
                        rs.getInt("idTipoDocumento"), rs.getString("nombreTipoDoc")
                );
                procedencias.add(td);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return procedencias;
    }

    public Boolean delete(int id_proce) {
        try {
            String query = "delete from tipoDocumento where idTipoDocumento = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id_proce);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(tipoDocumento td) {
        try {
            String query = "insert into tipoDocumento"
                    + " (idTipoDocumento, nombreTipoDoc)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(   1, td.getIdTipoDocumento());
            st.setString(  2, td.getNombreTipoDoc());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(tipoDocumento td) {
        try {
            String query = "update tipoDocumento"
                    + " set nombreTipoDoc = ?"
                    + " where idTipoDocumento=?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, td.getNombreTipoDoc());
            st.setInt(   2, td.getIdTipoDocumento());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


}
