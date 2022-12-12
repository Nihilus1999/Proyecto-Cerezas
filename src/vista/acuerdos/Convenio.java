/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.acuerdos;

import vista.evaluacion.*;
import controlador.controllerLogin;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convenio extends javax.swing.JFrame {
    
    Date id;
    String part1;
    String part2;
    String part3;
    String part4;
    String part5;
    String part6;
    
    Date fecha;
    
    ResultSet rs;
    
    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
    
    public Convenio() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }
    
    public static boolean validarNombre(String nombre){ //Metodo que valida el nombre
        return nombre.matches("^[a-zA-Z_]+( [a-zA-Z_]+)*$");
    }
    
      public void cortarCBP( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split("/");
        part1 = parts[0].replaceAll("Año: ", " ");//Nombre del cliente
        part2 = parts[1].replaceAll("Proveedor: ", " ");
        part3 = parts[2].replaceAll("Asociacion: ", " ");
}
    
    public void llenarConvenio(){
        cbConvenio.removeAllItems();
        cbConvenioEli.removeAllItems();
        cbConvenio.addItem(" ");
        cbConvenioEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select 'Año: ' || co.fecha_firma || ' / ' || 'Proveedor: ' || po.nombre || ' / ' || 'Asociacion: ' || ar.nombre as texto " +
            "from aja_convenio co, aja_asociacion_regional ar, aja_proveedor po " +
            "where co.id_asociacion = ar.id and co.id_proveedor = po.id"
                    + " order by  co.fecha_firma asc" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbConvenio.addItem(registro);
            cbConvenioEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void limpiar(){
        txtBeneficios.setText(null);
        dcFecha.setDate(null);
        
        cbConvenio.setSelectedIndex(0);
        cbConvenioEli.setSelectedIndex(0);
    }
    
    public void inserts(){
        
       if(dcFecha.getDate()==null){
            JOptionPane.showMessageDialog(null, "No hay ninguna fecha registrada");
        } else{
            try {
                
             
            
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_convenio (id_asociacion, id_proveedor, fecha_firma, beneficios, vigencia) values ("
                  + "(select id from aja_proveedor where nombre='"+ part2.trim() + "'),"
                  + "(select id from aja_asociacion_regional where nombre='"+ part3.trim() + "'),'"
                  + format.format(dcFecha.getDate()) +"' ,'"
                  +  txtBeneficios.getText() +"', '"
                 +  cbVigencia.getSelectedItem().toString() +"')";
           
           if(format.format(dcFecha.getDate()).equals(format.format(fecha))){
               JOptionPane.showMessageDialog(null, " la fecha no puede ser la misma");
           }else{
               
                    stmt.executeUpdate(sql);
                    controllerLogin.conexion.commit();
                    JOptionPane.showMessageDialog(null,"Se ha registrado el pago exitosamente");
                    llenarConvenio();
                    limpiar();
           }
                  
                  
                     } catch (SQLException e) {
                          JOptionPane.showMessageDialog(null, " El productor y cliente no pertenecen al contrato indicado");
                   }   
        }

    }

    public void delete(){
        
        try{
                Statement stmt = controllerLogin.conexion.createStatement();
                String sql = "DELETE FROM aja_convenio where  fecha_firma = '"+ id +"' " ;

                stmt.executeUpdate(sql);
                controllerLogin.conexion.commit();
                JOptionPane.showMessageDialog(null,"Se ha borrado el convenio exitosamente");
                llenarConvenio();
                limpiar();

           }catch (SQLException e) {
               e.printStackTrace();

           }     
    }
    
   
    
    public void update(){
       if(dcFecha.getDate()==null){
        
            JOptionPane.showMessageDialog(null, "No hay ninguna fecha registrada");
        
        } else{
            try{
                Statement stmt = controllerLogin.conexion.createStatement();
              String sql = "update aja_convenio set (beneficios, vigencia) = ('"
                  +  txtBeneficios.getText() +"', '"
                 +  cbVigencia.getSelectedItem().toString() +"')"
                         + " where fecha_firma = '"+ fecha + " ' ";
              
                    stmt.executeUpdate(sql);
                    controllerLogin.conexion.commit();
                    JOptionPane.showMessageDialog(null,"Se ha registrado el pago exitosamente");
                    llenarConvenio();
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
        jPanel3 = new javax.swing.JPanel();
        jVariedadPais1 = new javax.swing.JLabel();
        cbConvenio = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        cbConvenioEli = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jNombreVar = new javax.swing.JLabel();
        jNombreVar1 = new javax.swing.JLabel();
        jNombreVar5 = new javax.swing.JLabel();
        dcFecha = new com.toedter.calendar.JDateChooser();
        btnInsertar = new javax.swing.JButton();
        jNombreVar2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtBeneficios = new javax.swing.JTextArea();
        cbVigencia = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMINISTRACION DE VARIEDADES");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 400, 120, 30));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jVariedadPais1.setBackground(new java.awt.Color(0, 0, 0));
        jVariedadPais1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVariedadPais1.setForeground(new java.awt.Color(255, 255, 255));
        jVariedadPais1.setText("Convenio");
        jPanel3.add(jVariedadPais1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbConvenio.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbConvenio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbConvenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbConvenioActionPerformed(evt);
            }
        });
        jPanel3.add(cbConvenio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 400, 25));

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        jPanel3.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 580, 90));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Eliminar");
        btnDelete.setEnabled(false);
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 120, 30));

        cbConvenioEli.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbConvenioEli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbConvenioEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbConvenioEliActionPerformed(evt);
            }
        });
        jPanel2.add(cbConvenioEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 420, 25));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Convenio");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 590, 90));

        jNombreVar.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jNombreVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, -1, 24));

        jNombreVar1.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar1.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar1.setText("Beneficios:");
        jPanel1.add(jNombreVar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, 100, 24));

        jNombreVar5.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar5.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar5.setText("Fecha de firma:");
        jPanel1.add(jNombreVar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 24));
        jPanel1.add(dcFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 190, 30));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insetar");
        btnInsertar.setEnabled(false);
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 120, 30));

        jNombreVar2.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar2.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar2.setText("Vigencia:");
        jPanel1.add(jNombreVar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 100, 24));

        txtBeneficios.setColumns(20);
        txtBeneficios.setLineWrap(true);
        txtBeneficios.setRows(5);
        txtBeneficios.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtBeneficios);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, 110));

        cbVigencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "true", "false" }));
        jPanel1.add(cbVigencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 140, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbConvenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbConvenioActionPerformed
        if ( cbConvenio.getItemCount() > 1 ){
            cbConvenioEli.setEnabled(false);
            if (!cbConvenio.getSelectedItem().toString().equals(" ")){
               
                cortarCBP(cbConvenio);
                btnModificar.setEnabled(true);
                 btnInsertar.setEnabled(true);
                 
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    
                   ResultSet rs = stmt.executeQuery("select co.fecha_firma, co.beneficios, co.vigencia "
                            + "from aja_convenio co, aja_asociacion_regional ar, aja_proveedor pr"
                            + " where co.fecha_firma = '"+ part1.trim() +"' "
                           + " and co.id_asociacion = (select ar.id from aja_asociacion_regional ar where ar.nombre= '"+ part3.trim() +"') and ar.id=co.id_asociacion"
                            + " and co.id_proveedor = (select pr.id from aja_proveedor pr where pr.nombre = '"+ part2.trim() +"') and pr.id = co.id_proveedor");
                  
                   
                        while( rs.next() ){
                            
                            id = rs.getDate(1);
                            dcFecha.setDate(rs.getDate(1));
                            fecha =rs.getDate(1);
                            cbVigencia.setSelectedItem(rs.getString(3));
                            txtBeneficios.setText(rs.getString(2));
                            
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                cbConvenioEli.setEnabled(true);
                limpiar();
            }
        }    
    }//GEN-LAST:event_cbConvenioActionPerformed

    private void cbConvenioEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbConvenioEliActionPerformed
        if ( cbConvenioEli.getItemCount() > 1 ){
                cbConvenio.setEnabled(false);
                if (!cbConvenioEli.getSelectedItem().toString().equals(" ")){
                    
                cortarCBP(cbConvenioEli);
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                btnDelete.setEnabled(true);
                try {
                    
                    Statement stmt = controllerLogin.conexion.createStatement();
                    
                      
                  ResultSet rs = stmt.executeQuery("select co.fecha_firma, co.beneficios, co.vigencia "
                            + "from aja_convenio co, aja_asociacion_regional ar, aja_proveedor pr"
                            + " where co.fecha_firma = '"+ part1.trim() +"' "
                           + " and co.id_asociacion = (select ar.id from aja_asociacion_regional ar where ar.nombre= '"+ part3.trim() +"') and ar.id=co.id_asociacion"
                            + " and co.id_proveedor = (select pr.id from aja_proveedor pr where pr.nombre = '"+ part2.trim() +"') and pr.id = co.id_proveedor");
                   
                        while( rs.next() ){
                           id = rs.getDate(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnInsertar.setEnabled(false);
                cbConvenio.setSelectedIndex(0);
                btnDelete.setEnabled(false);
                cbConvenio.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_cbConvenioEliActionPerformed

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInsertarActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbConvenio;
    private javax.swing.JComboBox<String> cbConvenioEli;
    private javax.swing.JComboBox<String> cbVigencia;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jNombreVar;
    private javax.swing.JLabel jNombreVar1;
    private javax.swing.JLabel jNombreVar2;
    private javax.swing.JLabel jNombreVar5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jVariedadPais1;
    private javax.swing.JTextArea txtBeneficios;
    // End of variables declaration//GEN-END:variables
}
