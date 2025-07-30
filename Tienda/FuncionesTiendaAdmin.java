package Tienda;
//Hecho por Gutierrez Figueroa Alexis Alberto
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Usuario.FuncionesUsuario;
import Tienda.CrudVentas;

public class FuncionesTiendaAdmin {
    private static Scanner sc = new Scanner(System.in);

    // Muestra el menú principal de administración de la tienda
    public static void menuTiendaAdmin() {
        int opcion;
        
        do {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║       MENU TIENDA (ADMIN)              ║");
            System.out.println("║                                        ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║                                        ║");
            System.out.println("║  1. Gestionar productos                ║");
            System.out.println("║  2. Ver reporte de ventas              ║");
            System.out.println("║  3. Volver al menu admin               ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print(" Seleccione una opción: ");
            
            opcion = FuncionesUsuario.validarNumero(1, 3);
            
            switch (opcion) {
                case 1:
                    menuGestionProductos();
                    break;
                case 2:
                    verReporteVentas();
                    break;
                case 3:
                    System.out.println("Volviendo al menu admin...");
                    break;
            }
        } while (opcion != 3);
    }

    // Muestra el submenú para la gestión de productos
    private static void menuGestionProductos() {
        int opcion;
        
        do {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║       GESTIÓN DE PRODUCTOS             ║");
            System.out.println("║                                        ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║                                        ║");
            System.out.println("║  1. Agregar producto                   ║");
            System.out.println("║  2. Editar producto                    ║");
            System.out.println("║  3. Eliminar producto                  ║");
            System.out.println("║  4. Listar productos                   ║");
            System.out.println("║  5. Volver al menú anterior            ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print(" Seleccione una opción: ");
            
            opcion = FuncionesUsuario.validarNumero(1, 5);
            
            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    editarProducto();
                    break;
                case 3:
                    eliminarProducto();
                    break;
                case 4:
                    listarProductos();
                    break;
                case 5:
                    System.out.println("Volviendo al menú anterior...");
                    break;
            }
        } while (opcion != 5);
    }

    // Permite al administrador agregar un nuevo producto al sistema
    private static void agregarProducto() {
        sc.nextLine();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║       AGREGAR NUEVO PRODUCTO           ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();
        
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        
        System.out.print("Precio: ");
        double precio = validarDecimal(0.01, Double.MAX_VALUE);
        
        System.out.print("Cantidad en stock: ");
        int stock = FuncionesUsuario.validarNumero(0, Integer.MAX_VALUE);
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║       SELECCIONE CATEGORÍA             ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("1. Equipamiento      ");
        System.out.println("2. Accesorios        ");
        System.out.println("3. Suplementos       ");
        System.out.println("4. Ropa              ");

        System.out.print("Ingrese el número de categoría: ");
        
        int opcionCategoria = FuncionesUsuario.validarNumero(1, 4);
        String categoria;
        
        switch(opcionCategoria) {
            case 1: 
                categoria = "Equipamiento"; 
                break;
            case 2: 
                categoria = "Accesorios"; 
                break;
            case 3: 
                categoria = "Suplementos"; 
                break;
            case 4: 
                categoria = "Ropa"; 
                break;
            default:
                categoria = "";
                break;
        }
        
        Producto nuevoProducto = new Producto(0, nombre, descripcion, precio, stock, categoria);
        
        if (CrudTienda.insertarProducto(nuevoProducto)) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║  Producto agregado exitosamente!       ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
        } else {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║  Error al agregar el producto          ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
        }
    }

    // Permite editar los datos de un producto existente
    private static void editarProducto() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║       LISTADO DE PRODUCTOS             ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        ArrayList<Producto> productos = CrudTienda.obtenerProductos();
        
