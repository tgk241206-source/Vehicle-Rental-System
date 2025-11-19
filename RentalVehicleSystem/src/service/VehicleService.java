package service;

import dao.VehicleDAO;
import java.util.List;
import model.Vehicle;

public class VehicleService {

    private VehicleDAO vehicleDAO = new VehicleDAO();

    // Lay tat ca xe
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.getAll();
    }

    // Lay thong tin xe bang ID
    public Vehicle getVehicleById(int id) {
        return vehicleDAO.findById(id);
    }

    // Tim kiem xe theo tu khoa
    public List<Vehicle> search(String keyword) {
        return vehicleDAO.search(keyword);
    }

    // Loc xe theo loai
    public List<Vehicle> filterByCategory(String category) {
        return vehicleDAO.filterCategory(category);
    }

    // Admin them xe
    public boolean addVehicle(Vehicle v) {
        return vehicleDAO.add(v);
    }

    // Admin xoa xe
    public boolean deleteVehicle(int id) {
        return vehicleDAO.delete(id);
    }

    // Cap nhat trang thai xe 
    public boolean updateVehicleStatus(int id, String status) {
        return vehicleDAO.updateStatus(id, status);
    }

}
