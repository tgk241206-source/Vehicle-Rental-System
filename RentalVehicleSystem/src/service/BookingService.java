package service;

import dao.BookingDAO;
import dao.CustomerDAO;
import dao.VehicleDAO;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import model.Booking;
import model.Customer;
import model.Vehicle;

public class BookingService {

    private BookingDAO bookingDAO = new BookingDAO();
    private VehicleDAO vehicleDAO = new VehicleDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private NotificationService notificationService = new NotificationService();

    // Tinh so ngay thue
    public long calculateDays(LocalDate start, LocalDate end) {
        return ChronoUnit.DAYS.between(start, end);
    }

    // Tinh tong tien thue
    public double calculateTotalCost(long days, double pricePerDay) {
        return days * pricePerDay;
    }

    // Tao Booking moi
    public Booking createBooking(Customer c, Vehicle v,
            LocalDate start, LocalDate end,
            double totalCost) {

        // 1. VALIDATION: Kiem tra ngay hop le
        if (start.isAfter(end)) {
            System.out.println("Loi: Ngay bat dau phai truoc ngay ket thuc.");
            return null;
        }

        // 2. VALIDATION: Kiem tra xe co san sang khong?
        if (!"Available".equalsIgnoreCase(v.getStatus())) {
            System.out.println("Loi: Xe nay hien khong kha dung (Dang: " + v.getStatus() + ")");
            return null;
        }

        Booking b = new Booking(
                c.getId(),
                v.getId(),
                start,
                end,
                totalCost,
                "Pending" 
        );

        Booking created = bookingDAO.create(b);

        if (created != null) {
            // Cap nhat trang thai xe thanh "rented"
            v.setStatus("Rented");
            vehicleDAO.updateStatus(v.getId(), "Rented");

            // Gui email xac nhan
            notificationService.notifyBookingSuccess(
                c.getEmail(), 
                v.getModelName(),
                start.toString(), 
                end.toString(), 
                totalCost
            );
            
            System.out.println(">> Dat xe thanh cong! Ma don: " + created.getId());
        }

        return created;
    }

    // Lay booking theo ID
    public Booking getBookingById(int id) {
        return bookingDAO.findById(id);
    }

    // Lay danh sach booking cua khach
    public List<Booking> getBookingsByCustomer(int customerId) {
        return bookingDAO.findByCustomer(customerId);
    }

    // Huy booking
    public boolean cancelBooking(int id) {
        Booking b = bookingDAO.findById(id);

        if (b == null) {
            System.out.println("Loi: Khong tim thay don dat xe.");
            return false;
        }
        
        // Chi cho huy neu don chua hoan thanh
        if ("Completed".equalsIgnoreCase(b.getStatus())) {
            System.out.println("Loi: Khong the huy don da hoan thanh.");
            return false;
        }

        boolean success = bookingDAO.cancel(id);

        if (success) {
            // Tra lai trang thai xe thanh available
            vehicleDAO.updateStatus(b.getVehicleId(), "Available");

            // Gui email bao huy
            Customer c = customerDAO.findById(b.getCustomerId());
            if (c != null) {
                notificationService.notifyCancelBooking(c.getEmail(), id);
            }
            
            System.out.println(">> Da huy don hang #" + id);
        }

        return success;
    }

    // Admin cap nhat trang thai booking
    public boolean updateStatus(int id, String status) {
        boolean result = bookingDAO.updateStatus(id, status);
        
        if (result) {
            // Gui thong bao cap nhat trang thai
            Booking b = bookingDAO.findById(id);
            Customer c = customerDAO.findById(b.getCustomerId());
            if(c != null) {
                notificationService.notifyBookingStatus(c.getEmail(), id, status);
            }
        }
        return result;
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.getAll();
    }

}

