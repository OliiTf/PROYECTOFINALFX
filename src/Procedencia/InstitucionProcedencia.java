package Procedencia;

public class InstitucionProcedencia {

    int idInstitucion;
    String nombreInstitucion;

    public InstitucionProcedencia(int idInstitucion, String nombreInstitucion) {
        this.idInstitucion = idInstitucion;
        this.nombreInstitucion = nombreInstitucion;
    }

    public InstitucionProcedencia(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public InstitucionProcedencia(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public InstitucionProcedencia() {
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }
}
