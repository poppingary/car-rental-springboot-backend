package agk.wow.carrental.rpcdomain.request;

public class VehicleTypeRequest {
    private String excessMileageFee;
    private String serviceRate;
    private String vehicleType;

    public String getExcessMileageFee() {
        return excessMileageFee;
    }

    public void setExcessMileageFee(String excessMileageFee) {
        this.excessMileageFee = excessMileageFee;
    }

    public String getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(String serviceRate) {
        this.serviceRate = serviceRate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}