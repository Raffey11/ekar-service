package com.ekar.concurrencyapp.controllers;

import com.ekar.concurrencyapp.models.response.Response;
import com.ekar.concurrencyapp.models.pojo.Request;
import com.ekar.concurrencyapp.service.EkarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1.0")
@RequiredArgsConstructor
public class EkarController {

    private final EkarService ekarService;

    @PostMapping(value = "/create",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Response> createThreads(@RequestBody Request request) {
        log.info("Create Thread(s) Request Received : {}", request.toString());
        return ekarService.process(request);
    }

    @PostMapping(value = "/change-number",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Response> changeCounter(@RequestParam("counter") String counter) {
        log.info("Reset Counter Request Received, Counter Value : {}", counter);
        return ekarService.resetCounter(counter);
    }

}
