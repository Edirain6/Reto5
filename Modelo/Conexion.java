
package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexion {
    //1. Crear instancia de la clase Connection
    Connection connection;
    //Atributos
    String driver               = "com.mysql.cj.jdbc.Driver";
    String cadenaConnexion      = "jdbc:mysql://localhost:3307/";
    String nombreBaseDatos      = "reto1_g56_db";
    String usuario              = "root";
    String contraseña           = "";
    
    
    //2. Añadir el constructor sin args de la clase
    public Conexion() {
     
        //3. Itentar conectar con la base de datos
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(cadenaConnexion+nombreBaseDatos, usuario, contraseña);
            
            if(connection != null){
                System.out.println("Conexión exitosa con la base de datos");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No se pudo establecer conexión con la base de datos " + e);
        }
    }
    
    //5. Crear una función que retorna la clase connection
    public Connection getConnection() {
        return connection;
    }

    
}