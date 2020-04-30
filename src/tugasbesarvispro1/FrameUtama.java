/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasbesarvispro1;

import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author reza
 */
public class FrameUtama extends javax.swing.JFrame {
    private File file = new File ("/home/reza/NetBeansProjects/TugasBesarVisPro1/dataobat.csv");
    private static ArrayList<Object>EMP_DATA = new ArrayList();
    private String line = "";
    private String csvSplitBy = ",";
    private DefaultTableModel tableModel;
    long totalHarga = 0, jml = 0, harga=0, byr=0, kembalian=0, total=0;
    DecimalFormat df = new DecimalFormat("#,###");
    

    /**
     * Creates new form FrameUtama
     */
    public FrameUtama() {
        initComponents();
        long noTransaksi= System.currentTimeMillis();
        NomorTransaksi.setText(String.valueOf(noTransaksi));
        tableModel = (DefaultTableModel) tabelObat.getModel();
          if(file != null){;
           try {
                // inisialisasi variable untuk membaca file csv
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                Boolean header = true;
                // membaca data csv per-baris
                while((line = br.readLine()) != null){
                    EMP_DATA.add(line);
                    String[] data = line.split(csvSplitBy);
                    tableModel.addRow(data);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrameUtama.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FrameUtama.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }
    }    
         public static ArrayList<Object> getStrong(){
        return EMP_DATA;
    }
    
     private void showFileChooser(String title){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "CSV(*.csv)", "csv");
        chooser.setFileFilter(filter);
        chooser.setMultiSelectionEnabled(false);
        chooser.setApproveButtonText(title);
        chooser.setDialogTitle(title);
        int i = chooser.showOpenDialog(this);
        if(i== JFileChooser.CANCEL_OPTION){
            return;
        }
        file = chooser.getSelectedFile();
    }     
         
    private void Tambah(){
       harga = Long.parseLong(Harga.getText());
       jml = Integer.parseInt(txtJumlah.getText());
        
        
        totalHarga = harga * jml;
        byr = (totalHarga + byr);
        TotalHarga.setText("Rp."+df.format(totalHarga)+",-");
        Harga.setText("Rp."+df.format(harga)+",-");
        bayar.setText("Rp."+df.format(byr)+",-");
        
        DefaultTableModel tableModel = (DefaultTableModel) tblKeranjang.getModel();
        tableModel.addRow(new Object[]{Deskripsi.getText(),txtJumlah.getText(), Harga.getText(),TotalHarga.getText()}); 
    }
    
    private void Nyari(){
        DefaultTableModel tableModel = (DefaultTableModel)tabelObat.getModel();
        String search = Pencarian.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tableModel);
        tabelObat.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }
    
    private void Ulang(){
        int nanya =  JOptionPane.showConfirmDialog(null, "Anda Yakin Akan Mengulang?","Confirmation Dialog", 
                   JOptionPane.YES_NO_OPTION);
      if (nanya == JOptionPane.YES_OPTION){
        tableModel = (DefaultTableModel) tblKeranjang.getModel();
        tableModel.setRowCount(0);
        tabelObat.clearSelection();
        bayar.setText("-");
        angsulan.setText("-");
        Bon.setText("");
      }
    }
    
