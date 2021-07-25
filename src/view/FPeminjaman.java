/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anonim
 */
public class FPeminjaman extends javax.swing.JFrame {

    /**
     * Creates new form FPeminjaman
     */
    public FPeminjaman() {
        initComponents();
        
        load_data();
        IDOtomatis();
        sellectName();
        sellectBook();
        tpinjam();
    }
    
    public void sellectName(){
        try{
            Connection kon = koneksi.getConnection();
            Statement st = kon.createStatement();
            String sql = "Select nama from tbl_anggota";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                dAnggota.addItem(rs.getString("nama"));
                String query = "Select judul_buku from tbl_buku";
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void sellectBook(){
        try{
            Connection kon = koneksi.getConnection();
            Statement st = kon.createStatement();
            String query = "Select judul_buku from tbl_buku";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                dBuku.addItem(rs.getString("judul_buku"));
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void tpinjam(){
        Date ys = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        tPinjam.setText(s.format(ys));
    }
    
    //Create data dari Database tbl_anggota
    private void input_data() {
        
        try {
            Connection kon = koneksi.getConnection();
            Statement st = kon.createStatement();
            //untuk nama peminjman
           String tkembali = ((JTextField)tKembali.getDateEditor().getUiComponent()).getText();
           String sql = "INSERT INTO tbl_peminjaman values('" + IDPINJAM.getText()
                    + "','" + dAnggota.getSelectedItem()
                    + "','" + dBuku.getSelectedItem()
                    + "','" + tPinjam.getText()
                    + "','" + tkembali
                    + "')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Anggota Berhasil Dimasukkan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //Read data dari Database tbl_anggota---------------------------------------
    private void load_data(){
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
    
    //getTanggal
    public static Date getTanggalFromtable(JTable table, int kolom){
        JTable tabel = table;
        
        String str_tgl = String.valueOf(tabel.getValueAt(tabel.getSelectedRow(),kolom));
        Date tanggal = null;
        
        try{
            tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(str_tgl);
        }catch(ParseException ex){
            Logger.getLogger(FPeminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tanggal;
    }
    
    //ID Pinjam Otomatis
    private void IDOtomatis() {
        try {
            Connection kon = koneksi.getConnection();
            Statement st = kon.createStatement();
            String sql_id = "SELECT * FROM tbl_peminjaman order by id_peminjaman desc";
            ResultSet rs = st.executeQuery(sql_id);
            if(rs.next()) {
                String id_peminjaman = rs.getString("id_peminjaman").substring(1);
                String AN = "" + (Integer.parseInt(id_peminjaman) + 1);
                String Nol = "";
                if (AN.length() == 1){ //jika id_anggota A00001
                    Nol = "0000";
                }else if (AN.length() == 2){ //jika id_anggota A00010
                    Nol = "000";
                }else if (AN.length() == 3){ // jika id_anggota A00100
                    Nol = "00";
                }
                IDPINJAM.setText("P" + Nol + AN);
            }else{
                IDPINJAM.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //Reset Data setelah Input/Edit/Delete
    public void Clear() {
        tPinjam.setText(null);
        tKembali.setDate(null);
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
        jLabel2 = new javax.swing.JLabel();
        IDPINJAM = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dAnggota = new javax.swing.JComboBox<>();
        dBuku = new javax.swing.JComboBox<>();
        BINPUT = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABELPINJAM = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        tPinjam = new javax.swing.JTextField();
        tKembali = new com.toedter.calendar.JDateChooser();
        bDashboard = new javax.swing.JLabel();
        bPeminjaman = new javax.swing.JLabel();
        bPengembalian = new javax.swing.JLabel();
        bDataBuku = new javax.swing.JLabel();
        bDataBuku1 = new javax.swing.JLabel();
        bLogout = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Tanngal Kembali");

        IDPINJAM.setEditable(false);

        jLabel7.setText("Tanggal Pinjam");

        jLabel5.setText("Nama Buku");

        jLabel3.setText("Id Peminjaman");

        jLabel6.setText("Nama Anggota");

        BINPUT.setText("Pinjam Buku");
        BINPUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BINPUTActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/refresh.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

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

        tKembali.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dAnggota, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IDPINJAM, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(99, 99, 99)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BINPUT))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tPinjam)
                                    .addComponent(tKembali, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)))))
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(IDPINJAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(dAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(dBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(tKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BINPUT))))))
                .addGap(68, 68, 68)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 120, 885, 440));

        bDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDashboardMouseClicked(evt);
            }
        });
        getContentPane().add(bDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 163, 236, 58));

        bPeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bPeminjamanMouseClicked(evt);
            }
        });
        getContentPane().add(bPeminjaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 222, 236, 58));

        bPengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bPengembalianMouseClicked(evt);
            }
        });
        getContentPane().add(bPengembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 281, 236, 58));

        bDataBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDataBukuMouseClicked(evt);
            }
        });
        getContentPane().add(bDataBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 384, 236, 58));

        bDataBuku1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDataBuku1MouseClicked(evt);
            }
        });
        getContentPane().add(bDataBuku1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 443, 236, 58));

        bLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bLogoutMouseClicked(evt);
            }
        });
        getContentPane().add(bLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 502, 236, 58));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mPinjam.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1181, 590));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDashboardMouseClicked
        new FDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDashboardMouseClicked

    private void bPeminjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bPeminjamanMouseClicked
        new FPeminjaman().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bPeminjamanMouseClicked

    private void bPengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bPengembalianMouseClicked
        new FPengembalian().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bPengembalianMouseClicked

    private void bDataBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDataBukuMouseClicked
        new FDataBuku().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDataBukuMouseClicked

    private void bDataBuku1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDataBuku1MouseClicked
        new FDataAnggota().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDataBuku1MouseClicked

    private void bLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bLogoutMouseClicked
        new FLogin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bLogoutMouseClicked

    private void BINPUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BINPUTActionPerformed
        // TODO add your handling code here:
        input_data();
        load_data();
    }//GEN-LAST:event_BINPUTActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Clear();
    }//GEN-LAST:event_jLabel4MouseClicked

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
            java.util.logging.Logger.getLogger(FPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FPeminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BINPUT;
    private javax.swing.JTextField IDPINJAM;
    private javax.swing.JTable TABELPINJAM;
    private javax.swing.JLabel bDashboard;
    private javax.swing.JLabel bDataBuku;
    private javax.swing.JLabel bDataBuku1;
    private javax.swing.JLabel bLogout;
    private javax.swing.JLabel bPeminjaman;
    private javax.swing.JLabel bPengembalian;
    private javax.swing.JComboBox<String> dAnggota;
    private javax.swing.JComboBox<String> dBuku;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser tKembali;
    private javax.swing.JTextField tPinjam;
    // End of variables declaration//GEN-END:variables
}
