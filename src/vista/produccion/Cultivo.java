/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.produccion;

import controlador.controllerLogin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author jose-
 */
public class Cultivo extends javax.swing.JFrame {

    int id_Productor;
    int id_Variedad;
    int id_Cultivo;
    
    String part1;
    String part2;
    String part3;
    String part4;
    String part5;
    String part6;
    
    SimpleDateFormat format = new SimpleDateFormat("YYYY-MMM-dd");
    
    public Cultivo() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }
    
    public void insert(){
        try {
           cortarCBV(cbVariedad);
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_cultivo( " 
                   + "id_productor, id_variedad, produccion_anual_estimada, periodo_inicio, periodo_fin, maximo_exportar, calibre) " 
                   + "VALUES ((select prod.id " 
                   + "from aja_productor prod " 
                   + "where prod.nombre = '"+cbProductor.getSelectedItem().toString()+"'), " 
                   + "(select var.id " 
                   + "from aja_variedad_de_cerezas var, aja_pais pais " 
                   + "where var.nombre= '"+part1.trim()+"' and pais.nombre = '"+part2.trim()+"' and var.id_pais = pais.id )," 
                   + " "+spiProduccionA.getValue()+"," 
                   + " '"+format.format(datePeriodoIni.getDate())+"', " 
                   + " '"+format.format(datePeriodoFin.getDate())+"'," 
                   + " "+spiExport.getValue()+", " 
                   + " "+sliCalibre.getValue()+");" ;
           
                   int flag=0;
/*
                   ResultSet rs = stmt.executeQuery( "" );
                   while ( rs.next() ) {
                       if( true ){///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                           JOptionPane.showMessageDialog(null, "El cultivo que intento registrar ya existe");
                           flag=1;
                           break;
                       }
                    }
           */
                   if(flag==0){
                           stmt.executeUpdate(sql);
                           controllerLogin.conexion.commit();
                           JOptionPane.showMessageDialog(null,"Se ha registrado exitosamente");
                           llenarProductorVar();
                           limpiar();
                       }

                     } catch (SQLException e) {
                           e.printStackTrace();
                   }
    }
    
    public void delete(){
        try{
            Statement stmt = controllerLogin.conexion.createStatement();
            String sql = "delete from aja_cultivo where id =" + id_Cultivo;
            
            ResultSet rs = stmt.executeQuery( "select id from aja_cultivo" );
            int flag=0;
            while ( rs.next() ) {
                 if( rs.getInt(1) == id_Cultivo ){/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                     stmt.executeUpdate(sql);
                     controllerLogin.conexion.commit();
                     JOptionPane.showMessageDialog(null,"Se ha borrado el registrado exitosamente");
                     llenarProductorVar();
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
        try{
            Statement stmt = controllerLogin.conexion.createStatement();
            String sql = "Update aja_cultivo " 
                    + "Set (periodo_inicio, periodo_fin, produccion_anual_estimada, maximo_exportar, id_productor, id_variedad, calibre)=( '"+ format.format(datePeriodoIni.getDate()) +"',"
                    + " '"+ format.format(datePeriodoFin.getDate()) +"',"
                    + " "+ spiProduccionA.getValue() +","
                    + " "+ spiExport.getValue() +","
                    + " "+ id_Productor +","
                    + " "+ id_Variedad +","
                    + " "+ sliCalibre.getValue() +")" 
                    + "Where id = "+ id_Cultivo;
            
            int flag=0;
            /*
            ResultSet rs = stmt.executeQuery( "" );
            while ( rs.next() ) {
                if( true ){///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    JOptionPane.showMessageDialog(null, "El cultivo que intento registrar ya existe");
                    flag=1;
                    break;
                }
             }
            */
            if(flag==0){
                    stmt.executeUpdate(sql);
                    controllerLogin.conexion.commit();
                   JOptionPane.showMessageDialog(null,"Se modifico el registro con exito");
                    llenarProductorVar();
                    limpiar();
                }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void limpiar(){
        cbProductor.setSelectedIndex(0);
        cbVariedad.setSelectedIndex(0);
        sliCalibre.setValue(0);
        spiExport.setValue(0);
        spiProduccionA.setValue(0);
        datePeriodoIni.setDate(null);
        datePeriodoFin.setDate(null);
    }
    
    public void llenarProductorVar(){
        cbProductorVar.removeAllItems();
        cbProductorVarEli.removeAllItems();
        cbProductorVar.addItem(" ");
        cbProductorVarEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select 'Cultivo #' || cul.id || ' / ' ||pro.nombre || ' / ' ||  var.nombre || ' / ' || pais.nombre as texto from aja_cultivo cul, aja_productor pro, aja_variedad_de_cerezas var, aja_pais pais where (cul.id_productor = pro.id) and (cul.id_variedad = var.id) and (var.id_pais = pais.id) order by pro.nombre" );
            while(rs.next()){
                String registro = rs.getString(1);
                cbProductorVar.addItem(registro);
                cbProductorVarEli.addItem(registro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cultivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void llenarCBs(){
        try {
            cbProductor.addItem(" ");
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select nombre from aja_productor" );
            while(rs.next()){
                String registro = rs.getString(1);
                cbProductor.addItem(registro);
            }
            
            cbVariedad.addItem(" ");
            rs = stmt.executeQuery( "select var.nombre || ' / ' || pais.nombre as texto from aja_variedad_de_cerezas var, aja_pais pais where var.id_pais = pais.id" );
            while(rs.next()){
                String registro = rs.getString(1);
                cbVariedad.addItem(registro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cultivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cortarCBV( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split("/");
        part1 = parts[0]; 
        part2 = parts[1];
    }
    
    public void cortarCBPVP( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split("/");
        part3 = parts[0].replaceAll("Cultivo #", "");
        part4 = parts[1];
        part5 = parts[2];
        part6 = parts[3];
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
        jPanel2 = new javax.swing.JPanel();
        jProductorVariedad = new javax.swing.JLabel();
        cbProductorVar = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        jProductor = new javax.swing.JLabel();
        cbProductor = new javax.swing.JComboBox<>();
        jVariedad = new javax.swing.JLabel();
        cbVariedad = new javax.swing.JComboBox<>();
        jPeriodoIni = new javax.swing.JLabel();
        datePeriodoIni = new com.toedter.calendar.JDateChooser();
        jPeriodoFin = new javax.swing.JLabel();
        datePeriodoFin = new com.toedter.calendar.JDateChooser();
        jCalibre = new javax.swing.JLabel();
        sliCalibre = new javax.swing.JSlider();
        jPanel3 = new javax.swing.JPanel();
        jCalibreM = new javax.swing.JLabel();
        jProduccionA = new javax.swing.JLabel();
        spiProduccionA = new javax.swing.JSpinner();
        jCantidadM = new javax.swing.JLabel();
        spiExport = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        jProductorVarEli = new javax.swing.JLabel();
        cbProductorVarEli = new javax.swing.JComboBox<>();
        btnEliminar = new javax.swing.JButton();
        btnInsertar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jProductorVariedad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jProductorVariedad.setForeground(new java.awt.Color(255, 255, 255));
        jProductorVariedad.setText("Productor y Variedad:");
        jPanel2.add(jProductorVariedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, -1));

        cbProductorVar.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        cbProductorVar.setForeground(new java.awt.Color(0, 0, 0));
        cbProductorVar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductorVarActionPerformed(evt);
            }
        });
        jPanel2.add(cbProductorVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 350, 25));

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        jPanel2.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 120, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 500, 90));

        jProductor.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jProductor.setForeground(new java.awt.Color(255, 255, 255));
        jProductor.setText("Productor:");
        jPanel1.add(jProductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 260, -1));

        cbProductor.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(cbProductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 255, 25));

        jVariedad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVariedad.setForeground(new java.awt.Color(255, 255, 255));
        jVariedad.setText("Variedad:");
        jPanel1.add(jVariedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        cbVariedad.setForeground(new java.awt.Color(0, 0, 0));
        cbVariedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVariedadActionPerformed(evt);
            }
        });
        jPanel1.add(cbVariedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 255, 25));

        jPeriodoIni.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPeriodoIni.setForeground(new java.awt.Color(255, 255, 255));
        jPeriodoIni.setText("Periodo inicial disponible:");
        jPanel1.add(jPeriodoIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));
        jPanel1.add(datePeriodoIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 255, 25));

        jPeriodoFin.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPeriodoFin.setForeground(new java.awt.Color(255, 255, 255));
        jPeriodoFin.setText("Periodo final disponible:");
        jPanel1.add(jPeriodoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));
        jPanel1.add(datePeriodoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 255, 25));

        jCalibre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCalibre.setForeground(new java.awt.Color(255, 255, 255));
        jCalibre.setText("Calibre");
        jPanel1.add(jCalibre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, -1, -1));

        sliCalibre.setMajorTickSpacing(10);
        sliCalibre.setMaximum(40);
        sliCalibre.setMinorTickSpacing(1);
        sliCalibre.setPaintLabels(true);
        sliCalibre.setPaintTicks(true);
        sliCalibre.setSnapToTicks(true);
        sliCalibre.setValue(0);
        sliCalibre.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliCalibreStateChanged(evt);
            }
        });
        jPanel1.add(sliCalibre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 255, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jCalibreM.setBackground(new java.awt.Color(255, 255, 255));
        jCalibreM.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jCalibreM.setForeground(new java.awt.Color(0, 0, 0));
        jCalibreM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCalibreM.setText("-- mm");
        jCalibreM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jCalibreM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCalibreM, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 540, 50, 30));

        jProduccionA.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jProduccionA.setForeground(new java.awt.Color(255, 255, 255));
        jProduccionA.setText("Produccion anual estimada:");
        jPanel1.add(jProduccionA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));

        spiProduccionA.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 100.0d));
        jPanel1.add(spiProduccionA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 128, 25));

        jCantidadM.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCantidadM.setForeground(new java.awt.Color(255, 255, 255));
        jCantidadM.setText("Cantidad maxima a exportar:");
        jPanel1.add(jCantidadM, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, -1, -1));

        spiExport.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 100.0d));
        jPanel1.add(spiExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 128, 25));

        jPanel4.setBackground(new java.awt.Color(0, 153, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jProductorVarEli.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jProductorVarEli.setForeground(new java.awt.Color(255, 255, 255));
        jProductorVarEli.setText("Productor y Variedad");
        jPanel4.add(jProductorVarEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 17, -1, -1));

        cbProductorVarEli.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        cbProductorVarEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductorVarEliActionPerformed(evt);
            }
        });
        jPanel4.add(cbProductorVarEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 350, 25));

        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setText("Eliminar");
        jPanel4.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 120, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 500, 90));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 580, 120, 30));

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 730, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sliCalibreStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliCalibreStateChanged
        jCalibreM.setText(sliCalibre.getValue() + " mm");
    }//GEN-LAST:event_sliCalibreStateChanged

    private void cbVariedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVariedadActionPerformed
        if (cbVariedad.getItemCount() > 1){
            if(!cbVariedad.getSelectedItem().toString().equals(" ")){
                cortarCBV( cbVariedad );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery( "select var.id "
                            + "from aja_variedad_de_cerezas var, aja_pais pais "
                            + "where var.nombre = '"+ part1.trim() +"' "
                            + "and var.id_pais = (select pa.id from aja_pais pa where pa.nombre = '"+ part2.trim() +"') and pais.id=var.id_pais" );
                        while( rs.next() ){
                            id_Variedad = rs.getInt(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_cbVariedadActionPerformed

    private void cbProductorVarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductorVarActionPerformed
        if (cbProductorVar.getItemCount() > 1){
            if(!cbProductorVar.getSelectedItem().toString().equals(" ")){
                cbProductorVarEli.setEnabled(false);
                btnModificar.setEnabled(true);
                btnInsertar.setEnabled(false);
                btnEliminar.setEnabled(false);
                cortarCBPVP( cbProductorVar );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery( "Select cul.id as id_cultivo, var.id as id_variedad, prod.id as id_productor, prod.nombre as productor, var.nombre || ' / ' || pais.nombre as variedad, cul.calibre as calibre, cul.periodo_inicio, cul.periodo_fin, cul.produccion_anual_estimada, cul.maximo_exportar " 
                            + "From aja_variedad_de_cerezas var, aja_productor prod, aja_cultivo cul, aja_pais pais " 
                            + "Where var.id= cul.id_variedad and cul.id_productor= prod.id and pais.id= var.id_pais " 
                            + "And cul.id= "+part3.trim()+" and prod.nombre= '"+part4.trim()+"' and var.nombre= '"+part5.trim()+"' and pais.nombre= '"+part6.trim()+"'" );
                        while( rs.next() ){
                            id_Cultivo = rs.getInt(1);
                            id_Productor = rs.getInt(3);
                            id_Variedad = rs.getInt(2);
                            cbProductor.setSelectedItem(rs.getString(4));
                            cbVariedad.setSelectedItem(rs.getString(5));
                            datePeriodoIni.setDate(rs.getDate(7));
                            datePeriodoFin.setDate(rs.getDate(8));
                            sliCalibre.setValue(rs.getInt(6));
                            spiProduccionA.setValue(rs.getInt(9));
                            spiExport.setValue(rs.getInt(10));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                limpiar();
                btnEliminar.setEnabled(true);
                cbProductorVarEli.setEnabled(true);
                btnInsertar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cbProductorVarActionPerformed

    private void cbProductorVarEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductorVarEliActionPerformed
        if (cbProductorVarEli.getItemCount() > 1){
            if(!cbProductorVarEli.getSelectedItem().toString().equals(" ")){
                cbProductorVar.setEnabled(false);
                btnModificar.setEnabled(false);
                btnInsertar.setEnabled(false);
                btnEliminar.setEnabled(true);
                cortarCBPVP( cbProductorVarEli );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery( "Select cul.id as id_cultivo " 
                            + "From aja_variedad_de_cerezas var, aja_productor prod, aja_cultivo cul, aja_pais pais " 
                            + "Where var.id= cul.id_variedad and cul.id_productor= prod.id and pais.id= var.id_pais " 
                            + "And cul.id= "+part3.trim()+" and prod.nombre= '"+part4.trim()+"' and var.nombre= '"+part5.trim()+"' and pais.nombre= '"+part6.trim()+"'" );
                        while( rs.next() ){
                            id_Cultivo = rs.getInt(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(true);
                cbProductorVar.setEnabled(true);
                btnInsertar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cbProductorVarEliActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbProductor;
    private javax.swing.JComboBox<String> cbProductorVar;
    private javax.swing.JComboBox<String> cbProductorVarEli;
    private javax.swing.JComboBox<String> cbVariedad;
    private com.toedter.calendar.JDateChooser datePeriodoFin;
    private com.toedter.calendar.JDateChooser datePeriodoIni;
    private javax.swing.JLabel jCalibre;
    private javax.swing.JLabel jCalibreM;
    private javax.swing.JLabel jCantidadM;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jPeriodoFin;
    private javax.swing.JLabel jPeriodoIni;
    private javax.swing.JLabel jProduccionA;
    private javax.swing.JLabel jProductor;
    private javax.swing.JLabel jProductorVarEli;
    private javax.swing.JLabel jProductorVariedad;
    private javax.swing.JLabel jVariedad;
    private javax.swing.JSlider sliCalibre;
    private javax.swing.JSpinner spiExport;
    private javax.swing.JSpinner spiProduccionA;
    // End of variables declaration//GEN-END:variables
}
