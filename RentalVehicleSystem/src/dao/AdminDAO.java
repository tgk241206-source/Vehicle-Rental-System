package dao;

import java.util.ArrayList;
import java.util.List;
import model.Admin;

public class AdminDAO {

    private static List<Admin> adminList = new ArrayList<>();
    
    // Bien dem ID tu tang
    private static int idCounter = 10;

    static {
        // Admin so 1: Quan tri vien cap cao
        adminList.add(new Admin(1, "Super Admin", "admin@gmail.com", "123456", "ADMIN"));
        
        // Admin so 2: Quan ly
        adminList.add(new Admin(2, "Manager John", "manager@gmail.com", "123456", "MANAGER"));
    }

    public Admin checkLogin(String email, String password) {
        for (Admin admin : adminList) {
            // So sanh email va password
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    public void add(Admin admin) {
        // Tu dong tang ID
        admin.setId(idCounter++);
        adminList.add(admin);
    }

    public void register(Admin admin) {
        add(admin);
    }

    public boolean checkEmailExists(String email) {
        for (Admin admin : adminList) {
            if (admin.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }
}

