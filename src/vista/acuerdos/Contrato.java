/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.acuerdos;

import controlador.controllerLogin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author jose-
 */
public class Contrato extends javax.swing.JFrame {

    /**
     * Creates new form Contrato
     */
    int id, id_cliente, id_productor;
    String part1, part2, part3, part4;
    
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public Contrato() {
        initComponents();
    setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoApp.jpg")).getImage());
    }

    public void llenarContrato(){
        cbContrato.removeAllItems();
        cbContratoEli.removeAllItems();
        cbContrato.addItem(" ");
        cbContratoEli.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery(" select cli.denominacion_comercial ||' / '|| prod.nombre as texto "
                    + " from aja_cliente cli, aja_contrato con, aja_productor prod where con.id_productor= prod.id and con.id_cliente= cli.id "
                    + " order by con.id desc");
            while ( rs.next() ) {
                String registro = rs.getString(1);
                cbContrato.addItem(registro);
                cbContratoEli.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void llenarT(){
        cbTransporte.removeAllItems();
        cbTransporte.addItem(" ");
        cbTransporte.addItem("Aereo");
        cbTransporte.addItem("Terrestre");
        cbTransporte.addItem("Maritimo");        
    }
    
    public void llenarCliente(){
        cbCliente.removeAllItems();
        cbCliente.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "select ci.denominacion_comercial"
                    + " from aja_cliente ci "
                    + " order by 1 desc" );
            while ( rs.next() ) {
            String registro = rs.getString(1);
            cbCliente.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void llenarProd(){
        cbProductor.removeAllItems();
        cbProductor.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "Select prod.nombre  "
                    + " from aja_productor prod "
                    + "order by 1 desc" );
            while ( rs.next() ) {
                String registro = rs.getString(1);
                cbProductor.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void llenarMetodo(int myId){
        cbMetodo.removeAllItems();
        cbMetodo.addItem(" ");
        try {
            Statement stmt = controllerLogin.conexion.createStatement();
            ResultSet rs = stmt.executeQuery( "Select met.tipo  "
                    + " from AJA_METODO_DE_PAGO met, aja_contrato con "
                    + " where con.ID_metodo_pago=met.id and con.ID_productor_metodo_pago= met.id_productor and con.id=" +myId+" " );
            while ( rs.next() ) {
                String registro = rs.getString(1);
                cbMetodo.addItem(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void cortarCB( javax.swing.JComboBox combo ){
        String sb1  = combo.getSelectedItem().toString();
        String[] parts = sb1.split(" / ");
        part1= parts[0];//cliente
        part2 = parts[1];//productor
    }
    
    public void limpiar(){
        dateFirma.setDate(null);
        cbTransporte.setSelectedIndex(0);
        txtEstatus.setText(null);
        txtPrecio.setText(null);
        txtDescuento.setText(null);
        txtRazon.setText(null);
        dateCancela.setDate(null);
        cbCliente.setSelectedIndex(0);
        cbProductor.setSelectedIndex(0);
    }
    
    public void insert(){
        try {
           cortarCB(cbContrato);
           Statement stmt = controllerLogin.conexion.createStatement();
           String sql = " INSERT INTO aja_contrato " 
                   + " (id_cliente, id_productor, fecha_firma, tipo, estatus, precio_contrato, porcentaje_descuento, "
                   + " id_metodo_pago, id_productor_metodo_pago, fecha_cancelacion, razon_cancelacion) "
                   + " VALUES "
                   + " ( (select cli.id from aja_cliente cli where cli.denominacion_comercial='"+cbCliente.getSelectedItem().toString()+"' ), "
                   + " (select prod.id from aja_productor prod where prod.nombre='"+cbProductor.getSelectedItem().toString()+"' ), "
                   + " '"+format.format(dateFirma.getDate()) +"', "
                   + " '"+cbTransporte.getSelectedItem().toString().charAt(1)+"', "
                   + " '"+txtEstatus.getText()+"', "
                   + " "+txtPrecio.getText()+", "
                   + " "+txtDescuento.getText()+", "
                   + " " +cbMetodo.getSelectedItem().toString()+", "
                   + " (select prod.id from aja_productor prod where prod.nombre='"+cbProductor.getSelectedItem().toString()+"' ), "
                   + " '"+format.format(dateFirma.getDate()) +"', "
                   + " '"+txtRazon.getText()+"' );";
           
                   int flag=0;
/*
                   ResultSet rs = stmt.executeQuery( "" );
                   while ( rs.next() ) {
                       if( true ){///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                           JOptionPane.showMessageDialog(null, "El cultivo que intento registrar ya existe");
                           flag=1;
                           break;
                       }
                    }*/
                   if(flag==0){
                           stmt.executeUpdate(sql);
                           controllerLogin.conexion.commit();
                           JOptionPane.showMessageDialog(null,"Se ha registrado exitosamente");
                           llenarContrato();
                           limpiar();
                       }

                     } catch (SQLException e) {
                           e.printStackTrace();
                   }
    }
    
    public void delete(){
        try{
                Statement stmt = controllerLogin.conexion.createStatement();
                String sql = "DELETE FROM aja_contrato where  id = " + id;

                ResultSet rs = stmt.executeQuery( "select id from aja_contrato" );

              int flag=0;

              while ( rs.next() ) {
                   if(rs.getInt("id") == id){
                       stmt.executeUpdate(sql);
                       controllerLogin.conexion.commit();
                       JOptionPane.showMessageDialog(null,"Se ha borrado el contrato exitosamente");
                       llenarContrato();
                       limpiar();
                       flag = 1;
                       break;
                   }
                }
              if(flag == 0){
                  JOptionPane.showMessageDialog(null,"No se encontro el contrato");
              }
           }catch (SQLException e) {
               e.printStackTrace();
           }     
    }
    
    public void update(){
        try{
            Statement stmt = controllerLogin.conexion.createStatement();
            String sql = "Update aja_contrato " 
                    + " set "
                    + " id_cliente = (select cli.id from aja_cliente cli where cli.denominacion_comercial='"+cbCliente.getSelectedItem().toString()+"' ), "
                    + " id_productor =(select prod.id from aja_productor prod where prod.nombre='"+cbProductor.getSelectedItem().toString()+"' ), "
                    + " fecha_firma= '"+format.format(dateFirma.getDate()) +"', "
                    + " estatus = '" +txtEstatus.getText()+"', "
                    + " precio_contrato = "+txtPrecio.getText()+", ";
           if(!txtDescuento.getText().equals(""))         
                    sql+= " porcentaje_descuento=  "+txtDescuento.getText()+", ";
           
           if(!txtRazon.getText().equals("") && !format.format(dateFirma.getDate()).equals(""))
                sql+= " razon_cancelacion =  '"+txtRazon.getText()+"', "
                    + " fecha_cancelacion ="+ " '"+format.format(dateFirma.getDate()) +"', ";
           
            sql+= " id_metodo_pago= (select id from aja_metodo_de_pago where tipo='" +cbMetodo.getSelectedItem().toString()+"'), "
                    + " id_productor_metodo_pago ="+ " (select prod.id from aja_productor prod where prod.nombre='"+cbProductor.getSelectedItem().toString()+"' ), "
                    +" tipo ='"+cbTransporte.getSelectedItem().toString().charAt(0)
                    + "' where id= "+id;
            int flag=0;
            /*ResultSet rs = stmt.executeQuery( "" );
            while ( rs.next() ) {
                if( true ){///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    JOptionPane.showMessageDialog(null, "El cultivo que intento registrar ya existe");
                    flag=1;
                    break;
                }
             } */
            if(flag==0){
                    stmt.executeUpdate(sql);
                    controllerLogin.conexion.commit();
                   JOptionPane.showMessageDialog(null,"Se modifico el registro con exito");
                    llenarContrato();
                    limpiar();
                }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        
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
        btnRegresar = new javax.swing.JButton();
        txtEstatus = new javax.swing.JTextField();
        dateFirma = new com.toedter.calendar.JDateChooser();
        btnInsert = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cbContrato = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        txtVarExp = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cbContratoEli = new javax.swing.JComboBox<>();
        btnDelete = new javax.swing.JButton();
        txtVarExpEli = new javax.swing.JLabel();
        txtProd1 = new javax.swing.JLabel();
        txtProd2 = new javax.swing.JLabel();
        txtProd3 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        dateCancela = new com.toedter.calendar.JDateChooser();
        txtProd4 = new javax.swing.JLabel();
        cbTransporte = new javax.swing.JComboBox<>();
        txtProd5 = new javax.swing.JLabel();
        txtProd6 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        txtProd7 = new javax.swing.JLabel();
        txtRazon = new javax.swing.JTextField();
        txtProd8 = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        txtProd9 = new javax.swing.JLabel();
        cbProductor = new javax.swing.JComboBox<>();
        txtProd10 = new javax.swing.JLabel();
        cbMetodo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");

        txtEstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstatusActionPerformed(evt);
            }
        });

        btnInsert.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnInsert.setForeground(new java.awt.Color(0, 0, 0));
        btnInsert.setText("Insertar");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setToolTipText("");

        cbContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbContratoActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");

        txtVarExp.setBackground(new java.awt.Color(0, 0, 0));
        txtVarExp.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtVarExp.setForeground(new java.awt.Color(255, 255, 255));
        txtVarExp.setText("Contrato entre:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtVarExp)
                        .addGap(0, 117, Short.MAX_VALUE))
                    .addComponent(cbContrato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(txtVarExp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        cbContratoEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbContratoEliActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Eliminar");

        txtVarExpEli.setBackground(new java.awt.Color(0, 0, 0));
        txtVarExpEli.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtVarExpEli.setForeground(new java.awt.Color(255, 255, 255));
        txtVarExpEli.setText("Contrato entre:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtVarExpEli)
                        .addGap(0, 114, Short.MAX_VALUE))
                    .addComponent(cbContratoEli, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(txtVarExpEli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbContratoEli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        txtProd1.setBackground(new java.awt.Color(0, 0, 0));
        txtProd1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd1.setForeground(new java.awt.Color(255, 255, 255));
        txtProd1.setText("Fecha de firma:");

        txtProd2.setBackground(new java.awt.Color(0, 0, 0));
        txtProd2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd2.setForeground(new java.awt.Color(255, 255, 255));
        txtProd2.setText("Estatus:");

        txtProd3.setBackground(new java.awt.Color(0, 0, 0));
        txtProd3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd3.setForeground(new java.awt.Color(255, 255, 255));
        txtProd3.setText("Precio del contrato ($):");

        txtPrecio.setPreferredSize(new java.awt.Dimension(64, 25));

        txtProd4.setBackground(new java.awt.Color(0, 0, 0));
        txtProd4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd4.setForeground(new java.awt.Color(255, 255, 255));
        txtProd4.setText("Fecha cancelacion:");

        cbTransporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTransporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTransporteActionPerformed(evt);
            }
        });

        txtProd5.setBackground(new java.awt.Color(0, 0, 0));
        txtProd5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd5.setForeground(new java.awt.Color(255, 255, 255));
        txtProd5.setText("Tipo de transporte:");

        txtProd6.setBackground(new java.awt.Color(0, 0, 0));
        txtProd6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd6.setForeground(new java.awt.Color(255, 255, 255));
        txtProd6.setText("Porcentaje de descuento:");

        txtProd7.setBackground(new java.awt.Color(0, 0, 0));
        txtProd7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd7.setForeground(new java.awt.Color(255, 255, 255));
        txtProd7.setText("Razon de cancelacion:");

        txtProd8.setBackground(new java.awt.Color(0, 0, 0));
        txtProd8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd8.setForeground(new java.awt.Color(255, 255, 255));
        txtProd8.setText("Cliente:");

        cbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtProd9.setBackground(new java.awt.Color(0, 0, 0));
        txtProd9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd9.setForeground(new java.awt.Color(255, 255, 255));
        txtProd9.setText("Productor:");

        cbProductor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtProd10.setBackground(new java.awt.Color(0, 0, 0));
        txtProd10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProd10.setForeground(new java.awt.Color(255, 255, 255));
        txtProd10.setText("Metodo de pago:");

        cbMetodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btnRegresar))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtProd1)
                                .addComponent(txtProd2)
                                .addComponent(txtEstatus)
                                .addComponent(dateFirma, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtProd3)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProd5)
                            .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProd8)
                            .addComponent(cbMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProd10))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtProd7)
                            .addComponent(dateCancela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtProd4)
                            .addComponent(txtProd6)
                            .addComponent(txtDescuento)
                            .addComponent(txtRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProd9)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtProd8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtProd9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbProductor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtProd1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtProd6)
                        .addGap(3, 3, 3)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateFirma, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtProd5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtProd2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtProd7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtProd3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtProd4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateCancela, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtProd10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstatusActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInsertActionPerformed

