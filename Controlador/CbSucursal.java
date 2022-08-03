
package Controlador;



import Modelo.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;


public class CbSucursal {
    
    Connection connection;
    Conexion conexion = new Conexion();
    Statement st;
    ResultSet rs;
    
    public CbSucursal(){        
    }   
            
    public ArrayList getListaSucursales() {
        System.out.println("Funciòn getListaSucursales");
        ArrayList mListaSucursales = new ArrayList();
        Sucursal sucursal = null;
        try{
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = (ResultSet)st.executeQuery("SELECT idSucursal, nombreSucursal FROM `sucursal`;");
            while(rs.next()){
                sucursal = new Sucursal();
                sucursal.setIdSucursal(rs.getInt("idSucursal"));
                sucursal.setNombreSucursal(rs.getString("nombreSucursal"));
                mListaSucursales.add(sucursal);
            }            
        }catch(SQLException e){
            System.out.println(e);
        }
        return mListaSucursales;
    }        

    public Object getSelectedItem() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getSelected() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}          
            
