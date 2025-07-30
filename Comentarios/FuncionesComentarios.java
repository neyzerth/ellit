package Comentarios;
//hecho por: Brayan Castañeda
// Contiene todas las funciones de interacción con el usuario para comentarios

import Usuario.FuncionesUsuario;
import Usuario.Usuario;
import java.util.ArrayList;
import java.util.Scanner;

public class FuncionesComentarios {
    static Scanner sc = new Scanner(System.in);
    
    
    public static void menuComentarios() {
        int opcion;
        do {
System.out.println("╔════════════════════════════════════════╗");
System.out.println("║                                        ║");
System.out.println("║          MENÚ DE COMENTARIOS           ║");
System.out.println("║                                        ║");
System.out.println("╠════════════════════════════════════════╣");
System.out.println("║                                        ║");
System.out.println("║  1. Ver comentarios de la plataforma   ║");
System.out.println("║  2. Hacer un comentario                ║");
System.out.println("║  3. Editar mis comentarios             ║");
System.out.println("║  4. Eliminar mis comentarios           ║");
System.out.println("║  5. Regresar                           ║");
System.out.println("║                                        ║");
System.out.println("╚════════════════════════════════════════╝");

System.out.print(" Seleccione una opción: ");
            
            opcion = FuncionesUsuario.validarNumero(1, 5);
            
            switch (opcion) {
                case 1:
                    verTodosLosComentarios();
                    break;
                case 2:
                sc.nextLine();
                    crearNuevoComentario();
                    break;
                case 3:
                    editarMisComentarios();
                    break;
                case 4:
                    eliminarMisComentarios();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
            }
        } while (opcion != 5);
    }
    
    // ===============================
    // Menú de gestión de comentarios (admin)
    // ===============================
    public static void menuGestionComentarios() {
        int opcion;
        do {
System.out.println("╔════════════════════════════════════════╗");
System.out.println("║                                        ║");
System.out.println("║     GESTIÓN DE COMENTARIOS (ADMIN)     ║");
System.out.println("║                                        ║");
System.out.println("╠════════════════════════════════════════╣");
System.out.println("║                                        ║");
System.out.println("║  1. Ver todos los comentarios          ║");
System.out.println("║  2. Eliminar comentarios               ║");
System.out.println("║  3. Volver al menú admin               ║");
System.out.println("║                                        ║");
System.out.println("╚════════════════════════════════════════╝");

System.out.print(" Seleccione una opción: ");
            
            opcion = FuncionesUsuario.validarNumero(1, 3);
            
            switch (opcion) {
                case 1:
                    verTodosLosComentariosAdmin();
                    break;
                case 2:
                    eliminarComentariosAdmin();
                    break;
                case 3:
                    System.out.println("Volviendo al menú admin...");
                    break;
            }
        } while (opcion != 3);
    }
    
    // ===============================
    // Ver todos los comentarios (usuario normal)
    // ===============================
    public static void verTodosLosComentarios() {
        ArrayList<Comentario> comentarios = CrudComentarios.obtenerTodosLosComentarios();
        
        if (comentarios.isEmpty()) {
            System.out.println("\nNo hay comentarios en la plataforma.");
            return;
        }
        
        System.out.println("\n--- COMENTARIOS DE LA PLATAFORMA ---");
        for (Comentario c : comentarios) {
            System.out.println(c);
            System.out.println("Calificación: " + c.getEstrellas());
            System.out.println("----------------------------------");
        }
    }
    
    // ===============================
    // Ver todos los comentarios (admin)
    // ===============================
    public static void verTodosLosComentariosAdmin() {
        ArrayList<Comentario> comentarios = CrudComentarios.obtenerTodosLosComentarios();
        
        if (comentarios.isEmpty()) {
            System.out.println("\nNo hay comentarios en la plataforma.");
            return;
        }
        
        System.out.println("\n--- TODOS LOS COMENTARIOS ---");
        for (Comentario c : comentarios) {
            System.out.println("ID: " + c.getId());
            System.out.println(c);
            System.out.println("Calificación: " + c.getEstrellas());
            System.out.println("----------------------------------");
        }
    }
    
