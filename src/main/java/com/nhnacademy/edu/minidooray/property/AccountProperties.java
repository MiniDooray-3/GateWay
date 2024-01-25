package com.nhnacademy.edu.minidooray.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "account.server")
@Getter
@Setter
public class AccountProperties {
    private String port;
}
