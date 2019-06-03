package sample;

import java.sql.Date;

public class ReporteConsultas {
    int numFolio, numDocumento;
    Date FechaDocumento, fechaRecepcion;
    String nombreFormato, nombreTipoDoc, nombreInstitucion, quienFirma, puesto, dirigidaA, asunto, observaciones, quienRecibe, nombreArea, descPrioridad, descInstruccion;
    Date fechaEntrega, fechaLimite;

   //DOCUMENTO
    public ReporteConsultas(int numFolio, int numDocumento, Date fechaDocumento, Date fechaRecepcion, String nombreFormato, String nombreTipoDoc) {
        this.numFolio = numFolio;
        this.numDocumento = numDocumento;
        FechaDocumento = fechaDocumento;
        this.fechaRecepcion = fechaRecepcion;
        this.nombreFormato = nombreFormato;
        this.nombreTipoDoc = nombreTipoDoc;
    }

    //PROCEDENCIA
    public ReporteConsultas(String nombreInstitucion, String quienFirma, String puesto, String dirigidaA, String asunto, String observaciones) {
        this.nombreInstitucion = nombreInstitucion;
        this.quienFirma = quienFirma;
        this.puesto = puesto;
        this.dirigidaA = dirigidaA;
        this.asunto = asunto;
        this.observaciones = observaciones;
    }


    //DESTINATARIO
    public ReporteConsultas(String quienRecibe, String nombreArea, String descPrioridad, String descInstruccion, Date fechaEntrega, Date fechaLimite) {
        this.quienRecibe = quienRecibe;
        this.nombreArea = nombreArea;
        this.descPrioridad = descPrioridad;
        this.descInstruccion = descInstruccion;
        this.fechaEntrega = fechaEntrega;
        this.fechaLimite = fechaLimite;
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

    public Date getFechaDocumento() {
        return FechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        FechaDocumento = fechaDocumento;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getNombreFormato() {
        return nombreFormato;
    }

    public void setNombreFormato(String nombreFormato) {
        this.nombreFormato = nombreFormato;
    }

    public String getNombreTipoDoc() {
        return nombreTipoDoc;
    }

    public void setNombreTipoDoc(String nombreTipoDoc) {
        this.nombreTipoDoc = nombreTipoDoc;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getQuienFirma() {
        return quienFirma;
    }

    public void setQuienFirma(String quienFirma) {
        this.quienFirma = quienFirma;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDirigidaA() {
        return dirigidaA;
    }

    public void setDirigidaA(String dirigidaA) {
        this.dirigidaA = dirigidaA;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getQuienRecibe() {
        return quienRecibe;
    }

    public void setQuienRecibe(String quienRecibe) {
        this.quienRecibe = quienRecibe;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getDescPrioridad() {
        return descPrioridad;
    }

    public void setDescPrioridad(String descPrioridad) {
        this.descPrioridad = descPrioridad;
    }

    public String getDescInstruccion() {
        return descInstruccion;
    }

    public void setDescInstruccion(String descInstruccion) {
        this.descInstruccion = descInstruccion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
}
