/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.cargamentos.EnviosReales;
import vista.cargamentos.VariedadExportada;
import vista.principal.Cargamentos;
import vista.principal.Menu_Inicio;

public class controllerCargamentos implements ActionListener{
    
    Cargamentos ca = new Cargamentos();
    EnviosReales er = new EnviosReales();
    VariedadExportada ve = new VariedadExportada();
    
    public controllerCargamentos(Cargamentos ca) {
        this.ca=ca;
        this.ca.btnRegresar.addActionListener(this);
        this.ca.btnEnvioReal.addActionListener(this);
        this.ca.btnVariedadExpor.addActionListener(this);
        this.er.btnRegresar.addActionListener(this);
        this.ve.btnRegresar.addActionListener(this);
        this.ve.btnInsert.addActionListener(this);
        this.ve.btnDelete.addActionListener(this);
        this.ve.btnModificar.addActionListener(this);
    }
    
    public void iniciarCargamentos(){
        ca.setTitle("Cargamentos y Envios"); //El titulo que tendra en la ventana
        ca.setResizable(false);
        ca.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==ca.btnRegresar){
            Menu_Inicio mm = new Menu_Inicio();
            controllerPrincipal ctrl = new controllerPrincipal(mm);
            ctrl.iniciarMenu();
            ca.setVisible(false);
        }
        
        if(ae.getSource()==ca.btnEnvioReal){
            er.setTitle("Envios Reales");
            er.setResizable(false);
            er.setLocationRelativeTo(null);
            er.setVisible(true);
            ca.setVisible(false);
        }
        
        if(ae.getSource()==er.btnRegresar){
            iniciarCargamentos();
            ca.setVisible(true);
            er.setVisible(false);
        }
        if(ae.getSource()==er.btnInsert){
            er.insert();
        }

        if(ae.getSource()==er.btnDelete){
            er.delete();
        }

        if(ae.getSource()==er.btnModificar){
            er.update();
        }
        
        if(ae.getSource()==er.btnRegresar){
            iniciarCargamentos();
            ca.setVisible(true);
            er.setVisible(false);
        }
        
        if(ae.getSource()==ca.btnVariedadExpor){
            ve.setTitle("Variedad Exportada");
            ve.setResizable(false);
            ve.setLocationRelativeTo(null);
            ve.setVisible(true);
            ca.setVisible(false);
            ve.llenarVarExp();
        }

        if(ae.getSource()==ve.btnInsert){
            ve.insert();
        }

        if(ae.getSource()==ve.btnDelete){
            ve.delete();
        }

        if(ae.getSource()==ve.btnModificar){
            ve.update();
        }
        
        if(ae.getSource()==ve.btnRegresar){
            iniciarCargamentos();
            ca.setVisible(true);
            ve.setVisible(false);
        }
    }
}
