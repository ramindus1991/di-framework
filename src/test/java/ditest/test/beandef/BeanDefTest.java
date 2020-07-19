package ditest.test.beandef;

import ditest.annotation.Bean;
import ditest.annotation.Configuration;
import ditest.beantest.TestBean;
import ditest.components.ITest;
import ditest.components.pak1.Test2;
import ditest.components.pak1.pak2.Test3;
import ditest.config.Config;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Configuration
public class BeanDefTest {

    private Config config;

    @Bean("testBeanDef")
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
    public void testBeanAnnotation() throws Exception {
        TestBean testBean = (TestBean) config.getBeanFactory().getBean("testBeanDef");
        Assert.assertNotNull(testBean);
    }

    @Test
    public void testBeanByClassName() throws Exception {
        ITest t = (ITest) config.getBeanFactory().getBean("test2");
        Assert.assertNotNull(t);
        Assert.assertTrue(t instanceof Test2);
    }

    @Test
    public void testBeanByComponentNameValue() throws Exception {
        Test3 t = (Test3)config.getBeanFactory().getBean("t3");
        Assert.assertNotNull(t);
    }
}
