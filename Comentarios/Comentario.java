package Comentarios;
// ===============================
// Clase Comentario.java
// ===============================
// Contiene los atributos de los comentarios, constructores, getters/setters y toString

import java.util.Date;

public class Comentario {
    // ===============================
    // Atributos
    // ===============================
    private int id;
    private int usuarioId;
    private String usuarioNombre; // No se almacena en BD, se obtiene con JOIN
    private String texto;
    private int calificacion;
    private Date fecha;

    // ===============================
    // Constructor vacío
    // ===============================
    public Comentario() {}

    // ===============================
    // Constructor con parámetros (para crear nuevos comentarios)
    // ===============================
    public Comentario(int usuarioId, String texto, int calificacion) {
        this.usuarioId = usuarioId;
        this.texto = texto;
        this.calificacion = calificacion;
        this.fecha = new Date(); // Fecha actual automáticamente
    }

    // ===============================
    // Constructor completo (para obtener de BD)
    // ===============================
    public Comentario(int id, int usuarioId, String usuarioNombre, String texto, int calificacion, Date fecha) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.texto = texto;
        this.calificacion = calificacion;
        this.fecha = fecha;
    }

    // ===============================
    // Getters y Setters
    // ===============================
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // ===============================
    // Método toString()
    // ===============================
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(usuarioNombre).append(" (").append(fecha).append("):\n");
        sb.append("Calificación: ").append(calificacion).append("/5\n");
        sb.append("Comentario:\n").append(texto).append("\n");
        return sb.toString();
    }

    // Método para mostrar estrellas de calificación
    public String getEstrellas() {
    StringBuilder estrellas = new StringBuilder();
    for (int i = 0; i < calificacion; i++) {
        estrellas.append("*");
    }
    for (int i = 0; i < 5 - calificacion; i++) {
        estrellas.append(" ");
    }
    return estrellas.toString();
}
}