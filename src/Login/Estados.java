package Login;

public class Estados {

    int idEstado;
    String nombreEstado;

    public Estados(int idEstado, String nombreEstado) {
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
    }

    public Estados(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    @Override
    public String toString() {
        return nombreEstado;
    }
}
