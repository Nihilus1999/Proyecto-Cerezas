/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.evaluacion;

import controlador.controllerLogin;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pago extends javax.swing.JFrame {
    
    int id;
    String part1;
    String part2;
    String part3;
    String part4;
    String part5;
    String part6;
    
    Date fecha;
    
    ResultSet rs;
    
    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
    
    public Pago() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }
    
    public static boolean validarNombre(String nombre){ //Metodo que valida el nombre
        return nombre.matches("^[a-zA-Z_]+( [a-zA-Z_]+)*$");
    }
    
      public void cortarCBP( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split("/");
        part1 = parts[0].replaceAll("Pago#", " ");//Nombre del cliente
        part2 = parts[1].replaceAll("Contrato#", " ");
        part3 = parts[2];//nombre de la varieble
        part4 = parts[3];
}
    
    
    
    public void llenarPago(){
        cbPago.removeAllItems();
        cbPagoEli.removeAllItems();
        cbPago.addItem(" ");
        cbPagoEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select 'Pago#' || pa.id || ' / ' || 'Contrato#' || co.id || ' / ' ||   po.nombre || ' / ' ||  ci.denominacion_comercial as texto " +
            "from aja_pago pa ,aja_contrato co, aja_cliente ci, aja_productor po " +
            "where pa.id_cliente = ci.id and pa.id_productor = po.id and pa.id_contrato=co.id"
                    + " order by  pa.id asc" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbPago.addItem(registro);
            cbPagoEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void limpiar(){
        spMonto.setValue(0);
        dcFecha.setDate(null);
        cbPago.setSelectedIndex(0);
        cbPagoEli.setSelectedIndex(0);
    }
    
    public void inserts(){
        
        
        
       if(dcFecha.getDate()==null || spMonto.getValue()==null){
            JOptionPane.showMessageDialog(null, "No hay ninguna fecha registrada");
        } else{
            try {
            
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_pago (id_cliente, id_productor, id_contrato, fecha, monto) values ("
                  + "(select id from aja_cliente where denominacion_comercial='"+ part4.trim() + "'),"
                  + "(select id from aja_productor where nombre='"+ part3.trim() + "'),"
                  + "(select id from aja_contrato where id="+ part2.trim() + "),'"
                  + format.format(dcFecha.getDate()) +"' ,"
                  +  spMonto.getValue() +")";
           
           if(format.format(dcFecha.getDate()).equals(format.format(fecha))){
               JOptionPane.showMessageDialog(null, " la fecha no puede ser la misma");
           }else{
               
                    stmt.executeUpdate(sql);
                    controllerLogin.conexion.commit();
                    JOptionPane.showMessageDialog(null,"Se ha registrado el pago exitosamente");
                    llenarPago();
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
                String sql = "DELETE FROM aja_pago where  id = " + id;

                stmt.executeUpdate(sql);
                controllerLogin.conexion.commit();
                JOptionPane.showMessageDialog(null,"Se ha borrado el pago exitosamente");
                llenarPago();
                limpiar();

           }catch (SQLException e) {
               e.printStackTrace();

           }     
    }
    
   
    
    public void update(){
       if(dcFecha.getDate()==null || spMonto.getValue()==null){
        
            JOptionPane.showMessageDialog(null, "No hay ninguna fecha registrada");
        
        } else{
            try{
                Statement stmt = controllerLogin.conexion.createStatement();
              String sql = "update aja_pago set (id_cliente, id_productor, id_contrato, fecha, monto) = ("
                  + "(select id from aja_cliente where denominacion_comercial='"+ part4.trim() + "'),"
                  + "(select id from aja_productor where nombre='"+ part3.trim() + "'),"
                  + "(select id from aja_contrato where id="+ part2.trim() + "),'"
                  + format.format(dcFecha.getDate()) +"' ,"
                  +  spMonto.getValue() +")";
                
                     if(format.format(dcFecha.getDate()).equals(format.format(fecha))){
               JOptionPane.showMessageDialog(null, " la fecha no puede ser la misma");
           }else{
               
                    stmt.executeUpdate(sql);
                    controllerLogin.conexion.commit();
                    JOptionPane.showMessageDialog(null,"Se ha registrado el pago exitosamente");
                    llenarPago();
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
        jPanel3 = new javax.swing.JPanel();
        jVariedadPais1 = new javax.swing.JLabel();
        cbPago = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        cbPagoEli = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jNombreVar = new javax.swing.JLabel();
        jNombreVar1 = new javax.swing.JLabel();
        spMonto = new javax.swing.JSpinner();
        jNombreVar5 = new javax.swing.JLabel();
        dcFecha = new com.toedter.calendar.JDateChooser();
        btnInsertar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMINISTRACION DE VARIEDADES");

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 400, 120, 30));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jVariedadPais1.setBackground(new java.awt.Color(0, 0, 0));
        jVariedadPais1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVariedadPais1.setForeground(new java.awt.Color(255, 255, 255));
        jVariedadPais1.setText("Pago");
        jPanel3.add(jVariedadPais1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbPago.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPagoActionPerformed(evt);
            }
        });
        jPanel3.add(cbPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 400, 25));

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        jPanel3.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 580, 90));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Eliminar");
        btnDelete.setEnabled(false);
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 120, 30));

        cbPagoEli.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbPagoEli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbPagoEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPagoEliActionPerformed(evt);
            }
        });
        jPanel2.add(cbPagoEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 420, 25));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Pago");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 590, 90));

        jNombreVar.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jNombreVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, -1, 24));

        jNombreVar1.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar1.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar1.setText("Monto:");
        jPanel1.add(jNombreVar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 70, 24));
        jPanel1.add(spMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, 180, 30));

        jNombreVar5.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar5.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar5.setText("Fecha pago:");
        jPanel1.add(jNombreVar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 24));
        jPanel1.add(dcFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 150, 30));

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

    private void cbPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPagoActionPerformed
        if ( cbPago.getItemCount() > 1 ){
            cbPagoEli.setEnabled(false);
            if (!cbPago.getSelectedItem().toString().equals(" ")){
               
                cortarCBP(cbPago);
                btnModificar.setEnabled(true);
                 btnInsertar.setEnabled(true);
                 
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    
                   ResultSet rs = stmt.executeQuery("select pa.id, pa.fecha, pa.monto "
                            + "from aja_pago pa, aja_cliente ci, aja_productor pr, aja_contrato co"
                            + " where pa.id= '"+ part1.trim() +"' "
                           + " and pa.id_cliente = (select ci.id from aja_cliente ci where ci.denominacion_comercial = '"+ part4.trim() +"') and ci.id=pa.id_cliente"
                            + " and pa.id_productor = (select pr.id from aja_productor pr where pr.nombre = '"+ part3.trim() +"') and pr.id = pa.id_productor"
                            + " and pa.id_contrato = '"+ part2.trim() +"' and co.id = pa.id_contrato");
                   
                        while( rs.next() ){
                            
                            id = rs.getInt(1);
                            fecha = rs.getDate(2);
                            dcFecha.setDate(rs.getDate(2));
                            spMonto.setValue(rs.getInt(3));
                            
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                cbPagoEli.setEnabled(true);
                limpiar();
            }
        }    
    }//GEN-LAST:event_cbPagoActionPerformed

    private void cbPagoEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPagoEliActionPerformed
        if ( cbPagoEli.getItemCount() > 1 ){
                cbPago.setEnabled(false);
                if (!cbPagoEli.getSelectedItem().toString().equals(" ")){
                    
                cortarCBP(cbPagoEli);
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                btnDelete.setEnabled(true);
                try {
                    
                    Statement stmt = controllerLogin.conexion.createStatement();
                    
                     ResultSet rs = stmt.executeQuery("select pa.id, pa.fecha, pa.monto "
                            + "from aja_pago pa, aja_cliente ci, aja_productor pr, aja_contrato co"
                            + " where pa.id= '"+ part1.trim() +"' "
                           + " and pa.id_cliente = (select ci.id from aja_cliente ci where ci.denominacion_comercial = '"+ part4.trim() +"') and ci.id=pa.id_cliente"
                            + " and pa.id_productor = (select pr.id from aja_productor pr where pr.nombre = '"+ part3.trim() +"') and pr.id = pa.id_productor"
                            + " and pa.id_contrato = '"+ part2.trim() +"' and co.id = pa.id_contrato");
                   
                        while( rs.next() ){
                            id = rs.getInt(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnInsertar.setEnabled(false);
                cbPago.setSelectedIndex(0);
                btnDelete.setEnabled(false);
                cbPago.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_cbPagoEliActionPerformed

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
    private javax.swing.JComboBox<String> cbPago;
    private javax.swing.JComboBox<String> cbPagoEli;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jNombreVar;
    private javax.swing.JLabel jNombreVar1;
    private javax.swing.JLabel jNombreVar5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jVariedadPais1;
    private javax.swing.JSpinner spMonto;
    // End of variables declaration//GEN-END:variables
}
