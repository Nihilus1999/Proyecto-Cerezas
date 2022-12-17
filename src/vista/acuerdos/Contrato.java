/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.acuerdos;

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
public class Contrato extends javax.swing.JFrame {

    /**
     * Creates new form Contrato
     */
    int id, id_cliente, id_productor;
    String part1, part2, part3, part4;
    
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public Contrato() {
        initComponents();
    setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }

    public void llenarContrato(){
        cbContrato.removeAllItems();
        cbContratoEli.removeAllItems();
        cbContrato.addItem(" ");
        cbContratoEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery(" select cli.denominacion_comercial ||' / '|| prod.nombre as texto "
                    + " from aja_cliente cli, aja_contrato con, aja_productor prod where con.id_productor= prod.id and con.id_cliente= cli.id "
                    + " order by con.id desc");
            while ( rs.next() ) {
                String registro = rs.getString(1);
                cbContrato.addItem(registro);
                cbContratoEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void llenarT(){
        cbTransporte.addItem("Aereo");
        cbTransporte.addItem("Terrestre");
        cbTransporte.addItem("Maritimo");        
    }
    
    public void llenarCliente(){
        cbCliente.removeAllItems();
        cbCliente.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select ci.denominacion_comercial"
                    + " from aja_cliente ci "
                    + " order by 1 desc" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbCliente.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void llenarProd(){
        cbProductor.removeAllItems();
        cbProductor.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "Select prod.nombre  "
                    + " from aja_productor prod "
                    + "order by 1 desc" );
            while ( rs.next() ) {
                String registro = rs.getString(1);
                cbProductor.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void llenarMetodo(){
        cbMetodo.removeAllItems();
        cbMetodo.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "Select 'Metodo #' || met.id  "
                    + " from AJA_METODO_DE_PAGO met "
                    + " where met.id_productor = (SELECT id from aja_productor where nombre = '"+cbProductor.getSelectedItem().toString()+"')"
                            + " and met.id not in (select id from aja_contrato)  " );
            while ( rs.next() ) {
                String registro = rs.getString(1);
                cbMetodo.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void cortarCB( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split(" / ");
        part1= parts[0];//cliente
        part2 = parts[1];//productor
    }
    
    public void limpiar(){
        dateFirma.setDate(null);
        cbTransporte.setSelectedIndex(0);
        txtEstatus.setText(null);
        txtPrecio.setText(null);
        txtDescuento.setText(null);
        txtRazon.setText(null);
        dateCancela.setDate(null);
        cbCliente.setSelectedIndex(0);
        cbProductor.setSelectedIndex(0);
    }
    
    public void insert(){
        try {
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = " INSERT INTO aja_contrato " 
                   + " (id_cliente, id_productor, fecha_firma, tipo, estatus, precio_contrato, porcentaje_descuento, "
                   + " id_metodo_pago, id_productor_metodo_pago, fecha_cancelacion, razon_cancelacion) "
                   + " VALUES "
                   + " ( (select cli.id from aja_cliente cli where cli.denominacion_comercial='"+cbCliente.getSelectedItem().toString()+"' ), "
                   + " (select prod.id from aja_productor prod where prod.nombre='"+cbProductor.getSelectedItem().toString()+"' ), "
                   + " '"+format.format(dateFirma.getDate()) +"', "
                   + " '"+cbTransporte.getSelectedItem().toString().charAt(0)+"', "
                   + " '"+txtEstatus.getText()+"', "
                   + " "+txtPrecio.getText()+", "
                   + " "+txtDescuento.getText()+", "
                   + " " +cbMetodo.getSelectedItem().toString().replaceAll("Metodo #", "")+", "
                   + " (select prod.id from aja_productor prod where prod.nombre='"+cbProductor.getSelectedItem().toString()+"' ), "
                   + "null, null)";
                           stmt.executeUpdate(sql);
                           controllerLogin.conexion.commit();
                     sql = "UPDATE aja_metodo_de_pago SET (contado_pago_contrato, contado_pago_envio) = ("+txtPrecio+"*.85, "+txtPrecio+"*.15) where id="+cbMetodo.getSelectedItem().toString().replaceAll("Metodo #", "")+" and tipo='contado'";
                           JOptionPane.showMessageDialog(null,"Se ha registrado exitosamente");
                           llenarContrato();
                           limpiar();

                     } catch (SQLException e) {
                           e.printStackTrace();
                   }
    }
    
    public void delete(){
        try{
                Statement stmt = controllerLogin.conexion.createStatement();
                String sql = "DELETE FROM aja_contrato where  id = " + id;

                ResultSet rs = stmt.executeQuery( "select id from aja_contrato" );

              int flag=0;

              while ( rs.next() ) {
                   if(rs.getInt("id") == id){
                       stmt.executeUpdate(sql);
                       controllerLogin.conexion.commit();
                       JOptionPane.showMessageDialog(null,"Se ha borrado el contrato exitosamente");
                       llenarContrato();
                       limpiar();
                       flag = 1;
                       break;
                   }
                }
              if(flag == 0){
                  JOptionPane.showMessageDialog(null,"No se encontro el contrato");
              }
           }catch (SQLException e) {
               e.printStackTrace();
           }     
    }
    
    public void update(){
        try{
            Statement stmt = controllerLogin.conexion.createStatement();
            String sql = "Update aja_contrato " 
                    + " set "
                    + " id_cliente = (select cli.id from aja_cliente cli where cli.denominacion_comercial='"+cbCliente.getSelectedItem().toString()+"' ), "
                    + " id_productor =(select prod.id from aja_productor prod where prod.nombre='"+cbProductor.getSelectedItem().toString()+"' ), "
                    + " fecha_firma= '"+format.format(dateFirma.getDate()) +"', "
                    + " estatus = '" +txtEstatus.getText()+"', "
                    + " precio_contrato = "+txtPrecio.getText()+", ";
           if(!txtDescuento.getText().equals(""))         
                    sql+= " porcentaje_descuento=  "+txtDescuento.getText()+", ";
           
           if(!txtRazon.getText().equals("") && !format.format(dateFirma.getDate()).equals(""))
                sql+= " razon_cancelacion =  '"+txtRazon.getText()+"', "
                    + " fecha_cancelacion ="+ " '"+format.format(dateFirma.getDate()) +"', ";
           
            sql+= " id_metodo_pago= (select id from aja_metodo_de_pago where tipo='" +cbMetodo.getSelectedItem().toString()+"'), "
                    + " id_productor_metodo_pago ="+ " (select prod.id from aja_productor prod where prod.nombre='"+cbProductor.getSelectedItem().toString()+"' ), "
                    +" tipo ='"+cbTransporte.getSelectedItem().toString().charAt(0)
                    + "' where id= "+id;
            int flag=0;
            /*ResultSet rs = stmt.executeQuery( "" );
            while ( rs.next() ) {
                if( true ){///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    JOptionPane.showMessageDialog(null, "El cultivo que intento registrar ya existe");
                    flag=1;
                    break;
                }
             } */
            if(flag==0){
                    stmt.executeUpdate(sql);
                    controllerLogin.conexion.commit();
                   JOptionPane.showMessageDialog(null,"Se modifico el registro con exito");
                    llenarContrato();
                    limpiar();
                }
        }catch (SQLException e) {
            e.printStackTrace();
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
        txtEstatus = new javax.swing.JTextField();
        dateFirma = new com.toedter.calendar.JDateChooser();
        btnInsert = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cbContrato = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        txtVarExp = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cbContratoEli = new javax.swing.JComboBox<>();
        btnDelete = new javax.swing.JButton();
        txtVarExpEli = new javax.swing.JLabel();
        txtProd1 = new javax.swing.JLabel();
        txtProd2 = new javax.swing.JLabel();
        txtProd3 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        dateCancela = new com.toedter.calendar.JDateChooser();
        txtProd4 = new javax.swing.JLabel();
        cbTransporte = new javax.swing.JComboBox<>();
        txtProd5 = new javax.swing.JLabel();
        txtProd6 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        txtProd7 = new javax.swing.JLabel();
        txtRazon = new javax.swing.JTextField();
        txtProd8 = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        txtProd9 = new javax.swing.JLabel();
        cbProductor = new javax.swing.JComboBox<>();
        txtProd10 = new javax.swing.JLabel();
        cbMetodo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 680, 120, 30));

        txtEstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstatusActionPerformed(evt);
            }
        });
        jPanel1.add(txtEstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 381, 283, 25));
        jPanel1.add(dateFirma, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 238, 283, 25));

