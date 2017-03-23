package cn.com.factory;


/**
 * beanFactory顶层工厂接口，定义了beanFactory基本规范
 * @author xiao
 * @date 2015/8/6.
 */
public interface BeanFactory {

	//这里是对FactoryBean的转义定义，因为如果使用bean的名字检索FactoryBean得到的对象是工厂生成的对象，  
    //如果需要得到工厂本身，需要转义         
    String FACTORY_BEAN_PREFIX = "&";

    /**
     * 根据name获取bean对象
     * @param name
     * @return
     */
    Object getBean(String name);


    /**
     * 根据类型获取bean
     * @param requiredType
     * @param <T>
     * @return
     */
    <T> T getBean(Class<T> requiredType);

    /**
     * 根据name和需要的类型获取bean
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     */
    <T> T getBean(String name, Class<T> requiredType);


    /**
     * 根据name和指定参数构造bean对象
     * @param name
     * @param args
     * @return
     */
    Object getBean(String name, Object... args);


    /**
     * 根据类型和参数构造bean对象
     * @param requiredType
     * @param args
     * @param <T>
     * @return
     */
    <T> T getBean(Class<T> requiredType, Object... args);


    /**
     * 判断包含指定name的bean
     * @param name
     * @return
     */
    boolean containsBean(String name);


    /**
     * 判断指定name的bean是否是单例的
     * @param name
     * @return
     */
    boolean isSingleton(String name);



    boolean isPrototype(String name);


    /**
     * 判断指定name的bean是否是指定类型
     * @param name
     * @param typeToMatch
     * @return
     */
    //boolean isTypeMatch(String name, ResolvableType typeToMatch);


    /**
     * 判断指定name的bean是否是指定类型
     * @param name
     * @param typeToMatch
     * @return
     */
    boolean isTypeMatch(String name, Class<?> typeToMatch);


    /**
     * 获取指定name的bean类型
     * @param name
     * @return
     */
    Class<?> getType(String name);


    /**
     * 获取指定name的bean的别名
     * @param name
     * @return
     */
    String[] getAliases(String name);
}




