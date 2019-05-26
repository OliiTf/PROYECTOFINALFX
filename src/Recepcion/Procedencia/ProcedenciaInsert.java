package Recepcion.Procedencia;

import java.sql.Date;

public class ProcedenciaInsert {
    int idProcedencia,idInstitucion;
    String quienFirma,puesto,dirigidaA,asunto,observaciones;

    public ProcedenciaInsert(int idProcedencia, int idInstitucion, String quienFirma, String puesto, String dirigidaA, String asunto, String observaciones) {
        this.idProcedencia = idProcedencia;
        this.idInstitucion = idInstitucion;
        this.quienFirma = quienFirma;
        this.puesto = puesto;
        this.dirigidaA = dirigidaA;
        this.asunto = asunto;
        this.observaciones = observaciones;
    }

    public int getIdProcedencia() {
        return idProcedencia;
    }

    public void setIdProcedencia(int idProcedencia) {
        this.idProcedencia = idProcedencia;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
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
}
