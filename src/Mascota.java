public class Mascota {


    private int id;
    private String nombre;
    private String tipoAnimal;
    private String estado;
    private Dispositivo dispositivoAsociado;


    public Mascota() {
        this.id = 0;
        this.nombre = "Sin nombre";
        this.tipoAnimal = "Desconocido";
        this.estado = "Registrada";
        this.dispositivoAsociado = null;
    }


    public Mascota(int id, String nombre, String tipoAnimal, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.tipoAnimal = tipoAnimal;
        this.estado = estado;
        this.dispositivoAsociado = null;
    }


    public int getId() {
        return this.id;
    }

    public Dispositivo getDispositivoAsociado() {
        return this.dispositivoAsociado;
    }

    public void setDispositivoAsociado(Dispositivo dispositivo) {
        this.dispositivoAsociado = dispositivo;
    }

    @Override
    public String toString() {
        String infoDispositivo;
        if (this.dispositivoAsociado == null) {
            infoDispositivo = "Ningún dispositivo asignado";
        } else {
            infoDispositivo = "Asignado con código: " + this.dispositivoAsociado.getCodigo();
        }

        return "Mascota [" +
                "ID=" + this.id +
                ", Nombre='" + this.nombre + '\'' +
                ", Tipo='" + this.tipoAnimal + '\'' +
                ", Estado='" + this.estado + '\'' +
                ", Dispositivo=" + infoDispositivo +
                ']';
    }
}