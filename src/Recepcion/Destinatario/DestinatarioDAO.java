package Recepcion.Destinatario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DestinatarioDAO{

    Connection conn;

    public DestinatarioDAO(Connection conn) {
        this.conn = conn;
    }


    public Boolean insert(Destinatario Document) {
        try {
            String query = "insert into informaciondestinatario(idDestinatario, fechaLimite, fechaEntrega," +
                    " quienRecibe, idArea, idInstruccion, idPrioridad) values (?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, Document.getIdDestinatario());
            st.setDate(2, Document.getFechaLimite());
            st.setDate(3, Document.getFechaEntrega());
            st.setString(4,Document.getQuienRecibe() );
            st.setInt(5, Document.getIdArea());
            st.setInt(6, Document.getIdInstruccion());
            st.setInt(7,Document.getIdPrioridad());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


    public Destinatario countDestinat() {
        Destinatario p = new Destinatario();
        try {
            String query = "select count(*) as idDestinatario from informacionDestinatario;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                p = new Destinatario(
                        rs.getInt("idDestinatario")
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

    public ObservableList<Instruccion> fetchInstruccion() {
        ObservableList<Instruccion> Instruccion = FXCollections.observableArrayList();
        try {
            String query = "select instruccion.descInstruccion from instruccion";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Instruccion p = null;
            while (rs.next()) {
                p = new Instruccion(
                        rs.getString("descInstruccion")
                );
                Instruccion.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Instruccion;
    }

    public Instruccion findIdintruccion(String  nom_instruc) {
        Instruccion p = new Instruccion();
        try {
            String query = "select idInstruccion from instruccion where descInstruccion='" + nom_instruc + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                p = new Instruccion(
                        rs.getInt("idInstruccion")
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

    public ObservableList<Prioridad> fetchPrioridad() {
        ObservableList<Prioridad> Prioridad = FXCollections.observableArrayList();
        try {
            String query = "select prioridad.descPrioridad from prioridad;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Prioridad p = null;
            while (rs.next()) {
                p = new Prioridad(
                        rs.getString("descPrioridad")
                );
                Prioridad.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Prioridad;
    }

    public Prioridad findIdPrioridad(String  nom_prioridad) {
        Prioridad p = new Prioridad();
        try {
            String query = "select idPrioridad from prioridad where descPrioridad='" + nom_prioridad + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                p = new Prioridad(
                        rs.getInt("idPrioridad")
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
