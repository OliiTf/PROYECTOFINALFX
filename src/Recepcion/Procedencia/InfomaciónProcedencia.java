package Recepcion.Procedencia;

public class InfomaciónProcedencia {

    int idProcedencia;
    String quienFirma,puesto,dirigidaA,asunto,observaciones;
    int idInstitucion;

    public InfomaciónProcedencia(int idProcedencia, String quienFirma, String puesto, String dirigidaA, String asunto, String observaciones, int idInstitucion) {
        this.idProcedencia = idProcedencia;
        this.quienFirma = quienFirma;
        this.puesto = puesto;
        this.dirigidaA = dirigidaA;
        this.asunto = asunto;
        this.observaciones = observaciones;
        this.idInstitucion = idInstitucion;
    }

    public InfomaciónProcedencia() {
    }

    public int getIdProcedencia() {
        return idProcedencia;
    }

    public InfomaciónProcedencia(int idProcedencia) {
        this.idProcedencia = idProcedencia;
    }

    public void setIdProcedencia(int idProcedencia) {
        this.idProcedencia = idProcedencia;
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

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }



}
