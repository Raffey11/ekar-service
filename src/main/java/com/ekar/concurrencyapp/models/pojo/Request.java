package com.ekar.concurrencyapp.models.pojo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    private String producers;
    private String consumers;

    @Override
    public String toString() {
        return "{ \"producers\": " + producers + ", \"consumers\": " + consumers + " }";
    }
}
