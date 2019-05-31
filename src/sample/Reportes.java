package sample;

import java.sql.Date;

public class Reportes {

     /*número de folio, fecha de recepción, institución de procedencia
    y destinatario, así también un campo para firma de recepción.*/
     int numfolio;
     String nombreInstitucion,quienRecibe;
     Date fechaRecepcion;

     public Reportes(int numfolio, String nombreInstitucion, String quienRecibe, Date fechaRecepcion) {
          this.numfolio = numfolio;
          this.nombreInstitucion = nombreInstitucion;
          this.quienRecibe = quienRecibe;
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

     public Date getFechaRecepcion() {
          return fechaRecepcion;
     }

     public void setFechaRecepcion(Date fechaRecepcion) {
          this.fechaRecepcion = fechaRecepcion;
     }

}
