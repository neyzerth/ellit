
package Entrenadores;
//hecho por leslie Plata
// Encargada de las operaciones con la base de datos

import Conexion.conn;
import java.sql.*;
import java.util.ArrayList;
public class CrudEntrenador {

    // ===============================
    // Insertar nuevo entrenador
    // ===============================
    public static boolean insertarEntrenador(Entrenador e) {
        try (Connection con = conn.conectarBD()) {
            String sql = "INSERT INTO entrenadores (nombre, especialidad, correo, telefono, activo) VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getEspecialidad());
            ps.setString(3, e.getCorreo());
            ps.setString(4, e.getTelefono());
            ps.setBoolean(5, e.isActivo());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error al insertar entrenador: " + ex.getMessage());
            return false;
        }
    }

    // ===============================
    // Obtener todos los entrenadores
    // ===============================
    public static ArrayList<Entrenador> obtenerEntrenadores() {
        ArrayList<Entrenador> lista = new ArrayList<>();
        try (Connection con = conn.conectarBD()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM entrenadores");
            while (rs.next()) {
                Entrenador e = new Entrenador(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("especialidad"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getBoolean("activo")
                );
                lista.add(e);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener entrenadores: " + e.getMessage());
        }
        return lista;
    }

    // ===============================
    // Buscar entrenador por nombre
    // ===============================
    public static ArrayList<Entrenador> buscarPorNombre(String nombre) {
        ArrayList<Entrenador> resultados = new ArrayList<>();
        try (Connection con = conn.conectarBD()) {
            String sql = "SELECT * FROM entrenadores WHERE nombre LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Entrenador e = new Entrenador(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("especialidad"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getBoolean("activo")
                );
                resultados.add(e);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar entrenador: " + e.getMessage());
        }
        return resultados;
    }

    // ===============================
    // Buscar por especialidad
    // ===============================
    public static ArrayList<Entrenador> buscarPorEspecialidad(String especialidad) {
        ArrayList<Entrenador> resultados = new ArrayList<>();
        try (Connection con = conn.conectarBD()) {
            String sql = "SELECT * FROM entrenadores WHERE especialidad = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, especialidad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Entrenador e = new Entrenador(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("especialidad"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getBoolean("activo")
                );
                resultados.add(e);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por especialidad: " + e.getMessage());
        }
        return resultados;
    }

    // ===============================
    // Actualizar entrenador
    // ===============================
    public static boolean actualizarEntrenador(Entrenador e) {
        try (Connection con = conn.conectarBD()) {
            String sql = "UPDATE entrenadores SET nombre=?, especialidad=?, correo=?, telefono=?, activo=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getEspecialidad());
            ps.setString(3, e.getCorreo());
            ps.setString(4, e.getTelefono());
            ps.setBoolean(5, e.isActivo());
            ps.setInt(6, e.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error al actualizar entrenador: " + ex.getMessage());
            return false;
        }
    }

    // ===============================
    // Eliminar entrenador (cambiar estado a inactivo)
    // ===============================
    public static boolean desactivarEntrenador(int id) {
        try (Connection con = conn.conectarBD()) {
            String sql = "UPDATE entrenadores SET activo=false WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al desactivar entrenador: " + e.getMessage());
            return false;
        }
    }
}