package net.springbootdemo.exception;


public interface ErrorCode {
    int INTERNAL_SERVER_ERROR = 1; // internal server error
    int REST_ERROR = 2; // all framework errors
    int ACCESS_DENIED = 3; // access is denied
    int VALIDATION_FAILED = 4; // validationError
    int ILLEGAL_STATE = 5; // illegalStateError
    int DUPLICATE = 6; // duplicate
    int NOT_FOUND = 7; // not found
}
