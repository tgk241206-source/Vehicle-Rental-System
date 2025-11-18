package model;

import java.math.BigDecimal;

public class Vehicle {

    private Long id;
    private String code;           
    private String name;           
    private String brand;      
    private String model;          
    private String licensePlate;   
    private int seatCount;         
    private BigDecimal pricePerDay; 
    private BigDecimal deposit; 
    private String status;         
    private String description;

    public static final String STATUS_AVAILABLE = "AVAILABLE";
    public static final String STATUS_RENTED = "RENTED";
    public static final String STATUS_MAINTENANCE = "MAINTENANCE";

    public Vehicle() {
    }

    public Vehicle(Long id,
                   String code,
                   String name,
                   String brand,
                   String model,
                   String licensePlate,
                   int seatCount,
                   BigDecimal pricePerDay,
                   BigDecimal deposit,
                   String status,
                   String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.pricePerDay = pricePerDay;
        this.deposit = deposit;
        this.status = status;
        this.description = description;
        validate();
    }

    public void validate() {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Vehicle code is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Vehicle name is required");
        }
        if (licensePlate == null || licensePlate.isBlank()) {
            throw new IllegalArgumentException("License plate is required");
        }
        if (seatCount <= 0) {
            throw new IllegalArgumentException("Seat count must be > 0");
        }
        if (pricePerDay == null || pricePerDay.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price per day must be > 0");
        }
        if (deposit == null || deposit.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Deposit must be >= 0");
        }
        if (status == null || status.isBlank()) {
            status = STATUS_AVAILABLE;
        }
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
