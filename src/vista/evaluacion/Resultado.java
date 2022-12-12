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

public class Resultado extends javax.swing.JFrame {
    
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
    
    public Resultado() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }
    
    public static boolean validarNombre(String nombre){ //Metodo que valida el nombre
        return nombre.matches("^[a-zA-Z_]+( [a-zA-Z_]+)*$");
    }
    
      public void cortarCBP( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split("/");
        part1 = parts[0].replaceAll("Año# ", "");//Nombre del cliente
        part2 = parts[1].replaceAll("Cliente: ", "");
        part3 = parts[2].replaceAll("Productor: ", "");//nombre de la varieble
  
}

     public void llenarCliente(){
        cbCliente.removeAllItems();
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select denominacion_comercial from aja_cliente" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            
            cbCliente.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
    }
     
     public void llenarProductor(){
        cbProductor.removeAllItems();
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select nombre from aja_productor" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            
            cbProductor.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
    }
    
    public void llenarResultado(){
        cbResultado.removeAllItems();
        cbResultadoEli.removeAllItems();
        cbResultado.addItem(" ");
        cbResultadoEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT 'Año# ' || re.yyyy  || ' / ' ||  'Cliente: ' || ci.denominacion_comercial  || ' / ' ||  'Productor: ' || pr.nombre as cbFormula " +
            "from aja_resultado re ,aja_productor pr, aja_cliente ci " +
            "where re.id_cliente = ci.id and re.id_productor = pr.id"
                    + " order by ci.denominacion_comercial asc" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbResultado.addItem(registro);
            cbResultadoEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void limpiar(){
        cbCliente.setSelectedIndex(0);
        cbProductor.setSelectedIndex(0);
        spPorcentaje.setValue(0);
        cbResultado.setSelectedIndex(0);
        cbResultadoEli.setSelectedIndex(0);
    }
    
    public void inserts(){
        
       if(spPorcentaje.equals("0")){
            JOptionPane.showMessageDialog(null, "El campo porcentaje no puede tener valor 0");
        } else{
            try {
             
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_resultado (id_cliente, id_productor, yyyy, desicion, resultado_valor, resultado_porcentaje, fecha, observacion) values ("
                  + "(select id from aja_cliente where denominacion_comercial='"+ cbCliente.getSelectedItem().toString() + "'),"
                  + "(select id from aja_productor where nombre='"+ cbProductor.getSelectedItem().toString() + "'),'"
                  +  format.format(dcYear.getDate()) +"', '"
                  +  cbDecision.getSelectedItem().toString() +"', "
                   +  spValor.getValue()+", "
                   +  spPorcentaje.getValue()+", '"
                   +  format.format(dcFecha.getDate()) +"', '"
                  +  txtObservaciones.getText() +"')";
                   
                   stmt.executeUpdate(sql);
                    controllerLogin.conexion.commit();
                    JOptionPane.showMessageDialog(null,"Se ha registrado el resultado exitosamente");
                    llenarResultado();
                    limpiar();
                  
                   
                   
                     } catch (SQLException e) {
                           e.printStackTrace();
                   }   
        }

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
               String sql = "update aja_resultado set (id_cliente, id_productor,yyyy, desicion, resultado_valor, resultado_porcentaje, fecha, observacion) = ("
                  + "(select id from aja_cliente where denominacion_comercial='"+ cbCliente.getSelectedItem().toString() + "'),"
                  + "(select id from aja_productor where nombre='"+ cbProductor.getSelectedItem().toString() + "'),'"
                  +  format.format(dcYear.getDate()) +"', '"
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

        jPanel1 = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        btnInsertar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jVariedadPais1 = new javax.swing.JLabel();
        cbResultado = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        cbResultadoEli = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jNombreVar = new javax.swing.JLabel();
        jNombreVar1 = new javax.swing.JLabel();
        cbProductor = new javax.swing.JComboBox<>();
        cbCliente = new javax.swing.JComboBox<>();
        jNombreVar2 = new javax.swing.JLabel();
        jNombreVar3 = new javax.swing.JLabel();
        jNombreVar4 = new javax.swing.JLabel();
        jNombreVar5 = new javax.swing.JLabel();
        cbDecision = new javax.swing.JComboBox<>();
        spValor = new javax.swing.JSpinner();
        spPorcentaje = new javax.swing.JSpinner();
        jNombreVar6 = new javax.swing.JLabel();
        jNombreVar7 = new javax.swing.JLabel();
        dcYear = new com.toedter.calendar.JDateChooser();
        jNombreVar8 = new javax.swing.JLabel();
        dcFecha = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMINISTRACION DE VARIEDADES");

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 560, 120, 30));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 120, 30));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jVariedadPais1.setBackground(new java.awt.Color(0, 0, 0));
        jVariedadPais1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVariedadPais1.setForeground(new java.awt.Color(255, 255, 255));
        jVariedadPais1.setText("Resultado");
        jPanel3.add(jVariedadPais1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbResultado.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbResultado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbResultadoActionPerformed(evt);
            }
        });
        jPanel3.add(cbResultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 400, 25));

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

        cbResultadoEli.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbResultadoEli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbResultadoEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbResultadoEliActionPerformed(evt);
            }
        });
        jPanel2.add(cbResultadoEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 420, 25));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Resultado");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 590, 90));

        jNombreVar.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jNombreVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, -1, 24));

        jNombreVar1.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar1.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar1.setText("Observaciones:");
        jPanel1.add(jNombreVar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, -1, 24));

        jPanel1.add(cbProductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 250, 25));

        jPanel1.add(cbCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 250, 25));

        jNombreVar2.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar2.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar2.setText("Decision del resultado:");
        jPanel1.add(jNombreVar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, 24));

        jNombreVar3.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar3.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar3.setText("Nombre del Cliente:");
        jPanel1.add(jNombreVar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 24));

        jNombreVar4.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar4.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar4.setText("Año Resultado:");
        jPanel1.add(jNombreVar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, -1, 24));

        jNombreVar5.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar5.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar5.setText("Nombre del productor:");
        jPanel1.add(jNombreVar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, 24));

        cbDecision.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "renueva", "no renueva" }));
        jPanel1.add(cbDecision, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 250, 25));
        jPanel1.add(spValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 80, -1));
        jPanel1.add(spPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, 80, -1));

        jNombreVar6.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar6.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar6.setText("Resultado Valor:");
        jPanel1.add(jNombreVar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, -1, 24));

        jNombreVar7.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar7.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar7.setText("Fecha:");
        jPanel1.add(jNombreVar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, -1, 24));
        jPanel1.add(dcYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 130, -1));

        jNombreVar8.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar8.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar8.setText("Resultado Porcentaje:");
        jPanel1.add(jNombreVar8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, -1, 24));
        jPanel1.add(dcFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 280, 130, -1));

        txtObservaciones.setColumns(20);
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setRows(5);
        txtObservaciones.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtObservaciones);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 350, 290, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
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
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JComboBox<String> cbCliente;
    public javax.swing.JComboBox<String> cbDecision;
    public javax.swing.JComboBox<String> cbProductor;
    private javax.swing.JComboBox<String> cbResultado;
    private javax.swing.JComboBox<String> cbResultadoEli;
    private com.toedter.calendar.JDateChooser dcFecha;
    private com.toedter.calendar.JDateChooser dcYear;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jNombreVar;
    private javax.swing.JLabel jNombreVar1;
    private javax.swing.JLabel jNombreVar2;
    private javax.swing.JLabel jNombreVar3;
    private javax.swing.JLabel jNombreVar4;
    private javax.swing.JLabel jNombreVar5;
    private javax.swing.JLabel jNombreVar6;
    private javax.swing.JLabel jNombreVar7;
    private javax.swing.JLabel jNombreVar8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jVariedadPais1;
    private javax.swing.JSpinner spPorcentaje;
    private javax.swing.JSpinner spValor;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables
}
