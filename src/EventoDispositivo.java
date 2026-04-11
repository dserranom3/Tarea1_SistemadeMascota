import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventoDispositivo {


    private static int contadorEventosGlobal = 1;


    private int identificador;
    private String fechaHora;
    private TipoEvento tipoEvento;
    private Dispositivo dispositivoAsociado;


    public EventoDispositivo() {
        this.identificador = contadorEventosGlobal++;
        this.fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        this.tipoEvento = TipoEvento.ENCENDIDO; // <-- ¡Esta es la corrección!
        this.dispositivoAsociado = null;
    }


    public EventoDispositivo(TipoEvento tipoEvento, Dispositivo dispositivoAsociado) {
        this.identificador = contadorEventosGlobal++;
        this.fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")); // Fecha/Hora actual real
        this.tipoEvento = tipoEvento;
        this.dispositivoAsociado = dispositivoAsociado;
    }


    public int getIdentificador() { return identificador; }
    public String getFechaHora() { return fechaHora; }
    public TipoEvento getTipoEvento() { return tipoEvento; }
    public Dispositivo getDispositivoAsociado() { return dispositivoAsociado; }

    @Override
    public String toString() {
        String infoDisp = (dispositivoAsociado != null) ? dispositivoAsociado.getCodigo() : "Sin asignar";
        return "[EVENTO " + identificador + " | " + fechaHora + "] Tipo: " + tipoEvento + " | Dispositivo: " + infoDisp;
    }
}