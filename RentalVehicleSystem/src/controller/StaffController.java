package controller;

import dao.StaffDAO;
import java.util.List;
import java.util.stream.Collectors;
import model.Booking;
import model.Staff;

public class StaffController {
    
    private StaffDAO staffDAO = new StaffDAO();
    private BookingController bookingController = new BookingController(); 

    // Staff Login
    public Staff login(String email, String password) {
        Staff staff = staffDAO.findByEmail(email);
        if (staff != null && staff.getPassword().equals(password)) {
            return staff;
        }
        return null;
    }

    // Get list of bookings ready for PICK-UP (Status = Confirmed)
    public List<Booking> getPendingHandoverBookings() {
        return bookingController.getAllBookings().stream()
                .filter(b -> "Confirmed".equalsIgnoreCase(b.getStatus()))
                .collect(Collectors.toList());
    }

    // Get list of bookings currently RENTED (Status = Renting)
    public List<Booking> getRentingBookings() {
        return bookingController.getAllBookings().stream()
                .filter(b -> "Renting".equalsIgnoreCase(b.getStatus()))
                .collect(Collectors.toList());
    }

    public boolean processHandover(int bookingId) {
        return bookingController.updateBookingStatus(bookingId, "Renting");
    }

    public double processReturn(int bookingId, double surcharge, String note) {
        // Update status to Completed
        boolean isUpdated = bookingController.updateBookingStatus(bookingId, "Completed");
        
        if (isUpdated) {
            // Calculate Final Total = Original Price + Surcharges
            Booking b = bookingController.getBookingById(bookingId);
            return b.getTotalCost() + surcharge;
        }
        return -1;
    }
}