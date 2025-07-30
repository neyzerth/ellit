package Tienda;
//Hecho por Gutierrez Figueroa Alexis Alberto
import java.util.ArrayList;
import java.util.Scanner;
import Usuario.FuncionesUsuario;

public class FuncionesTienda {
    private static Scanner sc = new Scanner(System.in);

    // Muestra el menú principal de la tienda y maneja las opciones
    public static void menuTienda() {
        int opcion;
        Carrito carrito = new Carrito();
        
        do {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║             MENÚ TIENDA                ║");
            System.out.println("║                                        ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║                                        ║");
            System.out.println("║  1. Ver productos disponibles          ║");
            System.out.println("║  2. Buscar producto por nombre         ║");
            System.out.println("║  3. Buscar producto por categoría      ║");
            System.out.println("║  4. Ver carrito                        ║");
            System.out.println("║  5. Volver al menú principal           ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print(" Seleccione una opción: ");
            
            opcion = FuncionesUsuario.validarNumero(1, 5);
            
            switch (opcion) {
                case 1: 
                    verProductosDisponibles(carrito); 
                    break;
                case 2: 
                    buscarProductoPorNombre(carrito); 
                    break;
                case 3: 
                    buscarProductoPorCategoria(carrito); 
                    break;
                case 4: 
                    carrito.mostrarCarrito(); 
                    break;
                case 5: 
                    System.out.println("Volviendo al menú principal..."); 
                    break;
            }
        } while (opcion != 5);
    }

    // Muestra todos los productos disponibles y permite agregarlos al carrito
    private static void verProductosDisponibles(Carrito carrito) {
        ArrayList<Producto> productos = CrudTienda.obtenerProductos();
        
        if (productos.isEmpty()) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║   No hay productos disponibles         ║");
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
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║ ¿Desea agregar algún producto al       ║");
        System.out.println("║ carrito? (1.Sí / 2.No)                 ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print(" Seleccione una opción: ");
        
        if (FuncionesUsuario.validarNumero(1, 2) == 1) {
            System.out.print("╔════════════════════════════════════════╗\n║ Ingrese el ID del producto: ");
            int id = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
            System.out.println("╚════════════════════════════════════════╝");
            
            Producto seleccionado = CrudTienda.buscarProductoPorId(id);
            if (seleccionado != null) {
                carrito.agregarProducto(seleccionado);
            } else {
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║                                        ║");
                System.out.println("║ No se encontró un producto con ese ID. ║");
                System.out.println("║                                        ║");
                System.out.println("╚════════════════════════════════════════╝");
            }
        }
    }

    // Busca productos que coincidan con un nombre o parte del nombre
    private static void buscarProductoPorNombre(Carrito carrito) {
        System.out.print("╔════════════════════════════════════════╗\n║ Ingrese el nombre o parte del nombre: ");
        String nombre = sc.nextLine();
        System.out.println("╚════════════════════════════════════════╝");
        
        ArrayList<Producto> productos = CrudTienda.obtenerProductos();
        ArrayList<Producto> resultados = new ArrayList<>();
        
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(p);
            }
        }
        
        if (resultados.isEmpty()) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║ No se encontraron productos con ese    ║");
            System.out.println("║ nombre.                                ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            return;
        }
        
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                              ║");
        System.out.printf("║               RESULTADOS DE BÚSQUEDA: \"%-15s\"                         ║%n", nombre);
        System.out.println("║                                                                              ║");
        System.out.println("╠══════╦══════════════════════╦══════════╦═══════╦══════════════╗");
        System.out.println("║ ID   ║ Nombre               ║ Precio   ║ Stock ║ Categoría    ║");
        System.out.println("╠══════╬══════════════════════╬══════════╬═══════╬══════════════╣");
        
        for (Producto p : resultados) {
            System.out.printf("║ %-4d ║ %-20s ║ $%-7.2f ║ %-5d ║ %-12s ║%n",
                p.getIdProducto(),
                p.getNombre(),
                p.getPrecio(),
                p.getStock(),
                p.getCategoria());
        }
        
        System.out.println("╚══════╩══════════════════════╩══════════╩═══════╩══════════════╝");
        
        agregarAlCarritoDesdeBusqueda(resultados, carrito);
    }

    // Busca productos por categoría seleccionada
    private static void buscarProductoPorCategoria(Carrito carrito) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║        CATEGORÍAS DISPONIBLES          ║");
        System.out.println("║                                        ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║                                        ║");
        System.out.println("║  1. Suplementos                        ║");
        System.out.println("║  2. Ropa                               ║");
        System.out.println("║  3. Accesorios                         ║");
        System.out.println("║  4. Equipamiento                       ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print(" Seleccione una categoría: ");
        
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
        
        productos.stream()
            .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
            .forEach(resultados::add);
        
        if (resultados.isEmpty()) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║ No hay productos en esta categoría.    ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            return;
        }
        
        System.out.println("╔══════╦══════════════════════╦══════════╦═══════╦══════════════╗");
        System.out.println("║ ID   ║ Nombre               ║ Precio   ║ Stock ║ Categoría    ║");
        System.out.println("╠══════╬══════════════════════╬══════════╬═══════╬══════════════╣");
        
        for (Producto p : resultados) {
            System.out.printf("║ %-4d ║ %-20s ║ $%-7.2f ║ %-5d ║ %-12s ║%n",
                p.getIdProducto(),
                p.getNombre(),
                p.getPrecio(),
                p.getStock(),
                p.getCategoria());
        }
        
