package com.ekar.concurrencyapp.models;

import com.ekar.concurrencyapp.data.ThreadTimestampModelService;
import com.ekar.concurrencyapp.models.pojo.ThreadTimestampModel;
import com.ekar.concurrencyapp.util.enums.ThreadType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class Counter {
    private final AtomicInteger counter = new AtomicInteger(50);
    private final ThreadTimestampModelService threadTimestampModelService;

    public synchronized void increment() {
        if (isLimitReached()) {
            return;
        }
        int newValue = counter.incrementAndGet();

        if (newValue == 100) {
            logThreadCounterData(newValue, ThreadType.PRODUCER.getType());
        }
        log.info("Producer - {} , Counter : {}", Thread.currentThread().getName(), newValue);
    }

    public synchronized void decrement() {
        if (isLimitReached()) {
            return;
        }
        int newValue = counter.decrementAndGet();

        if (newValue == 0) {
            logThreadCounterData(newValue, ThreadType.CONSUMER.getType());
        }
        log.info("Consumer - {} , Counter : {}", Thread.currentThread().getName(), newValue);
    }

    private boolean isLimitReached() {
        return counter.get() == 100 || counter.get() == 0;
    }

    private void logThreadCounterData(int newValue, String threadType) {
        threadTimestampModelService.insert(ThreadTimestampModel.builder()
                .threadName(threadType + " - " + Thread.currentThread().getName())
                .counterValue(String.valueOf(newValue)).build());
    }

    public int getCounterValue() {
        return counter.get();
    }

    public void setCounterValue(int value) {
        counter.set(value);
    }
}