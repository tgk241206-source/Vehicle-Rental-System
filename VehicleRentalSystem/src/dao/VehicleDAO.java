package dao;

import model.Vehicle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    // Add vehicle
    public boolean addVehicle(Vehicle v) {
        String sql = "INSERT INTO Vehicle(type, brand, price_per_day, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, v.getType());
            ps.setString(2, v.getBrand());
            ps.setBigDecimal(3, v.getPricePerDay());
            ps.setString(4, v.getStatus());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding vehicle: " + e.getMessage());
            return false;
        }
    }

    // Update vehicle
    public boolean updateVehicle(Vehicle v) {
        String sql = "UPDATE Vehicle SET type = ?, brand = ?, price_per_day = ?, status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, v.getType());
            ps.setString(2, v.getBrand());
            ps.setBigDecimal(3, v.getPricePerDay());
            ps.setString(4, v.getStatus());
            ps.setInt(5, v.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating vehicle: " + e.getMessage());
            return false;
        }
    }

    // Delete vehicle
    public boolean deleteVehicle(int id) {
        String sql = "DELETE FROM Vehicle WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting vehicle: " + e.getMessage());
            return false;
        }
    }

    // Get vehicle by ID
    public Vehicle getVehicleById(int id) {
        String sql = "SELECT * FROM Vehicle WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Vehicle(rs.getInt("id"), rs.getString("type"),
                                       rs.getString("brand"), rs.getBigDecimal("price_per_day"),
                                       rs.getString("status"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching vehicle by ID: " + e.getMessage());
        }
        return null;
    }

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Vehicle(rs.getInt("id"), rs.getString("type"),
                                     rs.getString("brand"), rs.getBigDecimal("price_per_day"),
                                     rs.getString("status")));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching all vehicles: " + e.getMessage());
        }
        return list;
    }

    // Search vehicle by type or brand
    public List<Vehicle> searchVehicle(String keyword) {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle WHERE type LIKE ? OR brand LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Vehicle(rs.getInt("id"), rs.getString("type"),
                                         rs.getString("brand"), rs.getBigDecimal("price_per_day"),
                                         rs.getString("status")));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error searching vehicle: " + e.getMessage());
        }
        return list;
    }
}
