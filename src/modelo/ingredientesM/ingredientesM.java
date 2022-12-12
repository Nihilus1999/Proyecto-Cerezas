/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.ingredientesM;

/**
 *
 * @author aaron
 */
public class ingredientesM {
    public int id_ingrediente;
    public String nombre_ingrediente;
    public String medida;
    public float cantidad;
    
    public ingredientesM(int id_ingrediente, String nombre_ingrediente, String medida, float cantidad){
        this.id_ingrediente = id_ingrediente;
        this.nombre_ingrediente = nombre_ingrediente;
        this.medida = medida;
        this.cantidad = cantidad;
    }
}
