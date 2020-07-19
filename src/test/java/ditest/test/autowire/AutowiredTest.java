package ditest.test.autowire;

import ditest.annotation.Bean;
import ditest.annotation.Configuration;
import ditest.beantest.TestBean;
import ditest.components.pak1.pak2.Test3;
import ditest.config.Config;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Configuration
public class AutowiredTest {

    private Config config;

    @Bean("autowiredSetterField")
    public TestBean getAutowiredSetterField() {
        return new TestBean();
    }

    @Before
    public void setUp() throws Exception {
        config = new Config();
        config.addScanPackage("ditest");
        config.initConfig();
    }

    @Test
    public void testAutowiredField() throws Exception {
        Test3 t = (Test3)config.getBeanFactory().getBean("t3");
        Assert.assertNotNull(t.getAutowiredField());
    }

    @Test
    public void testAutowiredSetterMethod() throws Exception {
        Test3 t = (Test3)config.getBeanFactory().getBean("t3");
        Assert.assertNotNull(t.getAutowiredSetterField());
    }

    @Test
    public void testAutowiredConstructorInject() throws Exception {
        Test3 t = (Test3)config.getBeanFactory().getBean("t3");
        Assert.assertNotNull(t.getAutowiredConstructorField());
    }
}
