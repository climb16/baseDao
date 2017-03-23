package cn.com.factory;

/**
 * @author xiao
 * @date 2015/8/6.
 */
public interface HierarchicalBeanFactory extends BeanFactory{


    /**
     * 获取上一级beanFactory
     * @return
     */
    BeanFactory getParentBeanFactory();


    /**
     *
     * @param name
     * @return
     */
    boolean containsLocalBean(String name);



}
