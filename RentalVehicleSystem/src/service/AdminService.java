package service;

import dao.AdminDAO;
import dao.BookingDAO;
import dao.CustomerDAO;
import dao.PaymentDAO;
import dao.StaffDAO;
import dao.VehicleDAO;
import java.util.List;
import model.Admin;
import model.Booking;
import model.Customer;
import model.Staff;
import model.Vehicle;

public class AdminService {

    private AdminDAO adminDAO = new AdminDAO();
    private VehicleDAO vehicleDAO = new VehicleDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private BookingDAO bookingDAO = new BookingDAO();
    private PaymentDAO paymentDAO = new PaymentDAO();
    private StaffDAO staffDAO = new StaffDAO();

    // DANG NHAP ADMIN
    public Admin login(String email, String password) {
        return adminDAO.checkLogin(email, password);
    }

    // QUAN LY XE
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.getAll();
    }

    public boolean addVehicle(Vehicle v) {
        return vehicleDAO.add(v);
    }

    public boolean deleteVehicle(int id) {
        return vehicleDAO.delete(id);
    }

    // QUAN LY KHACH HANG
    public List<Customer> getAllCustomers() {
        return customerDAO.getAll();
    }

    public boolean deleteCustomer(int id) {
        return customerDAO.delete(id);
    }

    // Lay thong tin khach hang theo ID
    public Customer getCustomerById(int id) {
        return customerDAO.findById(id);
    }

    // QUAN LY DON DAT XE
    public List<Booking> getAllBookings() {
        return bookingDAO.getAll();
    }

    public Booking getBookingById(int id) {
        return bookingDAO.findById(id);
    }

    public boolean updateBookingStatus(int bookingId, String newStatus) {
        return bookingDAO.updateStatus(bookingId, newStatus);
    }

    // QUAN LY NHAN VIEN
    public List<Staff> getAllStaff() {
        return staffDAO.getAllStaff();
    }

    public boolean addStaff(Staff s) {
        return staffDAO.insert(s);
    }

    public boolean deleteStaff(int staffId) {
        return staffDAO.delete(staffId);
    }

    // BAO CAO – GIA LAP
    public int countBookings() {
        return bookingDAO.countBookings();
    }

    public double calculateTotalRevenue() {
        return paymentDAO.calculateTotalRevenue();
    }

    public int countRentedVehicles() {
        return vehicleDAO.countRentedVehicles();
    }

    public String getMostPopularVehicle() {
        return bookingDAO.getMostPopularVehicle();
    }


}