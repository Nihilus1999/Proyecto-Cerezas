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
public class Ubicaciones extends javax.swing.JFrame {

    /**
     * Creates new form VariedadCerezas
     */
    public Ubicaciones() {
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

        btnPais = new javax.swing.JButton();
        btnRegion = new javax.swing.JButton();
        btnCiudad = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jMapita = new javax.swing.JLabel();
        jTitulo = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jAtras = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jImagen = new javax.swing.JLabel();
        jFondoMenu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPais.setBackground(new java.awt.Color(115, 59, 3));
        btnPais.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnPais.setForeground(new java.awt.Color(255, 255, 255));
        btnPais.setText("Pais");
        btnPais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnPais.setContentAreaFilled(false);
        btnPais.setFocusPainted(false);
        btnPais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPaisMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPaisMouseExited(evt);
            }
        });
        getContentPane().add(btnPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 170, 45));

        btnRegion.setBackground(new java.awt.Color(115, 59, 3));
        btnRegion.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegion.setForeground(new java.awt.Color(255, 255, 255));
        btnRegion.setText("Región");
        btnRegion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnRegion.setContentAreaFilled(false);
        btnRegion.setFocusPainted(false);
        btnRegion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegionMouseExited(evt);
            }
        });
        getContentPane().add(btnRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 170, 45));

        btnCiudad.setBackground(new java.awt.Color(115, 59, 3));
        btnCiudad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnCiudad.setForeground(new java.awt.Color(255, 255, 255));
        btnCiudad.setText("Ciudad");
        btnCiudad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnCiudad.setContentAreaFilled(false);
        btnCiudad.setFocusPainted(false);
        btnCiudad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCiudadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCiudadMouseExited(evt);
            }
        });
        getContentPane().add(btnCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 170, 45));

        jPanel4.setBackground(new java.awt.Color(237, 139, 18));

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

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 170, 43));

        jPanel5.setBackground(new java.awt.Color(237, 139, 18));

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

        jPanel6.setBackground(new java.awt.Color(237, 139, 18));

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

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 170, 43));

        jMapita.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMapita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mapita.png"))); // NOI18N
        getContentPane().add(jMapita, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 170, 170));

        jTitulo.setBackground(new java.awt.Color(82, 171, 28));
        jTitulo.setFont(new java.awt.Font("Bookman Old Style", 1, 35)); // NOI18N
        jTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTitulo.setText("Ubicaciones y Regiones");
        jTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        getContentPane().add(jTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 440, -1));

        jPanel7.setBackground(new java.awt.Color(237, 139, 18));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 440, 45));

        jPanel3.setBackground(new java.awt.Color(237, 139, 18));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/atras.png"))); // NOI18N
        jPanel3.add(jAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 10, 32, 32));

        btnRegresar.setBackground(new java.awt.Color(115, 59, 3));
        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("   Regresar");
        btnRegresar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegresarMouseExited(evt);
            }
        });
        jPanel3.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 172, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 360, 172, 50));

        jImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mapaMini.png"))); // NOI18N
        getContentPane().add(jImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 250, 250));

        jFondoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoUbicacion.jpg"))); // NOI18N
        getContentPane().add(jFondoMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 0, 640, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseEntered
        btnRegresar.setContentAreaFilled(true);
        jAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/atras.png")));
    }//GEN-LAST:event_btnRegresarMouseEntered

    private void btnRegresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseExited
        btnRegresar.setContentAreaFilled(false);
    }//GEN-LAST:event_btnRegresarMouseExited

    private void btnPaisMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPaisMouseEntered
        btnPais.setContentAreaFilled(true);
    }//GEN-LAST:event_btnPaisMouseEntered

    private void btnPaisMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPaisMouseExited
        btnPais.setContentAreaFilled(false);
    }//GEN-LAST:event_btnPaisMouseExited

    private void btnRegionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegionMouseEntered
        btnRegion.setContentAreaFilled(true);
    }//GEN-LAST:event_btnRegionMouseEntered

    private void btnRegionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegionMouseExited
        btnRegion.setContentAreaFilled(false);
    }//GEN-LAST:event_btnRegionMouseExited

    private void btnCiudadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCiudadMouseEntered
        btnCiudad.setContentAreaFilled(true);
    }//GEN-LAST:event_btnCiudadMouseEntered

    private void btnCiudadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCiudadMouseExited
        btnCiudad.setContentAreaFilled(false);
    }//GEN-LAST:event_btnCiudadMouseExited

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCiudad;
    public javax.swing.JButton btnPais;
    public javax.swing.JButton btnRegion;
    public javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jAtras;
    private javax.swing.JLabel jFondoMenu;
    private javax.swing.JLabel jImagen;
    private javax.swing.JLabel jMapita;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel jTitulo;
    // End of variables declaration//GEN-END:variables
}
