package agk.wow.carrental.rpcdomain.request;

import java.util.List;

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
    private String isUnlimited;
    private String couponCode;
    private List<PaymentRequest> payments;

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

    public String getIsUnlimited() {
        return isUnlimited;
    }

    public void setIsUnlimited(String isUnlimited) {
        this.isUnlimited = isUnlimited;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public List<PaymentRequest> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentRequest> payments) {
        this.payments = payments;
    }
}