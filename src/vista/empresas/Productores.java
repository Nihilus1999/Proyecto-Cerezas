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

public class Productores extends javax.swing.JFrame {
    
    int id;
    int id_pais;
    int id_ciudad;
    int id_region;
    String part1;
    String part2;
    String part3;
    String part4;
    ResultSet rs;
    
    public Productores() {
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
    
     public void llenarCiudad(){
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select nombre from aja_ciudad order by nombre asc" );
            while ( rs.next() ) {
            String nombre = rs.getString("nombre");
            cbCiudad.addItem(nombre);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
     
      public void llenarRegion(){
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select nombre from aja_region order by nombre asc" );
            while ( rs.next() ) {
            String nombre = rs.getString("nombre");
            cbRegion.addItem(nombre);
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
        part4 = parts[3];
    }
    
    public void llenarProductor(){
        cbProductor.removeAllItems();
        cbProductorEli.removeAllItems();
        cbProductor.addItem(" ");
        cbProductorEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select pd.nombre || ' / ' || pais.nombre  || ' / ' || region.nombre  || ' / ' || ciudad.nombre" +
"                    as texto from aja_productor pd, aja_pais pais, aja_region region, aja_ciudad ciudad" +
"                    where pd.id_pais_region = pais.id and pd.id_region = region.id and pd.id_ciudad=ciudad.id" +
"                    order by texto asc" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbProductor.addItem(registro);
            cbProductorEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void limpiar(){
        txtEnvase.setText(null);
        cbCiudad.setSelectedIndex(0);
        cbRegion.setSelectedIndex(0);
        cbPais.setSelectedIndex(0);
        cbProductor.setSelectedIndex(0);
        cbProductorEli.setSelectedIndex(0);
    }
    
    public void inserts(){
        if(txtEnvase.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
        } else if ( validarNombre(txtEnvase.getText()) ==false){
            JOptionPane.showMessageDialog(null, "El campo nombre no puede tener numeros");
        }else{
            try {
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_variedad_de_cerezas (id_pais,nombre,descripcion,especie,precocidad) values ((select id from aja_pais where nombre='"
                   + cbPais.getSelectedItem().toString() + "'),'"
                   + txtEnvase.getText() +"', '"
                   + txtDireccion.getText() +"', '"
                   + cbRegion.getSelectedItem().toString() +"', '"
                   + cbCiudad.getSelectedItem().toString()+"');" ;

                   ResultSet rs = stmt.executeQuery( "select var.nombre, pa.nombre from aja_variedad_de_cerezas var, aja_pais pa where var.id_pais = pa.id" );

                   int flag=0;

                   while ( rs.next() ) {
                       if( rs.getString(1).equals(txtEnvase.getText()) && rs.getString(2).equals(cbPais.getSelectedItem().toString())){
                           JOptionPane.showMessageDialog(null, "La variedad que intento registrar ya existe");
                           flag=1;
                           break;
                       }
                    }

                   if(flag==0){
                           stmt.executeUpdate(sql);
                           controllerLogin.conexion.commit();
                           JOptionPane.showMessageDialog(null,"Se he registrado exitosamente");
                           llenarProductor();
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
                String sql = "DELETE FROM aja_productor where  id = " + id;

                ResultSet rs = stmt.executeQuery( "select id from aja_variedad_de_cerezas" );

              int flag=0;

              while ( rs.next() ) {
                   if(rs.getInt("id") == id){
                       stmt.executeUpdate(sql);
                       controllerLogin.conexion.commit();
                       JOptionPane.showMessageDialog(null,"Se ha borrado el registrado exitosamente");
                       llenarProductor();
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
         if(txtEnvase.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
        } else if ( validarNombre(txtEnvase.getText()) ==false){
            JOptionPane.showMessageDialog(null, "El campo nombre no puede tener numeros");
        }else{
            try{
                Statement stmt = controllerLogin.conexion.createStatement();
                String sql = "update aja_variedad_de_cerezas vdc " 
                        + "set (nombre,especie,precocidad,descripcion,id_pais)= " 
                        + "('"+ txtEnvase.getText() +"','"+ cbRegion.getSelectedItem().toString() +"','"+ cbCiudad.getSelectedItem().toString() +"','"+ txtDireccion.getText() +"'," 
                        + "(select pais.id from aja_pais pais where pais.nombre='"+ cbPais.getSelectedItem().toString() +"') ) "
                        + "where vdc.id = " + id;

                ResultSet rs = stmt.executeQuery( "select var.nombre, pa.nombre from aja_variedad_de_cerezas var, aja_pais pa where var.id_pais = pa.id" );

                       int flag=0;

                       while ( rs.next() ) {
                           if( rs.getString(1).equals(txtEnvase.getText()) && rs.getString(2).equals(cbPais.getSelectedItem().toString())){
                               JOptionPane.showMessageDialog(null, "La variedad que intento registrar ya existe");
                               flag=1;
                               break;
                           }
                        }

                       if(flag==0){
                               stmt.executeUpdate(sql);
                               controllerLogin.conexion.commit();
                              JOptionPane.showMessageDialog(null,"Se modifico el registro con exito");
                               llenarProductor();
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
        txtEnvase = new javax.swing.JTextField();
        cbCiudad = new javax.swing.JComboBox<>();
        cbPais = new javax.swing.JComboBox<>();
        cbRegion = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jVariedadPais1 = new javax.swing.JLabel();
        cbProductor = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        cbProductorEli = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jNombreVar = new javax.swing.JLabel();
        jDescripcion = new javax.swing.JLabel();
        jPrecocidad = new javax.swing.JLabel();
        jEspecie = new javax.swing.JLabel();
        jPais = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPaginaWeb = new javax.swing.JTextArea();
        jDescripcion1 = new javax.swing.JLabel();
        jNombreVar1 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMINISTRACION DE VARIEDADES");

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 570, 120, 30));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, 120, 30));
        jPanel1.add(txtEnvase, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 255, -1));

        jPanel1.add(cbCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 255, 25));

        jPanel1.add(cbPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 255, 25));

        jPanel1.add(cbRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 255, 25));

        txtDireccion.setColumns(20);
        txtDireccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDireccion.setLineWrap(true);
        txtDireccion.setRows(5);
        txtDireccion.setWrapStyleWord(true);
        txtDireccion.setBorder(null);
        jScrollPane1.setViewportView(txtDireccion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 290, 150));

        jPanel3.setBackground(new java.awt.Color(51, 51, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jVariedadPais1.setBackground(new java.awt.Color(0, 0, 0));
        jVariedadPais1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVariedadPais1.setForeground(new java.awt.Color(255, 255, 255));
        jVariedadPais1.setText("Productores:");
        jPanel3.add(jVariedadPais1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbProductor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbProductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductorActionPerformed(evt);
            }
        });
        jPanel3.add(cbProductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        jPanel3.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 410, 90));

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Eliminar");
        btnDelete.setEnabled(false);
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 120, 30));

        cbProductorEli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbProductorEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductorEliActionPerformed(evt);
            }
        });
        jPanel2.add(cbProductorEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Productores");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 410, 90));

        jNombreVar.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar.setText("Envase estandar:");
        jPanel1.add(jNombreVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, 24));

        jDescripcion.setBackground(new java.awt.Color(0, 0, 0));
        jDescripcion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        jDescripcion.setText("Pagina web del productor:");
        jPanel1.add(jDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, -1, 24));

