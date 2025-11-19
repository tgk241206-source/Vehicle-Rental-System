package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Vehicle;

public class VehicleDAO {

    private static List<Vehicle> vehicles = new ArrayList<>();
    private static int idCounter = 1;

    // Tao du lieu mau
    static {
        vehicles.add(new Vehicle(idCounter++, "Camry", "Toyota", "Sedan", 50.0, "Available", "New Car"));
        vehicles.add(new Vehicle(idCounter++, "Civic", "Honda", "Sedan", 45.0, "Rented", "Good condition"));
        vehicles.add(new Vehicle(idCounter++, "CX-5", "Mazda", "SUV", 60.0, "Available", "Family car"));
        vehicles.add(new Vehicle(idCounter++, "Ranger", "Ford", "Truck", 70.0, "Maintenance", "Off-road"));
    }

    // Lay tat ca xe
    public List<Vehicle> getAll() {
        return vehicles;
    }

    // Lay xe theo ID
    public Vehicle findById(int id) {
        return vehicles.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Tim kiem theo ten hoac hang xe
    public List<Vehicle> search(String keyword) {
        String key = keyword.toLowerCase();
        return vehicles.stream()
                .filter(v -> v.getName().toLowerCase().contains(key) || 
                             v.getBrand().toLowerCase().contains(key))
                .collect(Collectors.toList());
    }

    // Loc theo loai xe
    public List<Vehicle> filterCategory(String category) {
        return vehicles.stream()
                .filter(v -> v.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Them xe
    public boolean add(Vehicle v) {
        v.setId(idCounter++);
        if (v.getStatus() == null) v.setStatus("Available");
        vehicles.add(v);
        return true;
    }

    // Xoa xe
    public boolean delete(int id) {
        return vehicles.removeIf(v -> v.getId() == id);
    }
    // Dem xe dang cho thue
    public int countRentedVehicles() {
        return (int) vehicles.stream()
                .filter(v -> "Rented".equalsIgnoreCase(v.getStatus()))
                .count();
    }

    // Cap nhat trang thai xe
    public boolean updateStatus(int id, String status) {
        Vehicle v = findById(id);
        if (v != null) {
            v.setStatus(status);
            return true; 
        }
        return false;
    }
}