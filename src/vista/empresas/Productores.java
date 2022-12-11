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
    
    int id=0;
    int id_pais, id_ciudad, id_region, id_asoc, id_coop;
    String part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11;
    ResultSet rs;
    
    public Productores() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }
    
    public static boolean validarNombre(String nombre){ //Metodo que valida el nombre
        return nombre.matches("^[a-zA-Z_]+( [a-zA-Z_]+)*$");
    }

    public void llenarPais(){
        cbPais.removeAllItems();
        cbPais.addItem(" ");
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
        cbCiudad.removeAllItems();
        cbCiudad.addItem(" ");
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
        cbRegion.removeAllItems();
        cbRegion.addItem(" ");          
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
      
    public void llenarAsoc(){
        cbAsoc.removeAllItems();
        cbAsoc.addItem(" ");        
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select nombre from aja_asociacion_regional order by nombre asc" );
            while ( rs.next() ) {
                String nombre = rs.getString(1);
                cbAsoc.addItem(nombre);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void llenarCoop(){
        cbCoop.removeAllItems();
        cbCoop.addItem(" ");        
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select coop.nombre"
                    + " from aja_productor coop"
                    + " order by nombre asc" );
            while ( rs.next() ) {
            String nombre = rs.getString("nombre");
            cbCoop.addItem(nombre);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void cortarCB( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split("/ ");
        part1 = parts[0]; 
        part2 = parts[1];
        part3 = parts[2];
    }
        
    public void llenarProductor(){
        cbProductor.removeAllItems();
        cbProductorEli.removeAllItems();
        cbProductor.addItem(" ");
        cbProductorEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "Select prod.nombre ||'/ '|| ciu.nombre ||'/ '|| pais.nombre "
                    + "from aja_productor prod, aja_ciudad ciu, aja_pais pais "
                    + "where prod.id_ciudad= ciu.id and prod.id_pais_ciudad= ciu.id_pais and pais.id= ciu.id_pais");
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
        cbAsoc.setSelectedIndex(0);
        cbCoop.setSelectedIndex(0);
        txtDireccion.setText(null);
        txtNom.setText(null);
        txtPaginaWeb.setText(null);
    }
    
    public void inserts(){
            
            if(txtEnvase.getText().equals(" ")){
                JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            } else if ( validarNombre(txtEnvase.getText()) ==false){
                JOptionPane.showMessageDialog(null, "El campo nombre no puede tener numeros");
            }else if (part1.trim().equals(cbCoop.getSelectedItem().toString())){
                JOptionPane.showMessageDialog(null, "El productor no puede ser el mismo que la cooperativa");
            }else{
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    String sql = "INSERT INTO aja_productor "
                            + " (nombre, direccion, envase_estandar, id_ciudad, id_pais_ciudad, id_region, id_pais_region, pagina_web) VALUES  "
                            + " ( '"+txtNom.getText()+"', '"+txtDireccion.getText()+"', '"+txtEnvase.getText()+"',"
                            + " (select id from aja_ciudad where nombre='"+cbCiudad.getSelectedItem().toString()+"' ), "
                            + " (select id from aja_pais where nombre='"+cbPais.getSelectedItem().toString()+"' ), "
                            + "(select id from aja_region where nombre='"+cbRegion.getSelectedItem().toString()+"' ), "
                            + " (select id from aja_pais where nombre='"+cbPais.getSelectedItem().toString()+"' ), "
                            + "'"+txtPaginaWeb.getText()+"')" ;
                 
                    
                    int flag=0;
   /*ResultSet rs = stmt.executeQuery( "select var.nombre, pa.nombre from aja_variedad_de_cerezas var, aja_pais pa where var.id_pais = pa.id" );

                    while ( rs.next() ) {
                        if( rs.getString(1).equals(txtEnvase.getText()) && rs.getString(2).equals(cbPais.getSelectedItem().toString())){
                            JOptionPane.showMessageDialog(null, "La variedad que intento registrar ya existe");
                            flag=1;
                            break;
                        }
                    }//*/

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

                ResultSet rs = stmt.executeQuery( "select id from aja_productor" );

              int flag=0;

              while ( rs.next() ) {
                   if(rs.getInt("id") == id){
                       try {
                             stmt.executeUpdate(sql);
                             controllerLogin.conexion.commit();
                             JOptionPane.showMessageDialog(null,"Se ha borrado el registrado exitosamente");
                             llenarProductor();
                             limpiar();
                             break;
                       } catch (Exception e) {
                           JOptionPane.showMessageDialog(null,"No se puede borrar tiene trabajos con proveedores");
                       }
                       flag=1;
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
        cortarCB(cbProductor);
         if(txtEnvase.getText().equals(" ")){
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
        } else if ( validarNombre(txtEnvase.getText()) ==false){
            JOptionPane.showMessageDialog(null, "El campo nombre no puede tener numeros");
        }else if (part1.trim().equals(cbCoop.getSelectedItem().toString())){
                JOptionPane.showMessageDialog(null, "El productor no puede ser el mismo que la cooperativa");
        }else{
            try{
                Statement stmt = controllerLogin.conexion.createStatement();
                // sobreescribe datos pero sirve 
                String sql= "update aja_productor prod "
                            +  " SET nombre='"+txtNom.getText() +"' , "
                            + " direccion='"+txtDireccion.getText()+"' ,  "
                            + " envase_estandar='"+txtEnvase.getText()+"', "
                            + " id_ciudad="+ " (select id from aja_ciudad where nombre='"+cbCiudad.getSelectedItem().toString()+"' ), "
                            +"  id_pais_ciudad= "+ " (select id from aja_pais where nombre='"+cbPais.getSelectedItem().toString()+"' ), "
                            + " pagina_web= '"+txtPaginaWeb.getText()+"'  ";
                if (!cbRegion.getSelectedItem().equals(" "))
                    sql += " , id_region="+ "(select id from aja_region where nombre='"+cbRegion.getSelectedItem().toString()+"' ),  "
                            + " id_pais_region="+ " (select id from aja_pais where nombre='"+cbPais.getSelectedItem().toString()+"' )   ";
                if (!cbCoop.getSelectedItem().equals(" "))
                    sql+=", id_padre="+ " (select id from aja_prodcutror where nombre='"+cbCoop.getSelectedItem().toString()+"' )   ";
                sql+= " WHERE id="+id+" ";
                stmt.executeUpdate(sql);
                controllerLogin.conexion.commit();
               JOptionPane.showMessageDialog(null,"Se modifico el registro con exito");
                llenarProductor();
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
        txtEnvase = new javax.swing.JTextField();
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
        jEnvEst = new javax.swing.JLabel();
        cbCiudad = new javax.swing.JComboBox<>();
        jWeb = new javax.swing.JLabel();
        jCiudad = new javax.swing.JLabel();
        jCoop = new javax.swing.JLabel();
        jPais = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPaginaWeb = new javax.swing.JTextArea();
        jDireccion = new javax.swing.JLabel();
        jNombreVar1 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        cbCoop = new javax.swing.JComboBox<>();
        jRegion = new javax.swing.JLabel();
        jAsoc = new javax.swing.JLabel();
        cbAsoc = new javax.swing.JComboBox<>();

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

        jPanel1.add(cbPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 250, 25));

        jPanel1.add(cbRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 250, 25));

        txtDireccion.setColumns(20);
        txtDireccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDireccion.setLineWrap(true);
        txtDireccion.setRows(5);
        txtDireccion.setWrapStyleWord(true);
        txtDireccion.setBorder(null);
        jScrollPane1.setViewportView(txtDireccion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 290, 80));

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

        jEnvEst.setBackground(new java.awt.Color(0, 0, 0));
        jEnvEst.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jEnvEst.setForeground(new java.awt.Color(255, 255, 255));
        jEnvEst.setText("Envase estandar:");
        jPanel1.add(jEnvEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, 24));

        cbCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCiudadActionPerformed(evt);
            }
        });
        jPanel1.add(cbCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 250, 25));

        jWeb.setBackground(new java.awt.Color(0, 0, 0));
        jWeb.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jWeb.setForeground(new java.awt.Color(255, 255, 255));
        jWeb.setText("Pagina web del productor:");
        jPanel1.add(jWeb, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 400, -1, 24));

        jCiudad.setBackground(new java.awt.Color(0, 0, 0));
        jCiudad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCiudad.setForeground(new java.awt.Color(255, 255, 255));
        jCiudad.setText("Ciudad:");
        jPanel1.add(jCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, 24));

        jCoop.setBackground(new java.awt.Color(0, 0, 0));
        jCoop.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCoop.setForeground(new java.awt.Color(255, 255, 255));
        jCoop.setText("Cooperativa que pertenece:");
        jPanel1.add(jCoop, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, -1, 24));

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

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, 290, 40));

        jDireccion.setBackground(new java.awt.Color(0, 0, 0));
        jDireccion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jDireccion.setForeground(new java.awt.Color(255, 255, 255));
        jDireccion.setText("Direccion del productor:");
        jPanel1.add(jDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, 24));

        jNombreVar1.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar1.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar1.setText("Nombre del productor:");
        jPanel1.add(jNombreVar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 24));
        jPanel1.add(txtNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 255, -1));

        jPanel1.add(cbCoop, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 290, 25));

        jRegion.setBackground(new java.awt.Color(0, 0, 0));
        jRegion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jRegion.setForeground(new java.awt.Color(255, 255, 255));
        jRegion.setText("Region:");
        jPanel1.add(jRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, 24));

        jAsoc.setBackground(new java.awt.Color(0, 0, 0));
        jAsoc.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jAsoc.setForeground(new java.awt.Color(255, 255, 255));
        jAsoc.setText("Asociacion que pertenece:");
        jPanel1.add(jAsoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, -1, 24));

        cbAsoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbAsocItemStateChanged(evt);
            }
        });
        cbAsoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAsocActionPerformed(evt);
            }
        });
        jPanel1.add(cbAsoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 290, 25));

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
                    
                    ResultSet rs = stmt.executeQuery( "select productor.id, ciudad.id, pais.id, productor.nombre, productor.direccion, productor.envase_estandar, productor.pagina_web, "
                            + " ciudad.nombre, pais.nombre "
                            + " from aja_productor productor, aja_ciudad ciudad, aja_pais pais "
                            + " where pais.id=ciudad.id_pais and productor.id_pais_ciudad= ciudad.id_pais  and productor.id_ciudad= ciudad.id "
                            + " and productor.nombre = '"+ part1.trim() +"' "
                            + " and pais.nombre = '"+ part3.trim() +"' "
                            + "  and ciudad.nombre = '"+ part2.trim() +"' " );

                    while( rs.next() ){
                         id = rs.getInt(1);
                         id_ciudad= rs.getInt(2);
                         id_pais= rs.getInt(3);
                        String nombrepd = rs.getString(4);
                        String direccion = rs.getString(5);
                        String envase = rs.getString(6);
                        String pagina = rs.getString(7);
                        String pais = rs.getString(9);
                        String ciudad = rs.getString(8);
                        txtNom.setText(nombrepd);
                        cbCiudad.setSelectedItem(ciudad);
                        cbPais.setSelectedItem(pais);
                        txtEnvase.setText(envase);
                        txtDireccion.setText(direccion);
                        txtPaginaWeb.setText(pagina);
                    }
                    // luego de llenar los cuadros valido si es que tiene region 
                    ResultSet rs2 = stmt.executeQuery( "select  reg.id, reg.nombre, asoc.id, asoc.nombre "
                            + " from aja_productor productor, aja_ciudad ciudad, aja_pais pais, aja_region reg, "
                            + " aja_asociacion_regional asoc, aja_representacion rep "
                            + " where pais.id=ciudad.id_pais and productor.id_pais_ciudad= ciudad.id_pais  and productor.id_ciudad= ciudad.id "
                            + " and reg.id_pais=pais.id and productor.id_pais_region= reg.id_pais  and productor.id_region= reg.id  "
                            + " and asoc.id_region= reg.id and asoc.id_pais=reg.id_pais and productor.id=rep.id_productor and asoc.id=rep.id_asociacion "
                            + " and productor.id="+id);
                    // si no tiene region rs.next no tendra valor y este paso se saltara 
                    while( rs2.next() ){
                        id_region= rs2.getInt(1);
                        id_asoc= rs2.getInt(3);
                        String reg= rs2.getString(2);
                        String asoc= rs2.getString(4);
                        cbRegion.setSelectedItem(reg);
                        cbAsoc.setSelectedItem(asoc);
                    }
                    // cooperativa 
                    ResultSet rs3 = stmt.executeQuery( "select  coop.id, coop.nombre "
                            + " from aja_productor prod, aja_productor coop"
                            + " where prod.id_padre= coop.id "
                            + " and prod.id="+id);
                    // si no tiene region rs.next no tendra valor y este paso se saltara 
                    while( rs3.next() ){
                        id_coop= rs3.getInt(1);
                        String coop= rs3.getString(2);
                        cbCoop.setSelectedItem(coop);
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
                            + " pd.id_ciudad, pd.id_pais_ciudad, pd.id_region "
                            + " from aja_productor pd, aja_pais pais, aja_region region, aja_ciudad ciudad "
                            + " where pd.nombre = '"+ part1.trim() +"' "
                            + " and pais.nombre = '"+ part3.trim() +"' "
                            + "  and ciudad.id_pais=pais.id and pd.id_pais_ciudad=ciudad.id_pais and pd.id_ciudad=ciudad.id"
                            + "  and ciudad.nombre = '"+ part2.trim() +"' " );
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

    private void cbCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCiudadActionPerformed

    private void cbAsocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAsocActionPerformed
        // TODO add your handling code here:
        if (!cbAsoc.getSelectedItem().equals("")) {
            
            try {
                Statement stmt = controllerLogin.conexion.createStatement();
                ResultSet rs = stmt.executeQuery( "select reg.id , reg.nombre"
                        + "  from aja_region reg, aja_asociacion_regional asoc, aja_pais pais "
                        + " where reg.id = " + id_region+" " 
                        + " and reg.id=asoc.id_region and asoc.id_pais=reg.id_pais and reg.id_pais= pais.id  " );
                    while( rs.next() ){
                        id = rs.getInt(1);
                        String reg= rs.getString(2);
                        cbRegion.setSelectedItem(reg);
                    }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            cbRegion.setEnabled(false);
        }else cbRegion.setEnabled(true);
    }//GEN-LAST:event_cbAsocActionPerformed

    private void cbAsocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbAsocItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbAsocItemStateChanged

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JComboBox<String> cbAsoc;
    public javax.swing.JComboBox<String> cbCiudad;
    public javax.swing.JComboBox<String> cbCoop;
    public javax.swing.JComboBox<String> cbPais;
    private javax.swing.JComboBox<String> cbProductor;
    private javax.swing.JComboBox<String> cbProductorEli;
    public javax.swing.JComboBox<String> cbRegion;
    private javax.swing.JLabel jAsoc;
    private javax.swing.JLabel jCiudad;
    private javax.swing.JLabel jCoop;
    private javax.swing.JLabel jDireccion;
    private javax.swing.JLabel jEnvEst;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jNombreVar1;
    private javax.swing.JLabel jPais;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jRegion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jVariedadPais1;
    private javax.swing.JLabel jWeb;
    private javax.swing.JTextArea txtDireccion;
    public javax.swing.JTextField txtEnvase;
    public javax.swing.JTextField txtNom;
    private javax.swing.JTextArea txtPaginaWeb;
    // End of variables declaration//GEN-END:variables
}
