/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author EdisonNaranjo
 */
public class Operario extends Empleado{
    private boolean manejaMaquinaPesada;

    public Operario(boolean manejaMaquinaPesada, float salario, String puestoTrabajo, int idPersona, String nombrePersona, String apellidos, String tipoDocumento, String documento, String correo) {
        super(salario, puestoTrabajo, idPersona, nombrePersona, apellidos, tipoDocumento, documento, correo);
        this.manejaMaquinaPesada = manejaMaquinaPesada;
    }

    public Operario() {
    }

    public boolean isManejaMaquinaPesada() {
        return manejaMaquinaPesada;
    }

    public void setManejaMaquinaPesada(boolean manejaMaquinaPesada) {
        this.manejaMaquinaPesada = manejaMaquinaPesada;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombrePersona=" + nombrePersona+ ", correo=" + correo  + "salario=" + salario + ", puestoTrabajo=" + puestoTrabajo + "manejaMaquinaPesada=" + manejaMaquinaPesada+ '}';
    }
}