package service;

import dao.PaymentDAO;
import model.Rental;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PaymentService {
    private final PaymentDAO paymentDAO;
    private final PricingRules pricingRules; // injected config for base price, insurance, tax, discounts

    public PaymentService(PaymentDAO paymentDAO, PricingRules pricingRules) {
        this.paymentDAO = paymentDAO;
        this.pricingRules = pricingRules;
    }

    public double calculateRentalAmount(int vehicleID, LocalDateTime pickup, LocalDateTime drop, String discountCode) {
        long days = Math.max(1, ChronoUnit.DAYS.between(pickup, drop));
        double base = pricingRules.getBasePrice(vehicleID) * days;
        double insurance = pricingRules.getInsuranceFee(vehicleID, days);
        double addons = pricingRules.getAddOnFee(vehicleID);
        double tax = pricingRules.applyTax(base + insurance + addons);
        double discount = pricingRules.applyDiscount(discountCode, base + insurance + addons + tax);
        return base + insurance + addons + tax - discount;
    }

    public void preAuthorizeDeposit(Rental rental) {
        double deposit = pricingRules.getDeposit(rental.getVehicleID());
        rental.setDepositAmount(deposit);
        rental.setDepositStatus(Rental.DepositStatus.PRE_AUTHORIZED);
        paymentDAO.preAuthorize(rental.getOrderID(), deposit);
    }

    public void settleOnReturn(Rental rental, double surchargeTotal) {
        double currentPaid = paymentDAO.getPaidAmount(rental.getOrderID());
        double due = surchargeTotal - rental.getDepositAmount();
        if (due > 0) paymentDAO.captureAdditional(rental.getOrderID(), due);
        else paymentDAO.refund(rental.getOrderID(), Math.abs(due));
        paymentDAO.markFinalized(rental.getOrderID());
        rental.setDepositStatus(Rental.DepositStatus.REFUNDED);
    }

    public void processCancellation(Rental rental) {
        double fee = pricingRules.getCancellationFee(rental);
        if (fee > 0) paymentDAO.captureAdditional(rental.getOrderID(), fee);
        else paymentDAO.refund(rental.getOrderID(), rental.getDepositAmount());
    }
}
