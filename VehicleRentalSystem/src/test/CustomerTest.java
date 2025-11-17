package test;

import model.Customer;
import dao.CustomerDAO;

public class CustomerTest {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();
        
        // Test customer registration
        Customer newCustomer = new Customer("test@email.com", "test123", "Test User", "0123456789");
        boolean success = customerDAO.addCustomer(newCustomer);
        System.out.println("Registration successful: " + success);
        
        // Test login
        boolean validLogin = customerDAO.validateLogin("john@email.com", "password123");
        System.out.println("Login valid: " + validLogin);
        
        // Test invalid login
        boolean invalidLogin = customerDAO.validateLogin("john@email.com", "wrongpassword");
        System.out.println("Invalid login handled: " + !invalidLogin);
    }
}
