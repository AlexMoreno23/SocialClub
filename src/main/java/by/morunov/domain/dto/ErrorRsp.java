package by.morunov.domain.dto;

/**
 * @author Alex Morunov
 */
public class ErrorRsp {
    private String message;
    private int httpCode;

    public ErrorRsp(String message, int httpCode) {
        this.message = message;
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    @Override
    public String toString() {
        return "ErrorRsp{" +
                "message='" + message + '\'' +
                ", httpCode=" + httpCode +
                '}';
    }
}
