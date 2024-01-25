package com.nhnacademy.edu.minidooray.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "task.server")
public class TaskProperties {
    private String port;
}
