package com.example.pattern.kiosk.order;

import com.example.pattern.kiosk.order.Alarm;
import com.example.pattern.kiosk.order.Delivery;
import com.example.pattern.kiosk.order.Order;
import com.example.pattern.kiosk.order.OrderImpl;
import com.example.pattern.kiosk.order.Payment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class OrderDecoratorTest {
    // 데코레이터 패턴
    @Test
    void 키오스크_주문시작() {
        //주문 시작
        Order order = new OrderImpl();
        order.command();

        log.info("--------------------");

        //주문에 배송방법 및 결제방법 추가
        order = new Payment(new Delivery(new OrderImpl()));
        order.command();

        log.info("--------------------");

        //주문에 배송방법 + 결제방법 + 알람방식 추가
        order = new Payment(new Delivery(new Alarm(new OrderImpl())));
        order.command();

        // 웹 서비스 개발 시 잘 쓰는 방법은 아니다.

        /*
            실행 순서 :
            orderImpl.command()
            -> alarm.alarm()
            -> delivery.delivery()
            -> payment.pay()
         */

    }
}