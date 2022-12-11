/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.produccion;

import controlador.controllerLogin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author jose-
 */
public class ProduccionAnual extends javax.swing.JFrame {

    String part1;//id_cultivo
    String part2;//nombre productor
    String part3;//nombre var
    String part4;//nombre pais
    String part5;//id_produccion
    
    int id_Productor;
    int id_Var;
    
    SimpleDateFormat format = new SimpleDateFormat("YYYY-MMM-dd");
    
    public ProduccionAnual() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }
    
    public void insert(){    
        try {
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = "insert into aja_produccion_anual_real (id_cultivo, id_productor, id_variedad, yyyy, produccion) values " 
                   + "( "+cbCultivo.getSelectedItem().toString().replaceAll("Cultivo #", "")+", (select id_productor from aja_cultivo where id= "+cbCultivo.getSelectedItem().toString().replaceAll("Cultivo #", "")+" ), " 
                   + "(select id_variedad from aja_cultivo where id="+cbCultivo.getSelectedItem().toString().replaceAll("Cultivo #", "")+"), '"+format.format(dateAno.getDate())+"', "+spiProduccion.getValue()+")" ;
           
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
                           llenarCbCultivoProduA();
                           limpiar();
                       }

                     } catch (SQLException e) {
                           e.printStackTrace();
                   }
    }
 
    public void delete(){
        try{
            Statement stmt = controllerLogin.conexion.createStatement();
            String sql = "delete from aja_produccion_anual_real par " 
                            + "where id_cultivo="+part1.trim()+" and yyyy= '"+part5.trim()+"' ";
            ResultSet rs = stmt.executeQuery( "select yyyy, id_cultivo from aja_produccion_anual_real" );
            int flag=0;
            while ( rs.next() ) {
                 if( rs.getDate(1).toString().equals(part5.trim()) && String.valueOf(rs.getInt(2)).equals(part1.trim()) ){
                     stmt.executeUpdate(sql);
                     controllerLogin.conexion.commit();
                     JOptionPane.showMessageDialog(null,"Se ha borrado el registrado exitosamente");
                     llenarCbCultivoProduA();
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
    
    public void limpiar(){
        cbCultivo.setSelectedIndex(0);
        spiProduccion.setValue(0);
        dateAno.setDate(null);
    }

    public void llenarCbCultivoProduA(){
        cbCultivoProduAEli.removeAllItems();
        cbCultivoProduAEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select 'Cultivo #' || cul.id || ' / ' || produ.nombre || ' / ' || var.nombre || ' / ' || pais.nombre || ' / ' || 'Fecha: ' || proA.yyyy from aja_cultivo cul, aja_productor produ, aja_variedad_de_cerezas var, aja_pais pais, aja_produccion_anual_real proA where proA.id_cultivo = cul.id and cul.id_productor = produ.id and proA.id_variedad = var.id and var.id_pais = pais.id order by proA.yyyy DESC" );
            while ( rs.next() ) {
            String nombre = rs.getString(1);
            cbCultivoProduAEli.addItem(nombre);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
   
    public void llenarCbCultivo(){
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select 'Cultivo #' || id from aja_cultivo" );
            while ( rs.next() ) {
            String nombre = rs.getString(1);
            cbCultivo.addItem(nombre);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
       
    public void cortarCBP( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split("/");
        part1 = parts[0].replaceAll("Cultivo #", "");
        part2 = parts[1];
        part3 = parts[2];
        part4 = parts[3];
        part5 = parts[4].replaceAll("Fecha: ", "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jCultivoProduAEli = new javax.swing.JLabel();
        cbCultivoProduAEli = new javax.swing.JComboBox<>();
        btnEliminar = new javax.swing.JButton();
        jCultivo = new javax.swing.JLabel();
        cbCultivo = new javax.swing.JComboBox<>();
        jAno = new javax.swing.JLabel();
        dateAno = new com.toedter.calendar.JDateChooser();
        jProduccion = new javax.swing.JLabel();
        spiProduccion = new javax.swing.JSpinner();
        btnInsertar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 153, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCultivoProduAEli.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCultivoProduAEli.setForeground(new java.awt.Color(255, 255, 255));
        jCultivoProduAEli.setText("Cultivo y su Produccion Anual");
        jPanel3.add(jCultivoProduAEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        cbCultivoProduAEli.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        cbCultivoProduAEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCultivoProduAEliActionPerformed(evt);
            }
        });
        jPanel3.add(cbCultivoProduAEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 370, 25));

        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setText("Eliminar");
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 520, 90));

        jCultivo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCultivo.setForeground(new java.awt.Color(255, 255, 255));
        jCultivo.setText("Cultivo");
        jPanel1.add(jCultivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jPanel1.add(cbCultivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 255, 25));

        jAno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jAno.setForeground(new java.awt.Color(255, 255, 255));
        jAno.setText("Año");
        jPanel1.add(jAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));
        jPanel1.add(dateAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 255, 25));

        jProduccion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jProduccion.setForeground(new java.awt.Color(255, 255, 255));
        jProduccion.setText("Produccion");
        jPanel1.add(jProduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        spiProduccion.setModel(new javax.swing.SpinnerNumberModel(0.0f, null, null, 100.0f));
        jPanel1.add(spiProduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 128, 25));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 120, 30));

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbCultivoProduAEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCultivoProduAEliActionPerformed
        if (cbCultivoProduAEli.getItemCount() > 1){
            if(!cbCultivoProduAEli.getSelectedItem().toString().equals(" ")){
                btnInsertar.setEnabled(false);
                btnEliminar.setEnabled(true);
                cortarCBP(cbCultivoProduAEli);
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery( "SELECT prod.id, var.id " 
                                                                        + "FROM aja_produccion_anual_real par, aja_productor prod, aja_variedad_de_cerezas var, aja_cultivo cul, aja_pais pais " 
                                                                        + "WHERE par.id_cultivo=cul.id AND par.id_productor=cul.id_productor AND par.id_variedad=cul.id_variedad " 
                                                                        + "AND cul.id_productor= prod.id AND cul.id_variedad= var.id AND pais.id=var.id_pais " 
                                                                        + "AND var.id= (select varr.id " 
                                                                        + "from aja_variedad_de_cerezas varr, aja_pais paiss " 
                                                                        + "where varr.nombre= '"+part3.trim()+"' and pais.nombre ='"+part4.trim()+"' and var.id_pais= paiss.id) " 
                                                                        + "AND cul.id= "+part1.trim()
                                                                        +" AND par.yyyy= '"+part5.trim()
                                                                        +"' AND prod.id=(select prodd.id " 
                                                                        + "from aja_productor prodd " 
                                                                        + "where prodd.nombre= '"+part2.trim()+"')" );
                        while( rs.next() ){
                            id_Productor = rs.getInt(1);
                            id_Var = rs.getInt(2);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                limpiar();
                btnInsertar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cbCultivoProduAEliActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbCultivo;
    private javax.swing.JComboBox<String> cbCultivoProduAEli;
    private com.toedter.calendar.JDateChooser dateAno;
    private javax.swing.JLabel jAno;
    private javax.swing.JLabel jCultivo;
    private javax.swing.JLabel jCultivoProduAEli;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jProduccion;
    private javax.swing.JSpinner spiProduccion;
    // End of variables declaration//GEN-END:variables
}
