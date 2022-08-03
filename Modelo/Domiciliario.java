/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author EdisonNaranjo
 */
public class Domiciliario extends  Empleado{
    private String tipoTransporte;

    public Domiciliario(String tipoTransporte, float salario, String puestoTrabajo, int idPersona, String nombrePersona, String apellidos, String tipoDocumento, String documento, String correo) {
        super(salario, puestoTrabajo, idPersona, nombrePersona, apellidos, tipoDocumento, documento, correo);
        this.tipoTransporte = tipoTransporte;
    }

    public Domiciliario() {
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombrePersona=" + nombrePersona +", correo=" + correo  + "salario=" + salario + ", puestoTrabajo=" + puestoTrabajo + "tipoTransporte=" + tipoTransporte + '}'; 
    }
}