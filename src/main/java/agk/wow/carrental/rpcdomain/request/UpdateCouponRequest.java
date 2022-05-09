package agk.wow.carrental.rpcdomain.request;

public class UpdateCouponRequest extends CouponRequest {
    private String isUsed;

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }
}