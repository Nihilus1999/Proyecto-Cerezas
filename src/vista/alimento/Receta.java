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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ingredientesM.ingredientesM;
import org.postgresql.core.CachedQuery;

/**
 *
 * @author jose-
 */
public class Receta extends javax.swing.JFrame {

    String part1;
    String part2;
    
    ArrayList<ingredientesM> lista = new ArrayList<>();
    
    DefaultTableModel modelo;
    
    public Receta() {
        initComponents();
        modelo = (DefaultTableModel) tbReceta.getModel();
    }

    public void insert(){
        try {
            cortarCB(cbVar);
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql;
            if (cbProductor.getSelectedIndex() != 0){
                sql = "INSERT INTO aja_receta( " 
                    + " titulo, tipo, raciones, tiempo_preparacion, id_cliente, id_productor) " 
                    + "VALUES ( '"+txtNombreRe.getText()+"', '"+cbTipo.getSelectedItem().toString()+"', "+spiReciones.getValue()+", to_date('"+spiTiempoPre.getValue()+"','min'), null, (SELECT id from aja_productor where nombre = '"+cbProductor.getSelectedItem().toString()+"'))  ";
            }else{
                sql = "INSERT INTO aja_receta( " 
                    + " titulo, tipo, raciones, tiempo_preparacion, id_cliente, id_productor) " 
                    + "VALUES ( '"+txtNombreRe.getText()+"', '"+cbTipo.getSelectedItem().toString()+"', "+spiReciones.getValue()+", to_date('"+spiTiempoPre.getValue()+"','min'), (SELECT id from aja_cliente where denominacion_comercial = '"+cbCliente+"'), null)  ";
            }
            stmt.executeUpdate(sql);
            controllerLogin.conexion.commit();
            for ( int i = 0 ; i < lista.size() ; i++ ){
                sql = "INSERT INTO aja_medidas_del_ingrediente( " 
                        + "id_receta, id_ingrediente, cantidad, medida) " 
                        + "VALUES ((SELECT id from aja_receta where titulo = '"+txtNombreRe.getText()+"'), "+lista.get(i).id_ingrediente+", "+lista.get(i).cantidad+", ' "+lista.get(i).medida+" ') " ;
                stmt.executeUpdate(sql);
                controllerLogin.conexion.commit();
            }
            
            sql = "INSERT INTO aja_variedad_por_receta( " 
                    + "id_receta, id_variedad, cantidad) " 
                    + "VALUES ((SELECT id from aja_receta where titulo = '"+txtNombreRe.getText()+"'), (select var.id from aja_variedad_de_cerezas var, aja_pais pais where var.nombre= '"+part1.trim()+"' and pais.nombre = '"+part2.trim()+"' and var.id_pais = pais.id ), "
                    + " "+spiCantidadVar.getValue()+");" ;
                stmt.executeUpdate(sql);
                controllerLogin.conexion.commit();
           
            JOptionPane.showMessageDialog(null,"Se ha registrado exitosamente");
            limpiar();
            llenarCbRaceta();

        } catch (SQLException e) {
              e.printStackTrace();
        }
    }
    
    public void delete(){
         try{
                Statement stmt = controllerLogin.conexion.createStatement();
                String sql = "DELETE FROM aja_receta " 
                        + "WHERE titulo = '"+cbReceta.getSelectedItem().toString()+"' ";

                stmt.executeUpdate(sql);
                controllerLogin.conexion.commit();
                JOptionPane.showMessageDialog(null,"Se ha borrado la region exitosamente");
                limpiar();
                llenarCbRaceta();

           }catch (SQLException e) {
               e.printStackTrace();

           }   
    }
    
