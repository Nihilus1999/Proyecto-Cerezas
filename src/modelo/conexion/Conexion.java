package modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    
    public static Connection comm = null;
    private static final String HOST = "localhost";
    private static final String PUERTO = "5432";
    private static final String DB = "proyecto-cerezas";
    
    
     public static Connection conectar(String textUser, String textPassword){
            try{
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://"+HOST+":"+PUERTO+"/"+DB;
                comm = DriverManager.getConnection(url, textUser, textPassword);
                JOptionPane.showMessageDialog(null, "Se ha conectado exitosamente");
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e,"Error", JOptionPane.ERROR_MESSAGE);
            }
            return comm;
        }
     
    public static Connection deconectar(){
        
        try{
            comm = null;
            JOptionPane.showMessageDialog(null, "Se ha desconectado existosamente");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al desconectar");
        }
        return comm;
    }
    
}