        btnInsert.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsert.setForeground(new java.awt.Color(0, 0, 0));
        btnInsert.setText("Insertar");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });
        jPanel1.add(btnInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 120, 30));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setToolTipText("");

        cbContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbContratoActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");

        txtVarExp.setBackground(new java.awt.Color(0, 0, 0));
        txtVarExp.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtVarExp.setForeground(new java.awt.Color(255, 255, 255));
        txtVarExp.setText("Contrato entre:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtVarExp)
                        .addGap(0, 117, Short.MAX_VALUE))
                    .addComponent(cbContrato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(cbContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 21, -1, -1));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        cbContratoEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbContratoEliActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Eliminar");

        txtVarExpEli.setBackground(new java.awt.Color(0, 0, 0));
        txtVarExpEli.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtVarExpEli.setForeground(new java.awt.Color(255, 255, 255));
        txtVarExpEli.setText("Contrato entre:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtVarExpEli)
                        .addGap(0, 114, Short.MAX_VALUE))
                    .addComponent(cbContratoEli, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(cbContratoEli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 613, -1, -1));

        txtProd1.setBackground(new java.awt.Color(0, 0, 0));
        txtProd1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd1.setForeground(new java.awt.Color(255, 255, 255));
        txtProd1.setText("Fecha de firma:");
        jPanel1.add(txtProd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 208, -1, -1));

        txtProd2.setBackground(new java.awt.Color(0, 0, 0));
        txtProd2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd2.setForeground(new java.awt.Color(255, 255, 255));
        txtProd2.setText("Estatus:");
        jPanel1.add(txtProd2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 351, -1, -1));

        txtProd3.setBackground(new java.awt.Color(0, 0, 0));
        txtProd3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd3.setForeground(new java.awt.Color(255, 255, 255));
        txtProd3.setText("Precio del contrato ($):");
        jPanel1.add(txtProd3, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 421, -1, -1));

        txtPrecio.setPreferredSize(new java.awt.Dimension(64, 25));
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 451, 283, -1));
        jPanel1.add(dateCancela, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 451, 283, 25));

        txtProd4.setBackground(new java.awt.Color(0, 0, 0));
        txtProd4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd4.setForeground(new java.awt.Color(255, 255, 255));
        txtProd4.setText("Fecha cancelacion:");
        jPanel1.add(txtProd4, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 421, -1, -1));

        jPanel1.add(cbTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 308, 283, 25));

        txtProd5.setBackground(new java.awt.Color(0, 0, 0));
        txtProd5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd5.setForeground(new java.awt.Color(255, 255, 255));
        txtProd5.setText("Tipo de transporte:");
        jPanel1.add(txtProd5, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 278, -1, -1));

        txtProd6.setBackground(new java.awt.Color(0, 0, 0));
        txtProd6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd6.setForeground(new java.awt.Color(255, 255, 255));
        txtProd6.setText("Porcentaje de descuento:");
        jPanel1.add(txtProd6, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 211, -1, -1));
        jPanel1.add(txtDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 238, 283, 25));

        txtProd7.setBackground(new java.awt.Color(0, 0, 0));
        txtProd7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd7.setForeground(new java.awt.Color(255, 255, 255));
        txtProd7.setText("Razon de cancelacion:");
        jPanel1.add(txtProd7, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 278, -1, -1));
        jPanel1.add(txtRazon, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 308, 283, 98));

        txtProd8.setBackground(new java.awt.Color(0, 0, 0));
        txtProd8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd8.setForeground(new java.awt.Color(255, 255, 255));
        txtProd8.setText("Cliente:");
        jPanel1.add(txtProd8, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 135, -1, -1));

        jPanel1.add(cbCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 165, 283, 25));

        txtProd9.setBackground(new java.awt.Color(0, 0, 0));
        txtProd9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd9.setForeground(new java.awt.Color(255, 255, 255));
        txtProd9.setText("Productor:");
        jPanel1.add(txtProd9, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 135, -1, -1));

        cbProductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductorActionPerformed(evt);
            }
        });
        jPanel1.add(cbProductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 165, 283, 25));

        txtProd10.setBackground(new java.awt.Color(0, 0, 0));
        txtProd10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd10.setForeground(new java.awt.Color(255, 255, 255));
        txtProd10.setText("Metodo de pago:");
        jPanel1.add(txtProd10, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 488, -1, -1));

        jPanel1.add(cbMetodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 518, 283, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstatusActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInsertActionPerformed

    private void cbContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbContratoActionPerformed
        if ( cbContrato.getItemCount() > 1 ){
            if (!cbContrato.getSelectedItem().toString().equals(" ")){
                cbContratoEli.setEnabled(false);
                btnModificar.setEnabled(true);
                btnInsert.setEnabled(false);
                cortarCB(cbContrato );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(" select con.id, con.fecha_firma, con.tipo, con.estatus, con.precio_contrato, con.porcentaje_descuento, con.razon_cancelacion, con.fecha_cancelacion, cli.denominacion_comercial, prod.nombre "
                            + " from aja_cliente cli, aja_contrato con, aja_productor prod "
                            + " where cli.denominacion_comercial= '"+ part1.trim() +"' "
                            + " and prod.nombre = '"+ part2.trim() +"' ");
                        while( rs.next() ){
                            id = rs.getInt(1);
                            dateFirma.setDate(rs.getDate(2));
                            String  tipo= rs.getString(3);
                            String estatus = rs.getString(4);
                            String precio = rs.getString(5);
                            String descuento = rs.getString(6);
                            String cancela = rs.getString(7);
                            dateCancela.setDate(rs.getDate(8));
                            String cli = rs.getString(9);
                            String prod = rs.getString(10);
                            if(tipo.equals("A"))
                                cbTransporte.setSelectedItem("Aereo");
                            else if(tipo.equals("T"))    
                                cbTransporte.setSelectedItem("Terrestre");
                            else         
                                cbTransporte.setSelectedItem("Maritimo");
                            txtEstatus.setText(estatus);
                            txtPrecio.setText(precio);
                            txtDescuento.setText(descuento);
                            txtRazon.setText(cancela);
                            cbCliente.setSelectedItem(cli);
                            cbProductor.setSelectedItem(prod);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(false);
                btnInsert.setEnabled(true);
                cbContratoEli.setEnabled(true);
                limpiar();
            }
        }    
    }//GEN-LAST:event_cbContratoActionPerformed

    private void cbContratoEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbContratoEliActionPerformed
      if ( cbContrato.getItemCount() > 1 ){
            if (!cbContrato.getSelectedItem().toString().equals(" ")){
                cbContratoEli.setEnabled(false);
                btnModificar.setEnabled(true);
                btnInsert.setEnabled(false);
                cortarCB(cbContrato );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(" Select con.id "
                            + " from aja_cliente cli, aja_contrato con, aja_productor prod "
                            + " where cli.denominacion_comercial= '"+ part1.trim() +"' "
                            + " and prod.nombre = '"+ part2.trim() +"' ");
                        while( rs.next() ){
                            id = rs.getInt(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }
        }    
    }//GEN-LAST:event_cbContratoEliActionPerformed

    private void cbProductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductorActionPerformed
        if (cbProductor.getItemCount() > 1){
            if(!cbProductor.getSelectedItem().toString().equals(" ")){
                llenarMetodo();
            }else{
                
            }
        }
    }//GEN-LAST:event_cbProductorActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsert;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JComboBox<String> cbCliente;
    public javax.swing.JComboBox<String> cbContrato;
    public javax.swing.JComboBox<String> cbContratoEli;
    public javax.swing.JComboBox<String> cbMetodo;
    public javax.swing.JComboBox<String> cbProductor;
    public javax.swing.JComboBox<String> cbTransporte;
    public com.toedter.calendar.JDateChooser dateCancela;
    public com.toedter.calendar.JDateChooser dateFirma;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JTextField txtDescuento;
    public javax.swing.JTextField txtEstatus;
    public javax.swing.JTextField txtPrecio;
    private javax.swing.JLabel txtProd1;
    private javax.swing.JLabel txtProd10;
    private javax.swing.JLabel txtProd2;
    private javax.swing.JLabel txtProd3;
    private javax.swing.JLabel txtProd4;
    private javax.swing.JLabel txtProd5;
    private javax.swing.JLabel txtProd6;
    private javax.swing.JLabel txtProd7;
    private javax.swing.JLabel txtProd8;
    private javax.swing.JLabel txtProd9;
    public javax.swing.JTextField txtRazon;
    private javax.swing.JLabel txtVarExp;
    private javax.swing.JLabel txtVarExpEli;
    // End of variables declaration//GEN-END:variables
}
