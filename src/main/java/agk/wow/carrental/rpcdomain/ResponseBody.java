package agk.wow.carrental.rpcdomain;

import java.io.Serializable;

public class ResponseBody<T> implements Serializable {
    private static final long serialVersionUID = 623922752035245707L;

    String message;
    T data;

    public ResponseBody() {

    }

    public ResponseBody(String message) {
        this.message = message;
    }

    public static ResponseBody FAIL(String message) {
        return new ResponseBody(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}