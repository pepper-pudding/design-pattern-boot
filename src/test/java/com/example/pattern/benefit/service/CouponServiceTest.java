package com.example.pattern.benefit.service;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CouponServiceTest {
    // 커멘더 패턴
    @Test
    void 혜택_발송_스레드_테스트() throws InterruptedException {
        int numberOfThreads = 5;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        //ExecutorService 객체가 Invoker를 의미
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            //Runnable 구현 객체가 Command를 의미
            //CouponApiService 객체가 Receiver를 의미
            Runnable doThread = new CouponService(new CouponApiService());
            executorService.execute(() -> {
                doThread.run();
                latch.countDown();
            });
            // 만약에 위 방법을 사용하지 않고 람다를 사용한다면?
            executorService.execute(() -> {

            });
        }
        latch.await();
    }
}