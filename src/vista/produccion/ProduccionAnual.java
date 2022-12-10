/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.produccion;

import controlador.controllerLogin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jose-
 */
public class ProduccionAnual extends javax.swing.JFrame {

    /**
     * Creates new form ProduccionAnual
     */
    public ProduccionAnual() {
        initComponents();
    }

    public void llenarCbCultivoProduA(){
        cbCultivoProduA.removeAllItems();
        cbCultivoProduAEli.removeAllItems();
        cbCultivoProduA.addItem(" ");
        cbCultivoProduAEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select cul.nombre || ' / ' || produ.nombre || ' / ' || 'Fecha: ' || proA.id from aja_cultivo cul, aja_productor produ, aja_produccion_anua_real proA where proA.id_cultivo = cul.id and cul.id_productor = produ.id" );
            while ( rs.next() ) {
            String nombre = rs.getString("nombre");
            cbCultivoProduA.addItem(nombre);
            cbCultivoProduAEli.addItem(nombre);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jCultivoProduA = new javax.swing.JLabel();
        cbCultivoProduA = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
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

        jPanel2.setBackground(new java.awt.Color(0, 153, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCultivoProduA.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCultivoProduA.setForeground(new java.awt.Color(255, 255, 255));
        jCultivoProduA.setText("Cultivo y su Produccion Anual");
        jPanel2.add(jCultivoProduA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel2.add(cbCultivoProduA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        jButton1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Modificar");
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 120, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 410, 90));

        jPanel3.setBackground(new java.awt.Color(0, 153, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCultivoProduAEli.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCultivoProduAEli.setForeground(new java.awt.Color(255, 255, 255));
        jCultivoProduAEli.setText("Cultivo y su Produccion Anual");
        jPanel3.add(jCultivoProduAEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jPanel3.add(cbCultivoProduAEli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setText("Eliminar");
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 120, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 410, 90));

        jCultivo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCultivo.setForeground(new java.awt.Color(255, 255, 255));
        jCultivo.setText("Cultivo");
        jPanel1.add(jCultivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jPanel1.add(cbCultivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 255, 25));

        jAno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jAno.setForeground(new java.awt.Color(255, 255, 255));
        jAno.setText("Año");
        jPanel1.add(jAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));
        jPanel1.add(dateAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 255, 25));

        jProduccion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jProduccion.setForeground(new java.awt.Color(255, 255, 255));
        jProduccion.setText("Produccion");
        jPanel1.add(jProduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        spiProduccion.setModel(new javax.swing.SpinnerNumberModel(0.0f, null, null, 100.0f));
        jPanel1.add(spiProduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 128, 25));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 120, 30));

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbCultivo;
    private javax.swing.JComboBox<String> cbCultivoProduA;
    private javax.swing.JComboBox<String> cbCultivoProduAEli;
    private com.toedter.calendar.JDateChooser dateAno;
    private javax.swing.JLabel jAno;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jCultivo;
    private javax.swing.JLabel jCultivoProduA;
    private javax.swing.JLabel jCultivoProduAEli;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jProduccion;
    private javax.swing.JSpinner spiProduccion;
    // End of variables declaration//GEN-END:variables
}
