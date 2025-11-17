package ui;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class RentalUI extends JFrame {
    private MainMenu mainMenu;
    private String currentTab;
    
    // Dữ liệu mẫu theo Class Diagram
    private Map<String, String> customers = new HashMap<>(); // userID -> info
    private Map<String, String> vehicles = new HashMap<>();  // vehicleID -> info
    private Map<String, String> rentals = new HashMap<>();   // orderID -> info
    
    // Customer fields
    private JTextField txtUserId, txtFullName, txtPhone, txtEmail, txtAddress;
    private JComboBox<String> cbGender;
    private JTextArea txtCustomerNotes;
    
    // Rent fields
    private JTextField txtRentUserId, txtRentVehicleId, txtRentDays;
    private JComboBox<String> cbPaymentMethod;
    private JTextArea txtRentDetails;
    
    // Return fields
    private JTextField txtReturnOrderId, txtReturnVehicleId;
    private JTextArea txtReturnInfo;
    
    public RentalUI(MainMenu mainMenu, String tab) {
        this.mainMenu = mainMenu;
        this.currentTab = tab;
        initializeData();
        initializeUI();
    }
    
    private void initializeData() {
        // Thêm dữ liệu mẫu theo Class Diagram
        customers.put("USER001", "Nguyễn Văn A - 0123456789 - user001@email.com");
        customers.put("USER002", "Trần Thị B - 0987654321 - user002@email.com");
        customers.put("USER003", "Lê Văn C - 0912345678 - user003@email.com");
        
        vehicles.put("CAR001", "Toyota Vios - Car - Gasoline - 4 seats - 500,000 VND/day");
        vehicles.put("CAR002", "Honda City - Car - Gasoline - 4 seats - 550,000 VND/day");
        vehicles.put("BIKE001", "Honda Vision - Motorbike - Gasoline - 150,000 VND/day");
        
        rentals.put("ORDER001", "USER001 - CAR001 - 3 days - 1,500,000 VND - Pending");
    }
    
    private void initializeUI() {
        setTitle("QUẢN LÝ THUÊ XE - GIAO DIỆN NHÂN VIÊN");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                returnToMainMenu();
            }
        });
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        tabbedPane.addTab("👥 QUẢN LÝ KHÁCH HÀNG", createCustomerPanel());
        tabbedPane.addTab("🚗 THUÊ XE", createRentPanel());
        tabbedPane.addTab("↩️ TRẢ XE", createReturnPanel());
        
        // Set current tab
        switch(currentTab) {
            case "customer": tabbedPane.setSelectedIndex(0); break;
            case "rent": tabbedPane.setSelectedIndex(1); break;
            case "return": tabbedPane.setSelectedIndex(2); break;
        }
        
        JButton btnBack = new JButton("🏠 QUAY LẠI MENU CHÍNH");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(100, 100, 100));
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(e -> returnToMainMenu());
        
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);
    }
    
    private JPanel createCustomerPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(createTitledBorder("THÔNG TIN KHÁCH HÀNG", Color.BLUE));
        
        txtUserId = new JTextField();
        txtFullName = new JTextField();
        txtPhone = new JTextField();
        txtEmail = new JTextField();
        txtAddress = new JTextField();
        cbGender = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"});
        txtCustomerNotes = new JTextArea(3, 20);
        JScrollPane notesScroll = new JScrollPane(txtCustomerNotes);
        
        formPanel.add(createLabel("Mã UserID:"));
        formPanel.add(txtUserId);
        formPanel.add(createLabel("Họ Tên:"));
        formPanel.add(txtFullName);
        formPanel.add(createLabel("SĐT:"));
        formPanel.add(txtPhone);
        formPanel.add(createLabel("Email:"));
        formPanel.add(txtEmail);
        formPanel.add(createLabel("Giới tính:"));
        formPanel.add(cbGender);
        formPanel.add(createLabel("Địa Chỉ:"));
        formPanel.add(txtAddress);
        formPanel.add(createLabel("Ghi chú:"));
        formPanel.add(notesScroll);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnAdd = createStyledButton("➕ THÊM KH", new Color(60, 179, 113));
        JButton btnUpdate = createStyledButton("✏️ CẬP NHẬT", new Color(70, 130, 180));
        JButton btnClear = createStyledButton("🗑️ XÓA FORM", new Color(220, 80, 60));
        JButton btnList = createStyledButton("📋 DS KH", new Color(147, 112, 219));
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnList);
        
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Events
        btnAdd.addActionListener(e -> addCustomer());
        btnUpdate.addActionListener(e -> updateCustomer());
        btnClear.addActionListener(e -> clearCustomerForm());
        btnList.addActionListener(e -> showCustomerList());
        
        return panel;
    }
    
    private JPanel createRentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(createTitledBorder("THÔNG TIN THUÊ XE", Color.GREEN));
        
        txtRentUserId = new JTextField();
        txtRentVehicleId = new JTextField();
        txtRentDays = new JTextField();
        cbPaymentMethod = new JComboBox<>(new String[]{"Cash", "Credit Card", "Online"});
        txtRentDetails = new JTextArea(4, 20);
        JScrollPane detailsScroll = new JScrollPane(txtRentDetails);
        
        formPanel.add(createLabel("Mã UserID:"));
        formPanel.add(txtRentUserId);
        formPanel.add(createLabel("Mã VehicleID:"));
        formPanel.add(txtRentVehicleId);
        formPanel.add(createLabel("Số Ngày:"));
        formPanel.add(txtRentDays);
        formPanel.add(createLabel("Phương thức TT:"));
        formPanel.add(cbPaymentMethod);
        formPanel.add(createLabel("Chi tiết:"));
        formPanel.add(detailsScroll);
        
        // Calculate price button
        JButton btnCalculate = createStyledButton("💰 TÍNH TIỀN", new Color(255, 165, 0));
        formPanel.add(btnCalculate);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnRent = createStyledButton("✅ XÁC NHẬN THUÊ", new Color(60, 179, 113));
        JButton btnCheck = createStyledButton("🔍 KIỂM TRA", new Color(70, 130, 180));
        
        buttonPanel.add(btnRent);
        buttonPanel.add(btnCheck);
        
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Events
        btnRent.addActionListener(e -> rentVehicle());
        btnCheck.addActionListener(e -> checkRentalInfo());
        btnCalculate.addActionListener(e -> calculatePrice());
        
        return panel;
    }
    
    private JPanel createReturnPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(createTitledBorder("THÔNG TIN TRẢ XE", Color.ORANGE));
        
        txtReturnOrderId = new JTextField();
        txtReturnVehicleId = new JTextField();
        txtReturnInfo = new JTextArea(8, 20);
        txtReturnInfo.setEditable(false);
        JScrollPane infoScroll = new JScrollPane(txtReturnInfo);
        
        formPanel.add(createLabel("Mã OrderID:"));
        formPanel.add(txtReturnOrderId);
        formPanel.add(createLabel("Mã VehicleID:"));
        formPanel.add(txtReturnVehicleId);
        formPanel.add(createLabel("Thông Tin:"));
        formPanel.add(infoScroll);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnCheck = createStyledButton("🔍 KIỂM TRA", new Color(70, 130, 180));
        JButton btnReturn = createStyledButton("✅ XÁC NHẬN TRẢ", new Color(60, 179, 113));
        JButton btnCalculateFee = createStyledButton("🧮 TÍNH PHÍ", new Color(255, 140, 0));
        
        buttonPanel.add(btnCheck);
        buttonPanel.add(btnReturn);
        buttonPanel.add(btnCalculateFee);
        
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Events
        btnCheck.addActionListener(e -> checkReturnInfo());
        btnReturn.addActionListener(e -> returnVehicle());
        btnCalculateFee.addActionListener(e -> calculateReturnFee());
        
        return panel;
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    private TitledBorder createTitledBorder(String title, Color color) {
        TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(color, 2), 
            title
        );
        border.setTitleFont(new Font("Arial", Font.BOLD, 14));
        border.setTitleColor(color);
        return border;
    }
    
    private void returnToMainMenu() {
        this.dispose();
        mainMenu.showMainMenu();
    }
    
    // BUSINESS LOGIC METHODS - Kết nối với Service
    private void addCustomer() {
        String userId = txtUserId.getText().trim();
        String fullName = txtFullName.getText().trim();
        String phone = txtPhone.getText().trim();
        
        if (userId.isEmpty() || fullName.isEmpty()) {
            showError("Vui lòng nhập UserID và Họ tên!");
            return;
        }
        
        if (customers.containsKey(userId)) {
            showError("UserID đã tồn tại!");
            return;
        }
        
        String customerInfo = String.format("%s - %s - %s", fullName, phone, txtEmail.getText());
        customers.put(userId, customerInfo);
        
        showSuccess("Thêm khách hàng " + fullName + " thành công!\nUserID: " + userId);
        clearCustomerForm();
    }
    
    private void updateCustomer() {
        String userId = txtUserId.getText().trim();
        if (!customers.containsKey(userId)) {
            showError("Không tìm thấy UserID!");
            return;
        }
        
        String fullName = txtFullName.getText().trim();
        String customerInfo = String.format("%s - %s - %s", fullName, txtPhone.getText(), txtEmail.getText());
        customers.put(userId, customerInfo);
        
        showSuccess("Cập nhật thông tin khách hàng thành công!");
    }
    
    private void clearCustomerForm() {
        txtUserId.setText("");
        txtFullName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        cbGender.setSelectedIndex(0);
        txtCustomerNotes.setText("");
    }
    
    private void showCustomerList() {
        StringBuilder sb = new StringBuilder("DANH SÁCH KHÁCH HÀNG:\n\n");
        for (Map.Entry<String, String> entry : customers.entrySet()) {
            sb.append("UserID: ").append(entry.getKey())
              .append(" | Thông tin: ").append(entry.getValue()).append("\n");
        }
        
        JTextArea textArea = new JTextArea(15, 50);
        textArea.setText(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        JOptionPane.showMessageDialog(this, scrollPane, "DANH SÁCH KHÁCH HÀNG", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void rentVehicle() {
        String userId = txtRentUserId.getText().trim();
        String vehicleId = txtRentVehicleId.getText().trim();
        String days = txtRentDays.getText().trim();
        
        if (userId.isEmpty() || vehicleId.isEmpty() || days.isEmpty()) {
            showError("Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        
        if (!customers.containsKey(userId)) {
            showError("Không tìm thấy UserID!");
            return;
        }
        
        if (!vehicles.containsKey(vehicleId)) {
            showError("Không tìm thấy VehicleID!");
            return;
        }
        
        // Tạo orderID mới
        String orderId = "ORDER" + String.format("%03d", rentals.size() + 1);
        String customerInfo = customers.get(userId);
        String vehicleInfo = vehicles.get(vehicleId);
        
        int totalPrice = calculateTotalPrice(vehicleId, Integer.parseInt(days));
        String rentalInfo = String.format("%s - %s - %s days - %s VND - %s", 
            userId, vehicleId, days, formatCurrency(totalPrice), cbPaymentMethod.getSelectedItem());
        
        rentals.put(orderId, rentalInfo);
        
        showSuccess("Thuê xe thành công!\n" +
                   "OrderID: " + orderId + "\n" +
                   "Khách hàng: " + customerInfo + "\n" +
                   "Phương tiện: " + vehicleInfo + "\n" +
                   "Tổng tiền: " + formatCurrency(totalPrice) + " VND\n" +
                   "Phương thức TT: " + cbPaymentMethod.getSelectedItem());
        clearRentForm();
    }
    
    private void checkRentalInfo() {
        String userId = txtRentUserId.getText().trim();
        String vehicleId = txtRentVehicleId.getText().trim();
        
        if (userId.isEmpty() && vehicleId.isEmpty()) {
            showError("Vui lòng nhập UserID hoặc VehicleID!");
            return;
        }
        
        StringBuilder info = new StringBuilder();
        
        if (!userId.isEmpty()) {
            if (customers.containsKey(userId)) {
                info.append("✅ Tìm thấy khách hàng: ").append(customers.get(userId)).append("\n");
            } else {
                info.append("❌ Không tìm thấy UserID: ").append(userId).append("\n");
            }
        }
        
        if (!vehicleId.isEmpty()) {
            if (vehicles.containsKey(vehicleId)) {
                info.append("✅ Tìm thấy phương tiện: ").append(vehicles.get(vehicleId)).append("\n");
            } else {
                info.append("❌ Không tìm thấy VehicleID: ").append(vehicleId).append("\n");
            }
        }
        
        JOptionPane.showMessageDialog(this, info.toString(), "THÔNG TIN KIỂM TRA", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void calculatePrice() {
        try {
            String vehicleId = txtRentVehicleId.getText().trim();
            int days = Integer.parseInt(txtRentDays.getText());
            
            if (!vehicles.containsKey(vehicleId)) {
                showError("Không tìm thấy VehicleID!");
                return;
            }
            
            int totalPrice = calculateTotalPrice(vehicleId, days);
            showSuccess("Tổng tiền thuê: " + formatCurrency(totalPrice) + " VND\n" +
                       "(" + days + " ngày × " + getDailyPrice(vehicleId) + " VND/ngày)");
        } catch (NumberFormatException e) {
            showError("Vui lòng nhập số ngày hợp lệ!");
        }
    }
    
    private void checkReturnInfo() {
        String orderId = txtReturnOrderId.getText().trim();
        String vehicleId = txtReturnVehicleId.getText().trim();
        
        if (orderId.isEmpty() && vehicleId.isEmpty()) {
            showError("Vui lòng nhập OrderID hoặc VehicleID!");
            return;
        }
        
        StringBuilder info = new StringBuilder("THÔNG TIN KIỂM TRA TRẢ XE:\n\n");
        
        if (!orderId.isEmpty()) {
            if (rentals.containsKey(orderId)) {
                info.append("✅ Tìm thấy đơn hàng:\n")
                    .append("OrderID: ").append(orderId).append("\n")
                    .append("Thông tin: ").append(rentals.get(orderId)).append("\n\n");
            } else {
                info.append("❌ Không tìm thấy OrderID: ").append(orderId).append("\n\n");
            }
        }
        
        if (!vehicleId.isEmpty()) {
            // Tìm đơn hàng theo vehicleId
            boolean found = false;
            for (Map.Entry<String, String> entry : rentals.entrySet()) {
                if (entry.getValue().contains(vehicleId)) {
                    info.append("✅ Phương tiện đang được thuê:\n")
                        .append("OrderID: ").append(entry.getKey()).append("\n")
                        .append("Thông tin: ").append(entry.getValue()).append("\n");
                    found = true;
                    break;
                }
            }
            if (!found) {
                info.append("❌ Không tìm thấy thông tin thuê cho VehicleID: ").append(vehicleId).append("\n");
            }
        }
        
        txtReturnInfo.setText(info.toString());
    }
    
    private void returnVehicle() {
        String orderId = txtReturnOrderId.getText().trim();
        
        if (orderId.isEmpty()) {
            showError("Vui lòng nhập OrderID!");
            return;
        }
        
        if (rentals.containsKey(orderId)) {
            String rentalInfo = rentals.get(orderId);
            rentals.remove(orderId);
            
            showSuccess("Trả xe thành công!\n" +
                       "OrderID: " + orderId + "\n" +
                       "Thông tin: " + rentalInfo + "\n" +
                       "Trạng thái: ĐÃ HOÀN TẤT");
            clearReturnForm();
        } else {
            showError("Không tìm thấy OrderID!");
        }
    }
    
    private void calculateReturnFee() {
        // Tính phí trả xe (phí trễ, phí vượt km, v.v.)
        String orderId = txtReturnOrderId.getText().trim();
        
        if (orderId.isEmpty()) {
            showError("Vui lòng nhập OrderID!");
            return;
        }
        
        if (!rentals.containsKey(orderId)) {
            showError("Không tìm thấy OrderID!");
            return;
        }
        
        // Giả lập tính phí
        int lateFee = 200000; // Phí trễ
        int extraKmFee = 150000; // Phí vượt km
        int cleaningFee = 50000; // Phí vệ sinh
        int totalFee = lateFee + extraKmFee + cleaningFee;
        
        String feeInfo = String.format(
            "PHÍ TRẢ XE - OrderID: %s\n\n" +
            "Phí trễ giờ: %s VND\n" +
            "Phí vượt km: %s VND\n" +
            "Phí vệ sinh: %s VND\n" +
            "─────────────────────\n" +
            "TỔNG PHÍ: %s VND",
            orderId, formatCurrency(lateFee), formatCurrency(extraKmFee), 
            formatCurrency(cleaningFee), formatCurrency(totalFee)
        );
        
        txtReturnInfo.setText(feeInfo);
    }
    
    // Utility methods
    private int calculateTotalPrice(String vehicleId, int days) {
        return getDailyPrice(vehicleId) * days;
    }
    
    private int getDailyPrice(String vehicleId) {
        // Giá mặc định theo vehicle type
        if (vehicleId.startsWith("CAR")) return 500000;
        if (vehicleId.startsWith("BIKE")) return 150000;
        return 300000;
    }
    
    private String formatCurrency(int amount) {
        return String.format("%,d", amount);
    }
    
    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Thành Công", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    
    private void clearRentForm() {
        txtRentUserId.setText("");
        txtRentVehicleId.setText("");
        txtRentDays.setText("");
        cbPaymentMethod.setSelectedIndex(0);
        txtRentDetails.setText("");
    }
    
    private void clearReturnForm() {
        txtReturnOrderId.setText("");
        txtReturnVehicleId.setText("");
        txtReturnInfo.setText("");
    }
}
