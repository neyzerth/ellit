package Entrenadores;

//hecho por leslie Plata
// Contiene los menús y funciones de interacción con el usuario

import Usuario.FuncionesUsuario;
import java.util.ArrayList;
import java.util.Scanner;

public class FuncionesEntrenador {
    static Scanner sc = new Scanner(System.in);
    
    // Especialidades predefinidas
    private static final String[] ESPECIALIDADES = {
        "Musculación", "Cardio", "Yoga", "Crossfit", "Pilates", "Boxeo"
    };
    
    // ===============================
    // Menú principal para usuarios
    // ===============================
    public static void menuEntrenadores() {
        int opcion;
        do {
System.out.println("╔════════════════════════════════════════╗");
System.out.println("║                                        ║");
System.out.println("║            ENTRENADORES                ║");
System.out.println("║                                        ║");
System.out.println("╠════════════════════════════════════════╣");
System.out.println("║                                        ║");
System.out.println("║  1. Ver todos los entrenadores         ║");
System.out.println("║  2. Buscar por nombre                  ║");
System.out.println("║  3. Buscar por especialidad            ║");
System.out.println("║  4. Volver                             ║");
System.out.println("║                                        ║");
System.out.println("╚════════════════════════════════════════╝");
System.out.print(" Seleccione una opción: ");
            
            opcion = FuncionesUsuario.validarNumero(1, 4);
            
            switch (opcion) {
                case 1:
                    verTodosLosEntrenadores();
                    break;
                case 2:
                    buscarEntrenadorPorNombre();
                    break;
                case 3:
                    buscarPorEspecialidad();
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal...");
                    break;
            }
        } while (opcion != 4);
    }
    
    // ===============================
    // Menú de gestión para admin
    // ===============================
    public static void menuGestionEntrenadores() {
        int opcion;
        do {
System.out.println("╔════════════════════════════════════════╗");
System.out.println("║                                        ║");
System.out.println("║      GESTIÓN DE ENTRENADORES           ║");
System.out.println("║                                        ║");
System.out.println("╠════════════════════════════════════════╣");
System.out.println("║                                        ║");
System.out.println("║  1. Ver todos los entrenadores         ║");
System.out.println("║  2. Agregar nuevo entrenador           ║");
System.out.println("║  3. Editar entrenador                  ║");
System.out.println("║  4. Desactivar entrenador              ║");
System.out.println("║  5. Volver                             ║");
System.out.println("║                                        ║");
System.out.println("╚════════════════════════════════════════╝");
System.out.print(" Seleccione una opción: ");
            
            opcion = FuncionesUsuario.validarNumero(1, 5);
            
            switch (opcion) {
                case 1:
                    verTodosLosEntrenadores();
                    break;
                case 2:
                    agregarNuevoEntrenador();
                    break;
                case 3:
                    editarEntrenador();
                    break;
                case 4:
                    desactivarEntrenador();
                    break;
                case 5:
                    System.out.println("Volviendo al menú admin...");
                    break;
            }
        } while (opcion != 5);
    }
    
    // ===============================
    // Ver todos los entrenadores
    // ===============================
    private static void verTodosLosEntrenadores() {
        ArrayList<Entrenador> entrenadores = CrudEntrenador.obtenerEntrenadores();
        
        if (entrenadores.isEmpty()) {
            System.out.println("\nNo hay entrenadores registrados.");
            return;
        }
        
        System.out.println("\n--- LISTA DE ENTRENADORES ---");
        for (Entrenador e : entrenadores) {
            System.out.println(e);
        }
    }
    
    // ===============================
    // Buscar entrenador por nombre
    // ===============================
    private static void buscarEntrenadorPorNombre() {
        System.out.print("\nIngrese el nombre o parte del nombre: ");
        sc.nextLine(); 
        String nombre = sc.nextLine();
        
        ArrayList<Entrenador> resultados = CrudEntrenador.buscarPorNombre(nombre);
        
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron entrenadores con ese nombre.");
            return;
        }
        
        System.out.println("\n--- RESULTADOS DE BÚSQUEDA ---");
        for (Entrenador e : resultados) {
            System.out.println(e);
        }
    }
    
    // ===============================
    // Buscar por especialidad
    // ===============================
    private static void buscarPorEspecialidad() {
        System.out.println("\nSeleccione la especialidad:");
        for (int i = 0; i < ESPECIALIDADES.length; i++) {
            System.out.println((i+1) + ". " + ESPECIALIDADES[i]);
        }
        
        int opcion = FuncionesUsuario.validarNumero(1, ESPECIALIDADES.length);
        String especialidad = ESPECIALIDADES[opcion-1];
        
        ArrayList<Entrenador> resultados = CrudEntrenador.buscarPorEspecialidad(especialidad);
        
        if (resultados.isEmpty()) {
            System.out.println("\nNo hay entrenadores con esa especialidad.");
            return;
        }
        
        System.out.println("\n--- ENTRENADORES DE " + especialidad.toUpperCase() + " ---");
        for (Entrenador e : resultados) {
            System.out.println(e);
        }
    }
    
    
   // ===============================
