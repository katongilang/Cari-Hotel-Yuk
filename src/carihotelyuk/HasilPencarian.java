/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carihotelyuk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Katon Gilang
 */
public class HasilPencarian extends javax.swing.JFrame {

    /**
     * Creates new form HasilPencarian
     */
    private String keyword;
    Home home;
    public HasilPencarian() {
        initComponents();
    }
    public HasilPencarian(Home home, String keyword) {
        initComponents();
        this.keyword=keyword;
        this.home=home;
    }
    
    private void pencarian(String keyword){
        String sorting=cbFiturSorting.getSelectedItem().toString();
        String filtering=cbFiturFiltering.getSelectedItem().toString();
        
        if (filtering=="Tidak" && sorting=="Tidak"){
            try{
                tfYangDiCari.setText(keyword);
                String sql="SELECT * FROM hotel WHERE nama_hotel LIKE '%"+keyword+"%' OR alamat_hotel LIKE '%"+keyword+"%'";
                
                Connection con=ModuleDB.connectDB();
                Statement stmt=con.createStatement();
                ResultSet result=stmt.executeQuery(sql);
                DefaultTableModel model=(DefaultTableModel)jtabHasilPencarian.getModel();
                model.setRowCount(0);
                
//                if (!result.next()){
//                    notFound.setText("Tidak ditemukan !");
//                    con.close();
//                }
//                
                while(result.next()){
                        notFound.setText(null);
                        Vector row=new Vector();
                        row.add(result.getString("id_hotel"));
                        row.add(result.getString("nama_hotel"));
                        row.add(result.getString("alamat_hotel"));
                        row.add(result.getString("fasilitas_hotel"));
                        row.add(result.getString("bintang"));
                        row.add(result.getString("starting_price"));
                        row.add(result.getString("rating_hotel"));
                        model.addRow(row);
                }
                
                
                con.close();
                jtabHasilPencarian.setModel(model);
            }
            catch(SQLException e){
                showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        else if (filtering=="Tidak" && sorting=="Ya"){
            String berdasarkan=cbBerdasarkan.getSelectedItem().toString();
            String secara=cbSecara.getSelectedItem().toString();
            try{
                tfYangDiCari.setText(keyword);
                String sql="SELECT * FROM hotel WHERE nama_hotel LIKE '%"+keyword+"%' OR alamat_hotel LIKE '%"+keyword+"%' ORDER BY "+berdasarkan+" "+secara;
                Connection con=ModuleDB.connectDB();
                Statement stmt=con.createStatement();
                ResultSet result=stmt.executeQuery(sql);
                DefaultTableModel model=(DefaultTableModel)jtabHasilPencarian.getModel();
                model.setRowCount(0);
                while(result.next()){
                        Vector row=new Vector();
                        row.add(result.getString("id_hotel"));
                        row.add(result.getString("nama_hotel"));
                        row.add(result.getString("alamat_hotel"));
                            row.add(result.getString("fasilitas_hotel"));
                        row.add(result.getString("bintang"));
                        row.add(result.getString("starting_price"));
                        row.add(result.getString("rating_hotel"));
                        model.addRow(row);

                    }

                con.close();
                jtabHasilPencarian.setModel(model);
            }
            catch(SQLException e){
                showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            
                   
        }
        else if (filtering=="Ya" && sorting=="Tidak"){
            String filterBerdasarkan=cbPilihan.getSelectedItem().toString();
            
            if(filterBerdasarkan=="Fasilitas"){
                String fasilitas=fasilit.getText().toString();
                try{
                    tfYangDiCari.setText(keyword);
                    String sql="SELECT * FROM hotel WHERE  ( nama_hotel LIKE '%"+keyword+"%' OR alamat_hotel LIKE '%"+keyword+"%' ) AND fasilitas_hotel LIKE '%"+fasilitas+"%' ";
                    Connection con=ModuleDB.connectDB();
                    Statement stmt=con.createStatement();
                    ResultSet result=stmt.executeQuery(sql);
                    DefaultTableModel model=(DefaultTableModel)jtabHasilPencarian.getModel();
                    model.setRowCount(0);
                    while(result.next()){
                            Vector row=new Vector();
                            row.add(result.getString("id_hotel"));
                            row.add(result.getString("nama_hotel"));
                            row.add(result.getString("alamat_hotel"));
                            row.add(result.getString("fasilitas_hotel"));
                            row.add(result.getString("bintang"));
                            row.add(result.getString("starting_price"));
                            row.add(result.getString("rating_hotel"));
                            model.addRow(row);

                        }

                    con.close();
                    jtabHasilPencarian.setModel(model);
                }
                catch(SQLException e){
                    showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
                
            else if(filterBerdasarkan=="Rentang Harga"){
                int minimal =(Integer)min.getValue();
                int maximal =(Integer)max.getValue();
                try{
                    tfYangDiCari.setText(keyword);
                    String sql="SELECT * FROM hotel WHERE ( nama_hotel LIKE '%"+keyword+"%' OR alamat_hotel LIKE '%"+keyword+"%' ) AND starting_price BETWEEN '"+minimal+"' AND '"+maximal+"' ";
                    Connection con=ModuleDB.connectDB();
                    Statement stmt=con.createStatement();
                    ResultSet result=stmt.executeQuery(sql);
                    DefaultTableModel model=(DefaultTableModel)jtabHasilPencarian.getModel();
                    model.setRowCount(0);
                    while(result.next()){
                            Vector row=new Vector();
                            row.add(result.getString("id_hotel"));
                            row.add(result.getString("nama_hotel"));
                            row.add(result.getString("alamat_hotel"));
                            row.add(result.getString("fasilitas_hotel"));
                            row.add(result.getString("bintang"));
                            row.add(result.getString("starting_price"));
                            row.add(result.getString("rating_hotel"));
                            model.addRow(row);

                        }

                    con.close();
                    jtabHasilPencarian.setModel(model);
                }
                catch(SQLException e){
                    showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
                
            else if(filterBerdasarkan=="Rating"){
                    String rat=cbFilterRating.getSelectedItem().toString();
                    int x = min();
                    int y = max();
                        
                       try{   
                        tfYangDiCari.setText(keyword);
                        String sql="SELECT * FROM hotel WHERE ( nama_hotel LIKE '%"+keyword+"%' OR alamat_hotel LIKE '%"+keyword+"%' ) AND rating_hotel BETWEEN '"+x+"' AND '"+y+"' ";
                        Connection con=ModuleDB.connectDB();
                        Statement stmt=con.createStatement();
                        ResultSet result=stmt.executeQuery(sql);
                        DefaultTableModel model=(DefaultTableModel)jtabHasilPencarian.getModel();
                        model.setRowCount(0);
                        while(result.next()){
                                Vector row=new Vector();
                                row.add(result.getString("id_hotel"));
                                row.add(result.getString("nama_hotel"));
                                row.add(result.getString("alamat_hotel"));
                                row.add(result.getString("fasilitas_hotel"));
                                row.add(result.getString("bintang"));
                                row.add(result.getString("starting_price"));
                                row.add(result.getString("rating_hotel"));
                                model.addRow(row);

                            }

                        con.close();
                        jtabHasilPencarian.setModel(model);
                    }
                    catch(SQLException e){
                        showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
            }
            else if(filterBerdasarkan=="Tingkat Bintang"){
                    String rat=cbFilterRating.getSelectedItem().toString();
                    int i = tingkatBintang();
                        
                       try{   
                        tfYangDiCari.setText(keyword);
                        String sql="SELECT * FROM hotel WHERE ( nama_hotel LIKE '%"+keyword+"%' OR alamat_hotel LIKE '%"+keyword+"%' ) AND bintang="+i;
                        Connection con=ModuleDB.connectDB();
                        Statement stmt=con.createStatement();
                        ResultSet result=stmt.executeQuery(sql);
                        DefaultTableModel model=(DefaultTableModel)jtabHasilPencarian.getModel();
                        model.setRowCount(0);
                        while(result.next()){
                                Vector row=new Vector();
                                row.add(result.getString("id_hotel"));
                                row.add(result.getString("nama_hotel"));
                                row.add(result.getString("alamat_hotel"));
                                row.add(result.getString("fasilitas_hotel"));
                                row.add(result.getString("bintang"));
                                row.add(result.getString("starting_price"));
                                row.add(result.getString("rating_hotel"));
                                model.addRow(row);

                            }

                        con.close();
                        jtabHasilPencarian.setModel(model);
                    }
                    catch(SQLException e){
                        showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }   
            }     
        }
        else if (filtering=="Ya" && sorting=="Ya"){
            String filterBerdasarkan=cbPilihan.getSelectedItem().toString();
            String berdasarkan=cbBerdasarkan.getSelectedItem().toString();
            String secara=cbSecara.getSelectedItem().toString();
            
            if(filterBerdasarkan=="Fasilitas"){
                String fasilitas=fasilit.getText().toString();
                try{
                    tfYangDiCari.setText(keyword);
                    String sql="SELECT * FROM hotel WHERE  ( nama_hotel LIKE '%"+keyword+"%' OR alamat_hotel LIKE '%"+keyword+"%' ) AND fasilitas_hotel LIKE '%"+fasilitas+"%' ORDER BY "+berdasarkan+" "+secara;
                    Connection con=ModuleDB.connectDB();
                    Statement stmt=con.createStatement();
                    ResultSet result=stmt.executeQuery(sql);
                    DefaultTableModel model=(DefaultTableModel)jtabHasilPencarian.getModel();
                    model.setRowCount(0);
                    while(result.next()){
                            Vector row=new Vector();
                            row.add(result.getString("id_hotel"));
                            row.add(result.getString("nama_hotel"));
                            row.add(result.getString("alamat_hotel"));
                            row.add(result.getString("fasilitas_hotel"));
                            row.add(result.getString("bintang"));
                            row.add(result.getString("starting_price"));
                            row.add(result.getString("rating_hotel"));
                            model.addRow(row);

                        }

                    con.close();
                    jtabHasilPencarian.setModel(model);
                }
                catch(SQLException e){
                    showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
                
            else if(filterBerdasarkan=="Rentang Harga"){
                int minimal =(Integer)min.getValue();
                int maximal =(Integer)max.getValue();
                try{
                    tfYangDiCari.setText(keyword);
                    String sql="SELECT * FROM hotel WHERE ( nama_hotel LIKE '%"+keyword+"%' OR alamat_hotel LIKE '%"+keyword+"%' ) AND starting_price BETWEEN '"+minimal+"' AND '"+maximal+"' ORDER BY "+berdasarkan+" "+secara;
                    Connection con=ModuleDB.connectDB();
                    Statement stmt=con.createStatement();
                    ResultSet result=stmt.executeQuery(sql);
                    DefaultTableModel model=(DefaultTableModel)jtabHasilPencarian.getModel();
                    model.setRowCount(0);
                    while(result.next()){
                            Vector row=new Vector();
                            row.add(result.getString("id_hotel"));
                            row.add(result.getString("nama_hotel"));
                            row.add(result.getString("alamat_hotel"));
                            row.add(result.getString("fasilitas_hotel"));
                            row.add(result.getString("bintang"));
                            row.add(result.getString("starting_price"));
                            row.add(result.getString("rating_hotel"));
                            model.addRow(row);

                        }

                    con.close();
                    jtabHasilPencarian.setModel(model);
                }
                catch(SQLException e){
                    showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
                
            else if(filterBerdasarkan=="Rating"){
                    String rat=cbFilterRating.getSelectedItem().toString();
                    int x = min();
                    int y = max();
                        
                       try{   
                        tfYangDiCari.setText(keyword);
                        String sql="SELECT * FROM hotel WHERE ( nama_hotel LIKE '%"+keyword+"%' OR alamat_hotel LIKE '%"+keyword+"%' ) AND rating_hotel BETWEEN '"+x+"' AND '"+y+"' ORDER BY "+berdasarkan+" "+secara;
                        Connection con=ModuleDB.connectDB();
                        Statement stmt=con.createStatement();
                        ResultSet result=stmt.executeQuery(sql);
                        DefaultTableModel model=(DefaultTableModel)jtabHasilPencarian.getModel();
                        model.setRowCount(0);
                        while(result.next()){
                                Vector row=new Vector();
                                row.add(result.getString("id_hotel"));
                                row.add(result.getString("nama_hotel"));
                                row.add(result.getString("alamat_hotel"));
                                row.add(result.getString("fasilitas_hotel"));
                                row.add(result.getString("bintang"));
                                row.add(result.getString("starting_price"));
                                row.add(result.getString("rating_hotel"));
                                model.addRow(row);

                            }

                        con.close();
                        jtabHasilPencarian.setModel(model);
                    }
                    catch(SQLException e){
                        showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
            }
            else if(filterBerdasarkan=="Tingkat Bintang"){
                    String rat=cbFilterRating.getSelectedItem().toString();
                    int i = tingkatBintang();
                        
                       try{   
                        tfYangDiCari.setText(keyword);
                        String sql="SELECT * FROM hotel WHERE ( nama_hotel LIKE '%"+keyword+"%' OR alamat_hotel LIKE '%"+keyword+"%' ) AND bintang='"+i+"' ORDER BY "+berdasarkan+" "+secara;
                        Connection con=ModuleDB.connectDB();
                        Statement stmt=con.createStatement();
                        ResultSet result=stmt.executeQuery(sql);
                        DefaultTableModel model=(DefaultTableModel)jtabHasilPencarian.getModel();
                        model.setRowCount(0);
                        while(result.next()){
                                Vector row=new Vector();
                                row.add(result.getString("id_hotel"));
                                row.add(result.getString("nama_hotel"));
                                row.add(result.getString("alamat_hotel"));
                                row.add(result.getString("fasilitas_hotel"));
                                row.add(result.getString("bintang"));
                                row.add(result.getString("starting_price"));
                                row.add(result.getString("rating_hotel"));
                                model.addRow(row);

                            }

                        con.close();
                        jtabHasilPencarian.setModel(model);
                    }
                    catch(SQLException e){
                        showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }   
            }     
        }
    }
    public int tingkatBintang(){
        String cek=cbFilterTingkatBintang.getSelectedItem().toString();
        if(cek=="1"){
            return 1;
        }
        else if(cek=="2"){
            return 2;
        }
        else if(cek=="3"){
            return 3;
        }
        else if(cek=="4"){
            return 4;
        }
        else if(cek=="5"){
            return 5;
        }
        return -1;
    }
    public int min() {
        String cek=cbFilterRating.getSelectedItem().toString();
        if(cek=="Very Good"){
            return 9;
        }
        else if(cek=="Good"){
            return 7;
        }
        else if(cek=="Moderate"){
            return 5;
        }
        else if(cek=="Bad"){
            return 3;
        }
        else if(cek=="Very Bad"){
            return 1;
        }
   
    return -1;
    }
    
    public int max() {
        String cek=cbFilterRating.getSelectedItem().toString();
        if(cek=="Very Good"){
            return 10;
        }
        else if(cek=="Good"){
            return 8;
        }
        else if(cek=="Moderate"){
            return 6;
        }
        else if(cek=="Bad"){
            return 4;
        }
        else if(cek=="Very Bad"){
            return 2;
        }
   
    return -1;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfYangDiCari = new javax.swing.JTextField();
        jButtonCari = new javax.swing.JButton();
        panelFasilitas = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        fasilit = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabHasilPencarian = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        btnLihatDetail = new javax.swing.JButton();
        panelSorting = new javax.swing.JPanel();
        cbBerdasarkan = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbSecara = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        notFound = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbFiturSorting = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbFiturFiltering = new javax.swing.JComboBox<>();
        btnSorting = new javax.swing.JButton();
        btnFiltering = new javax.swing.JButton();
        panelRentangHarga = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        min = new javax.swing.JSpinner();
        max = new javax.swing.JSpinner();
        panelRating = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbFilterRating = new javax.swing.JComboBox<>();
        panelTingkatBintang = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        cbFilterTingkatBintang = new javax.swing.JComboBox<>();
        panelFiltering = new javax.swing.JPanel();
        cbPilihan = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        menBarHome = new javax.swing.JMenuBar();
        menuLihat = new javax.swing.JMenu();
        menuBeranda = new javax.swing.JMenuItem();
        itemDaftarHotel = new javax.swing.JMenuItem();
        itemReview = new javax.swing.JMenuItem();
        itemDaftarUser = new javax.swing.JMenuItem();
        itemDaftarFavorite = new javax.swing.JMenuItem();
        menuModerasi = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuModerasiGambar = new javax.swing.JMenuItem();
        asa = new javax.swing.JMenu();
        itemDaftar = new javax.swing.JMenuItem();
        itemMasuk = new javax.swing.JMenuItem();
        itemGantiPassword = new javax.swing.JMenuItem();
        menuKeluar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Cari  : ");

        tfYangDiCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfYangDiCariActionPerformed(evt);
            }
        });
        tfYangDiCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfYangDiCariKeyPressed(evt);
            }
        });

        jButtonCari.setText("GO !");
        jButtonCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCariActionPerformed(evt);
            }
        });

        panelFasilitas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel3.setText("Fasilitas                :");

        fasilit.setColumns(20);
        fasilit.setRows(5);
        jScrollPane2.setViewportView(fasilit);

        javax.swing.GroupLayout panelFasilitasLayout = new javax.swing.GroupLayout(panelFasilitas);
        panelFasilitas.setLayout(panelFasilitasLayout);
        panelFasilitasLayout.setHorizontalGroup(
            panelFasilitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFasilitasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelFasilitasLayout.setVerticalGroup(
            panelFasilitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFasilitasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFasilitasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jtabHasilPencarian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Hotel", "Alamat", "Fasilitas", "Tingkat Bintang", "Starting Price", "Rating"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtabHasilPencarian);

        jLabel9.setText("Hasil Pencarian");

        btnLihatDetail.setText("Lihat Detail");
        btnLihatDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLihatDetailActionPerformed(evt);
            }
        });

        panelSorting.setBorder(javax.swing.BorderFactory.createTitledBorder("Sorting"));

        cbBerdasarkan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "starting_price", "bintang", "rating_hotel" }));

        jLabel2.setText("Sorting Berdasarkan        :  ");

        jLabel11.setText("Sorting Secara                 :  ");

        cbSecara.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASC", "DESC" }));

        javax.swing.GroupLayout panelSortingLayout = new javax.swing.GroupLayout(panelSorting);
        panelSorting.setLayout(panelSortingLayout);
        panelSortingLayout.setHorizontalGroup(
            panelSortingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSortingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSortingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel11))
                .addGap(57, 57, 57)
                .addGroup(panelSortingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbSecara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbBerdasarkan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        panelSortingLayout.setVerticalGroup(
            panelSortingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSortingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelSortingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBerdasarkan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(panelSortingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbSecara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        notFound.setText(" ");

        jLabel8.setText("Gunakan Fitur Sorting ?");

        cbFiturSorting.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak", "Ya" }));

        jLabel10.setText("Gunakan Fitur Filtering ?");

        cbFiturFiltering.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak", "Ya" }));

        btnSorting.setText("Ok");
        btnSorting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortingActionPerformed(evt);
            }
        });

