package Tienda;

// Importaciones para manejar listas, mapas y entrada de usuario
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Usuario.FuncionesUsuario;
import Tienda.CrudVentas;

public class FuncionesTiendaAdmin {
    // Scanner para leer entrada del usuario
    private static Scanner sc = new Scanner(System.in);

    // Menú principal de administración de tienda
    public static void menuTiendaAdmin() {
        int opcion;
        
        do {
            // Mostrar opciones del menú
            System.out.println("\n--- MENU TIENDA (ADMIN) ---");
            System.out.println("1. Gestionar productos");
            System.out.println("2. Ver reporte de ventas");
            System.out.println("3. Volver al menu admin");
            
            // Validar y leer opción del usuario
            opcion = FuncionesUsuario.validarNumero(1, 3);
            
            switch (opcion) {
                case 1:
                    menuGestionProductos();  // Ir a gestión de productos
                    break;
                case 2:
                    verReporteVentas();  // Mostrar reportes
                    break;
                case 3:
                    System.out.println("Volviendo al menu admin...");  // Salir
                    break;
            }
        } while (opcion != 3);  // Repetir hasta elegir volver
    }

    // Menú de gestión de productos
    private static void menuGestionProductos() {
        int opcion;
        
        do {
            // Mostrar opciones de gestión
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Editar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Listar productos");
            System.out.println("5. Volver al menú anterior");
            
            // Validar y leer opción
            opcion = FuncionesUsuario.validarNumero(1, 5);
            
            switch (opcion) {
                case 1:
                    agregarProducto();  // Añadir nuevo producto
                    break;
                case 2:
                    editarProducto();  // Modificar producto
                    break;
                case 3:
                    eliminarProducto();  // Eliminar producto
                    break;
                case 4:
                    listarProductos();  // Mostrar todos los productos
                    break;
                case 5:
                    System.out.println("Volviendo al menú anterior...");  // Salir
                    break;
            }
        } while (opcion != 5);  // Repetir hasta elegir volver
    }

    // Método para añadir nuevo producto
    private static void agregarProducto() {
        sc.nextLine(); // Limpiar buffer del scanner
        System.out.println("\n--- AGREGAR NUEVO PRODUCTO ---");
        
        // Solicitar datos del producto
        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();
        
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        
        System.out.print("Precio: ");
        double precio = validarDecimal(0.01, Double.MAX_VALUE);
        
        System.out.print("Cantidad en stock: ");
        int stock = FuncionesUsuario.validarNumero(0, Integer.MAX_VALUE);
        
        System.out.print("Categoría: ");
        String categoria = sc.nextLine();
        
        // Crear objeto producto
        Producto nuevoProducto = new Producto(0, nombre, descripcion, precio, stock, categoria);
        
        // Intentar insertar en la base de datos
        if (CrudTienda.insertarProducto(nuevoProducto)) {
            System.out.println("Producto agregado exitosamente!");
        } else {
            System.out.println("Error al agregar el producto");
        }
    }

    // Método para editar producto existente
    private static void editarProducto() {
        // Pedir ID del producto a editar
        System.out.print("\nIngrese el ID del producto a editar: ");
        int id = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
        
        // Buscar producto en la base de datos
        Producto producto = CrudTienda.buscarProductoPorId(id);
        if (producto == null) {
            System.out.println("No se encontró un producto con ese ID.");
            return;
        }
        
        sc.nextLine(); // Limpiar buffer
        
        // Mostrar menú de edición
        System.out.println("\n--- EDITANDO PRODUCTO ID: " + id + " ---");
        System.out.println("Deje vacío para mantener el valor actual");
        
        // Editar nombre
        System.out.print("Nombre actual: " + producto.getNombre() + "\nNuevo nombre: ");
        String nombre = sc.nextLine();
        if (!nombre.isEmpty()) {
            producto.setNombre(nombre);
        }
        
        // Editar descripción
        System.out.print("\nDescripción actual: " + producto.getDescripcion() + "\nNueva descripción: ");
        String descripcion = sc.nextLine();
        if (!descripcion.isEmpty()) {
            producto.setDescripcion(descripcion);
        }
        
        // Editar precio
        System.out.print("\nPrecio actual: " + producto.getPrecio() + "\nNuevo precio (0 para no cambiar): ");
        double precio = validarDecimal(0, Double.MAX_VALUE);
        if (precio > 0) {
            producto.setPrecio(precio);
        }
        
        // Editar stock
        System.out.print("\nStock actual: " + producto.getStock() + "\nNuevo stock (-1 para no cambiar): ");
        int stock = FuncionesUsuario.validarNumero(-1, Integer.MAX_VALUE);
        if (stock >= 0) {
            producto.setStock(stock);
        }
        
        // Editar categoría
        System.out.print("\nCategoría actual: " + producto.getCategoria() + "\nNueva categoría: ");
        String categoria = sc.nextLine();
        if (!categoria.isEmpty()) {
            producto.setCategoria(categoria);
        }
        
        // Intentar actualizar en la base de datos
        if (CrudTienda.actualizarProducto(producto)) {
            System.out.println("Producto actualizado exitosamente!");
        } else {
            System.out.println("Error al actualizar el producto");
        }
    }

