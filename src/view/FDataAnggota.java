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
public class FDataAnggota extends javax.swing.JFrame {

    /**
     * Creates new form FDataAnggota
     */
    public FDataAnggota() {
        initComponents();
        load_data(); //memanggil menampilkan data
        IDOtomatis(); //menampilkan id_otomatis
        LoadTingkat(); //load combo tingkat
        LoadJurusan(); //load combo jurusan
        
        //Menampilkan button Edit dan Delete saat data dipilih
        BEdit.setEnabled(false);
        BDelete.setEnabled(false);
    }
    
    //Create Data---------------------------------------------------------------
    private void input_data(){
        try{
            Connection kon = koneksi.getConnection();
            Statement st=kon.createStatement();
            //untuk jenis kelamin
            String jk="";
            if(JKP.isSelected()){
                jk=JKP.getText();
            }else{
                jk=JKW.getText();
            }
            
            String sql="INSERT INTO tbl_anggota values('"+ID.getText()
                    +"','"+NIS.getText()
                    +"','"+NAMA.getText()
                    +"','"+jk
                    +"','"+KTINGKAT.getSelectedItem()
                    +"','"+KJURUSAN.getSelectedItem()
                    +"','"+NOPE.getText()
                    +"','"+STATUS.getSelectedItem()
                    +"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Anggota Berhasil Dimasukkan");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //Read data dari Database tbl_anggota---------------------------------------
    private void load_data(){
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
    
    //Update data---------------------------------------------------------------
    public void Update(){
        try{
            Connection kon = koneksi.getConnection();
            Statement st=kon.createStatement();
            String jk="";
            if(JKP.isSelected()){
                jk=JKP.getText();
            }else{
                jk=JKW.getText();
            }
            
            String sql_update="UPDATE tbl_anggota SET nis='"+NIS.getText()
                    +"',nama='"+NAMA.getText()
                    +"',jk='"+jk
                    +"',id_tingkat='"+KTINGKAT.getSelectedItem()
                    +"',kd_jurusan='"+KJURUSAN.getSelectedItem()
                    +"',no_hp='"+NOPE.getText()
                    +"',status='"+STATUS.getSelectedItem()
                    +"'WHERE id_anggota='"+ID.getText()+"'";
            st.execute(sql_update);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Update");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //Delete Data---------------------------------------------------------------
    private void delete(){
        try{
            Connection kon = koneksi.getConnection();
            Statement st=kon.createStatement();
            String sql_delete="DELETE from tbl_anggota WHERE "
                    +"id_anggota='"+ID.getText()+"'";
            st.executeUpdate(sql_delete);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
  
    //ID Anggota Otomatis
    private void IDOtomatis(){
        try{
            Connection kon = koneksi.getConnection();
            Statement st=kon.createStatement();
            String sql_id="SELECT * FROM tbl_anggota order by id_anggota desc";
            ResultSet rs=st.executeQuery(sql_id);
            if(rs.next()){
                String id_anggota=rs.getString("id_anggota").substring(1);
                String AN=""+(Integer.parseInt(id_anggota)+1);
                String Nol="";
                if(AN.length()==1){//jika id_anggota A00001
                    Nol="0000";
                }else if(AN.length()==2){ //jika id_anggota A00010
                    Nol="000";
                }else if(AN.length()==3){ // jika id_anggota A00100
                    Nol="00";
                }
                ID.setText("A"+Nol+AN);
            }else{
                ID.setText("A00001");
            }
        }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
    }
    //Load Combo Tingkat
    public void LoadTingkat(){
        try{
            Connection kon = koneksi.getConnection();
            Statement st=kon.createStatement();
            String sql_tingkat="SELECT id_tingkat FROM tbl_tingkat";
            ResultSet rs=st.executeQuery(sql_tingkat);
            while(rs.next()){
                KTINGKAT.addItem(rs.getString("id_tingkat"));
            }
            rs.close();
        }
        catch(Exception e){
            
            }
    }
    
    //Load Combo Jurusan
    public void LoadJurusan(){
        try{
            Connection kon = koneksi.getConnection();
            Statement st=kon.createStatement();
            String sql_tingkat="SELECT kd_jurusan FROM tbl_jurusan";
            ResultSet rs=st.executeQuery(sql_tingkat);
            while(rs.next()){
                KJURUSAN.addItem(rs.getString("kd_jurusan"));
            }
            rs.close();
        }
        catch(Exception e){
            
        }
    }
     
    
    //Reset Data setelah Input/Edit/Delete
    public void Clear(){
        NIS.setText("");
        NAMA.setText("");
        NOPE.setText("");
        JKP.setSelected(rootPaneCheckingEnabled);
        KTINGKAT.setSelectedItem(1);
        KJURUSAN.setSelectedItem("FAR");
        STATUS.setSelectedItem("AKTIF");
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JK = new javax.swing.ButtonGroup();
        bDashboard = new javax.swing.JLabel();
        bPeminjaman = new javax.swing.JLabel();
        bPengembalian = new javax.swing.JLabel();
        bDataBuku = new javax.swing.JLabel();
        bDataAnggota = new javax.swing.JLabel();
        bLogout = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        idAnggota = new javax.swing.JLabel();
        NIS = new javax.swing.JTextField();
        nis = new javax.swing.JLabel();
        NAMA = new javax.swing.JTextField();
        nama = new javax.swing.JLabel();
        JKP = new javax.swing.JRadioButton();
        JKW = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        BDelete = new javax.swing.JButton();
        KTINGKAT = new javax.swing.JComboBox<>();
        KJURUSAN = new javax.swing.JComboBox<>();
        NOPE = new javax.swing.JTextField();
        STATUS = new javax.swing.JComboBox<>();
        BInput = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelAnggota = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1197, 629));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        bDataAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDataAnggotaMouseClicked(evt);
            }
        });
        getContentPane().add(bDataAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 443, 236, 58));

        bLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bLogoutMouseClicked(evt);
            }
        });
        getContentPane().add(bLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 502, 236, 58));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("TINGKAT");

        jLabel7.setText("JURUSAN");

        jLabel9.setText("HO HP");

        jLabel10.setText("STATUS");

        ID.setEnabled(false);

        idAnggota.setText("ID ANGGOTA");

        nis.setText("NIS");

        nama.setText("NAMA ANGGOTA");

        JK.add(JKP);
        JKP.setSelected(true);
        JKP.setText("PRIA");

        JK.add(JKW);
        JKW.setText("WANITA");

        jLabel5.setText("JENIS KELAMIN");

        BDelete.setText("DELETE");
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });

        STATUS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AKTIF", "TIDAK AKTIF" }));

        BInput.setText("INPUT");
        BInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInputActionPerformed(evt);
            }
        });

        BEdit.setText("EDIT");
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Input Data Anggota");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Tabel Data Anggota");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(nama)
                                                    .addComponent(jLabel6))
                                                .addGap(10, 10, 10)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(KTINGKAT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGap(196, 196, 196))
                                                    .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nis)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel9))
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(45, 45, 45)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(KJURUSAN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(STATUS, 0, 116, Short.MAX_VALUE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(idAnggota)
                                                .addGap(28, 28, 28)
                                                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel2))
                                        .addGap(147, 147, 147)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(JKP)
                                        .addGap(18, 18, 18)
                                        .addComponent(JKW))
                                    .addComponent(NIS, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                    .addComponent(NOPE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BInput)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(BDelete)
                                        .addGap(18, 18, 18)
                                        .addComponent(BEdit)))))
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idAnggota)
                            .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nis)
                            .addComponent(NIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama)
                            .addComponent(JKP)
                            .addComponent(jLabel5)
                            .addComponent(JKW))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(KTINGKAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(NOPE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(KJURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(160, Short.MAX_VALUE)
                        .addComponent(STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addComponent(BInput)
                .addGap(8, 8, 8)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BEdit)
                    .addComponent(BDelete))
                .addGap(50, 50, 50))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 131, 885, 430));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mAnggotaa.jpg"))); // NOI18N
        jLabel1.setText("\n");
        jLabel1.setMaximumSize(new java.awt.Dimension(1181, 590));
        jLabel1.setMinimumSize(new java.awt.Dimension(1181, 590));
        jLabel1.setPreferredSize(new java.awt.Dimension(1181, 590));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TabelAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelAnggotaMouseClicked
        // TODO add your handling code here:
        int bar=TabelAnggota.getSelectedRow();
        String a=TabelAnggota.getValueAt(bar, 0).toString();
        String b=TabelAnggota.getValueAt(bar, 1).toString();
        String c=TabelAnggota.getValueAt(bar, 2).toString();
        String d=TabelAnggota.getValueAt(bar, 3).toString();
        String e=TabelAnggota.getValueAt(bar, 4).toString();
        String f=TabelAnggota.getValueAt(bar, 5).toString();
        String g=TabelAnggota.getValueAt(bar, 6).toString();
        String h=TabelAnggota.getValueAt(bar, 7).toString();

        ID.setText(a);
        NIS.setText(b);
        NAMA.setText(c);
        //Jenia kelamin
        if("PRIA".equals(d)){
            JKP.setSelected(true);
        }else{
            JKW.setSelected(true);
        }
        //TINGKAT
        KTINGKAT.setSelectedItem(e);
        KJURUSAN.setSelectedItem(f);
        NOPE.setText(g);
        //STATUS
        if("AKTIF".equals(h)){
            STATUS.setSelectedItem(h);
        }else{
            STATUS.setSelectedItem(h);
        }

        //Set Disable Input
        BInput.setEnabled(false);
        BEdit.setEnabled(true);
        BDelete.setEnabled(true);
    }//GEN-LAST:event_TabelAnggotaMouseClicked

    private void bLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bLogoutMouseClicked
        new FLogin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bLogoutMouseClicked

    private void bDataAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDataAnggotaMouseClicked
        new FDataAnggota().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDataAnggotaMouseClicked

    private void bDataBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDataBukuMouseClicked
        new FDataBuku().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bDataBukuMouseClicked

    private void BInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInputActionPerformed
        int simpan=JOptionPane.showOptionDialog(this, 
                "Apakah Data Sudah Benar? SIMPAN?",
                "Simpan Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if(simpan==JOptionPane.YES_OPTION){
            input_data();
            load_data();
            Clear();
            IDOtomatis();
        }
    }//GEN-LAST:event_BInputActionPerformed

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        int update=JOptionPane.showOptionDialog(this, 
                "Apakah Data Akan Di Update? Update?",
                "Update Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if(update==JOptionPane.YES_OPTION){
            Update();
            Clear();
            load_data();
            IDOtomatis();
            //Set Enable Input
            BInput.setEnabled(true);
            BEdit.setEnabled(false);
            BDelete.setEnabled(false);
        }
    }//GEN-LAST:event_BEditActionPerformed

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        int delete=JOptionPane.showOptionDialog(this, 
                "Apakah Data Akan Di Hapus? Hapus?",
                "Hapus Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if(delete==JOptionPane.YES_OPTION){
            delete();
            Clear();
            load_data();
            IDOtomatis();
            //Set Enable Input
            BInput.setEnabled(true);
            BEdit.setEnabled(false);
            BDelete.setEnabled(false);
        }
    }//GEN-LAST:event_BDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FDataAnggota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BInput;
    private javax.swing.JTextField ID;
    private javax.swing.ButtonGroup JK;
    private javax.swing.JRadioButton JKP;
    private javax.swing.JRadioButton JKW;
    private javax.swing.JComboBox<String> KJURUSAN;
    private javax.swing.JComboBox<String> KTINGKAT;
    private javax.swing.JTextField NAMA;
    private javax.swing.JTextField NIS;
    private javax.swing.JTextField NOPE;
    private javax.swing.JComboBox<String> STATUS;
    private javax.swing.JTable TabelAnggota;
    private javax.swing.JLabel bDashboard;
    private javax.swing.JLabel bDataAnggota;
    private javax.swing.JLabel bDataBuku;
    private javax.swing.JLabel bLogout;
    private javax.swing.JLabel bPeminjaman;
    private javax.swing.JLabel bPengembalian;
    private javax.swing.JLabel idAnggota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nama;
    private javax.swing.JLabel nis;
    // End of variables declaration//GEN-END:variables
}
