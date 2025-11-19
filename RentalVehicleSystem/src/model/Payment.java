package model;

import java.time.LocalDateTime;

public class Payment {

    private int id;
    private int bookingId;
    private int customerId;
    private double amount;
    private String method;
    private String status; 
    private LocalDateTime date;

    public Payment() {
    }

    public Payment(int id, int bookingId, int customerId, double amount,
            String method, String status, LocalDateTime date) {
        this.id = id;
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.date = date;
    }

    public Payment(int bookingId, int customerId, double amount,
            String method, String status, LocalDateTime date) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Payment {" +
                "id=" + id +
                ", bookingId=" + bookingId +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }
}
