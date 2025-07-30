package Usuario;
//hecho por Daniel Barrientos
// Contiene todas las funciones de interacción con el usuario (login, registro, modificar datos, etc.)

import Menu.menus;
import java.util.ArrayList;
import java.util.Scanner;

public class FuncionesUsuario {
    static Scanner sc = new Scanner(System.in);
    static Usuario usuarioActual = null;

    // ===============================
    // Función de login
    // ===============================
   public static void login() {
    int opcion;
    do {
System.out.println("╔════════════════════════════════════════╗");
System.out.println("║                                        ║");
System.out.println("║        ¿YA TIENES UNA CUENTA?          ║");
System.out.println("║                                        ║");
System.out.println("╠════════════════════════════════════════╣");
System.out.println("║                                        ║");
System.out.println("║  1. Sí, ingresar credenciales          ║");
System.out.println("║  2. No, deseo registrarme              ║");
System.out.println("║                                        ║");
System.out.println("╚════════════════════════════════════════╝");
System.out.print(" Selecciona una opción: ");
        opcion = validarNumero(1, 2);

        if (opcion == 2) {
            registro();
            break;
        }

        boolean sesionIniciada = false;

        while (!sesionIniciada) {
    System.out.println("╔════════════════════════════════════════╗");
    System.out.println("║                                        ║");
    System.out.println("║          INICIO DE SESIÓN              ║");
    System.out.println("║                                        ║");
    System.out.println("╚════════════════════════════════════════╝");
    System.out.println("══════════════════════════════════════════");
            System.out.print("Usuario: ");
            String nombre = sc.next();
            System.out.print("Contraseña: ");
            String contrasenia = sc.next();

            Usuario u = CrudUsuario.buscarUsuario(nombre);
            if (u != null && u.getContrasenia().equals(contrasenia)) {
                usuarioActual = u;
                System.out.println("¡Bienvenido, " + u.getUsuario() + "!");
                sesionIniciada = true;

                if (u.getRol().equalsIgnoreCase("admin")) {
                    menus.menuAdmin();
                } else {
                    menus.menuUsuario();
                }

            } else {
                System.out.println(" Usuario no encontrado o contraseña incorrecta.");
                System.out.println("¿Deseas intentarlo de nuevo (1) o registrarte (2)?");
                int reintentar = validarNumero(1, 2);

                if (reintentar == 2) {
                    registro();
                    break;
                }
            }
        }

    } while (opcion != 1);
}


