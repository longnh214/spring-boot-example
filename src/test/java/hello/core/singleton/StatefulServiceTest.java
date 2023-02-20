package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 만원 주문
        int price = statefulService1.order("userA", 10000);
        //ThreadB : B사용자 2만원 주문
        int price2 = statefulService2.order("userB", 20000);

        //ThreadA : A사용자 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + price);
        //ThreadB : B사용자 주문 금액 조회
//        int price2 = statefulService2.getPrice();
        System.out.println("price2 = " + price2);

        Assertions.assertThat(price).isEqualTo(10000);
        Assertions.assertThat(price2).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}