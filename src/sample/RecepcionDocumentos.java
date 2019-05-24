package sample;

import java.sql.Date;

public class RecepcionDocumentos {
    private int no_folio;
    private Date no_doc;
    private String formato, tipo, quienrecibe, area, prioridad,
            instruccion, institucion, firma, puesto, dirigidoa, asunto, observaciones;
    private Date fecha_doc, fecharecep, fechalimite, fechaentrega;
    private boolean entregado;


    public RecepcionDocumentos(int no_folio, Date no_doc, String formato,
                               String tipo, String quienrecibe, String area,
                               String prioridad, String instruccion, String institucion,
                               String firma, String puesto, String dirigidoa, String asunto,
                               String observaciones, Date fecha_doc, Date fecharecep, Date fechalimite,
                               Date fechaentrega, boolean entregado) {
        this.no_folio = no_folio;
        this.no_doc = no_doc;
        this.formato = formato;
        this.tipo = tipo;
        this.quienrecibe = quienrecibe;
        this.area = area;
        this.prioridad = prioridad;
        this.instruccion = instruccion;
        this.institucion = institucion;
        this.firma = firma;
        this.puesto = puesto;
        this.dirigidoa = dirigidoa;
        this.asunto = asunto;
        this.observaciones = observaciones;
        this.fecha_doc = fecha_doc;
        this.fecharecep = fecharecep;
        this.fechalimite = fechalimite;
        this.fechaentrega = fechaentrega;
        this.entregado = entregado;
    }

    public int getNo_folio() {
        return no_folio;
    }

    public void setNo_folio(int no_folio) {
        this.no_folio = no_folio;
    }

    public Date getNo_doc() {
        return no_doc;
    }

    public void setNo_doc(Date no_doc) {
        this.no_doc = no_doc;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getQuienrecibe() {
        return quienrecibe;
    }

    public void setQuienrecibe(String quienrecibe) {
        this.quienrecibe = quienrecibe;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(String instruccion) {
        this.instruccion = instruccion;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDirigidoa() {
        return dirigidoa;
    }

    public void setDirigidoa(String dirigidoa) {
        this.dirigidoa = dirigidoa;
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

    public Date getFecha_doc() {
        return fecha_doc;
    }

    public void setFecha_doc(Date fecha_doc) {
        this.fecha_doc = fecha_doc;
    }

    public Date getFecharecep() {
        return fecharecep;
    }

    public void setFecharecep(Date fecharecep) {
        this.fecharecep = fecharecep;
    }

    public Date getFechalimite() {
        return fechalimite;
    }

    public void setFechalimite(Date fechalimite) {
        this.fechalimite = fechalimite;
    }

    public Date getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(Date fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }
}







