package Tienda;

import Usuario.FuncionesUsuario;
import java.util.ArrayList;
import java.util.Scanner;

public class FuncionesTienda {
    private static Scanner sc = new Scanner(System.in); // Scanner para entrada de usuario

    // Menú principal de la tienda
    public static void menuTienda() {
        int opcion;
        Carrito carrito = new Carrito(); // Crea un carrito vacío
        
        do {
            // Mostrar opciones
            System.out.println("\n--- MENÚ TIENDA ---");
            System.out.println("1. Ver productos disponibles");
            System.out.println("2. Buscar producto por nombre");
            System.out.println("3. Buscar producto por categoría");
            System.out.println("4. Ver carrito");
            System.out.println("5. Confirmar compra");
            System.out.println("6. Volver al menú principal");
            
            opcion = FuncionesUsuario.validarNumero(1, 6); // Validar entrada
            
            switch (opcion) {
                case 1: verProductosDisponibles(carrito); break; // Mostrar productos
                case 2: buscarProductoPorNombre(carrito); break; // Buscar por nombre
                case 3: buscarProductoPorCategoria(carrito); break; // Buscar por categoría
                case 4: carrito.mostrarCarrito(); break; // Mostrar carrito
                case 5: carrito.confirmarCompra(); break; // Finalizar compra
                case 6: System.out.println("Volviendo al menú principal..."); break; // Salir
            }
        } while (opcion != 6); // Repetir hasta que elija salir
    }

    // Muestra todos los productos disponibles
    private static void verProductosDisponibles(Carrito carrito) {
        ArrayList<Producto> productos = CrudTienda.obtenerProductos(); // Obtener lista de productos
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles.");
            return;
        }
        
        System.out.println("\n--- PRODUCTOS DISPONIBLES ---");
        productos.forEach(System.out::println); // Mostrar cada producto
        
        // Opción para agregar al carrito
        System.out.println("\n¿Desea agregar algún producto al carrito? (1.Sí / 2.No)");
        if (FuncionesUsuario.validarNumero(1, 2) == 1) {
            System.out.print("Ingrese el ID del producto: ");
            int id = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
            
            Producto seleccionado = CrudTienda.buscarProductoPorId(id); // Buscar producto por ID
            if (seleccionado != null) {
                carrito.agregarProducto(seleccionado); // Agregar al carrito
            } else {
                System.out.println("No se encontró un producto con ese ID.");
            }
        }
    }

    // Busca productos por nombre
    private static void buscarProductoPorNombre(Carrito carrito) {
        System.out.print("Ingrese el nombre o parte del nombre del producto: ");
        sc.nextLine();
        String nombre = sc.nextLine();
        
        ArrayList<Producto> productos = CrudTienda.obtenerProductos();
        ArrayList<Producto> resultados = new ArrayList<>();
        
        // Filtrar productos que coincidan con el nombre
        productos.stream()
            .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            .forEach(resultados::add);
        
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos con ese nombre.");
            return;
        }
        
        System.out.println("\n--- RESULTADOS DE BÚSQUEDA ---");
        resultados.forEach(System.out::println); // Mostrar resultados
        
        agregarAlCarritoDesdeBusqueda(resultados, carrito); // Opción para agregar al carrito
    }

    // Busca productos por categoría
    private static void buscarProductoPorCategoria(Carrito carrito) {
        System.out.println("Categorías disponibles:");
        System.out.println("1. Suplementos");
        System.out.println("2. Ropa");
        System.out.println("3. Accesorios");
        System.out.println("4. Equipamiento");
        System.out.print("Seleccione una categoría: ");
        
        int opcion = FuncionesUsuario.validarNumero(1, 4);
String categoria;
switch (opcion) {
    case 1:
        categoria = "Suplementos";
        break;
    case 2:
        categoria = "Ropa";
        break;
    case 3:
        categoria = "Accesorios";
        break;
    case 4:
        categoria = "Equipamiento";
        break;
    default:
        categoria = "";
        break;
}
        
        ArrayList<Producto> productos = CrudTienda.obtenerProductos();
        ArrayList<Producto> resultados = new ArrayList<>();
        
        // Filtrar productos por categoría
        productos.stream()
            .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
            .forEach(resultados::add);
        
        if (resultados.isEmpty()) {
            System.out.println("No hay productos en esta categoría.");
            return;
        }
        
        System.out.println("\n--- PRODUCTOS EN CATEGORÍA " + categoria.toUpperCase() + " ---");
        resultados.forEach(System.out::println); // Mostrar productos
        
        agregarAlCarritoDesdeBusqueda(resultados, carrito); // Opción para agregar al carrito
    }

    // Función auxiliar para agregar productos al carrito desde resultados de búsqueda
    private static void agregarAlCarritoDesdeBusqueda(ArrayList<Producto> resultados, Carrito carrito) {
        System.out.println("\n¿Desea agregar algún producto al carrito? (1.Sí / 2.No)");
        if (FuncionesUsuario.validarNumero(1, 2) == 1) {
            System.out.print("Ingrese el ID del producto: ");
            int id = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
            
            // Buscar producto por ID en los resultados
            Producto seleccionado = resultados.stream()
                .filter(p -> p.getIdProducto() == id)
                .findFirst()
                .orElse(null);
            
            if (seleccionado != null) {
                carrito.agregarProducto(seleccionado); // Agregar al carrito
            } else {
                System.out.println("El ID no corresponde a los productos mostrados.");
            }
        }
    }
}