package agk.wow.carrental.constant;

public enum ResponseBodyMessage {
    SUCCESS("Success"),
    USER_IS_REGISTERED("User is already registered"),
    NO_CUSTOMER("Can't find the customer by customer id"),
    PASSWORD_NOT_MATCH("Current password doesn't match");

    String message;

    ResponseBodyMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}