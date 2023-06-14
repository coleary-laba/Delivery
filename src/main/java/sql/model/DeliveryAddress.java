package sql.model;

public class DeliveryAddress {
    private int addressId;
    private int customerId;
    private String address;

    public DeliveryAddress(int addressId, int customerId, String address) {
        this.addressId = addressId;
        this.customerId = customerId;
        this.address = address;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }
}

