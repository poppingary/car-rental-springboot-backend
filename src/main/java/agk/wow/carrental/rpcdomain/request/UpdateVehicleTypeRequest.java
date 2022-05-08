package agk.wow.carrental.rpcdomain.request;

public class UpdateVehicleTypeRequest extends VehicleTypeRequest {
    private String vehicleTypeId;

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
}