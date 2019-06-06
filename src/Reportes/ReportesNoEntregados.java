package Reportes;

import java.sql.Date;

public class ReportesNoEntregados {

    int idDestinatario,numFolio, numDocumento;
    String nombreInstitucion, quienRecibe;
    Date fechaRecepcion;
    Date fechaEntrega;
    Boolean entregado;

    public ReportesNoEntregados(int idDestinatario, int numFolio, int numDocumento, String nombreInstitucion, Date fechaRecepcion, Boolean entregado, Date fechaEntrega,String quienRecibe) {
        this.idDestinatario = idDestinatario;
        this.numFolio = numFolio;
        this.numDocumento = numDocumento;
        this.nombreInstitucion = nombreInstitucion;
        this.fechaRecepcion = fechaRecepcion;
        this.entregado = entregado;
        this.fechaEntrega =fechaEntrega;
        this.quienRecibe =quienRecibe;
    }


    public ReportesNoEntregados( Date fechaEntrega,String quienRecibe) {
        this.fechaEntrega = fechaEntrega;
        this.quienRecibe=quienRecibe;
    }

    public ReportesNoEntregados(int numFolio, int numDocumento, String nombreInstitucion, Date fechaRecepcion, Date fechaEntrega, Boolean entregado) {
        this.numFolio = numFolio;
        this.numDocumento = numDocumento;
        this.nombreInstitucion = nombreInstitucion;
        this.fechaRecepcion = fechaRecepcion;
        this.fechaEntrega = fechaEntrega;
        this.entregado = entregado;
    }

    public String getQuienRecibe() {
        return quienRecibe;
    }

    public void setQuienRecibe(String quienRecibe) {
        this.quienRecibe = quienRecibe;
    }

    public int getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getNumFolio() {
        return numFolio;
    }

    public void setNumFolio(int numFolio) {
        this.numFolio = numFolio;
    }

    public int getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(int numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Boolean getEntregado() {
        return entregado;
    }

    public void setEntregado(Boolean entregado) {
        this.entregado = entregado;
    }
}
