/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.evaluacion;

/**
 *
 * @author jose-
 */
public class Resultado extends javax.swing.JFrame {

    /**
     * Creates new form Resultado
     */
    public Resultado() {
        initComponents();
    }

    public void delete(){
        
        try{
                Statement stmt = controllerLogin.conexion.createStatement();
                String sql = "DELETE FROM aja_resultado where  yyyy = '"+ id +"' " ;

                stmt.executeUpdate(sql);
                controllerLogin.conexion.commit();
                JOptionPane.showMessageDialog(null,"Se ha borrado el resultado exitosamente");
                llenarResultado();
                limpiar();

           }catch (SQLException e) {
               e.printStackTrace();

           }     
    }
    
   
    
    public void update(){
       if(spPorcentaje.equals("0")){
        
            JOptionPane.showMessageDialog(null, "El campo porcentaje no puede tener valor 0");
        
        } else{
            try{
                Statement stmt = controllerLogin.conexion.createStatement();
               String sql = "update aja_resultado set (desicion, resultado_valor, resultado_porcentaje, fecha, observacion) = ('"
                  +  cbDecision.getSelectedItem().toString() +"', "
                   +  spValor.getValue()+", "
                   +  spPorcentaje.getValue()+", '"
                   +  format.format(dcFecha.getDate()) +"', '"
                  +  txtObservaciones.getText() +"')"
                       + " where yyyy = '"+ fecha + " ' ";
                
               
               
                    if(format.format(dcFecha.getDate()).equals(format.format(fecha))){
                        
               JOptionPane.showMessageDialog(null, " la fecha no puede ser la misma");
               
           }else{
               
                    stmt.executeUpdate(sql);
                    controllerLogin.conexion.commit();
                    JOptionPane.showMessageDialog(null,"Se ha registrado el resultado exitosamente");
                    cbProductor.setEnabled(true);
                    cbCliente.setEnabled(true);
                    dcYear.setEnabled(true);
                    llenarResultado();
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

        btnRegresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnRegresar.setText("Regresar");

        jLabel1.setText("Resultado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegresar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jLabel1)
                .addContainerGap(174, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
                .addComponent(btnRegresar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbResultadoActionPerformed
        if ( cbResultado.getItemCount() > 1 ){
            cbResultadoEli.setEnabled(false);
            if (!cbResultado.getSelectedItem().toString().equals(" ")){
               
                cortarCBP(cbResultado);
                btnModificar.setEnabled(true);
                btnInsertar.setEnabled(false);
                cbProductor.setEnabled(false);
                cbCliente.setEnabled(false);
                dcYear.setEnabled(false);
                
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    
                  ResultSet rs = stmt.executeQuery("select re.yyyy, re.desicion, re.resultado_valor, re.resultado_porcentaje, re.fecha, re.observacion,"
                            + " ci.denominacion_comercial, pr.nombre "
                            + "from aja_resultado re, aja_cliente ci, aja_productor pr "
                            + " where re.id_cliente= (select ci.id from aja_cliente ci where ci.denominacion_comercial = '"+ part2.trim() +"')  and ci.id=re.id_cliente"
                            + " and re.id_productor = (select pr.id from aja_productor pr where pr.nombre = '"+ part3.trim() +"') and pr.id = re.id_productor"
                            + " and re.yyyy= '" + part1.trim()   +"' ");
                   
                        while( rs.next() ){
                            
                            id = rs.getDate(1);
                            dcYear.setDate(rs.getDate(1));
                            fecha = rs.getDate(1);
                            cbDecision.setSelectedItem(rs.getString(2));
                            spValor.setValue(rs.getInt(3));
                            spPorcentaje.setValue(rs.getInt(4));
                            dcFecha.setDate(rs.getDate(5));
                            txtObservaciones.setText(rs.getString(6));
                            cbCliente.setSelectedItem(rs.getString(7));
                            cbProductor.setSelectedItem(rs.getString(8));
                            
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(true);
                cbResultadoEli.setEnabled(true);
                limpiar();
            }
        }    
    }//GEN-LAST:event_cbResultadoActionPerformed

    private void cbResultadoEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbResultadoEliActionPerformed
        if ( cbResultadoEli.getItemCount() > 1 ){
                cbResultado.setEnabled(false);
                if (!cbResultadoEli.getSelectedItem().toString().equals(" ")){
                    
                cortarCBP(cbResultadoEli);
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                btnDelete.setEnabled(true);
                try {
                    
                    Statement stmt = controllerLogin.conexion.createStatement();
                    
                  ResultSet rs = stmt.executeQuery("select re.yyyy, re.desicion, re.resultado_valor, re.resultado_porcentaje, re.fecha, re.observacion,"
                            + " ci.denominacion_comercial, pr.nombre "
                            + "from aja_resultado re, aja_cliente ci, aja_productor pr "
                            + " where re.id_cliente= (select ci.id from aja_cliente ci where ci.denominacion_comercial = '"+ part2.trim() +"')  and ci.id=re.id_cliente"
                            + " and re.id_productor = (select pr.id from aja_productor pr where pr.nombre = '"+ part3.trim() +"') and pr.id = re.id_productor"
                            + " and re.yyyy= '" + part1.trim()   +"' ");
                            
                   
                        while( rs.next() ){
                            id = rs.getDate(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnInsertar.setEnabled(true);
                cbResultado.setSelectedIndex(0);
                btnDelete.setEnabled(false);
                cbResultado.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_cbResultadoEliActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
