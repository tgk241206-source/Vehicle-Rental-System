package controller;

import java.util.List;
import model.Admin;
import model.Booking;
import model.Customer;
import model.Staff;
import model.Vehicle;
import service.AdminService;
import util.EmailSender;

public class AdminController {

    private AdminService adminService;

    public AdminController() {
        this.adminService = new AdminService();
    }

    // DANG NHAP
    public Admin login(String email, String password) {
        Admin admin = adminService.login(email, password);
        if (admin != null) {
            System.out.println(">> Dang nhap Admin thanh cong! Xin chao " + admin.getName());
        } else {
            System.out.println(">> Sai email hoac mat khau Admin!");
        }
        return admin;
    }

    // QUAN LY XE
    public void showAllVehicles() {
        List<Vehicle> list = adminService.getAllVehicles();
        System.out.println("\n===== DANH SACH XE (ADMIN) =====");
        if (list.isEmpty()) {
            System.out.println("Khong co xe nao.");
            return;
        }
        for (Vehicle v : list) {
            System.out.println(v);
        }
    }

    public void addVehicle(Vehicle v) {
        boolean ok = adminService.addVehicle(v);
        if (ok) {
            System.out.println(">> Them xe thanh cong!");
        } else {
            System.out.println(">> Loi: Khong the them xe.");
        }
    }

    public void deleteVehicle(int vehicleId) {
        boolean ok = adminService.deleteVehicle(vehicleId);
        if (ok) {
            System.out.println(">> Xoa xe thanh cong!");
        } else {
            System.out.println(">> Loi: Khong tim thay xe de xoa.");
        }
    }

    // QUAN LY KHACH HANG
    public void showAllCustomers() {
        List<Customer> list = adminService.getAllCustomers();
        System.out.println("\n===== DANH SACH KHACH HANG =====");
        if (list.isEmpty()) {
            System.out.println("Khong co khach hang nao.");
            return;
        }
        for (Customer c : list) {
            System.out.println(c);
        }
    }

    public void deleteCustomer(int customerId) {
        boolean ok = adminService.deleteCustomer(customerId);
        if (ok) {
            System.out.println(">> Xoa khach hang thanh cong!");
        } else {
            System.out.println(">> Loi: Khong the xoa khach hang.");
        }
    }

    // QUAN LY DON DAT XE
    public void showAllBookings() {
        List<Booking> list = adminService.getAllBookings();
        System.out.println("\n===== DANH SACH DON DAT XE =====");
        if (list.isEmpty()) {
            System.out.println("Khong co don dat xe.");
            return;
        }
        for (Booking b : list) {
            System.out.println(b);
        }
    }

    public void updateBookingStatus(int bookingId, String newStatus) {
        boolean ok = adminService.updateBookingStatus(bookingId, newStatus);

        if (ok) {
            System.out.println("Cap nhat trang thai don thanh cong!");

            Booking booking = adminService.getBookingById(bookingId);
            
            // Lay ID khach tu Booking
            int customerId = booking.getCustomerId();
            
            // Nho Service tim thong tin khach hang
            Customer customer = adminService.getCustomerById(customerId);

            // Lay email
            String emailDest = (customer != null) ? customer.getEmail() : "unknown@noemail.com";

            // Gui email
            EmailSender.send(
                    emailDest,
                    "Cap nhat trang thai don thue xe",
                    "Don #" + bookingId + " da duoc cap nhat sang trang thai: " + newStatus
            );

        } else {
            System.out.println("Loi cap nhat don!");
        }
    }

    // QUAN LY NHAN VIEN
    public void showAllStaff() {
        List<Staff> list = adminService.getAllStaff();
        System.out.println("\n===== DANH SACH NHAN VIEN =====");
        if (list.isEmpty()) {
            System.out.println("Khong co nhan vien.");
            return;
        }
        for (Staff s : list) {
            System.out.println(s);
        }
    }

    public void addStaff(Staff s) {
        boolean ok = adminService.addStaff(s);
        if (ok) System.out.println("Them nhan vien thanh cong!");
        else System.out.println("Khong the them nhan vien.");
    }

    public void deleteStaff(int staffId) {
        boolean ok = adminService.deleteStaff(staffId);
        if (ok) System.out.println("Xoa nhan vien thanh cong!");
        else System.out.println("Khong the xoa nhan vien.");
    }

    // BAO CAO
    public void showReport() {
        System.out.println("\n===== BAO CAO DOANH THU (GIA LAP) =====");
        System.out.println("Tong don thue xe: " + adminService.countBookings());
        System.out.println("Tong doanh thu: $" + adminService.calculateTotalRevenue());
        System.out.println("So xe dang thue: " + adminService.countRentedVehicles());
        System.out.println("Xe pho bien nhat: " + adminService.getMostPopularVehicle());
    }

}

