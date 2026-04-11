import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Mascota> listaMascotas = new ArrayList<>();
        ArrayList<Dispositivo> listaDispositivos = new ArrayList<>();
        ArrayList<EventoDispositivo> listaEventos = new ArrayList<>();
        ArrayList<Alerta> listaAlertas = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        boolean simulacionActiva = false;

        do {
            System.out.println("\n=== TAREA 2: SISTEMA CONCURRENTE DE RASTREO ===");
            System.out.println("1. Registrar mascota");
            System.out.println("2. Registrar dispositivo");
            System.out.println("3. Asociar dispositivo a mascota");
            System.out.println("4. Registrar ubicación manualmente");
            System.out.println("5. Consultar información de mascota");
            System.out.println("6. Iniciar / Detener Simulación Automática");
            System.out.println("7. Generar Alertas y Eventos");
            System.out.println("8. Ver Historial de Eventos y Alertas");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");


            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("❌ ERROR: Entrada inválida. Debe ingresar un número.");
                scanner.nextLine();
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRAR MASCOTA ---");
                    System.out.print("ID (número): ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Tipo de animal: ");
                    String tipo = scanner.nextLine();
                    System.out.print("Estado: ");
                    String estado = scanner.nextLine();
                    listaMascotas.add(new Mascota(id, nombre, tipo, estado));
                    System.out.println("✅ Mascota registrada.");
                    break;

                case 2:
                    System.out.println("\n--- REGISTRAR DISPOSITIVO ---");
                    System.out.print("Código: ");
                    String codigo = scanner.nextLine();
                    listaDispositivos.add(new Dispositivo(codigo, "En Espera"));
                    System.out.println("✅ Dispositivo registrado.");
                    break;

                case 3:
                    System.out.println("\n--- ASOCIAR DISPOSITIVO ---");
                    System.out.print("ID de la mascota: ");
                    int idMascota = scanner.nextInt();
                    scanner.nextLine();
                    Mascota mascEncontrada = null;
                    for (Mascota m : listaMascotas) {
                        if (m.getId() == idMascota) { mascEncontrada = m; break; }
                    }

                    if (mascEncontrada == null) {
                        System.out.println("❌ Mascota no encontrada.");
                        break;
                    }

                    System.out.print("Código del dispositivo: ");
                    String codBuscar = scanner.nextLine();
                    Dispositivo dispEncontrado = null;
                    for (Dispositivo d : listaDispositivos) {
                        if (d.getCodigo().equals(codBuscar)) { dispEncontrado = d; break; }
                    }

                    if (dispEncontrado != null) {
                        mascEncontrada.setDispositivoAsociado(dispEncontrado);
                        System.out.println("✅ Dispositivo asociado exitosamente.");
                    } else {
                        System.out.println("❌ Dispositivo no encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- REGISTRAR UBICACIÓN MANUAL ---");

                    System.out.println("📝 Nota: Con la simulación activa (Opción 6), los dispositivos reportan solos.");
                    break;

                case 5:

                    System.out.println("\n--- CONSULTAR MASCOTA ---");
                    System.out.print("ID de la mascota: ");
                    int idCons = scanner.nextInt();
                    scanner.nextLine();
                    boolean encontrada = false;
                    for (Mascota m : listaMascotas) {
                        if (m.getId() == idCons) {
                            encontrada = true;
                            System.out.println(m.toString());
                            if (m.getDispositivoAsociado() != null) {
                                System.out.println("Ubicaciones registradas: " + m.getDispositivoAsociado().getHistorialUbicaciones().size());
                            }
                            break;
                        }
                    }
                    if (!encontrada) System.out.println("❌ Mascota no encontrada.");
                    break;

                case 6:

                    if (!simulacionActiva) {
                        System.out.println("\n🚀 Iniciando simulación concurrente...");
                        for (Dispositivo d : listaDispositivos) {
                            if (!d.isAlive()) {
                                try {
                                    d.start();
                                } catch (IllegalThreadStateException e) {
                                    System.out.println("⚠️ El hilo de " + d.getCodigo() + " ya no puede reiniciarse.");
                                }
                            }
                        }
                        simulacionActiva = true;
                        System.out.println("✅ Dispositivos transmitiendo de forma autónoma.");
                    } else {
                        System.out.println("\n🛑 Deteniendo simulación...");
                        for (Dispositivo d : listaDispositivos) {
                            d.detenerSimulacion();
                        }
                        simulacionActiva = false;
                    }
                    break;

                case 7:

                    System.out.println("\n🔍 Analizando actividad de dispositivos...");
                    int nuevasAlertas = 0;
                    for (Mascota m : listaMascotas) {
                        Dispositivo d = m.getDispositivoAsociado();
                        if (d != null) {
                            int cantidadUbicaciones = d.getHistorialUbicaciones().size();

                            if (cantidadUbicaciones > 3) {
                                EventoDispositivo nuevoEvento = new EventoDispositivo(TipoEvento.MOVIMIENTO_DETECTADO, d);
                                Alerta nuevaAlerta = new Alerta(TipoAlerta.INFORMATIVA, m);

                                listaEventos.add(nuevoEvento);
                                listaAlertas.add(nuevaAlerta);
                                nuevasAlertas++;
                            }
                        }
                    }
                    System.out.println("✅ Análisis completado. Se generaron " + nuevasAlertas + " eventos y alertas.");
                    break;

                case 8:
                    System.out.println("\n=== 📋 REGISTRO DE EVENTOS ===");
                    if (listaEventos.isEmpty()) System.out.println("No hay eventos registrados.");
                    for (EventoDispositivo e : listaEventos) System.out.println(e.toString());

                    System.out.println("\n=== ⚠️ REGISTRO DE ALERTAS ===");
                    if (listaAlertas.isEmpty()) System.out.println("No hay alertas registradas.");
                    for (Alerta a : listaAlertas) System.out.println(a.toString());
                    break;

                case 9:
                    System.out.println("Finalizando sistema... ¡Deteniendo todos los hilos!");
                    for (Dispositivo d : listaDispositivos) d.detenerSimulacion();
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 9);

        scanner.close();
    }
}