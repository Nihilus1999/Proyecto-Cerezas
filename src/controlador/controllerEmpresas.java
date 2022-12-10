/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.empresas.AsociacionRegional;
import vista.empresas.Clientes;
import vista.empresas.Productores;
import vista.empresas.Proveedores;
import vista.principal.Empresas;
import vista.principal.Menu_Inicio;

/**
 *
 * @author jose-
 */

public class controllerEmpresas implements ActionListener{
    Empresas em = new Empresas();
    AsociacionRegional an = new AsociacionRegional();
    Clientes ci = new Clientes();
    Proveedores pv = new Proveedores();
    Productores pd = new Productores();
    
    
    public controllerEmpresas(Empresas em){
        this.em=em;
        this.em.btnRegresar.addActionListener(this);
        this.em.btnAsociacion.addActionListener(this);
        this.em.btnCliente.addActionListener(this);
        this.em.btnProveedor.addActionListener(this);
        this.em.btnProductor.addActionListener(this);
        this.an.btnRegresar.addActionListener(this);
        this.an.btnInsertar.addActionListener(this);
        this.an.btnDelete.addActionListener(this);
        this.an.btnModificar.addActionListener(this);
        this.ci.btnRegresar.addActionListener(this);
        this.ci.btnInsertar.addActionListener(this);
        this.ci.btnDelete.addActionListener(this);
        this.ci.btnModificar.addActionListener(this);
        this.pd.btnRegresar.addActionListener(this);
        this.pd.btnInsertar.addActionListener(this);
        this.pd.btnDelete.addActionListener(this);
        this.pd.btnModificar.addActionListener(this);
        this.pv.btnRegresar.addActionListener(this);
        this.pv.btnInsertar.addActionListener(this);
        this.pv.btnDelete.addActionListener(this);
        this.pv.btnModificar.addActionListener(this);
    }
    
    public void iniciarEmpresas(){
         em.setTitle("Empresas"); //El titulo que tendra en la ventana
         em.setResizable(false);
         em.setLocationRelativeTo(null);
    } 

    @Override
    public void actionPerformed(ActionEvent ae) {
         if(ae.getSource()==em.btnRegresar){
            Menu_Inicio mm = new Menu_Inicio();
            controllerPrincipal ctrl = new controllerPrincipal(mm);
            ctrl.iniciarMenu();
            em.setVisible(false);
        }
         
        if(ae.getSource()==em.btnAsociacion){
            an.setTitle("Asociacion Nacional");
            an.setResizable(false);
            an.setLocationRelativeTo(null);
            an.setVisible(true);
            em.setVisible(false);
            an.llenarAsociacion();
            an.llenarPais();
            an.llenarRegion();
        }
        
         if(ae.getSource()==an.btnInsertar){
              an.inserts();
          }
          
          if(ae.getSource()==an.btnDelete){
              an.delete();
          }
          
          if(ae.getSource()==an.btnModificar){
              an.update();
          }
        
        if(ae.getSource()==an.btnRegresar){
            iniciarEmpresas();
            em.setVisible(true);
            an.setVisible(false);
        }
        
        if(ae.getSource()==em.btnCliente){
            ci.setTitle("Clientes");
            ci.setResizable(false);
            ci.setLocationRelativeTo(null);
            ci.setVisible(true);
            em.setVisible(false);
            ci.llenarCiudad();
            ci.llenarPais();
            ci.llenarClientes();
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
            iniciarEmpresas();
            em.setVisible(true);
            ci.setVisible(false);
        }
        
        if(ae.getSource()==em.btnProductor){
            pd.setTitle("Productores");
            pd.setResizable(false);
            pd.setLocationRelativeTo(null);
            pd.setVisible(true);
            em.setVisible(false);
            pd.llenarPais();
            pd.llenarProductor();
            pd.llenarCiudad();
            pd.llenarRegion();
        }
        
         if(ae.getSource()==pd.btnInsertar){
              pd.inserts();
          }
          
          if(ae.getSource()==pd.btnDelete){
              pd.delete();
          }
          
          if(ae.getSource()==pd.btnModificar){
              pd.update();
          }
        
        if(ae.getSource()==pd.btnRegresar){
            iniciarEmpresas();
            em.setVisible(true);
            pd.setVisible(false);
        }
        
        if(ae.getSource()==em.btnProveedor){
            pv.setTitle("Proveedores");
            pv.setResizable(false);
            pv.setLocationRelativeTo(null);
            pv.setVisible(true);
            em.setVisible(false);
            pv.llenarPais();
            pv.llenarProveedor();
        }
        
        if(ae.getSource()==pv.btnInsertar){
              pv.inserts();
          }
        
        if(ae.getSource()==pv.btnDelete){
              pv.delete();
          }
          
          if(ae.getSource()==pv.btnModificar){
              pv.update();
          }
        
        if(ae.getSource()==pv.btnRegresar){
            iniciarEmpresas();
            em.setVisible(true);
            pv.setVisible(false);
        }
        
    }
}
