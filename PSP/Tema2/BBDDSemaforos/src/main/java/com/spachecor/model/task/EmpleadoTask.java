package com.spachecor.model.task;

import com.spachecor.model.entity.Empleado;
import com.spachecor.model.service.EmpleadoService;

import java.util.concurrent.Semaphore;

/**
 * Clase EmpleadoTask que implementa la interfaz Runnable. Es ejecutable por un hilo y está construida de tal forma que
 * pueda respetar un semáforo a la hora de acceder a la bbdd
 * @author Selene
 * @see Semaphore
 * @see Runnable
 * @version 1.0
 */
public class EmpleadoTask implements Runnable{
    private Semaphore bbdd;
    private Empleado empleado;
    private EmpleadoAction empleadoAction;
    private String nuevoNombre;
    private EmpleadoService empleadoService;

    /**
     * Constructor para la inserción y eliminación
     * @param bbdd el semáforo que indica cuantos accesos simultáneos pueden entrar a la bbdd
     * @param empleado el empleado a insertar o eliminar
     * @param empleadoAction la accion a realizar, únicamente insertar o eliminar
     */
    public EmpleadoTask(Semaphore bbdd, Empleado empleado, EmpleadoAction empleadoAction) {
        this.bbdd = bbdd;
        this.empleado = empleado;
        if(empleadoAction==EmpleadoAction.INSERTAR||empleadoAction==EmpleadoAction.ELIMINAR){
            this.empleadoAction = empleadoAction;
        }else throw new IllegalArgumentException("Accion no valido");
        this.empleadoService = new EmpleadoService();
    }

    /**
     * Constructor único para la modificación
     * @param bbdd el semáforo que indica cuantos accesos simultáneos pueden entrar a la bbdd
     * @param empleado el empleado a actualizar
     * @param nuevoNombre el nuevo nombre del empleado
     */
    public EmpleadoTask(Semaphore bbdd, Empleado empleado, String nuevoNombre) {
        this.bbdd = bbdd;
        this.empleado = empleado;
        this.empleadoAction = EmpleadoAction.ACTUALIZAR;
        this.nuevoNombre = nuevoNombre;
        this.empleadoService = new EmpleadoService();
    }

    /**
     * Constructor único para realizar el listado de empleados
     * @param bbdd el semáforo que indica cuantos accesos simultáneos pueden entrar a la bbdd
     */
    public EmpleadoTask(Semaphore bbdd) {
        this.bbdd = bbdd;
        this.empleadoAction = EmpleadoAction.LISTAR;
        this.empleadoService = new EmpleadoService();
    }

    @Override
    public void run() {
        switch(empleadoAction){
            case INSERTAR -> {
                try{
                    bbdd.acquire();
                    empleadoService.agregarEmpleado(empleado);
                    Thread.sleep(2000);
                    bbdd.release();
                }catch(InterruptedException e){
                    System.out.println("Imposible realizar la inserción");
                    e.printStackTrace();
                }
            }
            case LISTAR -> {
                try{
                    bbdd.acquire();
                    System.out.println(empleadoService.obtenerEmpleados());
                    Thread.sleep(2000);
                    bbdd.release();
                }catch (InterruptedException e){
                    System.out.println("Imposible listar los empleados");
                    e.printStackTrace();
                }
            }
            case ACTUALIZAR -> {
                try{
                    bbdd.acquire();
                    empleadoService.actualizarEmpleado(empleado, nuevoNombre);
                    Thread.sleep(2000);
                    bbdd.release();
                }catch (InterruptedException e){
                    System.out.println("Imposible realizar la actualización");
                    e.printStackTrace();
                }
            }
            case ELIMINAR -> {
                try{
                    bbdd.acquire();
                    empleadoService.eliminarEmpleado(empleado.getId());
                    Thread.sleep(2000);
                    bbdd.release();
                }catch (InterruptedException e){
                    System.out.println("Imposible realizar la eliminación");
                    e.printStackTrace();
                }
            }
            default -> throw new IllegalArgumentException("Accion no valido");
        }
    }
}
