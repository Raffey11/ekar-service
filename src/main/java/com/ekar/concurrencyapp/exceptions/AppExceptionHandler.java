package com.ekar.concurrencyapp.exceptions;

import com.ekar.concurrencyapp.models.response.ErrorResponse;
import com.ekar.concurrencyapp.util.constants.ApiStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RequestValidationException.class})
    public ResponseEntity<Object> handlePaymentServiceException(RequestValidationException ex, WebRequest request) {
        String errorMessageDetail = ex.getLocalizedMessage();
        if (StringUtils.isEmpty(errorMessageDetail)) errorMessageDetail = ex.toString();
        ErrorResponse response = new ErrorResponse();
        response.setStatus(ApiStatus.BAD_REQUEST_CODE);
        response.setMessage(ApiStatus.BAD_REQUEST);
        response.setErrors(errorMessageDetail);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {JsonProcessingException.class})
    public ResponseEntity<Object> handlePaymentServiceException(JsonProcessingException ex, WebRequest request) {
        String errorMessageDetail = ex.getLocalizedMessage();
        if (StringUtils.isEmpty(errorMessageDetail)) errorMessageDetail = ex.toString();
        ErrorResponse response = getJsonErrorErrorResponse(errorMessageDetail);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse getJsonErrorErrorResponse(String errorMessageDetail) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(ApiStatus.INTERNAL_SERVER_ERROR_CODE);
        response.setMessage(ApiStatus.INTERNAL_SERVER_ERROR);
        response.setErrors(errorMessageDetail);
        return response;
    }
}
