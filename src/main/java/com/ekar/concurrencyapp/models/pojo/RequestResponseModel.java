package com.ekar.concurrencyapp.models.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestResponseModel {
    private String request;

    @Override
    public String toString() {
        return "Request : " + request;
    }
}
