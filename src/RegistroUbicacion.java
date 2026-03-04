public class RegistroUbicacion {


    private int identificador;
    private String fechaHora;
    private String informacionUbicacion;


    public RegistroUbicacion() {
        this.identificador = 0;
        this.fechaHora = "No definida";
        this.informacionUbicacion = "Desconocida";
    }


    public RegistroUbicacion(int identificador, String fechaHora, String informacionUbicacion) {
        this.identificador = identificador;
        this.fechaHora = fechaHora;
        this.informacionUbicacion = informacionUbicacion;
    }


    public int getIdentificador() {
        return this.identificador;
    }

    public String getFechaHora() {
        return this.fechaHora;
    }

    public String getInformacionUbicacion() {
        return this.informacionUbicacion;
    }
}