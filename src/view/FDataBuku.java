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

public class FDataBuku extends javax.swing.JFrame {

    /**
     * Creates new form FDataBuku
     */
    public FDataBuku() {
        initComponents();
        load_data(); //memanggil menampilkan data
        IDOtomatis(); //menampilkan id_otomatis
    }
    
        //load data dari Database tbl_anggota
        private void input_data() {
        try {
            Connection kon = koneksi.getConnection();
            Statement st = kon.createStatement();
            //untuk penerbit
            String penerbit = "";
            if (RDeepublish.isSelected()) {
                penerbit = RDeepublish.getText();
            } else if (RMuueza.isSelected()) {
                penerbit = RMuueza.getText();
            } else if (RGramedia.isSelected()) {
                penerbit = RGramedia.getText();
            } else {
                RPolije.isSelected();
                penerbit = RPolije.getText();
            }

            String sql = "INSERT INTO tbl_buku values('" + ID.getText()
                    + "','" + JUDUL.getText()
                    + "','" + PENGARANG.getText()
                    + "','" + penerbit
                    + "','" + TTERBIT.getSelectedItem()
                    + "')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Anggota Berhasil Dimasukkan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Reset Data setelah Input/Edit/Delete
    public void Clear() {
        ID.setText("");
        PENGARANG.setText("");
        JUDUL.setText("");
        TTERBIT.setSelectedItem(1);
    }
    
    //load data dari Database tbl_anggota
    private void load_data() {
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
    
    //Edit data
    public void Update() {
        try {
            Connection kon = koneksi.getConnection();
            Statement st = kon.createStatement();
            String publish = "";
            if (RDeepublish.isSelected()) {
                publish = RDeepublish.getText();
            } else if (RMuueza.isSelected()) {
                publish = RMuueza.getText();
            } else if (RGramedia.isSelected()) {
                publish = RGramedia.getText();
            } else {
                publish = RPolije.getText();
            }

            String sql_update = "UPDATE tbl_buku SET judul_buku='" + JUDUL.getText()
                    + "',pengarang='" + PENGARANG.getText()
                    + "',penerbit='" + publish
                    + "',tahun_terbit='" + TTERBIT.getSelectedItem()
                    + "'WHERE id_buku='" + ID.getText() + "'";
            st.execute(sql_update);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //ID Anggota Otomatis
    private void IDOtomatis() {
        try {
            Connection kon = koneksi.getConnection();
            Statement st = kon.createStatement();
            String sql_id = "SELECT * FROM tbl_buku order by id_buku desc";
            ResultSet rs = st.executeQuery(sql_id);
            if (rs.next()) {
                String id_buku = rs.getString("id_buku").substring(1);
                String AN = "" + (Integer.parseInt(id_buku) + 1);
                String Nol = "";
                if (AN.length() == 1) //jika id_anggota A00001
                {
                    Nol = "0000";
                } else if (AN.length() == 2) //jika id_anggota A00010
                {
                    Nol = "000";
                } else if (AN.length() == 3) // jika id_anggota A00100
                {
                    Nol = "00";
                }
                ID.setText("B" + Nol + AN);
            } else {
                ID.setText("B00001");
            }
        } catch (Exception e) {
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

        PUBLISH = new javax.swing.ButtonGroup();
        bPengembalian = new javax.swing.JLabel();
        bPeminjaman = new javax.swing.JLabel();
        bDashboard = new javax.swing.JLabel();
        bDataBuku = new javax.swing.JLabel();
        bDataBuku1 = new javax.swing.JLabel();
        bLogout = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        scroolPane = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();
        BDelete = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        RGramedia = new javax.swing.JRadioButton();
        PENGARANG = new javax.swing.JTextField();
        RMuueza = new javax.swing.JRadioButton();
        TTERBIT = new javax.swing.JComboBox<>();
        RPolije = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        JUDUL = new javax.swing.JTextField();
        JJudul = new javax.swing.JLabel();
        JPengarang = new javax.swing.JLabel();
        JPenerbit = new javax.swing.JLabel();
        JTerbit = new javax.swing.JLabel();
        RDeepublish = new javax.swing.JRadioButton();
        ID = new javax.swing.JTextField();
        RESETBtn = new javax.swing.JButton();
        BInput = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bPengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bPengembalianMouseClicked(evt);
            }
        });
        getContentPane().add(bPengembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 281, 236, 58));

        bPeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bPeminjamanMouseClicked(evt);
            }
        });
        getContentPane().add(bPeminjaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 222, 236, 58));

        bDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDashboardMouseClicked(evt);
            }
        });
        getContentPane().add(bDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 163, 236, 58));

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

        BDelete.setText("Delete");
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });

        BEdit.setText("Update");
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });

        refreshBtn.setText("Refresh");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        RGramedia.setBackground(new java.awt.Color(255, 255, 255));
        PUBLISH.add(RGramedia);
        RGramedia.setText("Gramedia");

        PENGARANG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PENGARANGActionPerformed(evt);
            }
        });

        RMuueza.setBackground(new java.awt.Color(255, 255, 255));
        PUBLISH.add(RMuueza);
        RMuueza.setText("Muueza");
        RMuueza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RMuuezaActionPerformed(evt);
            }
        });

        TTERBIT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2016", "2017", "2018", "2019", "2020", "2021" }));
        TTERBIT.setToolTipText("2016\n2017\n2018\n2019\n2020\n2021");
        TTERBIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TTERBITActionPerformed(evt);
            }
        });

        RPolije.setBackground(new java.awt.Color(255, 255, 255));
        PUBLISH.add(RPolije);
        RPolije.setText("Polije");

        jLabel2.setText("ID BUKU");

        JUDUL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JUDULActionPerformed(evt);
            }
        });

        JJudul.setText("JUDUL ");

        JPengarang.setText("PENGARANG");

        JPenerbit.setText("PENERBIT");

        JTerbit.setText("Tahun Terbit");

        RDeepublish.setBackground(new java.awt.Color(255, 255, 255));
        PUBLISH.add(RDeepublish);
        RDeepublish.setText("Deepublish");

        ID.setEnabled(false);

        RESETBtn.setText("RESET");
        RESETBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESETBtnActionPerformed(evt);
            }
        });

        BInput.setText("INPUT");
        BInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(refreshBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BDelete)
                        .addGap(18, 18, 18)
                        .addComponent(BEdit))
                    .addComponent(scroolPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(JPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addComponent(JTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(RESETBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                                .addComponent(BInput))
                            .addComponent(ID, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PENGARANG, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TTERBIT, javax.swing.GroupLayout.Alignment.LEADING, 0, 234, Short.MAX_VALUE))
                        .addGap(83, 83, 83)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JJudul)
                            .addComponent(JPenerbit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(RDeepublish, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(RGramedia))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(RPolije)
                                    .addComponent(RMuueza)))
                            .addComponent(JUDUL, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JUDUL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JJudul))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RMuueza)
                            .addComponent(RDeepublish))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RPolije)
                            .addComponent(RGramedia, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PENGARANG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JPengarang)
                            .addComponent(JPenerbit))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TTERBIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTerbit))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RESETBtn)
                    .addComponent(BInput))
                .addGap(64, 64, 64)
                .addComponent(scroolPane, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshBtn)
                    .addComponent(BEdit)
                    .addComponent(BDelete))
                .addGap(62, 62, 62))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 120, 886, 442));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mBuku.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bookTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookTableMouseClicked
        // TODO add your handling code here:
        int bar=bookTable.getSelectedRow();
        String a=bookTable.getValueAt(bar, 0).toString();
        String b=bookTable.getValueAt(bar, 1).toString();
        String c=bookTable.getValueAt(bar, 2).toString();
        String d=bookTable.getValueAt(bar, 3).toString();
        String e=bookTable.getValueAt(bar, 4).toString();

        ID.setText(a);
        JUDUL.setText(b);
        PENGARANG.setText(c);
        //        Penerbit
        if("Deepublish".equals(d))
        {
            RDeepublish.setSelected(true);
        }
        else if("Muueza".equals(d))
        {
            RMuueza.setSelected(true);
        }
        else if("Gramedia".equals(d))
        {
            RGramedia.setSelected(true);
        }
        else{
            "Polije".equals(d);
            RPolije.setSelected(true);
        }
        //        TERBIT
        TTERBIT.setSelectedItem(e);

        //        Set Disable Input
        BInput.setEnabled(false);
        BEdit.setEnabled(true);
        BDelete.setEnabled(true);

    }//GEN-LAST:event_bookTableMouseClicked

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pililah baris terlebih dahulu");
        } else {
            int option = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menhapus data ini?", "Hapus Data", JOptionPane.WARNING_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                try
                {
                    Connection kon = koneksi.getConnection();
                    Statement st=kon.createStatement();
                    String sql_delete="DELETE from tbl_buku WHERE "
                    +"id_buku='"+ID.getText()+"'";
                    st.executeUpdate(sql_delete);
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
                    load_data();
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }//GEN-LAST:event_BDeleteActionPerformed

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        Update();
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.setRowCount(0);
        load_data();
    }//GEN-LAST:event_BEditActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.setRowCount(0);
        load_data();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void PENGARANGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PENGARANGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PENGARANGActionPerformed

    private void RMuuezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RMuuezaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RMuuezaActionPerformed

    private void TTERBITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TTERBITActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TTERBITActionPerformed

    private void JUDULActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JUDULActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JUDULActionPerformed

    private void RESETBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESETBtnActionPerformed
        // TODO add your handling code here:
        Clear();
    }//GEN-LAST:event_RESETBtnActionPerformed

    private void BInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInputActionPerformed
        // TODO add your handling code here:
        //        ConfirmYESNO Question
        int simpan = JOptionPane.showOptionDialog(this,
            "Apakah Data Sudah Benar? SIMPAN?",
            "Simpan Data",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (simpan == JOptionPane.YES_OPTION) {
            input_data();
            load_data();
            //            Clear();
            IDOtomatis();
        }
    }//GEN-LAST:event_BInputActionPerformed

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

    private void bPengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bPengembalianMouseClicked
        new FPengembalian().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bPengembalianMouseClicked

    private void bPeminjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bPeminjamanMouseClicked
        new FPeminjaman().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bPeminjamanMouseClicked

    private void bDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDashboardMouseClicked
        new FDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDashboardMouseClicked

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
            java.util.logging.Logger.getLogger(FDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FDataBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BInput;
    private javax.swing.JTextField ID;
    private javax.swing.JLabel JJudul;
    private javax.swing.JLabel JPenerbit;
    private javax.swing.JLabel JPengarang;
    private javax.swing.JLabel JTerbit;
    private javax.swing.JTextField JUDUL;
    private javax.swing.JTextField PENGARANG;
    private javax.swing.ButtonGroup PUBLISH;
    private javax.swing.JRadioButton RDeepublish;
    private javax.swing.JButton RESETBtn;
    private javax.swing.JRadioButton RGramedia;
    private javax.swing.JRadioButton RMuueza;
    private javax.swing.JRadioButton RPolije;
    private javax.swing.JComboBox<String> TTERBIT;
    private javax.swing.JLabel bDashboard;
    private javax.swing.JLabel bDataBuku;
    private javax.swing.JLabel bDataBuku1;
    private javax.swing.JLabel bLogout;
    private javax.swing.JLabel bPeminjaman;
    private javax.swing.JLabel bPengembalian;
    private javax.swing.JTable bookTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JScrollPane scroolPane;
    // End of variables declaration//GEN-END:variables
}
