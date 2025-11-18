package service;

import model.Customer;
import model.Rental;
import model.Staff;
import model.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class RentalService {

    private final List<Rental> rentals = new ArrayList<>();
    private long nextId = 1L;

    private final PaymentService paymentService;

    public RentalService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public Rental createRental(Customer customer,
                               Vehicle vehicle,
                               Staff staff,
                               LocalDate startDate,
                               LocalDate endDate,
                               String pickupLocation,
                               String dropoffLocation,
                               String paymentMethod,
                               BigDecimal insuranceFee,
                               BigDecimal taxRate,
                               BigDecimal discountAmount) {

        if (customer == null) {
            throw new IllegalArgumentException("Customer is required");
        }
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle is required");
        }
        if (!Vehicle.STATUS_AVAILABLE.equals(vehicle.getStatus())) {
            throw new IllegalStateException("Vehicle is not available");
        }
        if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Invalid rental dates");
        }

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        if (days == 0) {
            days = 1;
        }

        BigDecimal baseAmount = vehicle.getPricePerDay().multiply(BigDecimal.valueOf(days));
        BigDecimal totalAmount = paymentService.calculateRentalCost(
                vehicle.getPricePerDay(),
                days,
                insuranceFee != null ? insuranceFee : BigDecimal.ZERO,
                taxRate != null ? taxRate : BigDecimal.ZERO,
                discountAmount != null ? discountAmount : BigDecimal.ZERO
        );

        Rental rental = new Rental();
        rental.setId(nextId++);
        rental.setCustomer(customer);
        rental.setVehicle(vehicle);
        rental.setStaff(staff);
        rental.setBookingTime(LocalDateTime.now());
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        rental.setPickupLocation(pickupLocation);
        rental.setDropoffLocation(dropoffLocation);
        rental.setTotalDays(days);
        rental.setBaseAmount(baseAmount);
        rental.setSurchargeAmount(BigDecimal.ZERO);
        rental.setDiscountAmount(discountAmount != null ? discountAmount : BigDecimal.ZERO);
        rental.setTotalAmount(totalAmount);
        rental.setDepositAmount(vehicle.getDeposit());
        rental.setStatus(Rental.STATUS_BOOKED);
        rental.setCreatedAt(LocalDateTime.now());

        rental.validate();
        rentals.add(rental);

        vehicle.setStatus(Vehicle.STATUS_RENTED);

        return rental;
    }

    public Rental createRental(Customer customer,
                               Vehicle vehicle,
                               Staff staff,
                               LocalDate startDate,
                               LocalDate endDate,
                               String pickupLocation,
                               String dropoffLocation,
                               String paymentMethod) {
        return createRental(
                customer,
                vehicle,
                staff,
                startDate,
                endDate,
                pickupLocation,
                dropoffLocation,
                paymentMethod,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO
        );
    }

    public Rental findById(long id) {
        for (Rental r : rentals) {
            if (r.getId() != null && r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public void returnVehicle(long rentalId,
                              LocalDate actualReturnDate,
                              BigDecimal fuelSurcharge,
                              BigDecimal extraMileageFee,
                              BigDecimal damageFee) {

        Rental rental = findById(rentalId);
        if (rental == null) {
            throw new IllegalArgumentException("Rental not found");
        }

        if (actualReturnDate == null || actualReturnDate.isBefore(rental.getStartDate())) {
            throw new IllegalArgumentException("Invalid actual return date");
        }

        long lateDays = 0;
        if (actualReturnDate.isAfter(rental.getEndDate())) {
            lateDays = ChronoUnit.DAYS.between(rental.getEndDate(), actualReturnDate);
        }

        BigDecimal lateFee = paymentService.calculateLateFee(
                rental.getVehicle().getPricePerDay(),
                lateDays
        );

        BigDecimal surcharge = BigDecimal.ZERO;
        if (fuelSurcharge != null) surcharge = surcharge.add(fuelSurcharge);
        if (extraMileageFee != null) surcharge = surcharge.add(extraMileageFee);
        if (damageFee != null) surcharge = surcharge.add(damageFee);
        surcharge = surcharge.add(lateFee);

        rental.setSurchargeAmount(surcharge);
        rental.setTotalAmount(rental.getTotalAmount().add(surcharge));
        rental.setStatus(Rental.STATUS_COMPLETED);
        rental.setUpdatedAt(LocalDateTime.now());

        // Trả xe: set lại AVAILABLE
        rental.getVehicle().setStatus(Vehicle.STATUS_AVAILABLE);
    }

    public List<Rental> getAllRentals() {
        return new ArrayList<>(rentals);
    }
}
