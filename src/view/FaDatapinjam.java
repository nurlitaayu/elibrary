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
public class FaDatapinjam extends javax.swing.JFrame {

    /**
     * Creates new form FaDatapinjam
     */
    public FaDatapinjam() {
        initComponents();
        
        load_Pinjam();
        load_kembali();
    }
    
    //Load Data Pinjam
    private void load_Pinjam(){
        Object header[] = {"Id Pinjam","Nama Peminjam","Judul Buku","Tangal Pinjam","Tanggal Kembali"};
        DefaultTableModel tableModel = new DefaultTableModel(null, header);
        TABELPINJAM.setModel(tableModel);

        String sql_data="SELECT * FROM tbl_peminjaman";
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

                String d[]={d1,d2,d3,d4,d5};
                tableModel.addRow(d);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
           }
    }
    //Load Data Pinjam
    private void load_kembali(){
        Object header[] = {"Id Pinjam","Nama Peminjam","Judul Buku","Tangal Pinjam","Tanggal Kembali"};
        DefaultTableModel tableModel = new DefaultTableModel(null, header);
        TABELKEMBALI.setModel(tableModel);

        String sql_data="SELECT * FROM tbl_kembali";
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

                String d[]={d1,d2,d3,d4,d5};
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
        jScrollPane1 = new javax.swing.JScrollPane();
        TABELPINJAM = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TABELKEMBALI = new javax.swing.JTable();
        bDashboard = new javax.swing.JLabel();
        bDataPinjam = new javax.swing.JLabel();
        MasterData = new javax.swing.JLabel();
        bLogout = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        TABELPINJAM.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TABELPINJAM);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Daftar Pinjam");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Daftar Kembali");

        TABELKEMBALI.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(TABELKEMBALI);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel8)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 120, 885, 440));

        bDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDashboardMouseClicked(evt);
            }
        });
        getContentPane().add(bDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 163, 236, 58));

        bDataPinjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDataPinjamMouseClicked(evt);
            }
        });
        getContentPane().add(bDataPinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 222, 236, 58));

        MasterData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MasterDataMouseClicked(evt);
            }
        });
        getContentPane().add(MasterData, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 281, 236, 58));

        bLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bLogoutMouseClicked(evt);
            }
        });
        getContentPane().add(bLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 384, 236, 58));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mKepalaPeminjaman.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1181, 590));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDashboardMouseClicked
        new FaDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDashboardMouseClicked

    private void bDataPinjamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDataPinjamMouseClicked
        new FaDatapinjam().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDataPinjamMouseClicked

    private void MasterDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MasterDataMouseClicked
        new FaMasterdata().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MasterDataMouseClicked

    private void bLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bLogoutMouseClicked
        new FLogin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bLogoutMouseClicked

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
            java.util.logging.Logger.getLogger(FaDatapinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FaDatapinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FaDatapinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FaDatapinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FaDatapinjam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MasterData;
    private javax.swing.JTable TABELKEMBALI;
    private javax.swing.JTable TABELPINJAM;
    private javax.swing.JLabel bDashboard;
    private javax.swing.JLabel bDataPinjam;
    private javax.swing.JLabel bLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}