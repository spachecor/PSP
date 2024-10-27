package com.spachecor.model.service;

import com.spachecor.model.entity.Empleado;
import com.spachecor.model.repository.EmpleadoDAO;

import java.util.List;

/**
 * Clase EmpleadoService que hereda de EmpleadoDAO y que se encarga de aplicar la sincronía a los métodos CRUD de la
 * clase EmpleadoDAO
 * @author Selene
 * @see EmpleadoDAO
 * @version 1.0
 */
public class EmpleadoService extends EmpleadoDAO {
    /**
     * Método que aplica la sincronía al agregar un nuevo empleado
     * @param empleado El empleado a agregar
     */
    public synchronized void agregarEmpleado(Empleado empleado) {
        super.agregarEmpleado(empleado);
    }

    /**
     * Método que aplica la sincronía al obtener una lista de empleados
     * @return Una lista con los empleados contenidos en la tabla empleado de la bbdd
     */
    public synchronized List<Empleado> obtenerEmpleados() {
        return super.obtenerEmpleados();
    }

    /**
     * Método que aplica la sincronía al actualizar un empleado
     * @param empleado El empleado a actualizar
     * @param nuevoNombre El nuevo nombre del empleado
     */
    public synchronized void actualizarEmpleado(Empleado empleado, String nuevoNombre) {
        super.actualizarEmpleado(empleado, nuevoNombre);
    }

    /**
     * Método que aplica la sincronía al eliminar un empleado
     * @param id El id del empleado a eliminar
     */
    public synchronized void eliminarEmpleado(Integer id) {
        super.eliminarEmpleado(id);
    }
}
