package com.cirvor.learn.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class DemoTask {
//    /**
//     * 异步定时执行
//     *
//     * @throws InterruptedException
//     */
//    @Async
//    @Scheduled(cron = "0/5 * * * * *")
//    public void scheduled1() throws InterruptedException {
//        System.out.println(new Date());
//        Thread.sleep(3000);
//    }

//    /**
//     * 上一次开始执行时间点之后5秒再执行
//     * @throws InterruptedException
//     */
//    @Scheduled(fixedRate = 5000)
//    public void scheduled2() throws InterruptedException {
//        System.out.println(LocalDateTime.now());
//        Thread.sleep(3000);
//    }

//    /**
//     * 上一次执行完毕时间点之后5秒再执行
//     * @throws InterruptedException
//     */
//    @Scheduled(fixedDelay = 5000)
//    public void scheduled3() throws InterruptedException {
//        System.out.println(LocalDateTime.now());
//        Thread.sleep(3000);
//    }

//    /**
//     * 第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
//     * @throws InterruptedException
//     */
//    @Scheduled(initialDelay=1000, fixedRate=5000)
//    public void scheduled4() throws InterruptedException {
//        System.out.println(LocalDateTime.now());
//        Thread.sleep(3000);
//    }
}
