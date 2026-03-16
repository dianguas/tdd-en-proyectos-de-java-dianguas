package com.tt1.test;

/**
 * Capa de acceso a datos del sistema de tareas.
 * Actúa como intermediario entre el servicio de negocio y la base de datos,
 * delegando las operaciones CRUD en un {@link DBStub}.
 */
public class Repositorio {

    private DBStub bd;

    /**
     * Crea un repositorio usando una instancia de base de datos proporcionada.
     *
     * @param bd Instancia de {@link DBStub} a utilizar como base de datos.
     */
    public Repositorio(DBStub bd) {
        this.bd = bd;
    }

    /**
     * Crea un repositorio con una instancia de base de datos por defecto.
     */
    public Repositorio() {
        this.bd = new DBStub();
    }

    /**
     * Busca una tarea por su nombre y devuelve el nombre si existe.
     *
     * @param nombre Nombre de la tarea a buscar.
     * @return El nombre de la tarea si existe, null si no se encuentra.
     */
    public String buscarServicio(String nombre) {
        ToDo t = bd.READ(nombre);
        if (t != null) {
            return t.getNombre();
        }
        return null;
    }

    /**
     * Marca una tarea como completada y la actualiza en la base de datos.
     *
     * @param servicio Tarea a marcar como completada.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean completadoServicio(ToDo servicio) {
        servicio.setCompletado(true);
        return bd.UPDATE(servicio);
    }

    /**
     * Añade una nueva tarea a la base de datos.
     *
     * @param servicio Tarea a añadir.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean agnadirServicio(ToDo servicio) {
        return bd.CREATE(servicio);
    }

    /**
     * Registra una dirección de correo electrónico en el sistema.
     *
     * @param correo Dirección de correo electrónico a registrar.
     * @return true si se añadió correctamente, false en caso contrario.
     */
    public boolean agnadirCorreo(String correo) {
        return bd.agnadirCorreo(correo);
    }

    /**
     * Devuelve todas las tareas almacenadas en la base de datos.
     *
     * @return Lista de todas las tareas {@link ToDo}.
     */
    public java.util.List<ToDo> getTareas() {
        return bd.getServicios();
    }

    /**
     * Devuelve todos los correos electrónicos registrados.
     *
     * @return Colección de direcciones de correo electrónico.
     */
    public java.util.Collection<String> getCorreos() {
        return bd.getCorreos();
    }
}