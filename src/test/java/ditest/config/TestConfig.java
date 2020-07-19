package ditest.config;

import ditest.components.Test1;
import ditest.beantest.TestBean;
import ditest.annotation.Bean;
import ditest.annotation.Configuration;
import ditest.components.pak1.Test2;

@Configuration
public class TestConfig {

    @Bean("beanAnnotationTest1")
    public Test1 getTest1(){
        return new Test1();
    }

    @Bean("beanAnnotationTest2")
    public Test2 getTest2(){
        return new Test2();
    }

    @Bean("beanAnnotationTest3")
    public TestBean getTestBean(){
        return new TestBean();
    }
}