    public void limpiar(){
        cbCliente.setSelectedIndex(0);
        cbProductor.setSelectedIndex(0);
        cbIngrediente.setSelectedIndex(0);
        spiCantidad.setValue(1);
        spiCantidadVar.setValue(1);
        spiReciones.setValue(1);
        spiTiempoPre.setValue(1);
        txtMedida.setText("");
        lista.removeAll(lista);
        try {
            DefaultTableModel model = (DefaultTableModel) tbReceta.getModel();
            int filas=tbReceta.getRowCount();
            for (int i = 0;filas>i; i++) {
                model.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    public void llenarCBs(){
        try {
            cbProductor.addItem(" ");
            cbCliente.addItem(" ");
            Statement stmt = controllerLogin.conexion.createStatement();
            
            ResultSet rs = stmt.executeQuery( "select nombre from aja_productor" );
            
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbProductor.addItem(registro);
            }
            
            rs = stmt.executeQuery( "select denominacion_comercial from aja_cliente" );
            
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbCliente.addItem(registro);
            }
            
            rs = stmt.executeQuery( "select nombre from aja_ingrediente" );
            
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbIngrediente.addItem(registro);
            }
            
            rs = stmt.executeQuery( "select var.nombre || ' / ' || pais.nombre from aja_variedad_de_cerezas var, aja_pais pais where var.id_pais = pais.id" );
            
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbVar.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void llenarCbRaceta(){
        try{
            cbReceta.removeAllItems();
            cbReceta.addItem(" ");
            Statement stmt = controllerLogin.conexion.createStatement();
            
            ResultSet rs = stmt.executeQuery( "select titulo from aja_receta" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbReceta.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void llenarTabla(){
        Object [] fila = new Object[3];
        fila[0] = lista.get(lista.size() - 1).nombre_ingrediente;
        fila[1] = lista.get(lista.size() - 1).cantidad;
        fila[2] = lista.get(lista.size() - 1).medida;
        modelo.addRow(fila);
        tbReceta.setModel(modelo);
    }
    
    public void cortarCB( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split("/");
        part1 = parts[0]; 
        part2 = parts[1];
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jProductor = new javax.swing.JLabel();
        cbProductor = new javax.swing.JComboBox<>();
        jCliente = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        jIngrediente = new javax.swing.JLabel();
        cbIngrediente = new javax.swing.JComboBox<>();
        jMedida = new javax.swing.JLabel();
        txtMedida = new javax.swing.JTextField();
        jCantidad = new javax.swing.JLabel();
        spiCantidad = new javax.swing.JSpinner();
        jVar = new javax.swing.JLabel();
        cbVar = new javax.swing.JComboBox<>();
        jCantidadvar = new javax.swing.JLabel();
        spiCantidadVar = new javax.swing.JSpinner();
        jRaciones = new javax.swing.JLabel();
        spiReciones = new javax.swing.JSpinner();
        jTiempoPre = new javax.swing.JLabel();
        spiTiempoPre = new javax.swing.JSpinner();
        btnInsertar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbReceta = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jReceta = new javax.swing.JLabel();
        cbReceta = new javax.swing.JComboBox<>();
        btnEliminar = new javax.swing.JButton();
        jNombreRe = new javax.swing.JLabel();
        txtNombreRe = new javax.swing.JTextField();
        jTipo = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 43, 55));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jProductor.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jProductor.setForeground(new java.awt.Color(255, 255, 255));
        jProductor.setText("Productor");
        jPanel1.add(jProductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbProductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductorActionPerformed(evt);
            }
        });
        jPanel1.add(cbProductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        jCliente.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCliente.setForeground(new java.awt.Color(255, 255, 255));
        jCliente.setText("Cliente");
        jPanel1.add(jCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        cbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClienteActionPerformed(evt);
            }
        });
        jPanel1.add(cbCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 255, 25));

        jIngrediente.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jIngrediente.setForeground(new java.awt.Color(255, 255, 255));
        jIngrediente.setText("Ingrediente");
        jPanel1.add(jIngrediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jPanel1.add(cbIngrediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 255, 25));

