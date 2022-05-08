package agk.wow.carrental.rpcdomain.request;

public class UpdateLocationRequest extends LocationRequest {
    private String locationId;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
