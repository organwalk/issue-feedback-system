package com.issue.issuefeedbacksystem.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties("allow-api-list")
public class AllowApiConfig {
    private List<String> student;
    private List<String> teacher;
    private List<String> deptLeader;
    private List<String> schoolLeader;
    private List<String> admin;
}
