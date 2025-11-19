package service;

import dao.CustomerDAO;
import model.Customer;

public class AuthService {

    private CustomerDAO customerDAO = new CustomerDAO();

    // Dang ky tai khoan
    public boolean register(Customer c) {
        return customerDAO.insert(c);
    }

    // Dang nhap
    public Customer login(String email, String password) {
        return customerDAO.checkLogin(email, password);
    }

    // Kiem tra email da ton tai chua
    public boolean checkEmailExists(String email) {
        return customerDAO.findByEmail(email) != null;
    }

    // Quen mat khau – tao mat khau moi va gui lai
    public String recoverPassword(String email) {

        Customer c = customerDAO.findByEmail(email);

        if (c == null) {
            return null;
        }

        // Tao mat khau moi
        String newPass = "123456";

        // Cap nhat vao DB
        boolean updated = customerDAO.changePassword(c.getId(), c.getPassword(), newPass);

        if (!updated)
            return null;

        return newPass;
    }

}

