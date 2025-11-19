package dao;

import java.util.ArrayList;
import java.util.List;
import model.Staff;

public class StaffDAO {

    private static List<Staff> staffList = new ArrayList<>();
    private static int idCounter = 1;

    // Tao du lieu nhan vien mau
    static {
        // Tao nhan vien so 1
        Staff s1 = new Staff();
        s1.setId(idCounter++);
        s1.setName("Nguyen Van A");
        s1.setEmail("staff1@.com");
        s1.setPassword("123456");
        s1.setPhone("0901234567");
        s1.setRole("Sales");
        staffList.add(s1);

        // Tao nhan vien so 2
        Staff s2 = new Staff();
        s2.setId(idCounter++);
        s2.setName("Tran Thi B");
        s2.setEmail("staff2@.com");
        s2.setPassword("123456");
        s2.setPhone("0909876543");
        s2.setRole("Technician");
        staffList.add(s2);
    }

    // Lay tat ca nhan vien
    public List<Staff> getAllStaff() {
        return staffList;
    }

    // Them nhan vien moi
    public boolean insert(Staff s) {
        s.setId(idCounter++);
        staffList.add(s);
        return true;
    }

    // Xoa nhan vien theo ID
    public boolean delete(int id) {
        return staffList.removeIf(s -> s.getId() == id);
    }

    // Tim nhan vien theo email (dung de check trung hoac login)
    public Staff findByEmail(String email) {
        return staffList.stream()
                .filter(s -> s.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
}
