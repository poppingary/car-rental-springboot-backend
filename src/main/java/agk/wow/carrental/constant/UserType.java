package agk.wow.carrental.constant;

public enum UserType {
    MANAGER("Manager"),
    CUSTOMER("Customer"),
    EMPLOYEE("Employee");

    String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}