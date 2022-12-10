/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.ubicacion;

import vista.produccion.*;
import controlador.controllerLogin;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pais extends javax.swing.JFrame {
    
    int id;
    String part1;
    String part2;
    ResultSet rs;
    
    public Pais() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }
    
    public static boolean validarNombre(String nombre){ //Metodo que valida el nombre
        return nombre.matches("^[a-zA-Z_]+( [a-zA-Z_]+)*$");
    }

    public void llenarPais(){
        cbPais.removeAllItems();
        cbPaisEli.removeAllItems();
        cbPais.addItem(" ");
        cbPaisEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select nombre from aja_pais" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbPais.addItem(registro);
            cbPaisEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void limpiar(){
        txtNom.setText(null);
        cbContinente.setSelectedIndex(0);
        cbPais.setSelectedIndex(0);
        cbPaisEli.setSelectedIndex(0);
    }
    
    public void inserts(){
        if(txtNom.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
        } else if ( validarNombre(txtNom.getText()) ==false){
            JOptionPane.showMessageDialog(null, "El campo nombre no puede tener numeros");
        }else{
            try {
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_pais (nombre ,continente) values ('"
                   + txtNom.getText() +"', '"
                   + cbContinente.getSelectedItem().toString()+"');" ;

                   ResultSet rs = stmt.executeQuery( "select nombre from aja_pais");

                   int flag=0;

                   while ( rs.next() ) {
                       if( rs.getString(1).equals(txtNom.getText())){
                           JOptionPane.showMessageDialog(null, "El pais que intento registrar ya existe");
                           flag=1;
                           break;
                       }
                    }

                   if(flag==0){
                           stmt.executeUpdate(sql);
                           controllerLogin.conexion.commit();
                           JOptionPane.showMessageDialog(null,"Se he registrado exitosamente");
                           llenarPais();
                           limpiar();
                       }

                     } catch (SQLException e) {
                           e.printStackTrace();
                   }   
        }

    }

    public void delete(){
        try{
                Statement stmt = controllerLogin.conexion.createStatement();
                String sql = "DELETE FROM aja_pais where  id = " + id;

                ResultSet rs = stmt.executeQuery( "select id from aja_pais" );

              int flag=0;

              while ( rs.next() ) {
                   if(rs.getInt("id") == id){
                       stmt.executeUpdate(sql);
                       controllerLogin.conexion.commit();
                       JOptionPane.showMessageDialog(null,"Se ha borrado el pais exitosamente");
                       llenarPais();
                       limpiar();
                       flag = 1;
                       break;
                   }
                }

              if(flag == 0){
                  JOptionPane.showMessageDialog(null,"No se encontro el pais");
              }

           }catch (SQLException e) {
               e.printStackTrace();

           }     
    }
    
   
    
    public void update(){
         if(txtNom.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
        } else if ( validarNombre(txtNom.getText()) ==false){
            JOptionPane.showMessageDialog(null, "El campo nombre no puede tener numeros");
        }else{
            try{
                Statement stmt = controllerLogin.conexion.createStatement();
                /*String sql = "update aja_variedad_de_cerezas vdc " 
                        + "set (nombre,especie,precocidad,descripcion,id_pais)= " 
                        + "('"+ txtNom.getText() +"','"+ cbContinente.getSelectedItem().toString() +"','" 
                        + "(select pais.id from aja_pais pais where pais.nombre='"+"') ) "
                        + "where vdc.id = " + id;*/
                
                String sql = "update aja_pais pais"
                        + " set (nombre, continente) ="
                        + "( '" + txtNom.getText() + "', '" + cbContinente.getSelectedItem().toString()+"')"
                        + " where id = " +  id;

                ResultSet rs = stmt.executeQuery( "select nombre from aja_pais" );

                       int flag=0;

                       while ( rs.next() ) {
                           if( rs.getString(1).equals(txtNom.getText())){
                               JOptionPane.showMessageDialog(null, "La variedad que intento registrar ya existe");
                               flag=1;
                               break;
                           }
                        }

                       if(flag==0){
                               stmt.executeUpdate(sql);
                               controllerLogin.conexion.commit();
                              JOptionPane.showMessageDialog(null,"Se modifico el registro con exito");
                               llenarPais();
                               limpiar();
                           }

            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
            
       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        btnInsertar = new javax.swing.JButton();
        txtNom = new javax.swing.JTextField();
        cbContinente = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jVariedadPais1 = new javax.swing.JLabel();
        cbPais = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        cbPaisEli = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jNombreVar = new javax.swing.JLabel();
        jPrecocidad = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMINISTRACION DE VARIEDADES");

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 430, 120, 30));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 120, 30));
        jPanel1.add(txtNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 255, 25));

        cbContinente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "America", "Europa", "Asia", "Africa", "Oceania" }));
        jPanel1.add(cbContinente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 255, 25));

        jPanel3.setBackground(new java.awt.Color(255, 153, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jVariedadPais1.setBackground(new java.awt.Color(0, 0, 0));
        jVariedadPais1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVariedadPais1.setForeground(new java.awt.Color(255, 255, 255));
        jVariedadPais1.setText("Paises");
        jPanel3.add(jVariedadPais1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbPais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cbPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPaisActionPerformed(evt);
            }
        });
        jPanel3.add(cbPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        jPanel3.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 410, 90));

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Eliminar");
        btnDelete.setEnabled(false);
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 120, 30));

        cbPaisEli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbPaisEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPaisEliActionPerformed(evt);
            }
        });
        jPanel2.add(cbPaisEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Paises");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 410, 90));

        jNombreVar.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar.setText("Nombre del Pais");
        jPanel1.add(jNombreVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, 24));

        jPrecocidad.setBackground(new java.awt.Color(0, 0, 0));
        jPrecocidad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPrecocidad.setForeground(new java.awt.Color(255, 255, 255));
        jPrecocidad.setText("Continente");
        jPanel1.add(jPrecocidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, 24));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPaisActionPerformed
        if ( cbPais.getItemCount() > 1 ){
            cbPaisEli.setEnabled(false);
            if (!cbPais.getSelectedItem().toString().equals(" ")){
                btnModificar.setEnabled(true);
                btnInsertar.setEnabled(false);
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    
                    
                    ResultSet rs = stmt.executeQuery(" SELECT id, nombre, continente" +
"                   FROM aja_pais where nombre = '"+ cbPais.getSelectedItem().toString() + "' ");
                        while( rs.next() ){
                            id = rs.getInt(1);
                            String nombrePais = rs.getString(2);
                            String continente= rs.getString(3);
                            txtNom.setText(nombrePais);
                            cbContinente.setSelectedItem(continente);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(true);
                cbPaisEli.setEnabled(true);
                limpiar();
            }
        }    
    }//GEN-LAST:event_cbPaisActionPerformed

    private void cbPaisEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPaisEliActionPerformed
        if ( cbPaisEli.getItemCount() > 1 ){
                cbPais.setEnabled(false);
                if (!cbPaisEli.getSelectedItem().toString().equals(" ")){
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                btnDelete.setEnabled(true);
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(" SELECT id" +
"                   FROM aja_pais where nombre = '"+ cbPaisEli.getSelectedItem().toString()+ "' ");
                        while( rs.next() ){
                            id = rs.getInt(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnInsertar.setEnabled(true);
                cbPais.setSelectedIndex(0);
                btnDelete.setEnabled(false);
                cbPais.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_cbPaisEliActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JComboBox<String> cbContinente;
    private javax.swing.JComboBox<String> cbPais;
    private javax.swing.JComboBox<String> cbPaisEli;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jNombreVar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jPrecocidad;
    private javax.swing.JLabel jVariedadPais1;
    public javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
