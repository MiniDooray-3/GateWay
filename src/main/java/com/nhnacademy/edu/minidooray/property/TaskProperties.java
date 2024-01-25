package com.nhnacademy.edu.minidooray.property;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "task.server")
@Getter
public class TaskProperties {
    private String port;
}
