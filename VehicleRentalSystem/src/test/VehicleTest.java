package test;

import model.Vehicle;
import dao.VehicleDAO;
import java.util.List;

public class VehicleTest {
    public static void main(String[] args) {

        VehicleDAO vehicleDAO = new VehicleDAO();
        
        List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
        System.out.println("All Vehicles:");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.getName() + " - " + vehicle.getRentalPricePerDay());
        }
        
        System.out.println("\nSearch Results:");
        List<Vehicle> searchResults = vehicleDAO.searchVehicles("Toyota", null, null, null);
        for (Vehicle vehicle : searchResults) {
            System.out.println(vehicle.getName());
        }
        
        Vehicle vehicle = vehicleDAO.getVehicleByCode("V001");
        if (vehicle != null) {
            System.out.println("\nFound vehicle: " + vehicle.getName());
        }
    }
}
