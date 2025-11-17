package service;

import model.Rental;

public class PaymentService {
    
    public boolean processPayment(Rental rental, String paymentMethod, String paymentDetails) {
        // Simulate payment processing
        System.out.println("Processing payment for rental: " + rental.getRentalId());
        System.out.println("Amount: $" + rental.getTotalCost());
        System.out.println("Method: " + paymentMethod);
        
        // In real implementation, integrate with payment gateway
        boolean paymentSuccess = simulatePaymentGateway(paymentDetails);
        
        if (paymentSuccess) {
            rental.setPaymentStatus("paid");
            rental.setStatus("confirmed");
            return true;
        } else {
            rental.setPaymentStatus("failed");
            return false;
        }
    }
    
    public boolean processDeposit(Rental rental, String paymentDetails) {
        System.out.println("Processing deposit for rental: " + rental.getRentalId());
        System.out.println("Deposit amount: $" + rental.getDepositAmount());
        
        boolean depositSuccess = simulatePaymentGateway(paymentDetails);
        
        if (depositSuccess) {
            System.out.println("Deposit processed successfully");
            return true;
        } else {
            System.out.println("Deposit processing failed");
            return false;
        }
    }
    
    public boolean refundDeposit(Rental rental, double refundAmount) {
        System.out.println("Refunding deposit for rental: " + rental.getRentalId());
        System.out.println("Refund amount: $" + refundAmount);
        
        // Simulate refund process
        return true;
    }
    
    private boolean simulatePaymentGateway(String paymentDetails) {
        // Simulate payment gateway response
        // In real implementation, this would integrate with actual payment providers
        return Math.random() > 0.1; // 90% success rate for demo
    }
}
