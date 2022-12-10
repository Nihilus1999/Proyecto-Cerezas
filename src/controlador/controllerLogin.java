/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.conexion.Conexion;
import vista.Otros.TextPrompt;
import vista.principal.Login;
import vista.principal.Menu_Inicio;

/**
 *
 * @author jose-
 */
public class controllerLogin implements ActionListener{
    private final Login login;
    public static Connection conexion;
    
    public controllerLogin(Login login){
        this.login = login;
        this.login.btnLogin.addActionListener(this);
        this.login.btnSalir.addActionListener(this);
    }
    
    public void iniciaLogin(){
        login.setTitle("Inicio de sesion"); //El titulo que tendra en la ventana
        login.setLocationRelativeTo(null); //Para que se inicie la ventana en el centro
        login.setResizable(false);
        TextPrompt user = new TextPrompt("Ingrese su Usuario",login.txtUser);
        TextPrompt password = new TextPrompt("********",login.txtPassword);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==login.btnLogin){
            String usuario = login.txtUser.getText();
            String password = String.valueOf(login.txtPassword.getPassword());
            
            if(usuario.equals("") || password.equals("")){
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos antes de hacer login");
            }else{
                if(( conexion = Conexion.conectar(login.txtUser.getText(),String.valueOf(login.txtPassword.getPassword()))) != null ){
                    try {
                        conexion.setAutoCommit(false);
                    } catch (SQLException ex) {
                        Logger.getLogger(controllerLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Menu_Inicio mp = new Menu_Inicio();
                   controllerPrincipal ctrl = new controllerPrincipal(mp);
                    ctrl.iniciarMenu();
                    mp.setVisible(true);
                    login.txtUser.setText("");
                    login.txtPassword.setText("");
                    login.setVisible(false);
               }
                
            }
                
        }
        
        if(ae.getSource()==login.btnSalir){
            System.exit(0);
        }
    }
}
