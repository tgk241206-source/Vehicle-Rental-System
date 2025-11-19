package controller;

import model.Customer;
import service.AuthService;
import util.EmailSender;

public class AuthController {

    private AuthService authService;

    public AuthController() {
        this.authService = new AuthService();
    }

    // Dang ky tai khoan
    public boolean register(String name, String email, String password, String phone, String address) {

        // Kiem tra trung email
        boolean exists = authService.checkEmailExists(email);
        if (exists) {
            System.out.println(">> Loi: Email da ton tai trong he thong!");
            return false;
        }

        // Tao doi tuong Customer
        Customer newCustomer = new Customer(0, name, email, password, phone, address);

        boolean success = authService.register(newCustomer);

        // Gui email neu thanh cong
        if (success) {
            EmailSender.send(
                email, 
                "Chao mung thanh vien moi", 
                "Dang ky thanh cong! Chao mung " + name + " den voi dich vu thue xe."
            );
        }
        return success;
    }

    // Dang nhap
    public Customer login(String email, String password) {

        Customer customer = authService.login(email, password);

        if (customer == null) {
            System.out.println(">> Dang nhap that bai: Sai email hoac mat khau!");
        } else {
            System.out.println(">> Dang nhap thanh cong! Xin chao " + customer.getName());
        }

        return customer;
    }

    // Quen mat khau
    public void recoverPassword(String email) {

        String newPass = authService.recoverPassword(email);

        if (newPass == null) {
            System.out.println(">> Loi: Email khong ton tai trong he thong!");
            return;
        }

        // Mo phong gui email
        EmailSender.send(
                email,
                "Khoi phuc mat khau",
                "Mat khau moi cua ban la: " + newPass);

        System.out.println(">> He thong: Mat khau moi da duoc gui toi email " + email);
    }
}