package ui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set giao diện hệ thống
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Chạy ứng dụng
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
}
