package com.spacrod.herencias;

public class Empleado extends Persona {
    private String cargo;

    public Empleado(String nombre, Integer edad, String cargo) {
        super(nombre, edad);
        this.cargo = cargo;
    }

    @Override
    public void run() {
        super.ticarEntrada();
        System.out.println(super.getNombre()+" está trabajando...");
        try {
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(super.getNombre()+" ha terminado su tarea.");
        super.ticarSalida();
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
