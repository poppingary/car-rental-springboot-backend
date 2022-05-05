package agk.wow.carrental.rpcdomain.request;

public class ReservationRequest {
    private String pickupDate;
    private String dropOffDate;
    private String startOdometer;
    private String endOdometer;
    private String dailyMileageLimit;
    private String customerId;
    private String vehicleId;
    private String pickupLocationId;
    private String dropOffLocationId;

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(String dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public String getStartOdometer() {
        return startOdometer;
    }

    public void setStartOdometer(String startOdometer) {
        this.startOdometer = startOdometer;
    }

    public String getEndOdometer() {
        return endOdometer;
    }

    public void setEndOdometer(String endOdometer) {
        this.endOdometer = endOdometer;
    }

    public String getDailyMileageLimit() {
        return dailyMileageLimit;
    }

    public void setDailyMileageLimit(String dailyMileageLimit) {
        this.dailyMileageLimit = dailyMileageLimit;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPickupLocationId() {
        return pickupLocationId;
    }

    public void setPickupLocationId(String pickupLocationId) {
        this.pickupLocationId = pickupLocationId;
    }

    public String getDropOffLocationId() {
        return dropOffLocationId;
    }

    public void setDropOffLocationId(String dropOffLocationId) {
        this.dropOffLocationId = dropOffLocationId;
    }
}