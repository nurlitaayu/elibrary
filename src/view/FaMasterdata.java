/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anonim
 */
public class FaMasterdata extends javax.swing.JFrame {

    /**
     * Creates new form FaMasterdata
     */
    public FaMasterdata() {
        initComponents();
        
        load_buku();
        load_anggota();
    }
    
    //load data dari Database tbl_anggota
    private void load_buku() {
        Object header[] = {"ID BUKU", "JUDUL BUKU", "PENGARANG", "PENERBIT", "TAHUN TERBIT"};
        DefaultTableModel data = new DefaultTableModel(null, header);
        bookTable.setModel(data);
        String sql_data = "SELECT * FROM tbl_buku";
        try {
            Connection kon = koneksi.getConnection();
            Statement st = kon.createStatement();
            ResultSet rs = st.executeQuery(sql_data);
            while (rs.next()) {
                String d1 = rs.getString(1);
                String d2 = rs.getString(2);
                String d3 = rs.getString(3);
                String d4 = rs.getString(4);
                String d5 = rs.getString(5);

                String d[] = {d1, d2, d3, d4, d5};
                data.addRow(d);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //Read data dari Database tbl_anggota---------------------------------------
    private void load_anggota(){
        Object header[] = {"ID ANGGOTA","NIS","NAMA ANGGOTA","JK","TINGKAT","JURUSAN","NO HP","STATUS"};
        DefaultTableModel tableModel = new DefaultTableModel(null, header);
        TabelAnggota.setModel(tableModel);

        String sql_data="SELECT * FROM tbl_anggota";
        try{
            Connection kon = koneksi.getConnection();
            Statement st=kon.createStatement();
            ResultSet rs=st.executeQuery(sql_data);
            while(rs.next()){
                String d1=rs.getString(1);
                String d2=rs.getString(2);
                String d3=rs.getString(3);
                String d4=rs.getString(4);
                String d5=rs.getString(5);
                String d6=rs.getString(6);
                String d7=rs.getString(7);
                String d8=rs.getString(8);
                
                String d[]={d1,d2,d3,d4,d5,d6,d7,d8};
                tableModel.addRow(d);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
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
        scroolPane = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelAnggota = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        MasterData = new javax.swing.JLabel();
        bDashboard = new javax.swing.JLabel();
        bLogout = new javax.swing.JLabel();
        bDataPinjam = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        bookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        bookTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookTableMouseClicked(evt);
            }
        });
        scroolPane.setViewportView(bookTable);

        TabelAnggota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelAnggotaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelAnggota);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Data Anggota");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Data Buku");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scroolPane, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroolPane, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 120, 886, 442));

        MasterData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MasterDataMouseClicked(evt);
            }
        });
        getContentPane().add(MasterData, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 281, 236, 58));

        bDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDashboardMouseClicked(evt);
            }
        });
        getContentPane().add(bDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 163, 236, 58));

        bLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bLogoutMouseClicked(evt);
            }
        });
        getContentPane().add(bLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 384, 236, 58));

        bDataPinjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDataPinjamMouseClicked(evt);
            }
        });
        getContentPane().add(bDataPinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 222, 236, 58));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mKepalaMaster.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1181, 590));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MasterDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MasterDataMouseClicked
        new FaMasterdata().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MasterDataMouseClicked

    private void bDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDashboardMouseClicked
        new FaDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDashboardMouseClicked

    private void bLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bLogoutMouseClicked
        new FLogin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bLogoutMouseClicked

    private void bDataPinjamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDataPinjamMouseClicked
        new FaDatapinjam().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDataPinjamMouseClicked

    private void bookTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookTableMouseClicked

    }//GEN-LAST:event_bookTableMouseClicked

    private void TabelAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelAnggotaMouseClicked

    }//GEN-LAST:event_TabelAnggotaMouseClicked

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
            java.util.logging.Logger.getLogger(FaMasterdata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FaMasterdata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FaMasterdata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FaMasterdata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FaMasterdata().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MasterData;
    private javax.swing.JTable TabelAnggota;
    private javax.swing.JLabel bDashboard;
    private javax.swing.JLabel bDataPinjam;
    private javax.swing.JLabel bLogout;
    private javax.swing.JTable bookTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane scroolPane;
    // End of variables declaration//GEN-END:variables
}