        btnFiltering.setText("Ok");
        btnFiltering.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilteringActionPerformed(evt);
            }
        });

        panelRentangHarga.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel14.setText("Rentang Harga    :");

        jLabel15.setText("sampai");

        javax.swing.GroupLayout panelRentangHargaLayout = new javax.swing.GroupLayout(panelRentangHarga);
        panelRentangHarga.setLayout(panelRentangHargaLayout);
        panelRentangHargaLayout.setHorizontalGroup(
            panelRentangHargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRentangHargaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(max, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRentangHargaLayout.setVerticalGroup(
            panelRentangHargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRentangHargaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelRentangHargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(max, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89))
        );

        panelRating.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel12.setText("Rating                 :");

        cbFilterRating.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Very Good", "Good", "Moderate", "Bad", "Very Bad" }));

        javax.swing.GroupLayout panelRatingLayout = new javax.swing.GroupLayout(panelRating);
        panelRating.setLayout(panelRatingLayout);
        panelRatingLayout.setHorizontalGroup(
            panelRatingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRatingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(cbFilterRating, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRatingLayout.setVerticalGroup(
            panelRatingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRatingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRatingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbFilterRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelTingkatBintang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel13.setText("Tingkat Bintang   :");

        cbFilterTingkatBintang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "4", "3", "2", "1" }));

        javax.swing.GroupLayout panelTingkatBintangLayout = new javax.swing.GroupLayout(panelTingkatBintang);
        panelTingkatBintang.setLayout(panelTingkatBintangLayout);
        panelTingkatBintangLayout.setHorizontalGroup(
            panelTingkatBintangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTingkatBintangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(20, 20, 20)
                .addComponent(cbFilterTingkatBintang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTingkatBintangLayout.setVerticalGroup(
            panelTingkatBintangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTingkatBintangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTingkatBintangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbFilterTingkatBintang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFiltering.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtering"));

        cbPilihan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "Fasilitas", "Rentang Harga", "Rating", "Tingkat Bintang" }));

        jLabel4.setText("Filtering Berdasarkan        :  ");

        javax.swing.GroupLayout panelFilteringLayout = new javax.swing.GroupLayout(panelFiltering);
        panelFiltering.setLayout(panelFilteringLayout);
        panelFilteringLayout.setHorizontalGroup(
            panelFilteringLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFilteringLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(57, 57, 57)
                .addComponent(cbPilihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        panelFilteringLayout.setVerticalGroup(
            panelFilteringLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFilteringLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelFilteringLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPilihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(76, 76, 76))
        );

        menBarHome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        menuLihat.setText("Lihat");

        menuBeranda.setText("Beranda");
        menuBeranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBerandaActionPerformed(evt);
            }
        });
        menuLihat.add(menuBeranda);

        itemDaftarHotel.setText("Daftar Hotel");
        itemDaftarHotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDaftarHotelActionPerformed(evt);
            }
        });
        menuLihat.add(itemDaftarHotel);

        itemReview.setText("Daftar Review");
        itemReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReviewActionPerformed(evt);
            }
        });
        menuLihat.add(itemReview);

        itemDaftarUser.setText("Daftar User");
        itemDaftarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDaftarUserActionPerformed(evt);
            }
        });
        menuLihat.add(itemDaftarUser);

        itemDaftarFavorite.setText("Daftar Favorite");
        itemDaftarFavorite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDaftarFavoriteActionPerformed(evt);
            }
        });
        menuLihat.add(itemDaftarFavorite);

        menBarHome.add(menuLihat);

        menuModerasi.setText("Moderasi");

        jMenuItem1.setText("No Tlp");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuModerasi.add(jMenuItem1);

        menuModerasiGambar.setText("Gambar");
        menuModerasiGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuModerasiGambarActionPerformed(evt);
            }
        });
        menuModerasi.add(menuModerasiGambar);

        menBarHome.add(menuModerasi);

        asa.setText("User");
        asa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asaActionPerformed(evt);
            }
        });
        asa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                asaKeyPressed(evt);
            }
        });

        itemDaftar.setText("Daftar");
        itemDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDaftarActionPerformed(evt);
            }
        });
        asa.add(itemDaftar);

        itemMasuk.setText("Masuk");
        itemMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMasukActionPerformed(evt);
            }
        });
        asa.add(itemMasuk);

        itemGantiPassword.setText("Ubah Password");
        itemGantiPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGantiPasswordActionPerformed(evt);
            }
        });
        asa.add(itemGantiPassword);

        menuKeluar.setText("Keluar");
        menuKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKeluarActionPerformed(evt);
            }
        });
        asa.add(menuKeluar);

        menBarHome.add(asa);

        setJMenuBar(menBarHome);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfYangDiCari, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCari))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelSorting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(40, 40, 40)
                                .addComponent(cbFiturFiltering, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnFiltering))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addGap(36, 36, 36)
                        .addComponent(cbFiturSorting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnSorting))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelFiltering, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelFasilitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(panelRating, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelTingkatBintang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelRentangHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(notFound))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLihatDetail)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(notFound)
                    .addComponent(jLabel1)
                    .addComponent(tfYangDiCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCari))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLihatDetail)
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbFiturSorting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSorting))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelSorting, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cbFiturFiltering, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFiltering))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelFiltering, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelFasilitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelRentangHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelTingkatBintang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfYangDiCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfYangDiCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfYangDiCariActionPerformed

    private void jButtonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCariActionPerformed
        String keyword=tfYangDiCari.getText();
        
        if(keyword.isEmpty()){
            showMessageDialog(null,"Field harus diisi!");
        }
        else
            pencarian(keyword);
    }//GEN-LAST:event_jButtonCariActionPerformed

    private void tfYangDiCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfYangDiCariKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfYangDiCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setLocationRelativeTo(null);
        
       
        if("guest".equals(ModuleDB.level)){ //kalo user guest
            menuModerasi.setVisible(false);
            itemDaftarHotel.setVisible(false);
            itemReview.setVisible(false);
            itemDaftarUser.setVisible(false);
            itemMasuk.setVisible(false);
            itemDaftar.setVisible(false);
            panelSorting.setVisible(false);
            panelFasilitas.setVisible(false);
            
            panelFiltering.setVisible(false);
            panelFasilitas.setVisible(false);
            panelRentangHarga.setVisible(false);
            panelRating.setVisible(false);
            panelTingkatBintang.setVisible(false);
            asa.setText(ModuleDB.nama_lengkap);
        } 
        else if(ModuleDB.level==null){ //kalo user biasa
            menuKeluar.setVisible(false);
            menuModerasi.setVisible(false);
            itemDaftarHotel.setVisible(false);
            itemReview.setVisible(false);
            itemDaftarUser.setVisible(false);
            itemGantiPassword.setVisible(false);
            itemDaftarFavorite.setVisible(false);
            panelSorting.setVisible(false);
            panelFasilitas.setVisible(false);
            
            panelFiltering.setVisible(false);
            panelFasilitas.setVisible(false);
            panelRentangHarga.setVisible(false);
            panelRating.setVisible(false);
            panelTingkatBintang.setVisible(false);
        }
        else{ //kalo admin
            itemMasuk.setVisible(false);
            itemDaftar.setVisible(false);
            panelSorting.setVisible(false);
            panelFasilitas.setVisible(false);
            
            panelFiltering.setVisible(false);
            panelFasilitas.setVisible(false);
            panelRentangHarga.setVisible(false);
            panelRating.setVisible(false);
            panelTingkatBintang.setVisible(false);
            itemDaftarFavorite.setVisible(false);
            asa.setText(ModuleDB.nama_lengkap);
        }
        pencarian(this.keyword);
    }//GEN-LAST:event_formWindowOpened

    private void btnLihatDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLihatDetailActionPerformed
        int rowIdx=jtabHasilPencarian.getSelectedRow();
        int idHotel=Integer.parseInt(jtabHasilPencarian.getValueAt(rowIdx,0).toString());
        
        LihatDetail er=new LihatDetail(this,idHotel);
        er.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        er.setVisible(true);
        
    }//GEN-LAST:event_btnLihatDetailActionPerformed

    private void menuBerandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBerandaActionPerformed
        new Home().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuBerandaActionPerformed

    private void itemDaftarHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDaftarHotelActionPerformed
        new DaftarHotel().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itemDaftarHotelActionPerformed

    private void itemDaftarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDaftarUserActionPerformed
        DaftarUser m=new DaftarUser();
        m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        m.setVisible(true);
    }//GEN-LAST:event_itemDaftarUserActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        ModerasiNoTlp m=new ModerasiNoTlp();
        m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        m.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuModerasiGambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuModerasiGambarActionPerformed
        ModerasiGambar m=new ModerasiGambar();
        m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        m.setVisible(true);
    }//GEN-LAST:event_menuModerasiGambarActionPerformed

    private void itemDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDaftarActionPerformed
        new Register().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itemDaftarActionPerformed

    private void itemMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMasukActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itemMasukActionPerformed

    private void menuKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKeluarActionPerformed
        ModuleDB.username=null;
        ModuleDB.level=null;
        ModuleDB.id_user=0;
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuKeluarActionPerformed

    private void asaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asaActionPerformed

    }//GEN-LAST:event_asaActionPerformed

    private void asaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_asaKeyPressed

    }//GEN-LAST:event_asaKeyPressed

    private void itemGantiPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGantiPasswordActionPerformed
        UbahPassword m=new UbahPassword();
        m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        m.setVisible(true);
    }//GEN-LAST:event_itemGantiPasswordActionPerformed

    private void btnSortingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortingActionPerformed
        String sorting=cbFiturSorting.getSelectedItem().toString();
        if(sorting == "Ya")
            panelSorting.setVisible(true);
        
        if(sorting == "Tidak")
            panelSorting.setVisible(false);
        
    }//GEN-LAST:event_btnSortingActionPerformed

    private void btnFilteringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilteringActionPerformed
        String filtering=cbFiturFiltering.getSelectedItem().toString();
        String pilihan=cbPilihan.getSelectedItem().toString();
        if(filtering == "Ya"){
            panelFiltering.setVisible(true);
            panelFasilitas.setVisible(false);
            panelRentangHarga.setVisible(false);
            panelRating.setVisible(false);
            panelTingkatBintang.setVisible(false);
            
            if(pilihan == "Fasilitas")
                panelFasilitas.setVisible(true);
            else if(pilihan == "Rentang Harga")
                panelRentangHarga.setVisible(true);
            else if(pilihan == "Rating")
                panelRating.setVisible(true);
            else if(pilihan == "Tingkat Bintang")
                panelTingkatBintang.setVisible(true);
            else{
              panelFasilitas.setVisible(false);
              panelRentangHarga.setVisible(false);
              panelRating.setVisible(false);
              panelTingkatBintang.setVisible(false);  
            }
           
        }
        if(filtering == "Tidak"){
            panelFiltering.setVisible(false);
            panelFasilitas.setVisible(false);
            panelRentangHarga.setVisible(false);
            panelRating.setVisible(false);
            panelTingkatBintang.setVisible(false);
        }
            
    }//GEN-LAST:event_btnFilteringActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String keyword=tfYangDiCari.getText();
        
        if(keyword.isEmpty()){
            showMessageDialog(null,"Field harus diisi!");
        }
        else
            pencarian(keyword);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void itemReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReviewActionPerformed
        DaftarReview m=new DaftarReview();
        m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        m.setVisible(true);
    }//GEN-LAST:event_itemReviewActionPerformed

    private void itemDaftarFavoriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDaftarFavoriteActionPerformed
        DaftarFavorite m=new DaftarFavorite();
        m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        m.setVisible(true);
    }//GEN-LAST:event_itemDaftarFavoriteActionPerformed

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
            java.util.logging.Logger.getLogger(HasilPencarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HasilPencarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HasilPencarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HasilPencarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HasilPencarian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu asa;
    private javax.swing.JButton btnFiltering;
    private javax.swing.JButton btnLihatDetail;
    private javax.swing.JButton btnSorting;
    private javax.swing.JComboBox<String> cbBerdasarkan;
    private javax.swing.JComboBox<String> cbFilterRating;
    private javax.swing.JComboBox<String> cbFilterTingkatBintang;
    private javax.swing.JComboBox<String> cbFiturFiltering;
    private javax.swing.JComboBox<String> cbFiturSorting;
    private javax.swing.JComboBox<String> cbPilihan;
    private javax.swing.JComboBox<String> cbSecara;
    private javax.swing.JTextArea fasilit;
    private javax.swing.JMenuItem itemDaftar;
    private javax.swing.JMenuItem itemDaftarFavorite;
    private javax.swing.JMenuItem itemDaftarHotel;
    private javax.swing.JMenuItem itemDaftarUser;
    private javax.swing.JMenuItem itemGantiPassword;
    private javax.swing.JMenuItem itemMasuk;
    private javax.swing.JMenuItem itemReview;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonCari;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtabHasilPencarian;
    private javax.swing.JSpinner max;
    private javax.swing.JMenuBar menBarHome;
    private javax.swing.JMenuItem menuBeranda;
    private javax.swing.JMenuItem menuKeluar;
    private javax.swing.JMenu menuLihat;
    private javax.swing.JMenu menuModerasi;
    private javax.swing.JMenuItem menuModerasiGambar;
    private javax.swing.JSpinner min;
    private javax.swing.JLabel notFound;
    private javax.swing.JPanel panelFasilitas;
    private javax.swing.JPanel panelFiltering;
    private javax.swing.JPanel panelRating;
    private javax.swing.JPanel panelRentangHarga;
    private javax.swing.JPanel panelSorting;
    private javax.swing.JPanel panelTingkatBintang;
    private javax.swing.JTextField tfYangDiCari;
    // End of variables declaration//GEN-END:variables
}
