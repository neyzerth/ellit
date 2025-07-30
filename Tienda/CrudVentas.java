package Tienda;
//Hecho por Gutierrez Figueroa Alexis Alberto
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Conexion.conn;

public class CrudVentas {
    
    // Obtiene un reporte resumido de todas las ventas
    public static List<Map<String, Object>> obtenerReporteVentas() {
        List<Map<String, Object>> reporte = new ArrayList<>();
        
        try (Connection con = conn.conectarBD()) {
            // Consulta SQL para obtener ventas con datos de usuario y conteo de productos
            String sql = "SELECT v.idVenta, u.usuario, v.fecha, v.total, " +
                         "COUNT(d.idProducto) as productos, SUM(d.cantidad) as items " +
                         "FROM ventas v " +
                         "JOIN usuarios u ON v.idUsuario = u.id " +
                         "JOIN detalle_ventas d ON v.idVenta = d.idVenta " +
                         "GROUP BY v.idVenta " +
                         "ORDER BY v.fecha DESC";
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            // Mapear resultados a estructura de Map
            while (rs.next()) {
                Map<String, Object> venta = new HashMap<>();
                venta.put("idVenta", rs.getInt("idVenta"));
                venta.put("usuario", rs.getString("usuario"));
                venta.put("fecha", rs.getTimestamp("fecha"));
                venta.put("total", rs.getDouble("total"));
                venta.put("productos", rs.getInt("productos"));
                venta.put("items", rs.getInt("items"));
                reporte.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reporte de ventas: " + e.getMessage());
        }
        
        return reporte;
    }
    
    // Obtiene estadísticas completas de ventas (totales, por categoría y por mes)
    public static List<Map<String, Object>> obtenerEstadisticasVentas() {
        List<Map<String, Object>> estadisticas = new ArrayList<>();
        
        try (Connection con = conn.conectarBD()) {
            // 1. Consulta para totales generales
            String sqlTotales = "SELECT COUNT(*) as totalVentas, SUM(total) as ingresosTotales FROM ventas";
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlTotales);
            
            if (rs.next()) {
                Map<String, Object> totales = new HashMap<>();
                totales.put("totalVentas", rs.getInt("totalVentas"));
                totales.put("ingresosTotales", rs.getDouble("ingresosTotales"));
                estadisticas.add(totales);
            }
            
            // 2. Consulta para ventas por categoría
            String sqlCategorias = "SELECT p.categoria, SUM(d.cantidad) as items, " +
                                  "SUM(d.precioUnitario * d.cantidad) as ingresos " +
                                  "FROM detalle_ventas d " +
                                  "JOIN productos p ON d.idProducto = p.idProducto " +
                                  "GROUP BY p.categoria";
            
            rs = st.executeQuery(sqlCategorias);
            
            List<Map<String, Object>> porCategoria = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> categoria = new HashMap<>();
                categoria.put("categoria", rs.getString("categoria"));
                categoria.put("items", rs.getInt("items"));
                categoria.put("ingresos", rs.getDouble("ingresos"));
                porCategoria.add(categoria);
            }
            estadisticas.addAll(porCategoria);
            
            // 3. Consulta para ventas por mes
            String sqlMensual = "SELECT DATE_FORMAT(v.fecha, '%Y-%m') as mes, " +
                               "COUNT(*) as ventas, SUM(v.total) as ingresos " +
                               "FROM ventas v " +
                               "GROUP BY DATE_FORMAT(v.fecha, '%Y-%m') " +
                               "ORDER BY mes DESC";
            
            rs = st.executeQuery(sqlMensual);
            
            List<Map<String, Object>> porMes = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> mes = new HashMap<>();
                mes.put("mes", rs.getString("mes"));
                mes.put("ventas", rs.getInt("ventas"));
                mes.put("ingresos", rs.getDouble("ingresos"));
                porMes.add(mes);
            }
            estadisticas.addAll(porMes);
            
        } catch (SQLException e) {
            System.out.println("Error al obtener estadisticas de ventas: " + e.getMessage());
        }
        
        return estadisticas;
    }
    
    // Obtiene el detalle completo de una venta específica
    public static List<Map<String, Object>> obtenerDetalleVenta(int idVenta) {
        List<Map<String, Object>> detalle = new ArrayList<>();
        
        try (Connection con = conn.conectarBD()) {
            // Consulta SQL para productos vendidos en una venta específica
            String sql = "SELECT p.nombre, d.cantidad, d.precioUnitario, " +
                         "(d.cantidad * d.precioUnitario) as subtotal " +
                         "FROM detalle_ventas d " +
                         "JOIN productos p ON d.idProducto = p.idProducto " +
                         "WHERE d.idVenta = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);
            ResultSet rs = ps.executeQuery();
            
            // Mapear cada producto del detalle
            while (rs.next()) {
                Map<String, Object> item = new HashMap<>();
                item.put("nombre", rs.getString("nombre"));
                item.put("cantidad", rs.getInt("cantidad"));
                item.put("precioUnitario", rs.getDouble("precioUnitario"));
                item.put("subtotal", rs.getDouble("subtotal"));
                detalle.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener detalle de venta: " + e.getMessage());
        }
        
        return detalle;
    }
    
    // Registra una nueva venta y sus detalles en la base de datos (transacción)
    public static boolean registrarVenta(int idUsuario, ArrayList<Producto> productos) {
        Connection con = null;
        try {
            // 1. Establecer conexión y desactivar auto-commit
            con = conn.conectarBD();
            con.setAutoCommit(false);
            
            // 2. Calcular total de la venta
            double total = 0;
            for (Producto p : productos) {
                total += p.getPrecio();
            }
            
            // 3. Insertar registro en tabla ventas
            String sqlVenta = "INSERT INTO ventas (idUsuario, total) VALUES (?, ?)";
            PreparedStatement psVenta = con.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            psVenta.setInt(1, idUsuario);
            psVenta.setDouble(2, total);
            psVenta.executeUpdate();
            
            // 4. Obtener ID de la venta recién creada
            int idVenta = 0;
            ResultSet rs = psVenta.getGeneratedKeys();
            if (rs.next()) {
                idVenta = rs.getInt(1);
            }
            
            // 5. Insertar detalles de la venta (batch)
            String sqlDetalle = "INSERT INTO detalle_ventas (idVenta, idProducto, cantidad, precioUnitario) VALUES (?, ?, ?, ?)";
            PreparedStatement psDetalle = con.prepareStatement(sqlDetalle);
            
            for (Producto p : productos) {
                psDetalle.setInt(1, idVenta);
                psDetalle.setInt(2, p.getIdProducto());
                psDetalle.setInt(3, 1); // Cantidad fija en 1
                psDetalle.setDouble(4, p.getPrecio());
                psDetalle.addBatch();
                
                // 6. Actualizar stock de cada producto
                actualizarStock(con, p.getIdProducto(), -1);
            }
            
            psDetalle.executeBatch();
            con.commit(); // Confirmar transacción
            return true;
            
        } catch (SQLException e) {
            // Manejo de errores (rollback)
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al revertir transacción: " + ex.getMessage());
            }
            System.err.println("Error al registrar venta: " + e.getMessage());
            return false;
        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error al restaurar auto-commit: " + e.getMessage());
            }
        }
    }

    // Método auxiliar para actualizar el stock de un producto
    private static void actualizarStock(Connection con, int idProducto, int cantidad) throws SQLException {
        String sql = "UPDATE productos SET stock = stock + ? WHERE idProducto = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, cantidad);
        ps.setInt(2, idProducto);
        ps.executeUpdate();
    }
}