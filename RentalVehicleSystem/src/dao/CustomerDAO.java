package dao;

import java.util.ArrayList;
import java.util.List;
import model.Customer;

public class CustomerDAO {

    private static List<Customer> customers = new ArrayList<>();
    private static int idCounter = 1;

    static {
        customers.add(new Customer(idCounter++, "Nguyen Van Khach", "customer@gmail.com", "123456", "0912345678", "Hanoi"));
        customers.add(new Customer(idCounter++, "Le Thi B", "b@gmail.com", "123456", "0987654321", "Saigon"));
    }

    // Them khach hang (Dang ky)
    public boolean insert(Customer c) {
        // Kiem tra trung email truoc khi them
        if (findByEmail(c.getEmail()) != null) {
            return false; 
        }
        c.setId(idCounter++);
        customers.add(c);
        return true;
    }

    // Kiem tra Login
    public Customer checkLogin(String email, String password) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email) && c.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    // Tim theo email
    public Customer findByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    // Tim theo ID
    public Customer findById(int id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Lay danh sach khach hang (Cho Admin)
    public List<Customer> getAll() {
        return customers;
    }

    // Cap nhat thong tin (Profile)
    public boolean update(Customer c) {
            Customer existing = findById(c.getId());
            if (existing != null) {
                existing.setName(c.getName());
                existing.setPhone(c.getPhone());
                existing.setAddress(c.getAddress());
                
                existing.setGender(c.getGender());
                existing.setDob(c.getDob());
                
                return true;
            }
            return false;
        }

    // Doi mat khau
    public boolean changePassword(int id, String oldPass, String newPass) {
        Customer c = findById(id);
        if (c != null && c.getPassword().equals(oldPass)) {
            c.setPassword(newPass);
            return true;
        }
        return false;
    }

    // Xoa khach hang (Cho Admin)
    public boolean delete(int customerId) {
        return customers.removeIf(c -> c.getId() == customerId);
    }
}