package Tienda;

public class Producto {
    // Atributos de la clase Producto
    private int idProducto;        // Identificador único del producto
    private String nombre;         // Nombre del producto
    private String descripcion;    // Descripción detallada
    private double precio;         // Precio unitario
    private int stock;             // Cantidad disponible
    private String categoria;      // Categoría a la que pertenece

    // Constructor vacío (para inicialización básica)
    public Producto() {}

    // Constructor completo (inicializa todos los atributos)
    public Producto(int idProducto, String nombre, String descripcion, 
                   double precio, int stock, String categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    // --- Getters (obtener valores) ---
    public int getIdProducto() { return idProducto; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getCategoria() { return categoria; }

    // --- Setters (modificar valores) ---
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    // Representación textual del producto (para impresión/debug)
    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Precio: $%.2f | Stock: %d | Categoría: %s",
                idProducto, nombre, precio, stock, categoria);
    }
}