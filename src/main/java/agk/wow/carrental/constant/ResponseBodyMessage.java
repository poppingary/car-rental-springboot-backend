package agk.wow.carrental.constant;

public enum ResponseBodyMessage {
    SUCCESS("Success"),
    USER_IS_REGISTERED("User is already registered");

    String message;

    ResponseBodyMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}