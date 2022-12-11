/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.adopcion_cerezas;


import controlador.controllerLogin;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class ProgramaApadrinamiento extends javax.swing.JFrame {
    
    int id_productor;
    int id_padrino;
    int id_variedad;
    String part1;
    String part2;
    String part3;
    String part4;
    String part5;
    String part6;
    String part7;
    String part8;
    String part9;
    String part10;
    
    ResultSet rs;
    
    SimpleDateFormat format = new SimpleDateFormat("YYYY-MMM-dd");
    
    public ProgramaApadrinamiento() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }
    
    public static boolean validarNombre(String nombre){ //Metodo que valida el nombre
        return nombre.matches("^[a-zA-Z_]+( [a-zA-Z_]+)*$");
    }
    
    public static boolean validarNumero(String nombre){ //Metodo que valida el nombre
        return nombre.matches("[0-9]*");
    }
    
    public void llenarPadrino(){
        cbPadrino.removeAllItems();
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select primer_nombre || ' / ' || primer_apellido from aja_padrino" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbPadrino.addItem(registro);
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
     
      public void llenarVariedad(){
        cbVariedad.removeAllItems();
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select var.nombre || ' / ' || pais.nombre from aja_variedad_de_cerezas var, aja_pais pais "
                    + "where var.id_pais=pais.id" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbVariedad.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
    }
      
    public void llenarPrograma(){
        cbPrograma.removeAllItems();
        cbProgramaEli.removeAllItems();
        cbPrograma.addItem(" ");
        cbProgramaEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT 'Productor: ' || pro.nombre || ' / ' || 'Padrino: ' || pa.primer_nombre || ' : ' || pa.primer_apellido || ' / ' || 'Variedad: ' || var.nombre || ' / ' || pais.nombre as cbPrograma " +
            "from aja_productor pro, aja_padrino pa, aja_variedad_de_cerezas var, aja_pais pais, aja_programa_de_apadrinamiento progra " +
            "where progra.id_productor = pro.id and progra.id_padrino = pa.id and progra.id_variedad = var.id and pais.id = var.id_pais " );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbPrograma.addItem(registro);
            cbProgramaEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void limpiar(){
       cbPadrino.setSelectedIndex(0);
       cbProductor.setSelectedIndex(0);
       cbVariedad.setSelectedIndex(0);
       dcInicio.setDate(null);
       dcFin.setDate(null);
       txtDescripcion.setText(null);
       sfAporte.setValue(0);
       cbPrograma.setSelectedIndex(0);
       cbProgramaEli.setSelectedIndex(0);
    }
    
    public void cortarVA( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split("/");
        part7 = parts[0]; 
        part8 = parts[1];
    }
    
    public void cortarPA( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split("/");
        part9 = parts[0]; 
        part10 = parts[1];
    }
    
    public void cortarCBP( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split("/");
        part1 = parts[0].replaceAll("Productor: ", "");//Nombre del productor
        part2 = parts[1].replaceAll("Padrino: ", "");//Nombre y Apellido del Padrino pero con : en medio
        String[] partsTo = part2.split(":");
        part3 = partsTo[0];//Primer nombre del padrino
        part4 = partsTo[1];//Primer Apellido del padrino
        part5 = parts[2].replaceAll("Variedad: ", "");//Nombre de la variedad
        part6 = parts[3];//Nombre del pais
}
    
    
    public void inserts(){
        if(txtDescripcion.getText().equals("")){
            JOptionPane.showMessageDialog(null, "La descripcion no puede estar vacia");
        }else{
            cortarPA(cbPadrino);
            cortarVA(cbVariedad);
            
            try {
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_programa_de_apadrinamiento (id_padrino ,id_productor, id_variedad, fecha_inicio, descripcion, fecha_fin, aporte_anual) values ("
                    + "(select id from aja_padrino where primer_nombre= '"+part9.trim()+ "' and primer_apellido = '" + part10.trim() + "'  ),"
                    + "(select id from aja_productor where nombre='"+ cbProductor.getSelectedItem().toString() + "'),"
                    + "(select var.id from aja_variedad_de_cerezas var, aja_pais pa where var.nombre= '"+part7.trim() + "' and pa.nombre = '"+part8.trim() + "'  and var.id_pais = pa.id ),"
                    + " '"+format.format(dcInicio.getDate())+"', '" 
                    +       txtDescripcion.getText()+"', "
                    + " '"+format.format(dcInicio.getDate())+"'," 
                    + " "+ sfAporte.getValue()+");" ;
           
                        stmt.executeUpdate(sql);
                         controllerLogin.conexion.commit();
                         JOptionPane.showMessageDialog(null,"Se he registrado exitosamente");
                         llenarPrograma();
                         limpiar();

                     } catch (SQLException e) {
                           e.printStackTrace();
                   }   
        }

    }

    public void delete(){
        try{
                Statement stmt = controllerLogin.conexion.createStatement();
                String sql = "DELETE FROM aja_programa_de_apadrinamiento where "
                        + " id_padrino = " + id_padrino + " and id_productor = "+ id_productor + " and id_variedad ="+ id_variedad;

                
                
                 stmt.executeUpdate(sql);
                       controllerLogin.conexion.commit();
                       JOptionPane.showMessageDialog(null,"Se ha borrado el programa exitosamente");
                       llenarPrograma();
                       limpiar();

           }catch (SQLException e) {
               e.printStackTrace();

           }     
    }
    
   
    
    public void update(){
         if(txtDescripcion.getText().equals("")){
            JOptionPane.showMessageDialog(null, "La descripcion no puede estar vacia");
        }else{
             
            try{
                cortarPA(cbPadrino);
                 cortarVA(cbVariedad);
                Statement stmt = controllerLogin.conexion.createStatement();
                
                System.out.println(part9.trim());
               
               String sql = "update aja_programa_de_apadrinamiento ap set (id_padrino ,id_productor, id_variedad, fecha_inicio, descripcion, fecha_fin, aporte_anual) = ("
                    + "(select id from aja_padrino where primer_nombre= '"+part9.trim()+ "' and primer_apellido = '" + part10.trim() + "'  ),"
                    + "(select id from aja_productor where nombre='"+ cbProductor.getSelectedItem().toString() + "'),"
                    + "(select var.id from aja_variedad_de_cerezas var, aja_pais pa where var.nombre= '"+part7.trim() + "' and pa.nombre = '"+part8.trim() + "'  and var.id_pais = pa.id ),"
                    + " '"+format.format(dcInicio.getDate())+"', '" 
                    +       txtDescripcion.getText()+"', "
                    + " '"+format.format(dcFin.getDate())+"'," 
                    + " "+ sfAporte.getValue()+") "
                       + " where  ap.id_padrino = " + id_padrino + " and ap.id_productor = "+ id_productor + " and ap.id_variedad ="+ id_variedad ;
                
               System.out.println(id_padrino);
               System.out.println(id_productor);
                            System.out.println(id_variedad);
               
               
                stmt.executeUpdate(sql);
                controllerLogin.conexion.commit();
                JOptionPane.showMessageDialog(null,"El programa se cambio con exito");
                 llenarPrograma();
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
        cbPrograma = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        cbProgramaEli = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jNombreVar = new javax.swing.JLabel();
        jPrecocidad = new javax.swing.JLabel();
        jNombreVar1 = new javax.swing.JLabel();
        jPrecocidad1 = new javax.swing.JLabel();
        jNombreVar2 = new javax.swing.JLabel();
        cbPadrino = new javax.swing.JComboBox<>();
        cbProductor = new javax.swing.JComboBox<>();
        cbVariedad = new javax.swing.JComboBox<>();
        sfAporte = new com.toedter.components.JSpinField();
        jNombreVar3 = new javax.swing.JLabel();
        dcFin = new com.toedter.calendar.JDateChooser();
        dcInicio = new com.toedter.calendar.JDateChooser();
        jPrecocidad2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMINISTRACION DE VARIEDADES");

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 620, 120, 30));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, 120, 30));

        jPanel3.setBackground(new java.awt.Color(255, 51, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jVariedadPais1.setBackground(new java.awt.Color(0, 0, 0));
        jVariedadPais1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVariedadPais1.setForeground(new java.awt.Color(255, 255, 255));
        jVariedadPais1.setText("Programa de Apadrinamiento");
        jPanel3.add(jVariedadPais1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbPrograma.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        cbPrograma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cbPrograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProgramaActionPerformed(evt);
            }
        });
        jPanel3.add(cbPrograma, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 460, 30));

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        jPanel3.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 630, 90));

        jPanel2.setBackground(new java.awt.Color(255, 51, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Eliminar");
        btnDelete.setEnabled(false);
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 120, 30));

        cbProgramaEli.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        cbProgramaEli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbProgramaEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProgramaEliActionPerformed(evt);
            }
        });
        jPanel2.add(cbProgramaEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 470, 25));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Programa de Apadrinamiento");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 630, 90));

        jNombreVar.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar.setText("Aporte anual:");
        jPanel1.add(jNombreVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, 24));

        jPrecocidad.setBackground(new java.awt.Color(0, 0, 0));
        jPrecocidad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPrecocidad.setForeground(new java.awt.Color(255, 255, 255));
        jPrecocidad.setText("Descripcion del programa");
        jPanel1.add(jPrecocidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, -1, 24));

        jNombreVar1.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar1.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar1.setText("Nombre del padrino");
        jPanel1.add(jNombreVar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 24));

        jPrecocidad1.setBackground(new java.awt.Color(0, 0, 0));
        jPrecocidad1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPrecocidad1.setForeground(new java.awt.Color(255, 255, 255));
        jPrecocidad1.setText("Fecha inicio:");
        jPanel1.add(jPrecocidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, -1, 24));

        jNombreVar2.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar2.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar2.setText("Nombre del productor");
        jPanel1.add(jNombreVar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, 24));

        jPanel1.add(cbPadrino, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 260, -1));

        jPanel1.add(cbProductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 260, -1));

        jPanel1.add(cbVariedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 260, -1));
        jPanel1.add(sfAporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 110, -1));

        jNombreVar3.setBackground(new java.awt.Color(0, 0, 0));
        jNombreVar3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreVar3.setForeground(new java.awt.Color(255, 255, 255));
        jNombreVar3.setText("Nombre de la variedad:");
        jPanel1.add(jNombreVar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, 24));
        jPanel1.add(dcFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, 240, -1));
        jPanel1.add(dcInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 240, -1));

        jPrecocidad2.setBackground(new java.awt.Color(0, 0, 0));
        jPrecocidad2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPrecocidad2.setForeground(new java.awt.Color(255, 255, 255));
        jPrecocidad2.setText("Fecha fin:");
        jPanel1.add(jPrecocidad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, -1, 24));

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(null);
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 330, 290, 140));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbProgramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProgramaActionPerformed
        if ( cbPrograma.getItemCount() > 1 ){
            cbProgramaEli.setEnabled(false);
            
            if (!cbPrograma.getSelectedItem().toString().equals(" ")){
                btnModificar.setEnabled(true);
                btnInsertar.setEnabled(false);
              
                cortarCBP(cbPrograma );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    
                     ResultSet rs = stmt.executeQuery(" select padrino.primer_nombre, padrino.segundo_nombre, productor.nombre, variedad.nombre, ap.fecha_inicio,"
                            + " ap.descripcion, ap.fecha_fin, ap.aporte_anual, pais.nombre, padrino.id, productor.id, variedad.id "
                            + "from aja_programa_de_apadrinamiento ap, aja_padrino padrino, aja_productor productor, aja_variedad_de_cerezas variedad, aja_pais pais"
                            + " where ap.id_padrino= (select pa.id from aja_padrino pa where pa.primer_nombre = '"+ part3.trim() +"' "
                            + "and primer_apellido= '"+ part4.trim() +"' ) and padrino.id=ap.id_padrino"
                            + " and ap.id_productor = (select pr.id from aja_productor pr where pr.nombre = '"+ part1.trim() +"') and productor.id=ap.id_productor"
                            + " and ap.id_variedad = (select va.id from aja_variedad_de_cerezas va where va.nombre = '"+ part5.trim() +"') and variedad.id=ap.id_variedad "
                            + " and variedad.id_pais=pais.id");
                            
                     
                        while( rs.next() ){
                            
                            cbPadrino.setSelectedItem(rs.getString(1) + " / " + rs.getString(2));
                            cbProductor.setSelectedItem(rs.getString(3));
                            cbVariedad.setSelectedItem(rs.getString(4) + " / " + rs.getString(9));
                            dcInicio.setDate(rs.getDate(5));
                             txtDescripcion.setText(rs.getString(6));
                            dcFin.setDate(rs.getDate(7));
                            sfAporte.setValue(rs.getInt(8));
                            id_padrino = rs.getInt(10);
                            id_productor = rs.getInt(11);
                            id_variedad = rs.getInt(12);
                            
                            System.out.println(id_padrino);
                            System.out.println(id_productor);
                            System.out.println(id_variedad);
                            
                        }
                        
                        
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(true);
                cbProgramaEli.setEnabled(true);
                limpiar();
            }
        }    
    }//GEN-LAST:event_cbProgramaActionPerformed

    private void cbProgramaEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProgramaEliActionPerformed
        if ( cbProgramaEli.getItemCount() > 1 ){
                
                if (!cbProgramaEli.getSelectedItem().toString().equals(" ")){
                cbPrograma.setEnabled(false);
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                btnDelete.setEnabled(true);
                cortarCBP(cbProgramaEli );
                
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    
                   ResultSet rs = stmt.executeQuery(" select  padrino.id, productor.id, variedad.id "
                            + "from aja_programa_de_apadrinamiento ap, aja_padrino padrino, aja_productor productor, aja_variedad_de_cerezas variedad, aja_pais pais"
                            + " where ap.id_padrino= (select pa.id from aja_padrino pa where pa.primer_nombre = '"+ part3.trim() +"' "
                            + "and primer_apellido= '"+ part4.trim() +"' ) and padrino.id=ap.id_padrino"
                            + " and ap.id_productor = (select pr.id from aja_productor pr where pr.nombre = '"+ part1.trim() +"') and productor.id=ap.id_productor"
                            + " and ap.id_variedad = (select va.id from aja_variedad_de_cerezas va where va.nombre = '"+ part5.trim() +"') and variedad.id=ap.id_variedad "
                            + " and variedad.id_pais=pais.id");
                    
                        while( rs.next() ){
                            id_padrino = rs.getInt(1);
                            id_productor = rs.getInt(2);
                            id_variedad = rs.getInt(3);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnInsertar.setEnabled(true);
                cbPrograma.setSelectedIndex(0);
                btnDelete.setEnabled(false);
                cbPrograma.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_cbProgramaEliActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbPadrino;
    private javax.swing.JComboBox<String> cbProductor;
    private javax.swing.JComboBox<String> cbPrograma;
    private javax.swing.JComboBox<String> cbProgramaEli;
    private javax.swing.JComboBox<String> cbVariedad;
    private com.toedter.calendar.JDateChooser dcFin;
    private com.toedter.calendar.JDateChooser dcInicio;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jNombreVar;
    private javax.swing.JLabel jNombreVar1;
    private javax.swing.JLabel jNombreVar2;
    private javax.swing.JLabel jNombreVar3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jPrecocidad;
    private javax.swing.JLabel jPrecocidad1;
    private javax.swing.JLabel jPrecocidad2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jVariedadPais1;
    private com.toedter.components.JSpinField sfAporte;
    private javax.swing.JTextArea txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
