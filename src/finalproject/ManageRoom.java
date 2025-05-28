/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package finalproject;

/**
 *
 * @author Anjelin Ligiana Nainggolan
 */

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class ManageRoom extends javax.swing.JFrame {

    static Iterable<ManageRoom> getcmbType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static Iterable<ManageRoom> get() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pat = null;
    Statement at = null;

    /**
     * Creates new form Frame1
     */
    public ManageRoom() {
    initComponents();
    connect();
    ShowRecordInTable();
    addTableClickListener();
    initializeSearchFilter();
    addClearButtonListener();
    addTableClickListener();
   
    
    // Tambahin ini supaya scroll bar muncul saat perlu
    jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    roomTable.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
    roomTable.getTableHeader().setBackground(new java.awt.Color(0, 102, 204));
    roomTable.getTableHeader().setForeground(java.awt.Color.blue);

    // Atur tinggi baris tabel
    roomTable.setRowHeight(24);

    // Meratakan teks kolom Price (kolom ke-4, indeks 3) ke kanan
    javax.swing.table.DefaultTableCellRenderer rightRenderer = new javax.swing.table.DefaultTableCellRenderer();
    rightRenderer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    roomTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

    
    btnAddRoom.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
    }
    });
    
    Back.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        backActionPerformed(evt);
    }
    });
    
    btnAddRoom.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        
        ShowRecordInTable(); 
    }
});


}
    private void backActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        new Dashboard().setVisible(true);
    }

    
    private void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2h_oovp", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Failed: " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void ShowRecordInTable() {
        int c=0;
            try {
                pat=conn.prepareStatement("select * from rooms");
                rs=pat.executeQuery();
                ResultSetMetaData rsmd=rs.getMetaData();
                c=rsmd.getColumnCount();
                DefaultTableModel DModel=(DefaultTableModel) roomTable.getModel();
                DModel.setRowCount(0);
                while(rs.next()) {
                    Vector column = new Vector();
                    column.add(rs.getString("number"));
                    column.add(rs.getString("type"));
                    column.add(rs.getString("bed"));
                    column.add(rs.getString("availability"));
                    double priceValue = rs.getDouble("price");
                    String formattedPrice = String.format("Rp. %,d,-", (long) priceValue).replace(",", ".");
    
                    column.add(formattedPrice);
                    DModel.addRow(column);
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManageRoom.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static Vector<String> getRoomNumbersByType(String type) {
    Vector<String> roomNumbers = new Vector<>();

    try {
        Connection conn;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2h_oovp", "root", "");

        PreparedStatement stmt = conn.prepareStatement("SELECT number FROM rooms WHERE type = ? AND availability = 'available'");
        stmt.setString(1, type);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            roomNumbers.add(rs.getString("number"));
        }

        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return roomNumbers;
}


    /**e
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        roomTable = new javax.swing.JTable();
        btnAddRoom = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtroomNumber = new javax.swing.JTextField();
        cmbType = new javax.swing.JComboBox<>();
        txtPrice = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Back = new javax.swing.JButton();
        cmbBed = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        EditRoom = new javax.swing.JButton();
        DeleteRoom = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        ClearForm = new javax.swing.JButton();
        Next = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roomTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        roomTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Room Number", "Type", "Bed", "Availability", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(roomTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, 720, 190));

        btnAddRoom.setBackground(new java.awt.Color(204, 255, 255));
        btnAddRoom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddRoom.setForeground(new java.awt.Color(0, 0, 204));
        btnAddRoom.setText("Add Room");
        getContentPane().add(btnAddRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 460, 110, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finalproject/p2h_attribute/ManageRoom.png"))); // NOI18N
        jLabel3.setText("ROOM MANAGEMENT ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, 36));
        getContentPane().add(txtroomNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 310, 30));

        cmbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Standard", "Deluxe", "Suite" }));
        getContentPane().add(cmbType, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 310, 30));
        getContentPane().add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 310, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Room Number :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Room Type :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Price :");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, -1, -1));

        Back.setBackground(new java.awt.Color(255, 204, 204));
        Back.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Back.setForeground(new java.awt.Color(204, 0, 0));
        Back.setText("Back to Dashboard");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });
        getContentPane().add(Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 180, 30));

        cmbBed.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Double" }));
        cmbBed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBedActionPerformed(evt);
            }
        });
        getContentPane().add(cmbBed, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 310, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Room Bed :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        EditRoom.setBackground(new java.awt.Color(255, 255, 255));
        EditRoom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EditRoom.setForeground(new java.awt.Color(51, 0, 204));
        EditRoom.setText("Edit");
        EditRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditRoomActionPerformed(evt);
            }
        });
        getContentPane().add(EditRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 460, 60, 30));

        DeleteRoom.setBackground(new java.awt.Color(255, 255, 255));
        DeleteRoom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteRoom.setForeground(new java.awt.Color(255, 51, 51));
        DeleteRoom.setText("Delete");
        DeleteRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteRoomActionPerformed(evt);
            }
        });
        getContentPane().add(DeleteRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, 90, 30));

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, 270, 30));

        ClearForm.setBackground(new java.awt.Color(255, 255, 255));
        ClearForm.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ClearForm.setForeground(new java.awt.Color(255, 153, 102));
        ClearForm.setText("Clear");
        ClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearFormActionPerformed(evt);
            }
        });
        getContentPane().add(ClearForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 460, 70, 30));

        Next.setBackground(new java.awt.Color(204, 255, 204));
        Next.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Next.setForeground(new java.awt.Color(0, 153, 0));
        Next.setText("Next");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });
        getContentPane().add(Next, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 460, 90, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        jLabel1.setText("@anjelin.ln");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 580, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void initializeSearchFilter() {
        
        txtSearch.setText("Search...");
        txtSearch.setForeground(Color.GRAY); 
        txtSearch.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals("Search...")) {
                    txtSearch.setText(""); // Hapus placeholder ketika field aktif
                    txtSearch.setForeground(Color.BLACK); // Mengubah teks menjadi hitam
                }
            }

            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setText("Search..."); // Menampilkan placeholder kembali jika tidak ada input
                    txtSearch.setForeground(Color.GRAY); // Menampilkan placeholder dalam warna abu-abu
                }
            }
        });
        
    DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    roomTable.setRowSorter(sorter);

    txtSearch.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) {
            searchFilter(sorter);
        }

        public void removeUpdate(DocumentEvent e) {
            searchFilter(sorter);
        }

        public void changedUpdate(DocumentEvent e) {
            searchFilter(sorter);
        }
    });
}

    private void searchFilter(TableRowSorter<DefaultTableModel> sorter) {
    String text = txtSearch.getText().trim();
    if (text.isEmpty() || text.equals("Search...")) {
        sorter.setRowFilter(null); // Jangan filter, tampilkan semua
    } else {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
    }
}

    
    
    private void cmbBedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBedActionPerformed

    private void EditRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditRoomActionPerformed
        
    if (txtroomNumber.getText().equals("") || txtPrice.getText().equals("")) {
        JOptionPane.showMessageDialog(this, "All fields are required.");
        return;
    }

    try {
        String rawPrice = txtPrice.getText()
                .replace("Rp", "")
                .replace(".", "")
                .replace(",", "")
                .replace("-", "")
                .trim();
        double price = Double.parseDouble(rawPrice);

        pat = conn.prepareStatement("UPDATE rooms SET type=?, bed=?, price=? WHERE number=?");
        pat.setString(1, cmbType.getSelectedItem().toString());
        pat.setString(2, cmbBed.getSelectedItem().toString());
        pat.setDouble(3, price);
        pat.setString(4, txtroomNumber.getText());
        int updated = pat.executeUpdate();

        if (updated > 0) {
            JOptionPane.showMessageDialog(this, "Room Updated");
            ShowRecordInTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Room Not Found");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ManageRoom.class.getName()).log(Level.SEVERE, null, ex);
    }
    
        // TODO add your handling code here:
    }//GEN-LAST:event_EditRoomActionPerformed

    private void DeleteRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteRoomActionPerformed
       if (txtroomNumber.getText().equals("")) {
        JOptionPane.showMessageDialog(this, "Please enter Room Number to delete.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this room?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            pat = conn.prepareStatement("DELETE FROM rooms WHERE number=?");
            pat.setString(1, txtroomNumber.getText());
            int deleted = pat.executeUpdate();

            if (deleted > 0) {
                JOptionPane.showMessageDialog(this, "Room Deleted");
                ShowRecordInTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Room Not Found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteRoomActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void ClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearFormActionPerformed
    txtroomNumber.setText("");
    txtPrice.setText("");
    txtSearch.setText("");
    cmbType.setSelectedIndex(0);
    cmbBed.setSelectedIndex(0);        // TODO add your handling code here:
    }//GEN-LAST:event_ClearFormActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        new BookingRoom().setVisible(true);       // TODO add your handling code here:
    }//GEN-LAST:event_NextActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        new Dashboard().setVisible(true);       
    }//GEN-LAST:event_BackActionPerformed

   
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        if(txtroomNumber.getText().equals("")){
            JOptionPane.showMessageDialog(this,"All Field is Required");
            txtroomNumber.requestFocus();
        }
        else if(txtPrice.getText().equals("")){
            JOptionPane.showMessageDialog(this,"All Field is Required");
            txtPrice.requestFocus();
        }
        else{
            try {
                pat=conn.prepareStatement("select * from rooms where number=?");
                pat.setString(1, txtroomNumber.getText());
                rs=pat.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(this,"Room Number Already Exist");
            }
                else{
                    pat=conn.prepareStatement("insert into rooms(number, type, Bed, availability, price) values(?,?,?,?,?)");
                    pat.setString(1, txtroomNumber.getText());
                    pat.setString(2, cmbType.getItemAt(cmbType.getSelectedIndex()));
                    pat.setString(3, cmbBed.getItemAt(cmbBed.getSelectedIndex()));
                    pat.setString(4, "available");
                     String rawPrice = txtPrice.getText()
                    .replace("Rp", ".")
                    .replace(".", "")
                    .replace(",", "")
                    .replace("-", "")
                    .trim();
                double price = Double.parseDouble(rawPrice);

                pat.setDouble(5, price);
                pat.executeUpdate();

                JOptionPane.showMessageDialog(this, "Room Added");
                ShowRecordInTable();
                txtroomNumber.setText("");
                txtPrice.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
}
    
    
    private void addTableClickListener() {
    roomTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int selectedRowView = roomTable.getSelectedRow();
            System.out.println("Row clicked: " + selectedRowView); // Debugging

            if (selectedRowView == -1) {
                return;
            }

            // Konversi indeks baris dari view ke model
            int selectedRowModel = roomTable.convertRowIndexToModel(selectedRowView);

            System.out.println("Model row index: " + selectedRowModel); // Debugging

            DefaultTableModel model = (DefaultTableModel) roomTable.getModel();

            // Ambil data berdasarkan indeks yang benar
            String number = model.getValueAt(selectedRowModel, 0).toString();
            String type = model.getValueAt(selectedRowModel, 1).toString();
            String bed = model.getValueAt(selectedRowModel, 2).toString();
            String availability = model.getValueAt(selectedRowModel, 3).toString();
            String formattedPrice = model.getValueAt(selectedRowModel, 4).toString();

            // Menampilkan data ke UI
            txtroomNumber.setText(number);
            cmbType.setSelectedItem(type);
            cmbBed.setSelectedItem(bed);

            String cleanPrice = formattedPrice
                .replace("Rp.", "")
                .replace(".", "")
                .replace(",-", "")
                .trim();

            txtPrice.setText("Rp" + cleanPrice);
        }
    });
}


    private void clearForm() {
    txtroomNumber.setText("");
    txtPrice.setText("");
    txtSearch.setText("");
    cmbType.setSelectedIndex(0);
    cmbBed.setSelectedIndex(0);

    // Kalau ada komponen lain yang perlu direset, tambahkan di sini.
}
    private void addClearButtonListener() {
        ClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearForm();  // Panggil method untuk mengosongkan form
            }
        });
    }
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
            java.util.logging.Logger.getLogger(ManageRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageRoom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton ClearForm;
    private javax.swing.JButton DeleteRoom;
    private javax.swing.JButton EditRoom;
    private javax.swing.JButton Next;
    private javax.swing.JButton btnAddRoom;
    private javax.swing.JComboBox<String> cmbBed;
    private javax.swing.JComboBox<String> cmbType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable roomTable;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtroomNumber;
    // End of variables declaration//GEN-END:variables

    Object getRoomType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String getRoomNumber() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    String getRoomBed() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
