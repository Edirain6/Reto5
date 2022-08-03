
package Modelo;

public class Sucursal {
    
    private int idSucursal;
    private String nombreSucursal;
    
    public Sucursal() {
        idSucursal = 0;
        nombreSucursal = "";        
    }

    public Sucursal(int idSucursal, String nombreSucursal) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdSucursal() {
        return idSucursal;
    }
    
    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    
    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }
    
    
    @Override
    public String toString() {
        return getNombreSucursal(); 
    }
    
    
    
    
}
