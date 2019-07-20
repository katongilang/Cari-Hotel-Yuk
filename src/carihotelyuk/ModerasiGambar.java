/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carihotelyuk;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Katon Gilang
 */
public class ModerasiGambar extends javax.swing.JFrame {

    /**
     * Creates new form ModerasiGambar
     */
    public ModerasiGambar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jtabGambar = new javax.swing.JTable();
        btnTolakGambar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Setujui = new javax.swing.JButton();
        btnLihat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jtabGambar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Path Gambar", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jtabGambar);

        btnTolakGambar.setText("Tolak");
        btnTolakGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTolakGambarActionPerformed(evt);
            }
        });

        jLabel3.setText("DAFTAR GAMBAR YANG MENUNGGU MODERASI");

        Setujui.setText("Setujui");
        Setujui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetujuiActionPerformed(evt);
            }
        });

        btnLihat.setText("Perview Gambar");
        btnLihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLihatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addGap(111, 178, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLihat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTolakGambar)
                        .addGap(18, 18, 18)
                        .addComponent(Setujui))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Setujui)
                    .addComponent(btnTolakGambar)
                    .addComponent(btnLihat))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTolakGambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTolakGambarActionPerformed
        int dialogResult=JOptionPane.showOptionDialog(null,"Apakah anda yakin menghapus path gambar hotel ini?","Konfirmasi Hapus path gambar",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(dialogResult==JOptionPane.YES_OPTION){
            int rowIdx=jtabGambar.getSelectedRow();
            int idGambar=Integer.parseInt(jtabGambar.getValueAt(rowIdx,0).toString());
            String sql="DELETE FROM gambar WHERE id_gambar="+idGambar;
            try{
                Connection con=ModuleDB.connectDB();
                Statement stmt=con.createStatement();
                stmt.executeUpdate(sql);
                showMessageDialog(null,"Path Gambar berhasil dihapus!");
                daftarGambar();
            }
            catch(SQLException e){
                showMessageDialog(null,e.getMessage());
            }
        }
    }//GEN-LAST:event_btnTolakGambarActionPerformed

    private void daftarGambar(){
        try{
            String sql="SELECT h.id_hotel,g.id_gambar, g.id_hotel,g.path_gambar,g.status_moderasi FROM hotel h, gambar g WHERE g.id_hotel=h.id_hotel AND g.status_moderasi='waiting'";
            Connection con=ModuleDB.connectDB();
            Statement stmt=con.createStatement();
            ResultSet result=stmt.executeQuery(sql);
            DefaultTableModel model=(DefaultTableModel)jtabGambar.getModel();
            model.setRowCount(0);
            while(result.next()){
                Vector row=new Vector();
                row.add(result.getString("id_gambar"));
                row.add(result.getString("path_gambar"));
                row.add(result.getString("status_moderasi"));
                model.addRow(row);
            }
            con.close();
            jtabGambar.setModel(model);
        }
        catch(SQLException e){
            showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setLocationRelativeTo(null);
        daftarGambar();
    }//GEN-LAST:event_formWindowOpened

    private void SetujuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetujuiActionPerformed
        int rowIdx=jtabGambar.getSelectedRow();
        int idGambar=Integer.parseInt(jtabGambar.getValueAt(rowIdx,0).toString());
        String status= "approved";
        try{
            Connection con=ModuleDB.connectDB();
            Statement stmt=con.createStatement();
            String sql="UPDATE gambar SET status_moderasi='"+status+"' WHERE id_gambar='"+idGambar+"'";
            stmt.executeUpdate(sql);
            showMessageDialog(null,"Gambar telah di APPROVE!");
            daftarGambar();
            con.close();
            
        }
        catch(SQLException e){
            showMessageDialog(null,e.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SetujuiActionPerformed

    private void btnLihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatActionPerformed
       // perviewGambar();
        int rowIdx=jtabGambar.getSelectedRow();
        int idGambar=Integer.parseInt(jtabGambar.getValueAt(rowIdx,0).toString());
        
        PerviewGambar l=new PerviewGambar(this,idGambar);
        l.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        l.setVisible(true);
    }//GEN-LAST:event_btnLihatActionPerformed

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
            java.util.logging.Logger.getLogger(ModerasiGambar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModerasiGambar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModerasiGambar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModerasiGambar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModerasiGambar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Setujui;
    private javax.swing.JButton btnLihat;
    private javax.swing.JButton btnTolakGambar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtabGambar;
    // End of variables declaration//GEN-END:variables
}