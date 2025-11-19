package controller;

import java.time.LocalDate;
import java.util.List;
import model.Booking;
import model.Customer;
import model.Vehicle;
import service.BookingService;
import util.EmailSender;

public class BookingController {

    private BookingService bookingService = new BookingService();


    // Tao booking 
    public boolean createBooking(Customer customer, Vehicle vehicle,
            LocalDate startDate, LocalDate endDate) {

        // Kiem tra xe null
        if (vehicle == null) {
            System.out.println(">> Loi: Thong tin xe khong hop le.");
            return false;
        }

        // Kiem tra trang thai xe
        if (!"Available".equalsIgnoreCase(vehicle.getStatus())) {
            System.out.println(">> Xe nay hien khong kha dung (Trang thai: " + vehicle.getStatus() + ")");
            return false;
        }

        System.out.println("\n===== XAC NHAN DON DAT XE =====");
        System.out.println("Khach hang: " + customer.getName());
        System.out.println("Xe: " + vehicle.getModelName());
        System.out.println("Thoi gian: " + startDate + " den " + endDate);

        // Tinh toan chi phi
        long days = bookingService.calculateDays(startDate, endDate);
        if (days <= 0) {
            System.out.println(">> Loi: Ngay tra xe phai sau ngay nhan xe!");
            return false;
        }
        
        double total = bookingService.calculateTotalCost(days, vehicle.getPricePerDay());
        System.out.println("So ngay: " + days);
        System.out.println("Tong tien: $" + total);

        // Goi Service tao don
        Booking booking = bookingService.createBooking(customer, vehicle, startDate, endDate, total);

        if (booking != null) {
            // Gui email mo phong
            EmailSender.send(
                    customer.getEmail(),
                    "Xac nhan dat xe thanh cong",
                    "Ma don: #" + booking.getId() + "\nXe: " + vehicle.getName() + "\nTong tien: $" + total);
            return true;
        } else {
            return false;
        }
    }

    // Lay danh sach don cua rieng khach hang
    public List<Booking> getBookingsByCustomer(int customerId) {
        return bookingService.getBookingsByCustomer(customerId);
    }

    // Huy don (Khach hang tu huy)
    public boolean cancelBooking(int bookingId) {
        boolean success = bookingService.cancelBooking(bookingId);
        if (success) {
            System.out.println(">> Da gui yeu cau huy don hang #" + bookingId);
        } else {
            System.out.println(">> Huy that bai (Khong tim thay ID hoac don da hoan thanh).");
        }
        return success;
    }

    // LOGIC CHO ADMIN & STAFF (QUAN LY)
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // Lay chi tiet 1 don hang theo ID
    public Booking getBookingById(int bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    // Cap nhat trang thai don hang
    public boolean updateBookingStatus(int bookingId, String newStatus) {
        boolean result = bookingService.updateStatus(bookingId, newStatus);
        if (result) {
            System.out.println(">> Cap nhat trang thai don #" + bookingId + " thanh: " + newStatus);
        } else {
            System.out.println(">> Cap nhat that bai. Kiem tra lai ID don hang.");
        }
        return result;
    }
}