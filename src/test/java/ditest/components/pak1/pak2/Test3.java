package ditest.components.pak1.pak2;

import ditest.annotation.Autowired;
import ditest.annotation.Component;
import ditest.annotation.Inject;
import ditest.beantest.TestBean;
import ditest.components.ITest;
import ditest.components.Test1;

@Component("t3")
public class Test3 {

    @Autowired
    Test1 autowiredField;

    ITest autowiredConstructorField;

    TestBean autowiredSetterField;

    @Autowired
    public Test3(@Inject("test2") ITest autowiredConstructorField){
        this.autowiredConstructorField = autowiredConstructorField;
    }

    public Test1 getAutowiredField() {
        return autowiredField;
    }

    public void setAutowiredField(Test1 autowiredField) {
        this.autowiredField = autowiredField;
    }

    public ITest getAutowiredConstructorField() {
        return autowiredConstructorField;
    }

    public void setAutowiredConstructorField(ITest autowiredConstructorField) {
        this.autowiredConstructorField = autowiredConstructorField;
    }

    public TestBean getAutowiredSetterField() {
        return autowiredSetterField;
    }

    @Autowired
    public void setAutowiredSetterField(TestBean autowiredSetterField) {
        this.autowiredSetterField = autowiredSetterField;
    }
}