        if (productos.isEmpty()) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║  No hay productos registrados.         ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            return;
        }
        
        System.out.println("╔══════╦══════════════════════╦══════════╦═══════╦══════════════╗");
        System.out.println("║ ID   ║ Nombre               ║ Precio   ║ Stock ║ Categoría    ║");
        System.out.println("╠══════╬══════════════════════╬══════════╬═══════╬══════════════╣");
        
        for (Producto p : productos) {
            System.out.printf("║ %-4d ║ %-20s ║ $%-7.2f ║ %-5d ║ %-12s ║%n", 
                p.getIdProducto(), 
                p.getNombre(), 
                p.getPrecio(), 
                p.getStock(), 
                p.getCategoria());
        }
        System.out.println("╚══════╩══════════════════════╩══════════╩═══════╩══════════════╝");

        System.out.print("╔════════════════════════════════════════╗\n║ Ingrese el ID del producto a editar: ");
        int id = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
        System.out.println("╚════════════════════════════════════════╝");
        
        Producto producto = CrudTienda.buscarProductoPorId(id);
        if (producto == null) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║ No se encontró un producto con ese ID. ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            return;
        }
        
        sc.nextLine();
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.printf ("║       EDITANDO PRODUCTO ID: %-10d ║%n", id);
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Deje vacío para mantener el valor actual");
        
        System.out.print("Nombre actual: " + producto.getNombre() + "\nNuevo nombre: ");
        String nombre = sc.nextLine();
        if (!nombre.isEmpty()) producto.setNombre(nombre);
        
        System.out.print("\nDescripción actual: " + producto.getDescripcion() + "\nNueva descripción: ");
        String descripcion = sc.nextLine();
        if (!descripcion.isEmpty()) producto.setDescripcion(descripcion);
        
        System.out.print("\nPrecio actual: " + producto.getPrecio() + "\nNuevo precio (0 para no cambiar): ");
        double precio = validarDecimal(0, Double.MAX_VALUE);
        if (precio > 0) producto.setPrecio(precio);
        
        System.out.print("\nStock actual: " + producto.getStock() + "\nNuevo stock (-1 para no cambiar): ");
        int stock = FuncionesUsuario.validarNumero(-1, Integer.MAX_VALUE);
        if (stock >= 0) producto.setStock(stock);
        
        boolean categoriaValida = false;
        do {
            System.out.print("\nCategoría actual: " + producto.getCategoria() + "\nNueva categoría (deje vacío para no cambiar): ");
            String categoria = sc.nextLine();
            
            if (categoria.isEmpty()) {
                categoriaValida = true;
                break;
            }
            
            if (categoria.equals("Equipamiento") || categoria.equals("Accesorios") || 
                categoria.equals("Suplementos") || categoria.equals("Ropa")) {
                producto.setCategoria(categoria);
                categoriaValida = true;
            } else {
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║                                        ║");
                System.out.println("║ Categoría no válida. Las categorías    ║");
                System.out.println("║ permitidas son: Equipamiento,          ║");
                System.out.println("║ Accesorios, Suplementos, Ropa          ║");
                System.out.println("║                                        ║");
                System.out.println("╚════════════════════════════════════════╝");
                System.out.println("¿Desea intentar con otra categoría? (1. Sí / 2. No)");
                int opcion = FuncionesUsuario.validarNumero(1, 2);
                if (opcion == 2) {
                    categoriaValida = true;
                }
            }
        } while (!categoriaValida);
        
        if (CrudTienda.actualizarProducto(producto)) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║  Producto actualizado exitosamente!    ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
        } else {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║  Error al actualizar el producto       ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
        }
    }

    // Permite eliminar un producto del sistema
    private static void eliminarProducto() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║       LISTADO DE PRODUCTOS             ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        ArrayList<Producto> productos = CrudTienda.obtenerProductos();
        
        if (productos.isEmpty()) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║  No hay productos registrados.         ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            return;
        }
        
        System.out.println("╔══════╦══════════════════════╦══════════╦═══════╦══════════════╗");
        System.out.println("║ ID   ║ Nombre               ║ Precio   ║ Stock ║ Categoría    ║");
        System.out.println("╠══════╬══════════════════════╬══════════╬═══════╬══════════════╣");
        
        for (Producto p : productos) {
            System.out.printf("║ %-4d ║ %-20s ║ $%-7.2f ║ %-5d ║ %-12s ║%n", 
                p.getIdProducto(), 
                p.getNombre(), 
                p.getPrecio(), 
                p.getStock(), 
                p.getCategoria());
        }
        System.out.println("╚══════╩══════════════════════╩══════════╩═══════╩══════════════╝");

        System.out.print("╔════════════════════════════════════════╗\n║ Ingrese el ID del producto a eliminar: ");
        int id = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
        System.out.println("╚════════════════════════════════════════╝");
        
        Producto producto = CrudTienda.buscarProductoPorId(id);
        if (producto == null) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║ No se encontró un producto con ese ID. ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            return;
        }
        
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.printf ("║ ¿Eliminar el producto: %-35s   ║%n", producto.getNombre());
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║ 1. Sí                                                        ║");
        System.out.println("║ 2. No                                                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.print(" Seleccione una opción: ");
        int confirmacion = FuncionesUsuario.validarNumero(1, 2);
        
        if (confirmacion == 1) {
            if (CrudTienda.eliminarProducto(id)) {
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║                                        ║");
                System.out.println("║ Producto eliminado exitosamente!       ║");
                System.out.println("║                                        ║");
                System.out.println("╚════════════════════════════════════════╝");
            } else {
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║                                        ║");
                System.out.println("║ Error al eliminar el producto          ║");
                System.out.println("║                                        ║");
                System.out.println("╚════════════════════════════════════════╝");
            }
        } else {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║ Eliminación cancelada.                 ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
        }
    }

    // Muestra todos los productos registrados en el sistema
    private static void listarProductos() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║       LISTADO DE PRODUCTOS             ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        ArrayList<Producto> productos = CrudTienda.obtenerProductos();
        
        if (productos.isEmpty()) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║ No hay productos registrados.          ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            return;
        }
        
        System.out.println("╔══════╦══════════════════════╦══════════╦═══════╦══════════════╗");
        System.out.println("║ ID   ║ Nombre               ║ Precio   ║ Stock ║ Categoría    ║");
        System.out.println("╠══════╬══════════════════════╬══════════╬═══════╬══════════════╣");
        
        for (Producto p : productos) {
            System.out.printf("║ %-4d ║ %-20s ║ $%-7.2f ║ %-5d ║ %-12s ║%n", 
                p.getIdProducto(), 
                p.getNombre(), 
                p.getPrecio(), 
                p.getStock(), 
                p.getCategoria());
        }
        System.out.println("╚══════╩══════════════════════╩══════════╩═══════╩══════════════╝");
    }

    // Muestra el menú de reportes de ventas
    private static void verReporteVentas() {
        int opcion;
        
        do {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║         REPORTES DE VENTAS             ║");
            System.out.println("║                                        ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║                                        ║");
            System.out.println("║  1. Ver historial de ventas            ║");
            System.out.println("║  2. Ver estadísticas generales         ║");
            System.out.println("║  3. Ver detalle de una venta           ║");
            System.out.println("║  4. Volver                             ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print(" Seleccione una opción: ");
            
            opcion = FuncionesUsuario.validarNumero(1, 4);
            
            switch (opcion) {
                case 1:
                    mostrarHistorialVentas();
                    break;
                case 2:
                    mostrarEstadisticasVentas();
                    break;
                case 3:
                    mostrarDetalleVenta();
                    break;
                case 4:
                    break;
            }
        } while (opcion != 4);
    }

    // Muestra estadísticas generales de ventas
    private static void mostrarEstadisticasVentas() {
        List<Map<String, Object>> estadisticas = CrudVentas.obtenerEstadisticasVentas();
        
        if (estadisticas == null || estadisticas.isEmpty()) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║  No hay datos estadísticos disponibles ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            return;
        }
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║       ESTADÍSTICAS GENERALES           ║");
        System.out.println("║                                        ║");
        System.out.println("╠════════════════════════════════════════╣");
        
        Map<String, Object> totales = estadisticas.get(0);
        System.out.printf("║Ventas totales:%-25d║%n", totales.get("totalVentas"));
        System.out.printf("║Ingresos totales:$%-22.2f║%n", totales.get("ingresosTotales"));
        System.out.println("╚════════════════════════════════════════╝");
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║       VENTAS POR CATEGORÍA             ║");
        System.out.println("║                                        ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Categoría      Items     Ingresos      ║");
        System.out.println("╠════════════════════════════════════════╣");
        
        for (int i = 1; i < estadisticas.size(); i++) {
            Map<String, Object> stat = estadisticas.get(i);
            if (stat.containsKey("categoria")) {
                System.out.printf("║%-15s%-10d$%-15.2f║%n",
                    stat.get("categoria"),
                    stat.get("items"),
                    stat.get("ingresos"));
            }
        }
        System.out.println("╚════════════════════════════════════════╝");
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║         VENTAS POR MES                 ║");
        System.out.println("║                                        ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Mes        Ventas    Ingresos          ║");
        System.out.println("╠════════════════════════════════════════╣");
        
        for (int i = 1; i < estadisticas.size(); i++) {
            Map<String, Object> stat = estadisticas.get(i);
            if (stat.containsKey("mes")) {
                System.out.printf("║ %-10s%-10d$%-15.2f   ║%n",
                    stat.get("mes"),
                    stat.get("ventas"),
                    stat.get("ingresos"));
            }
        }
        System.out.println("╚════════════════════════════════════════╝");
    }

    // Muestra el historial completo de ventas realizadas
    private static void mostrarHistorialVentas() {
        List<Map<String, Object>> ventas = CrudVentas.obtenerReporteVentas();
        
        if (ventas == null || ventas.isEmpty()) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║     No hay ventas registradas          ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            return;
        }
        
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                              ║");
        System.out.println("║                            HISTORIAL DE VENTAS                               ║");
        System.out.println("║                                                                              ║");
        System.out.println("╠══════╦════════════╦════════════════════════╦════════════╦════════╦═══════════╣");
        System.out.println("║ ID   ║ Usuario    ║ Fecha                  ║ Total      ║ Prod.  ║ Items     ║");
        System.out.println("╠══════╬════════════╬════════════════════════╬════════════╬════════╬═══════════╣");
        
        for (Map<String, Object> venta : ventas) {
            System.out.printf("║ %-4d ║ %-10s ║ %-20s   ║ $%-9.2f ║ %-6d ║ %-8d  ║%n",
                venta.get("idVenta"),
                venta.get("usuario"),
                venta.get("fecha").toString().substring(0, 19),
                venta.get("total"),
                venta.get("productos"),
                venta.get("items"));
        }
        
        System.out.println("╚══════╩════════════╩════════════════════════╩════════════╩════════╩═══════════╝");
    }

    // Muestra el detalle completo de una venta específica
    private static void mostrarDetalleVenta() {
        // Primero mostrar el historial de ventas
        List<Map<String, Object>> ventas = CrudVentas.obtenerReporteVentas();
        
        if (ventas == null || ventas.isEmpty()) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║     No hay ventas registradas          ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            return;
        }
        
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                              ║");
        System.out.println("║                            HISTORIAL DE VENTAS                               ║");
        System.out.println("║                                                                              ║");
        System.out.println("╠══════╦════════════════════════╦════════════╦═════════════════════════════════╣");
        System.out.println("║ ID   ║ Fecha                  ║ Usuario    ║ Total                           ║");
        System.out.println("╠══════╬════════════════════════╬════════════╬═════════════════════════════════╣");
        
        for (Map<String, Object> venta : ventas) {
            System.out.printf("║ %-4d ║ %-20s   ║ %-10s ║ $%-28.2f   ║%n",
                venta.get("idVenta"),
                venta.get("fecha").toString().substring(0, 19),
                venta.get("usuario"),
                venta.get("total"));
        }
        
        System.out.println("╚══════╩════════════════════════╩════════════╩═════════════════════════════════╝");
        
        // Luego pedir el ID a consultar
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║    CONSULTAR DETALLE DE VENTA          ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print(" Ingrese el ID de la venta a consultar: ");
        int idVenta = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
        
        List<Map<String, Object>> detalle = CrudVentas.obtenerDetalleVenta(idVenta);
        
        if (detalle == null || detalle.isEmpty()) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║  No se encontró la venta o no tiene    ║");
            System.out.println("║  detalles                              ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            return;
        }
        
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf ("║                      DETALLE DE VENTA #%-36d  ║%n", idVenta);
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Producto                      Cantidad   P. Unit.      Subtotal              ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
        
        double total = 0;
        for (Map<String, Object> item : detalle) {
            System.out.printf("║ %-30s %-10d $%-10.2f      $%-10.2f       ║%n",
                item.get("nombre"),
                item.get("cantidad"),
                item.get("precioUnitario"),
                item.get("subtotal"));
            total += (double) item.get("subtotal");
        }
        
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf ("║ TOTAL: %-65.2f     ║%n", total);
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
    }

    // Valida que un número decimal esté dentro de un rango específico
    private static double validarDecimal(double min, double max) {
        double valor;
        while (true) {
            try {
                valor = sc.nextDouble();
                if (valor >= min && valor <= max) {
                    sc.nextLine();
                    return valor;
                } else {
                    System.out.printf("Ingrese un valor entre %.2f y %.2f: ", min, max);
                }
            } catch (Exception e) {
                System.out.print("Entrada inválida. Ingrese un número decimal: ");
                sc.next();
            }
        }
    }
}