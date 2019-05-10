package com.hc.kugou.solr;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Date:2019/5/4
 * @Description:com.hc.kugou.bean
 * @Version:1.0
 */
@Data
public class SolrBean<T> implements Serializable {
    /**
     * 结果集
     */
    private List<T> solrBeanList;

    /**
     * 查询结果条数
     */
    private Long foundNum;

    /**
     * 高亮显示
     */
    private String highlight;
}
