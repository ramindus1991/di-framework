<h1>di-framework</h1>	
Dependency Injection framework from the scratch

<h2>Getting Started</h2>	

<h3>System Requirements</h3>
    - Java 1.8 or higher

<h3>Add Maven dependency</h3>

 
```maven
<dependencies>
    <!-- other dependencies are there -->
    <dependency>
        <groupId>di-group</groupId>
        <artifactId>di-framework</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
    <!-- other dependencies are there -->
</dependencies>
```

<h3>Configuration</h3>
```java
        Config config = new Config();
        config.addScanPackage("sample.first");
        config.addScanPackage("sample.second");
        config.initConfig();
```

<h3>Define Component</h3>

Bean name defined by class Name. 

example : test1
```java
        @Component
        public class Test1 {
                  
        }
```

Bean name defined by component value. 

example : sampleBeanName

```java
        @Component("sampleBeanName")
        public class Test1 {
          
        }
```

<h3>Define Scope</h3>

Default scope will be singleton.

Prototype scope
```java
        @Scope(ScopeType.PROTOTYPE)
        @Component("sampleBeanName")
        public class Test1 {
          
        }
```

<h3>Autowire</h3>

Autowire By class name
```java
        @Autowired
        Test1 test1;
        
        @Autowired
        ITest test2;
```

Autowire By bean name

```java
        @Autowired
        Test1 beanName;
        
```

Autowire fields setters and constructor

```java
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
```

<h3>Bean</h3>
Define Bean using @Bean annotation with method
```
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
```