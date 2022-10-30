package com.ekar.concurrencyapp.data.services;

import com.ekar.concurrencyapp.data.ThreadTimestampModelService;
import com.ekar.concurrencyapp.data.entities.RequestResponseLog;
import com.ekar.concurrencyapp.data.entities.ThreadTimestamp;
import com.ekar.concurrencyapp.data.repositories.ThreadTimestampRepository;
import com.ekar.concurrencyapp.models.pojo.RequestResponseModel;
import com.ekar.concurrencyapp.models.pojo.ThreadTimestampModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class ThreadTimestampModelServiceImpl implements ThreadTimestampModelService {

    private final ThreadTimestampRepository threadTimestampRepository;

    @Override
    public void insert(ThreadTimestampModel model) {
        threadTimestampRepository.save(mapToEntity(model));
    }

    private ThreadTimestamp mapToEntity(ThreadTimestampModel model) {
        ThreadTimestamp log = new ThreadTimestamp();
        log.setThreadName(model.getThreadName());
        log.setCounterValue(model.getCounterValue());
        log.setRequestTimestamp(new Date());
        return log;
    }
}
