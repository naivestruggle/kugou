package com.hc.commons;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Date:2019/4/16
 * @Description:com.hc.commons
 * @Version:1.0
 */
public class XMLUtils {
    private XMLUtils(){}
    /**
     * 根据省id获取城市的Map
     *
     * @param key
     * @return
     */
    public static Map<String,String> xmlCities(String key, Class c) {
        Map<String,String> map = new LinkedHashMap<String,String>();
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(c.getClassLoader().getResourceAsStream("xmlConfig/Cities.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        Iterator it = root.elementIterator();
        int i = 1;
        while (it.hasNext()) {
            org.dom4j.Element book = (org.dom4j.Element) it.next();
            List<Attribute> arrlist = book.attributes();
            for (Attribute attr : arrlist){
                if (attr.getName().equals("PID")){
                    if (attr.getValue().equals(key)){
                        map.put(i+"",book.getStringValue());
                    }
                }
            }
            i++;
        }
        return map;
    }


    /**
     * 根据城市的id获取县
     * @param key
     * @param c
     * @return
     */
    public static Map<String,String> xmlDistricts(String key,Class c) {
        Map<String,String> map = new LinkedHashMap<String,String>();
        SAXReader saxReader = new SAXReader();
        org.dom4j.Document document = null;
        try {
            document = saxReader.read(c.getClassLoader().getResourceAsStream("xmlConfig/Districts.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        org.dom4j.Element root = document.getRootElement();
        Iterator it = root.elementIterator();
        int i = 1;
        while (it.hasNext()) {
            org.dom4j.Element book = (org.dom4j.Element) it.next();
            List<Attribute> arrlist = book.attributes();
            for (Attribute attr : arrlist){
                if (attr.getName().equals("CID") && attr.getValue().equals(key)){
                    map.put(i + "", book.getStringValue());
                }
            }
            i++;
        }
        return map;
    }

    /**
     * 获得所有的省
     * @return
     */
    public static Map<String, String> xmlProvince(Class c) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        // 创建SaxReader对象
        SAXReader saxReader = new SAXReader();
        // 通过saxReader中的read()获取到dom4j 文档对象 Document
        org.dom4j.Document document = null;
        try {
            document = saxReader.read(c.getClassLoader().getResourceAsStream("xmlConfig/Provinces.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 获取根元素
        org.dom4j.Element root = document.getRootElement();
        Iterator it = root.elementIterator();
        int i = 1;
        while (it.hasNext()) {
            org.dom4j.Element book = (org.dom4j.Element) it.next();
            map.put(i + "", book.getStringValue());
            i++;
        }
        return map;
    }
}
