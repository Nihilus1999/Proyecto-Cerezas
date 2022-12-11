/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.alimento;

import controlador.controllerLogin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.produccion.Cultivo;

/**
 *
 * @author jose-
 */
public class Elaboracion extends javax.swing.JFrame {

    ArrayList<String> pasos = new ArrayList<>();
    DefaultTableModel modelo;
    
    public Elaboracion() {
        initComponents();
        modelo = (DefaultTableModel) tbElavoracion.getModel();
    }

    public void insert(){
        try {
           Statement stmt = controllerLogin.conexion.createStatement();
            for ( int i = 0 ; i < pasos.size() ; i++ ){
                String sql = "INSERT INTO aja_elaboracion (id_receta, numero_paso, descripcion) VALUES "
                        + " ( (SELECT id from aja_receta where titulo = '"+cbReceta.getSelectedItem().toString()+"'), "+(i + 1)+", ' "+pasos.get(i)+" ')";
                stmt.executeUpdate(sql);
                controllerLogin.conexion.commit();
            }
           
            JOptionPane.showMessageDialog(null,"Se ha registrado exitosamente");
            limpiar();
            llenarReceta();

        } catch (SQLException e) {
              e.printStackTrace();
        }
    }
    
    public void limpiar(){
        cbReceta.setSelectedIndex(0);
        txtDescripcionA.setText("");
        pasos.removeAll(pasos);
        try {
            DefaultTableModel model = (DefaultTableModel) tbElavoracion.getModel();
            int filas=tbElavoracion.getRowCount();
            for (int i = 0;filas>i; i++) {
                model.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    public void llenarTabla(){
        Object [] fila = new Object[2];
        fila[0] = pasos.size();
        fila[1] = pasos.get(pasos.size() - 1);
        modelo.addRow(fila);
        tbElavoracion.setModel(modelo);
    }
    
    public void llenarReceta(){
        cbReceta.removeAllItems();
        cbReceta.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select titulo from aja_receta where id not in (select id_receta from aja_elaboracion)" );
            while(rs.next()){
                String registro = rs.getString(1);
                cbReceta.addItem(registro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cultivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jReceta = new javax.swing.JLabel();
        cbReceta = new javax.swing.JComboBox<>();
        jDescripcion = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        txtDescripcion = new javax.swing.JScrollPane();
        txtDescripcionA = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbElavoracion = new javax.swing.JTable();
        btnInsertar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 43, 55));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jReceta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jReceta.setForeground(new java.awt.Color(255, 255, 255));
        jReceta.setText("Receta");
        jPanel1.add(jReceta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        cbReceta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRecetaActionPerformed(evt);
            }
        });
        jPanel1.add(cbReceta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 255, 25));

        jDescripcion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        jDescripcion.setText("Descripcion");
        jPanel1.add(jDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        btnAgregar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregar.setText("Agregar");
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 120, 30));

        txtDescripcionA.setColumns(20);
        txtDescripcionA.setLineWrap(true);
        txtDescripcionA.setRows(5);
        txtDescripcionA.setWrapStyleWord(true);
        txtDescripcionA.setEnabled(false);
        txtDescripcion.setViewportView(txtDescripcionA);

        jPanel1.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 410, -1));

        tbElavoracion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Paso", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbElavoracion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tbElavoracion.setRowHeight(30);
        tbElavoracion.setShowGrid(true);
        jScrollPane1.setViewportView(tbElavoracion);
        if (tbElavoracion.getColumnModel().getColumnCount() > 0) {
            tbElavoracion.getColumnModel().getColumn(0).setResizable(false);
            tbElavoracion.getColumnModel().getColumn(0).setPreferredWidth(70);
            tbElavoracion.getColumnModel().getColumn(1).setResizable(false);
            tbElavoracion.getColumnModel().getColumn(1).setPreferredWidth(343);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 610, 270));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        btnInsertar.setEnabled(false);
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 120, 30));

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 550, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        pasos.add(txtDescripcionA.getText());
        txtDescripcionA.setText("");
        llenarTabla();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void cbRecetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRecetaActionPerformed
        if( cbReceta.getItemCount() > 1 ){
            if(!cbReceta.getSelectedItem().toString().equals(" ")){
                txtDescripcionA.setEnabled(true);
                btnAgregar.setEnabled(true);
                btnInsertar.setEnabled(true);
            }else{
                txtDescripcionA.setEnabled(false);
                btnAgregar.setEnabled(false);
                btnInsertar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cbRecetaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbReceta;
    private javax.swing.JLabel jDescripcion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jReceta;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tbElavoracion;
    private javax.swing.JScrollPane txtDescripcion;
    private javax.swing.JTextArea txtDescripcionA;
    // End of variables declaration//GEN-END:variables
}
