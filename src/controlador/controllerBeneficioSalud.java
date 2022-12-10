/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import vista.principal.BeneficiosSalud;
import vista.principal.Menu_Inicio;
import java.awt.event.ActionListener;

/**
 *
 * @author jose-
 */
public class controllerBeneficioSalud implements ActionListener{
           
    BeneficiosSalud be = new BeneficiosSalud();

    public controllerBeneficioSalud (BeneficiosSalud be) {
        this.be=be;
        this.be.btnRegresar.addActionListener(this);
    }
    
    public void iniciarBeneficios(){
        be.setTitle("Cargamentos y Envios"); //El titulo que tendra en la ventana
        be.setResizable(false);
        be.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==be.btnRegresar){
            Menu_Inicio mm = new Menu_Inicio();
            controllerPrincipal ctrl = new controllerPrincipal(mm);
            ctrl.iniciarMenu();
            be.setVisible(false);
        }
    }
}
