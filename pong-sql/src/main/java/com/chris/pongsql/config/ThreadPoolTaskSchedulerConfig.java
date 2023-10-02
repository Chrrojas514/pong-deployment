package com.chris.pongsql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@ComponentScan(
        basePackages = "com.chris.pongsql.config.taskscheduler",
        basePackageClasses = {ThreadPoolTaskScheduler.class}
)
public class ThreadPoolTaskSchedulerConfig {

    @Bean
    public org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler threadPoolTaskScheduler =
                new org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler();

        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix(
                "ThreadPoolTaskSchedulerConfig"
        );

        return threadPoolTaskScheduler;
    }
}
