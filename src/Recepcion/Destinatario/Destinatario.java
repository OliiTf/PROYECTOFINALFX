package Recepcion.Destinatario;


import java.sql.Date;

public class Destinatario {


    int idDestinatario;
    Date fechaLimite, fechaEntrega;
    String quienRecibe;
    int  idArea,idInstruccion, idPrioridad;

    public Destinatario() {
    }

    public Destinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public Destinatario(int idDestinatario, Date fechaLimite, Date fechaEntrega, String quienRecibe, int idArea, int idInstruccion, int idPrioridad) {
        this.idDestinatario = idDestinatario;
        this.fechaLimite = fechaLimite;
        this.fechaEntrega = fechaEntrega;
        this.quienRecibe = quienRecibe;
        this.idArea = idArea;
        this.idInstruccion = idInstruccion;
        this.idPrioridad = idPrioridad;
    }

    public int getIdDestinataario() {
        return idDestinatario;
    }

    public void setIdDestinataario(int idDestinataario) {
        this.idDestinatario = idDestinataario;
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

    public String getQuienRecibe() {
        return quienRecibe;
    }

    public void setQuienRecibe(String quienRecibe) {
        this.quienRecibe = quienRecibe;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
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

    @Override
    public String toString() {
        return Integer.toString(idDestinatario);
    }
}

