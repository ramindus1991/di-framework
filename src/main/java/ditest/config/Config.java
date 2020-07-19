package ditest.config;

import ditest.annotation.Bean;
import ditest.annotation.Component;
import ditest.annotation.Configuration;
import ditest.factory.BeanFactory;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.*;

public class Config {

    private BeanFactory beanFactory;

    private Set<String> scanPackages = new HashSet<>();

    public void addScanPackage(String pkg){
        scanPackages.add(pkg);
    }

    public void removeScanPackage(String pkg){
        scanPackages.remove(pkg);
    }

    public Set<String> getScanPackages() {
        return scanPackages;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void initConfig() throws Exception{
        beanFactory = new BeanFactory();
        initBeanDefinitions();
        initConfigBeans();
    }

    private void initConfigBeans() throws Exception {
        for(String pkg:scanPackages){
            Reflections reflections = new Reflections(pkg);
            Set<Class<? extends Object>> configClasses = reflections.getTypesAnnotatedWith(Configuration.class);
            for(Class<?> clz:configClasses) {
                Object obj = clz.getDeclaredConstructor().newInstance();
                for (Method method : clz.getMethods()) {
                    Bean bean = method.getAnnotation(Bean.class);
                    if(bean!=null) {
                        Object rtn = method.invoke(obj);
                        beanFactory.addBeanDef(bean.value(),method.getReturnType());
                        beanFactory.addBean(bean.value(), rtn);
                    }
                }
            }
        }
    }

    private void initBeanDefinitions() throws Exception {
        for(String pkg:scanPackages){
            Reflections reflections = new Reflections(pkg);
            Set<Class<? extends Object>> componentClasses = reflections.getTypesAnnotatedWith(Component.class);
            for (Class<?> clz:componentClasses){
                String beanName = clz.getAnnotation(Component.class).value();
                if(beanName.length()==0){
                    String clzName = clz.getName().substring(clz.getName().lastIndexOf(".")+1);
                    beanName = clzName.substring(0,1).toLowerCase() + clzName.substring(1);
                }
                beanFactory.addBeanDef(beanName,clz);
            }
        }
    }

}
