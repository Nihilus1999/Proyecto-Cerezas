/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.cargamentos;

import controlador.controllerLogin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 *
 * @author jose-
 */
public class VariedadExportada extends javax.swing.JFrame {

    int id, id_cliente, id_productor, id_contrato, id_cultivo, id_variedad;
    String part1, part2, part3, part4;
    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
    
    public VariedadExportada() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }
    
    public void cortarVarExp( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split(" / ");
        part1= parts[0];
        String[] parts2= parts[1].split(" | ");
        part2 = parts2[0]; // nombre productor
        String[] parts3 = parts2[1].split(" / ");
        part3 = parts3[0]; // nombre variedad
        part4 = parts3[0];
    }
    
    public static boolean validarNombre(String nombre){ //Metodo que valida el nombre
        return nombre.matches("^[a-zA-Z_]+( [a-zA-Z_]+)*$");
    }
    
    public void llenarVarExp(){
        cbVarExp.removeAllItems();
        cbVarExpEli.removeAllItems();
        cbVarExp.addItem(" ");
        cbVarExpEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "Select cli.denominacion_comercial||' / '|| prod.nombre ||' | '||var.nombre ||' / '|| varexp.fecha_envio texto "
                    + " from aja_cliente cli, aja_contrato con, aja_productor prod, aja_cultivo cul, aja_variedad_de_cerezas var, aja_variedad_exportada varexp"
                    + " where cli.id= con.id_cliente and con.id_productor= prod.id and prod.id= cul.id_productor and cul.id_variedad= var.id  "
                    + " and varexp.id_cliente= con.id_cliente and varexp.id_contrato= con.id and varexp.id_productor= con.id_productor and varexp.id_cultivo= cul.id and varexp.id_cultivo_productor= cul.id_productor and varexp.id_cultivo_variedad= cul.id_variedad "
                    + " order by cli.denominacion_comercial desc ");
            while ( rs.next() ) {
                String registro = rs.getString(1);
                cbVarExp.addItem(registro);
                cbVarExpEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void limpiar(){
        cbVarExp.setSelectedIndex(0);
        txtDescripcion.setText(null);
        txtCant.setText(null);
        txtDescuento.setText(null);
        dateFechaEnvio.setDate(null);
    }
    
    public void insert(){
        try {
           cortarVarExp(cbVarExp);
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_variedad_exportada "
                   + "  (id_cliente, id_productor, id_contrato, id_cultivo, id_cultivo_productor, id_cultivo_variedad, fecha_envio, cantidad, descripcion, porcentaje_descuento)  "
                   + " VALUES "
                   + " ( "+id_cliente+",  "
                   + " "+id_productor+",  "
                   + " "+id_contrato+",  "
                   + " "+id_cultivo+",  "
                   + " "+id_productor+",  "
                   + " "+id_variedad+",  "
                   + " '"+format.format(dateFechaEnvio.getDate())+"',  "
                   + " "+txtCant.getText()+",  "
                   + " '"+txtDescripcion.getText()+"',  "
                   + " "+txtDescuento.getText()+") " ;
           
                    if(true){
                        stmt.executeUpdate(sql);
                        controllerLogin.conexion.commit();
                        JOptionPane.showMessageDialog(null,"Se ha registrado exitosamente");
                        llenarVarExp();
                        limpiar();
                    }
        } catch (Exception e) {
        }
    }

    public void update(){
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            String sql ="update from aja_variedad_exportada set fecha_envio='2020-12-31' "
                    + " where id="+id;
            
        } catch (Exception e) {
        }
    }
    
    public void delete(){
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            String sql = "delete from aja_variedad_exportada "
                    + "where id =" + id
                    + " and id_contrato="+id_contrato+" "
                    + " and id_cultivo="+id_cultivo+" ";
            ResultSet rs = stmt.executeQuery( "select id from aja_variedad_exportada" );
            int flag=0;
            while ( rs.next() ) {
                 if( rs.getInt(1) == id ){/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                     stmt.executeUpdate(sql);
                     controllerLogin.conexion.commit();
                     JOptionPane.showMessageDialog(null,"Se ha borrado el registrado exitosamente");
                     llenarVarExp();
                     limpiar();
                     flag = 1;
                     break;
                 }
              }
              if(flag == 0){
                  JOptionPane.showMessageDialog(null,"No se encontro el registro");
              }
        } catch (Exception e) {
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        txtCant = new javax.swing.JTextField();
        dateFechaEnvio = new com.toedter.calendar.JDateChooser();
        btnInsert = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cbVarExp = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        txtVarExp = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cbVarExpEli = new javax.swing.JComboBox<>();
        btnDelete = new javax.swing.JButton();
        txtVarExpEli = new javax.swing.JLabel();
        txtProd1 = new javax.swing.JLabel();
        txtProd2 = new javax.swing.JLabel();
        txtProd3 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        txtProd4 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(227, 141, 4));

        jPanel1.setBackground(new java.awt.Color(255, 204, 51));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");

        txtCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantActionPerformed(evt);
            }
        });

        btnInsert.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsert.setForeground(new java.awt.Color(0, 0, 0));
        btnInsert.setText("Insertar");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(227, 141, 4));
        jPanel2.setToolTipText("");

        cbVarExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVarExpActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");

        txtVarExp.setBackground(new java.awt.Color(0, 0, 0));
        txtVarExp.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtVarExp.setForeground(new java.awt.Color(255, 255, 255));
        txtVarExp.setText("Variedad exportada por:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtVarExp)
                        .addGap(0, 72, Short.MAX_VALUE))
                    .addComponent(cbVarExp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(txtVarExp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbVarExp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanel3.setBackground(new java.awt.Color(227, 141, 4));

        cbVarExpEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVarExpEliActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Eliminar");

        txtVarExpEli.setBackground(new java.awt.Color(0, 0, 0));
        txtVarExpEli.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtVarExpEli.setForeground(new java.awt.Color(255, 255, 255));
        txtVarExpEli.setText("Variedad exportada por:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtVarExpEli)
                        .addGap(0, 68, Short.MAX_VALUE))
                    .addComponent(cbVarExpEli, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(txtVarExpEli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbVarExpEli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        txtProd1.setBackground(new java.awt.Color(0, 0, 0));
        txtProd1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd1.setForeground(new java.awt.Color(255, 255, 255));
        txtProd1.setText("Fecha envio:");

        txtProd2.setBackground(new java.awt.Color(0, 0, 0));
        txtProd2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd2.setForeground(new java.awt.Color(255, 255, 255));
        txtProd2.setText("Cantidad:");

        txtProd3.setBackground(new java.awt.Color(0, 0, 0));
        txtProd3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd3.setForeground(new java.awt.Color(255, 255, 255));
        txtProd3.setText("Porcentaje de descuento:");

        txtProd4.setBackground(new java.awt.Color(0, 0, 0));
        txtProd4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd4.setForeground(new java.awt.Color(255, 255, 255));
        txtProd4.setText("Descripcion:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtProd1)
                    .addComponent(txtProd2)
                    .addComponent(txtCant)
                    .addComponent(dateFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProd3)
                    .addComponent(txtDescuento))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProd4))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRegresar))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtProd1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtProd2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtProd3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtProd4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRegresar))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbVarExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVarExpActionPerformed
        if (cbVarExp.getItemCount() > 1){
            if(!cbVarExp.getSelectedItem().toString().equals(" ")){
                cortarVarExp(cbVarExp );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery( "Select varexp.id, varexp.id_contrato, varexp.id_cliente, varexp.id_productor, varexp.id_cultivo, varexp.id_cultivo_variedad, varexp.fecha_envio, varexp.cantidad, varexp.descripcion, varexp.porcentaje_descuento"
                    + " from aja_cliente cli, aja_contrato con, aja_productor prod, aja_cultivo cul, aja_variedad_de_cerezas var, aja_variedad_exportada varexp"
                    + " where cli.id= con.id_cliente and con.id_productor= prod.id and prod.id= cul.id_productor and cul.id_variedad= var.id  "
                    + " and varexp.id_cliente= con.id_cliente and varexp.id_contrato= con.id and varexp.id_productor= con.id_productor and varexp.id_cultivo= cul.id and varexp.id_cultivo_productor= cul.id_productor and varexp.id_cultivo_variedad= cul.id_variedad "
                    + " and cli.denominacion_comercial= '"+part1.trim()+"'  "
                    + " and prod.nombre= '"+part2.trim()+"'  "
                    + " and var.nombre = '"+part3.trim()+"'  "
                    + " and varexp.fecha_envio='" +format.format(part4.trim())+"' ");
                        while( rs.next() ){
                            id = rs.getInt(1);
                            id_contrato = rs.getInt(2);
                            id_cliente = rs.getInt(3);
                            id_productor = rs.getInt(4);
                            id_cultivo = rs.getInt(5);
                            id_variedad = rs.getInt(6);
                            txtCant.setText(rs.getString(8));
                            txtDescripcion.setText(rs.getString(9));
                            txtDescuento.setText(rs.getString(10));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                limpiar();
                btnDelete.setEnabled(true);
                cbVarExp.setEnabled(true);
                btnInsert.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cbVarExpActionPerformed

    private void cbVarExpEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVarExpEliActionPerformed
       if (cbVarExp.getItemCount() > 1){
            if(!cbVarExp.getSelectedItem().toString().equals(" ")){
                cortarVarExp(cbVarExp );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery( "Select varexp.id, varexp.id_contrato, varexp.id_cliente, varexp.id_productor, varexp.id_cultivo, varexp.id_cultivo_variedad "
                    + " from aja_cliente cli, aja_contrato con, aja_productor prod, aja_cultivo cul, aja_variedad_de_cerezas var, aja_variedad_exportada varexp"
                    + " where cli.id= con.id_cliente and con.id_productor= prod.id and prod.id= cul.id_productor and cul.id_variedad= var.id  "
                    + " and varexp.id_cliente= con.id_cliente and varexp.id_contrato= con.id and varexp.id_productor= con.id_productor and varexp.id_cultivo= cul.id and varexp.id_cultivo_productor= cul.id_productor and varexp.id_cultivo_variedad= cul.id_variedad "
                    + " and cli.denominacion_comercial= '"+part1.trim()+"'  "
                    + " and prod.nombre ='"+part2.trim()+"'  "
                    + " and var.nombre = '"+part3.trim()+"'  "
                    + " and varexp.fecha_envio='" +format.format(part4.trim())+"' " );
                        while( rs.next() ){
                            id = rs.getInt(1);
                            id_contrato = rs.getInt(2);
                            id_cliente = rs.getInt(3);
                            id_productor = rs.getInt(4);
                            id_cultivo = rs.getInt(5);
                            id_variedad = rs.getInt(6);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_cbVarExpEliActionPerformed

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInsertActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsert;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JComboBox<String> cbVarExp;
    public javax.swing.JComboBox<String> cbVarExpEli;
    public com.toedter.calendar.JDateChooser dateFechaEnvio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JTextField txtCant;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtDescuento;
    private javax.swing.JLabel txtProd1;
    private javax.swing.JLabel txtProd2;
    private javax.swing.JLabel txtProd3;
    private javax.swing.JLabel txtProd4;
    private javax.swing.JLabel txtVarExp;
    private javax.swing.JLabel txtVarExpEli;
    // End of variables declaration//GEN-END:variables
}
