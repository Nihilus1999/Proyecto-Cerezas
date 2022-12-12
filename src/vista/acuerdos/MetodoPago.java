/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package vista.acuerdos;

import controlador.controllerLogin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vista.produccion.Cultivo;

/**
 *
 * @author jose-
 */
public class MetodoPago extends javax.swing.JFrame {

    String id_metodo;
    
    public MetodoPago() {
        initComponents();
    }

    public void insert(){
        try {
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql;
           if( cbTipo.getSelectedItem().toString().equals("contado") ){
               sql = "INSERT INTO public.aja_metodo_de_pago( " 
                   + "id_productor, tipo, cantidad_cuota, porcentaje_cuota, contado_pago_contrato, contado_pago_envio) " 
                   + "VALUES ((SELECT id from aja_productor where nombre = '"+cbProductor.getSelectedItem().toString()+"'), '"+cbTipo.getSelectedItem().toString()+"', null, null, 0, 0) " ;
           }else{
               sql = "INSERT INTO public.aja_metodo_de_pago( " 
                   + "id_productor, tipo, cantidad_cuota, porcentaje_cuota, contado_pago_contrato, contado_pago_envio) " 
                   + "VALUES ((SELECT id from aja_productor where nombre = '"+cbProductor.getSelectedItem().toString()+"'), '"+cbTipo.getSelectedItem().toString()+"', "+spiCuotas.getValue()+", "+spiPorcentaje.getValue()+", null, null) " ;
           }
           
           
            stmt.executeUpdate(sql);
            controllerLogin.conexion.commit();
            JOptionPane.showMessageDialog(null,"Se ha registrado exitosamente");
            llenarCbsM();
            limpiar();
        } catch (SQLException e) {
              e.printStackTrace();
        }
    }
    
    public void update(){
        try {
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql;
           if( cbTipo.getSelectedItem().toString().equals("contado") ){
               sql = "UPDATE aja_metodo_de_pago SET ( " 
                   + "tipo, cantidad_cuota, porcentaje_cuota, contado_pago_contrato, contado_pago_envio) " 
                   + "= ('"+cbTipo.getSelectedItem().toString()+"', null, null, 0, 0) where id = "+id_metodo+" " ;
           }else{
               sql = "UPDATE aja_metodo_de_pago SET( " 
                   + "tipo, cantidad_cuota, porcentaje_cuota, contado_pago_contrato, contado_pago_envio) " 
                   + "= ('"+cbTipo.getSelectedItem().toString()+"', "+spiCuotas.getValue()+", "+spiPorcentaje.getValue()+", null, null) where id = "+id_metodo+" " ;
           }
           
           
            stmt.executeUpdate(sql);
            controllerLogin.conexion.commit();
            JOptionPane.showMessageDialog(null,"Se ha actualizado exitosamente");
            llenarCbsM();
            limpiar();
        } catch (SQLException e) {
              e.printStackTrace();
        }
    }
    
    public void delete(){
        try {
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "DELETE FROM aja_metodo_de_pago " 
                   + "WHERE id = "+id_metodo+" " ;
           
            stmt.executeUpdate(sql);
            controllerLogin.conexion.commit();
            JOptionPane.showMessageDialog(null,"Se ha eliminado exitosamente");
            llenarCbsM();
            limpiar();
        } catch (SQLException e) {
              e.printStackTrace();
        }
    }
    
    public void limpiar(){
        
    }
    
    public void llenarPro(){
        try {
            cbProductor.addItem(" ");
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select nombre from aja_productor" );
            while(rs.next()){
                String registro = rs.getString(1);
                cbProductor.addItem(registro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cultivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void llenarCbsM(){
         try {
            cbMetodoP.removeAllItems();
            cbMetodoPEli.removeAllItems();
            cbMetodoP.addItem(" ");
            cbMetodoPEli.addItem(" ");
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select 'Metodo de Pago #' || id from aja_metodo_de_pago where id_productor = (SELECT id from aja_productor where nombre = '"+cbProductor.getSelectedItem().toString()+"')" );
            while(rs.next()){
                String registro = rs.getString(1);
                cbMetodoP.addItem(registro);
                cbMetodoPEli.addItem(registro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cultivo.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void cortarM( javax.swing.JComboBox combo ){
        id_metodo = combo.getSelectedItem().toString().replaceAll("Metodo de Pago #", "");
     }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jMetodoP = new javax.swing.JLabel();
        cbMetodoP = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jProductor = new javax.swing.JLabel();
        cbProductor = new javax.swing.JComboBox<>();
        jTipo = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        jCantidadC = new javax.swing.JLabel();
        spiCuotas = new javax.swing.JSpinner();
        jPorcentaje = new javax.swing.JLabel();
        spiPorcentaje = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jMetodoPEli = new javax.swing.JLabel();
        cbMetodoPEli = new javax.swing.JComboBox<>();
        btnEliminar = new javax.swing.JButton();
        btnInsertar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jMetodoP.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jMetodoP.setForeground(new java.awt.Color(255, 255, 255));
        jMetodoP.setText("Metodo de pago");
        jPanel2.add(jMetodoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbMetodoP.setEnabled(false);
        cbMetodoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMetodoPActionPerformed(evt);
            }
        });
        jPanel2.add(cbMetodoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        jPanel2.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 120, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 410, 90));

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, 120, 30));

        jProductor.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jProductor.setForeground(new java.awt.Color(255, 255, 255));
        jProductor.setText("Productor");
        jPanel1.add(jProductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        cbProductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductorActionPerformed(evt);
            }
        });
        jPanel1.add(cbProductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 255, 25));

