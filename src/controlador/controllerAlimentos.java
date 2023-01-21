/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.alimento.Elaboracion;
import vista.alimento.Ingredientes;
import vista.alimento.Receta;
import vista.principal.Alimentos;
import vista.principal.Menu_Inicio;


public class controllerAlimentos implements ActionListener {
    Alimentos al = new Alimentos();
    Elaboracion el = new Elaboracion();
    Ingredientes in = new Ingredientes();
    Receta re = new Receta();
    
    
    public controllerAlimentos(Alimentos al){
        this.al=al;
        this.al.btnRegresar.addActionListener(this);
        this.al.btnElaboracion.addActionListener(this);
        this.al.btnIngrediente.addActionListener(this);
        this.al.btnReceta.addActionListener(this);
        this.el.btnInsertar.addActionListener(this);
        this.el.btnRegresar.addActionListener(this);
        this.in.btnRegresar.addActionListener(this);
        this.in.btnInsertar.addActionListener(this);
        this.in.btnModificar.addActionListener(this);
        this.in.btnDelete.addActionListener(this);
        this.re.btnEliminar.addActionListener(this);
        this.re.btnInsertar.addActionListener(this);
        this.re.btnRegresar.addActionListener(this);
    }
    
    public void iniciarAlimentos(){
        al.setTitle("Alimentos de Cerezas"); //El titulo que tendra en la ventana
        al.setResizable(false);
        al.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource()==al.btnRegresar){
            Menu_Inicio mm = new Menu_Inicio();
            controllerPrincipal ctrl = new controllerPrincipal(mm);
            ctrl.iniciarMenu();
            al.setVisible(false);
        }
        
        if(ae.getSource()==al.btnElaboracion){
            el.setTitle("Elaboracion");
            el.setResizable(false);
            el.setLocationRelativeTo(null);
            el.setVisible(true);
            al.setVisible(false);
            el.llenarReceta();
        }
        
        if(ae.getSource()==el.btnInsertar){
            el.insert();
        }
        
        if(ae.getSource()==el.btnRegresar){
            iniciarAlimentos();
            al.setVisible(true);
            el.setVisible(false);
        }
        
        if(ae.getSource()==al.btnIngrediente){
            in.setTitle("Ingredientes");
            in.setResizable(false);
            in.setLocationRelativeTo(null);
            in.setVisible(true);
            al.setVisible(false);
            in.llenarIngrediente();
        }
        
         if(ae.getSource()==in.btnInsertar){
              in.inserts();
          }
          
          if(ae.getSource()==in.btnDelete){
              in.delete();
          }
          
          if(ae.getSource()==in.btnModificar){
              in.update();
          }
        
        if(ae.getSource()==in.btnRegresar){
            iniciarAlimentos();
            al.setVisible(true);
            in.setVisible(false);
        }
        
        if(ae.getSource()==al.btnReceta){
            re.setTitle("Recetas");
            re.setResizable(false);
            re.setLocationRelativeTo(null);
            re.setVisible(true);
            al.setVisible(false);
            re.llenarCBs();
            re.llenarCbRaceta();
        }
        
        if(ae.getSource()==re.btnEliminar){
           re.delete();
        }
        
        if(ae.getSource()==re.btnInsertar){
           re.insert();
        }
        
        if(ae.getSource()==re.btnRegresar){
            iniciarAlimentos();
            al.setVisible(true);
            re.setVisible(false);
        }
        
        
        
        
    }
}
