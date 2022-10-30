package com.ekar.concurrencyapp.util.enums;

public enum ThreadType {
    PRODUCER("Producer"),
    CONSUMER("Consumer");

    private String type;

    ThreadType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