    // Método para eliminar producto
    private static void eliminarProducto() {
        // Pedir ID del producto a eliminar
        System.out.print("\nIngrese el ID del producto a eliminar: ");
        int id = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
        
        // Buscar producto en la base de datos
        Producto producto = CrudTienda.buscarProductoPorId(id);
        if (producto == null) {
            System.out.println("No se encontró un producto con ese ID.");
            return;
        }
        
        // Pedir confirmación
        System.out.println("¿Está seguro que desea eliminar el producto: " + producto.getNombre() + "? (1.Sí / 2.No)");
        int confirmacion = FuncionesUsuario.validarNumero(1, 2);
        
        // Eliminar si se confirma
        if (confirmacion == 1) {
            if (CrudTienda.eliminarProducto(id)) {
                System.out.println("Producto eliminado exitosamente!");
            } else {
                System.out.println("Error al eliminar el producto");
            }
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    // Método para listar todos los productos
    private static void listarProductos() {
        System.out.println("\n--- LISTADO DE PRODUCTOS ---");
        // Obtener productos de la base de datos
        ArrayList<Producto> productos = CrudTienda.obtenerProductos();
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        
        // Mostrar encabezados
        System.out.println("ID  Nombre                Precio   Stock  Categoría");
        System.out.println("--------------------------------------------------");
        
        // Mostrar cada producto formateado
        for (Producto p : productos) {
            System.out.printf("%-4d%-20s$%-8.2f%-7d%-15s\n", 
                p.getIdProducto(), 
                p.getNombre(), 
                p.getPrecio(), 
                p.getStock(), 
                p.getCategoria());
        }
    }

    // Menú de reportes de ventas
    private static void verReporteVentas() {
        int opcion;
        
        do {
            // Mostrar opciones de reportes
            System.out.println("\n--- REPORTES DE VENTAS ---");
            System.out.println("1. Ver historial de ventas");
            System.out.println("2. Ver estadísticas generales");
            System.out.println("3. Ver detalle de una venta");
            System.out.println("4. Volver");
            
            // Validar y leer opción
            opcion = FuncionesUsuario.validarNumero(1, 4);
            
            switch (opcion) {
                case 1:
                    mostrarHistorialVentas();  // Mostrar historial completo
                    break;
                case 2:
                    mostrarEstadisticasVentas();  // Mostrar estadísticas
                    break;
                case 3:
                    mostrarDetalleVenta();  // Mostrar detalle de venta específica
                    break;
                case 4:
                    break;  // Salir
            }
        } while (opcion != 4);  // Repetir hasta elegir volver
    }

    // Mostrar estadísticas de ventas
    private static void mostrarEstadisticasVentas() {
        // Obtener estadísticas de la base de datos
        List<Map<String, Object>> estadisticas = CrudVentas.obtenerEstadisticasVentas();
        
        if (estadisticas == null || estadisticas.isEmpty()) {
            System.out.println("\nNo hay datos estadísticos disponibles.");
            return;
        }
        
        // Mostrar totales generales
        System.out.println("\n--- ESTADÍSTICAS GENERALES ---");
        Map<String, Object> totales = estadisticas.get(0);
        System.out.println("Ventas totales: " + totales.get("totalVentas"));
        System.out.printf("Ingresos totales: $%.2f\n", totales.get("ingresosTotales"));
        
        // Mostrar ventas por categoría
        System.out.println("\n--- VENTAS POR CATEGORÍA ---");
        System.out.println("Categoría      Items     Ingresos");
        System.out.println("----------------------------------");
        
        for (int i = 1; i < estadisticas.size(); i++) {
            Map<String, Object> stat = estadisticas.get(i);
            if (stat.containsKey("categoria")) {
                System.out.printf("%-15s%-10d$%-15.2f\n",
                    stat.get("categoria"),
                    stat.get("items"),
                    stat.get("ingresos"));
            }
        }
        
        // Mostrar ventas por mes
        System.out.println("\n--- VENTAS POR MES ---");
        System.out.println("Mes        Ventas    Ingresos");
        System.out.println("----------------------------------");
        
        for (int i = 1; i < estadisticas.size(); i++) {
            Map<String, Object> stat = estadisticas.get(i);
            if (stat.containsKey("mes")) {
                System.out.printf("%-10s%-10d$%-15.2f\n",
                    stat.get("mes"),
                    stat.get("ventas"),
                    stat.get("ingresos"));
            }
        }
    }

    // Mostrar historial completo de ventas
    private static void mostrarHistorialVentas() {
        // Obtener ventas de la base de datos
        List<Map<String, Object>> ventas = CrudVentas.obtenerReporteVentas();
        
        if (ventas == null || ventas.isEmpty()) {
            System.out.println("\nNo hay ventas registradas.");
            return;
        }
        
        // Mostrar encabezados
        System.out.println("\n--- HISTORIAL DE VENTAS ---");
        System.out.println("ID      Usuario        Fecha                Total     Productos Items");
        System.out.println("------------------------------------------------------------");
        
        // Mostrar cada venta formateada
        for (Map<String, Object> venta : ventas) {
            System.out.printf("%-8d%-15s%-20s$%-9.2f%-10d%-8d\n",
                venta.get("idVenta"),
                venta.get("usuario"),
                venta.get("fecha").toString().substring(0, 19),
                venta.get("total"),
                venta.get("productos"),
                venta.get("items"));
        }
    }

    // Mostrar detalle de una venta específica
    private static void mostrarDetalleVenta() {
        // Pedir ID de la venta
        System.out.print("\nIngrese el ID de la venta a consultar: ");
        int idVenta = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
        
        // Obtener detalle de la base de datos
        List<Map<String, Object>> detalle = CrudVentas.obtenerDetalleVenta(idVenta);
        
        if (detalle == null || detalle.isEmpty()) {
            System.out.println("No se encontró la venta o no tiene detalles.");
            return;
        }
        
        // Mostrar encabezados
        System.out.println("\n--- DETALLE DE VENTA #" + idVenta + " ---");
        System.out.println("Producto                      Cantidad P. Unit.  Subtotal");
        System.out.println("--------------------------------------------------");
        
        // Calcular y mostrar cada item de la venta
        double total = 0;
        for (Map<String, Object> item : detalle) {
            System.out.printf("%-30s%-8d$%-9.2f$%-9.2f\n",
                item.get("nombre"),
                item.get("cantidad"),
                item.get("precioUnitario"),
                item.get("subtotal"));
            total += (double) item.get("subtotal");
        }
        
        // Mostrar total
        System.out.println("--------------------------------------------------");
        System.out.printf("%-48s$%-9.2f\n", "TOTAL:", total);
    }

    // Validar entrada de número decimal
    private static double validarDecimal(double min, double max) {
        double valor;
        while (true) {
            try {
                valor = sc.nextDouble();
                if (valor >= min && valor <= max) {
                    sc.nextLine(); // Limpiar buffer
                    return valor;
                } else {
                    System.out.printf("Ingrese un valor entre %.2f y %.2f: ", min, max);
                }
            } catch (Exception e) {
                System.out.print("Entrada inválida. Ingrese un número decimal: ");
                sc.next(); // Limpiar buffer
            }
        }
    }
}