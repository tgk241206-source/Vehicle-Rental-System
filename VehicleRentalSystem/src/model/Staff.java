package model;

public class Staff {

    private Long id;
    private String staffCode;
    private String fullName;
    private String role;       
    private String branch;      
    private String phone;
    private String email;
    private String passwordHash;

    public Staff() {
    }

    public Staff(Long id,
                 String staffCode,
                 String fullName,
                 String role,
                 String branch,
                 String phone,
                 String email,
                 String passwordHash) {
        this.id = id;
        this.staffCode = staffCode;
        this.fullName = fullName;
        this.role = role;
        this.branch = branch;
        this.phone = phone;
        this.email = email;
        this.passwordHash = passwordHash;
        validate();
    }

    public void validate() {
        if (staffCode == null || staffCode.isBlank()) {
            throw new IllegalArgumentException("Staff code is required");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Staff name is required");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Staff phone is required");
        }
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
