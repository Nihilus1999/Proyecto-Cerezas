/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.principal;

import javax.swing.ImageIcon;

/**
 *
 * @author aaron
 */
public class Menu_Inicio extends javax.swing.JFrame {

    /**
     * Creates new form Menu_Inicio
     */
    public Menu_Inicio() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
        setLocationRelativeTo(null);
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
        jImagenMenu = new javax.swing.JLabel();
        jCasa = new javax.swing.JLabel();
        pBotone = new javax.swing.JPanel();
        btnUbicaciones = new javax.swing.JButton();
        btnProduccion = new javax.swing.JButton();
        btnAcuerdos = new javax.swing.JButton();
        btnAdopcion = new javax.swing.JButton();
        btnAlimentos = new javax.swing.JButton();
        btnBeneficios = new javax.swing.JButton();
        btnCargamentos = new javax.swing.JButton();
        btnEvaluacion = new javax.swing.JButton();
        btnEmpresas = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jAtras = new javax.swing.JLabel();
        btnDesconectar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jFondo_App = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carpeta.png"))); // NOI18N
        jPanel1.add(jImagenMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, 400, 400));

        jCasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cabana-de-madera.png"))); // NOI18N
        jPanel1.add(jCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 170, 170));

        pBotone.setBackground(new java.awt.Color(136, 103, 44));
        pBotone.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnUbicaciones.setBackground(new java.awt.Color(152, 132, 43));
        btnUbicaciones.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnUbicaciones.setForeground(new java.awt.Color(255, 255, 255));
        btnUbicaciones.setText("Ubicacion y Región");
        btnUbicaciones.setToolTipText("");
        btnUbicaciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnUbicaciones.setContentAreaFilled(false);
        btnUbicaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUbicaciones.setFocusPainted(false);
        btnUbicaciones.setIconTextGap(0);
        btnUbicaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUbicacionesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUbicacionesMouseExited(evt);
            }
        });
        pBotone.add(btnUbicaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 240, 48));

        btnProduccion.setBackground(new java.awt.Color(152, 132, 43));
        btnProduccion.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnProduccion.setForeground(new java.awt.Color(255, 255, 255));
        btnProduccion.setText("Produccion de Cereza");
        btnProduccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnProduccion.setContentAreaFilled(false);
        btnProduccion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProduccion.setFocusPainted(false);
        btnProduccion.setIconTextGap(0);
        btnProduccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProduccionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProduccionMouseExited(evt);
            }
        });
        pBotone.add(btnProduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 240, 48));

        btnAcuerdos.setBackground(new java.awt.Color(152, 132, 43));
        btnAcuerdos.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnAcuerdos.setForeground(new java.awt.Color(255, 255, 255));
        btnAcuerdos.setText("Acuerdo y Metodo de Pago");
        btnAcuerdos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnAcuerdos.setContentAreaFilled(false);
        btnAcuerdos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAcuerdos.setFocusPainted(false);
        btnAcuerdos.setIconTextGap(0);
        btnAcuerdos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAcuerdosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAcuerdosMouseExited(evt);
            }
        });
        pBotone.add(btnAcuerdos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 48));

        btnAdopcion.setBackground(new java.awt.Color(152, 132, 43));
        btnAdopcion.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnAdopcion.setForeground(new java.awt.Color(255, 255, 255));
        btnAdopcion.setText("Adopción de Cerezo");
        btnAdopcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnAdopcion.setContentAreaFilled(false);
        btnAdopcion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdopcion.setFocusPainted(false);
        btnAdopcion.setIconTextGap(0);
        btnAdopcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdopcionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdopcionMouseExited(evt);
            }
        });
        pBotone.add(btnAdopcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 240, 48));

        btnAlimentos.setBackground(new java.awt.Color(152, 132, 43));
        btnAlimentos.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnAlimentos.setForeground(new java.awt.Color(255, 255, 255));
        btnAlimentos.setText("Receta e Ingrediente");
        btnAlimentos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnAlimentos.setContentAreaFilled(false);
        btnAlimentos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlimentos.setFocusPainted(false);
        btnAlimentos.setIconTextGap(0);
        btnAlimentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAlimentosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAlimentosMouseExited(evt);
            }
        });
        pBotone.add(btnAlimentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 240, 48));

        btnBeneficios.setBackground(new java.awt.Color(152, 132, 43));
        btnBeneficios.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnBeneficios.setForeground(new java.awt.Color(255, 255, 255));
        btnBeneficios.setText("Beneficios para la salud");
        btnBeneficios.setToolTipText("");
        btnBeneficios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnBeneficios.setContentAreaFilled(false);
        btnBeneficios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBeneficios.setFocusPainted(false);
        btnBeneficios.setIconTextGap(0);
        btnBeneficios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBeneficiosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBeneficiosMouseExited(evt);
            }
        });
        pBotone.add(btnBeneficios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 240, 48));

        btnCargamentos.setBackground(new java.awt.Color(152, 132, 43));
        btnCargamentos.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnCargamentos.setForeground(new java.awt.Color(255, 255, 255));
        btnCargamentos.setText("Cargamento y Envio");
        btnCargamentos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnCargamentos.setContentAreaFilled(false);
        btnCargamentos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCargamentos.setFocusPainted(false);
        btnCargamentos.setIconTextGap(0);
        btnCargamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCargamentosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCargamentosMouseExited(evt);
            }
        });
        pBotone.add(btnCargamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 240, 48));

        btnEvaluacion.setBackground(new java.awt.Color(152, 132, 43));
        btnEvaluacion.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnEvaluacion.setForeground(new java.awt.Color(255, 255, 255));
        btnEvaluacion.setText("Evaluación y Resultado");
        btnEvaluacion.setToolTipText("");
        btnEvaluacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnEvaluacion.setContentAreaFilled(false);
        btnEvaluacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEvaluacion.setFocusPainted(false);
        btnEvaluacion.setIconTextGap(0);
        btnEvaluacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEvaluacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEvaluacionMouseExited(evt);
            }
        });
        pBotone.add(btnEvaluacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 240, 48));

        btnEmpresas.setBackground(new java.awt.Color(152, 132, 43));
        btnEmpresas.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnEmpresas.setForeground(new java.awt.Color(255, 255, 255));
        btnEmpresas.setText("Empresa y Asociación");
        btnEmpresas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnEmpresas.setContentAreaFilled(false);
        btnEmpresas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEmpresas.setFocusPainted(false);
        btnEmpresas.setIconTextGap(0);
        btnEmpresas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEmpresasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEmpresasMouseExited(evt);
            }
        });
        pBotone.add(btnEmpresas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 240, 48));

        jPanel1.add(pBotone, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jPanel3.setBackground(new java.awt.Color(136, 103, 44));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar-sesion.png"))); // NOI18N
        jPanel3.add(jAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        btnDesconectar.setBackground(new java.awt.Color(152, 132, 43));
        btnDesconectar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnDesconectar.setForeground(new java.awt.Color(255, 255, 255));
        btnDesconectar.setText("   Desconectar");
        btnDesconectar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnDesconectar.setContentAreaFilled(false);
        btnDesconectar.setFocusPainted(false);
        btnDesconectar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDesconectar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDesconectarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDesconectarMouseExited(evt);
            }
        });
        jPanel3.add(btnDesconectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 171, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 620, 172, 50));

        jPanel4.setBackground(new java.awt.Color(136, 103, 44));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 60)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Menu Principal");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 580, 80));

        jFondo_App.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoMadera.jpg"))); // NOI18N
        jPanel1.add(jFondo_App, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 680));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDesconectarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDesconectarMouseEntered
        btnDesconectar.setContentAreaFilled(true);
         jAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerrar-sesion.png")));
    }//GEN-LAST:event_btnDesconectarMouseEntered

    private void btnDesconectarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDesconectarMouseExited
        btnDesconectar.setContentAreaFilled(false);
    }//GEN-LAST:event_btnDesconectarMouseExited

    private void btnBeneficiosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBeneficiosMouseExited
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carpeta.png")));
        btnBeneficios.setContentAreaFilled(false);
    }//GEN-LAST:event_btnBeneficiosMouseExited

    private void btnBeneficiosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBeneficiosMouseEntered
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salud.png")));
        btnBeneficios.setContentAreaFilled(true);
    }//GEN-LAST:event_btnBeneficiosMouseEntered

    private void btnUbicacionesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbicacionesMouseExited
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carpeta.png")));
        btnUbicaciones.setContentAreaFilled(false);
    }//GEN-LAST:event_btnUbicacionesMouseExited

    private void btnUbicacionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbicacionesMouseEntered
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mapa.png")));
        btnUbicaciones.setContentAreaFilled(true);
    }//GEN-LAST:event_btnUbicacionesMouseEntered

    private void btnEvaluacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEvaluacionMouseExited
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carpeta.png")));
        btnEvaluacion.setContentAreaFilled(false);
    }//GEN-LAST:event_btnEvaluacionMouseExited

    private void btnEvaluacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEvaluacionMouseEntered
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/igualdad.png")));
        btnEvaluacion.setContentAreaFilled(true);
    }//GEN-LAST:event_btnEvaluacionMouseEntered

    private void btnAlimentosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlimentosMouseExited
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carpeta.png")));
        btnAlimentos.setContentAreaFilled(false);
    }//GEN-LAST:event_btnAlimentosMouseExited

    private void btnAlimentosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlimentosMouseEntered
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bandeja-de-comida.png")));
        btnAlimentos.setContentAreaFilled(true);
    }//GEN-LAST:event_btnAlimentosMouseEntered

    private void btnAdopcionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdopcionMouseExited
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carpeta.png")));
        btnAdopcion.setContentAreaFilled(false);
    }//GEN-LAST:event_btnAdopcionMouseExited

    private void btnAdopcionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdopcionMouseEntered
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/programa.png")));
        btnAdopcion.setContentAreaFilled(true);
    }//GEN-LAST:event_btnAdopcionMouseEntered

    private void btnProduccionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProduccionMouseExited
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carpeta.png")));
        btnProduccion.setContentAreaFilled(false);
    }//GEN-LAST:event_btnProduccionMouseExited

    private void btnProduccionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProduccionMouseEntered
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/siembra.png")));
        btnProduccion.setContentAreaFilled(true);
    }//GEN-LAST:event_btnProduccionMouseEntered

    private void btnCargamentosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCargamentosMouseExited
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carpeta.png")));
        btnCargamentos.setContentAreaFilled(false);
    }//GEN-LAST:event_btnCargamentosMouseExited

    private void btnCargamentosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCargamentosMouseEntered
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cargamento.png")));
        btnCargamentos.setContentAreaFilled(true);
    }//GEN-LAST:event_btnCargamentosMouseEntered

    private void btnAcuerdosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAcuerdosMouseExited
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carpeta.png")));
        btnAcuerdos.setContentAreaFilled(false);
    }//GEN-LAST:event_btnAcuerdosMouseExited

    private void btnAcuerdosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAcuerdosMouseEntered
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/contrato.png")));
        btnAcuerdos.setContentAreaFilled(true);
    }//GEN-LAST:event_btnAcuerdosMouseEntered

    private void btnEmpresasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmpresasMouseEntered
        jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fabrica.png")));
        btnEmpresas.setContentAreaFilled(true);
    }//GEN-LAST:event_btnEmpresasMouseEntered

    private void btnEmpresasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmpresasMouseExited
          jImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carpeta.png")));
        btnEmpresas.setContentAreaFilled(false);
    }//GEN-LAST:event_btnEmpresasMouseExited

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAcuerdos;
    public javax.swing.JButton btnAdopcion;
    public javax.swing.JButton btnAlimentos;
    public javax.swing.JButton btnBeneficios;
    public javax.swing.JButton btnCargamentos;
    public javax.swing.JButton btnDesconectar;
    public javax.swing.JButton btnEmpresas;
    public javax.swing.JButton btnEvaluacion;
    public javax.swing.JButton btnProduccion;
    public javax.swing.JButton btnUbicaciones;
    private javax.swing.JLabel jAtras;
    private javax.swing.JLabel jCasa;
    public javax.swing.JLabel jFondo_App;
    public javax.swing.JLabel jImagenMenu;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public javax.swing.JPanel pBotone;
    // End of variables declaration//GEN-END:variables
}
