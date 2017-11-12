package com.tsystems.service.exception;

/**
 * Schedule service exception.
 */
class ServiceException extends RuntimeException {
    ServiceException(String message){
        super(message);
    }
}
