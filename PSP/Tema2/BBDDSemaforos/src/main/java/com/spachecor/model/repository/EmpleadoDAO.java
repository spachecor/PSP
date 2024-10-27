package com.spachecor.model.repository;

import com.spachecor.model.entity.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase EmpleadoDAO que es el Data Object Model de la entidad Empleado. Contiene el CRUD del empleado en la bbdd
 * @author Selene
 * @see Empleado
 * @version 1.0
 */
public class EmpleadoDAO {
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static BBDDInicializadorEmpleado bbddInicializadorEmpleado;
    private static Connection connection;
    private PreparedStatement preparedStatement;
    static{
        URL = "jdbc:mysql://localhost:3306/empleados";
        USER = "root";
        PASSWORD = "";
        bbddInicializadorEmpleado = new BBDDInicializadorEmpleado();
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Se ha creado la conexion");
        }catch(Exception e){
            System.out.println("Imposible crear la conexión");
            e.printStackTrace();
        }
    }
    protected EmpleadoDAO() {}

    /**
     * Método que agrega un nuevo empleado a la bbdd
     * @param empleado El empleado a agregar
     */
    protected void agregarEmpleado(Empleado empleado){
        String sql = "insert into empleado values(?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, empleado.getId());
            preparedStatement.setString(2, empleado.getNombre());
            preparedStatement.executeUpdate();
            System.out.println("Empleado ingresado correctamente");
        }catch(Exception e){
            System.out.println("Imposible ingresar el nuevo empleado");
            e.printStackTrace();
        }
    }

    /**
     * Método que obtiene una lista de empleados de la tabla empleado de la bbdd
     * @return Una lista con los empleados contenidos en la tabla empleado de la bbdd
     */
    protected List<Empleado> obtenerEmpleados(){
        List<Empleado> empleados = new ArrayList<>();
        String sql = "select * from empleado";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("id"));
                empleado.setNombre(resultSet.getString("nombre"));
                empleados.add(empleado);
            }
            System.out.println("Empleados obtenidos correctamente");
        }catch (Exception e) {
            System.out.println("Imposible obtener los empleados");
            e.printStackTrace();
        }
        return empleados;
    }

    /**
     * Método que actualiza un empleado de la bbdd
     * @param empleado El empleado a actualizar
     * @param nuevoNombre El nuevo nombre del empleado
     */
    protected void actualizarEmpleado(Empleado empleado, String nuevoNombre){
        String sql = "update empleado set nombre = ? where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, nuevoNombre);
            preparedStatement.setInt(2, empleado.getId());
            preparedStatement.executeUpdate();
            System.out.println("Empleado actualizado correctamente");
        }catch (Exception e){
            System.out.println("Imposible actualizar el empleado");
            e.printStackTrace();
        }
    }

    /**
     * Método que elimina un empleado de la bbdd
     * @param id El id del empleado a eliminar
     */
    protected void eliminarEmpleado(Integer id){
        String sql = "delete from empleado where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Empleado eliminado correctamente");
        }catch (Exception e){
            System.out.println("Imposible eliminar el empleado");
            e.printStackTrace();
        }
    }

    /**
     * Método que cierra la conexión con la bbdd
     */
    public static void cerrarConexion(){
        try{
            connection.close();
            System.out.println("Conexion cerrada");
        }catch(Exception e){
            System.out.println("Imposible cerrar la conexión");
        }
    }
}
