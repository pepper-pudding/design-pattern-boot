package com.example.pattern.mail.service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
//@Service
//@Primary
public class MailAdapter implements MailSenderA {
    private final MailSolutionB mailSolutionB;

    @Override
    public void send(MailSolutionA.MailParam mailParam) {
        // MailSolutionA의 MailParam을 받아서 MailSolutionB의 mailParam으로 만들어줌
        // 클라이언트 호출 부분을 변경하지 않고도 서비스 제공 가능해짐
        MailSolutionB.MailParam param = MailSolutionB.MailParam.builder().mailTitle(mailParam.getTitle())
                .mailBody(mailParam.getBody()).receiverEmail(mailParam.getEmail()).build();
        mailSolutionB.sendApi(param);
    }
}
