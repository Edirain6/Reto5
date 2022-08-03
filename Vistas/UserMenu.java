package Vistas;

import Controlador.*;
import Modelo.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class UserMenu extends javax.swing.JFrame {
    
    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    ResultSet rs;
    DefaultTableModel contenidoTablaEmpleado, contenidoTablaDirecciones;
    ComboBoxModel enumDepartamento, enumZona, enumTipoCalle;
       
    public UserMenu() {     
        enumDepartamento = new DefaultComboBoxModel(EnumDepartamento.values());
        enumZona = new DefaultComboBoxModel(EnumZona.values());
        enumTipoCalle = new DefaultComboBoxModel(EnumTipoCalle.values());
        initComponents();
        this.setLocationRelativeTo(null);
        listarDepartamentos();
        ListarEmpleado();
        
                
    }
    
    private void ListarEmpleado(){
        String filtroBusqueda = txtSearch.getText();
        System.out.println("Buscar todos los empleado con nombre o apellido: "+ filtroBusqueda);
                
        if(filtroBusqueda.isEmpty()){
            String query = "SELECT nombreEmp,apellidos,tipoDocumento,documento,correo, nombreSucursal FROM `empleado INNER JOIN sucursal ON empleado.FK_idSucursal = sucursal.idSucursal";
            try {
                connection = conexion.getConnection();
                st = connection.createStatement();
                rs = st.executeQuery(query);
             
                Object[] empleado = new Object[6];
                contenidoTablaEmpleado = (DefaultTableModel)tblEmpleado.getModel();
                
                while(rs.next()) {
                    empleado[0] = rs.getString("nombreEmp");
                    empleado[1] = rs.getString("apellidos");
                    empleado[2] = rs.getString("tipoDocumento");
                    empleado[3] = rs.getString("documento");
                    empleado[4] = rs.getString("correo");
                    empleado[5] = rs.getString("nombreSucursal");
                    
                    
                    contenidoTablaEmpleado.addRow(empleado);                    
                    tblEmpleado.setModel(contenidoTablaEmpleado);
                }               
            } catch(SQLException e){
               System.out.println("Nose pudo cargar la información del empleado");
            }            
        } else  {
            String query = "SELECT nombreEmp,apellidos,tipoDocumento,documento,correo, nombreSucursal FROM empleado INNER JOIN sucursal WHERE empleado.FK_idSucursal = sucursal.idSucursal AND nombreEmp LIKE '%" + filtroBusqueda + "%' OR apellidos LIKE '%" + filtroBusqueda + "%'";
            System.out.println(query);
            try {
                connection = conexion.getConnection();
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Object[] empleados = new Object[6];
                contenidoTablaEmpleado = (DefaultTableModel) tblEmpleado.getModel();
                while (rs.next()) {
                empleados[0] = rs.getString("nombreEmp");
                empleados[1] = rs.getString("apellidos");
                empleados[2] = rs.getString("tipoDocumento");
                empleados[3] = rs.getString("documento");
                empleados[4] = rs.getString("correo");
                empleados[5] = rs.getString("nombreSucursal");
                contenidoTablaEmpleado.addRow(empleados);
                tblEmpleado.setModel(contenidoTablaEmpleado);
                }
            } catch (SQLException e) {
                System.out.println("Error"); }
            
        }       
    }
    
    public void listarDepartamentos() {
        String filtroDepartamento = txtSearch.getText();
        if (filtroDepartamento.isEmpty()) {
            String query = "SELECT nombreSucursal, nombreDepartamento,CONCAT('Zona ', zona, '.', tipoCalle, ' ', numero1, ' #No. ', numero2, ' - ',"
                    + "numero3 ) As direccion FROM `sucursal` INNER JOIN `direccion` ON FK_idDireccion = idDireccion GROUP BY nombreSucursal,"
                    + "nombreDepartamento ORDER BY nombreDepartamento;";
            try{
                connection = conexion.getConnection();
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Object[] direccion = new Object[3];
                contenidoTablaDirecciones = (DefaultTableModel) tblDepartamentos.getModel();
                while(rs.next()){
                    direccion[0] = rs.getString("nombreSucursal");
                    direccion[1] = rs.getString("nombreDepartamento");
                    direccion[2] = rs.getString("direccion");
                    contenidoTablaDirecciones.addRow(direccion);                    
                    tblDepartamentos.setModel(contenidoTablaDirecciones);
                }            
            }catch (SQLException e){
                System.out.println(e);
            }
        } else {
            String query = "SELECT nombreSucursal, nombreDepartamento,CONCAT('Zona ', zona, '.', tipoCalle, ' ', numero1, ' #No. ', numero2, ' - ',"
                    + "numero3 ) As direccion FROM `sucursal` INNER JOIN `direccion` ON FK_idDireccion = idDireccion AND nombreDepartamento LIKE '%" + filtroDepartamento + "%' GROUP BY nombreSucursal, nombreDepartamento ORDER BY nombreDepartamento;";
            try{
                connection = conexion.getConnection();
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Object[] direccion = new Object[3];
                contenidoTablaDirecciones = (DefaultTableModel) tblDepartamentos.getModel();
                while (rs.next()) {
                    direccion[0] = rs.getString("nombreSucursal");
                    direccion[1] = rs.getString("nombreDepartamento");
                    direccion[2] = rs.getString("direccion");
                    System.out.println("sucursal: " + direccion[0] + ", departamento: " + direccion[1] + ", direccion: " + direccion[2]);
                    contenidoTablaDirecciones.addRow(direccion);
                    tblDepartamentos.setModel(contenidoTablaDirecciones);
                }
            }catch (SQLException e) {
                System.out.println(e);
            }
        }
    }    

    public void eliminarDatosTablaDepartamentos() {
        for (int i = 0; tblDepartamentos.getRowCount() >= i; i++){
            contenidoTablaDirecciones.removeRow(i);
            i -= 1;
        }
        cbDepartamento.setSelectedIndex(0);
        cbTipoCalle.setSelectedIndex(0);
        cbZona.setSelectedIndex(0);
        txtNumero1.setText("");
        txtNumero1.setText("");
        txtNumero1.setText("");
    }        
    public void eliminarDatosTablaEmpleado() {
        for (int i = 0; i < tblEmpleado.getRowCount(); i++) {
            contenidoTablaEmpleado.removeRow(i);
            i -=1;
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleado = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAddUser = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNumero3 = new javax.swing.JTextField();
        txtNumero2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cbDepartamento = new javax.swing.JComboBox<>();
        cbZona = new javax.swing.JComboBox<>();
        cbTipoCalle = new javax.swing.JComboBox<>();
        txtNumero1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDepartamentos = new javax.swing.JTable();
        btnListarEmpleado = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnPuestosTrabajo = new javax.swing.JButton();

        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Zona");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        tblEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido(s)", "Tipo documento", "Documento", "Correo", "Sucursal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpleado);

        jLabel1.setText("Empleados");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Misión TIC 2022");

        btnAddUser.setText("Nuevo");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        jLabel3.setText("Empleado");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddUser))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnAddUser)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(978, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(65, 65, 65))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(124, 124, 124)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Empleado", jPanel1);

        jPanel5.setBackground(new java.awt.Color(218, 217, 217));

        jPanel6.setBackground(new java.awt.Color(234, 234, 234));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Departamento");

        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Zona");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("#");

        txtNumero3.setForeground(new java.awt.Color(51, 51, 51));
        txtNumero3.setBorder(null);

        txtNumero2.setForeground(new java.awt.Color(51, 51, 51));
        txtNumero2.setBorder(null);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("-");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Tipo calle");

        cbDepartamento.setForeground(new java.awt.Color(51, 51, 51));
        cbDepartamento.setModel(enumDepartamento);

        cbZona.setForeground(new java.awt.Color(51, 51, 51));
        cbZona.setModel(enumZona);

        cbTipoCalle.setForeground(new java.awt.Color(51, 51, 51));
        cbTipoCalle.setModel(enumTipoCalle);

        txtNumero1.setForeground(new java.awt.Color(51, 51, 51));
        txtNumero1.setBorder(null);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(cbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel7))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(cbTipoCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNumero1))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(25, 25, 25)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(32, 32, 32)
                        .addComponent(txtNumero2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 73, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumero1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(cbTipoCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txtNumero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNumero3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 596, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblDepartamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sucursal", "Departamento", "Dirección"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDepartamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDepartamentosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDepartamentos);

        btnListarEmpleado.setText("ListarEmp");
        btnListarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarEmpleadoActionPerformed(evt);
            }
        });

        jButton1.setText("Search");

        jLabel5.setText("Departamento");

        btnPuestosTrabajo.setText("PuestosTrabajo");
        btnPuestosTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPuestosTrabajoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(343, 343, 343))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 932, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPuestosTrabajo)
                    .addComponent(btnListarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnPuestosTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        jTabbedPane1.addTab("Sucursales", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        eliminarDatosTablaEmpleado();
        ListarEmpleado();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        AddUserForm addUserForm = new AddUserForm(this, /*rootPaneCheckingEnabled*/true);
        addUserForm.setVisible(true);
        eliminarDatosTablaEmpleado();
        ListarEmpleado();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void tblEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadoMouseClicked
        int row = tblEmpleado.getSelectedRow();
        String nombreEmp = (String) tblEmpleado.getValueAt(row, 0);
        String apellidos = (String) tblEmpleado.getValueAt(row, 1);
        String tipoDocumento= (String) tblEmpleado.getValueAt(row, 2);
        String documento = (String) tblEmpleado.getValueAt(row, 3);
        String correo = (String) tblEmpleado.getValueAt(row, 4);
        String sucursal = (String) tblEmpleado.getValueAt(row, 5);
        System.out.println(nombreEmp + " " + apellidos + " " + tipoDocumento + " " + documento);
        String queryIdEmpleado = "SELECT idEmp from empleado where documento = '" + documento + "';";
        System.out.println(queryIdEmpleado);
        try{
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(queryIdEmpleado);
            while(rs.next()){
                int idEmpleado = rs.getInt("idEmp");
                System.out.println(idEmpleado);
                ShowUserForm showUserForm = new ShowUserForm(this, true);
                showUserForm.recibeDatos(idEmpleado, nombreEmp, apellidos, tipoDocumento,documento, correo, sucursal);
                showUserForm.setVisible(true);

            }
        }catch(SQLException e){
            System.out.println(e);
        }  
        eliminarDatosTablaEmpleado();
        ListarEmpleado();
               
    }//GEN-LAST:event_tblEmpleadoMouseClicked

    private void btnListarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarEmpleadoActionPerformed
        PuestosTrabajo puestosTrabajo = new PuestosTrabajo(this, true);
        puestosTrabajo.setVisible(true);
    }//GEN-LAST:event_btnListarEmpleadoActionPerformed
    
        
    private void tblDepartamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDepartamentosMouseClicked
        int row = tblDepartamentos.getSelectedRow();
        String sucursal = (String) tblDepartamentos.getValueAt(row, 0);
        System.out.println(sucursal);
        String departamento = (String) tblDepartamentos.getValueAt(row, 1);       
        String queryIdSucursal = "SELECT idSucursal FROM `sucursal` inner join `direccion` WHERE FK_idDireccion = idDireccion AND nombreSucursal = '" + sucursal + "'";
        try{
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(queryIdSucursal);
            while (rs.next()){
                int idSucursal = rs.getInt("idSucursal");
                GestionarSucursalForm gestionarSucursal = new GestionarSucursalForm(this, true);
                gestionarSucursal.recibirDatosSucursal(idSucursal);
                gestionarSucursal.setVisible(true);
                eliminarDatosTablaDepartamentos();
                listarDepartamentos();
                
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_tblDepartamentosMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        eliminarDatosTablaEmpleado();
        ListarEmpleado();
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String departamento = cbDepartamento.getSelectedItem().toString();
        String zona = cbZona.getSelectedItem().toString();
        String tipoCalle = cbTipoCalle.getSelectedItem().toString();
        String numero1 = txtNumero1.getText();
        String numero2 = txtNumero2.getText();
        String numero3 = txtNumero3.getText();
        //String query = "INSERT INTO `direccion`( `nombreDepartamento`, `zona`, `tipoCalle`, `numero1`, `numero2`, `numero3`) VALUES('"+ departamento +"', '"+zona +"','"+ tipoCalle +"','"+ numero1 +"','"+ numero2 +"','"+ numero3 +"';)";
        if (departamento != "Seleccionaunaopción" && tipoCalle != "Seleccionaunaopción" && zona != "Seleccionaunaopción" && numero1.isEmpty() && numero2.isEmpty() && numero3.isEmpty()){
            SucursalForm sucursalForm = new SucursalForm(this, true);
            sucursalForm.setVisible(true);
            sucursalForm.recibirDatosDireccion(departamento, zona, tipoCalle, numero1, numero2, numero3);
            eliminarDatosTablaDepartamentos();
            listarDepartamentos();
        } else{
            JOptionPane.showMessageDialog(this,"FAltan campos por diligenciar", "Direccion", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnPuestosTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPuestosTrabajoActionPerformed
        PuestosTrabajo puestosTrabajo = new PuestosTrabajo(this, true);
        
    }//GEN-LAST:event_btnPuestosTrabajoActionPerformed
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserMenu().setVisible(true);
            }
        });
    }    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListarEmpleado;
    private javax.swing.JButton btnPuestosTrabajo;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbDepartamento;
    private javax.swing.JComboBox<String> cbTipoCalle;
    private javax.swing.JComboBox<String> cbZona;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblDepartamentos;
    private javax.swing.JTable tblEmpleado;
    private javax.swing.JTextField txtNumero1;
    private javax.swing.JTextField txtNumero2;
    private javax.swing.JTextField txtNumero3;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
    
