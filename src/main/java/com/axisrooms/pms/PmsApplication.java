package com.axisrooms.pms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@ComponentScan("com.axisrooms.pms")
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.axisrooms.pms.repo"})
@EnableAsync
@SpringBootApplication
public class PmsApplication implements AsyncConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(PmsApplication.class, args);
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("pms-pool-");
        executor.initialize();
        return executor;
    }

}
