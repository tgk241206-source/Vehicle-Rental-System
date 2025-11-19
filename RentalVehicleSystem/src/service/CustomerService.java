package service;

import dao.CustomerDAO;
import model.Customer;

public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    // Lay thong tin khach hang theo ID
    public Customer getCustomerById(int id) {
        return customerDAO.findById(id);
    }

    // Cap nhat thong tin
    public boolean updateCustomer(Customer c) {
        return customerDAO.update(c);
    }

    // Doi mat khau
    public boolean changePassword(int id, String oldPass, String newPass) {
        return customerDAO.changePassword(id, oldPass, newPass);
    }
}