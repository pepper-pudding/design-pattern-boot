package com.example.pattern.order.service;

import com.example.pattern.delivery.service.DeliveryService;
import com.example.pattern.mail.service.MailSenderA;
import com.example.pattern.mail.service.MailSolutionA;
import com.example.pattern.order.entity.Order;
import com.example.pattern.payment.service.PaymentService;
import com.example.pattern.user.entity.Member;
import com.example.pattern.user.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private MailSenderA mailSenderA;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PaymentService paymentService;

    public void order(Order order) {
        // 브릿지 패턴
        Optional<Member> findMember = memberRepository.findById(order.getMemberId());
        Member member = findMember.orElse(new Member(1L, "비회원주문", "test@email.com", null, "010-123-4567", "123-456-1234567"));

        // 결제
        paymentService.pay(order.getAmount(), member);
        if (StringUtils.isNotEmpty(member.getAccountNumber())) {
            paymentService.setPaymentMethod(new CardMethodService());
        } else if (StringUtils.isNotEmpty(member.getPhoneNumber())) {
            paymentService.setPaymentMethod(new PhoneMethodService());
        }
        paymentService.pay1(order.getAmount(), member);

        // 배송
        deliveryService.deliver(order);

        // 알림
        MailSolutionA.MailParam mailParam = MailSolutionA.MailParam.builder().title("배송이 시작되었습니다.")
            .body("mail body").email("test@nhn.com").build();
        mailSenderA.send(mailParam);
    }
}