    private void Hapus(){
         DefaultTableModel tableModel = (DefaultTableModel) tblKeranjang.getModel();        
            int SelectedRowIndex = tblKeranjang.getSelectedRow();
            if(SelectedRowIndex<0){
                JOptionPane.showMessageDialog(this, "Silahkan pilih barang yang akan di hapus!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
            if(SelectedRowIndex>=0){
                if(JOptionPane.showConfirmDialog(this,"Anda yakin untuk menghapus?","Konfirmasi Dialog",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==0){
                    JOptionPane.showMessageDialog(this, "Data Berhasil Di Hapus");
                    tableModel.removeRow(SelectedRowIndex);                    
                }                   
            }
    }
    
    
    private void Kembalian(){
        total=Long.parseLong(mayar.getText());
        kembalian = total-byr; 
        angsulan.setText("Rp."+df.format(kembalian)+",-");
        if(total<byr){
            angsulan.setText("-");
            JOptionPane.showMessageDialog(null, "Uang Pembayaran Anda Kurang!", "Dialog Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void BonBayar(){
        Bon.setText("============APOTEK KESEHATAN============="
+"\n" +"NO TRANSAKSI: " +NomorTransaksi.getText()
+"\n" +"TOTAL BELANJA : " +bayar.getText()
+"\n" +"UANG: " +mayar.getText() 
+"\n" +"KEMBALIAN : " + angsulan.getText()
+"\n" +"      ============TERIMA KASIH=============      ");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Jumlah = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelObat = new javax.swing.JTable();
        txtJumlah = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKeranjang = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Deskripsi = new javax.swing.JLabel();
        Harga = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TotalHarga = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Pencarian = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        bayar = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        mayar = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        angsulan = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        NomorTransaksi = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Bon = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("APOTEK");

        Jumlah.setBackground(new java.awt.Color(254, 254, 254));
        Jumlah.setBorder(javax.swing.BorderFactory.createTitledBorder("Transaksi"));

        tabelObat.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        tabelObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Supplier", "Kategori", "Deskripsi", "Satuan Beli", "Satuan Jual", "Harga Jual", "MarkUp"
            }
        ));
        tabelObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelObatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelObat);

        txtJumlah.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(1, 1, 1));
        jLabel3.setText("Jumlah");

        jButton2.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(1, 1, 1));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-plus-math-24.png"))); // NOI18N
        jButton2.setText("Tambahkan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tblKeranjang.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        tblKeranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Deskripsi", "Jumlah", "Harga", "Total Harga"
            }
        ));
        jScrollPane2.setViewportView(tblKeranjang);

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Info"));

        jLabel4.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(1, 1, 1));
        jLabel4.setText("Deskripsi");

        Deskripsi.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        Deskripsi.setForeground(new java.awt.Color(1, 1, 1));
        Deskripsi.setText("-");

        Harga.setBackground(new java.awt.Color(254, 254, 254));
        Harga.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        Harga.setForeground(new java.awt.Color(1, 1, 1));
        Harga.setText("-");

        jLabel7.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(1, 1, 1));
        jLabel7.setText("Harga");

        jLabel5.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(1, 1, 1));
        jLabel5.setText("Total Harga");

        TotalHarga.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        TotalHarga.setForeground(new java.awt.Color(1, 1, 1));
        TotalHarga.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Harga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Deskripsi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(296, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Deskripsi))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Harga)
                    .addComponent(jLabel7))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(1, 1, 1));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-synchronize-24.png"))); // NOI18N
        jButton4.setText("Ulangi");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(1, 1, 1));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-trash-24.png"))); // NOI18N
        jButton5.setText("Hapus");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JumlahLayout = new javax.swing.GroupLayout(Jumlah);
        Jumlah.setLayout(JumlahLayout);
        JumlahLayout.setHorizontalGroup(
            JumlahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(JumlahLayout.createSequentialGroup()
                .addGroup(JumlahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JumlahLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        JumlahLayout.setVerticalGroup(
            JumlahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JumlahLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JumlahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JumlahLayout.createSequentialGroup()
                        .addGroup(JumlahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jButton4)
                            .addComponent(jButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 14, Short.MAX_VALUE))
                    .addGroup(JumlahLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jLabel6.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jLabel6.setText("Search");

        Pencarian.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        Pencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PencarianKeyReleased(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(254, 254, 254));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pembayaran"));

        jLabel8.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(1, 1, 1));
        jLabel8.setText("Total Bayar");

        bayar.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        bayar.setForeground(new java.awt.Color(1, 1, 1));
        bayar.setText("-");

        jLabel9.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(1, 1, 1));
        jLabel9.setText("Masukan Jumlah Pembayaran");

        mayar.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N

        jButton3.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(1, 1, 1));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-stack-of-money-24.png"))); // NOI18N
        jButton3.setText("Bayar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(1, 1, 1));
        jLabel10.setText("Kembalian");

        angsulan.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        angsulan.setForeground(new java.awt.Color(1, 1, 1));
        angsulan.setText("-");

        jLabel11.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(1, 1, 1));
        jLabel11.setText("No. Transaksi");

        NomorTransaksi.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        NomorTransaksi.setForeground(new java.awt.Color(1, 1, 1));
        NomorTransaksi.setText("-");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(27, 27, 27)
                        .addComponent(bayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(261, 261, 261))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(mayar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NomorTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(angsulan, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(bayar))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(mayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(NomorTransaksi))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(angsulan))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jButton6.setText("jButton6");

        jPanel3.setBackground(new java.awt.Color(254, 254, 254));

        jLabel1.setFont(new java.awt.Font("Courier 10 Pitch", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 1, 1));
        jLabel1.setText("Apotek Kesehatan");

        jLabel2.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(1, 1, 1));
        jLabel2.setText("Jln. Janur Kuning Blok C No 22");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-rod-of-asclepius-50.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-rod-of-asclepius-50.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(85, 85, 85)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(80, 80, 80)
                .addComponent(jLabel13)
                .addGap(380, 380, 380))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(254, 254, 254));

        Bon.setColumns(20);
        Bon.setRows(5);
        jScrollPane3.setViewportView(Bon);

        jLabel14.setFont(new java.awt.Font("Capture it 2", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(1, 1, 1));
        jLabel14.setText("Only I can change my life. No one can do it for me.");

        jLabel15.setFont(new java.awt.Font("Courier 10 Pitch", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(1, 1, 1));
        jLabel15.setText("-Carol Burnet-");

        jButton1.setFont(new java.awt.Font("Courier 10 Pitch", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(1, 1, 1));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-print-24.png"))); // NOI18N
        jButton1.setText("Cetak");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(211, 211, 211))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel14))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );

        jMenu3.setForeground(new java.awt.Color(1, 1, 1));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-back-arrow-16.png"))); // NOI18N
        jMenu3.setText("Kembali");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu1.setForeground(new java.awt.Color(1, 1, 1));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-opened-folder-16.png"))); // NOI18N
        jMenu1.setText("Buka");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenu1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMenu1KeyPressed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Jumlah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(Pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelObatMouseClicked
        // TODO add your handling code here:
        int i = tabelObat.getSelectedRow();
        TableModel tableModel = tabelObat.getModel();
            Deskripsi.setText(tableModel.getValueAt(i, 3).toString());
            Harga.setText(tableModel.getValueAt(i, 7).toString());
    }//GEN-LAST:event_tabelObatMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(txtJumlah.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Jumlah pesanan belum terisi, silahkan isi terlebih dahulu"
                ,"Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        Tambah();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void PencarianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PencarianKeyReleased
        // TODO add your handling code here:
        Nyari();
    }//GEN-LAST:event_PencarianKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Ulang();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Hapus();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Kembalian();
        BonBayar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            Bon.print();
        } catch (PrinterException ex) {
            Logger.getLogger(FrameUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenu1KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenu1KeyPressed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
        showFileChooser("Open");
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            Boolean header = true;
            while((line=br.readLine()) != null){
                if(header == false) {
                    EMP_DATA.add(line);
                    String[] data = line.split(csvSplitBy);
                    tableModel.addRow(data);
                }
                header = false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrameUtama.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrameUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu1MouseClicked

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
            java.util.logging.Logger.getLogger(FrameUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Bon;
    private javax.swing.JLabel Deskripsi;
    private javax.swing.JLabel Harga;
    private javax.swing.JPanel Jumlah;
    private javax.swing.JLabel NomorTransaksi;
    private javax.swing.JTextField Pencarian;
    private javax.swing.JLabel TotalHarga;
    private javax.swing.JLabel angsulan;
    private javax.swing.JLabel bayar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField mayar;
    private javax.swing.JTable tabelObat;
    private javax.swing.JTable tblKeranjang;
    private javax.swing.JTextField txtJumlah;
    // End of variables declaration//GEN-END:variables
}
