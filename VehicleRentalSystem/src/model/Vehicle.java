package model;

import java.math.BigDecimal;

public class Vehicle {
    private Long id;
    private String code;
    private String name;
    private String type;        
    private String brand;
    private String engineType;  
    private int seatCount;
    private BigDecimal pricePerDay;
    private BigDecimal depositAmount;
    private String status;      
    private String imageUrl;
    private String description;

    public static final String STATUS_AVAILABLE = "AVAILABLE";
    public static final String STATUS_RENTED = "RENTED";
    public static final String STATUS_MAINTENANCE = "MAINTENANCE";

    public Vehicle() {
    }

    public Vehicle(Long id,
                   String code,
                   String name,
                   String type,
                   String brand,
                   String engineType,
                   int seatCount,
                   BigDecimal pricePerDay,
                   BigDecimal depositAmount,
                   String status,
                   String imageUrl,
                   String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.engineType = engineType;
        this.seatCount = seatCount;
        this.pricePerDay = pricePerDay;
        this.depositAmount = depositAmount;
        this.status = status;
        this.imageUrl = imageUrl;
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
        if (seatCount <= 0) {
            throw new IllegalArgumentException("Seat count must be > 0");
        }
        if (pricePerDay == null || pricePerDay.signum() < 0) {
            throw new IllegalArgumentException("Price per day must be >= 0");
        }
        if (depositAmount == null || depositAmount.signum() < 0) {
            throw new IllegalArgumentException("Deposit must be >= 0");
        }
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getEngineType() { return engineType; }
    public void setEngineType(String engineType) { this.engineType = engineType; }

    public int getSeatCount() { return seatCount; }
    public void setSeatCount(int seatCount) { this.seatCount = seatCount; }

    public BigDecimal getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(BigDecimal pricePerDay) { this.pricePerDay = pricePerDay; }

    public BigDecimal getDepositAmount() { return depositAmount; }
    public void setDepositAmount(BigDecimal depositAmount) { this.depositAmount = depositAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Vehicle{id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", status='" + status + '\'' +
                '}';
    }
}