    // ===============================
    // Registro de nuevo usuario
    // ===============================
   public static void registro() {
    Usuario u = new Usuario();
    sc.nextLine(); // Limpiar buffer

    // Validación nombre de usuario (único)
    boolean usuarioExiste;
    do {
    usuarioExiste = false;
    boolean usuarioVacio = true;
    
    do {
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = sc.nextLine().trim(); // trim() elimina espacios en blanco al inicio y final
        
        if (nombreUsuario.isEmpty()) {
            System.out.println("El nombre de usuario no puede estar vacío");
            continue;
        }
        
        usuarioVacio = false;
        
        if (CrudUsuario.buscarUsuario(nombreUsuario) != null) {
            System.out.println("Este nombre de usuario ya existe");
            System.out.println("1. Ingresar otro nombre");
            System.out.println("2. Iniciar sesión");
            System.out.print("Seleccione: ");
            int opcion = validarNumero(1, 2);
            if (opcion == 2) {
                login();
                return;
            }
            usuarioExiste = true;
        } else {
            u.setUsuario(nombreUsuario);
        }
    } while (usuarioVacio);
    
} while (usuarioExiste);

    // Validación correo electrónico (único y formato)
    boolean correoExiste;
    do {
        correoExiste = false;
        System.out.print("Correo electrónico: ");
        String correo = sc.nextLine();
        
        if (!correo.matches("^[\\w.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("Formato inválido. Ejemplo: usuario@dominio.com");
            correoExiste = true;
        } 
        else if (CrudUsuario.buscarUsuarioPorCorreo(correo) != null) {
            System.out.println(" Este correo ya está registrado");
            System.out.println("1. Ingresar otro correo");
            System.out.println("2. Iniciar sesión");
            System.out.print("Seleccione: ");
            int opcion = validarNumero(1, 2);
            if (opcion == 2) {
                login();
                return;
            }
            correoExiste = true;
        } else {
            u.setCorreo(correo);
        }
    } while (correoExiste);

    // Validación contraseña (mínimo 6 caracteres)
    String contrasenia;
    do {
        System.out.print("Contraseña (mínimo 6 caracteres): ");
        contrasenia = sc.nextLine();
        if (contrasenia.length() < 6) {
            System.out.println("La contraseña debe tener al menos 6 caracteres");
        }
    } while (contrasenia.length() < 6);
    u.setContrasenia(contrasenia);

    // Validación edad (1-100 años)
    float edad;
    do {
        System.out.print("Edad (1-100 años): ");
        while (!sc.hasNextFloat()) {
            System.out.println("Por favor introduce un número válido.");
            sc.nextLine();
        }
        edad = sc.nextFloat();
        if (edad < 1 || edad > 100) {
            System.out.println("La edad debe estar entre 1 y 100 años");
        }
    } while (edad < 1 || edad > 100);
    u.setEdad(edad);

    u.setRol("cliente");

    try {
        CrudUsuario.insertarUsuario(u);
        System.out.println("\n Registro exitoso!");
        System.out.println("Ahora puedes iniciar sesión con tus credenciales");
    } catch (Exception e) {
        System.out.println("\n Error al registrar: " + e.getMessage());
    }
    
    login();
}

    // ===============================
    // Ver perfil del usuario
    // ===============================
    public static void verPerfil() {
        System.out.println(usuarioActual);
        System.out.println("¿Deseas modificar algún dato? (1.Sí / 2.No)");
        int op = validarNumero(1, 2);

        if (op == 1) {
            modificarDatosUsuario();
        }
    }

    // ===============================
    // Modificar datos como cliente
    // ===============================
    public static void modificarDatosUsuario() {
System.out.println("╔════════════════════════════════════════╗");
System.out.println("║                                        ║");
System.out.println("║        MODIFICAR DATOS DE USUARIO      ║");
System.out.println("║                                        ║");
System.out.println("╠════════════════════════════════════════╣");
System.out.println("║                                        ║");
System.out.println("║  1.   Cambiar nombre de usuario        ║");
System.out.println("║  2.   Cambiar contraseña               ║");
System.out.println("║  3.   Cambiar correo electrónico       ║");
System.out.println("║  4.   Cambiar edad                     ║");
System.out.println("║  5.   Cancelar                         ║");
System.out.println("║                                        ║");
System.out.println("╚════════════════════════════════════════╝");
System.out.println("══════════════════════════════════════════");
System.out.print(" Seleccione una opción: ");
        int opcion = validarNumero(1, 5);

        switch (opcion) {
            case 1:
                System.out.print("Nuevo usuario: ");
                usuarioActual.setUsuario(sc.next());
                break;
            case 2:
                System.out.print("Nueva contraseña: ");
                usuarioActual.setContrasenia(sc.next());
                break;
            case 3:
                System.out.print("Nuevo correo: ");
                usuarioActual.setCorreo(sc.next());
                break;
            case 4:
                System.out.print("Nueva edad: ");
                while (!sc.hasNextFloat()) {
                    System.out.println("Introduce una edad válida:");
                    sc.nextLine();
                }
                usuarioActual.setEdad(sc.nextFloat());
                break;
            case 5:
                return;
        }

        CrudUsuario.actualizarUsuario(usuarioActual);
    }

    // ===============================
    // Función del admin para modificar usuarios
    // ===============================
 public static void adminModificaUsuario() {
    ArrayList<Usuario> usuarios = CrudUsuario.obtenerUsuarios();
    
    System.out.println("\n══════════════════════════ LISTADO DE USUARIOS ══════════════════════════");
    System.out.println("---------------------------------------------------------------------------");
    System.out.printf("%-6s %-18s %-30s %-6s %-10s%n", 
                     "ID", "USUARIO", "CORREO", "EDAD", "ROL");
    System.out.println("---------------------------------------------------------------------------");

    for (Usuario u : usuarios) {
        System.out.printf("%-6d %-18s %-30s %-6.0f %-10s%n",
                        u.getId(),
                        u.getUsuario(),
                        u.getCorreo(),
                        u.getEdad(),
                        u.getRol());
    }
    
    System.out.println("═══════════════════════════════════════════════════════════════════════════\n");

    System.out.print("Introduce el ID del usuario a modificar: ");
    
    // Validar que se ingrese un número entero
    int id;
    while (true) {
        if (sc.hasNextInt()) {
            id = sc.nextInt();
            break;
        } else {
            System.out.println("Debes ingresar un número entero válido.");
            System.out.print("Introduce el ID del usuario a modificar: ");
            sc.next(); // Limpiar el valor incorrecto
        }
    }
    
    Usuario u = null;

    for (Usuario us : usuarios) {
        if (us.getId() == id) {
            u = us;
            break;
        }
    }

    if (u == null) {
        System.out.println("Usuario no encontrado.");
        return;
    }

    System.out.println("╔════════════════════════════════════════╗");
    System.out.println("║                                        ║");
    System.out.println("║       MODIFICACIÓN DE USUARIO          ║");
    System.out.println("║                                        ║");
    System.out.println("╠════════════════════════════════════════╣");
    System.out.println("║                                        ║");
    System.out.println("║  1. Nombre de usuario                  ║");
    System.out.println("║  2. Contraseña                         ║");
    System.out.println("║  3. Correo electrónico                 ║");
    System.out.println("║  4. Edad                               ║");
    System.out.println("║  5. Rol                                ║");
    System.out.println("║  6. Cancelar                           ║");
    System.out.println("║                                        ║");
    System.out.println("╚════════════════════════════════════════╝");
    System.out.println("══════════════════════════════════════════");
    System.out.print(" Seleccione qué desea modificar: ");
    int opcion = validarNumero(1, 6);

    switch (opcion) {
        case 1:
            System.out.print("Nuevo usuario: ");
            u.setUsuario(sc.next());
            break;
        case 2:
            System.out.print("Nueva contraseña: ");
            u.setContrasenia(sc.next());
            break;
        case 3:
            System.out.print("Nuevo correo: ");
            u.setCorreo(sc.next());
            break;
        case 4:
            System.out.print("Nueva edad: ");
            while (!sc.hasNextFloat()) {
                System.out.println("Por favor introduce un número válido para la edad:");
                sc.next(); // Limpiar el valor incorrecto
            }
            u.setEdad(sc.nextFloat());
            break;
        case 5:
            System.out.println("Selecciona nuevo rol:\n1. Admin\n2. Cliente");
            int rol = validarNumero(1, 2);
            u.setRol(rol == 1 ? "admin" : "cliente");
            break;
        case 6:
            return;
    }

    CrudUsuario.actualizarUsuario(u);
}
    // ===============================
    // Validar solo opciones numéricas válidas
    // ===============================
    public static int validarNumero(int min, int max) {
        int op;
        while (true) {
            if (sc.hasNextInt()) {
                op = sc.nextInt();
                if (op >= min && op <= max) {
                    return op;
                } else {
                    System.out.println("Opción fuera de rango.");
                }
            } else {
                System.out.println("Solo números.");
                sc.next();
            }
        }
    }

    // ===============================
    // Getter del usuario actual logueado
    // ===============================
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    // ===============================
    // Cerrar sesión
    // ===============================
    public static void cerrarSesion() {
        usuarioActual = null;
        System.out.println("Sesión cerrada.");
        login(); // Regresar al login
    }
    // ===============================
// Submenú para gestionar usuarios (Admin)
// ===============================
public static void menuGestionUsuarios() {
    int opcion;
    do {
System.out.println("╔════════════════════════════════════════╗");
System.out.println("║          GESTIÓN DE USUARIOS           ║");
System.out.println("╠════════════════════════════════════════╣");
System.out.println("║ 1. Ver usuarios     2. Editar usuario  ║");
System.out.println("║ 3. Eliminar usuario 4. Nuevo usuario   ║");
System.out.println("║ 5. Volver                              ║");
System.out.println("╚════════════════════════════════════════╝");
System.out.print("Operación [1-5]: ");

        opcion = validarNumero(1, 5);

        switch (opcion) {
            case 1:
                mostrarTodosLosUsuarios();
                break;
            case 2:
                adminModificaUsuario();
                break;
            case 3:
                eliminarUsuarioPorAdmin();
                break;
            case 4:
                registrarUsuarioPorAdmin();
                break;
            case 5:
                System.out.println("Volviendo al menú admin...");
                break;
        }
    } while (opcion != 5);
}

// ===============================
// Mostrar todos los usuarios (para ver o consultar)
// ===============================
public static void mostrarTodosLosUsuarios() {
    ArrayList<Usuario> usuarios = CrudUsuario.obtenerUsuarios();
    
    System.out.println("\n══════════════════════════ LISTADO DE USUARIOS ══════════════════════════");
    System.out.println("---------------------------------------------------------------------------");
    System.out.printf("%-6s %-18s %-30s %-6s %-10s%n", 
                     "ID", "USUARIO", "CORREO", "EDAD", "ROL");
    System.out.println("---------------------------------------------------------------------------");

    for (Usuario u : usuarios) {
        System.out.printf("%-6d %-18s %-30s %-6.0f %-10s%n",
                        u.getId(),
                        u.getUsuario(),
                        u.getCorreo(),
                        u.getEdad(),
                        u.getRol());
    }
    
    System.out.println("═══════════════════════════════════════════════════════════════════════════\n");
}

// ===============================
// Eliminar usuario
// ===============================
public static void eliminarUsuarioPorAdmin() {
    mostrarTodosLosUsuarios();
    System.out.print("Introduce el ID del usuario a eliminar: ");
    int id = validarNumero(1, Integer.MAX_VALUE);

    System.out.println("¿Estás seguro que deseas eliminar este usuario? (1. Sí | 2. No)");
    int confirmar = validarNumero(1, 2);
    if (confirmar == 1) {
        CrudUsuario.eliminarUsuario(id);
    } else {
        System.out.println("Eliminación cancelada.");
    }
}

// ===============================
// Registrar nuevo usuario como admin
// ===============================
public static void registrarUsuarioPorAdmin() {
    Usuario u = new Usuario();
    sc.nextLine(); // limpiar buffer

    System.out.print("Nombre de usuario: ");
    u.setUsuario(sc.nextLine());

    System.out.print("Correo: ");
    u.setCorreo(sc.nextLine());

    System.out.print("Contraseña: ");
    u.setContrasenia(sc.nextLine());

    System.out.print("Edad: ");
    while (!sc.hasNextFloat()) {
        System.out.println("Introduce una edad válida:");
        sc.nextLine();
    }
    u.setEdad(sc.nextFloat());

    System.out.println("Rol del nuevo usuario:");
    System.out.println("1. Admin\n2. Cliente");
    int rol = validarNumero(1, 2);
    u.setRol(rol == 1 ? "admin" : "cliente");

    CrudUsuario.insertarUsuario(u);
    System.out.println(" Usuario registrado por el administrador.");
}

}