    // ===============================
    // Crear nuevo comentario
    // ===============================
    

public static void crearNuevoComentario() {
    Usuario usuarioActual = FuncionesUsuario.getUsuarioActual();
    if (usuarioActual == null) {
        System.out.println("No hay usuario logueado.");
        return;
    }
    
    Comentario nuevo = new Comentario();
    nuevo.setUsuarioId(usuarioActual.getId());
    
    System.out.println("\n--- NUEVO COMENTARIO ---");
    
    // Obtener texto del comentario (corregido)
    System.out.println("Escribe tu comentario (máx 500 caracteres):");
    // Eliminamos el sc.nextLine() que estaba aquí antes
    String texto = sc.nextLine(); // Ahora lee directamente la entrada
    
    while (texto.trim().isEmpty() || texto.length() > 500) {
        if (texto.length() > 500) {
            System.out.println("El comentario no puede tener más de 500 caracteres.");
        } else {
            System.out.println("El comentario no puede estar vacío.");
        }
        texto = sc.nextLine();
    }
    nuevo.setTexto(texto);
    
    // Obtener calificación
    System.out.println("Califica la plataforma (1-5 estrellas):");
    int calificacion = FuncionesUsuario.validarNumero(1, 5);
    nuevo.setCalificacion(calificacion);
    
    // Insertar en la base de datos
    CrudComentarios.insertarComentario(nuevo);
    System.out.println("¡Gracias por tu comentario!");
}
    // ===============================
    // Editar comentarios del usuario actual
    // ===============================
    public static void editarMisComentarios() {
        Usuario usuarioActual = FuncionesUsuario.getUsuarioActual();
        if (usuarioActual == null) {
            System.out.println("No hay usuario logueado.");
            return;
        }
        
        ArrayList<Comentario> misComentarios = CrudComentarios.obtenerComentariosPorUsuario(usuarioActual.getId());
        
        if (misComentarios.isEmpty()) {
            System.out.println("\nNo tienes comentarios para editar.");
            return;
        }
        
        System.out.println("\n--- MIS COMENTARIOS ---");
        for (Comentario c : misComentarios) {
            System.out.println("ID: " + c.getId());
            System.out.println(c);
            System.out.println("Calificación: " + c.getEstrellas());
            System.out.println("----------------------------------");
        }
        
        System.out.print("\nIngresa el ID del comentario a editar (0 para cancelar): ");
        int idComentario = FuncionesUsuario.validarNumero(0, Integer.MAX_VALUE);
        
        if (idComentario == 0) {
            return;
        }
        
        // Verificar que el comentario pertenece al usuario
        if (!CrudComentarios.comentarioPerteneceAUsuario(idComentario, usuarioActual.getId())) {
            System.out.println("No puedes editar un comentario que no es tuyo.");
            return;
        }
        
        // Buscar el comentario a editar
        Comentario comentarioAEditar = null;
        for (Comentario c : misComentarios) {
            if (c.getId() == idComentario) {
                comentarioAEditar = c;
                break;
            }
        }
        
        if (comentarioAEditar == null) {
            System.out.println("Comentario no encontrado.");
            return;
        }
        
        // Menú de edición
System.out.println("╔════════════════════════════════════════╗");
System.out.println("║                                        ║");
System.out.println("║        ¿QUÉ DESEAS EDITAR?             ║");
System.out.println("║                                        ║");
System.out.println("╠════════════════════════════════════════╣");
System.out.println("║                                        ║");
System.out.println("║  1. Texto del comentario               ║");
System.out.println("║  2. Calificación                       ║");
System.out.println("║  3. Cancelar                           ║");
System.out.println("║                                        ║");
System.out.println("╚════════════════════════════════════════╝");
System.out.println("══════════════════════════════════════════");
System.out.print(" Seleccione una opción: ");
        
        int opcion = FuncionesUsuario.validarNumero(1, 3);
        
        switch (opcion) {
            case 1:
                System.out.println("\nTexto actual:");
                System.out.println(comentarioAEditar.getTexto());
                System.out.println("\nNuevo texto (máx 500 caracteres):");
                sc.nextLine(); // Limpiar buffer
                String nuevoTexto = sc.nextLine();
                while (nuevoTexto.isEmpty() || nuevoTexto.length() > 500) {
                    System.out.println("El comentario no puede estar vacío ni tener más de 500 caracteres.");
                    nuevoTexto = sc.nextLine();
                }
                comentarioAEditar.setTexto(nuevoTexto);
                break;
                
            case 2:
                System.out.println("\nCalificación actual: " + comentarioAEditar.getEstrellas());
                System.out.println("Nueva calificación (1-5 estrellas):");
                int nuevaCalificacion = FuncionesUsuario.validarNumero(1, 5);
                comentarioAEditar.setCalificacion(nuevaCalificacion);
                break;
                
            case 3:
                return;
        }
        
        // Actualizar en la base de datos
        CrudComentarios.actualizarComentario(comentarioAEditar);
        System.out.println("Comentario actualizado correctamente.");
    }
    
