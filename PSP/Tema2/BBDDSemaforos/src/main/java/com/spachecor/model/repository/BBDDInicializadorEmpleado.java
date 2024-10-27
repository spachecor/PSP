package com.spachecor.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Clase BBDDInicializadorEmpleado que se encarga de crear la base de datos empleados y la tabla empleado
 * @author Selene
 * @version 1.0
 */
public class BBDDInicializadorEmpleado {
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private String crearBBDD;
    private String usarBBDD;
    private String crearTabla;
    static{
        URL = "jdbc:mysql://localhost:3306/";
        USER = "root";
        PASSWORD = "";
    }
    {
        this.crearBBDD = "create database if not exists empleados";
        this.usarBBDD = "use empleados;";
        this.crearTabla = "create table if not exists empleado(" +
                "id int," +
                "nombre varchar(250)," +
                "constraint empleado_id_pk primary key(id)" +
                ");";
    }

    protected BBDDInicializadorEmpleado() {
        crearBBDDTablas();
    }

    /**
     * Método qye se encarga de crear la base de datos y la tabla
     */
    private void crearBBDDTablas(){
        try(
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ){
            //creamos la bbdd, la usamos, y creamos la tabla
            statement.executeUpdate(crearBBDD);
            statement.executeUpdate(usarBBDD);
            statement.executeUpdate(crearTabla);
            System.out.println("Proceso de creacion de bbdd completado");
        }catch (Exception e){
            System.out.println("Imposible crear la bbdd");
            e.printStackTrace();
        }
    }
}
