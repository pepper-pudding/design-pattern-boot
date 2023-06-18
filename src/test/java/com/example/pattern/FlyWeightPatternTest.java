package com.example.pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FlyWeightPatternTest {
    // 플라이웨이트 패턴 : 인스턴스가 필요할 때마다 매번 생성하는 것이 아니고 가능한 한 공유해서 사용함으로써 메모리를 절약하는 패턴
    @Test
    void 스트링_플라이웨이트패턴_테스트() {

        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");

        // 서로 같다!
        Assertions.assertSame(str1, str2);

        // 서로 다르다!
        Assertions.assertNotSame(str1, str3);

        // 서로 같다!
        Assertions.assertSame(str1, str3.intern());

        /*
            클래스가 JVM에 로드되면 모든 리터럴은 constant pool에 위치하게 된다.
            그리고 리터럴을 통해 같은 문자를 생성한다면 풀 안의 같은 상수를 참조하게 되는데 이를 String interning이라고 한다.
            String을 리터럴로 생성될 때 intern()이라는 메서드가 호출되고 이 intern() 메서드는
            constant pool에 같은 문자가 존재하는지 확인 후 존재한다면 그 참조 값을 가지게 된다.
         */
    }

    @Test
    void Integer_플라이웨이트패턴_테스트() {
        // 플라이웨이트 패턴
        Integer integer1 = Integer.valueOf("123");
        Integer integer2 = Integer.valueOf("123");

        // 서로 같다!
        Assertions.assertSame(integer1, integer2);

        Integer integer3 = Integer.valueOf("128");
        Integer integer4 = Integer.valueOf("128");

        // 서로 다르다!
        Assertions.assertNotSame(integer3, integer4);

        // 직접 Integer.valueOf 함수 확인해보기!!!!
        // Integer.valueOf의 내부 구현을 살펴보면, 파라미터 값이 126보다 작으면 공용풀을 사용하지만 크면 아예 다른 인스턴스를 사용하도록 되어 있음
    }
}
