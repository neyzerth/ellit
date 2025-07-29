package Entrenadores;
// ===============================
// Clase Entrenador.java
// ===============================
// Contiene los atributos, constructores, getters/setters y toString

public class Entrenador {
    // ===============================
    // Atributos
    // ===============================
    private int id;
    private String nombre;
    private String especialidad;
    private String correo;
    private String telefono;
    private boolean activo;

    // ===============================
    // Constructor vacío
    // ===============================
    public Entrenador() {}

    // ===============================
    // Constructor con parámetros
    // ===============================
    public Entrenador(int id, String nombre, String especialidad, 
                     String correo, String telefono, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.correo = correo;
        this.telefono = telefono;
        this.activo = activo;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // ===============================
    // Método toString()
    // ===============================
    @Override
    public String toString() {
        String estado = activo ? "Activo" : "Inactivo";
        return String.format(
            "\nID: %d | Nombre: %s\n" +
            "Especialidad: %s\n" +
            "Contacto: %s | Tel: %s\n" +
            "Estado: %s\n" +
            "----------------------------",
            id, nombre, especialidad, correo, telefono, estado
        );
    }
}