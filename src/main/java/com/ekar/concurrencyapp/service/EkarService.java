package com.ekar.concurrencyapp.service;

import com.ekar.concurrencyapp.models.response.Response;
import com.ekar.concurrencyapp.models.pojo.Request;
import org.springframework.http.ResponseEntity;

public interface EkarService {
    ResponseEntity<Response> process(Request request);
    ResponseEntity<Response> resetCounter(String value);
}
