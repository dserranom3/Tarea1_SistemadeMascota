import java.util.ArrayList;
import java.util.Random;


public class Dispositivo extends Thread {

    private String codigo;
    private String estado;
    private ArrayList<RegistroUbicacion> historialUbicaciones = new ArrayList<>();


    private boolean simulando;
    private Random random = new Random();

    public Dispositivo() {
        this.codigo = "Sin asignar";
        this.estado = "Apagado";
        this.simulando = false;
    }

    public Dispositivo(String codigo, String estado) {
        this.codigo = codigo;
        this.estado = estado;
        this.simulando = false;
    }

    public String getCodigo() { return this.codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getEstado() { return this.estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public ArrayList<RegistroUbicacion> getHistorialUbicaciones() { return this.historialUbicaciones; }

    public void agregarUbicacion(RegistroUbicacion nuevaUbicacion) {
        this.historialUbicaciones.add(nuevaUbicacion);
    }

    public void detenerSimulacion() {
        this.simulando = false;
    }

 // hilo
    @Override
    public void run() {
        this.simulando = true;
        this.estado = "Transmitiendo";

        while (simulando) {
            try {

                int tiempoPausa = 3000 + random.nextInt(3000);
                Thread.sleep(tiempoPausa);


                String latitud = String.format("%.4f", (9.9 + random.nextDouble()));
                String longitud = String.format("%.4f", (-84.0 - random.nextDouble()));
                String nuevaUbicacion = "Lat: " + latitud + ", Lon: " + longitud;


                int idRegistro = this.historialUbicaciones.size() + 1;

                String fechaHora = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));

                RegistroUbicacion registro = new RegistroUbicacion(idRegistro, fechaHora, nuevaUbicacion);
                this.agregarUbicacion(registro);


                System.out.println("\n📡 [SIMULACIÓN] Dispositivo " + this.codigo + " generó nueva ubicación: " + nuevaUbicacion);

            } catch (InterruptedException e) {

                System.out.println("⚠️ Error en el hilo del dispositivo " + this.codigo + ": " + e.getMessage());
            }
        }
        this.estado = "En Espera";
    }

    @Override
    public String toString() {
        return "Dispositivo [" + "Código='" + this.codigo + '\'' + ", Estado='" + this.estado + '\'' + ']';
    }
}