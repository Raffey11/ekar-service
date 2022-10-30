package com.ekar.concurrencyapp.util.factories;

import com.ekar.concurrencyapp.models.response.Response;
import com.ekar.concurrencyapp.util.constants.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseBuilderFactory {

    private static final String COUNTER_SUCCESS_RESPONSE = "counter value has been set to %s";

    private ApiResponseBuilderFactory() {}

    public static ResponseEntity<Response> buildSuccessApiResponse(String message) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Response.builder()
                        .status(ApiStatus.SUCCESS)
                        .message(message)
                        .build());
    }

    public static ResponseEntity<Response> buildCounterResetSuccessResponse(String value) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Response.builder()
                        .status(ApiStatus.SUCCESS)
                        .message(String.format(COUNTER_SUCCESS_RESPONSE, value))
                        .build());
    }
}