        System.out.println("╚══════╩══════════════════════╩══════════╩═══════╩══════════════╝");
        
        agregarAlCarritoDesdeBusqueda(resultados, carrito);
    }

    // Permite agregar productos al carrito desde los resultados de búsqueda
    private static void agregarAlCarritoDesdeBusqueda(ArrayList<Producto> resultados, Carrito carrito) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║ ¿Desea agregar algún producto al       ║");
        System.out.println("║ carrito? (1.Sí / 2.No)                 ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print(" Seleccione una opción: ");
        
        if (FuncionesUsuario.validarNumero(1, 2) == 1) {
            System.out.print("╔════════════════════════════════════════╗\n║ Ingrese el ID del producto: ");
            int id = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
            System.out.println("╚════════════════════════════════════════╝");
            
            Producto seleccionado = resultados.stream()
                .filter(p -> p.getIdProducto() == id)
                .findFirst()
                .orElse(null);
            
            if (seleccionado != null) {
                carrito.agregarProducto(seleccionado);
            } else {
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║                                        ║");
                System.out.println("║ El ID no corresponde a los productos   ║");
                System.out.println("║ mostrados.                             ║");
                System.out.println("║                                        ║");
                System.out.println("╚════════════════════════════════════════╝");
            }
        }
    }

    // Clase interna para manejar el carrito de compras
    public static class Carrito {
        private ArrayList<Producto> productos;
        private double total;

        public Carrito() {
            productos = new ArrayList<>();
            total = 0.0;
        }

        // Agrega un producto al carrito y actualiza el total
        public void agregarProducto(Producto producto) {
            productos.add(producto);
            total += producto.getPrecio();
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║ Producto agregado al carrito           ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
        }

        // Muestra el contenido actual del carrito
        public void mostrarCarrito() {
            if (productos.isEmpty()) {
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║                                        ║");
                System.out.println("║   El carrito está vacío                ║");
                System.out.println("║                                        ║");
                System.out.println("╚════════════════════════════════════════╝");
                return;
            }

            System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                              ║");
            System.out.println("║                              CONTENIDO DEL CARRITO                           ║");
            System.out.println("║                                                                              ║");
            System.out.println("╠══════╦══════════════════════╦══════════╦═══════╦═══════════════╦═════════════╣");
            System.out.println("║ ID   ║ Nombre               ║ Precio   ║ Cant. ║ Subtotal      ║ Categoría   ║");
            System.out.println("╠══════╬══════════════════════╬══════════╬═══════╬═══════════════╬═════════════╣");

            for (Producto p : productos) {
                System.out.printf("║ %-4d ║ %-20s ║ $%-7.2f ║ %-5d ║ $%-10.2f   ║ %-11s ║%n",
                    p.getIdProducto(),
                    p.getNombre(),
                    p.getPrecio(),
                    1, // Cantidad
                    p.getPrecio(), // Subtotal
                    p.getCategoria());
            }

            System.out.println("╠══════╩══════════════════════╩══════════╩═══════╩═══════════════╩═════════════╣");
            System.out.printf("║ TOTAL: $%-65.2f    ║%n", total);
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");

            // Opciones para el carrito
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║ 1. Eliminar producto del carrito       ║");
            System.out.println("║ 2. Confirmar compra                    ║");
            System.out.println("║ 3. Volver al menú de tienda            ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print(" Seleccione una opción: ");

            int opcion = FuncionesUsuario.validarNumero(1, 3);
            switch (opcion) {
                case 1:
                    System.out.print("╔════════════════════════════════════════╗\n║ Ingrese el ID del producto a eliminar: ");
                    int idEliminar = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
                    System.out.println("╚════════════════════════════════════════╝");
                    eliminarProducto(idEliminar);
                    break;
                case 2:
                    confirmarCompra();
                    break;
                case 3:
                    break;
            }
        }

        // Elimina un producto del carrito por su ID
        private void eliminarProducto(int id) {
            Producto aEliminar = null;
            for (Producto p : productos) {
                if (p.getIdProducto() == id) {
                    aEliminar = p;
                    break;
                }
            }

            if (aEliminar != null) {
                productos.remove(aEliminar);
                total -= aEliminar.getPrecio();
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║                                        ║");
                System.out.println("║ Producto eliminado del carrito         ║");
                System.out.println("║                                        ║");
                System.out.println("╚════════════════════════════════════════╝");
            } else {
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║                                        ║");
                System.out.println("║ No se encontró el producto en el       ║");
                System.out.println("║ carrito.                               ║");
                System.out.println("║                                        ║");
                System.out.println("╚════════════════════════════════════════╝");
            }
        }

        // Confirma la compra y vacía el carrito
        public void confirmarCompra() {
            if (productos.isEmpty()) {
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║                                        ║");
                System.out.println("║ El carrito está vacío                  ║");
                System.out.println("║                                        ║");
                System.out.println("╚════════════════════════════════════════╝");
                return;
            }

            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║ ¿Confirmar compra por $" + String.format("%.2f", total) + "? (1.Sí / 2.No) ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print(" Seleccione una opción: ");

            if (FuncionesUsuario.validarNumero(1, 2) == 1) {
                System.out.println("╔════════════════════════════════════════╗");
                System.out.println("║                                        ║");
                System.out.println("║ Compra realizada con éxito!            ║");
                System.out.println("║ Gracias por su compra.                 ║");
                System.out.println("║                                        ║");
                System.out.println("╚════════════════════════════════════════╝");
                productos.clear();
                total = 0.0;
            }
        }
    }
}