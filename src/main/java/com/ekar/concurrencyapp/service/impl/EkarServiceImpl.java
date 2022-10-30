package com.ekar.concurrencyapp.service.impl;

import com.ekar.concurrencyapp.data.RequestResponseModelService;
import com.ekar.concurrencyapp.exceptions.RequestValidationException;
import com.ekar.concurrencyapp.models.*;
import com.ekar.concurrencyapp.models.pojo.Request;
import com.ekar.concurrencyapp.models.pojo.RequestResponseModel;
import com.ekar.concurrencyapp.models.response.Response;
import com.ekar.concurrencyapp.service.EkarService;
import com.ekar.concurrencyapp.util.constants.Errors;
import com.ekar.concurrencyapp.util.constants.ResponseMessage;
import com.ekar.concurrencyapp.util.factories.ApiResponseBuilderFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public final class EkarServiceImpl implements EkarService {

    private final RequestResponseModelService requestResponseModelService;
    private final Counter counter;
    private final ObjectMapper mapper;

    @Override
    public ResponseEntity<Response> process(Request request) {

        logIncomingRequest(request);

        validateIncomingRequest(request);

        if (isLimitReached()) {
            return ApiResponseBuilderFactory.buildSuccessApiResponse(ResponseMessage.LIMIT_REACHED);
        }

        spawnThreads(request.getProducers(), request.getConsumers());

        return ApiResponseBuilderFactory.buildSuccessApiResponse(ResponseMessage.THREAD_CREATION_SUCCESS);
    }

    @Override
    public ResponseEntity<Response> resetCounter(String value) {
        counter.setCounterValue(Integer.parseInt(value));
        return ApiResponseBuilderFactory.buildCounterResetSuccessResponse(value);
    }

    private void logIncomingRequest(Request request) {
        try {
            String jsonRequest = mapper.writeValueAsString(request);
            RequestResponseModel model = RequestResponseModel.builder()
                    .request(jsonRequest).build();
            requestResponseModelService.insert(model);
        } catch (JsonProcessingException e) {
            log.error(Errors.INTERNAL_SERVER_ERROR);
            throw new RuntimeException(Errors.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateIncomingRequest(Request request) {
        if (Integer.parseInt(request.getProducers()) < 0 || Integer.parseInt(request.getConsumers()) < 0) {
            throw new RequestValidationException(Errors.NEGATIVE_NUMBER_NOT_ALLOWED);
        }
    }

    private boolean isLimitReached() {
        return counter.getCounterValue() == 100 || counter.getCounterValue() == 0;
    }

    private void spawnThreads(String producers, String consumers) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(producers); i++) {
            threads.add(new Thread(new Producer(counter)));
        }
        for (int i = 0; i < Integer.parseInt(consumers); i++) {
            threads.add(new Thread(new Consumer(counter)));
        }
        threads.forEach(Thread::start);
    }
}