        jMedida.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jMedida.setForeground(new java.awt.Color(255, 255, 255));
        jMedida.setText("Medida");
        jPanel1.add(jMedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, -1, -1));
        jPanel1.add(txtMedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, 128, 30));

        jCantidad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCantidad.setForeground(new java.awt.Color(255, 255, 255));
        jCantidad.setText("Cantidad");
        jPanel1.add(jCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, -1, -1));

        spiCantidad.setModel(new javax.swing.SpinnerNumberModel(1.0f, 1.0f, null, 1.0f));
        jPanel1.add(spiCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 128, 25));

        jVar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jVar.setForeground(new java.awt.Color(255, 255, 255));
        jVar.setText("Variedad de cereza en receta");
        jPanel1.add(jVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jPanel1.add(cbVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 255, 25));

        jCantidadvar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jCantidadvar.setForeground(new java.awt.Color(255, 255, 255));
        jCantidadvar.setText("Cantidad de cereza (g)");
        jPanel1.add(jCantidadvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, -1, -1));

        spiCantidadVar.setModel(new javax.swing.SpinnerNumberModel(1.0f, 1.0f, null, 1.0f));
        jPanel1.add(spiCantidadVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 128, 25));

        jRaciones.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jRaciones.setForeground(new java.awt.Color(255, 255, 255));
        jRaciones.setText("Raciones");
        jPanel1.add(jRaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, -1, -1));

        spiReciones.setModel(new javax.swing.SpinnerNumberModel(1.0f, 1.0f, null, 1.0f));
        jPanel1.add(spiReciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 128, 25));

        jTiempoPre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTiempoPre.setForeground(new java.awt.Color(255, 255, 255));
        jTiempoPre.setText("Tiempo de preparacion (min)");
        jPanel1.add(jTiempoPre, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, -1, -1));

        spiTiempoPre.setModel(new javax.swing.SpinnerNumberModel(1.0f, 1.0f, null, 1.0f));
        jPanel1.add(spiTiempoPre, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 128, 25));

        btnInsertar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsertar.setForeground(new java.awt.Color(0, 0, 0));
        btnInsertar.setText("Insertar");
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 570, 120, 30));

        btnBorrar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnBorrar.setForeground(new java.awt.Color(0, 0, 0));
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 180, 120, 30));

        btnAgregar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 120, 30));

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 570, 120, 30));

        tbReceta.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tbReceta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ingrediente", "Cantidad", "Medida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbReceta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tbReceta.setRowHeight(30);
        jScrollPane1.setViewportView(tbReceta);
        if (tbReceta.getColumnModel().getColumnCount() > 0) {
            tbReceta.getColumnModel().getColumn(0).setResizable(false);
            tbReceta.getColumnModel().getColumn(1).setResizable(false);
            tbReceta.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 890, 310));

        jPanel2.setBackground(new java.awt.Color(191, 0, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jReceta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jReceta.setForeground(new java.awt.Color(255, 255, 255));
        jReceta.setText("Receta");
        jPanel2.add(jReceta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel2.add(cbReceta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 255, 25));

        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setText("Eliminar");
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 120, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 410, 90));

        jNombreRe.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jNombreRe.setForeground(new java.awt.Color(255, 255, 255));
        jNombreRe.setText("Nombre de la receta");
        jPanel1.add(jNombreRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, -1));
        jPanel1.add(txtNombreRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 255, 30));

        jTipo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTipo.setForeground(new java.awt.Color(255, 255, 255));
        jTipo.setText("Tipo");
        jPanel1.add(jTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, -1, -1));

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "B", "P", "S", "Otro" }));
        jPanel1.add(cbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, 60, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbProductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductorActionPerformed
        if( cbProductor.getItemCount() > 1 ){
            if(!cbProductor.getSelectedItem().toString().equals(" ")){
                cbCliente.setEnabled(false);
            }else{
                cbCliente.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cbProductorActionPerformed

    private void cbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClienteActionPerformed
        if( cbCliente.getItemCount() > 1 ){
            if(!cbCliente.getSelectedItem().toString().equals(" ")){
                cbProductor.setEnabled(false);
            }else{
                cbProductor.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cbClienteActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if ( cbIngrediente.getItemCount() > 1 ){
            try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select id from aja_ingrediente where nombre = '"+cbIngrediente.getSelectedItem().toString()+"' " );
            while( rs.next() ){
                lista.add(new ingredientesM(rs.getInt(1), cbIngrediente.getSelectedItem().toString(),txtMedida.getText(), (Float)spiCantidad.getValue()));
                llenarTabla();
            }
         } catch (SQLException e) {
                e.printStackTrace();
        }
        cbIngrediente.removeItem(cbIngrediente.getSelectedItem().toString());
        cbIngrediente.setSelectedIndex(0);
        txtMedida.setText("");
        }else{
            cbIngrediente.setEnabled(false);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnBorrarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnInsertar;
    public javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbCliente;
    private javax.swing.JComboBox<String> cbIngrediente;
    private javax.swing.JComboBox<String> cbProductor;
    private javax.swing.JComboBox<String> cbReceta;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JComboBox<String> cbVar;
    private javax.swing.JLabel jCantidad;
    private javax.swing.JLabel jCantidadvar;
    private javax.swing.JLabel jCliente;
    private javax.swing.JLabel jIngrediente;
    private javax.swing.JLabel jMedida;
    private javax.swing.JLabel jNombreRe;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jProductor;
    private javax.swing.JLabel jRaciones;
    private javax.swing.JLabel jReceta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jTiempoPre;
    private javax.swing.JLabel jTipo;
    private javax.swing.JLabel jVar;
    private javax.swing.JSpinner spiCantidad;
    private javax.swing.JSpinner spiCantidadVar;
    private javax.swing.JSpinner spiReciones;
    private javax.swing.JSpinner spiTiempoPre;
    private javax.swing.JTable tbReceta;
    private javax.swing.JTextField txtMedida;
    private javax.swing.JTextField txtNombreRe;
    // End of variables declaration//GEN-END:variables
}
