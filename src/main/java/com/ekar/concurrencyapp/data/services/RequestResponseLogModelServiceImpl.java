package com.ekar.concurrencyapp.data.services;

import com.ekar.concurrencyapp.data.RequestResponseModelService;
import com.ekar.concurrencyapp.data.entities.RequestResponseLog;
import com.ekar.concurrencyapp.data.repositories.RequestResponseLogRepository;
import com.ekar.concurrencyapp.models.pojo.RequestResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestResponseLogModelServiceImpl implements RequestResponseModelService {

    private final RequestResponseLogRepository requestResponseLogRepository;

    @Override
    public void insert(RequestResponseModel model) {
        log.info("saving incoming request : {}", model);
        requestResponseLogRepository.save(mapToEntity(model));
    }

    private RequestResponseLog mapToEntity(RequestResponseModel model) {
        RequestResponseLog log = new RequestResponseLog();
        log.setRequest(model.getRequest());
        log.setRequestTimestamp(new Date());
        return log;
    }
}
