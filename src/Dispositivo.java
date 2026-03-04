import java.util.ArrayList;

public class Dispositivo {


    private String codigo;
    private String estado;
    private ArrayList<RegistroUbicacion> historialUbicaciones = new ArrayList<>();


    public Dispositivo() {
        this.codigo = "Sin asignar.";
        this.estado = "Apagado";
    }


    public Dispositivo(String codigo, String estado) {
        this.codigo = codigo;
        this.estado = estado;
    }


    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public void agregarUbicacion(RegistroUbicacion nuevaUbicacion) {
        this.historialUbicaciones.add(nuevaUbicacion);
    }

    public ArrayList<RegistroUbicacion> getHistorialUbicaciones() {
        return this.historialUbicaciones;
    }


    @Override
    public String toString() {
        return "Dispositivo [" +
                "Código='" + this.codigo + '\'' +
                ", Estado='" + this.estado + '\'' +
                ']';
    }
}