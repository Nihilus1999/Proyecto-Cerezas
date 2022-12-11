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

public class Formula extends javax.swing.JFrame {
    
    int id;
    String part1;
    String part2;
    String part3;
    String part4;
    String part5;
    String part6;
    
    ResultSet rs;
    
    public Formula() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }
    
    public static boolean validarNombre(String nombre){ //Metodo que valida el nombre
        return nombre.matches("^[a-zA-Z_]+( [a-zA-Z_]+)*$");
    }
    
      public void cortarCBP( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split("/");
        part1 = parts[0].replaceAll("Cliente: ", "");//Nombre del cliente
        part2 = parts[1];//nombre del pais
        part3 = parts[2].replaceAll("Formula: ", "");//nombre de la varieble
             
}
    
    public void llenarVariable(){
      cbVariable.removeAllItems();
      try {
          Statement stmt = controllerLogin.conexion.createStatement();
          ResultSet rs = stmt.executeQuery( "select nombre from aja_variable" );
          while ( rs.next() ) {
          String registro = rs.getString(1);

          cbVariable.addItem(registro);
          }
      } catch (SQLException e) {
              e.printStackTrace();
      }

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
    
    public void llenarFormula(){
        cbFormula.removeAllItems();
        cbFormulaEli.removeAllItems();
        cbFormula.addItem(" ");
        cbFormulaEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT 'Cliente: ' || ci.denominacion_comercial || ' / ' || pais.nombre  || ' / ' ||  'Formula: ' || va.nombre as cbFormula " +
            "from aja_formula fo ,aja_variable va, aja_cliente ci, aja_pais pais " +
            "where fo.id_cliente = ci.id and fo.id_variable = va.id and ci.id_pais_ciudad=pais.id" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbFormula.addItem(registro);
            cbFormulaEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void limpiar(){
        cbCliente.setSelectedIndex(0);
        cbVariable.setSelectedIndex(0);
        cbTipo.setSelectedIndex(0);
        spPorcentaje.setValue(0);
        cbFormula.setSelectedIndex(0);
        cbFormulaEli.setSelectedIndex(0);
    }
    
    public void inserts(){
        
       if(spPorcentaje.equals("0")){
            JOptionPane.showMessageDialog(null, "El campo porcentaje no puede tener valor 0");
        } else{
            try {
             
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_formula (id_cliente, id_variable, porcentaje_importancia, tipo) values ("
                  + "(select id from aja_cliente where denominacion_comercial='"+ cbCliente.getSelectedItem().toString() + "'),"
                  + "(select id from aja_variable where nombre='"+ cbVariable.getSelectedItem().toString() + "'),"
                  +  spPorcentaje.getValue() +", '"
                  +  cbTipo.getSelectedItem().toString() +"')";

                   ResultSet rs = stmt.executeQuery( "select nombre from aja_variable" );
                   
                   stmt.executeUpdate(sql);
                    controllerLogin.conexion.commit();
                    JOptionPane.showMessageDialog(null,"Se ha registrado la formula exitosamente");
                    llenarVariable();
                    limpiar();
                  
                   
                   
                     } catch (SQLException e) {
                           e.printStackTrace();
                   }   
        }

    }

    public void delete(){
        
        try{
                Statement stmt = controllerLogin.conexion.createStatement();
                String sql = "DELETE FROM aja_formula where  id = " + id;

                stmt.executeUpdate(sql);
                controllerLogin.conexion.commit();
                JOptionPane.showMessageDialog(null,"Se ha borrado la variable exitosamente");
                llenarFormula();
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
               String sql = "update aja_formula set (id_cliente, id_variable, porcentaje_importancia, tipo) = ("
                  + "(select id from aja_cliente where denominacion_comercial='"+ cbCliente.getSelectedItem().toString() + "'),"
                  + "(select id from aja_variable where nombre='"+ cbVariable.getSelectedItem().toString() + "'),"
                  +  spPorcentaje.getValue() +", '"
                  +  cbTipo.getSelectedItem().toString() +"')"
                  + " where id  =" + id;
                
                    stmt.executeUpdate(sql);
                    controllerLogin.conexion.commit();
                   JOptionPane.showMessageDialog(null,"Se modifico la formula con exito");
                    llenarVariable();
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
        jPanel3 = new javax.swing.JPanel();
        jVariedadPais1 = new javax.swing.JLabel();
        cbFormula = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        cbFormulaEli = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jNombreVar = new javax.swing.JLabel();
        jNombreVar1 = new javax.swing.JLabel();
        cbVariable = new javax.swing.JComboBox<>();
        cbCliente = new javax.swing.JComboBox<>();
        jNombreVar2 = new javax.swing.JLabel();
        jNombreVar3 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        spPorcentaje = new javax.swing.JSpinner();
        jNombreVar4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMINISTRACION DE VARIEDADES");

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 470, 120, 30));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 120, 30));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jVariedadPais1.setBackground(new java.awt.Color(0, 0, 0));
        jVariedadPais1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVariedadPais1.setForeground(new java.awt.Color(255, 255, 255));
        jVariedadPais1.setText("Formula");
        jPanel3.add(jVariedadPais1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbFormula.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbFormula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFormulaActionPerformed(evt);
            }
        });
        jPanel3.add(cbFormula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 400, 25));

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

        cbFormulaEli.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        cbFormulaEli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbFormulaEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFormulaEliActionPerformed(evt);
            }
        });
        jPanel2.add(cbFormulaEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 420, 25));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Formula");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 590, 90));

        jNombreVar.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jNombreVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, -1, 24));

        jNombreVar1.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar1.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar1.setText("Porcentaje de aceptacion");
        jPanel1.add(jNombreVar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, 24));

        jPanel1.add(cbVariable, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 250, 25));

        jPanel1.add(cbCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 250, 25));

        jNombreVar2.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar2.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar2.setText("Nombre de la Variable:");
        jPanel1.add(jNombreVar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, 24));

        jNombreVar3.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar3.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar3.setText("Nombre del Cliente:");
        jPanel1.add(jNombreVar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 24));

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "dulce", "acida" }));
        jPanel1.add(cbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 250, 25));
        jPanel1.add(spPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 130, 30));

        jNombreVar4.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar4.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar4.setText("Tipo de Sabor:");
        jPanel1.add(jNombreVar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, 24));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFormulaActionPerformed
        if ( cbFormula.getItemCount() > 1 ){
            cbFormulaEli.setEnabled(false);
            if (!cbFormula.getSelectedItem().toString().equals(" ")){
               
                cortarCBP(cbFormula);
                btnModificar.setEnabled(true);
                btnInsertar.setEnabled(false);
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    
                   ResultSet rs = stmt.executeQuery("select fo.id, fo.tipo, fo.porcentaje_importancia, ci.denominacion_comercial, var.nombre "
                            + "from aja_formula fo, aja_cliente ci, aja_variable var, aja_pais pais"
                            + " where fo.id_cliente= (select ci.id from aja_cliente ci where ci.denominacion_comercial = '"+ part1.trim() +"')  and ci.id=fo.id_cliente"
                            + " and fo.id_variable = (select var.id from aja_variable var where var.nombre = '"+ part3.trim() +"') and var.id = fo.id_variable"
                            + " and ci.id_pais_ciudad=pais.id");
                   
                        while( rs.next() ){
                            
                            id = rs.getInt(1);
                            cbTipo.setSelectedItem(rs.getString(2));
                            spPorcentaje.setValue(rs.getInt(3));
                            cbCliente.setSelectedItem(rs.getString(4));
                            cbVariable.setSelectedItem(rs.getString(5));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(true);
                cbFormulaEli.setEnabled(true);
                limpiar();
            }
        }    
    }//GEN-LAST:event_cbFormulaActionPerformed

    private void cbFormulaEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFormulaEliActionPerformed
        if ( cbFormulaEli.getItemCount() > 1 ){
                cbFormula.setEnabled(false);
                if (!cbFormulaEli.getSelectedItem().toString().equals(" ")){
                    
                cortarCBP(cbFormulaEli);
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                btnDelete.setEnabled(true);
                try {
                    
                    Statement stmt = controllerLogin.conexion.createStatement();
                    
                    ResultSet rs = stmt.executeQuery("select fo.id, fo.tipo, fo.porcentaje_importancia, ci.denominacion_comercial, var.nombre "
                            + "from aja_formula fo, aja_cliente ci, aja_variable var, aja_pais pais"
                            + " where fo.id_cliente= (select ci.id from aja_cliente ci where ci.denominacion_comercial = '"+ part1.trim() +"')  and ci.id=fo.id_cliente"
                            + " and fo.id_variable = (select var.id from aja_variable var where var.nombre = '"+ part3.trim() +"') and var.id = fo.id_variable"
                            + " and ci.id_pais_ciudad=pais.id");
                   
                        while( rs.next() ){
                            id = rs.getInt(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnInsertar.setEnabled(true);
                cbFormula.setSelectedIndex(0);
                btnDelete.setEnabled(false);
                cbFormula.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_cbFormulaEliActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JComboBox<String> cbCliente;
    private javax.swing.JComboBox<String> cbFormula;
    private javax.swing.JComboBox<String> cbFormulaEli;
    public javax.swing.JComboBox<String> cbTipo;
    public javax.swing.JComboBox<String> cbVariable;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jNombreVar;
    private javax.swing.JLabel jNombreVar1;
    private javax.swing.JLabel jNombreVar2;
    private javax.swing.JLabel jNombreVar3;
    private javax.swing.JLabel jNombreVar4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jVariedadPais1;
    private javax.swing.JSpinner spPorcentaje;
    // End of variables declaration//GEN-END:variables
}
