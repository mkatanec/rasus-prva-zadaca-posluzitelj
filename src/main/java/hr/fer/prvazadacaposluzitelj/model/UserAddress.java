package hr.fer.prvazadacaposluzitelj.model;

public class UserAddress {
    private String ipAddress;

    public UserAddress() {
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
