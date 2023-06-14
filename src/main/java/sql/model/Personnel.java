package sql.model;

public class Personnel {
    private int personnelId;
    private String personnelName;
    private String address;
    private String phoneNumber;

    public Personnel(int personnelId, String personnelName, String address, String phoneNumber) {
        this.personnelId = personnelId;
        this.personnelName = personnelName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getPersonnelId() {
        return personnelId;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
