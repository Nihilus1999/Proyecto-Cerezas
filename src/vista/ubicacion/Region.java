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

public class Region extends javax.swing.JFrame {
    
    int id;
    String part1;
    String part2;
    ResultSet rs;
    
    public Region() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }
    
    public static boolean validarNombre(String nombre){ //Metodo que valida el nombre
        return nombre.matches("^[a-zA-Z_]+( [a-zA-Z_]+)*$");
    }

    public void llenarPais(){
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select nombre from aja_pais" );
            while ( rs.next() ) {
            String nombre = rs.getString("nombre");
            cbPais.addItem(nombre);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void cortarCB( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split("/");
        part1 = parts[0]; 
        part2 = parts[1];
    }
    
    public void llenarRegion(){
        cbRegion.removeAllItems();
        cbRegionEli.removeAllItems();
        cbRegion.addItem(" ");
        cbRegionEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select re.nombre || ' / ' || pais.nombre as texto from aja_region re, aja_pais pais where re.id_pais = pais.id" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbRegion.addItem(registro);
            cbRegionEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void limpiar(){
        txtNom.setText(null);
        cbPais.setSelectedIndex(0);
        cbRegion.setSelectedIndex(0);
        cbRegionEli.setSelectedIndex(0);
    }
    
    public void inserts(){
        if(txtNom.getText().equals("") || txtDescripcion.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
        } else if ( validarNombre(txtNom.getText()) ==false){
            JOptionPane.showMessageDialog(null, "El campo nombre no puede tener numeros");
        }else{
            try {
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_region (id_pais,nombre, descripcion) values ((select id from aja_pais where nombre='"
                   + cbPais.getSelectedItem().toString() + "'),'"
                   + txtNom.getText() +"' ,'"
                  +  txtDescripcion.getText() +"')";

                   ResultSet rs = stmt.executeQuery( "select re.nombre, pa.nombre from aja_region re, aja_pais pa where re.id_pais = pa.id" );

                   int flag=0;

                   while ( rs.next() ) {
                       if( rs.getString(1).equals(txtNom.getText()) && rs.getString(2).equals(cbPais.getSelectedItem().toString())){
                           JOptionPane.showMessageDialog(null, "La region que intento registrar ya existe");
                           flag=1;
                           break;
                       }
                    }

                   if(flag==0){
                           stmt.executeUpdate(sql);
                           controllerLogin.conexion.commit();
                           JOptionPane.showMessageDialog(null,"Se he registrado exitosamente");
                           llenarRegion();
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
                String sql = "DELETE FROM aja_region where  id = " + id;

                ResultSet rs = stmt.executeQuery( "select id from aja_region" );

              int flag=0;

              while ( rs.next() ) {
                   if(rs.getInt("id") == id){
                       stmt.executeUpdate(sql);
                       controllerLogin.conexion.commit();
                       JOptionPane.showMessageDialog(null,"Se ha borrado la region exitosamente");
                       llenarRegion();
                       limpiar();
                       flag = 1;
                       break;
                   }
                }

              if(flag == 0){
                  JOptionPane.showMessageDialog(null,"No se encontro la region");
              }

           }catch (SQLException e) {
               e.printStackTrace();

           }     
    }
    
   
    
    public void update(){
         if(txtNom.getText().equals("")|| txtDescripcion.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
        } else if ( validarNombre(txtNom.getText()) ==false){
            JOptionPane.showMessageDialog(null, "El campo nombre no puede tener numeros");
        }else{
            try{
                Statement stmt = controllerLogin.conexion.createStatement();
                String sql = "update aja_region re " 
                        + "set (nombre, descripcion, id_pais)= " 
                        + "('"+ txtNom.getText() +"',"
                        + " '"+ txtDescripcion.getText() +"',"
                        + "(select pais.id from aja_pais pais where pais.nombre='"+ cbPais.getSelectedItem().toString() +"') ) "
                        + "where re.id = " + id;
                
                
                               stmt.executeUpdate(sql);
                               controllerLogin.conexion.commit();
                              JOptionPane.showMessageDialog(null,"Se modifico la region con exito");
                               llenarRegion();
                               limpiar();

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
        cbPais = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jVariedadPais1 = new javax.swing.JLabel();
        cbRegion = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        cbRegionEli = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jNombreVar = new javax.swing.JLabel();
        jPais = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jNombreVar1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMINISTRACION DE VARIEDADES");

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 470, 120, 30));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 120, 30));
        jPanel1.add(txtNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 255, 25));

        jPanel1.add(cbPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 255, 25));

        jPanel3.setBackground(new java.awt.Color(255, 153, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jVariedadPais1.setBackground(new java.awt.Color(0, 0, 0));
        jVariedadPais1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVariedadPais1.setForeground(new java.awt.Color(255, 255, 255));
        jVariedadPais1.setText("Region y Pais");
        jPanel3.add(jVariedadPais1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbRegion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbRegion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRegionActionPerformed(evt);
            }
        });
        jPanel3.add(cbRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        jPanel3.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 410, 90));

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Eliminar");
        btnDelete.setEnabled(false);
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 120, 30));

        cbRegionEli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbRegionEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRegionEliActionPerformed(evt);
            }
        });
        jPanel2.add(cbRegionEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Region y Pais");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 410, 90));

        jNombreVar.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar.setText("Descripcion:");
        jPanel1.add(jNombreVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, -1, 24));

        jPais.setBackground(new java.awt.Color(0, 0, 0));
        jPais.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPais.setForeground(new java.awt.Color(255, 255, 255));
        jPais.setText("Pais:");
        jPanel1.add(jPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, 24));

        txtDescripcion.setColumns(20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, -1, 170));

        jNombreVar1.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar1.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar1.setText("Nombre de la Region:");
        jPanel1.add(jNombreVar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 24));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbRegionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRegionActionPerformed
        if ( cbRegion.getItemCount() > 1 ){
            cbRegionEli.setEnabled(false);
            if (!cbRegion.getSelectedItem().toString().equals(" ")){
                cortarCB(cbRegion );
                btnModificar.setEnabled(true);
                btnInsertar.setEnabled(false);
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery( "select  re.id, re.nombre, re.descripcion, pais.nombre "
                            + "from aja_region re, aja_pais pais "
                            + "where re.nombre = '"+ part1.trim() +"' "
                            + "and re.id_pais = (select pa.id from aja_pais pa where pa.nombre = '"+ part2.trim() +"') and pais.id=re.id_pais" );
                        while( rs.next() ){
                            id = rs.getInt(1);
                            String nombreCi = rs.getString(2);
                            String descripcion = rs.getString(3);
                            String paisNom = rs.getString(4);
                            txtNom.setText(nombreCi);
                            cbPais.setSelectedItem(paisNom);
                            txtDescripcion.setText(descripcion);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(true);
                cbRegionEli.setEnabled(true);
                limpiar();
            }
        }    
    }//GEN-LAST:event_cbRegionActionPerformed

    private void cbRegionEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRegionEliActionPerformed
        if ( cbRegionEli.getItemCount() > 1 ){
                cbRegion.setEnabled(false);
                if (!cbRegionEli.getSelectedItem().toString().equals(" ")){
                cortarCB(cbRegionEli );
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                btnDelete.setEnabled(true);
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                   ResultSet rs = stmt.executeQuery( "select  re.id, re.nombre, pais.nombre "
                            + "from aja_region re, aja_pais pais "
                            + "where re.nombre = '"+ part1.trim() +"' "
                            + "and re.id_pais = (select pa.id from aja_pais pa where pa.nombre = '"+ part2.trim() +"') and pais.id=re.id_pais" );
                        while( rs.next() ){
                            id = rs.getInt(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnInsertar.setEnabled(true);
                cbRegion.setSelectedIndex(0);
                btnDelete.setEnabled(false);
                cbRegion.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_cbRegionEliActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JComboBox<String> cbPais;
    private javax.swing.JComboBox<String> cbRegion;
    private javax.swing.JComboBox<String> cbRegionEli;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jNombreVar;
    private javax.swing.JLabel jNombreVar1;
    private javax.swing.JLabel jPais;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jVariedadPais1;
    private javax.swing.JTextArea txtDescripcion;
    public javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
