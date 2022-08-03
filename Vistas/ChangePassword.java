
package Vistas;

import javax.swing.JOptionPane;
import Modelo.*;
import java.sql.Connection;
import java.sql.ResultSet;
/*import java.sql.SQLException;*/
import java.sql.Statement;


public class ChangePassword extends javax.swing.JDialog {
    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    ResultSet rs;

    
    public ChangePassword(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtNewpPassword = new javax.swing.JLabel();
        txtRepeatpPassword = new javax.swing.JLabel();
        btnSavePassword = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtNewPassword = new javax.swing.JPasswordField();
        txtRepeatPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        txtNewpPassword.setForeground(new java.awt.Color(0, 0, 0));
        txtNewpPassword.setText("Contraseña nueva");

        txtRepeatpPassword.setForeground(new java.awt.Color(0, 0, 0));
        txtRepeatpPassword.setText("Repetir contraseña");

        btnSavePassword.setIcon(new javax.swing.ImageIcon("D:\\Escritorio\\PracticaEdison\\Java_Projects\\Reto1_G56\\src\\main\\java\\Imagenes\\confirmIcon.png")); // NOI18N
        btnSavePassword.setText("Guardar");
        btnSavePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavePasswordActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon("D:\\Escritorio\\PracticaEdison\\Java_Projects\\Reto1_G56\\src\\main\\java\\Imagenes\\cancel2con.png")); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtNewPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtNewPassword.setForeground(new java.awt.Color(51, 51, 51));
        txtNewPassword.setBorder(null);
        txtNewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNewPasswordActionPerformed(evt);
            }
        });

        txtRepeatPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtRepeatPassword.setForeground(new java.awt.Color(51, 51, 51));
        txtRepeatPassword.setBorder(null);
        txtRepeatPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRepeatPasswordActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\Escritorio\\PracticaEdison\\Java_Projects\\Reto1_G56\\src\\main\\java\\Imagenes\\LogoMin.png")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 84, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(txtNewpPassword))
                            .addComponent(txtRepeatpPassword, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRepeatPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(111, 111, 111))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSavePassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(98, 98, 98))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewpPassword))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRepeatPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRepeatpPassword))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSavePassword)
                    .addComponent(btnCancelar))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSavePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePasswordActionPerformed
        String nuevaContraseña = txtNewPassword.getText();
        String repetirContraseña = txtRepeatPassword.getText();
        if(nuevaContraseña.equals(repetirContraseña)){
            JOptionPane.showMessageDialog(this,"Cambio exitoso", "Actualización de Datos", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }else {
            JOptionPane.showMessageDialog(this, "Contraseñas no coinciden", "Actualización de datos", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Contraseña nueva: ");
        this.dispose();
    }//GEN-LAST:event_btnSavePasswordActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewPasswordActionPerformed
        String nuevaContraseña = txtNewpPassword.getText();
        String repetirContraseña = txtRepeatpPassword.getText();
        if(nuevaContraseña.isEmpty() || repetirContraseña.isEmpty()){
            JOptionPane.showMessageDialog(this, "Hay campos sin diligenciar", "Cambio de contraseña", JOptionPane.WARNING_MESSAGE);
        }else {
            if(nuevaContraseña.equals(repetirContraseña)){
                JOptionPane.showMessageDialog(this, "Cambio exitoso", "Actualización de datos", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Contraseñas no coinciden", "Actualización de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
        System.out.println("Contraseña nueva: ");
    }//GEN-LAST:event_txtNewPasswordActionPerformed

    private void txtRepeatPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRepeatPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRepeatPasswordActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChangePassword dialog = new ChangePassword(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSavePassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtNewPassword;
    private javax.swing.JLabel txtNewpPassword;
    private javax.swing.JPasswordField txtRepeatPassword;
    private javax.swing.JLabel txtRepeatpPassword;
    // End of variables declaration//GEN-END:variables
}
