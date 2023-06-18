package com.example.pattern.kiosk.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    // private 생성자를 만들었을 때의 장점 : 객체 생성 불가

    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private으로 적용시켜 외부에서 인스턴스 생성을 막는다
    private SingletonService() {

    }
}
