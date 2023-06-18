package com.example.pattern.review.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TextReview extends Review {
    // 템플릿 메서드 패턴
    @Override
    public void putContent() {
        log.info("한줄 리뷰를 작성 하였습니다.");
    }
}
