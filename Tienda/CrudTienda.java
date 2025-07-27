package Tienda;

import java.sql.*;
import java.util.ArrayList;
import Conexion.conn;

public class CrudTienda {

    // Inserta un nuevo producto en la BD
    public static boolean insertarProducto(Producto p) {
        try (Connection con = conn.conectarBD()) {
            String sql = "INSERT INTO productos (nombre, descripcion, precio, stock, categoria) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            // Asigna valores a los parámetros
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getCategoria());
            
            // Ejecuta y verifica si se insertó
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar producto: " + e.getMessage());
            return false;
        }
    }

    // Obtiene todos los productos de la BD
    public static ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> lista = new ArrayList<>();
        try (Connection con = conn.conectarBD()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM productos");
            
            // Recorre resultados y crea objetos Producto
            while (rs.next()) {
                lista.add(new Producto(
                    rs.getInt("idProducto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getString("categoria")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }
        return lista;
    }

    // Busca un producto por su ID
    public static Producto buscarProductoPorId(int id) {
        try (Connection con = conn.conectarBD()) {
            String sql = "SELECT * FROM productos WHERE idProducto = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            // Si encuentra resultados, crea el objeto Producto
            if (rs.next()) {
                return new Producto(
                    rs.getInt("idProducto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getString("categoria")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar producto: " + e.getMessage());
        }
        return null;
    }

    // Actualiza un producto existente
    public static boolean actualizarProducto(Producto p) {
        try (Connection con = conn.conectarBD()) {
            String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=?, categoria=? WHERE idProducto=?";
            PreparedStatement ps = con.prepareStatement(sql);
            // Asigna nuevos valores
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getCategoria());
            ps.setInt(6, p.getIdProducto());
            
            // Ejecuta y verifica si se actualizó
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    // Elimina un producto por ID
    public static boolean eliminarProducto(int id) {
        try (Connection con = conn.conectarBD()) {
            String sql = "DELETE FROM productos WHERE idProducto=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            // Ejecuta y verifica si se eliminó
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
}