package model;

import java.time.LocalDate;

public class Customer {

    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;
    private String address;
    private String driverLicenseNumber;
    private String nationalId;     
    private String passwordHash;   

    public Customer() {
    }

    public Customer(Long id,
                    String fullName,
                    LocalDate dateOfBirth,
                    String phone,
                    String email,
                    String address,
                    String driverLicenseNumber,
                    String nationalId,
                    String passwordHash) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.driverLicenseNumber = driverLicenseNumber;
        this.nationalId = nationalId;
        this.passwordHash = passwordHash;
        validate();
    }

    public void validate() {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Customer name is required");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone is required");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (driverLicenseNumber == null || driverLicenseNumber.isBlank()) {
            throw new IllegalArgumentException("Driver license is required");
        }
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
