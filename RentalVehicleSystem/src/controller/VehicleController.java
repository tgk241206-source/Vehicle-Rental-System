package controller;

import java.util.List;
import model.Vehicle;
import service.VehicleService;

public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController() {
        this.vehicleService = new VehicleService();
    }

    // Lay toan bo xe 
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    // Tim kiem xe
    public List<Vehicle> search(String keyword) {
        return vehicleService.search(keyword);
    }

    // Lay xe theo ID
    public Vehicle getVehicleById(int id) {
        return vehicleService.getVehicleById(id);
    }

    // Loc theo loai
    public List<Vehicle> filterByCategory(String category) {
        return vehicleService.filterByCategory(category);
    }
    
    public void showVehicleDetail(int id) {
        Vehicle v = getVehicleById(id);
        if (v != null) {
            System.out.println(v);
        } else {
            System.out.println("Khong tim thay xe.");
        }
    }
}