        jPrecocidad.setBackground(new java.awt.Color(0, 0, 0));
        jPrecocidad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPrecocidad.setForeground(new java.awt.Color(255, 255, 255));
        jPrecocidad.setText("Ciudad:");
        jPanel1.add(jPrecocidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, 24));

        jEspecie.setBackground(new java.awt.Color(0, 0, 0));
        jEspecie.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jEspecie.setForeground(new java.awt.Color(255, 255, 255));
        jEspecie.setText("Region:");
        jPanel1.add(jEspecie, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, 24));

        jPais.setBackground(new java.awt.Color(0, 0, 0));
        jPais.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPais.setForeground(new java.awt.Color(255, 255, 255));
        jPais.setText("Pais:");
        jPanel1.add(jPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, 24));

        txtPaginaWeb.setColumns(20);
        txtPaginaWeb.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPaginaWeb.setLineWrap(true);
        txtPaginaWeb.setRows(5);
        txtPaginaWeb.setWrapStyleWord(true);
        txtPaginaWeb.setBorder(null);
        jScrollPane2.setViewportView(txtPaginaWeb);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 290, 80));

        jDescripcion1.setBackground(new java.awt.Color(0, 0, 0));
        jDescripcion1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jDescripcion1.setForeground(new java.awt.Color(255, 255, 255));
        jDescripcion1.setText("Direccion del productor:");
        jPanel1.add(jDescripcion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, -1, 24));

        jNombreVar1.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar1.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar1.setText("Nombre del productor:");
        jPanel1.add(jNombreVar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 24));
        jPanel1.add(txtNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 255, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbProductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductorActionPerformed
        if ( cbProductor.getItemCount() > 1 ){
            if (!cbProductor.getSelectedItem().toString().equals(" ")){
                cortarCB(cbProductor );
                cbProductorEli.setEnabled(false);
                btnModificar.setEnabled(true);
                btnInsertar.setEnabled(false);
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    
                    ResultSet rs = stmt.executeQuery( "select productor.id, ciudad.id, region.id, pais.id, productor.nombre, productor.direccion, productor.envase_estandar, productor.pagina_web, "
                            + "ciudad.nombre, region.nombre, pais.nombre from aja_productor productor, aja_ciudad ciudad, aja_region region, aja_pais pais "
                            + "where pais.id= region.id_pais or region is null"
                            + "and  pais.id=ciudad.id_pais and productor.id_pais_region=region.id_pais and productor.id_pais_ciudad= ciudad.id_pais and region.id=productor.id_region and productor.id_ciudad= ciudad.id");
                    

                    
                        while( rs.next() ){
                            
                             id = rs.getInt(1);
                            String nombrepd = rs.getString(2);
                            String direccion = rs.getString(3);
                            String envase = rs.getString(4);
                            String pagina = rs.getString(5);
                            String pais = rs.getString(6);
                            String region = rs.getString(7);
                            String ciudad = rs.getString(8);
                            
                            System.out.println(nombrepd);
                            
                            txtNom.setText(nombrepd);
                            cbCiudad.setSelectedItem(ciudad);
                            cbRegion.setSelectedItem(region);
                            cbPais.setSelectedItem(pais);
                            txtEnvase.setText(envase);
                            txtDireccion.setText(direccion);
                            txtPaginaWeb.setText(pagina);
                            
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(true);
                cbProductorEli.setEnabled(true);
                limpiar();
            }
        }    
    }//GEN-LAST:event_cbProductorActionPerformed

    private void cbProductorEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductorEliActionPerformed
        if ( cbProductorEli.getItemCount() > 1 ){
                if (!cbProductorEli.getSelectedItem().toString().equals(" ")){
                cbProductor.setEnabled(false);
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                btnDelete.setEnabled(true);
                cortarCB(cbProductorEli );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery( "select pd.id, pd.nombre, pd.direccion, pd.envase_estandar,pd.pagina_web,"
                            + " pd.id_ciudad, pd.id_pais_ciudad, pd.region "
                            + "from aja_productor pd, aja_pais pais, aja_region region, aja_ciudad ciudad "
                            + "where pd.nombre = '"+ part1.trim() +"' "
                            + "and pd.id_pais = (select pa.id from aja_pais pa where pa.nombre = '"+ part2.trim() +"') "
                            + "and pais.id=var.id_pais" );
                        while( rs.next() ){
                            id = rs.getInt(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnInsertar.setEnabled(true);
                cbProductor.setSelectedIndex(0);
                btnDelete.setEnabled(false);
                cbProductor.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_cbProductorEliActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JComboBox<String> cbCiudad;
    public javax.swing.JComboBox<String> cbPais;
    private javax.swing.JComboBox<String> cbProductor;
    private javax.swing.JComboBox<String> cbProductorEli;
    public javax.swing.JComboBox<String> cbRegion;
    private javax.swing.JLabel jDescripcion;
    private javax.swing.JLabel jDescripcion1;
    private javax.swing.JLabel jEspecie;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jNombreVar;
    private javax.swing.JLabel jNombreVar1;
    private javax.swing.JLabel jPais;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jPrecocidad;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jVariedadPais1;
    private javax.swing.JTextArea txtDireccion;
    public javax.swing.JTextField txtEnvase;
    public javax.swing.JTextField txtNom;
    private javax.swing.JTextArea txtPaginaWeb;
    // End of variables declaration//GEN-END:variables
}
