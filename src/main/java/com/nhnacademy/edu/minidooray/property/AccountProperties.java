package com.nhnacademy.edu.minidooray.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "account.server")
public class AccountProperties {

    private String port;
}
