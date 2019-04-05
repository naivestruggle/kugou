package com.hc.kugou.solr;

import com.hc.commons.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:
 * @Date:2019/5/4
 * @Description:com.hc.kugou.component
 * @Version:1.0
 */
public class SolrManager<T>{
    private SolrClient client;
    public static final int SORT_RULE_DESC = 1;
    public static final int SORT_RULE_ASC = 2;

    private Class<T> clazz;

    private SolrManager(){}

    private SolrManager(Class<T> clazz,SolrClient client){
        this.clazz = clazz;
        this.client = client;
    }

    public static SolrManager getInstance(Class clazz,SolrClient client){
        return new SolrManager(clazz,client);
    }

    /**
     * 更新   先删除 再 添加
     * @param id    要更新的id
     * @param object    要更新的对象
     */
    public void update(String id,T object){
        delete(id);
        add(object);
    }

    /**
     * 批量更新
     * @param map
     */
    public void update(java.util.Map<String,T> map){
        for(java.util.Map.Entry<String,T> me : map.entrySet()){
            delete(me.getKey());
            add(me.getValue());
        }
    }

    /**
     * 根据ID数组批量删除
     * @param ids   id数组
     */
    public void delete(String... ids){
        delete(Arrays.asList(ids));
    }

    /**
     * 根据ID集合批量删除
     * @param ids    id集合
     */
    public void delete(List<String> ids){
        try {
            client.deleteById(ids);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //提交
        commit();
    }

    /**
     * 根据ID删除
     * @param id    ID
     */
    public void delete(String id){
        try {
            client.deleteById(id);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //提交
        commit();
    }

    /**
     * 添加一个对象
     * @param object    对象
     */
    public void add(T object){
        //添加一个对象但是未提交
        addOneNotCommit(object);

        //提交
        commit();
    }


    /**
     * 批量添加
     * @param objects   集合
     */
    public void add(List<T> objects){
        for(T object : objects){
            addOneNotCommit(object);
        }
        //提交
        commit();
    }

    /**
     * 批量添加
     * @param objects   数组
     */
    public void add(T... objects){
        for (T object:objects){
            //添加一个对象但是未提交
            addOneNotCommit(object);
        }
        //提交
        commit();
    }

    /**
     * 添加一个对象  但是未提交
     * @param object    对象
     */
    private void addOneNotCommit(T object) {
        //得到所有的字段  包括私有的
        Field[] fields = clazz.getDeclaredFields();
        SolrInputDocument doc = new SolrInputDocument();
        for (Field field:fields){
            String fieldName = field.getName();
            //得到数据库字段   也是域名
            String sqlString = StringUtils.humpToLine(fieldName);

            //得到get方法名
            String methodName = StringUtils.toGetter(fieldName);

            //得到get方法对象
            Method method = null;
            try {
                 method = clazz.getMethod(methodName);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            //将所有字段和值添加到document中
            try {
                doc.setField("id",UUID.randomUUID().toString().replace("-",""));
                doc.setField(sqlString,method.invoke(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        //将整个doc添加到solr连接对象中
        try {
            client.add(doc);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 提交
     */
    private void commit() {
        try {
            client.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询
     * @param queryStr  关键词
     * @param filterQueries 过滤
     * @param sortFieldName 排序域
     * @param sortRule  排序规则
     * @param start 从第几条开始查
     * @param rows  查多少条
     * @param defaultFields  默认域
     * @param pointFields   指定域
     * @param highlightField    高亮域
     * @return  查询结果
     */
    public SolrBean<T> find(String queryStr,String[] filterQueries,String sortFieldName,Integer sortRule,int start,int rows,
                             String[] defaultFields,String[] pointFields,String highlightField){
        return find(queryStr,filterQueries,sortFieldName,sortRule,start,rows,defaultFields,pointFields,highlightField,"<font style='color:red;'>","</font>");
    }

    /**
     * 根据详细条件查询
     * @param queryStr  关键字
     * @param filterQueries 过滤  可以传多组过滤
     * @param sortFieldName 排序  根据指定域排序
     * @param sortRule  排序规则  升 降
     * @param start 从第几个记录开始查
     * @param rows  查多少条
     * @param defaultFields  默认域
     * @param pointFields   指定域
     * @param highlightField    高亮的域
     * @param highlightSimplePre    高亮前缀
     * @param highlightSimpleLast   高亮后缀
     * @return  查询结果集
     */
    private SolrBean<T> find(String queryStr,String[] filterQueries,String sortFieldName,Integer sortRule,int start,int rows,
                             String defaultFields[],String[] pointFields,String highlightField,
                             String highlightSimplePre,String highlightSimpleLast){
        //查询
        SolrQuery solrQuery = new SolrQuery();
        //关键词
        solrQuery.setQuery(queryStr);

        //过滤条件
//        solrQuery.setFilterQueries("author_name:出山","filesize:[* TO 4000000]");
        if(filterQueries!=null && filterQueries.length>0) {
            solrQuery.setFilterQueries(filterQueries);
        }

        //排序
        if(sortFieldName != null && sortRule != null) {
            if (sortRule == SORT_RULE_ASC) {
                solrQuery.addSort(sortFieldName, SolrQuery.ORDER.asc);
            } else if (sortRule == SORT_RULE_DESC) {
                solrQuery.addSort(sortFieldName, SolrQuery.ORDER.desc);
            }
        }
        //分页
        solrQuery.setStart(start);
        solrQuery.setRows(rows);
        //默认域
        if(defaultFields != null && defaultFields.length > 0) {
            solrQuery.set("df", defaultFields);
        }
        //只查询指定域
        if(pointFields != null && pointFields.length > 0){
            solrQuery.set("fl",pointFields);
        }
        //高亮
        if(highlightField != null){
            //打开开关
            solrQuery.setHighlight(true);
            //指定高亮域
            solrQuery.addHighlightField(highlightField);
            //前缀
            solrQuery.setHighlightSimplePre(highlightSimplePre);
            //后缀
            solrQuery.setHighlightSimplePost(highlightSimpleLast);
        }



        //执行查询
        QueryResponse response = null;
        try {
            response = client.query(solrQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMapping(response,highlightField);
    }

    /**
     * 查询结果集映射
     * @param response
     * @return
     */
    private SolrBean<T> resultMapping(QueryResponse response,String highlightField) {
        //查询对象
        SolrBean<T> solrBean = new SolrBean<T>();
        //文档结果集
        SolrDocumentList docs = response.getResults();

        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        Long numFound = docs.getNumFound();
        solrBean.setFoundNum(numFound);
        Map<String,T> objectList = new HashMap<String,T>();
        //遍历每个document对象   也就是对应着每一条数据库记录
        for(SolrDocument doc:docs){
            //高亮信息
            if(highlighting != null){
                Map<String, List<String>> map1 = highlighting.get(doc.get("id"));
                List<String> list = map1.get(highlightField);
                if(list != null) {
                    solrBean.setHighlight(list.get(0));
                }
            }
            System.out.println(doc);
            //得到id
            String id = String.valueOf(doc.get("id"));
//            System.out.println("id："+id);

            //创建一个对象用来保存数据
            T object = null;
            try {
                object = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            //得到所有的属性
            Field[] fields = clazz.getDeclaredFields();
            for (Field field:fields){
                String fieldName = field.getName();
                //得到数据库字段   也是域名
                String sqlString = StringUtils.humpToLine(fieldName);

                //得到set方法名
                String methodName = StringUtils.toSetter(fieldName);

                //得到属性的类型
                Class type = field.getType();

                //得到get方法对象
                Method method = null;
                try {
                    method = clazz.getMethod(methodName,type);

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                Object val = doc.get(sqlString);

                //映射
                mapping(object, type, method, val);
            }

            objectList.put(id,object);
        }
        solrBean.setSolrBeanMap(objectList);
        return solrBean;
    }

    /**
     * 映射
     * @param object
     * @param type
     * @param method
     * @param val
     */
    private void mapping(T object, Class type, Method method, Object val) {
        if(val != null) {
            String value  = String.valueOf(val);
            //调用对象方法 设置值
            try {
                //得到类型名
                String attrType = type.getName();
                g2:
                switch (attrType) {
                    case "java.math.BigDecimal":
                        method.invoke(object, BigDecimal.valueOf(Double.valueOf(value)));
                        break;
                    case "java.lang.Integer":
                        method.invoke(object, Integer.parseInt(value));
                        break g2;
                    case "java.lang.String":
                        method.invoke(object, value);
                        break g2;
                    case "java.lang.Long":
                        method.invoke(object, Long.parseLong(value));
                        break g2;
                    case "java.sql.Date":
                        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        method.invoke(object, new java.sql.Date(dateformat.parse(value).getTime()));
                        break g2;
                    case "java.sql.Timestamp":
                        method.invoke(object, Timestamp.valueOf(value));
                        break g2;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

}
