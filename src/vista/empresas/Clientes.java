/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.empresas;

import vista.produccion.*;
import controlador.controllerLogin;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Clientes extends javax.swing.JFrame {
    
    int id;
    String part1;
    String part2;
    String part3;
    ResultSet rs;
    
    public Clientes() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }
    
    public static boolean validarNombre(String nombre){ //Metodo que valida el nombre
        return nombre.matches("^[a-zA-Z_]+( [a-zA-Z_]+)*$");
    }
    
     public static boolean validarNumero(String nombre){ //Metodo que valida el nombre
        return nombre.matches("[0-9]*");
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
    
    public void llenarCiudad(){
         try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select nombre from aja_ciudad" );
            while ( rs.next() ) {
            String nombre = rs.getString("nombre");
            cbCiudad.addItem(nombre);
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
        part3 = parts[2];
    }
    
    public void llenarClientes(){
        cbClientes.removeAllItems();
        cbClienteEli.removeAllItems();
        cbClientes.addItem(" ");
        cbClienteEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select ci.denominacion_comercial || ' / ' || pais.nombre || ' / ' || ciudad.nombre as texto "
                    + "from aja_cliente ci, aja_pais pais, aja_ciudad ciudad where ci.id_pais_ciudad = pais.id and ci.id_ciudad = ciudad.id" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbClientes.addItem(registro);
            cbClienteEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void limpiar(){
        txtNom.setText(null);
        cbCiudad.setSelectedIndex(0);
        cbPais.setSelectedIndex(0);
        txtMision.setText(null);
        txtInferior.setText(null);
        txtSuperior.setText(null);
        txtAceptacion.setText(null);
        cbClientes.setSelectedIndex(0);
        cbClienteEli.setSelectedIndex(0);
    }
    
    public void inserts(){
        if(txtNom.getText().equals("") || txtMision.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Los campos no puede estar vacio");
        } else if ( validarNombre(txtNom.getText()) ==false){
            JOptionPane.showMessageDialog(null, "El campo nombre no puede tener numeros");
        }else if(validarNumero(txtInferior.getText())==false || validarNumero(txtSuperior.getText())==false || validarNumero(txtAceptacion.getText())==false ){
            JOptionPane.showMessageDialog(null, "Los campos rango inferior, superior y porcentaje de aceptacion solo pueden tener numero");
        }else{
            try {
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_cliente (id_pais_ciudad, id_ciudad, denominacion_comercial, mision, rango_inferior,"
                   + " rango_superior, porcentaje_aceptacion) values ("
                   + "(select id from aja_pais where nombre='" + cbPais.getSelectedItem().toString() + "'),"
                   + "(select id from aja_ciudad where nombre='" + cbCiudad.getSelectedItem().toString() + "'),'"
                   + txtNom.getText()+"', '"
                   + txtMision.getText() +"', '"
                   + Integer.parseInt(txtInferior.getText())+"', '"
                   + Integer.parseInt(txtSuperior.getText()) +"', '"
                   + Integer.parseInt(txtAceptacion.getText()) +"')";

                   ResultSet rs = stmt.executeQuery( "select ci.denominacion_comercial, pa.nombre from aja_cliente ci, aja_pais pa where ci.id_pais_ciudad = pa.id" );

                   int flag=0;

                   while ( rs.next() ) {
                       if( rs.getString(1).equals(txtNom.getText()) && rs.getString(2).equals(cbPais.getSelectedItem().toString())){
                           JOptionPane.showMessageDialog(null, "El cliente que intento registrar ya existe");
                           flag=1;
                           break;
                       }
                    }

                   if(flag==0){
                           stmt.executeUpdate(sql);
                           controllerLogin.conexion.commit();
                           JOptionPane.showMessageDialog(null,"Se ha registrado exitosamente");
                           llenarClientes();
                           limpiar();
                       }

                     } catch (SQLException e) {
                         e.printStackTrace();
                           JOptionPane.showMessageDialog(null, "La ciudad no concuerda con el pais indicado");
                   }   
        }

    }

    public void delete(){
        try{
                Statement stmt = controllerLogin.conexion.createStatement();
                String sql = "DELETE FROM aja_cliente where  id = " + id;

                ResultSet rs = stmt.executeQuery( "select id from aja_cliente" );

              int flag=0;

              while ( rs.next() ) {
                   if(rs.getInt("id") == id){
                       stmt.executeUpdate(sql);
                       controllerLogin.conexion.commit();
                       JOptionPane.showMessageDialog(null,"Se ha borrado el cliente exitosamente");
                       llenarClientes();
                       limpiar();
                       flag = 1;
                       break;
                   }
                }

              if(flag == 0){
                  JOptionPane.showMessageDialog(null,"No se encontro el cliente");
              }

           }catch (SQLException e) {
               e.printStackTrace();

           }     
    }
    
   
    
    public void update(){
         if(txtNom.getText().equals("")|| txtMision.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Los campos no puede estar vacio");
        } else if ( validarNombre(txtNom.getText()) ==false){
            JOptionPane.showMessageDialog(null, "El campo nombre no puede tener numeros");
        }else if(validarNumero(txtInferior.getText())==false || validarNumero(txtSuperior.getText())==false || validarNumero(txtAceptacion.getText())==false ){
            JOptionPane.showMessageDialog(null, "Los campos rango inferior, superior y porcentaje de aceptacion solo pueden tener numero");
        }else{
            try{
                Statement stmt = controllerLogin.conexion.createStatement();
                
                String sql = "update aja_cliente ci set (id_pais_ciudad,id_ciudad,denominacion_comercial, mision,"
                        + " rango_inferior, rango_superior, porcentaje_aceptacion) = ("
                   + "(select id from aja_pais where nombre='" + cbPais.getSelectedItem().toString() + "'),"
                   + "(select id from aja_ciudad where nombre='" + cbCiudad.getSelectedItem().toString() + "'),'"
                   + txtNom.getText() +"', '"
                   + txtMision.getText() +"', '"
                   + Integer.parseInt(txtInferior.getText()) +"', '"
                   + Integer.parseInt(txtSuperior.getText()) +"', '"
                   + Integer.parseInt(txtAceptacion.getText()) +"') "
                   + " where ci.id = " + id;
                

                ResultSet rs = stmt.executeQuery( "select ci.denominacion_comercial, pa.nombre from aja_cliente ci, aja_pais pa where ci.id_pais_ciudad = pa.id" );

                       int flag=0;
                       
                       if(flag==0){
                               stmt.executeUpdate(sql);
                               controllerLogin.conexion.commit();
                              JOptionPane.showMessageDialog(null,"Se modifico el cliente con exito");
                               llenarClientes();
                               limpiar();
                           }

            }catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "La ciudad no concuerda con el pais indicado");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMision = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jVariedadPais1 = new javax.swing.JLabel();
        cbClientes = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        cbClienteEli = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jNombreVar = new javax.swing.JLabel();
        jDescripcion = new javax.swing.JLabel();
        jPrecocidad = new javax.swing.JLabel();
        jPais = new javax.swing.JLabel();
        cbCiudad = new javax.swing.JComboBox<>();
        jPais1 = new javax.swing.JLabel();
        txtAceptacion = new javax.swing.JTextField();
        jPais2 = new javax.swing.JLabel();
        txtInferior = new javax.swing.JTextField();
        jPais3 = new javax.swing.JLabel();
        txtSuperior = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMINISTRACION DE VARIEDADES");

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 540, 120, 30));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 410, 120, 30));
        jPanel1.add(txtNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 255, -1));

        jPanel1.add(cbPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 255, 25));

        txtMision.setColumns(20);
        txtMision.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtMision.setLineWrap(true);
        txtMision.setRows(5);
        txtMision.setWrapStyleWord(true);
        txtMision.setBorder(null);
        jScrollPane1.setViewportView(txtMision);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 290, 170));

        jPanel3.setBackground(new java.awt.Color(51, 51, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jVariedadPais1.setBackground(new java.awt.Color(0, 0, 0));
        jVariedadPais1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVariedadPais1.setForeground(new java.awt.Color(255, 255, 255));
        jVariedadPais1.setText("Clientes");
        jPanel3.add(jVariedadPais1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClientesActionPerformed(evt);
            }
        });
        jPanel3.add(cbClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 30));

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        jPanel3.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 410, 90));

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Eliminar");
        btnDelete.setEnabled(false);
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 120, 30));

        cbClienteEli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbClienteEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClienteEliActionPerformed(evt);
            }
        });
        jPanel2.add(cbClienteEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 30));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Clientes");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 410, 90));

        jNombreVar.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar.setText("Denominacion Comercial");
        jPanel1.add(jNombreVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 24));

        jDescripcion.setBackground(new java.awt.Color(0, 0, 0));
        jDescripcion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        jDescripcion.setText("Mision del cliente");
        jPanel1.add(jDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, -1, 24));

        jPrecocidad.setBackground(new java.awt.Color(0, 0, 0));
        jPrecocidad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPrecocidad.setForeground(new java.awt.Color(255, 255, 255));
        jPrecocidad.setText("Ciudad:");
        jPanel1.add(jPrecocidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, 24));

        jPais.setBackground(new java.awt.Color(0, 0, 0));
        jPais.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPais.setForeground(new java.awt.Color(255, 255, 255));
        jPais.setText("Porcentaje de aceptacion");
        jPanel1.add(jPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, -1, 24));

        jPanel1.add(cbCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 255, 25));

        jPais1.setBackground(new java.awt.Color(0, 0, 0));
        jPais1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPais1.setForeground(new java.awt.Color(255, 255, 255));
        jPais1.setText("Pais:");
        jPanel1.add(jPais1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 24));
        jPanel1.add(txtAceptacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 120, -1));

        jPais2.setBackground(new java.awt.Color(0, 0, 0));
        jPais2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPais2.setForeground(new java.awt.Color(255, 255, 255));
        jPais2.setText("Rango inferior");
        jPanel1.add(jPais2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, 24));
        jPanel1.add(txtInferior, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 120, -1));

        jPais3.setBackground(new java.awt.Color(0, 0, 0));
        jPais3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPais3.setForeground(new java.awt.Color(255, 255, 255));
        jPais3.setText("Rango superior");
        jPanel1.add(jPais3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, -1, 24));
        jPanel1.add(txtSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 120, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClientesActionPerformed
        if ( cbClientes.getItemCount() > 1 ){
            if (!cbClientes.getSelectedItem().toString().equals(" ")){
                cbClienteEli.setEnabled(false);
                btnModificar.setEnabled(true);
                btnInsertar.setEnabled(false);
                cortarCB(cbClientes );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(" select ci.id, ci.denominacion_comercial, ci.mision, ci.rango_inferior,"
                            + " ci.rango_superior, ci.porcentaje_aceptacion, pais.nombre, ciudad.nombre "
                            + "from aja_cliente ci, aja_pais pais, aja_ciudad ciudad "
                            + "where ci.denominacion_comercial = '"+ part1.trim() +"' "
                            + " and ci.id_pais_ciudad = (select pa.id from aja_pais pa where pa.nombre = '"+ part2.trim() +"') and pais.id=ci.id_pais_ciudad"
                            + " and ci.id_ciudad = (select ciu.id from aja_ciudad ciu where ciu.nombre = '"+ part3.trim() +"') and ciudad.id=ci.id_ciudad ");
                    
                        while( rs.next() ){
                            id = rs.getInt(1);
                            String nombreCI = rs.getString(2);
                            String  mision= rs.getString(3);
                            String inferior = rs.getString(4);
                            String superior = rs.getString(5);
                            String aceptacion = rs.getString(6);
                            String nombrePais = rs.getString(7);
                            String nombreRegion = rs.getString(8);
                            txtNom.setText(nombreCI);
                            txtMision.setText(mision);
                            txtInferior.setText(inferior);
                            txtSuperior.setText(superior);
                            txtAceptacion.setText(aceptacion);
                            cbPais.setSelectedItem(nombrePais);
                            cbCiudad.setSelectedItem(nombreRegion);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(true);
                cbClienteEli.setEnabled(true);
                limpiar();
            }
        }    
    }//GEN-LAST:event_cbClientesActionPerformed

    private void cbClienteEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClienteEliActionPerformed
        if ( cbClienteEli.getItemCount() > 1 ){
                if (!cbClienteEli.getSelectedItem().toString().equals(" ")){
                cbClientes.setEnabled(false);
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                btnDelete.setEnabled(true);
                cortarCB(cbClienteEli );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(" select ci.id, ci.denominacion_comercial, ci.mision, ci.rango_inferior,"
                            + " ci.rango_superior, ci.porcentaje_aceptacion, pais.nombre, ciudad.nombre "
                            + "from aja_cliente ci, aja_pais pais, aja_ciudad ciudad "
                            + "where ci.denominacion_comercial = '"+ part1.trim() +"' "
                            + " and ci.id_pais_ciudad = (select pa.id from aja_pais pa where pa.nombre = '"+ part2.trim() +"') and pais.id=ci.id_pais_ciudad"
                            + " and ci.id_ciudad = (select ciu.id from aja_ciudad ciu where ciu.nombre = '"+ part3.trim() +"') and ciudad.id=ci.id_ciudad ");
                        while( rs.next() ){
                            id = rs.getInt(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnInsertar.setEnabled(true);
                cbClientes.setSelectedIndex(0);
                btnDelete.setEnabled(false);
                cbClientes.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_cbClienteEliActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JComboBox<String> cbCiudad;
    private javax.swing.JComboBox<String> cbClienteEli;
    private javax.swing.JComboBox<String> cbClientes;
    public javax.swing.JComboBox<String> cbPais;
    private javax.swing.JLabel jDescripcion;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jNombreVar;
    private javax.swing.JLabel jPais;
    private javax.swing.JLabel jPais1;
    private javax.swing.JLabel jPais2;
    private javax.swing.JLabel jPais3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jPrecocidad;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jVariedadPais1;
    public javax.swing.JTextField txtAceptacion;
    public javax.swing.JTextField txtInferior;
    private javax.swing.JTextArea txtMision;
    public javax.swing.JTextField txtNom;
    public javax.swing.JTextField txtSuperior;
    // End of variables declaration//GEN-END:variables
}
