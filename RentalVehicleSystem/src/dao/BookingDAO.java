package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Booking;

public class BookingDAO {

    private static List<Booking> bookings = new ArrayList<>();
    private static int idCounter = 1;

    static {
    }


    // Tao booking moi
    public Booking create(Booking b) {
        b.setId(idCounter++);
        if (b.getStatus() == null) {
            b.setStatus("Confirmed");
        }
        bookings.add(b);
        return b;
    }

    // Lay booking theo ID
    public Booking findById(int id) {
        return bookings.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Lay toan bo booking cua 1 khach hang
    public List<Booking> findByCustomer(int customerId) {
        return bookings.stream()
                .filter(b -> b.getCustomerId() == customerId)
                .collect(Collectors.toList());
    }

    // Lay toan bo booking (Dung cho Admin xem danh sach)
    public List<Booking> getAll() {
        return bookings;
    }

    // Huy booking
    public boolean cancel(int bookingId) {
        Booking b = findById(bookingId);
        if (b != null) {
            b.setStatus("Cancelled");
            return true;
        }
        return false;
    }

    // Cap nhat trang thai (Confirmed, Completed, Cancelled...)
    public boolean updateStatus(int id, String status) {
        Booking b = findById(id);
        if (b != null) {
            b.setStatus(status);
            return true;
        }
        return false;
    }

    // Dem tong so don dat
    public int countBookings() {
        return bookings.size();
    }

    // Tim xe duoc thue nhieu nhat
    public String getMostPopularVehicle() {
        if (bookings.isEmpty()) return "No data";

        Map<Integer, Integer> vehicleCount = new HashMap<>();
        
        // Dem so lan xuat hien cua tung Vehicle ID
        for (Booking b : bookings) {
            int vId = b.getVehicleId();
            vehicleCount.put(vId, vehicleCount.getOrDefault(vId, 0) + 1);
        }

        // Tim ID co so lan thue lon nhat
        int maxId = -1;
        int maxCount = -1;

        for (Map.Entry<Integer, Integer> entry : vehicleCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxId = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return "Vehicle ID: " + maxId + " (" + maxCount + " bookings)";
    }

}
