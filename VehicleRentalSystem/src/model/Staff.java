package model;

public class Staff {
    private Long id;
    private String staffCode;
    private String fullName;
    private String position;     
    private String branchName;
    private String phoneNumber;
    private String email;
    private String username;
    private String passwordHash;
    private String role;         
    private boolean active = true;

    public Staff() {
    }

    public Staff(Long id,
                 String staffCode,
                 String fullName,
                 String position,
                 String branchName,
                 String phoneNumber,
                 String email,
                 String username,
                 String passwordHash,
                 String role) {
        this.id = id;
        this.staffCode = staffCode;
        this.fullName = fullName;
        this.position = position;
        this.branchName = branchName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        validate();
    }

    public void validate() {
        if (staffCode == null || staffCode.isBlank()) {
            throw new IllegalArgumentException("Staff code is required");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name is required");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (phoneNumber == null || phoneNumber.length() < 8) {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStaffCode() { return staffCode; }
    public void setStaffCode(String staffCode) { this.staffCode = staffCode; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
