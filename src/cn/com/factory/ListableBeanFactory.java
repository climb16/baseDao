package cn.com.factory;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author xiao
 * @date 2015/8/6.
 */
public interface ListableBeanFactory extends BeanFactory {


    boolean containsBeanDefinition(String beanName);


    int getBeanDefinitionCount();


    String[] getBeanDefinitionNames();


    //String[] getBeanNamesForType(ResolvableType type);



    String[] getBeanNamesForType(Class<?> type);


    String[] getBeanNamesForType(Class<?> type, boolean includeNonSingletons, boolean allowEagerInit);


    <T> Map<String, T> getBeansOfType(Class<T> type);


    <T> Map<String, T> getBeansOfType(Class<T> type, boolean includeNonSingletons, boolean allowEagerInit);

    String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType);


    Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType);


    <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType);


}
