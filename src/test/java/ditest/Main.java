package ditest;

import ditest.beantest.TestBean;
import ditest.components.ITest;
import ditest.components.Test1;
import ditest.config.Config;
import ditest.components.pak1.Test2;
import ditest.components.pak1.pak2.Test3;

public class Main {
    public static void main(String[] args) throws Exception{
        Config config = new Config();
        config.addScanPackage("ditest");
        config.initConfig();
        Test3 t = (Test3)config.getBeanFactory().getBean("EX");
        Test3 t2 = (Test3)config.getBeanFactory().getBean("EX");

        Test1 t3 = (Test1)config.getBeanFactory().getBean("test1");
        Test1 t4 = (Test1)config.getBeanFactory().getBean("test1");
        ITest t5 = (Test2)config.getBeanFactory().getBean("test2");
        ITest t6 = (Test2)config.getBeanFactory().getBean("test2");

        Test1 beanAnnotationTest1 = (Test1)config.getBeanFactory().getBean("beanAnnotationTest1");
        ITest beanAnnotationTest2 = (Test2)config.getBeanFactory().getBean("beanAnnotationTest2");
        TestBean beanAnnotationTest3 = (TestBean)config.getBeanFactory().getBean("beanAnnotationTest3");

        System.out.println();
    }

}
