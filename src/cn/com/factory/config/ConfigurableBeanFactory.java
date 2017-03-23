package cn.com.factory.config;

import cn.com.factory.BeanFactory;
import cn.com.factory.HierarchicalBeanFactory;

import java.beans.PropertyEditor;
import java.security.AccessControlContext;

/**
 * @author xiao
 * @date 2015/8/6.
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory {



    String SCOPE_SINGLETON = "singleton"; //单例


    String SCOPE_PROTOTYPE = "prototype";


    /**
     * 设置父beanFactory
     * @param parentBeanFactory
     */
    void setParentBeanFactory(BeanFactory parentBeanFactory);


    /**
     * 设置bean的类加载器
     * @param beanClassLoader
     */
    void setBeanClassLoader(ClassLoader beanClassLoader);


    /**
     * 获取bean类加载器
     * @return
     */
    ClassLoader getBeanClassLoader();


    /**
     * 设置临时类加载器
     * @param tempClassLoader
     */
    void setTempClassLoader(ClassLoader tempClassLoader);


    /**
     * 获取临时类加载器
     * @return
     */
    ClassLoader getTempClassLoader();


    /**
     * 设置是否开启缓存
     * @param cacheBeanMetadata
     */
    void setCacheBeanMetadata(boolean cacheBeanMetadata);


    /**
     * 判断是否开启缓存
     * @return
     */
    boolean isCacheBeanMetadata();

    /**
     * 设置bean解析者
     * @param resolver
     */
    void setBeanExpressionResolver();


    /**
     * 获取bean解析者
     * @return
     */
    //BeanExpressionResolver getBeanExpressionResolver();



    //void setConversionService(ConversionService conversionService);


   //ConversionService getConversionService();


    //void addPropertyEditorRegistrar(PropertyEditorRegistrar registrar);


    //void registerCustomEditor(Class<?> requiredType, Class<? extends PropertyEditor> propertyEditorClass);


    //void copyRegisteredEditorsTo(PropertyEditorRegistry registry);


    //void setTypeConverter(TypeConverter typeConverter);


    //TypeConverter getTypeConverter();


   // void addEmbeddedValueResolver(StringValueResolver valueResolver);


   // String resolveEmbeddedValue(String value);


    //void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);


    //int getBeanPostProcessorCount();


    //void registerScope(String scopeName, Scope scope);


    //String[] getRegisteredScopeNames();


    //Scope getRegisteredScope(String scopeName);


    //AccessControlContext getAccessControlContext();


   // void copyConfigurationFrom(ConfigurableBeanFactory otherFactory);


    //void registerAlias(String beanName, String alias);


    //void resolveAliases(StringValueResolver valueResolver);


   // BeanDefinition getMergedBeanDefinition(String beanName);


    //boolean isFactoryBean(String name);


   // void setCurrentlyInCreation(String beanName, boolean inCreation);


   /* boolean isCurrentlyInCreation(String beanName);


    void registerDependentBean(String beanName, String dependentBeanName);


    String[] getDependentBeans(String beanName);


    String[] getDependenciesForBean(String beanName);


    void destroyBean(String beanName, Object beanInstance);


    void destroyScopedBean(String beanName);


    void destroySingletons();
*/
}
