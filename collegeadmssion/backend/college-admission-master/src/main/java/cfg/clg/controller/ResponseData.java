package cfg.clg.controller;

public class ResponseData<T> {
    private String status;
    private String message;
    private T data;

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for message
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter and Setter for data
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
