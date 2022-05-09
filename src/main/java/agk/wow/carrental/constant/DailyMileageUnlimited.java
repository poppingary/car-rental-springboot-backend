package agk.wow.carrental.constant;

public enum DailyMileageUnlimited {
    YES("Y"),
    NO("N");

    String unlimitation;

    DailyMileageUnlimited(String unlimitation) {
        this.unlimitation = unlimitation;
    }

    public String getUnlimitation() {
        return unlimitation;
    }
}