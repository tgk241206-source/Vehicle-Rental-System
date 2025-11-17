package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Rental {
    private Long id;
    private String rentalCode;
    private Customer customer;
    private Vehicle vehicle;
    private Staff createdBy;          

    private LocalDateTime pickupDateTime;
    private LocalDateTime returnDateTime;
    private String pickupLocation;
    private String dropoffLocation;

    private int rentalDays;
    private BigDecimal basePricePerDay;
    private BigDecimal addOnFee;
    private BigDecimal insuranceFee;
    private BigDecimal taxFee;
    private BigDecimal discountAmount;
    private BigDecimal depositAmount;
    private BigDecimal totalAmount;

    private String status;           

    public static final String STATUS_PENDING   = "PENDING";
    public static final String STATUS_CONFIRMED = "CONFIRMED";
    public static final String STATUS_RENTING   = "RENTING";
    public static final String STATUS_COMPLETED = "COMPLETED";
    public static final String STATUS_CANCELLED = "CANCELLED";

    public Rental() {
    }

    public Rental(Long id,
                  String rentalCode,
                  Customer customer,
                  Vehicle vehicle,
                  Staff createdBy,
                  LocalDateTime pickupDateTime,
                  LocalDateTime returnDateTime,
                  String pickupLocation,
                  String dropoffLocation,
                  int rentalDays,
                  BigDecimal basePricePerDay,
                  BigDecimal addOnFee,
                  BigDecimal insuranceFee,
                  BigDecimal taxFee,
                  BigDecimal discountAmount,
                  BigDecimal depositAmount,
                  BigDecimal totalAmount,
                  String status) {
        this.id = id;
        this.rentalCode = rentalCode;
        this.customer = customer;
        this.vehicle = vehicle;
        this.createdBy = createdBy;
        this.pickupDateTime = pickupDateTime;
        this.returnDateTime = returnDateTime;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.rentalDays = rentalDays;
        this.basePricePerDay = basePricePerDay;
        this.addOnFee = addOnFee;
        this.insuranceFee = insuranceFee;
        this.taxFee = taxFee;
        this.discountAmount = discountAmount;
        this.depositAmount = depositAmount;
        this.totalAmount = totalAmount;
        this.status = status;
        validate();
    }

    public void validate() {
        if (customer == null) {
            throw new IllegalArgumentException("Customer is required");
        }
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle is required");
        }
        if (pickupDateTime == null || returnDateTime == null
                || !returnDateTime.isAfter(pickupDateTime)) {
            throw new IllegalArgumentException("Return time must be after pickup time");
        }
        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Rental days must be > 0");
        }
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRentalCode() { return rentalCode; }
    public void setRentalCode(String rentalCode) { this.rentalCode = rentalCode; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public Staff getCreatedBy() { return createdBy; }
    public void setCreatedBy(Staff createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getPickupDateTime() { return pickupDateTime; }
    public void setPickupDateTime(LocalDateTime pickupDateTime) { this.pickupDateTime = pickupDateTime; }

    public LocalDateTime getReturnDateTime() { return returnDateTime; }
    public void setReturnDateTime(LocalDateTime returnDateTime) { this.returnDateTime = returnDateTime; }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public String getDropoffLocation() { return dropoffLocation; }
    public void setDropoffLocation(String dropoffLocation) { this.dropoffLocation = dropoffLocation; }

    public int getRentalDays() { return rentalDays; }
    public void setRentalDays(int rentalDays) { this.rentalDays = rentalDays; }

    public BigDecimal getBasePricePerDay() { return basePricePerDay; }
    public void setBasePricePerDay(BigDecimal basePricePerDay) { this.basePricePerDay = basePricePerDay; }

    public BigDecimal getAddOnFee() { return addOnFee; }
    public void setAddOnFee(BigDecimal addOnFee) { this.addOnFee = addOnFee; }

    public BigDecimal getInsuranceFee() { return insuranceFee; }
    public void setInsuranceFee(BigDecimal insuranceFee) { this.insuranceFee = insuranceFee; }

    public BigDecimal getTaxFee() { return taxFee; }
    public void setTaxFee(BigDecimal taxFee) { this.taxFee = taxFee; }

    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }

    public BigDecimal getDepositAmount() { return depositAmount; }
    public void setDepositAmount(BigDecimal depositAmount) { this.depositAmount = depositAmount; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
