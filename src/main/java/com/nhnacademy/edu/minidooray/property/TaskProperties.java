package com.nhnacademy.edu.minidooray.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "task.server")
@Getter
@Setter
public class TaskProperties {
    private String port;
}
