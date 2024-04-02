class Customer {
    private String customerId;
    private String name;
    private String nidNumber;
    private String mobileNumber;
    public Customer(String customerId, String name, String nidNumber, String mobileNumber) {
        this.customerId = customerId;
        this.name = name;
        this.nidNumber = nidNumber;
        this.mobileNumber = mobileNumber;
    }
    public String getCustomerId() {
        return customerId;
    }
    public String getName() {
        return name;
    }
    public String getNidNumber() {
        return nidNumber;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
}