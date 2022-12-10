/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.produccion;

import controlador.controllerLogin;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VariedadCerezas extends javax.swing.JFrame {
    
    int id;
    String part1;
    String part2;
    ResultSet rs;
    
    public VariedadCerezas() {
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
    
    public void llenarVariedad(){
        cbVariedad.removeAllItems();
        cbVariedadEli.removeAllItems();
        cbVariedad.addItem(" ");
        cbVariedadEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select var.nombre || ' / ' || pais.nombre as texto from aja_variedad_de_cerezas var, aja_pais pais where var.id_pais = pais.id" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbVariedad.addItem(registro);
            cbVariedadEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void limpiar(){
        txtNom.setText("");
        txtDescripcion.setText("");
        cbPrecocidad.setSelectedIndex(0);
        cbEspecie.setSelectedIndex(0);
        cbPais.setSelectedIndex(0);
    }
    
    public void insert(){
        if(txtNom.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacio");
        } else if ( validarNombre(txtNom.getText()) ==false){
            JOptionPane.showMessageDialog(null, "El campo nombre no puede tener numeros");
        }else{
            try {
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_variedad_de_cerezas (id_pais,nombre,descripcion,especie,precocidad) values ((select id from aja_pais where nombre='"
                   + cbPais.getSelectedItem().toString() + "'),'"
                   + txtNom.getText() +"', '"
                   + txtDescripcion.getText() +"', '"
                   + cbEspecie.getSelectedItem().toString() +"', '"
                   + cbPrecocidad.getSelectedItem().toString()+"');" ;

                   ResultSet rs = stmt.executeQuery( "select var.nombre, pa.nombre from aja_variedad_de_cerezas var, aja_pais pa where var.id_pais = pa.id" );

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
                           JOptionPane.showMessageDialog(null,"Se ha registrado exitosamente");
                           llenarVariedad();
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
                String sql = "DELETE FROM aja_variedad_de_cerezas where  id = " + id;

                ResultSet rs = stmt.executeQuery( "select id from aja_variedad_de_cerezas" );

              int flag=0;

              while ( rs.next() ) {
                   if(rs.getInt("id") == id){
                       stmt.executeUpdate(sql);
                       controllerLogin.conexion.commit();
                       JOptionPane.showMessageDialog(null,"Se ha borrado el registrado exitosamente");
                       llenarVariedad();
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
         if(txtNom.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
        } else if ( validarNombre(txtNom.getText()) ==false){
            JOptionPane.showMessageDialog(null, "El campo nombre no puede tener numeros");
        }else{
            try{
                Statement stmt = controllerLogin.conexion.createStatement();
                String sql = "update aja_variedad_de_cerezas vdc " 
                        + "set (nombre,especie,precocidad,descripcion,id_pais)= " 
                        + "('"+ txtNom.getText() +"','"+ cbEspecie.getSelectedItem().toString() +"','"+ cbPrecocidad.getSelectedItem().toString() +"','"+ txtDescripcion.getText() +"'," 
                        + "(select pais.id from aja_pais pais where pais.nombre='"+ cbPais.getSelectedItem().toString() +"') ) "
                        + "where vdc.id = " + id;

                ResultSet rs = stmt.executeQuery( "select var.nombre, pa.nombre from aja_variedad_de_cerezas var, aja_pais pa where var.id_pais = pa.id" );

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
                              JOptionPane.showMessageDialog(null,"Se modifico el registro con exito");
                               llenarVariedad();
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
        cbPrecocidad = new javax.swing.JComboBox<>();
        cbPais = new javax.swing.JComboBox<>();
        cbEspecie = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jVariedadPais1 = new javax.swing.JLabel();
        cbVariedad = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        cbVariedadEli = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jNombreVar = new javax.swing.JLabel();
        jDescripcion = new javax.swing.JLabel();
        jPrecocidad = new javax.swing.JLabel();
        jEspecie = new javax.swing.JLabel();
        jPais = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMINISTRACION DE VARIEDADES");

        jPanel1.setBackground(new java.awt.Color(0, 102, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 550, 120, 30));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 120, 30));
        jPanel1.add(txtNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 255, -1));

        cbPrecocidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ETe", "Te", "ME", "Ta", "ETa" }));
        jPanel1.add(cbPrecocidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 255, 25));

        jPanel1.add(cbPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 255, 25));

        cbEspecie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PC", "PA", "CD", "Otro" }));
        jPanel1.add(cbEspecie, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 255, 25));

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(null);
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 290, 290));

        jPanel3.setBackground(new java.awt.Color(0, 153, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jVariedadPais1.setBackground(new java.awt.Color(0, 0, 0));
        jVariedadPais1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVariedadPais1.setForeground(new java.awt.Color(255, 255, 255));
        jVariedadPais1.setText("Variedad y Pais:");
        jPanel3.add(jVariedadPais1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbVariedad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbVariedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVariedadActionPerformed(evt);
            }
        });
        jPanel3.add(cbVariedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        jPanel3.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 410, 90));

        jPanel2.setBackground(new java.awt.Color(0, 153, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Eliminar");
        btnDelete.setEnabled(false);
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 120, 30));

        cbVariedadEli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbVariedadEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVariedadEliActionPerformed(evt);
            }
        });
        jPanel2.add(cbVariedadEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Variedad y Pais:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 410, 90));

        jNombreVar.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar.setText("Nombre de la Variedad:");
        jPanel1.add(jNombreVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 24));

        jDescripcion.setBackground(new java.awt.Color(0, 0, 0));
        jDescripcion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        jDescripcion.setText("Descripcion de la Variedad:");
        jPanel1.add(jDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, -1, 24));

        jPrecocidad.setBackground(new java.awt.Color(0, 0, 0));
        jPrecocidad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPrecocidad.setForeground(new java.awt.Color(255, 255, 255));
        jPrecocidad.setText("Precocidad:");
        jPanel1.add(jPrecocidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, 24));

        jEspecie.setBackground(new java.awt.Color(0, 0, 0));
        jEspecie.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jEspecie.setForeground(new java.awt.Color(255, 255, 255));
        jEspecie.setText("Especie:");
        jPanel1.add(jEspecie, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, 24));

        jPais.setBackground(new java.awt.Color(0, 0, 0));
        jPais.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPais.setForeground(new java.awt.Color(255, 255, 255));
        jPais.setText("Pais:");
        jPanel1.add(jPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, 24));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbVariedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVariedadActionPerformed
        if ( cbVariedad.getItemCount() > 1 ){
            if (!cbVariedad.getSelectedItem().toString().equals(" ")){
                cbVariedadEli.setEnabled(false);
                btnModificar.setEnabled(true);
                btnInsertar.setEnabled(false);
                cortarCB( cbVariedad );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery( "select var.id, var.nombre, var.especie, var.precocidad, var.descripcion, pais.nombre "
                            + "from aja_variedad_de_cerezas var, aja_pais pais "
                            + "where var.nombre = '"+ part1.trim() +"' "
                            + "and var.id_pais = (select pa.id from aja_pais pa where pa.nombre = '"+ part2.trim() +"') and pais.id=var.id_pais" );
                        while( rs.next() ){
                            id = rs.getInt(1);
                            String nombreVar = rs.getString(2);
                            String especie = rs.getString(3);
                            String precocidad = rs.getString(4);
                            String descripcion = rs.getString(5);
                            String paisNom = rs.getString(6);
                            txtNom.setText(nombreVar);
                            txtDescripcion.setText(descripcion);
                            cbPrecocidad.setSelectedItem(precocidad);
                            cbEspecie.setSelectedItem(especie);
                            cbPais.setSelectedItem(paisNom);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(true);
                cbVariedadEli.setEnabled(true);
                limpiar();
            }
        }    
    }//GEN-LAST:event_cbVariedadActionPerformed

    private void cbVariedadEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVariedadEliActionPerformed
        if ( cbVariedadEli.getItemCount() > 1 ){
                if (!cbVariedadEli.getSelectedItem().toString().equals(" ")){
                cbVariedad.setEnabled(false);
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                btnDelete.setEnabled(true);
                cortarCB( cbVariedadEli );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery( "select var.id, var.nombre, var.especie, var.precocidad, var.descripcion, pais.nombre "
                            + "from aja_variedad_de_cerezas var, aja_pais pais "
                            + "where var.nombre = '"+ part1.trim() +"' "
                            + "and var.id_pais = (select pa.id from aja_pais pa where pa.nombre = '"+ part2.trim() +"') and pais.id=var.id_pais" );
                        while( rs.next() ){
                            id = rs.getInt(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnInsertar.setEnabled(true);
                cbVariedad.setSelectedIndex(0);
                btnDelete.setEnabled(false);
                cbVariedad.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_cbVariedadEliActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JComboBox<String> cbEspecie;
    public javax.swing.JComboBox<String> cbPais;
    public javax.swing.JComboBox<String> cbPrecocidad;
    private javax.swing.JComboBox<String> cbVariedad;
    private javax.swing.JComboBox<String> cbVariedadEli;
    private javax.swing.JLabel jDescripcion;
    private javax.swing.JLabel jEspecie;
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
