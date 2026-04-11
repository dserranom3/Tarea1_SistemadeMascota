import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Alerta {


    private static int contadorAlertasGlobal = 1;

    private int identificador;
    private String fechaHora;
    private TipoAlerta tipoAlerta; // Usamos el enum
    private Mascota mascotaAsociada;


    public Alerta() {
        this.identificador = contadorAlertasGlobal++;
        this.fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        this.tipoAlerta = TipoAlerta.INFORMATIVA;
        this.mascotaAsociada = null;
    }


    public Alerta(TipoAlerta tipoAlerta, Mascota mascotaAsociada) {
        this.identificador = contadorAlertasGlobal++;
        this.fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        this.tipoAlerta = tipoAlerta;
        this.mascotaAsociada = mascotaAsociada;
    }


    public int getIdentificador() { return identificador; }
    public String getFechaHora() { return fechaHora; }
    public TipoAlerta getTipoAlerta() { return tipoAlerta; }
    public Mascota getMascotaAsociada() { return mascotaAsociada; }

    @Override
    public String toString() {
        String infoMascota = (mascotaAsociada != null) ? mascotaAsociada.getNombre() : "Sin mascota";
        return "⚠️ [ALERTA " + identificador + " | " + fechaHora + "] Nivel: " + tipoAlerta + " | Mascota: " + infoMascota;
    }
}