// Agregar nuevo entrenador (admin)
// ===============================
private static void agregarNuevoEntrenador() {
    Entrenador nuevo = new Entrenador();
    sc.nextLine(); 
    
    System.out.println("\n--- NUEVO ENTRENADOR ---");
    
    // Nombre
    System.out.print("Nombre completo: ");
    nuevo.setNombre(sc.nextLine());
    
    // Especialidad
    System.out.println("Seleccione la especialidad:");
    for (int i = 0; i < ESPECIALIDADES.length; i++) {
        System.out.println((i+1) + ". " + ESPECIALIDADES[i]);
    }
    int opEsp = FuncionesUsuario.validarNumero(1, ESPECIALIDADES.length);
    nuevo.setEspecialidad(ESPECIALIDADES[opEsp-1]);
    
    // Correo (validación)
    String correo;
    do {
        System.out.print("Correo electrónico (debe contener @ y dominio válido): ");
        correo = sc.nextLine();
        if (!correo.matches("^[A-Za-z0-9+_.-]+@(gmail|hotmail|outlook)\\.com$")) {
            System.out.println("Formato inválido. Ejemplo: usuario@gmail.com");
        }
    } while (!correo.matches("^[A-Za-z0-9+_.-]+@(gmail|hotmail|outlook)\\.com$"));
    nuevo.setCorreo(correo);
    
    // Teléfono (validación)
    String telefono;
    do {
        System.out.print("Teléfono (10 dígitos sin lada): ");
        telefono = sc.nextLine();
        if (!telefono.matches("^\\d{10}$")) {
            System.out.println("Debe tener exactamente 10 dígitos.");
        }
    } while (!telefono.matches("^\\d{10}$"));
    nuevo.setTelefono("+52" + telefono); // Agrega la lada automáticamente
    
    // Estado activo
    nuevo.setActivo(true);
    
    // Insertar en BD
    if (CrudEntrenador.insertarEntrenador(nuevo)) {
        System.out.println("\nEntrenador agregado correctamente!");
    } else {
        System.out.println("\nError al agregar entrenador.");
    }
} 
    // ===============================
    // Editar entrenador (admin)
    // ===============================
    private static void editarEntrenador() {
        ArrayList<Entrenador> entrenadores = CrudEntrenador.obtenerEntrenadores();
        
        if (entrenadores.isEmpty()) {
            System.out.println("\nNo hay entrenadores para editar.");
            return;
        }
        
        System.out.println("\n--- SELECCIONE ENTRENADOR A EDITAR ---");
        for (Entrenador e : entrenadores) {
            System.out.println(e);
        }
        
        System.out.print("\nIngrese el ID del entrenador a editar: ");
        int id = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
        
        // Buscar el entrenador
        Entrenador aEditar = null;
        for (Entrenador e : entrenadores) {
            if (e.getId() == id) {
                aEditar = e;
                break;
            }
        }
        
        if (aEditar == null) {
            System.out.println("ID no válido.");
            return;
        }
        
        System.out.println("\nEditando entrenador:");
        System.out.println(aEditar);
        
        // Menú de edición
System.out.println("╔════════════════════════════════════════╗");
System.out.println("║                                        ║");
System.out.println("║          ¿QUÉ DESEA EDITAR?            ║");
System.out.println("║                                        ║");
System.out.println("╠════════════════════════════════════════╣");
System.out.println("║                                        ║");
System.out.println("║  1. Nombre                             ║");
System.out.println("║  2. Especialidad                       ║");
System.out.println("║  3. Correo                             ║");
System.out.println("║  4. Teléfono                           ║");
System.out.println("║  5. Estado                             ║");
System.out.println("║  6. Cancelar                           ║");
System.out.println("║                                        ║");
System.out.println("╚════════════════════════════════════════╝");
System.out.print(" Seleccione una opción: ");
        
        int opcion = FuncionesUsuario.validarNumero(1, 6);
        sc.nextLine(); // Limpiar buffer
        
        switch (opcion) {
            case 1:
                System.out.print("Nuevo nombre: ");
                aEditar.setNombre(sc.nextLine());
                break;
            case 2:
                System.out.println("Nueva especialidad:");
                for (int i = 0; i < ESPECIALIDADES.length; i++) {
                    System.out.println((i+1) + ". " + ESPECIALIDADES[i]);
                }
                int opEsp = FuncionesUsuario.validarNumero(1, ESPECIALIDADES.length);
                aEditar.setEspecialidad(ESPECIALIDADES[opEsp-1]);
                break;
            case 3:
                System.out.print("Nuevo correo: ");
                aEditar.setCorreo(sc.nextLine());
                break;
            case 4:
                System.out.print("Nuevo teléfono: ");
                aEditar.setTelefono(sc.nextLine());
                break;
            case 5:
                System.out.println("Cambiar estado (1. Activo / 2. Inactivo):");
                int estado = FuncionesUsuario.validarNumero(1, 2);
                aEditar.setActivo(estado == 1);
                break;
            case 6:
                return;
        }
        
        if (CrudEntrenador.actualizarEntrenador(aEditar)) {
            System.out.println("\nEntrenador actualizado correctamente!");
        } else {
            System.out.println("\nError al actualizar entrenador.");
        }
    }
    
    // ===============================
    // Desactivar entrenador (admin)
    // ===============================
    private static void desactivarEntrenador() {
        ArrayList<Entrenador> entrenadores = CrudEntrenador.obtenerEntrenadores();
        
        if (entrenadores.isEmpty()) {
            System.out.println("\nNo hay entrenadores para desactivar.");
            return;
        }
        
        System.out.println("\n--- SELECCIONE ENTRENADOR A DESACTIVAR ---");
        for (Entrenador e : entrenadores) {
            System.out.println(e);
        }
        
        System.out.print("\nIngrese el ID del entrenador a desactivar: ");
        int id = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
        
        System.out.println("¿Está seguro de desactivar este entrenador? (1. Sí / 2. No)");
        int confirmar = FuncionesUsuario.validarNumero(1, 2);
        
        if (confirmar == 1) {
            if (CrudEntrenador.desactivarEntrenador(id)) {
                System.out.println("Entrenador desactivado correctamente.");
            } else {
                System.out.println("Error al desactivar entrenador.");
            }
        }
    }
}