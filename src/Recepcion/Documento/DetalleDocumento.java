package Recepcion.Documento;

import java.sql.Date;

public class DetalleDocumento {
    int idDocumento, numDocumento;
    Date fechaRecepcion, fechaDocumento;
    int idFormato, idTipoDocumento,numFolio;


    public DetalleDocumento() {
    }

    public DetalleDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public DetalleDocumento(int idDocumento, int numDocumento, Date fechaRecepcion, Date fechaDocumento, int idFormato, int idTipoDocumento, int numFolio) {
        this.idDocumento = idDocumento;
        this.numDocumento = numDocumento;
        this.fechaRecepcion = fechaRecepcion;
        this.fechaDocumento = fechaDocumento;
        this.idFormato = idFormato;
        this.idTipoDocumento = idTipoDocumento;
        this.numFolio = numFolio;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public int getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(int numDocumento) {
        this.numDocumento = numDocumento;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public int getIdFormato() {
        return idFormato;
    }

    public void setIdFormato(int idFormato) {
        this.idFormato = idFormato;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public int getNumFolio() {
        return numFolio;
    }

    public void setNumFolio(int numFolio) {
        this.numFolio = numFolio;
    }

    @Override
    public String toString() {
        return Integer.toString(idDocumento);
    }
}
