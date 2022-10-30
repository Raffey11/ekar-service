package com.ekar.concurrencyapp.models.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse extends Response {
    private String errors;
}