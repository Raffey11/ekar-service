package com.ekar.concurrencyapp.config;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@ConfigurationProperties(prefix = "ekar.api")
@Validated
@RefreshScope
public class EkarApiConfig {
    @NotNull
    private Integer counterValue;
    @NotNull
    private Integer sleepInterval;
}
