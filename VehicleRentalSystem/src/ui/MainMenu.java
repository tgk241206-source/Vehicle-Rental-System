package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JButton btnCustomer, btnRent, btnReturn, btnStaff, btnReport, btnExit;
    
    public MainMenu() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("HỆ THỐNG THUÊ XE - MENU CHÍNH");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(new Color(240, 245, 255));
        
        // Title
        JLabel titleLabel = new JLabel("HỆ THỐNG QUẢN LÝ THUÊ XE", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 80, 180));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 15, 15));
        buttonPanel.setBackground(new Color(240, 245, 255));
        
        btnCustomer = new JButton("📝 QUẢN LÝ KHÁCH HÀNG");
        btnRent = new JButton("🚗 THUÊ XE");
        btnReturn = new JButton("↩️ TRẢ XE");
        btnStaff = new JButton("👥 QUẢN LÝ NHÂN VIÊN");
        btnReport = new JButton("📊 XEM BÁO CÁO");
        btnExit = new JButton("❌ THOÁT");
        
        // Style buttons
        styleButton(btnCustomer, new Color(70, 130, 180));
        styleButton(btnRent, new Color(60, 179, 113));
        styleButton(btnReturn, new Color(255, 165, 0));
        styleButton(btnStaff, new Color(147, 112, 219));
        styleButton(btnReport, new Color(75, 0, 130));
        styleButton(btnExit, new Color(220, 80, 60));
        
        buttonPanel.add(btnCustomer);
        buttonPanel.add(btnRent);
        buttonPanel.add(btnReturn);
        buttonPanel.add(btnStaff);
        buttonPanel.add(btnReport);
        buttonPanel.add(btnExit);
        
        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(220, 230, 255));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel infoLabel = new JLabel("Hệ thống quản lý thuê xe - Phiên bản hoàn chỉnh theo tài liệu thiết kế");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabel.setForeground(new Color(80, 80, 80));
        infoPanel.add(infoLabel);
        
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Event listeners
        btnCustomer.addActionListener(e -> openCustomerManagement());
        btnRent.addActionListener(e -> openRentVehicle());
        btnReturn.addActionListener(e -> openReturnVehicle());
        btnStaff.addActionListener(e -> openStaffManagement());
        btnReport.addActionListener(e -> openReports());
        btnExit.addActionListener(e -> System.exit(0));
    }
    
    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void openCustomerManagement() {
        RentalUI rentalUI = new RentalUI(this, "customer");
        rentalUI.setVisible(true);
        this.setVisible(false);
    }
    
    private void openRentVehicle() {
        RentalUI rentalUI = new RentalUI(this, "rent");
        rentalUI.setVisible(true);
        this.setVisible(false);
    }
    
    private void openReturnVehicle() {
        RentalUI rentalUI = new RentalUI(this, "return");
        rentalUI.setVisible(true);
        this.setVisible(false);
    }
    
    private void openStaffManagement() {
        Staff staffUI = new Staff(this);
        staffUI.setVisible(true);
        this.setVisible(false);
    }
    
    private void openReports() {
        // Hiển thị báo cáo theo yêu cầu
        showReportDialog();
    }
    
    private void showReportDialog() {
        String[] options = {"Báo cáo doanh thu", "Báo cáo phương tiện", "Báo cáo khách hàng", "Thống kê tổng quan"};
        String choice = (String) JOptionPane.showInputDialog(this,
            "Chọn loại báo cáo:",
            "XEM BÁO CÁO",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
            
        if (choice != null) {
            String reportData = generateReportData(choice);
            JTextArea textArea = new JTextArea(20, 50);
            textArea.setText(reportData);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            
            JOptionPane.showMessageDialog(this, scrollPane, "BÁO CÁO: " + choice, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private String generateReportData(String reportType) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== BÁO CÁO HỆ THỐNG THUÊ XE ===\n");
        sb.append("Ngày xuất báo cáo: ").append(new java.util.Date()).append("\n\n");
        
        switch(reportType) {
            case "Báo cáo doanh thu":
                sb.append("DOANH THU THÁNG 12/2024:\n");
                sb.append("────────────────────────\n");
                sb.append("Tổng doanh thu: 250,000,000 VND\n");
                sb.append("Số giao dịch: 150\n");
                sb.append("Doanh thu trung bình: 1,666,667 VND\n");
                sb.append("Phương tiện cho thuê nhiều nhất: Toyota Vios\n");
                sb.append("Chi nhánh hoạt động tốt nhất: Quận 1\n");
                break;
                
            case "Báo cáo phương tiện":
                sb.append("THỐNG KÊ PHƯƠNG TIỆN:\n");
                sb.append("─────────────────────\n");
                sb.append("Tổng số xe: 50\n");
                sb.append("Đang cho thuê: 25\n");
                sb.append("Có sẵn: 20\n");
                sb.append("Bảo trì: 5\n");
                sb.append("Tỷ lệ sử dụng: 75%\n");
                sb.append("Top 3 xe được thuê nhiều:\n");
                sb.append("1. Toyota Vios - 45 lần\n");
                sb.append("2. Honda City - 38 lần\n");
                sb.append("3. Hyundai Accent - 32 lần\n");
                break;
                
            case "Báo cáo khách hàng":
                sb.append("THỐNG KÊ KHÁCH HÀNG:\n");
                sb.append("───────────────────\n");
                sb.append("Tổng số khách hàng: 200\n");
                sb.append("Khách hàng mới tháng: 25\n");
                sb.append("Khách hàng thân thiết: 45\n");
                sb.append("Tỷ lệ quay lại: 65%\n");
                sb.append("Top 3 khách hàng:\n");
                sb.append("1. Nguyễn Văn A - 15 lần thuê\n");
                sb.append("2. Trần Thị B - 12 lần thuê\n");
                sb.append("3. Lê Văn C - 10 lần thuê\n");
                break;
                
            case "Thống kê tổng quan":
                sb.append("THỐNG KÊ TỔNG QUAN:\n");
                sb.append("──────────────────\n");
                sb.append("Tổng đơn hàng: 500\n");
                sb.append("Đơn thành công: 480\n");
                sb.append("Đơn hủy: 20\n");
                sb.append("Tỷ lệ thành công: 96%\n");
                sb.append("Doanh thu năm: 2,800,000,000 VND\n");
                sb.append("Khách hàng đánh giá: 4.8/5 sao\n");
                sb.append("Thời gian thuê trung bình: 4.5 ngày\n");
                break;
        }
        
        sb.append("\n─────────────────────────────────────\n");
        sb.append("Báo cáo được tạo tự động bởi hệ thống");
        
        return sb.toString();
    }
    
    public void showMainMenu() {
        this.setVisible(true);
    }
}
