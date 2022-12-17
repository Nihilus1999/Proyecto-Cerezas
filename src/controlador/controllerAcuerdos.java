/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.acuerdos.Contrato;
import vista.acuerdos.Convenio;
import vista.acuerdos.ConvenioTrabajo;
import vista.acuerdos.MetodoPago;
import vista.acuerdos.Renovacion;
import vista.principal.Acuerdos;
import vista.principal.Menu_Inicio;

/**
 *
 * @author jose-
 */
public class controllerAcuerdos implements ActionListener {
    
    Acuerdos ac = new Acuerdos();
    Contrato ca = new Contrato();
    Convenio co = new Convenio();
    ConvenioTrabajo ct = new ConvenioTrabajo();
    MetodoPago mp = new MetodoPago();
    Renovacion re = new Renovacion();
    
    public controllerAcuerdos(Acuerdos ac){
        this.ac=ac;
        this.ac.btnRegresar.addActionListener(this);
        this.ac.btnContratos.addActionListener(this);
        this.ac.btnConvenio.addActionListener(this);
        this.ac.btnConvenioT.addActionListener(this);
        this.ac.btnMetodosPago.addActionListener(this);
        this.ac.btnRenovacion.addActionListener(this);
        this.ca.btnRegresar.addActionListener(this);
        this.ca.btnInsert.addActionListener(this);
        this.ca.btnDelete.addActionListener(this);
        this.ca.btnModificar.addActionListener(this);
        this.co.btnRegresar.addActionListener(this);
        this.co.btnInsertar.addActionListener(this);
        this.co.btnModificar.addActionListener(this);
        this.co.btnDelete.addActionListener(this);
        this.ct.btnRegresar.addActionListener(this);
        this.ct.btnInsertar.addActionListener(this);
        this.ct.btnModificar.addActionListener(this);
        this.ct.btnDelete.addActionListener(this);
        this.mp.btnEliminar.addActionListener(this);
        this.mp.btnInsertar.addActionListener(this);
        this.mp.btnModificar.addActionListener(this);
        this.mp.btnRegresar.addActionListener(this);
        this.re.btnRegresar.addActionListener(this);
        this.re.btnInsertar.addActionListener(this);
        this.re.btnDelete.addActionListener(this);
        this.re.btnModificar.addActionListener(this);
    }
    
    public void iniciarAcuerdos(){
        ac.setTitle("Acuerdos y Pagos"); //El titulo que tendra en la ventana
        ac.setResizable(false);
        ac.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource()==ac.btnRegresar){
            Menu_Inicio mm = new Menu_Inicio();
            controllerPrincipal ctrl = new controllerPrincipal(mm);
            ctrl.iniciarMenu();
            ac.setVisible(false);
        }
        
        if(ae.getSource()==ac.btnContratos){
            ca.setTitle("Contratos");
            ca.setResizable(false);
            ca.setLocationRelativeTo(null);
            ca.setVisible(true);
            ac.setVisible(false);
            ca.llenarCliente();
            ca.llenarProd();
            ca.llenarT();
            ca.llenarContrato();
        }

          if(ae.getSource()==ca.btnInsert){
              ca.insert();
          }
          
          if(ae.getSource()==ca.btnDelete){
              ca.delete();
          }
          
          if(ae.getSource()==ca.btnModificar){
              ca.update();
          }
          
          if(ae.getSource()==ca.btnRegresar){
              iniciarAcuerdos();
              ca.setVisible(false);
              ac.setVisible(true);
          }
        
        if(ae.getSource()==ca.btnRegresar){
            iniciarAcuerdos();
            ac.setVisible(true);
            ca.setVisible(false);
        }
        
        if(ae.getSource()==ac.btnConvenio){
            co.setTitle("Convenios");
            co.setResizable(false);
            co.setLocationRelativeTo(null);
            co.setVisible(true);
            ac.setVisible(false);
            co.llenarConvenio();
        }
        
         if(ae.getSource()==co.btnInsertar){
              co.inserts();
          }
          
          if(ae.getSource()==co.btnDelete){
              co.delete();
          }
          
          if(ae.getSource()==co.btnModificar){
              co.update();
          }
        
        if(ae.getSource()==co.btnRegresar){
             iniciarAcuerdos();
            ac.setVisible(true);
            co.setVisible(false);
        }
        
        if(ae.getSource()==ac.btnConvenioT){
            ct.setTitle("Convenios de Trabajo");
            ct.setResizable(false);
            ct.setLocationRelativeTo(null);
            ct.setVisible(true);
            ac.setVisible(false);
            ct.llenarConvenioT();
        }
        
         if(ae.getSource()==ct.btnInsertar){
              ct.inserts();
          }
          
          if(ae.getSource()==ct.btnDelete){
              ct.delete();
          }
          
          if(ae.getSource()==ct.btnModificar){
              ct.update();
          }
        
        if(ae.getSource()==ct.btnRegresar){
             iniciarAcuerdos();
            ac.setVisible(true);
            ct.setVisible(false);
        }
        
        if(ae.getSource()==ac.btnMetodosPago){
            mp.setTitle("Metodos de Pago");
            mp.setResizable(false);
            mp.setLocationRelativeTo(null);
            mp.setVisible(true);
            ac.setVisible(false);
            mp.llenarPro();
        }
        
        if(ae.getSource()==mp.btnEliminar){
            mp.delete();
        }
        
        if(ae.getSource()==mp.btnInsertar){
            mp.insert();
        }
        
        if(ae.getSource()==mp.btnModificar){
            mp.update();
        }
        
        if(ae.getSource()==mp.btnRegresar){
             iniciarAcuerdos();
            ac.setVisible(true);
            mp.setVisible(false);
        }
        
        if(ae.getSource()==ac.btnRenovacion){
            re.setTitle("Renovaciones");
            re.setResizable(false);
            re.setLocationRelativeTo(null);
            re.setVisible(true);
            ac.setVisible(false);
            re.llenarRenovacion();
        }
        
         if(ae.getSource()==re.btnInsertar){
              re.inserts();
          }
          
          if(ae.getSource()==re.btnDelete){
              re.delete();
          }
          
          if(ae.getSource()==re.btnModificar){
              re.update();
          }
        
        if(ae.getSource()==re.btnRegresar){
            iniciarAcuerdos();
            ac.setVisible(true);
            re.setVisible(false);
        }
        
    }
}
