package com.sunyard.dip.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取spring容器中的bean的工具类
 *
 * Created by Administrator on 2018/6/11/0011.
 */
public class SpringBeanTool implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringBeanTool.applicationContext = applicationContext;
    }

    /**
     * 根据名称获得bean
     *
     * @param name  bean名称
     * @return
     * */
     public static Object getBean(String name) {
         return applicationContext.getBean(name);
     }

     public static <T> T getBean(String name, Class<T> clazz) {
         return (T) applicationContext.getBean(name, clazz);
     }

     public static boolean containsBean(String name) {
         return applicationContext.containsBean(name);
     }
}
