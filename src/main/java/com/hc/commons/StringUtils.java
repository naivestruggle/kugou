package com.hc.commons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:
 * @Date:2019/5/4
 * @Description:com.hc.commons
 * @Version:1.0
 */
public class StringUtils {
    private StringUtils(){}
    /**
     * 定义正则
     */
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 登录用户
     */
    public static final String LOGINED_USER = "loginedUser";

    /**
     * 用户歌单前缀
     */
    public static final String PLAT_SONG_LIST_PRE = "playSongListPre";

    /**
     * 定义条件
     */
    public static final int GET_METHOD = 1;
    public static final int SET_METHOD = 2;

    /**
     * 下划线转驼峰
     * @param str   需要转换的字符串
     * @return  转换完成的字符串
     */
    public static String lineToHump(String str){
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     * @param str   需要转换的字符串
     * @return  转换完成的字符串
     */
    public static String humpToLine(String str){
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 得到传入字符串的get set方法名
     * @param str   传入字段
     * @param type  类型  get  set
     * @return  方法名
     */
    public static String toGetterSetter(String str,int type){
        if(type == GET_METHOD){
            return "get"+getGetterSetterString(str);
        }else if (type == SET_METHOD){
            return "set"+getGetterSetterString(str);
        }else{
            return null;
        }
    }

    /**
     * 得到传入字段的get方法名
     * @param str   传入字段
     * @return  get方法名
     */
    public static String toGetter(String str){
        return toGetterSetter(str,GET_METHOD);
    }

    /**
     * 得到传入字段的set方法名
     * @param str      传入字段
     * @return  set方法名
     */
    public static String toSetter(String str){
        return toGetterSetter(str,SET_METHOD);
    }

    /**
     * 将字符串变成可以转换为get set 的
     * @param str   原字符串
     * @return 新字符串
     */
    private static String getGetterSetterString(String str) {
        //取第二个字符
        String s2 = str.substring(1,2);
        if(s2.matches("[A-Z]")){
            //如果第二个字符是大写  就不用变化  直接在前面添加字符串
            return str;
        }else{
            //如果第二个符不是大写  就将第一个字符转换为大写
            String s1 = str.substring(0,1).toUpperCase();
            return s1 + str.substring(1);
        }
    }

}
