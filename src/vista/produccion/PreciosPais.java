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

public class PreciosPais extends javax.swing.JFrame {

    String part1;
    String part2;
    
    SimpleDateFormat format = new SimpleDateFormat("YYYY-MMM-dd");
    
    public PreciosPais() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }

    public void insert(){
        try {
           cortarCBV(cbVariedad);
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "INSERT INTO aja_historico_precio_variedad (id_pais, id_variedad, fecha_inicio, precio, calibre) "
                   + "VALUES ( (SELECT id from aja_pais where nombre = '"+cbPais.getSelectedItem().toString()+"' ), (SELECT var.id from aja_variedad_de_cerezas var where var.nombre = '"+part1.trim()+"' and var.id_pais = (SELECT id from aja_pais where nombre = '"+part2.trim()+"')), ' "+format.format(dateFechaIni.getDate())+ " ', "+spiPrecio.getValue()+", "+sliCalibre.getValue()+" )";
           stmt.executeUpdate(sql);
           controllerLogin.conexion.commit();
           
                   ResultSet rs = stmt.executeQuery( "SELECT id_variedad ,id_pais, fecha_inicio from aja_historico_precio_variedad where id_variedad = (SELECT var.id from aja_variedad_de_cerezas var where var.nombre = '"+part1.trim()+"' and var.id_pais = (SELECT id from aja_pais where nombre = '"+part2.trim()+"')) "
                           + " and id_pais = (SELECT id from aja_pais where nombre = '"+cbPais.getSelectedItem().toString()+"' ) order by fecha_inicio DESC" );
                   rs.next();
                   while ( rs.next() ) {
                       sql = ("UPDATE public.aja_historico_precio_variedad " 
                               + "SET fecha_final= ' "+format.format(dateFechaIni.getDate())+" ' " 
                               + "WHERE id_variedad = "+rs.getInt(1)+" and id_pais = "+rs.getInt(2)+" and fecha_inicio = ' "+format.format(rs.getDate(3))+" ' ");
                       stmt.executeUpdate(sql);
                       controllerLogin.conexion.commit();
                       System.out.println("Actualizo");
                        break;
                    }
                   
                   JOptionPane.showMessageDialog(null,"Se ha registrado exitosamente");
                   limpiar();
                   
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void limpiar(){
        cbPais.setSelectedIndex(0);
        cbVariedad.setSelectedIndex(0);
        dateFechaIni.setDate(null);
    }
    
    public void llenarVar(){
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select var.nombre || ' / ' || pais.nombre as cbVar from aja_variedad_de_cerezas var, aja_pais pais where var.id_pais = pais.id order by var.nombre" );
            while(rs.next()){
                String registro = rs.getString(1);
                cbVariedad.addItem(registro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cultivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void llenarPais(){
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select pais.nombre as cbPais from aja_pais pais order by pais.nombre" );
            while(rs.next()){
                String registro = rs.getString(1);
                cbPais.addItem(registro);
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPais = new javax.swing.JLabel();
        cbPais = new javax.swing.JComboBox<>();
        jVariedad = new javax.swing.JLabel();
        cbVariedad = new javax.swing.JComboBox<>();
        jFechaIni = new javax.swing.JLabel();
        dateFechaIni = new com.toedter.calendar.JDateChooser();
        jCalibre = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jCalibreM = new javax.swing.JLabel();
        sliCalibre = new javax.swing.JSlider();
        jPrecio = new javax.swing.JLabel();
        spiPrecio = new javax.swing.JSpinner();
        btnIngresar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPais.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPais.setForeground(new java.awt.Color(255, 255, 255));
        jPais.setText("Pais");
        jPanel1.add(jPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jPanel1.add(cbPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 255, 25));

        jVariedad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVariedad.setForeground(new java.awt.Color(255, 255, 255));
        jVariedad.setText("Variedad");
        jPanel1.add(jVariedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jPanel1.add(cbVariedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 255, 25));

        jFechaIni.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jFechaIni.setForeground(new java.awt.Color(255, 255, 255));
        jFechaIni.setText("Fecha inicio");
        jPanel1.add(jFechaIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));
        jPanel1.add(dateFechaIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 255, 25));

        jCalibre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCalibre.setForeground(new java.awt.Color(255, 255, 255));
        jCalibre.setText("Calibre");
        jPanel1.add(jCalibre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCalibreM)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCalibreM)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 50, 30));

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
        jPanel1.add(sliCalibre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 255, -1));

        jPrecio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPrecio.setForeground(new java.awt.Color(255, 255, 255));
        jPrecio.setText("Precio");
        jPanel1.add(jPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        spiPrecio.setModel(new javax.swing.SpinnerNumberModel(0.0f, null, null, 10.0f));
        jPanel1.add(spiPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 128, 25));

        btnIngresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(0, 0, 0));
        btnIngresar.setText("Ingresar");
        jPanel1.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 120, 30));

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sliCalibreStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliCalibreStateChanged
        jCalibreM.setText(sliCalibre.getValue() + " mm");
    }//GEN-LAST:event_sliCalibreStateChanged

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnIngresar;
    public javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbPais;
    private javax.swing.JComboBox<String> cbVariedad;
    private com.toedter.calendar.JDateChooser dateFechaIni;
    private javax.swing.JLabel jCalibre;
    private javax.swing.JLabel jCalibreM;
    private javax.swing.JLabel jFechaIni;
    private javax.swing.JLabel jPais;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jPrecio;
    private javax.swing.JLabel jVariedad;
    private javax.swing.JSlider sliCalibre;
    private javax.swing.JSpinner spiPrecio;
    // End of variables declaration//GEN-END:variables

}
