package agk.wow.carrental.constant;

public enum VehicleAvailable {
    YES("Y"),
    NO("N");

    String available;

    VehicleAvailable(String available) {
        this.available = available;
    }

    public String getAvailable() {
        return available;
    }
}