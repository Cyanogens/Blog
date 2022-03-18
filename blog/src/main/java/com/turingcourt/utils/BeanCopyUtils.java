package com.turingcourt.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Cyanogen
 * @description Bean复制实用程序
 * @date 2022/3/18 23:58
 */
public class BeanCopyUtils {

    private BeanCopyUtils() {
        // 防止实例化工具类
        throw new AssertionError("No " + BeanCopyUtils.class.getName() + " instances for you !");
    }

    /**
     * 描述: 封装BeanUtils.copyProperties, 实现类拷贝
     *
     * @param source      源对象
     * @param targetClass 目标对象Class
     * @param <T>         目标对象
     * @return 所需类型的bean
     */
    public static <T> T copyBean(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }

        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        throw new BeanCopyException(source, targetClass);
    }

    private static class BeanCopyException extends RuntimeException {

        public BeanCopyException(Object source, Class<?> targetClass) {
            super("Bean Copy Error: source=>" + source.toString() + ", targetClass=>" + targetClass.toString());
        }
    }
}
