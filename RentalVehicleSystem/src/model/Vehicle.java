package model;

public class Vehicle {

    private int id;
    private String name;
    private String brand;
    private String category;
    private double pricePerDay;
    private String status;
    private String description;

    public Vehicle() {
    }

    public Vehicle(int id, String name, String brand, String category,
            double pricePerDay, String status, String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.pricePerDay = pricePerDay;
        this.status = status;
        this.description = description;
    }


    public Vehicle(String name, String brand, String category,
            double pricePerDay, String status, String description) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.pricePerDay = pricePerDay;
        this.status = status;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModelName() {
        return this.brand + " " + this.name; 
    }

    @Override
    public String toString() {
        return "Vehicle {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}

