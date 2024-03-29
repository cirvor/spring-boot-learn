package com.cirvor.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import tk.mybatis.spring.annotation.MapperScan;


@EnableAsync
@EnableScheduling
@MapperScan(basePackages = "com.cirvor.learn.mapper")
@EnableCaching
@SpringBootApplication
public class LearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnApplication.class, args);
    }

    /**
     * 修改调度任务线程池数量
     *
     * @return 线程池
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }

}
