package com.hc.commons;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author:
 * @Date:2019/4/6
 * @Description:com.hc.commons
 * @Version:1.0
 */
public class ReflectUtils {
    private ReflectUtils(){}
    /**
     * 得到目标类的目标方法(包括父类中的)   没有获取到就抛异常
     * @param targetClass 目标类
     * @param targetMethodName    目标方法名
     * @param paramsClass   参数类型集合
     * @return  方法对象
     * @throws NoSuchMethodException    抛出异常
     */
    public static Method getMethod(Class targetClass, String targetMethodName, Class...paramsClass) throws NoSuchMethodException {
        //先到本类找  没有就去父类找
        try {
            return targetClass.getDeclaredMethod(targetMethodName,paramsClass);
        } catch (NoSuchMethodException e) {
            Class classes = targetClass.getSuperclass();
            //找到最后没找到  就抛异常
            if(classes == null){
                throw new NoSuchMethodException("没有"+targetMethodName+"()方法对象");
            }
            return getMethod(classes,targetMethodName,paramsClass);
        }
    }

    /**
     * 得到目标类中的目标属性对象(包括父类的)
     * @param targetClass
     * @param targetFieldName
     * @return
     */
    public static Field getField(Class targetClass, String targetFieldName) throws NoSuchFieldException {
        //先到本类找  没有就去父类找
        try {
            return targetClass.getDeclaredField(targetFieldName);
        } catch (NoSuchFieldException e) {
            Class classes = targetClass.getSuperclass();
            //找到最后没找到  就抛异常
            if(classes == null){
                throw new NoSuchFieldException("没有"+targetFieldName+"()方法对象");
            }
            return getField(classes,targetFieldName);
        }
    }
}
