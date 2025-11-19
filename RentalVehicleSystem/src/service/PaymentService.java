package service;

import dao.BookingDAO;
import dao.CustomerDAO;
import dao.PaymentDAO;
import java.time.LocalDateTime;
import java.util.List;
import model.Booking;
import model.Customer;
import model.Payment;

public class PaymentService {

    private PaymentDAO paymentDAO = new PaymentDAO();
    private BookingDAO bookingDAO = new BookingDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private NotificationService notificationService = new NotificationService();

    // Tao thanh toan moi
    public boolean makePayment(int bookingId, int customerId, double amount, String method) {

        // Kiem tra booking co ton tai khong
        Booking booking = bookingDAO.findById(bookingId);
        if (booking == null) {
            System.out.println("Loi: Booking ID " + bookingId + " khong ton tai.");
            return false;
        }

        // Neu booking bi huy thi khong thanh toan
        if ("Cancelled".equalsIgnoreCase(booking.getStatus())) {
            System.out.println("Loi: Khong the thanh toan cho don da huy.");
            return false;
        }

        // Tao payment object
        Payment p = new Payment();
        p.setBookingId(bookingId);
        p.setCustomerId(customerId);
        p.setAmount(amount);
        p.setMethod(method);
        p.setStatus("Success");
        p.setDate(LocalDateTime.now());

        // Luu vao DB
        boolean success = paymentDAO.create(p);

        if (success) {
            System.out.println(">> Thanh toan thanh cong!");

            // Logic lay Email (SUA LOI TAI DAY)
            Customer customer = customerDAO.findById(customerId);
            
            String emailToSend = "unknown@email.com";
            if (customer != null) {
                emailToSend = customer.getEmail();
            }

            // Gui email mo phong
            notificationService.notifyPayment(emailToSend, amount, method);
            
            // Cap nhat trang thai Booking thanh "Paid"
            bookingDAO.updateStatus(bookingId, "Paid");
        } else {
            System.out.println("Loi khi ghi thanh toan vao he thong.");
        }

        return success;
    }

    // Lay thong tin thanh toan theo booking
    public Payment getPaymentByBooking(int bookingId) {
        return paymentDAO.findByBookingId(bookingId);
    }

    // Lay tat ca thanh toan cua 1 khach hang
    public List<Payment> getPaymentsByCustomer(int customerId) {
        return paymentDAO.findByCustomer(customerId);
    }

    // Lay toan bo thanh toan (Admin)
    public List<Payment> getAllPayments() {
        return paymentDAO.getAll();
    }
}

