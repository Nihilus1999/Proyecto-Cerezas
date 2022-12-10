/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.principal.Menu_Inicio;
import vista.principal.Ubicaciones;
import vista.ubicacion.Ciudad;
import vista.ubicacion.Pais;
import vista.ubicacion.Region;

/**
 *
 * @author jose-
 */
public class controllerUbicaciones implements ActionListener{

    Ubicaciones ub = new Ubicaciones();
    Ciudad ci = new Ciudad();
    Pais pa = new Pais();
    Region re = new Region();
    
    
    public controllerUbicaciones(Ubicaciones ub) {
        this.ub=ub;
        this.ub.btnRegresar.addActionListener(this);
        this.ub.btnCiudad.addActionListener(this);
        this.ub.btnPais.addActionListener(this);
        this.ub.btnRegion.addActionListener(this);
        this.ci.btnRegresar.addActionListener(this);
        this.ci.btnInsertar.addActionListener(this);
        this.ci.btnDelete.addActionListener(this);
        this.ci.btnModificar.addActionListener(this);
        this.pa.btnRegresar.addActionListener(this);
        this.pa.btnInsertar.addActionListener(this);
        this.pa.btnDelete.addActionListener(this);
        this.pa.btnModificar.addActionListener(this);
        this.re.btnRegresar.addActionListener(this);
    }

    void iniciarUbicaciones() {
        ub.setTitle("Ubicaciones y Regiones"); //El titulo que tendra en la ventana
        ub.setResizable(false);
        ub.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
         if(ae.getSource()==ub.btnRegresar){
            Menu_Inicio mm = new Menu_Inicio();
            controllerPrincipal ctrl = new controllerPrincipal(mm);
            ctrl.iniciarMenu();
            ub.setVisible(false);
        }
         
         if(ae.getSource()==ub.btnCiudad){
             ci.setTitle("Ciudades");
             ci.setResizable(false);
             ci.setLocationRelativeTo(null);
             ci.setVisible(true);
             ub.setVisible(false);
             ci.llenarCiudad();
             ci.llenarPais();
         }
         
          if(ae.getSource()==ci.btnInsertar){
              ci.inserts();
          }
          
          if(ae.getSource()==ci.btnDelete){
              ci.delete();
          }
          
          if(ae.getSource()==ci.btnModificar){
              ci.update();
          }
         
         if(ae.getSource()==ci.btnRegresar){
             iniciarUbicaciones();
             ub.setVisible(true);
             ci.setVisible(false);
         }
         
         if(ae.getSource()==ub.btnPais){
             pa.setTitle("Paises");
             pa.setResizable(false);
             pa.setLocationRelativeTo(null);
             pa.setVisible(true);
             ub.setVisible(false);
             pa.llenarPais();
         }
         
         if(ae.getSource()==pa.btnInsertar){
              pa.inserts();
          }
          
          if(ae.getSource()==pa.btnDelete){
              pa.delete();
          }
          
          if(ae.getSource()==pa.btnModificar){
              pa.update();
          }
         
         if(ae.getSource()==pa.btnRegresar){
             iniciarUbicaciones();
             ub.setVisible(true);
             pa.setVisible(false);
         }
         
         if(ae.getSource()==ub.btnRegion){
             re.setTitle("Regiones");
             re.setResizable(false);
             re.setLocationRelativeTo(null);
             re.setVisible(true);
             ub.setVisible(false);
         }
         
         if(ae.getSource()==re.btnRegresar){
             iniciarUbicaciones();
             ub.setVisible(true);
             re.setVisible(false);
         }
    }
    
}
