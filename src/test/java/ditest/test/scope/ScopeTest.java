package ditest.test.scope;

import ditest.annotation.Configuration;
import ditest.components.ITest;
import ditest.beantest.PrototypeTestBean;
import ditest.config.Config;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Configuration
public class ScopeTest {

    private Config config;

    @Before
    public void setUp() throws Exception {
        config = new Config();
        config.addScanPackage("ditest");
        config.initConfig();
    }

    @Test
    public void testPrototypeScope() throws Exception {
        PrototypeTestBean testFirst = (PrototypeTestBean) config.getBeanFactory().getBean("prototypeTestBean");
        PrototypeTestBean testSecond = (PrototypeTestBean) config.getBeanFactory().getBean("prototypeTestBean");
        Assert.assertNotEquals(testFirst,testSecond);
    }

    @Test
    public void testSingletonScope() throws Exception {
        ITest testFirst = (ITest) config.getBeanFactory().getBean("test2");
        ITest testSecond = (ITest) config.getBeanFactory().getBean("test2");
        Assert.assertEquals(testFirst,testSecond);
    }

}
