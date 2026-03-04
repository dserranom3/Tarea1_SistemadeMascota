import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Mascota> listaMascotas = new ArrayList<>();
        ArrayList<Dispositivo> listaDispositivos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n=== SISTEMA DE RASTREO DE MASCOTAS ===");
            System.out.println("1. Registrar mascotas");
            System.out.println("2. Registrar dispositivos de localización");
            System.out.println("3. Asociar dispositivos a mascotas");
            System.out.println("4. Registrar ubicaciones de forma manual");
            System.out.println("5. Consultar la información almacenada");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRAR MASCOTA ---");
                    System.out.print("ID de la mascota (número): ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Tipo de animal: ");
                    String tipo = scanner.nextLine();
                    System.out.print("Estado (Ej. Activa, Perdida): ");
                    String estado = scanner.nextLine();

                    listaMascotas.add(new Mascota(id, nombre, tipo, estado));
                    System.out.println("✅ Mascota registrada con éxito.");
                    break;

                case 2:
                    System.out.println("\n--- REGISTRAR DISPOSITIVO ---");
                    System.out.print("Código del dispositivo (Ej. DEV-01): ");
                    String codigo = scanner.nextLine();
                    System.out.print("Estado del dispositivo (Ej. Encendido, Apagado): ");
                    String estadoDisp = scanner.nextLine();

                    listaDispositivos.add(new Dispositivo(codigo, estadoDisp));
                    System.out.println("✅ Dispositivo registrado con éxito.");
                    break;

                case 3:
                    System.out.println("\n--- ASOCIAR DISPOSITIVO ---");
                    System.out.print("Ingrese el ID de la mascota: ");
                    int idMascotaBuscar = scanner.nextInt();
                    scanner.nextLine();

                    Mascota mascotaEncontrada = null;
                    for (Mascota m : listaMascotas) {
                        if (m.getId() == idMascotaBuscar) {
                            mascotaEncontrada = m;
                            break;
                        }
                    }

                    if (mascotaEncontrada == null) {
                        System.out.println("❌ Mascota no encontrada.");
                        break;
                    }

                    System.out.print("Ingrese el Código del dispositivo a asociar: ");
                    String codigoBuscar = scanner.nextLine();

                    Dispositivo dispositivoEncontrado = null;
                    for (Dispositivo d : listaDispositivos) {
                        if (d.getCodigo().equals(codigoBuscar)) {
                            dispositivoEncontrado = d;
                            break;
                        }
                    }

                    if (dispositivoEncontrado != null) {
                        mascotaEncontrada.setDispositivoAsociado(dispositivoEncontrado);
                        System.out.println("✅ Dispositivo " + codigoBuscar + " asociado a la mascota " + mascotaEncontrada.getId());
                    } else {
                        System.out.println("❌ Dispositivo no encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- REGISTRAR UBICACIÓN ---");
                    System.out.print("Ingrese el ID de la mascota para registrar ubicación: ");
                    int idMascotaUbi = scanner.nextInt();
                    scanner.nextLine();

                    Mascota mascotaParaUbicacion = null;
                    for (Mascota m : listaMascotas) {
                        if (m.getId() == idMascotaUbi) {
                            mascotaParaUbicacion = m;
                            break;
                        }
                    }

                    if (mascotaParaUbicacion != null && mascotaParaUbicacion.getDispositivoAsociado() != null) {
                        System.out.print("ID del registro (número consecutivo): ");
                        int idRegistro = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Fecha y Hora (Ej. 03/03/2026 14:00): ");
                        String fechaHora = scanner.nextLine();
                        System.out.print("Información de ubicación (Ej. Parque de la Paz): ");
                        String infoUbi = scanner.nextLine();

                        RegistroUbicacion nuevaUbi = new RegistroUbicacion(idRegistro, fechaHora, infoUbi);
                        mascotaParaUbicacion.getDispositivoAsociado().agregarUbicacion(nuevaUbi);

                        System.out.println("✅ Ubicación registrada correctamente.");
                    } else {
                        System.out.println("❌ Error: Mascota no encontrada o no tiene dispositivo asociado.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- CONSULTAR INFORMACIÓN DE MASCOTA ---");
                    System.out.print("Ingrese el ID de la mascota a consultar: ");
                    int idConsulta = scanner.nextInt();
                    scanner.nextLine();

                    boolean encontrada = false;
                    for (Mascota m : listaMascotas) {
                        if (m.getId() == idConsulta) {
                            encontrada = true;
                            System.out.println("\n" + m.toString());

                            if (m.getDispositivoAsociado() != null) {
                                System.out.println("\nHistorial de Ubicaciones del dispositivo:");
                                ArrayList<RegistroUbicacion> historial = m.getDispositivoAsociado().getHistorialUbicaciones();
                                if (historial.isEmpty()) {
                                    System.out.println("- No hay ubicaciones registradas aún.");
                                } else {
                                    for (RegistroUbicacion ubi : historial) {
                                        System.out.println("  -> ID Registro: " + ubi.getIdentificador() + " | Fecha: " + ubi.getFechaHora() + " | Ubicación: " + ubi.getInformacionUbicacion());
                                    }
                                }
                            }
                            break;
                        }
                    }

                    if (!encontrada) {
                        System.out.println("❌ Mascota no encontrada en el sistema.");
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del sistema");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}