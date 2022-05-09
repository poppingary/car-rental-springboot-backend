package agk.wow.carrental.rpcdomain.request;

public class UpdateCouponRequest extends CouponRequest {
    private String couponCode;
    private String isUsed;

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }
}