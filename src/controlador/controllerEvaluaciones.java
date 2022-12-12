/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.evaluacion.Formula;
import vista.evaluacion.Pago;
import vista.evaluacion.Resultado;
import vista.evaluacion.Variable;
import vista.principal.Evaluaciones;
import vista.principal.Menu_Inicio;

/**
 *
 * @author jose-
 */

public class controllerEvaluaciones implements ActionListener{
    
    Evaluaciones ev = new Evaluaciones();
    Formula fo = new Formula();
    Pago p = new Pago();
    Resultado re = new Resultado();
    Variable var = new Variable();
    
    public controllerEvaluaciones(Evaluaciones ev){
        this.ev=ev;
        this.ev.btnRegresar.addActionListener(this);
        this.ev.btnFormula.addActionListener(this);
        this.ev.btnPago.addActionListener(this);
        this.ev.btnResultado.addActionListener(this);
        this.ev.btnVariable.addActionListener(this);
        this.fo.btnRegresar.addActionListener(this);
        this.fo.btnInsertar.addActionListener(this);
        this.fo.btnModificar.addActionListener(this);
        this.fo.btnDelete.addActionListener(this);
        this.p.btnRegresar.addActionListener(this);
        this.p.btnInsertar.addActionListener(this);
        this.p.btnModificar.addActionListener(this);
        this.p.btnDelete.addActionListener(this);
        this.re.btnRegresar.addActionListener(this);
        this.var.btnRegresar.addActionListener(this);
        this.var.btnInsertar.addActionListener(this);
        this.var.btnModificar.addActionListener(this);
        this.var.btnDelete.addActionListener(this);
    }
    
    public void iniciarEvaluaciones(){
        ev.setTitle("Evaluaciones y Resultados"); //El titulo que tendra en la ventana
        ev.setResizable(false);
        ev.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==ev.btnRegresar){
            Menu_Inicio mm = new Menu_Inicio();
            controllerPrincipal ctrl = new controllerPrincipal(mm);
            ctrl.iniciarMenu();
            ev.setVisible(false);
        }
        
        if(ae.getSource()==ev.btnFormula){
            fo.setTitle("Formula");
            fo.setResizable(false);
            fo.setLocationRelativeTo(null);
            fo.setVisible(true);
            ev.setVisible(false);
            fo.llenarFormula();
            fo.llenarCliente();
            fo.llenarVariable();
        }
        
           if(ae.getSource()==fo.btnInsertar){
              fo.inserts();
          }
          
          if(ae.getSource()==fo.btnDelete){
              fo.delete();
          }
          
          if(ae.getSource()==fo.btnModificar){
              fo.update();
          }
        
        if(ae.getSource()==fo.btnRegresar){
            iniciarEvaluaciones();
            ev.setVisible(true);
            fo.setVisible(false);
        }
        
         if(ae.getSource()==ev.btnPago){
            p.setTitle("Pago");
            p.setResizable(false);
            p.setLocationRelativeTo(null);
            p.setVisible(true);
            ev.setVisible(false);
            p.llenarPago();
          
            
        }
        
         if(ae.getSource()==p.btnRegresar){
            iniciarEvaluaciones();
            ev.setVisible(true);
            p.setVisible(false);
        }
         
           if(ae.getSource()==p.btnInsertar){
              p.inserts();
          }
          
          if(ae.getSource()==p.btnDelete){
              p.delete();
          }
          
          if(ae.getSource()==p.btnModificar){
              p.update();
          }
        
         if(ae.getSource()==ev.btnResultado){
            re.setTitle("Resultado");
            re.setResizable(false);
            re.setLocationRelativeTo(null);
            re.setVisible(true);
            ev.setVisible(false);
        }
         
          if(ae.getSource()==re.btnRegresar){
            iniciarEvaluaciones();
            ev.setVisible(true);
            re.setVisible(false);
        }
          
          if(ae.getSource()==ev.btnVariable){
            var.setTitle("Variable");
            var.setResizable(false);
            var.setLocationRelativeTo(null);
            var.setVisible(true);
            ev.setVisible(false);
            var.llenarVariable();
        }
          
            if(ae.getSource()==var.btnInsertar){
              var.inserts();
          }
          
          if(ae.getSource()==var.btnDelete){
              var.delete();
          }
          
          if(ae.getSource()==var.btnModificar){
              var.update();
          }
          
           if(ae.getSource()==var.btnRegresar){
            iniciarEvaluaciones();
            ev.setVisible(true);
            var.setVisible(false);
        }
          
       
    }
    
    
}
