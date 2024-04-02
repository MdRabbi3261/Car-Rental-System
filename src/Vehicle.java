import java.util.*;
class Vehicle {
    public String vehicleId;
    public String brand;
    public String model;
    public double perDayPrice;
    public boolean isAvailable;
    public Vehicle(String vehicleId, String brand, String model, double perDayPrice) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.perDayPrice = perDayPrice;
        this.isAvailable = true;
    }
    public String getVehicleId() {
        return vehicleId;
    }
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
    public double getPerDayPrice() {
        return perDayPrice;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void rent() {
        isAvailable = false;
    }
    public void returnVehicle() {
        isAvailable = true;
    }
    public double calculatePrice(int days) {
        return days*perDayPrice;
    }
}