        jTipo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTipo.setForeground(new java.awt.Color(255, 255, 255));
        jTipo.setText("Tipo");
        jPanel1.add(jTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "contado", "cuotas" }));
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });
        jPanel1.add(cbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 255, 25));

        jCantidadC.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCantidadC.setForeground(new java.awt.Color(255, 255, 255));
        jCantidadC.setText("Cantidad de cuotas");
        jPanel1.add(jCantidadC, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        spiCuotas.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spiCuotas.setEnabled(false);
        jPanel1.add(spiCuotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 128, 25));

        jPorcentaje.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPorcentaje.setForeground(new java.awt.Color(255, 255, 255));
        jPorcentaje.setText("Porcentaje por cuota");
        jPanel1.add(jPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        spiPorcentaje.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(1.0f), Float.valueOf(100.0f), Float.valueOf(1.0f)));
        spiPorcentaje.setEnabled(false);
        jPanel1.add(spiPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 128, 25));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jMetodoPEli.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jMetodoPEli.setForeground(new java.awt.Color(255, 255, 255));
        jMetodoPEli.setText("Metodo de pago");
        jPanel3.add(jMetodoPEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbMetodoPEli.setEnabled(false);
        cbMetodoPEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMetodoPEliActionPerformed(evt);
            }
        });
        jPanel3.add(cbMetodoPEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 410, 90));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        btnInsertar.setEnabled(false);
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 350, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbProductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductorActionPerformed
        if (cbProductor.getItemCount() > 1){
            if(!cbProductor.getSelectedItem().toString().equals(" ")){
                cbMetodoP.setEnabled(true);
                cbMetodoPEli.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnModificar.setEnabled(true);
                btnInsertar.setEnabled(true);
                llenarCbsM();
            }else{
                cbMetodoP.setEnabled(false);
                cbMetodoPEli.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cbProductorActionPerformed

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
        if(!cbTipo.getSelectedItem().toString().equals("contado")){
            spiCuotas.setEnabled(true);
            spiPorcentaje.setEnabled(true);
        }else{
            spiCuotas.setEnabled(false);
            spiPorcentaje.setEnabled(false);
        }
    }//GEN-LAST:event_cbTipoActionPerformed

    private void cbMetodoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMetodoPActionPerformed
        if (cbMetodoP.getItemCount() > 1){
            if(!cbMetodoP.getSelectedItem().toString().equals(" ")){
                cbMetodoPEli.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnInsertar.setEnabled(false);
                cortarM( cbMetodoP );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery( "SELECT pro.nombre, mt.tipo, mt.cantidad_cuota, mt.porcentaje_cuota from aja_productor pro, aja_metodo_de_pago mt where mt.id =  "+id_metodo+" and pro.id = mt.id_productor" );
                    while(rs.next()){
                        if ( rs.getString(2).equals("cuotas") ){
                            cbTipo.setSelectedItem("cuotas");
                            spiCuotas.setEnabled(true);
                            spiPorcentaje.setEnabled(true);
                            spiCuotas.setValue(rs.getInt(3));
                            spiPorcentaje.setValue(rs.getInt(4));
                        }else{
                            cbTipo.setSelectedItem("contado");
                            spiCuotas.setEnabled(false);
                            spiPorcentaje.setEnabled(false);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Cultivo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                cbMetodoPEli.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnInsertar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cbMetodoPActionPerformed

    private void cbMetodoPEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMetodoPEliActionPerformed
        if (cbMetodoPEli.getItemCount() > 1){
            if(!cbMetodoPEli.getSelectedItem().toString().equals(" ")){
                cbMetodoP.setEnabled(false);
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                cortarM( cbMetodoPEli );
            }else{
                cbMetodoP.setEnabled(true);
                btnModificar.setEnabled(true);
                btnInsertar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cbMetodoPEliActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JComboBox<String> cbMetodoP;
    private javax.swing.JComboBox<String> cbMetodoPEli;
    private javax.swing.JComboBox<String> cbProductor;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JLabel jCantidadC;
    private javax.swing.JLabel jMetodoP;
    private javax.swing.JLabel jMetodoPEli;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jPorcentaje;
    private javax.swing.JLabel jProductor;
    private javax.swing.JLabel jTipo;
    private javax.swing.JSpinner spiCuotas;
    private javax.swing.JSpinner spiPorcentaje;
    // End of variables declaration//GEN-END:variables

}