    private void cbContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbContratoActionPerformed
        if ( cbContrato.getItemCount() > 1 ){
            if (!cbContrato.getSelectedItem().toString().equals(" ")){
                cbContratoEli.setEnabled(false);
                btnModificar.setEnabled(true);
                btnInsert.setEnabled(false);
                cortarCB(cbContrato );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(" select con.id, con.fecha_firma, con.tipo, con.estatus, con.precio_contrato, con.porcentaje_descuento, con.razon_cancelacion, con.fecha_cancelacion, cli.denominacion_comercial, prod.nombre "
                            + " from aja_cliente cli, aja_contrato con, aja_productor prod "
                            + " where cli.denominacion_comercial= '"+ part1.trim() +"' "
                            + " and prod.nombre = '"+ part2.trim() +"' ");
                        while( rs.next() ){
                            id = rs.getInt(1);
                            dateFirma.setDate(rs.getDate(2));
                            String  tipo= rs.getString(3);
                            String estatus = rs.getString(4);
                            String precio = rs.getString(5);
                            String descuento = rs.getString(6);
                            String cancela = rs.getString(7);
                            dateCancela.setDate(rs.getDate(8));
                            String cli = rs.getString(9);
                            String prod = rs.getString(10);
                            if(tipo.equals("A"))
                                cbTransporte.setSelectedItem("Aereo");
                            else if(tipo.equals("T"))    
                                cbTransporte.setSelectedItem("Terrestre");
                            else         
                                cbTransporte.setSelectedItem("Maritimo");
                            txtEstatus.setText(estatus);
                            txtPrecio.setText(precio);
                            txtDescuento.setText(descuento);
                            txtRazon.setText(cancela);
                            cbCliente.setSelectedItem(cli);
                            cbProductor.setSelectedItem(prod);
                        }
                        llenarMetodo(id);
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }else{
                btnModificar.setEnabled(false);
                btnInsert.setEnabled(true);
                cbContratoEli.setEnabled(true);
                limpiar();
            }
        }    
    }//GEN-LAST:event_cbContratoActionPerformed

    private void cbContratoEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbContratoEliActionPerformed
      if ( cbContrato.getItemCount() > 1 ){
            if (!cbContrato.getSelectedItem().toString().equals(" ")){
                cbContratoEli.setEnabled(false);
                btnModificar.setEnabled(true);
                btnInsert.setEnabled(false);
                cortarCB(cbContrato );
                try {
                    Statement stmt = controllerLogin.conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(" Select con.id "
                            + " from aja_cliente cli, aja_contrato con, aja_productor prod "
                            + " where cli.denominacion_comercial= '"+ part1.trim() +"' "
                            + " and prod.nombre = '"+ part2.trim() +"' ");
                        while( rs.next() ){
                            id = rs.getInt(1);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            }
        }    
    }//GEN-LAST:event_cbContratoEliActionPerformed

    private void cbTransporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTransporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTransporteActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnInsert;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JComboBox<String> cbCliente;
    public javax.swing.JComboBox<String> cbContrato;
    public javax.swing.JComboBox<String> cbContratoEli;
    public javax.swing.JComboBox<String> cbMetodo;
    public javax.swing.JComboBox<String> cbProductor;
    public javax.swing.JComboBox<String> cbTransporte;
    public com.toedter.calendar.JDateChooser dateCancela;
    public com.toedter.calendar.JDateChooser dateFirma;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JTextField txtDescuento;
    public javax.swing.JTextField txtEstatus;
    public javax.swing.JTextField txtPrecio;
    private javax.swing.JLabel txtProd1;
    private javax.swing.JLabel txtProd10;
    private javax.swing.JLabel txtProd2;
    private javax.swing.JLabel txtProd3;
    private javax.swing.JLabel txtProd4;
    private javax.swing.JLabel txtProd5;
    private javax.swing.JLabel txtProd6;
    private javax.swing.JLabel txtProd7;
    private javax.swing.JLabel txtProd8;
    private javax.swing.JLabel txtProd9;
    public javax.swing.JTextField txtRazon;
    private javax.swing.JLabel txtVarExp;
    private javax.swing.JLabel txtVarExpEli;
    // End of variables declaration//GEN-END:variables
}
