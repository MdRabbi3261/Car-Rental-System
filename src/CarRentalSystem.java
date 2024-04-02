// import java.util.*;
// import java.util.regex.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarRentalSystem {
    public List<Vehicle> vehicles;
    public List<Customer> customers;
    public List<Rental> rentals;
    public List<Rental>crentals;
    public CarRentalSystem() {
        vehicles = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
        crentals=new ArrayList<>();
    }
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentVehicle(Vehicle vehicle, Customer customer, int days) {
        if (vehicle.isAvailable()) {
            vehicle.rent();
            Rental rental = new Rental(vehicle, customer, days);
            rentals.add(rental);
            crentals.add(rental);
        }
        else {
            System.out.println("Vehicle is not available for rent.");
        }
    }
    public void returnVehicle(Vehicle vehicle) {
        vehicle.returnVehicle();
        int flag = 0;
        for (Rental rental : rentals) {
            if (rental.getVehicle() == vehicle) {
                rentals.remove(rental);
                flag = 1;
                break;
            }
        }
        if (flag == 0)
            System.out.println("Vehicle was not rented.");
    }
    boolean isValidMobileNumber(String mobileNumber)
    {
        String MOBILE_PATTERN = "^01[3-9]\\d{8}$"; // Starts with "01", 3rd digit > 2, total 11 digits
        Pattern pattern = Pattern.compile(MOBILE_PATTERN);
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }
    public
    boolean validateNID(String nid)
    {
        Pattern pattern = Pattern.compile("\\d{10}");
        Matcher matcher = pattern.matcher(nid);
        return matcher.matches();
    }
    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Vehicle");
            System.out.println("2. Return a Vehicle");
            System.out.println("3. Add a Vehicle ");
            System.out.println("4. Remove a Vehicle");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                System.out.print("Enter your name: ");
                String customerName = sc.nextLine();
                String nid_number;
                while (true) {
                    System.out.println("Enter Your Nid Number Only Use 10 Digit Number : ");
                    nid_number = sc.nextLine();
                    if (!validateNID(nid_number)) {
                        System.out.println("Invalid NID number format.");
                        System.out.println("Please Enter Your Wright Nid Number");
                    } else
                        break;
                }
                String mobile_number;
                while (true) {
                    System.out.print("Enter your mobile number (11 digits starting with '01'): ");
                    mobile_number = sc.nextLine();
                    if (!isValidMobileNumber(mobile_number)) {
                        System.out.println("Invalid mobile number.");
                        System.out.println("Please enter a valid 11-digit number starting with '01' and having the 3rd digit greater than 2.");
                    } else {
                        break;
                    }
                }
                while (true) {
                    System.out.println("\n== Rent a Vehicle ==\n");
                    System.out.println("Select vehicle type:");
                    System.out.println("1. Car");
                    System.out.println("2. Bus");
                    System.out.println("3. Truck");
                    int vehicleType = sc.nextInt();
                    sc.nextLine();
                    System.out.println("\nAvailable Vehicles:");
                    for (Vehicle vehicle : vehicles) {
                        if ((vehicle instanceof Car && vehicleType == 1 && vehicle.isAvailable()) ||
                                (vehicle instanceof Bus && vehicleType == 2 && vehicle.isAvailable()) ||
                                (vehicle instanceof Truck && vehicleType == 3 && vehicle.isAvailable())) {
                            System.out.println(vehicle.getVehicleId() + " - " + vehicle.getBrand() + " " + vehicle.getModel());
                        }
                    }
                    System.out.println("\n Enter the vehicle ID you want to rent: ");
                    String vehicleId = sc.nextLine();
                    System.out.print("Enter the number of days for rental: ");
                    int rentalDays = sc.nextInt();
                    sc.nextLine();
                    Customer newCustomer = new Customer("cus" + (customers.size() + 1), customerName,nid_number , mobile_number);
                    addCustomer(newCustomer);
                    Vehicle selectedVehicle = null;
                    for (Vehicle vehicle : vehicles) {
                        if (vehicle.getVehicleId().equals(vehicleId) && vehicle.isAvailable()) {
                            selectedVehicle = vehicle;
                            break;
                        }
                    }
                    if (selectedVehicle != null) {
                        double Totalprice=0;
                        double totalPrice = selectedVehicle.getPerDayPrice() * rentalDays;
                        System.out.println("\n== Rental Information ==\n");
                        System.out.println("Vehicle: " + selectedVehicle.getBrand() + " " + selectedVehicle.getModel());
                        System.out.println("Rental Days: " + rentalDays);
                        System.out.printf("Total Price: $%.2f%n", totalPrice);
                        System.out.print("\nConfirm rental (Y/N): ");
                        String confirm = sc.nextLine();
                        if (confirm.equalsIgnoreCase("Y")) {
                            rentVehicle(selectedVehicle,newCustomer,rentalDays);
                            System.out.println("\nVehicle rented successfully.");
                            System.out.println("\nOptions:");
                            System.out.println("1. Rent another Vehicle");
                            System.out.println("2. Show Bill");
                            System.out.print("Enter your choice: ");
                            int ch = sc.nextInt();
                            sc.nextLine();
                            if (ch == 2) {
                                System.out.println("\n== Rental Bill ==\n");
                                System.out.println("Customer Name: " + newCustomer.getName());
                                System.out.println("Customer Nid number: " + newCustomer.getNidNumber());
                                System.out.println("Customer Mobile number: " + newCustomer.getMobileNumber());
                                int i=1;
                                for(Rental x:crentals)
                                {
                                    Vehicle v=x.getVehicle();
                                    double price=v.calculatePrice(x.getDays());
                                    System.out.println(i+" "+v.getBrand()+" "+v.getModel()+"  "+"(Rental Days: "+x.getDays()+")"+" Price= "+price);
                                    i++;
                                    Totalprice+=price;
                                }
                                System.out.println("Total Bill: $" + Totalprice);
                                crentals.clear();
                                break;
                            }
                        }
                        else
                        {
                            System.out.println("\nRental canceled.");
                            System.out.println("\nOptions:");
                            System.out.println("1. Rent another Vehicle");
                            System.out.println("2. Show Bill");
                            System.out.print("Enter your choice: ");
                            int ch = sc.nextInt();
                            sc.nextLine();
                            if (ch == 2) {
                                System.out.println("\n== Rental Bill ==\n");
                                System.out.println("Customer Name: " + newCustomer.getName());
                                System.out.println("Customer Nid number: " + newCustomer.getNidNumber());
                                System.out.println("Customer Mobile number: " + newCustomer.getMobileNumber());
                                int i=1;
                                for(Rental x:crentals)
                                {
                                    Vehicle v=x.getVehicle();
                                    double price=v.calculatePrice(x.getDays());
                                    System.out.println(i+" "+v.getBrand()+" "+v.getModel()+"  "+"(Rental Days: "+x.getDays()+")"+" Price= "+price);
                                    i++;
                                    Totalprice+=price;
                                }
                                System.out.println("Total Bill: $" + Totalprice);
                                crentals.clear();
                                break;
                            }
                        }
                    } else {
                        System.out.println("\nInvalid vehicle selection or vehicle not available for rent.");
                    }
                }
            } else if (choice == 2) {
                System.out.print("Enter the ID of the vehicle you want to return: ");
                String vehicleId = sc.nextLine();
                Vehicle vehicleToReturn = null;
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.getVehicleId().equals(vehicleId) && !vehicle.isAvailable()) {
                        vehicleToReturn = vehicle;
                        break;
                    }
                }
                if (vehicleToReturn != null) {
                    returnVehicle(vehicleToReturn);
                    System.out.println("Vehicle returned successfully.");
                } else {
                    System.out.println("Invalid vehicle ID or vehicle is not rented.");
                }
            } else if (choice == 3) {
                System.out.println("\n== Add a Vehicle ==\n");
                System.out.println("Select vehicle type to add:");
                System.out.println("1. Car");
                System.out.println("2. Bus");
                System.out.println("3. Truck");
                int vehicleType = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter vehicle ID: ");
                String vehicleId = sc.nextLine();
                System.out.print("Enter vehicle brand: ");
                String brand = sc.nextLine();
                System.out.print("Enter vehicle model: ");
                String model = sc.nextLine();
                System.out.print("Enter vehicle rental price per day: ");
                double pricePerDay = sc.nextDouble();
                sc.nextLine();
                Car newVehicle = null;
                if (vehicleType == 1) {
                    newVehicle = new Car (vehicleId, brand, model, pricePerDay);
                } else if (vehicleType == 2) {
                    newVehicle = new Car(vehicleId, brand, model, pricePerDay);
                } else if (vehicleType == 3) {
                    newVehicle = new Car(vehicleId, brand, model, pricePerDay);
                }
                if (newVehicle != null) {
                    addVehicle(newVehicle);
                    System.out.println("Vehicle added successfully.");
                } else {
                    System.out.println("Invalid vehicle type.");
                }
            } else if (choice == 4) {
                System.out.println("\n== Remove a Vehicle ==\n");
                System.out.println("Select vehicle type to add:");
                System.out.println("1. Car");
                System.out.println("2. Bus");
                System.out.println("3. Truck");
                int vehicleType = sc.nextInt();
                sc.nextLine();
                System.out.println("\nAvailable Vehicles:");
                for (Vehicle vehicle : vehicles) {
                    if ((vehicle instanceof Car && vehicleType == 1)||
                            (vehicle instanceof Bus && vehicleType == 2) ||
                            (vehicle instanceof Truck && vehicleType == 3)) {
                        System.out.println(vehicle.getVehicleId() + " - " + vehicle.getBrand() + " " + vehicle.getModel());
                    }
                }
                System.out.print("Enter the ID of the vehicle to remove: ");
                String vehicleId = sc.nextLine();
                boolean removed = false;
                Iterator<Vehicle> iterator = vehicles.iterator();
                while (iterator.hasNext()) {
                    Vehicle vehicle = iterator.next();
                    if (vehicle.getVehicleId().equals(vehicleId)) {
                        iterator.remove();
                        removed = true;
                        System.out.println("Vehicle removed successfully.");
                        break;
                    }
                }
                if (!removed)
                    System.out.println("Vehicle not found.");
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
            System.out.println("\nThank you for using the Car Rental System!");
        }
    }


}