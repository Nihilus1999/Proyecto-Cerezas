/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.adopcion_cerezas;


import controlador.controllerLogin;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Padrino extends javax.swing.JFrame {
    
    int id;
    String part1;
    String part2;
    ResultSet rs;
    
    public Padrino() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }
    
    public static boolean validarNombre(String nombre){ //Metodo que valida el nombre
        return nombre.matches("^[a-zA-Z_]+( [a-zA-Z_]+)*$");
    }
    
    public static boolean validarNumero(String nombre){ //Metodo que valida el nombre
        return nombre.matches("[0-9]*");
    }

    public void llenarPadrino(){
        cbPadrino.removeAllItems();
        cbPadrinoEli.removeAllItems();
        cbPadrino.addItem(" ");
        cbPadrinoEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select primer_nombre || ' / ' || primer_apellido from aja_padrino" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbPadrino.addItem(registro);
            cbPadrinoEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void limpiar(){
        txtDocumento.setText(null);
        txtNombre.setText(null);
        txtNombre2.setText(null);
        txtApellido.setText(null);
        txtApellido2.setText(null);
        cbPadrino.setSelectedIndex(0);
        cbPadrinoEli.setSelectedIndex(0);
    }
    
    public void cortarCB( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split("/");
        part1 = parts[0]; 
        part2 = parts[1];
    }
    
    
    public void inserts(){
        if(txtDocumento.getText().equals("") || txtNombre.getText().equals("") || txtApellido.getText().equals("") || txtApellido2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Los campos documento, primer nombre y apellidos no puede estar vacio");
        } else if ( validarNombre(txtNombre.getText()) ==false ||  validarNombre(txtApellido2.getText())==false ||  validarNombre(txtApellido.getText())==false){
            JOptionPane.showMessageDialog(null, "Los campos de nombre y apellido no puede tener numeros");
        }else if(validarNumero(txtDocumento.getText())==false){
            JOptionPane.showMessageDialog(null, "El campo documento solo pueden tener numero");
        }else{
            try {
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_padrino (documento_identidad ,primer_nombre, primer_apellido, segundo_apellido, segundo_nombre) values ('"
                    + txtDocumento.getText() +"', '"
                    + txtNombre.getText() +"', '"
                    + txtApellido.getText() +"', '"
                    + txtApellido2.getText() +"', '"
                    + txtNombre2.getText()+"');" ;

                   ResultSet rs = stmt.executeQuery( "select documento_identidad from aja_padrino");

                   int flag=0;

                   while ( rs.next() ) {
                       if( rs.getString(1).equals(txtDocumento.getText())){
                           JOptionPane.showMessageDialog(null, "El padrino que intento registrar ya existe");
                           flag=1;
                           break;
                       }
                    }

                   if(flag==0){
                           stmt.executeUpdate(sql);
                           controllerLogin.conexion.commit();
                           JOptionPane.showMessageDialog(null,"Se he registrado exitosamente");
                           llenarPadrino();
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
                String sql = "DELETE FROM aja_padrino where  id = " + id;

                ResultSet rs = stmt.executeQuery( "select id from aja_padrino" );

              int flag=0;

              while ( rs.next() ) {
                   if(rs.getInt("id") == id){
                       stmt.executeUpdate(sql);
                       controllerLogin.conexion.commit();
                       JOptionPane.showMessageDialog(null,"Se ha borrado el padrino exitosamente");
                       llenarPadrino();
                       limpiar();
                       flag = 1;
                       break;
                   }
                }

              if(flag == 0){
                  JOptionPane.showMessageDialog(null,"No se encontro el padrino");
              }

           }catch (SQLException e) {
               e.printStackTrace();

           }     
    }
    
   
    
    public void update(){
        if(txtDocumento.getText().equals("") || txtNombre.getText().equals("") || txtApellido.getText().equals("") || txtApellido2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Los campos documento, primer nombre y apellidos no puede estar vacio");
        } else if ( validarNombre(txtNombre.getText()) ==false ||  validarNombre(txtApellido2.getText())==false ||  validarNombre(txtApellido.getText())==false){
            JOptionPane.showMessageDialog(null, "Los campos de nombre y apellido no puede tener numeros");
        }else if(validarNumero(txtDocumento.getText())==false){
            JOptionPane.showMessageDialog(null, "El campo documento solo pueden tener numero");
        }else{
            try{
                Statement stmt = controllerLogin.conexion.createStatement();
                
                /*String sql = "update aja_pais pais"
                        + " set (nombre, continente) ="
                        + "( '" + txtNombre.getText() + "')"
                        + " where id = " +  id;*/
                
                String sql = "update aja_padrino set (documento_identidad ,primer_nombre, primer_apellido, segundo_apellido, segundo_nombre) = ('"
                    + txtDocumento.getText() +"', '"
                    + txtNombre.getText() +"', '"
                    + txtApellido.getText() +"', '"
                    + txtApellido2.getText() +"', '"
                    + txtNombre2.getText()+"')"
                    + " where id= "+ id;

                ResultSet rs = stmt.executeQuery( "select nombre from aja_padrino" );

                       int flag=0;

                       while ( rs.next() ) {
                           if( rs.getString(1).equals(txtNombre.getText())){
                               JOptionPane.showMessageDialog(null, "El padrino que intento registrar ya existe");
                               flag=1;
                               break;
                           }
                        }

                       if(flag==0){
                               stmt.executeUpdate(sql);
                               controllerLogin.conexion.commit();
                              JOptionPane.showMessageDialog(null,"El padrino se registro con exito");
                               llenarPadrino();
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
        txtNombre = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jVariedadPais1 = new javax.swing.JLabel();
        cbPadrino = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        cbPadrinoEli = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jNombreVar = new javax.swing.JLabel();
        jPrecocidad = new javax.swing.JLabel();
        txtNombre2 = new javax.swing.JTextField();
        jNombreVar1 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jPrecocidad1 = new javax.swing.JLabel();
        txtApellido2 = new javax.swing.JTextField();
        txtDocumento = new javax.swing.JTextField();
        jNombreVar2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMINISTRACION DE VARIEDADES");

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 480, 120, 30));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 120, 30));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 255, 25));

        jPanel3.setBackground(new java.awt.Color(255, 51, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jVariedadPais1.setBackground(new java.awt.Color(0, 0, 0));
        jVariedadPais1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVariedadPais1.setForeground(new java.awt.Color(255, 255, 255));
        jVariedadPais1.setText("Padrino");
        jPanel3.add(jVariedadPais1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbPadrino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cbPadrino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPadrinoActionPerformed(evt);
            }
        });
        jPanel3.add(cbPadrino, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        jPanel3.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 410, 90));

        jPanel2.setBackground(new java.awt.Color(255, 51, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Eliminar");
        btnDelete.setEnabled(false);
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 120, 30));

        cbPadrinoEli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbPadrinoEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPadrinoEliActionPerformed(evt);
            }
        });
        jPanel2.add(cbPadrinoEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Padrino");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 410, 90));

        jNombreVar.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar.setText("Primer apellido:");
        jPanel1.add(jNombreVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, 24));

        jPrecocidad.setBackground(new java.awt.Color(0, 0, 0));
        jPrecocidad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPrecocidad.setForeground(new java.awt.Color(255, 255, 255));
        jPrecocidad.setText("Segundo apellido:");
        jPanel1.add(jPrecocidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, 24));
        jPanel1.add(txtNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 255, 25));

        jNombreVar1.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar1.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar1.setText("Documento de Identidad:");
        jPanel1.add(jNombreVar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 24));
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 255, 25));

        jPrecocidad1.setBackground(new java.awt.Color(0, 0, 0));
        jPrecocidad1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPrecocidad1.setForeground(new java.awt.Color(255, 255, 255));
        jPrecocidad1.setText("Segundo nombre:");
        jPanel1.add(jPrecocidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, 24));
        jPanel1.add(txtApellido2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, 260, 25));
        jPanel1.add(txtDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 255, 25));

        jNombreVar2.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar2.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar2.setText("Primer nombre:");
        jPanel1.add(jNombreVar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, 24));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbPadrinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPadrinoActionPerformed
        if ( cbPadrino.getItemCount() > 1 ){
            cbPadrinoEli.setEnabled(false);
            
            if (!cbPadrino.getSelectedItem().toString().equals(" ")){
                btnModificar.setEnabled(true);
                btnInsertar.setEnabled(false);
                cortarCB(cbPadrino );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    
                     ResultSet rs = stmt.executeQuery(" select id, documento_identidad, primer_nombre, primer_apellido,"
                            + " segundo_apellido, segundo_nombre "
                            + "from aja_padrino "
                            + "where primer_nombre = '"+ part1.trim() +"' "
                            + " and primer_apellido =  '"+   part2.trim() +"' ");
                            
                     
                        while( rs.next() ){
                            id = rs.getInt(1);
                            String documento = rs.getString(2);
                            String nombre1= rs.getString(3);
                            String apellido1 = rs.getString(4);
                            String apellido2 = rs.getString(5);
                            String nombre2 = rs.getString(6);
                            txtDocumento.setText(documento);
                            txtNombre.setText(nombre1);
                            txtApellido.setText(apellido1);
                            txtApellido2.setText(apellido2);
                            txtNombre2.setText(nombre2);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(true);
                cbPadrinoEli.setEnabled(true);
                limpiar();
            }
        }    
    }//GEN-LAST:event_cbPadrinoActionPerformed

    private void cbPadrinoEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPadrinoEliActionPerformed
        if ( cbPadrinoEli.getItemCount() > 1 ){
                
                if (!cbPadrinoEli.getSelectedItem().toString().equals(" ")){
                cbPadrino.setEnabled(false);
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                btnDelete.setEnabled(true);
                cortarCB(cbPadrinoEli );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    
                    ResultSet rs = stmt.executeQuery(" select id, documento_identidad, primer_nombre, primer_apellido,"
                            + " segundo_apellido, segundo_nombre "
                            + "from aja_padrino "
                            + "where primer_nombre = '"+ part1.trim() +"' "
                            + " and primer_apellido =  '"+   part2.trim() +"' ");
                    
                        while( rs.next() ){
                            id = rs.getInt(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnInsertar.setEnabled(true);
                cbPadrino.setSelectedIndex(0);
                btnDelete.setEnabled(false);
                cbPadrino.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_cbPadrinoEliActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbPadrino;
    private javax.swing.JComboBox<String> cbPadrinoEli;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jNombreVar;
    private javax.swing.JLabel jNombreVar1;
    private javax.swing.JLabel jNombreVar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jPrecocidad;
    private javax.swing.JLabel jPrecocidad1;
    private javax.swing.JLabel jVariedadPais1;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtApellido2;
    public javax.swing.JTextField txtDocumento;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtNombre2;
    // End of variables declaration//GEN-END:variables
}
