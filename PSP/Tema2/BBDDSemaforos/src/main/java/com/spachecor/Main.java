package com.spachecor;

import com.spachecor.model.entity.Empleado;
import com.spachecor.model.repository.EmpleadoDAO;
import com.spachecor.model.task.EmpleadoAction;
import com.spachecor.model.task.EmpleadoTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore bbdd = new Semaphore(3);
        List<Thread> threads = new ArrayList<>();
        EmpleadoTask insercionJuan = new EmpleadoTask(bbdd, new Empleado(1, "juan"), EmpleadoAction.INSERTAR);
        threads.add(new Thread(insercionJuan));
        EmpleadoTask insercionSelene = new EmpleadoTask(bbdd, new Empleado(2, "selene"), EmpleadoAction.INSERTAR);
        threads.add(new Thread(insercionSelene));
        EmpleadoTask insercionLuisa = new EmpleadoTask(bbdd, new Empleado(3, "luisa"), EmpleadoAction.INSERTAR);
        threads.add(new Thread(insercionLuisa));
        EmpleadoTask insercionJose = new EmpleadoTask(bbdd, new Empleado(4, "jose"), EmpleadoAction.INSERTAR);
        threads.add(new Thread(insercionJose));
        EmpleadoTask insercionAlejandro = new EmpleadoTask(bbdd, new Empleado(5, "alejandro"), EmpleadoAction.INSERTAR);
        threads.add(new Thread(insercionAlejandro));
        EmpleadoTask insercionRoberta = new EmpleadoTask(bbdd, new Empleado(6, "roberta"), EmpleadoAction.INSERTAR);
        threads.add(new Thread(insercionRoberta));
        EmpleadoTask insercionManolo = new EmpleadoTask(bbdd, new Empleado(7, "manolo"), EmpleadoAction.INSERTAR);
        threads.add(new Thread(insercionManolo));
        EmpleadoTask insercionNacho = new EmpleadoTask(bbdd, new Empleado(8, "nacho"), EmpleadoAction.INSERTAR);
        threads.add(new Thread(insercionNacho));
        EmpleadoTask listarEmpleados = new EmpleadoTask(bbdd);

        for(Thread thread : threads) {
            thread.start();
        }

        try{
            //esperamos 10 segundos para asegurarnos de que tó ha terminado
            Thread.sleep(10000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        Thread t1 = new Thread(listarEmpleados);
        t1.start();
        try{
            t1.join();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        //una vez terminamos, cerramos la conexión
        EmpleadoDAO.cerrarConexion();

    }
}