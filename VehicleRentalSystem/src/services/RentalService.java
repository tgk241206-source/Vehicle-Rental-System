package service;

import model.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class RentalService {

    public boolean isVehicleAvailable(Vehicle vehicle,
                                      LocalDateTime pickup,
                                      LocalDateTime dropoff) {
        if (vehicle == null) return false;
        
        return Vehicle.STATUS_AVAILABLE.equalsIgnoreCase(vehicle.getStatus());
    }

    public BigDecimal calculateRentalCost(BigDecimal basePricePerDay,
                                          int rentalDays,
                                          BigDecimal addOnFee,
                                          BigDecimal insuranceFee,
                                          BigDecimal taxFee,
                                          BigDecimal discountAmount,
                                          BigDecimal depositAmount) {

        if (addOnFee == null) addOnFee = BigDecimal.ZERO;
        if (insuranceFee == null) insuranceFee = BigDecimal.ZERO;
        if (taxFee == null) taxFee = BigDecimal.ZERO;
        if (discountAmount == null) discountAmount = BigDecimal.ZERO;
        if (depositAmount == null) depositAmount = BigDecimal.ZERO;

        BigDecimal base = basePricePerDay.multiply(BigDecimal.valueOf(rentalDays));
        return base
                .add(addOnFee)
                .add(insuranceFee)
                .add(taxFee)
                .subtract(discountAmount)
                .add(depositAmount);
    }

    public Rental createRental(Customer customer,
                               Vehicle vehicle,
                               Staff staff,
                               LocalDateTime pickup,
                               LocalDateTime dropoff,
                               String pickupLocation,
                               String dropoffLocation,
                               BigDecimal addOnFee,
                               BigDecimal insuranceFee,
                               BigDecimal taxFee,
                               BigDecimal discountAmount,
                               BigDecimal depositAmount) {

        if (customer == null) {
            throw new IllegalArgumentException("Customer is required");
        }
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle is required");
        }
        if (!isVehicleAvailable(vehicle, pickup, dropoff)) {
            throw new IllegalStateException("Vehicle is not available");
        }

        int rentalDays = Math.max(1,
                (int) Math.ceil(Duration.between(pickup, dropoff).toHours() / 24.0));

        BigDecimal total = calculateRentalCost(
                vehicle.getPricePerDay(),
                rentalDays,
                addOnFee,
                insuranceFee,
                taxFee,
                discountAmount,
                depositAmount
        );

        return new Rental(
                null,
                generateRentalCode(),
                customer,
                vehicle,
                staff,
                pickup,
                dropoff,
                pickupLocation,
                dropoffLocation,
                rentalDays,
                vehicle.getPricePerDay(),
                addOnFee,
                insuranceFee,
                taxFee,
                discountAmount,
                depositAmount,
                total,
                Rental.STATUS_PENDING
        );
    }

    public void cancelRental(Rental rental) {
        if (rental == null) return;
        rental.setStatus(Rental.STATUS_CANCELLED);
    }

    private String generateRentalCode() {
        return "R-" + System.currentTimeMillis();
    }
}
