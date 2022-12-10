/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.principal.Menu_Inicio;
import vista.principal.Produccion;
import vista.produccion.Cultivo;
import vista.produccion.PreciosPais;
import vista.produccion.ProduccionAnual;
import vista.produccion.VariedadCerezas;

/**
 *
 * @author jose-
 */
public class controllerProduccion implements ActionListener{
    
    Produccion p = new Produccion();
    VariedadCerezas vc = new VariedadCerezas();
    Cultivo c = new Cultivo();
    ProduccionAnual pa = new ProduccionAnual();
    PreciosPais pp = new PreciosPais();
    
    public controllerProduccion(Produccion produccion){
        this.p = produccion;
        this.p.btnRegresar.addActionListener(this);
        this.p.btnVariedadCereza.addActionListener(this);
        this.p.btnCultivo.addActionListener(this);
        this.p.btnPrecios.addActionListener(this);
        this.p.btnProduccionAnual.addActionListener(this);
        this.vc.btnDelete.addActionListener(this);
        this.vc.btnInsertar.addActionListener(this);
        this.vc.btnRegresar.addActionListener(this);
        this.vc.btnModificar.addActionListener(this);
        this.pp.btnRegresar.addActionListener(this);
        this.c.btnInsertar.addActionListener(this);
        this.c.btnEliminar.addActionListener(this);
        this.c.btnModificar.addActionListener(this);
        this.c.btnRegresar.addActionListener(this);
        this.pa.btnRegresar.addActionListener(this);
    }
    
    public void iniciarProduccion(){
        p.setTitle("Produccion de Cerezas"); //El titulo que tendra en la ventana
        p.setResizable(false);
        p.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource()==p.btnRegresar){
            Menu_Inicio mm = new Menu_Inicio();
            controllerPrincipal ctrl = new controllerPrincipal(mm);
            ctrl.iniciarMenu();
            p.setVisible(false);
        }
        
        if(ae.getSource()==p.btnVariedadCereza){
            vc.setTitle("Variedad Cerezas");
            vc.setResizable(false);
            vc.setLocationRelativeTo(null);
            vc.setVisible(true);
            p.setVisible(false);
            vc.llenarPais();
            vc.llenarVariedad();
        }
          
          if(ae.getSource()==vc.btnInsertar){
              vc.insert();
          }
          
          if(ae.getSource()==vc.btnDelete){
              vc.delete();
          }
          
          if(ae.getSource()==vc.btnModificar){
              vc.update();
          }
          
          if(ae.getSource()==vc.btnRegresar){
              iniciarProduccion();
              vc.setVisible(false);
              p.setVisible(true);
          }
          
           if(ae.getSource()==p.btnCultivo){
            c.setTitle("Cultivo");
            c.setResizable(false);
            c.setLocationRelativeTo(null);
            c.setVisible(true);
            p.setVisible(false);
            c.llenarProductorVar();
            c.llenarCBs();
        }
           
          if(ae.getSource()==c.btnInsertar){
              c.insert();
          }
          
          if(ae.getSource()==c.btnEliminar){
              c.delete();
          }
          
          if(ae.getSource()==c.btnModificar){
              c.update();
          }
           
          if(ae.getSource()==c.btnRegresar){
              iniciarProduccion();
              p.setVisible(true);
              c.setVisible(false);
          }
          
           if(ae.getSource()==p.btnPrecios){
            pp.setTitle("Precios por Paises");
            pp.setResizable(false);
            pp.setLocationRelativeTo(null);
            pp.setVisible(true);
            p.setVisible(false);
        }
           
          if(ae.getSource()==pp.btnRegresar){
              iniciarProduccion();
              p.setVisible(true);
              pp.setVisible(false);
          }
          
          if(ae.getSource()==p.btnProduccionAnual){
            pa.setTitle("Produccion Anual");
            pa.setResizable(false);
            pa.setLocationRelativeTo(null);
            pa.setVisible(true);
            p.setVisible(false);
        }
          
          if(ae.getSource()==pa.btnRegresar){
              iniciarProduccion();
              p.setVisible(true);
              pa.setVisible(false);
          }
        
    }
    
}
