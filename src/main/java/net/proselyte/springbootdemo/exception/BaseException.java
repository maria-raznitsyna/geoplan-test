package net.proselyte.springbootdemo.exception;

/**
 * Base exception
 *
 * @author val.rudi
 */
public class BaseException extends RuntimeException {
    private int errorCode;

    public BaseException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public BaseException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BaseException(Throwable cause, int errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
