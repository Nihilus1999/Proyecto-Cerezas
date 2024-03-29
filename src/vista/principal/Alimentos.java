/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.principal;

import javax.swing.ImageIcon;

/**
 *
 * @author jose-
 */
public class Alimentos extends javax.swing.JFrame {

    /**
     * Creates new form VariedadCerezas
     */
    public Alimentos() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnReceta = new javax.swing.JButton();
        btnIngrediente = new javax.swing.JButton();
        btnElaboracion = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPankekes = new javax.swing.JLabel();
        jTitulo = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jAtras = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jImagenMenu = new javax.swing.JLabel();
        jFondoMenu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnReceta.setBackground(new java.awt.Color(255, 43, 55));
        btnReceta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnReceta.setForeground(new java.awt.Color(255, 255, 255));
        btnReceta.setText("Receta");
        btnReceta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnReceta.setContentAreaFilled(false);
        btnReceta.setFocusPainted(false);
        btnReceta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRecetaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRecetaMouseExited(evt);
            }
        });
        getContentPane().add(btnReceta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 170, 45));

        btnIngrediente.setBackground(new java.awt.Color(255, 43, 55));
        btnIngrediente.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnIngrediente.setForeground(new java.awt.Color(255, 255, 255));
        btnIngrediente.setText("Ingrediente");
        btnIngrediente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnIngrediente.setContentAreaFilled(false);
        btnIngrediente.setFocusPainted(false);
        btnIngrediente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIngredienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIngredienteMouseExited(evt);
            }
        });
        getContentPane().add(btnIngrediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 170, 45));

        btnElaboracion.setBackground(new java.awt.Color(255, 43, 55));
        btnElaboracion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnElaboracion.setForeground(new java.awt.Color(255, 255, 255));
        btnElaboracion.setText("Elaboración");
        btnElaboracion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnElaboracion.setContentAreaFilled(false);
        btnElaboracion.setFocusPainted(false);
        btnElaboracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnElavoracionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnElavoracionMouseExited(evt);
            }
        });
        getContentPane().add(btnElaboracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 170, 45));

        jPanel4.setBackground(new java.awt.Color(191, 0, 51));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 170, 43));

        jPanel5.setBackground(new java.awt.Color(191, 0, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 170, 43));

        jPanel6.setBackground(new java.awt.Color(191, 0, 51));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 170, 43));

        jPankekes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPankekes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pankekes.png"))); // NOI18N
        getContentPane().add(jPankekes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 170, 170));

        jTitulo.setBackground(new java.awt.Color(82, 171, 28));
        jTitulo.setFont(new java.awt.Font("Bookman Old Style", 1, 35)); // NOI18N
        jTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTitulo.setText("Recetas e Ingredientes");
        jTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        getContentPane().add(jTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 430, -1));

        jPanel7.setBackground(new java.awt.Color(191, 0, 51));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 430, 45));

        jPanel3.setBackground(new java.awt.Color(191, 0, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/atras.png"))); // NOI18N
        jPanel3.add(jAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 10, 32, 32));

        btnRegresar.setBackground(new java.awt.Color(255, 43, 55));
        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("   Regresar");
        btnRegresar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRegresar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegresarMouseExited(evt);
            }
        });
        jPanel3.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 172, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, 172, 50));

        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bandeja-de-comidaMini.png"))); // NOI18N
        getContentPane().add(jImagenMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 250, 250));

        jFondoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoRecetas.jpg"))); // NOI18N
        getContentPane().add(jFondoMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 0, 670, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseEntered
        btnRegresar.setContentAreaFilled(true);
        jAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/atras.png")));
    }//GEN-LAST:event_btnRegresarMouseEntered

    private void btnRegresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseExited
        btnRegresar.setContentAreaFilled(false);
    }//GEN-LAST:event_btnRegresarMouseExited

    private void btnRecetaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRecetaMouseEntered
        btnReceta.setContentAreaFilled(true);
    }//GEN-LAST:event_btnRecetaMouseEntered

    private void btnRecetaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRecetaMouseExited
        btnReceta.setContentAreaFilled(false);
    }//GEN-LAST:event_btnRecetaMouseExited

    private void btnIngredienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngredienteMouseEntered
        btnIngrediente.setContentAreaFilled(true);
    }//GEN-LAST:event_btnIngredienteMouseEntered

    private void btnIngredienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngredienteMouseExited
        btnIngrediente.setContentAreaFilled(false);
    }//GEN-LAST:event_btnIngredienteMouseExited

    private void btnElavoracionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnElavoracionMouseEntered
        btnElaboracion.setContentAreaFilled(true);
    }//GEN-LAST:event_btnElavoracionMouseEntered

    private void btnElavoracionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnElavoracionMouseExited
        btnElaboracion.setContentAreaFilled(false);
    }//GEN-LAST:event_btnElavoracionMouseExited

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnElaboracion;
    public javax.swing.JButton btnIngrediente;
    public javax.swing.JButton btnReceta;
    public javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jAtras;
    private javax.swing.JLabel jFondoMenu;
    private javax.swing.JLabel jImagenMenu;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel jPankekes;
    private javax.swing.JLabel jTitulo;
    // End of variables declaration//GEN-END:variables
}
