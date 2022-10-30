package com.ekar.concurrencyapp.models;

import com.ekar.concurrencyapp.config.EkarApiConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RequiredArgsConstructor
public class Producer implements Runnable {
    private final Counter counter;

    @Override
    public void run() {
        log.info("Producer - {} has been created", Thread.currentThread().getName());
        while (true) {
            if (isLimitReached()) {
                return;
            }
            counter.increment();
            suspendThread();
        }
    }

    private void suspendThread() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.info("Producer : {} has been interrupted", Thread.currentThread().getName());
            throw new RuntimeException(e);
        }
    }

    private boolean isLimitReached() {
        return counter.getCounterValue() == 100 || counter.getCounterValue() == 0;
    }
}