class Rental  {
    private Vehicle vehicle;
    private Customer customer;
    private int days;
    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public Customer getCustomer() {
        return customer;
    }
    public int getDays() {
        return days;
    }
}