    // ===============================
    // Eliminar comentarios del usuario actual
    // ===============================
    public static void eliminarMisComentarios() {
        Usuario usuarioActual = FuncionesUsuario.getUsuarioActual();
        if (usuarioActual == null) {
            System.out.println("No hay usuario logueado.");
            return;
        }
        
        ArrayList<Comentario> misComentarios = CrudComentarios.obtenerComentariosPorUsuario(usuarioActual.getId());
        
        if (misComentarios.isEmpty()) {
            System.out.println("\nNo tienes comentarios para eliminar.");
            return;
        }
        
        System.out.println("\n--- MIS COMENTARIOS ---");
        for (Comentario c : misComentarios) {
            System.out.println("ID: " + c.getId());
            System.out.println(c);
            System.out.println("Calificación: " + c.getEstrellas());
            System.out.println("----------------------------------");
        }
        
        System.out.print("\nIngresa el ID del comentario a eliminar (0 para cancelar): ");
        int idComentario = FuncionesUsuario.validarNumero(0, Integer.MAX_VALUE);
        
        if (idComentario == 0) {
            return;
        }
        
        // Verificar que el comentario pertenece al usuario
        if (!CrudComentarios.comentarioPerteneceAUsuario(idComentario, usuarioActual.getId())) {
            System.out.println("No puedes eliminar un comentario que no es tuyo.");
            return;
        }
        
        System.out.println("¿Estás seguro de que deseas eliminar este comentario? (1. Sí / 2. No)");
        int confirmar = FuncionesUsuario.validarNumero(1, 2);
        
        if (confirmar == 1) {
            CrudComentarios.eliminarComentario(idComentario);
            System.out.println("Comentario eliminado correctamente.");
        }
    }
    
    // ===============================
    // Eliminar comentarios (admin)
    // ===============================
    public static void eliminarComentariosAdmin() {
        ArrayList<Comentario> todosComentarios = CrudComentarios.obtenerTodosLosComentarios();
        
        if (todosComentarios.isEmpty()) {
            System.out.println("\nNo hay comentarios para eliminar.");
            return;
        }
        
        System.out.println("\n--- TODOS LOS COMENTARIOS ---");
        for (Comentario c : todosComentarios) {
            System.out.println("ID: " + c.getId());
            System.out.println(c);
            System.out.println("Calificación: " + c.getEstrellas());
            System.out.println("----------------------------------");
        }
        
        System.out.print("\nIngresa el ID del comentario a eliminar (0 para cancelar): ");
        int idComentario = FuncionesUsuario.validarNumero(0, Integer.MAX_VALUE);
        
        if (idComentario == 0) {
            return;
        }
        
        System.out.println("¿Estás seguro de que deseas eliminar este comentario? (1. Sí / 2. No)");
        int confirmar = FuncionesUsuario.validarNumero(1, 2);
        
        if (confirmar == 1) {
            CrudComentarios.eliminarComentario(idComentario);
            System.out.println("Comentario eliminado correctamente.");
        }
    }
}