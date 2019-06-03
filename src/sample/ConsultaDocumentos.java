package sample;

import java.awt.*;
import java.sql.Date;

public class ConsultaDocumentos {
    private int idDocumento;
    private int idDestinatario, idProcedencia;
    private int numFolio;
    private int numDocumento;
    private String  nombreInstitucion;
    private Date fechaRecepcion;
    private String observaciones;

    public ConsultaDocumentos(int idDocumento, int idDestinatario, int idProcedencia, int numFolio, int numDocumento, String nombreInstitucion, Date fechaRecepcion, String observaciones) {
        this.idDocumento = idDocumento;
        this.idDestinatario = idDestinatario;
        this.idProcedencia = idProcedencia;
        this.numFolio = numFolio;
        this.numDocumento = numDocumento;
        this.nombreInstitucion = nombreInstitucion;
        this.fechaRecepcion = fechaRecepcion;
        this.observaciones = observaciones;
    }

    public ConsultaDocumentos(int numFolio, int numDocumento, String nombreInstitucion, Date fechaRecepcion, String observaciones) {
        this.numFolio = numFolio;
        this.numDocumento = numDocumento;
        this.nombreInstitucion = nombreInstitucion;
        this.fechaRecepcion = fechaRecepcion;
        this.observaciones = observaciones;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public int getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public int getIdProcedencia() {
        return idProcedencia;
    }

    public void setIdProcedencia(int idProcedencia) {
        this.idProcedencia = idProcedencia;
    }

    @Override
    public String toString() {
        return Integer.toString(numFolio);
    }
}

