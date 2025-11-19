package controller;

import model.Customer;
import service.CustomerService;
import util.EmailSender;

public class CustomerController {

    private CustomerService customerService;

    public CustomerController() {
        this.customerService = new CustomerService();
    }

    // Xem thong tin ca nhan
    public void viewProfile(int customerId) {
        Customer c = customerService.getCustomerById(customerId);

        if (c == null) {
            System.out.println("Khong tim thay khach hang.");
            return;
        }

        System.out.println("\n===== THONG TIN CA NHAN =====");
        System.out.println("Ho ten: " + c.getName());
        System.out.println("Email: " + c.getEmail());
        System.out.println("SDT: " + c.getPhone());
        System.out.println("Dia chi: " + c.getAddress());
        System.out.println("Gioi tinh: " + c.getGender());
        System.out.println("Ngay sinh: " + c.getDob());
    }

    // Cap nhat thong tin ca nhan
    public void updateProfile(Customer updatedInfo) {

        boolean success = customerService.updateCustomer(updatedInfo);

        if (success) {
            System.out.println("Cap nhat thong tin thanh cong!");

            EmailSender.send(
                    updatedInfo.getEmail(),
                    "Cap nhat thong tin ca nhan",
                    "Thong tin tai khoan cua ban da duoc cap nhat thanh cong.");
        } else {
            System.out.println("Loi! Khong the cap nhat thong tin.");
        }
    }

    // Doi mat khau
    public void changePassword(int customerId, String oldPass, String newPass) {

        boolean success = customerService.changePassword(customerId, oldPass, newPass);

        if (success) {
            System.out.println("Doi mat khau thanh cong!");

            Customer c = customerService.getCustomerById(customerId);

            EmailSender.send(
                    c.getEmail(),
                    "Doi mat khau",
                    "Mat khau cua ban da duoc thay doi thanh cong.");
        } else {
            System.out.println("Mat khau cu khong dung hoac loi he thong.");
        }
    }

}