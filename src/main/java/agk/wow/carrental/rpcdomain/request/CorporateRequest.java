package agk.wow.carrental.rpcdomain.request;

public class CorporateRequest {
    private String registrationNumber;
    private String corporateDiscount;
    private String corporateName;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCorporateDiscount() {
        return corporateDiscount;
    }

    public void setCorporateDiscount(String corporateDiscount) {
        this.corporateDiscount = corporateDiscount;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }
}