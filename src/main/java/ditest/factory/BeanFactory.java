package ditest.factory;

import ditest.annotation.Autowired;
import ditest.annotation.Inject;
import ditest.annotation.Scope;
import ditest.types.ScopeType;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import static ditest.constant.Constant.SET;

public class BeanFactory {

    private Map<String,Class<?>> beanDefs = new HashMap<>();
    private Map<String,Object> beanMap = new HashMap<>();

    public Object getBean (String beanName) throws Exception {
        boolean isSingleton = isSingleton(beanName);
        if(beanMap.containsKey(beanName)){
            return beanMap.get(beanName);
        }
        Class clz = beanDefs.get(beanName);
        Object obj;
        obj = initConstructor(clz);

        initFields(clz, obj);

        initMethods(clz, obj);

        if(isSingleton){
            beanMap.put(beanName,obj);
        }
        return obj;
    }

    private void initMethods(Class clz, Object obj) throws Exception {
        for(Method method:clz.getMethods()){
            if(method.isAnnotationPresent(Autowired.class) && method.getName().startsWith(SET)){
                String methodName = method.getName().replaceFirst(SET,"");
                Object value = getBean(methodName.substring(0,1).toLowerCase()+methodName.substring(1));
                method.invoke(obj,value);
            }
        }
    }

    private void initFields(Class clz, Object obj) throws Exception {
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Object value = getBean(field.getName());
                String setterName = field.getName();
                setterName = SET + setterName.substring(0, 1).toUpperCase() + setterName.substring(1);
                Method[] methods = clz.getDeclaredMethods();
                boolean methodFound = false;
                for(Method method:methods){
                    if(setterName.equals(method.getName())){
                        method.invoke(obj,value);
                        methodFound = true;
                        break;
                    }
                }
                if (!methodFound){
                    throw new Exception();
                }
            }
        }
    }

    private Object initConstructor(Class clz) throws Exception {
        Object obj;
        Constructor[] constructors = clz.getConstructors();
        if(constructors.length>1){
            throw new Exception();
        }
        Constructor constructor = constructors[0];
        if(constructor.getParameters().length>0 && constructor.isAnnotationPresent(Autowired.class) ) {
            Object[] objArgs = new Object[constructor.getParameters().length];
            for (int i=0; i<constructor.getParameters().length; i++) {
                Parameter parameter = constructor.getParameters()[i];
                if (parameter.isAnnotationPresent(Inject.class)) {
                    Object value = getBean(parameter.getAnnotation(Inject.class).value());
                    objArgs[i] = value;
                }
            }
            obj = constructor.newInstance(objArgs);
        }else{
            obj = constructor.newInstance();
        }
        return obj;
    }

    private boolean isSingleton(String beanName) {
        boolean isSingleton = true;
        if(beanDefs.containsKey(beanName)) {
            Scope annotation = beanDefs.get(beanName).getAnnotation(Scope.class);
            if (annotation != null && annotation.value().equals(ScopeType.PROTOTYPE)) {
                isSingleton = false;
            }
        }
        return isSingleton;
    }

    public void addBean(String beanName, Object obj) throws Exception{
        if(!beanMap.keySet().contains(beanName)){
            beanMap.put(beanName,obj);
        }else {
            throw new Exception();
        }
    }

    public void addBeanDef(String beanName, Class<?> clz) throws Exception{
        if(!beanDefs.keySet().contains(beanName)){
            beanDefs.put(beanName,clz);
        }else {
            throw new Exception();
        }
    }

}
