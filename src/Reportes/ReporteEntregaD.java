package Reportes;

import java.sql.Date;

public class ReporteEntregaD {
    int numfolio;
    String nombreInstitucion,quienRecibe,nombreTipoDoc,descPrioridad,nombreFormato,observaciones;
    Date fechaRecepcion;

    public ReporteEntregaD(int numfolio, String nombreInstitucion, String quienRecibe, String nombreTipoDoc, String descPrioridad, String nombreFormato, String observaciones, Date fechaRecepcion) {
        this.numfolio = numfolio;
        this.nombreInstitucion = nombreInstitucion;
        this.quienRecibe = quienRecibe;
        this.nombreTipoDoc = nombreTipoDoc;
        this.descPrioridad = descPrioridad;
        this.nombreFormato = nombreFormato;
        this.observaciones = observaciones;
        this.fechaRecepcion = fechaRecepcion;
    }

    public int getNumfolio() {
        return numfolio;
    }

    public void setNumfolio(int numfolio) {
        this.numfolio = numfolio;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getQuienRecibe() {
        return quienRecibe;
    }

    public void setQuienRecibe(String quienRecibe) {
        this.quienRecibe = quienRecibe;
    }

    public String getNombreTipoDoc() {
        return nombreTipoDoc;
    }

    public void setNombreTipoDoc(String nombreTipoDoc) {
        this.nombreTipoDoc = nombreTipoDoc;
    }

    public String getDescPrioridad() {
        return descPrioridad;
    }

    public void setDescPrioridad(String descPrioridad) {
        this.descPrioridad = descPrioridad;
    }

    public String getNombreFormato() {
        return nombreFormato;
    }

    public void setNombreFormato(String nombreFormato) {
        this.nombreFormato = nombreFormato;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }
}
