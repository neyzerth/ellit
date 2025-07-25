package Comentarios;
// ===============================
// Clase CrudComentarios.java
// ===============================
// Encargada de conectarse a la base de datos y realizar operaciones CRUD para comentarios
import Conexion.conn;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class CrudComentarios {

    // ===============================
    // Insertar nuevo comentario
    // ===============================
    public static void insertarComentario(Comentario c) {
        try (Connection con = conn.conectarBD()) {
            String sql = "INSERT INTO comentarios (usuarioId, texto, calificacion, fecha) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getUsuarioId());
            ps.setString(2, c.getTexto());
            ps.setInt(3, c.getCalificacion());
            ps.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
            ps.executeUpdate();
            System.out.println("Comentario registrado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al registrar comentario: " + e.getMessage());
        }
    }

    // ===============================
    // Obtener todos los comentarios con nombres de usuario
    // ===============================
    public static ArrayList<Comentario> obtenerTodosLosComentarios() {
        ArrayList<Comentario> lista = new ArrayList<>();
        try (Connection con = conn.conectarBD()) {
            String sql = "SELECT c.*, u.usuario as usuarioNombre FROM comentarios c " +
                         "JOIN usuarios u ON c.usuarioId = u.id ORDER BY c.fecha DESC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Comentario c = new Comentario(
                    rs.getInt("id"),
                    rs.getInt("usuarioId"),
                    rs.getString("usuarioNombre"),
                    rs.getString("texto"),
                    rs.getInt("calificacion"),
                    rs.getTimestamp("fecha")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener comentarios: " + e.getMessage());
        }
        return lista;
    }

    // ===============================
    // Obtener comentarios de un usuario específico
    // ===============================
    public static ArrayList<Comentario> obtenerComentariosPorUsuario(int usuarioId) {
        ArrayList<Comentario> lista = new ArrayList<>();
        try (Connection con = conn.conectarBD()) {
            String sql = "SELECT c.*, u.usuario as usuarioNombre FROM comentarios c " +
                         "JOIN usuarios u ON c.usuarioId = u.id WHERE c.usuarioId = ? ORDER BY c.fecha DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comentario c = new Comentario(
                    rs.getInt("id"),
                    rs.getInt("usuarioId"),
                    rs.getString("usuarioNombre"),
                    rs.getString("texto"),
                    rs.getInt("calificacion"),
                    rs.getTimestamp("fecha")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener comentarios del usuario: " + e.getMessage());
        }
        return lista;
    }

    // ===============================
    // Actualizar comentario
    // ===============================
    public static void actualizarComentario(Comentario c) {
        try (Connection con = conn.conectarBD()) {
            String sql = "UPDATE comentarios SET texto = ?, calificacion = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getTexto());
            ps.setInt(2, c.getCalificacion());
            ps.setInt(3, c.getId());
            ps.executeUpdate();
            System.out.println("Comentario actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar comentario: " + e.getMessage());
        }
    }

    // ===============================
    // Eliminar comentario por ID
    // ===============================
    public static void eliminarComentario(int id) {
        try (Connection con = conn.conectarBD()) {
            String sql = "DELETE FROM comentarios WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Comentario eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar comentario: " + e.getMessage());
        }
    }

    // ===============================
    // Verificar si un comentario pertenece a un usuario
    // ===============================
    public static boolean comentarioPerteneceAUsuario(int comentarioId, int usuarioId) {
        try (Connection con = conn.conectarBD()) {
            String sql = "SELECT COUNT(*) FROM comentarios WHERE id = ? AND usuarioId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, comentarioId);
            ps.setInt(2, usuarioId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar propiedad del comentario: " + e.getMessage());
        }
        return false;
    }
}