/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion.Conexion;
import vista.principal.Acuerdos;
import vista.principal.Adopcion;
import vista.principal.Alimentos;
import vista.principal.BeneficiosSalud;
import vista.principal.Cargamentos;
import vista.principal.Empresas;
import vista.principal.Evaluaciones;
import vista.principal.Login;
import vista.principal.Menu_Inicio;
import vista.principal.Produccion;
import vista.principal.Ubicaciones;

/**
 *
 * @author jose-
 */
public class controllerPrincipal implements ActionListener{
    private Login login;
    Menu_Inicio mp = new Menu_Inicio();
    
    
    public controllerPrincipal(Menu_Inicio mp){
        this.mp=mp;
        this.mp.btnCargamentos.addActionListener(this);
        this.mp.btnAcuerdos.addActionListener(this);
        this.mp.btnEmpresas.addActionListener(this);
        this.mp.btnProduccion.addActionListener(this);
        this.mp.btnAdopcion.addActionListener(this);
        this.mp.btnAlimentos.addActionListener(this);
        this.mp.btnEvaluacion.addActionListener(this);
        this.mp.btnBeneficios.addActionListener(this);
        this.mp.btnDesconectar.addActionListener(this);
        this.mp.btnUbicaciones.addActionListener(this);
    }
    
    public void iniciarMenu(){
         mp.setTitle("Menu Principal"); //El titulo que tendra en la ventana
         mp.setResizable(false);
         mp.setLocationRelativeTo(null);
         mp.setVisible(true);
    }                                                

    @Override
    public void actionPerformed(ActionEvent ae) {
        
         if(ae.getSource()==mp.btnDesconectar){
                controllerLogin.conexion = Conexion.deconectar();
                Login login = new Login();
                controllerLogin ctrl = new controllerLogin(login);
                ctrl.iniciaLogin();
                login.setVisible(true);
                mp.setVisible(false);
        }
         
         if(ae.getSource()==mp.btnAcuerdos){
             Acuerdos ac = new Acuerdos();
             controllerAcuerdos ctrl = new controllerAcuerdos(ac);
             ctrl.iniciarAcuerdos();
             ac.setVisible(true);
             mp.setVisible(false);
         }
         
         if(ae.getSource()==mp.btnAdopcion){
             Adopcion ap = new Adopcion();
             controllerAdopcion ctrl = new controllerAdopcion(ap);
             ctrl.iniciarAdopcion();
             ap.setVisible(true);
             mp.setVisible(false);
         }
         
         if(ae.getSource()==mp.btnAlimentos){
             Alimentos al = new Alimentos();
             controllerAlimentos ctrl = new controllerAlimentos(al);
             ctrl.iniciarAlimentos();
             al.setVisible(true);
             mp.setVisible(false);
         }
         
          if(ae.getSource()==mp.btnCargamentos){
             Cargamentos ca = new Cargamentos();
             controllerCargamentos ctrl = new controllerCargamentos(ca);
             ctrl.iniciarCargamentos();
             ca.setVisible(true);
             mp.setVisible(false);
         }
          
           if(ae.getSource()==mp.btnEmpresas){
             Empresas em = new Empresas();
             controllerEmpresas ctrl = new controllerEmpresas(em);
             ctrl.iniciarEmpresas();
             em.setVisible(true);
             mp.setVisible(false);
         }
           
          if(ae.getSource()==mp.btnEvaluacion){
             Evaluaciones ev = new Evaluaciones();
             controllerEvaluaciones ctrl = new controllerEvaluaciones(ev);
             ctrl.iniciarEvaluaciones();
             ev.setVisible(true);
             mp.setVisible(false);
         }
           
         if(ae.getSource()==mp.btnProduccion){
             Produccion p = new Produccion();
             controllerProduccion ctrl = new controllerProduccion(p);
             ctrl.iniciarProduccion();
             p.setVisible(true);
             mp.setVisible(false);
         }
           
           if(ae.getSource()==mp.btnUbicaciones){
             Ubicaciones ub = new Ubicaciones();
             controllerUbicaciones ctrl = new controllerUbicaciones(ub);
             ctrl.iniciarUbicaciones();
             ub.setVisible(true);
             mp.setVisible(false);
         }
           if(ae.getSource()==mp.btnBeneficios){
             BeneficiosSalud be = new BeneficiosSalud();
             controllerBeneficioSalud ctrl = new controllerBeneficioSalud(be);
             ctrl.iniciarBeneficios();
             be.setVisible(true);
             mp.setVisible(false);
           }
           
    }
    
}
