package Tienda;

import java.util.ArrayList;

public class Carrito {
    private ArrayList<Producto> items = new ArrayList<>(); // Lista de productos en el carrito

    // Agrega un producto al carrito
    public void agregarProducto(Producto producto) {
        items.add(producto);
        System.out.println("Producto agregado al carrito: " + producto.getNombre());
    }

    // Muestra el contenido del carrito y el total
    public void mostrarCarrito() {
        if (items.isEmpty()) {
            System.out.println("El carrito está vacío");
            return;
        }

        System.out.println("\n--- CARRITO DE COMPRAS ---");
        double total = 0;
        
        // Recorre cada producto y suma precios
        for (Producto p : items) {
            System.out.println(p);
            total += p.getPrecio();
        }
        
        System.out.printf("\nTOTAL: $%.2f\n", total);
    }

    // Confirma la compra y registra la venta
    public boolean confirmarCompra() {
        if (items.isEmpty()) {
            System.out.println("No hay productos en el carrito");
            return false;
        }

        // Obtiene el ID del usuario logueado
        int idUsuario = Usuario.FuncionesUsuario.getUsuarioActual().getId();
        
        // Intenta registrar la venta en la base de datos
        if (CrudVentas.registrarVenta(idUsuario, items)) {
            System.out.println("Compra confirmada. ¡Gracias por su compra!");
            items.clear(); // Limpia el carrito después de la compra
            return true;
        } else {
            System.out.println("Error al procesar la compra.");
            return false;
        }
    }
}