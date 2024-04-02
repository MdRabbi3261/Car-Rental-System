public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();
        Car car1 = new Car("c001", "Toyota", "Camry", 50.0);
        Car car2 = new Car("c002", "Honda", "Accord", 100.0);
        Car car3 = new Car("c003", "Mahindra", "Thar", 150.0);
        Bus bus1 = new Bus("b001", "Volvo", "XC90", 200.0);
        Bus bus2 = new Bus("b002", "Mercedes", "Sprinter", 250.0);
        Truck truck1 = new Truck("t001", "Ford", "F-150", 300.0);
        Truck truck2 = new Truck("t002", "Chevrolet", "Silverado", 350.0);
        rentalSystem.addVehicle(car1);
        rentalSystem.addVehicle(car2);
        rentalSystem.addVehicle(car3);
        rentalSystem.addVehicle(bus1);
        rentalSystem.addVehicle(bus2);
        rentalSystem.addVehicle(truck1);
        rentalSystem.addVehicle(truck2);
        rentalSystem.menu();
    }
}
