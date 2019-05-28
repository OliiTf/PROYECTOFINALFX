package Recepcion.Destinatario;

import java.sql.Date;
import javafx.scene.control.DatePicker;

import java.sql.Date;

public class DestinatarioInsert {


        int idDestinatario,  idArea;
        String quienRecibe;
        int   idInstruccion, idPrioridad;
        Date fechaLimite, fechaEntrega;

    public DestinatarioInsert(int idDestinatario, int idArea, String quienRecibe, int idInstruccion, int idPrioridad, Date fechaLimite, Date fechaEntrega) {
        this.idDestinatario = idDestinatario;
        this.idArea = idArea;
        this.quienRecibe = quienRecibe;
        this.idInstruccion = idInstruccion;
        this.idPrioridad = idPrioridad;
        this.fechaLimite = fechaLimite;
        this.fechaEntrega = fechaEntrega;
    }

    public int getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getQuienRecibe() {
        return quienRecibe;
    }

    public void setQuienRecibe(String quienRecibe) {
        this.quienRecibe = quienRecibe;
    }

    public int getIdInstruccion() {
        return idInstruccion;
    }

    public void setIdInstruccion(int idInstruccion) {
        this.idInstruccion = idInstruccion;
    }

    public int getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(int idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}

