/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.adopcion_cerezas.Padrino;
import vista.adopcion_cerezas.ProgramaApadrinamiento;
import vista.principal.Adopcion;
import vista.principal.Menu_Inicio;

/**
 *
 * @author jose-
 */
public class controllerAdopcion implements ActionListener {
    
    Adopcion ap =new Adopcion();
    Padrino pa = new Padrino();
    ProgramaApadrinamiento pap = new ProgramaApadrinamiento();
    
    public controllerAdopcion(Adopcion ap){
        this.ap = ap;
        this.ap.btnRegresar.addActionListener(this);
        this.ap.btnPadrino.addActionListener(this);
        this.ap.btnPrograma.addActionListener(this);
        this.pa.btnRegresar.addActionListener(this);
        this.pap.btnRegresar.addActionListener(this);
    }
    
    public void iniciarAdopcion(){
        ap.setTitle("Adopcion de Cerezas"); //El titulo que tendra en la ventana
        ap.setResizable(false);
        ap.setLocationRelativeTo(null);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()== ap.btnRegresar){
            Menu_Inicio mm = new Menu_Inicio();
            controllerPrincipal ctrl = new controllerPrincipal(mm);
            ctrl.iniciarMenu();
            ap.setVisible(false);
        }
        
        if(ae.getSource()==ap.btnPadrino){
            pa.setTitle("Padrino");
            pa.setResizable(false);
            pa.setLocationRelativeTo(null);
            pa.setVisible(true);
            ap.setVisible(false);
        }
        
        if(ae.getSource()==pa.btnRegresar){
            iniciarAdopcion();
            ap.setVisible(true);
            pa.setVisible(false);
        }
        
        if(ae.getSource()==ap.btnPrograma){
            pap.setTitle("Programa de Apadrinamiento");
            pap.setResizable(false);
            pap.setLocationRelativeTo(null);
            pap.setVisible(true);
            ap.setVisible(false);
        }
        
        if(ae.getSource()==pap.btnRegresar){
            iniciarAdopcion();
            ap.setVisible(true);
            pap.setVisible(false);
        }
        
        
    }
    
    
    
    
    
    
}
