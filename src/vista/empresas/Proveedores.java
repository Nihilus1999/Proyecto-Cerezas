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

public class Proveedores extends javax.swing.JFrame {
    
    int id;
    String part1;
    String part2;
    ResultSet rs;
    
    public Proveedores() {
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
    
    public void llenarProveedor(){
        cbProveedor.removeAllItems();
        cbProveedorEli.removeAllItems();
        cbProveedor.addItem(" ");
        cbProveedorEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select pr.nombre || ' / ' || pais.nombre as texto from aja_proveedor pr, aja_pais pais where pr.id_pais = pais.id" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbProveedor.addItem(registro);
            cbProveedorEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void limpiar(){
        txtNom.setText(null);
        cbRamo.setSelectedIndex(0);
        cbPais.setSelectedIndex(0);
        txtDescripcion.setText(null);
        cbProveedor.setSelectedIndex(0);
        cbProveedorEli.setSelectedIndex(0);
    }
    
    public void inserts(){
        if(txtNom.getText().equals("") || txtDescripcion.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Los campos no puede estar vacio");
        } else if ( validarNombre(txtNom.getText()) ==false){
            JOptionPane.showMessageDialog(null, "El campo nombre no puede tener numeros");
        }else{
            try {
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_proveedor (id_pais,nombre,ramo,descripcion_ramo) values ((select id from aja_pais where nombre='"
                   + cbPais.getSelectedItem().toString() + "'),'"
                   + txtNom.getText() +"', '"
                   + cbRamo.getSelectedItem().toString() +"','"
                   + txtDescripcion.getText() +"')";

                   ResultSet rs = stmt.executeQuery( "select pr.nombre, pa.nombre from aja_proveedor pr, aja_pais pa where pr.id_pais = pa.id" );

                   int flag=0;

                   while ( rs.next() ) {
                       if( rs.getString(1).equals(txtNom.getText()) && rs.getString(2).equals(cbPais.getSelectedItem().toString())){
                           JOptionPane.showMessageDialog(null, "La variedad que intento registrar ya existe");
                           flag=1;
                           break;
                       }
                    }

                   if(flag==0){
                           stmt.executeUpdate(sql);
                           controllerLogin.conexion.commit();
                           JOptionPane.showMessageDialog(null,"Se he registrado exitosamente");
                           llenarProveedor();
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
                String sql = "DELETE FROM aja_proveedor where  id = " + id;

                ResultSet rs = stmt.executeQuery( "select id from aja_proveedor" );

              int flag=0;

              while ( rs.next() ) {
                   if(rs.getInt("id") == id){
                       stmt.executeUpdate(sql);
                       controllerLogin.conexion.commit();
                       JOptionPane.showMessageDialog(null,"Se ha borrado el registrado exitosamente");
                       llenarProveedor();
                       limpiar();
                       flag = 1;
                       break;
                   }
                }

              if(flag == 0){
                  JOptionPane.showMessageDialog(null,"No se encontro el registro");
              }

           }catch (SQLException e) {
               e.printStackTrace();

           }     
    }
    
   
    
    public void update(){
         if(txtNom.getText().equals("")|| txtDescripcion.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Los campos no puede estar vacio");
        } else if ( validarNombre(txtNom.getText()) ==false){
            JOptionPane.showMessageDialog(null, "El campo nombre no puede tener numeros");
        }else{
            try{
                Statement stmt = controllerLogin.conexion.createStatement();
                String sql = "update aja_proveedor pr " 
                        + "set (nombre,ramo,descripcion_ramo,id_pais)= " 
                        + "('"+ txtNom.getText() +"','" + cbRamo.getSelectedItem().toString() +"','" + txtDescripcion.getText() +"'," 
                        + "(select pais.id from aja_pais pais where pais.nombre='"+ cbPais.getSelectedItem().toString() +"') ) "
                        + "where pr.id = " + id;

                ResultSet rs = stmt.executeQuery( "select pr.nombre, pa.nombre from aja_proveedor pr, aja_pais pa where pr.id_pais = pa.id" );

                       int flag=0;
                       
                       if(flag==0){
                               stmt.executeUpdate(sql);
                               controllerLogin.conexion.commit();
                              JOptionPane.showMessageDialog(null,"Se modifico el proveedor con exito");
                               llenarProveedor();
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
        txtNom = new javax.swing.JTextField();
        cbPais = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jVariedadPais1 = new javax.swing.JLabel();
        cbProveedor = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        cbProveedorEli = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jNombreVar = new javax.swing.JLabel();
        jDescripcion = new javax.swing.JLabel();
        jPrecocidad = new javax.swing.JLabel();
        jPais = new javax.swing.JLabel();
        cbRamo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMINISTRACION DE VARIEDADES");

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 440, 120, 30));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 120, 30));
        jPanel1.add(txtNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 255, -1));

        jPanel1.add(cbPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 255, 25));

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(null);
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 290, 170));

        jPanel3.setBackground(new java.awt.Color(51, 51, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jVariedadPais1.setBackground(new java.awt.Color(0, 0, 0));
        jVariedadPais1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVariedadPais1.setForeground(new java.awt.Color(255, 255, 255));
        jVariedadPais1.setText("Proveedores");
        jPanel3.add(jVariedadPais1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProveedorActionPerformed(evt);
            }
        });
        jPanel3.add(cbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 30));

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

        cbProveedorEli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbProveedorEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProveedorEliActionPerformed(evt);
            }
        });
        jPanel2.add(cbProveedorEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 30));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Proveedores");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 410, 90));

        jNombreVar.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar.setText("Nombre del Proveedor:");
        jPanel1.add(jNombreVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 24));

        jDescripcion.setBackground(new java.awt.Color(0, 0, 0));
        jDescripcion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        jDescripcion.setText("Descripcion del ramo:");
        jPanel1.add(jDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, -1, 24));

        jPrecocidad.setBackground(new java.awt.Color(0, 0, 0));
        jPrecocidad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPrecocidad.setForeground(new java.awt.Color(255, 255, 255));
        jPrecocidad.setText("Ramo:");
        jPanel1.add(jPrecocidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, 24));

        jPais.setBackground(new java.awt.Color(0, 0, 0));
        jPais.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPais.setForeground(new java.awt.Color(255, 255, 255));
        jPais.setText("Pais:");
        jPanel1.add(jPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 24));

        cbRamo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "fertilizante", "transporte", "tecnologia", "recolectores", "otros" }));
        jPanel1.add(cbRamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 255, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProveedorActionPerformed
        if ( cbProveedor.getItemCount() > 1 ){
            if (!cbProveedor.getSelectedItem().toString().equals(" ")){
                cbProveedorEli.setEnabled(false);
                btnModificar.setEnabled(true);
                btnInsertar.setEnabled(false);
                cortarCB(cbProveedor );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery( "select pr.id, pr.nombre, pr.ramo, pr.descripcion_ramo, pais.nombre "
                            + "from aja_proveedor pr, aja_pais pais "
                            + "where pr.nombre = '"+ part1.trim() +"' "
                            + "and pr.id_pais = (select pa.id from aja_pais pa where pa.nombre = '"+ part2.trim() +"') and pais.id=pr.id_pais" );
                        while( rs.next() ){
                            id = rs.getInt(1);
                            String nombrePro = rs.getString(2);
                            String ramo = rs.getString(3);
                            String  descripcion= rs.getString(4);
                            String paisNom = rs.getString(5);
                            txtNom.setText(nombrePro);
                            cbRamo.setSelectedItem(ramo);
                            txtDescripcion.setText(descripcion);
                            cbPais.setSelectedItem(paisNom);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(true);
                cbProveedorEli.setEnabled(true);
                limpiar();
            }
        }    
    }//GEN-LAST:event_cbProveedorActionPerformed

    private void cbProveedorEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProveedorEliActionPerformed
        if ( cbProveedorEli.getItemCount() > 1 ){
                if (!cbProveedorEli.getSelectedItem().toString().equals(" ")){
                cbProveedor.setEnabled(false);
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                btnDelete.setEnabled(true);
                cortarCB(cbProveedorEli );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery( "select pr.id, pr.nombre, pr.ramo, pr.descripcion_ramo, pais.nombre "
                            + "from aja_proveedor pr, aja_pais pais "
                            + "where pr.nombre = '"+ part1.trim() +"' "
                            + "and pr.id_pais = (select pa.id from aja_pais pa where pa.nombre = '"+ part2.trim() +"') and pais.id=pr.id_pais" );
                        while( rs.next() ){
                            id = rs.getInt(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnInsertar.setEnabled(true);
                cbProveedor.setSelectedIndex(0);
                btnDelete.setEnabled(false);
                cbProveedor.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_cbProveedorEliActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JComboBox<String> cbPais;
    private javax.swing.JComboBox<String> cbProveedor;
    private javax.swing.JComboBox<String> cbProveedorEli;
    public javax.swing.JComboBox<String> cbRamo;
    private javax.swing.JLabel jDescripcion;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jNombreVar;
    private javax.swing.JLabel jPais;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jPrecocidad;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jVariedadPais1;
    private javax.swing.JTextArea txtDescripcion;
    public javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
