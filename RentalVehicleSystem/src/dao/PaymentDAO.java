package dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Payment;

public class PaymentDAO {

    private static List<Payment> payments = new ArrayList<>();
    private static int idCounter = 1;

    // Tao du lieu mau (de Admin vao xem thay co Doanh thu ngay)
    static {
        Payment p1 = new Payment();
        p1.setId(idCounter++);
        p1.setBookingId(101);
        p1.setCustomerId(1);
        p1.setAmount(150.0); 
        p1.setMethod("Credit Card");
        p1.setStatus("Completed");
        p1.setDate(LocalDateTime.now().minusDays(2));
        payments.add(p1);

        Payment p2 = new Payment();
        p2.setId(idCounter++);
        p2.setBookingId(102);
        p2.setCustomerId(2);
        p2.setAmount(200.0);
        p2.setMethod("Cash");
        p2.setStatus("Completed");
        p2.setDate(LocalDateTime.now().minusDays(1));
        payments.add(p2);
    }

    // Them thanh toan moi
    public boolean create(Payment p) {
        p.setId(idCounter++);
        if (p.getDate() == null) {
            p.setDate(LocalDateTime.now());
        }
        payments.add(p);
        return true;
    }

    // Lay thanh toan theo booking
    public Payment findByBookingId(int bookingId) {
        return payments.stream()
                .filter(p -> p.getBookingId() == bookingId)
                .findFirst()
                .orElse(null);
    }

    // Lay danh sach thanh toan cua khach hang
    public List<Payment> findByCustomer(int customerId) {
        return payments.stream()
                .filter(p -> p.getCustomerId() == customerId)
                .collect(Collectors.toList());
    }

    // Lay tat ca thanh toan (Cho Admin)
    public List<Payment> getAll() {
        return payments;
    }

    public double calculateTotalRevenue() {
        return payments.stream()
                .filter(p -> "Completed".equalsIgnoreCase(p.getStatus()))
                .mapToDouble(Payment::getAmount) 
                .sum();
    }
}