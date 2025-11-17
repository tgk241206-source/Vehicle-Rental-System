package ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Staff extends JFrame {
    private MainMenu mainMenu;
    private Map<String, String> staffMembers = new HashMap<>();
    
    // Staff fields
    private JTextField txtStaffId, txtStaffName, txtStaffPhone, txtStaffEmail;
    private JComboBox<String> cbStaffRole;
    private JTextArea txtStaffNotes;
    
    public Staff(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        initializeData();
        initializeUI();
    }
    
    private void initializeData() {
        // Thêm dữ liệu nhân viên mẫu
        staffMembers.put("STAFF001", "Nguyễn Thị D - Nhân viên - 0901111111");
        staffMembers.put("STAFF002", "Trần Văn E - Quản lý - 0902222222");
        staffMembers.put("STAFF003", "Lê Thị F - Nhân viên - 0903333333");
    }
    
    private void initializeUI() {
        setTitle("QUẢN LÝ NHÂN VIÊN");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                returnToMainMenu();
            }
        });
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
